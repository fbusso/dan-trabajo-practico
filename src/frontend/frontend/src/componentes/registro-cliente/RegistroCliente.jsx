import React, { useState, useEffect } from 'react'
import {
    Input,
    HStack,
    Button,
    Heading,
    IconButton,
    Select,
    Table,
    Thead,
    Tbody,
    Tr,
    Th,
    Td,
    Tooltip,
    TableCaption,
    useToast,
} from '@chakra-ui/react'
import './registro-cliente.css'
import { Link, useHistory} from 'react-router-dom'
import { AddIcon, DeleteIcon } from '@chakra-ui/icons'
import { path } from '../../pathConfig'
import axios from 'axios'
import Cookies from 'js-cookie'
import keycloak_config from '../../keycloak'

let initialValueObra = {
    descripcion: '',
    direccion: '',
    latitud: null,
    longitud: null,
    superficie: null,
    tipoObra: 'REFORMA',
}

export const RegistroCliente = () => {
    const history = useHistory()
    const toast = useToast()
    const COLOR = 'yellow'
    const token = Cookies.get('token')
    let [auth, setAuth] = useState({
        keycloak: null,
        authenticated: false,
    })
    const keycloak = keycloak_config
    const [clienteForm, setClienteForm] = useState({
        cuit: '',
        razonSocial: '',
        mail: '',
        obras: [],
    })

    const [obrasForm, setObrasForm] = useState(initialValueObra)

    let formatCUIT = (value) => {
        if (!value) return value
        let cuit = value.replace(/[^\d]/g, '')
        let length = cuit.length
        if (length < 3) return cuit
        if (length < 11) {
            return `${cuit.slice(0, 2)}-${cuit.slice(2)}`
        }
        return `${cuit.slice(0, 2)}-${cuit.slice(2, 10)}-${cuit.slice(10, 11)}`
    }

    let handleChange = (e, form) => {
        let fieldValue = e.target.value
        let fieldName = e.target.name
        if (fieldName === 'cuit') {
            document.getElementById('cuit').value = formatCUIT(fieldValue)
            fieldValue = fieldValue.split('-').join('')
        }
        form((prev) => ({
            ...prev,
            [fieldName]: fieldValue,
        }))
    }

    let deleteRow = (index) => {
        setClienteForm((prev) => ({
            ...prev,
            obras: prev.obras.filter((_, i) => i !== index),
        }))
    }

    let handleSubmit = () => {
        if (
            obrasForm.descripcion &&
            obrasForm.direccion &&
            obrasForm.latitud &&
            obrasForm.longitud &&
            obrasForm.superficie &&
            obrasForm.tipoObra &&
            !isNaN(obrasForm.latitud) &&
            !isNaN(obrasForm.longitud && !isNaN(obrasForm.superficie))
        ) {
            setClienteForm((prev) => ({
                ...prev,
                obras: [...prev.obras, obrasForm],
            }))
        }
    }

    let clearObraForm = () => {
        let ids = [
            'descripcion',
            'direccion',
            'latitud',
            'longitud',
            'superficie',
        ]
        ids.forEach((id) => (document.getElementById(id).value = ''))
        document.getElementById('tipoObra').value = 'REFORMA'
        setObrasForm(initialValueObra)
    }

    let validate = () => {
        let errors = []

        if (!clienteForm.mail) {
            errors.push("El campo 'Email' no puede ser vacío")
        }
        if (!clienteForm.razonSocial) {
            errors.push("El campo 'Razon social' no puede ser vacío")
        }
        if (!clienteForm.cuit) {
            errors.push("El campo 'CUIT' no puede ser vacío")
        }
        if (clienteForm.cuit && clienteForm.cuit.length !== 12) {
            errors.push(
                "El campo 'CUIT' no tiene la cantidad de caracteres necesarios"
            )
        }
        if (
            clienteForm.mail &&
            !/^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/.test(clienteForm.mail)
        ) {
            errors.push("El campo 'Email' no tiene un formato válido")
        }

        return errors
    }

    let submitClienteForm = async () => {
        let body = { obras: clienteForm.obras, cliente: {cuit: clienteForm.cuit.slice(0, 11), razonSocial: clienteForm.razonSocial, mail: clienteForm.mail}  }
        let requestError = validate()

        let response = await axios
            .post(`${path.USUARIO}/clientes`, body, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                withCredentials: true,
            })
            .catch((err) => {(err.response?.data && typeof err.response.data === 'string') && requestError.push(err.response?.data)})

        if(response){
            setTimeout(() => history.push('/'), 2500)
            return toast({
                title: 'El cliente se ha registrado con éxito.',
                description: 'Será redireccionado a la pantalla de inicio.',
                status: 'success',
                isClosable: true,
            })
        }
        else{
            toast({
                title: 'Hay errores en el registro del cliente, intentelo nuevamente.',
                description: (
                    <div>
                        <ul>
                            {requestError.map((error, i) => (
                                <li key={i}>{error.toString()}</li>
                            ))}
                        </ul>
                    </div>
                ),
                status: 'error',
                isClosable: true,
            })
        } 
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

    return auth.keycloak && auth.authenticated ? (
        <div className="registro-cliente-main-col">
            <Heading className="registro-cliente-heading">
                Registro cliente
            </Heading>
            <div className="registro-cliente-container">
                <div className="registro-cliente-col-1">
                    <HStack spacing={5}>
                        <Input
                            id="cuit"
                            name="cuit"
                            onChange={(event) =>
                                handleChange(event, setClienteForm)
                            }
                            className="registro-cliente-input"
                            type="text"
                            placeholder="CUIT"
                        />
                        <Input
                            id="razonSocial"
                            name="razonSocial"
                            onChange={(event) =>
                                handleChange(event, setClienteForm)
                            }
                            className="registro-cliente-input"
                            type="text"
                            placeholder="Razon social"
                        />
                        <Input
                            id="mail"
                            name="mail"
                            onChange={(event) =>
                                handleChange(event, setClienteForm)
                            }
                            autoComplete="off"
                            className="registro-cliente-input"
                            type="text"
                            placeholder="Email"
                        />
                    </HStack>
                </div>
                <div className="registro-cliente-col-2">
                    <Table className="registro-cliente-table">
                        <TableCaption>Obras asociadas al cliente</TableCaption>
                        <Thead>
                            <Tr>
                                <Th className="registro-cliente-th">
                                    Descripcion
                                </Th>
                                <Th className="registro-cliente-th">
                                    Direccion
                                </Th>
                                <Th className="registro-cliente-th">Latitud</Th>
                                <Th className="registro-cliente-th">
                                    Longitud
                                </Th>
                                <Th className="registro-cliente-th">
                                    Superficie
                                </Th>
                                <Th className="registro-cliente-th">
                                    Tipo obra
                                </Th>
                                <Th className="registro-cliente-del" />
                            </Tr>
                        </Thead>
                        <Tbody>
                            <Tr>
                                <Td className="registro-cliente-td">
                                    <Input
                                        id="descripcion"
                                        onChange={(event) =>
                                            handleChange(event, setObrasForm)
                                        }
                                        name="descripcion"
                                        className="registro-cliente-obra-input"
                                        type="text"
                                        placeholder="Descripcion"
                                    />
                                </Td>
                                <Td className="registro-cliente-td">
                                    <Input
                                        id="direccion"
                                        onChange={(event) =>
                                            handleChange(event, setObrasForm)
                                        }
                                        name="direccion"
                                        className="registro-cliente-obra-input"
                                        type="text"
                                        placeholder="Direccion"
                                    />
                                </Td>
                                <Td className="registro-cliente-td">
                                    <Input
                                        id="latitud"
                                        onChange={(event) =>
                                            handleChange(event, setObrasForm)
                                        }
                                        name="latitud"
                                        className="registro-cliente-obra-input"
                                        type="number"
                                        placeholder="Latitud"
                                    />
                                </Td>
                                <Td className="registro-cliente-td">
                                    <Input
                                        id="longitud"
                                        onChange={(event) =>
                                            handleChange(event, setObrasForm)
                                        }
                                        name="longitud"
                                        className="registro-cliente-obra-input"
                                        type="number"
                                        placeholder="Longitud"
                                    />
                                </Td>
                                <Td className="registro-cliente-td">
                                    <Input
                                        id="superficie"
                                        onChange={(event) =>
                                            handleChange(event, setObrasForm)
                                        }
                                        name="superficie"
                                        className="registro-cliente-obra-input"
                                        type="number"
                                        placeholder="Superficie"
                                    />
                                </Td>
                                <Td className="registro-cliente-td">
                                    <Select
                                        id="tipoObra"
                                        name="tipoObra"
                                        onChange={(event) =>
                                            handleChange(event, setObrasForm)
                                        }
                                    >
                                        <option default value="REFORMA">
                                            REFORMA
                                        </option>
                                        <option value="CASA">CASA</option>
                                        <option value="EDIFICIO">
                                            EDIFICIO
                                        </option>
                                        <option value="VIAL">VIAL</option>
                                    </Select>
                                </Td>
                                <Td className="registro-cliente-td">
                                    <Tooltip
                                        hasArrow
                                        label="Agregar obra"
                                        aria-label="tooltip"
                                    >
                                        <IconButton
                                            onClick={() => {
                                                handleSubmit()
                                                clearObraForm()
                                            }}
                                            className="registro-cliente-plus-icon"
                                            variant="solid"
                                            colorScheme={COLOR}
                                            aria-label="Add element"
                                            icon={<AddIcon />}
                                        />
                                    </Tooltip>
                                </Td>
                            </Tr>
                            {clienteForm.obras.map((m, i) => (
                                <Tr key={i}>
                                    <Td className="registro-cliente-td">
                                        {m.descripcion}
                                    </Td>
                                    <Td className="registro-cliente-td">
                                        {m.direccion}
                                    </Td>
                                    <Td className="registro-cliente-td">
                                        {m.latitud}
                                    </Td>
                                    <Td className="registro-cliente-td">
                                        {m.longitud}
                                    </Td>
                                    <Td className="registro-cliente-td">
                                        {m.superficie}
                                    </Td>
                                    <Td className="registro-cliente-td">
                                        {m.tipoObra}
                                    </Td>
                                    <Td className="registro-cliente-td">
                                        <IconButton
                                            onClick={() => deleteRow(i)}
                                            id={'delete-btn' + i}
                                            className="registro-cliente-delete-btn"
                                            h="1.75rem"
                                            size="sm"
                                            aria-label="Delete item"
                                            colorScheme="red"
                                            icon={<DeleteIcon />}
                                        />
                                    </Td>
                                </Tr>
                            ))}
                        </Tbody>
                    </Table>
                    <div className="registro-cliente-btn-row"></div>
                </div>
            </div>
            <div className="registro-cliente-btn-group">
                <Link className="link-home" to="/">
                    <Button variant="solid" size="lg">
                        Atrás
                    </Button>
                </Link>
                <Button
                    onClick={submitClienteForm}
                    colorScheme={COLOR}
                    variant="solid"
                    size="lg"
                >
                    Aceptar
                </Button>
            </div>
        </div>
    ) : null
}
