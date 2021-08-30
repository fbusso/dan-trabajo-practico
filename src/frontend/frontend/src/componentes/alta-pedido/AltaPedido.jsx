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
    InputLeftElement,
    InputRightElement,
    IconButton,
    Heading,
    Text,
    Table,
    Thead,
    Tbody,
    Tr,
    Th,
    Td,
    TableCaption,
    useToast,
    Tooltip
} from '@chakra-ui/react'
import './alta-pedido.css'
import { SearchIcon, AddIcon, DeleteIcon, RepeatIcon } from '@chakra-ui/icons'
import { Link, useHistory } from 'react-router-dom'
import axios from 'axios'
import Cookies from 'js-cookie'
import keycloak_config from '../../keycloak'
import { path } from '../../pathConfig'

export const AltaPedido = () => {
    const toast = useToast()
    const history = useHistory()
    const COLOR = 'yellow'
    const token = Cookies.get('token')
    const [obraSeleccionada, setObraSeleccionada] = useState({})
    const { isOpen, onOpen, onClose } = useDisclosure()
    const [obras, setObras] = useState([])
    const [pedidoForm, setPedidoForm] = useState({
        obra: null,
        fechaPedido: null,
        detallePedido: [],
    })
    const [detalleForm, setDetalleForm] = useState({
        producto: {
            descripcion: ''
        },
        cantidad: 0,
        precio: null,
    })
    let [auth, setAuth] = useState({
        keycloak: null,
        authenticated: false,
    })
    const keycloak = keycloak_config

    let handleChange = (e, form) => {

        if(e.target.name === 'producto'){
            form((prev) => ({
                ...prev,
                producto: {
                    descripcion: e.target.value
                },
            }))
        } else {
            form((prev) => ({
                ...prev,
                [e.target.name]: e.target.value,
            }))
        }
    }

    let submitDetalle = () => {
        if (detalleForm.producto.descripcion && detalleForm.cantidad) {
            setPedidoForm((prev) => ({
                ...prev,
                detallePedido: [...prev.detallePedido, detalleForm],
            }))
        }
    }

    let deleteRow = (index) => {
        setPedidoForm((prev) => ({
            ...prev,
            detallePedido: prev.detallePedido.filter((_, i) => i !== index),
        }))
    }

    
    let validate = () => {
        let errors = []

        if(!pedidoForm.obra){
            errors.push("El campo 'Obra' no puede ser vacío")
        }

        if(!pedidoForm.fechaPedido){
            errors.push("El campo 'FechaPedido' no puede ser vacío")
        }

        if(pedidoForm.detallePedido.length === 0){
            errors.push("No puede dar de alta un pedido sin materiales asociados")
        }
        return errors
    }

    let submitPedidoForm = async () => {
        validate()
        console.log(obraSeleccionada)
        let body = {
            ...pedidoForm,
            obra: {
                descripcion: obraSeleccionada.descripcion,
                clienteId: obraSeleccionada.cliente?.id,
            }

        }
        //let body = {...pedidoForm, obra: {clienteId: obra}}
        let response = await axios
            .post(`${path.PEDIDO}/pedido`, body, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                withCredentials: true,
            })
            .catch((err) => console.log(err.response))
        if (response) {
            setTimeout(() => history.push('/'), 2500)
            return toast({
                title: 'El pedido se creó exitosamente.',
                description: 'Será redireccionado a la pantalla de inicio.',
                status: 'success',
                isClosable: true,
            })
        } else {
            return toast({
                title: 'Hubo un error creando el pedido, intente nuevamente.',
                description: (
                    <div>
                        <ul>
                            {validate().map((error, i) => (
                                <li key={i}>{error}</li>
                            ))}
                        </ul>
                    </div>
                ),
                status: 'error',
                isClosable: true,
            })
        }
    }

    let loadObras = async () => {
        let response = await axios
            .get(`${path.USUARIO}/obras`, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                withCredentials: true,
            })
            .catch((err) => console.log(err.response))
        response && setObras(response.data?.content)
    }

    useEffect(() => {
        let getSessionData = async () => {
            let token = await keycloak.token
            Cookies.set('token', token)
        }
        keycloak.init({ onLoad: 'login-required' }).then((authenticated) => {
            setAuth({ keycloak, authenticated })
            getSessionData()
        })
    }, [keycloak])

    let isSelected = (obra) => obra.id === obraSeleccionada.id

    let submitSelection = () => {
        document.getElementById('obra').value = obraSeleccionada.id
        setPedidoForm((prev) => ({
            ...prev,
            obra: obraSeleccionada.id,
        }))
    }

    return auth.keycloak && auth.authenticated ? (
        <>
            <div className="alta-pedido-main-col">
                <div className="alta-pedido-container">
                    <Stack spacing={5}>
                        <Heading>Alta pedido</Heading>
                        <InputGroup size="md">
                            <Input
                                onChange={(event) =>
                                    handleChange(event, setPedidoForm)
                                }
                                id="obra"
                                name="obra"
                                className="alta-pedido-input"
                                type="number"
                                placeholder="Obra asociada"
                            />
                            <InputRightElement
                                className="alta-pedido-search"
                                width="4.5rem"
                            >
                                <IconButton
                                    onClick={() => {
                                        onOpen()
                                        loadObras()
                                    }}
                                    h="1.75rem"
                                    size="sm"
                                    aria-label="Search database"
                                    icon={<SearchIcon />}
                                />
                            </InputRightElement>
                        </InputGroup>
                        <Text>Fecha de envio</Text>
                        <Input
                            onChange={(event) =>
                                handleChange(event, setPedidoForm)
                            }
                            id="fechaPedido"
                            name="fechaPedido"
                            style={{ marginTop: 0 }}
                            type="date"
                            placeholder="Fecha de envio"
                        />
                    </Stack>

                    <div className="detalle">
                        <Stack
                            spacing={5}
                            id="alta-pedido-detalle"
                            className="detalle-stack"
                        >
                            <Text>Detalle del pedido</Text>
                            <Input
                                id="producto"
                                name="producto"
                                onChange={(event) =>
                                    handleChange(event, setDetalleForm)
                                }
                                className="alta-pedido-detalle-input"
                                type="text"
                                placeholder="Material"
                            />
                            <Input
                                id="cantidad"
                                name="cantidad"
                                onChange={(event) =>
                                    handleChange(event, setDetalleForm)
                                }
                                className="alta-pedido-detalle-input"
                                type="number"
                                placeholder="Cantidad"
                            />
                            <InputGroup>
                                <InputLeftElement
                                    pointerEvents="none"
                                    color="gray.300"
                                    fontSize="1.2em"
                                    children="$"
                                />
                                <Input
                                    id="precio"
                                    name="precio"
                                    onChange={(event) =>
                                        handleChange(event, setDetalleForm)
                                    }
                                    className="alta-pedido-detalle-input"
                                    type="text"
                                    placeholder="Precio"
                                />
                            </InputGroup>
                            <IconButton
                                onClick={submitDetalle}
                                className="alta-pedido-plus-icon"
                                variant="solid"
                                colorScheme={COLOR}
                                aria-label="Add element"
                                icon={<AddIcon />}
                            />
                        </Stack>
                    </div>
                </div>
                <div className="detalle-container">
                    <Table variant="simple">
                        <TableCaption>
                            Materiales asociados al pedido
                        </TableCaption>
                        <Thead>
                            <Tr>
                                <Th>
                                    <div className="detalle-align-center">
                                        Material
                                    </div>
                                </Th>
                                <Th>
                                    <div className="detalle-align-center">
                                        Cantidad
                                    </div>
                                </Th>
                                <Th>
                                    <div className="detalle-align-center">
                                        Precio
                                    </div>
                                </Th>
                                <Th></Th>
                            </Tr>
                        </Thead>
                        <Tbody>
                            {pedidoForm.detallePedido.map((m, n) => {
                                return (
                                    <Tr className="detalle-item" key={n}>
                                        <Td>
                                            <div className="detalle-align-center">
                                                {m.producto.descripcion}
                                            </div>
                                        </Td>
                                        <Td>
                                            <div className="detalle-align-center">
                                                {m.cantidad}
                                            </div>
                                        </Td>
                                        <Td>
                                            <div className="detalle-align-center">
                                                $ {m.precio}
                                            </div>
                                        </Td>
                                        <Td>
                                            <div className="detalle-align-center">
                                                <IconButton
                                                    onClick={() => deleteRow(n)}
                                                    id={'delete-btn' + n}
                                                    className="delete-btn"
                                                    h="1.75rem"
                                                    size="sm"
                                                    aria-label="Delete item"
                                                    colorScheme="red"
                                                    icon={<DeleteIcon />}
                                                />
                                            </div>
                                        </Td>
                                    </Tr>
                                )
                            })}
                        </Tbody>
                    </Table>
                </div>
                <div className="control-btn-group">
                    <Link className="link-home" to="/">
                        <Button variant="solid" size="lg">
                            Atrás
                        </Button>
                    </Link>
                    <Button
                        onClick={submitPedidoForm}
                        colorScheme={COLOR}
                        variant="solid"
                        size="lg"
                    >
                        Aceptar
                    </Button>
                </div>
            </div>

            <Modal isOpen={isOpen} onClose={onClose} isCentered>
                <ModalOverlay />
                <ModalContent className="alta-pedido-lista-obras">
                    <ModalHeader>Obras</ModalHeader>
                    <ModalCloseButton />
                    <ModalBody className="alta-pedido-modal-body">
                        { obras?.length ? (
                        <Table>
                            <Thead>
                                <Tr>
                                    <Th>Id</Th>
                                    <Th>Descripcion</Th>
                                    <Th>Direccion</Th>
                                    <Th>Latitud</Th>
                                    <Th>Longitud</Th>
                                    <Th>Superficie</Th>
                                    <Th>Tipo obra</Th>
                                </Tr>
                            </Thead>
                            <Tbody>
                                {
                                    obras.map((e, i) => (
                                        <Tr
                                            key={i}
                                            onClick={() =>
                                                setObraSeleccionada(e)
                                            }
                                            className={
                                                isSelected(e)
                                                    ? 'entity-list entity-list-focus'
                                                    : 'entity-list'
                                            }
                                        >
                                            <Td>{e.id}</Td>
                                            <Td>{e.descripcion}</Td>
                                            <Td>{e.direccion}</Td>
                                            <Td>{e.latitud}</Td>
                                            <Td>{e.longitud}</Td>
                                            <Td>{e.superficie}</Td>
                                            <Td>{e.tipoObra}</Td>
                                        </Tr>
                                    ))}
                            </Tbody>
                        </Table>) : (
                                <div className="home-reload">
                                    <Tooltip hasArrow label="Recargar obras">
                                        <IconButton
                                            variant="ghost"
                                            size="lg"
                                            icon={<RepeatIcon />}
                                            onClick={loadObras}
                                            style={{ borderRadius: '50%' }}
                                        />
                                    </Tooltip>
                                    <Text color="grey">
                                        No se encontraron obras
                                    </Text>
                                </div>
                            )}
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
        </>
    ) : null
}
