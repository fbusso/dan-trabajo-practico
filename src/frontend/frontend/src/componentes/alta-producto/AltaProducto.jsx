import React, { useState, useEffect } from 'react'
import {
    Input,
    Stack,
    Button,
    Heading,
    InputGroup,
    InputLeftElement,
    useToast,
    Select
} from '@chakra-ui/react'
import './alta-producto.css'
import { Link, useHistory } from 'react-router-dom'
import keycloak_config from '../../keycloak'
import Cookies from 'js-cookie'
import axios from 'axios'
import { path } from '../../pathConfig'

export const AltaProducto = () => {
    const toast = useToast()
    const history = useHistory()
    const token = Cookies.get('token')
    const [productoForm, setProductoForm] = useState({
        nombre: '',
        precio: null,
        descripcion: '',
        stockActual: 0,
        stockMinimo: 0,
        unidad: ''
    })
    const COLOR = 'yellow'
    let [auth, setAuth] = useState({
        keycloak: null,
        authenticated: false,
    })
    const keycloak = keycloak_config

    let handleChange = (e) => {
        setProductoForm((prev) => ({
            ...prev,
            [e.target.name]: e.target.value,
        }))
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

    let validate = () => {
        let errors = []

        if(!productoForm.nombre){
            errors.push("El campo 'Nombre' no puede ser vacío")
        }
        if(!productoForm.descripcion){
            errors.push("El campo 'Descripcion' no puede ser vacío")
        }
        if(!productoForm.precio){
            errors.push("El campo 'Precio' no puede ser vacío")
        }
        if(!productoForm.stockActual){
            errors.push("El campo 'Stock actual' no puede ser vacío")
        }
        if(!productoForm.stockMinimo){
            errors.push("El campo 'Stock mínimo' no puede ser vacío")
        }
        if(productoForm.precio && isNaN(productoForm.precio)){
            errors.push("El campo 'Precio' debe ser un número")
        }
        if(productoForm.stockActual && isNaN(productoForm.stockActual)){
            errors.push("El campo 'Stock actual' debe ser un número")
        }
        if(productoForm.stockMinimo && isNaN(productoForm.stockMinimo)){
            errors.push("El campo 'Stock mínimo' debe ser un número")
        }

        return errors
    }

    let handleSubmit = async (e) => {
        validate()
        let unidadProducto = productoForm.unidad || 'KILOGRAMOS'
        let body = {...productoForm, unidad: unidadProducto}
        let response =
            await axios.post(`${path.PRODUCTO}/material`,
            body,
            {
                headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`},
                withCredentials: true
            }
            ).catch(err=>console.log(err))
        if (response) {
            setTimeout(() => history.push('/'), 2500)
            return toast({
                title: 'El producto se creó exitosamente.',
                description: 'Será redireccionado a la pantalla de inicio.',
                status: 'success',
                isClosable: true,
            })
        } else {
            return toast({
                title: 'Hubo un error creando el producto, intente nuevamente.',
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
        <div className="alta-producto-container">
            <Stack spacing={5}>
                <Heading>Alta producto</Heading>
                <Input
                    id="nombre"
                    name="nombre"
                    onChange={handleChange}
                    className="registro-cliente-input"
                    type="text"
                    placeholder="Nombre"
                />
                <Input
                    id="descripcion"
                    name="descripcion"
                    onChange={handleChange}
                    className="registro-cliente-input"
                    type="text"
                    placeholder="Descripcion"
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
                        onChange={handleChange}
                        className="registro-cliente-input"
                        type="text"
                        placeholder="Precio"
                    />
                </InputGroup>
                <Input
                    id="stockActual"
                    name="stockActual"
                    onChange={handleChange}
                    className="registro-cliente-input"
                    type="text"
                    placeholder="Stock actual"
                />
                <Input
                    id="stockMinimo"
                    name="stockMinimo"
                    onChange={handleChange}
                    className="registro-cliente-input"
                    type="text"
                    placeholder="Stock mínimo"
                />
                <Select
                    id="unidad"
                    name="unidad"
                    onChange={handleChange}
                >
                    <option default value="KILOGRAMOS">
                        KILOGRAMOS
                    </option>
                    <option value="LITRO">LITRO</option>
                    <option value="UNIDADES">UNIDADES</option>
                    <option value="TONELADAS">TONELADAS</option>
                </Select>
                <div className="control-btn-group">
                    <Link className="link-home" to="/">
                        <Button variant="solid" size="lg">
                            Atrás
                        </Button>
                    </Link>
                    <Button colorScheme={COLOR} onClick={handleSubmit} variant="solid" size="lg">
                        Aceptar
                    </Button>
                </div>
            </Stack>
        </div>
    ) : null
}
