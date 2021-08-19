import React, { useState, useEffect } from 'react'
import {
    Input,
    Stack,
    Button,
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalFooter,
    ModalBody,
    ModalCloseButton,
    useDisclosure,
    InputGroup,
    InputRightElement,
    IconButton,
    Heading,
    Select,
    Text,
    Table,
    Thead,
    Tbody,
    Tr,
    Th,
    Td,
} from '@chakra-ui/react'
import './registro-pago.css'
import { SearchIcon } from '@chakra-ui/icons'
import { Link } from 'react-router-dom'
import { path } from '../../pathConfig'
import axios from 'axios'
import Cookies from 'js-cookie'
import keycloak_config from '../../keycloak'

let getFecha = () => {
    const today = new Date()
    let fecha = today
        .toLocaleDateString('es-AR')
        .replaceAll('/', '-')
        .split('-')
    let day = fecha[0].length === 1 ? '0' + fecha[0] : fecha[0]
    let month = fecha[1].length === 1 ? '0' + fecha[1] : fecha[1]
    fecha = [fecha[2], month, day].join('-')
    return fecha
}

export const RegistroPago = () => {
    const { isOpen, onOpen, onClose } = useDisclosure()
    const token = Cookies.get('token')
    const COLOR = 'yellow'
    let [pedidoSeleccionado, setPedidoSeleccionado] = useState({})
    let [pedidos, setPedidos] = useState([])
    let [medioPago, setMedioPago] = useState('transferencia')
    let [pagoForm, setPagoForm] = useState({
        pedidoId: null,
        fechaPago: getFecha(),
    })
    let [auth, setAuth] = useState({
        keycloak: null,
        authenticated: false,
    })
    const keycloak = keycloak_config

    useEffect(() => {
        let getSessionData = async () => {
            let token = keycloak.token
            Cookies.set('token', token)
        }
        keycloak.init({ onLoad: 'login-required' }).then((authenticated) => {
            setAuth({ keycloak, authenticated })
            getSessionData()
        })
    }, [keycloak])

    let loadPedidos = async () => {
        let response = await axios
            .get(`${path.PEDIDO}/pedido?page=0&size=5`, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                withCredentials: true,
            })
            .catch((err) => console.log(err.response))
        response && setPedidos(response.data.content)
    }

    const handleSelect = (e) => {
        setMedioPago(e.target.value)
    }

    let handleChange = (e) => {
        setPagoForm((prev) => ({
            ...prev,
            [e.target.name]: e.target.value,
        }))
    }

    let submitSelection = () => {
        document.getElementById('pedidoId').value = pedidoSeleccionado.id
        setPagoForm((prev) => ({
            ...prev,
            pedidoId: pedidoSeleccionado.id,
        }))
    }

    let isSelected = (pedido) => pedido.id === pedidoSeleccionado.id

    return auth.keycloak && auth.authenticated ? (
        <div className="registro-pago-main-col">
            <div className="registro-pago-container">
                <Stack spacing={3}>
                    <Heading className="registro-pago-heading">
                        Registrar pago
                    </Heading>
                    <InputGroup size="md">
                        <Input
                            id="pedidoId"
                            name="pedidoId"
                            onChange={handleChange}
                            className="registro-pago-input"
                            type="text"
                            placeholder="Pedido asociado"
                        />
                        <InputRightElement
                            className="registro-pago-search"
                            width="4.5rem"
                        >
                            <IconButton
                                onClick={() => {
                                    onOpen()
                                    loadPedidos()
                                }}
                                h="1.75rem"
                                size="sm"
                                aria-label="Search database"
                                icon={<SearchIcon />}
                            />
                        </InputRightElement>
                    </InputGroup>
                    <Text>Fecha pago</Text>
                    <Input
                        className="registro-pago-input-disabled"
                        type="text"
                        value={pagoForm.fechaPago}
                        isDisabled
                        variant="filled"
                    />
                    <Text>Medio de pago</Text>
                    <Select id="registro-pago-select" onChange={handleSelect}>
                        <option default value="transferencia">
                            TRANSFERENCIA
                        </option>
                        <option value="efectivo">EFECTIVO</option>
                        <option value="cheque">CHEQUE</option>
                    </Select>
                </Stack>
            </div>

            <div className="control-btn-group">
                <Link className="link-home" to="/">
                    <Button variant="solid" size="lg">
                        Atrás
                    </Button>
                </Link>
                <Link
                    to={
                        pagoForm.pedidoId
                            ? `/alta/pago/${pagoForm.pedidoId}/${medioPago}`
                            : `/alta/pago/`
                    }
                >
                    <Button
                        colorScheme={COLOR}
                        variant="solid"
                        size="lg"
                    >
                        Continuar
                    </Button>
                </Link>
            </div>

            <Modal
                className="registro-pago-lista-pedido"
                isOpen={isOpen}
                onClose={onClose}
                isCentered
            >
                <ModalOverlay />
                <ModalContent>
                    <ModalHeader>Pedidos</ModalHeader>
                    <ModalCloseButton />
                    <ModalBody>
                        <Table>
                            <Thead>
                                <Tr>
                                    <Th>Id</Th>
                                    <Th>Estado</Th>
                                    <Th>Fecha</Th>
                                    <Th>Id Obra</Th>
                                    <Th>Descripcion obra</Th>
                                    <Th>Id Cliente</Th>
                                    <Th>Costo total</Th>
                                </Tr>
                            </Thead>
                            <Tbody>
                                {pedidos &&
                                    pedidos.map((e, i) => (
                                        <Tr
                                            key={i}
                                            id={`pedido-elem-${i}`}
                                            onClick={() =>
                                                setPedidoSeleccionado(e)
                                            }
                                            className={
                                                isSelected(e)
                                                    ? 'entity-list entity-list-focus'
                                                    : 'entity-list'
                                            }
                                        >
                                            <Td>{e.id}</Td>
                                            <Td>{e.estadoPedido}</Td>
                                            <Td>{e.fechaPedido}</Td>
                                            <Td>{e.obra.id}</Td>
                                            <Td>{e.obra.descripcion}</Td>
                                            <Td>{e.obra.clienteId}</Td>
                                            <Td>$ {e.costoTotal}</Td>
                                        </Tr>
                                    ))}
                            </Tbody>
                        </Table>
                    </ModalBody>
                    <ModalFooter>
                        <div className="control-btn-group">
                            <Button variant="solid" onClick={onClose}>
                                Atrás
                            </Button>
                            <Button
                                colorScheme={COLOR}
                                mr={3}
                                onClick={() => {
                                    onClose()
                                    submitSelection()
                                }}
                            >
                                Seleccionar
                            </Button>
                        </div>
                    </ModalFooter>
                </ModalContent>
            </Modal>
        </div>
    ) : null
}
