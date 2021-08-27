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
    Table,
    Thead,
    Tbody,
    Th,
    Td,
    Tr,
    useToast,
    Text,
    Tooltip
} from '@chakra-ui/react'
import keycloak_config from '../../keycloak'
import { SearchIcon, RepeatIcon } from '@chakra-ui/icons'
import { Link, useHistory } from 'react-router-dom'
import { path } from '../../pathConfig'
import axios from 'axios'
import Cookies from 'js-cookie'
import './registro-obra.css'

export const RegistroObra = () => {
    const history = useHistory()
    const toast = useToast()
    const { isOpen, onOpen, onClose } = useDisclosure()
    const [clienteSeleccionado, setClienteSeleccionado] = useState({})
    let [clientes, setClientes] = useState([])
    const [obraForm, setObraForm] = useState({
        descripcion: '',
        direccion: '',
        latitud: 0,
        longitud: 0,
        superficie: 0,
        tipoObra: ''
    })
    const [ cliente, setCliente ] = useState({
        clienteId: null
    })
    const token = Cookies.get('token')
    const COLOR = 'yellow'
    let [auth, setAuth] = useState({
        keycloak: null,
        authenticated: false,
    })
    const keycloak = keycloak_config

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

    let handleChange = (e, form) => {
        form((prev) => ({
            ...prev,
            [e.target.name]: e.target.value,
        }))
    }

    let loadClientes = async () => {
        let response = await axios
            .get(`${path.USUARIO}/clientes`, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                withCredentials: true,
            })
            .catch((err) => console.log(err.response))
        response && setClientes(response.data)
    }

    let submitSelection = () => {
        document.getElementById('clienteId').value = clienteSeleccionado.id
        setCliente((prev) => ({
            ...prev,
            clienteId: clienteSeleccionado.id,
        }))
    }

    let isSelected = (cliente) => cliente.id === clienteSeleccionado.id

    let validate = () => {
        let errors = []

        if(!obraForm.descripcion){
            errors.push("El campo 'Descripcion' no puede ser vacío")
        }
        if(!obraForm.direccion){
            errors.push("El campo 'Direccion' no puede ser vacío")
        }
        if(!obraForm.latitud){
            errors.push("El campo 'Latitud' no puede ser vacío")
        }
        if(!obraForm.longitud){
            errors.push("El campo 'Longitud' no puede ser vacío")
        }
        if(!obraForm.superficie){
            errors.push("El campo 'Superficie' no puede ser vacío")
        }
        if(obraForm.latitud && isNaN(obraForm.latitud)){
            errors.push("El campo 'Latitud' debe ser un número")
        }
        if(obraForm.longitud && isNaN(obraForm.longitud)){
            errors.push("El campo 'Longitud' debe ser un número")
        }
        if(obraForm.superficie && isNaN(obraForm.superficie)){
            errors.push("El campo 'Superficie' debe ser un número")
        }
        return errors
    }

    let submitForm = async () => {
        validate()
        let tipoObraAux = obraForm.tipoObra || 'REFORMA'
        let body = {...obraForm, tipoObra: tipoObraAux}
        let response = await axios
            .post(`${path.USUARIO}/obras?clienteId=${cliente.clienteId}`, body, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                withCredentials: true,
            })
            .catch((err) => console.log(err.response.data))

        if (response) {
            setTimeout(() => history.push('/'), 2500)
            return toast({
                title: 'La obra se ha registrado exitosamente.',
                description: 'Será redireccionado a la pantalla de inicio.',
                status: 'success',
                isClosable: true,
            })
        } else {
            return toast({
                title: 'Hubo un error creando su obra, intente nuevamente.',
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

    return auth.keycloak && auth.authenticated ? (
        <div className="registro-obra-container">
            <Stack spacing={5}>
                <Heading>Registro obra</Heading>
                <div className="register-obra-row">
                    <div className="register-obra-col1">
                        <Input
                            id="descripcion"
                            name="descripcion"
                            className="registro-obra-input"
                            onChange={(event) =>
                                handleChange(event, setObraForm)
                            }
                            type="text"
                            placeholder="Descripcion"
                        />
                        <Input
                            id="direccion"
                            name="direccion"
                            className="registro-obra-input"
                            onChange={(event) =>
                                handleChange(event, setObraForm)
                            }
                            type="text"
                            placeholder="Direccion"
                        />
                        <InputGroup size="md">
                            <Input
                                id="clienteId"
                                name="clienteId"
                                onChange={(event) =>
                                    handleChange(event, setCliente)
                                }
                                className="registro-obra-input"
                                type="text"
                                placeholder="Cliente asociado"
                            />
                            <InputRightElement
                                className="registro-obra-search"
                                width="4.5rem"
                            >
                                <IconButton
                                    onClick={() => {
                                        onOpen()
                                        loadClientes()
                                    }}
                                    h="1.75rem"
                                    size="sm"
                                    aria-label="Search database"
                                    icon={<SearchIcon />}
                                />
                            </InputRightElement>
                        </InputGroup>
                    </div>
                    <div className="register-obra-col2">
                        <Input
                            id="latitud"
                            name="latitud"
                            className="registro-obra-input"
                            onChange={(event) =>
                                handleChange(event, setObraForm)
                            }
                            type="text"
                            placeholder="Latitud"
                        />
                        <Input
                            id="longitud"
                            name="longitud"
                            className="registro-obra-input"
                            onChange={(event) =>
                                handleChange(event, setObraForm)
                            }
                            type="text"
                            placeholder="Longitud"
                        />
                        <Input
                            id="superficie"
                            name="superficie"
                            className="registro-obra-input"
                            onChange={(event) =>
                                handleChange(event, setObraForm)
                            }
                            type="text"
                            placeholder="Superficie"
                        />
                        <Select
                            id="tipoObra"
                            name="tipoObra"
                            onChange={(event) =>
                                handleChange(event, setObraForm)
                            }
                        >
                            <option default value="REFORMA">
                                REFORMA
                            </option>
                            <option value="CASA">CASA</option>
                            <option value="EDIFICIO">EDIFICIO</option>
                            <option value="VIAL">VIAL</option>
                        </Select>
                    </div>
                </div>
                <div className="control-btn-group">
                    <Link className="link-home" to="/">
                        <Button variant="solid" size="lg">
                            Atrás
                        </Button>
                    </Link>
                    <Button colorScheme={COLOR} onClick={submitForm} variant="solid" size="lg">
                        Aceptar
                    </Button>
                </div>
            </Stack>

            <Modal isOpen={isOpen} onClose={onClose} isCentered>
                <ModalOverlay />
                <ModalContent>
                    <ModalHeader>Clientes</ModalHeader>
                    <ModalCloseButton />
                    <ModalBody className="registro-obra-modal-body">
                        {clientes?.length ?(
                        <Table>
                            <Thead>
                                <Tr>
                                    <Th>Id Cliente</Th>
                                    <Th>Razon social</Th>
                                    <Th>Cuit</Th>
                                    <Th>Mail</Th>
                                </Tr>
                            </Thead>
                            <Tbody>
                                {clientes.map((e, i) => (
                                        <Tr
                                            key={i}
                                            onClick={() =>
                                                setClienteSeleccionado(e)
                                            }
                                            className={
                                                isSelected(e)
                                                    ? 'entity-list entity-list-focus'
                                                    : 'entity-list'
                                            }
                                        >
                                            <Td>{e.id}</Td>
                                            <Td>{e.razonSocial}</Td>
                                            <Td>{e.cuit}</Td>
                                            <Td>{e.mail}</Td>
                                        </Tr>
                                    ))}
                            </Tbody>
                        </Table>) : (
                                <div className="home-reload">
                                    <Tooltip hasArrow label="Recargar clientes">
                                        <IconButton
                                            variant="ghost"
                                            size="lg"
                                            icon={<RepeatIcon />}
                                            onClick={loadClientes}
                                            style={{ borderRadius: '50%' }}
                                        />
                                    </Tooltip>
                                    <Text color="grey">
                                        No se encontraron clientes
                                    </Text>
                                </div>
                            )}
                    </ModalBody>
                    <ModalFooter>
                        <div className="control-btn-group">
                            <Button variant="solid" size="lg" onClick={onClose}>
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
