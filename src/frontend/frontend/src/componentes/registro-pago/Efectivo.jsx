import React, { useState, useEffect } from 'react'
import {
    Input,
    Stack,
    Button,
    Heading,
    Textarea,
    useToast,
} from '@chakra-ui/react'
import keycloak_config from '../../keycloak'
import './medio-pago.css'
import { Link, useParams, useHistory } from 'react-router-dom'
import { path } from '../../pathConfig'
import axios from 'axios'
import Cookies from 'js-cookie'

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

export const Efectivo = () => {
    const history = useHistory()
    const toast = useToast()
    const token = Cookies.get('token')
    const COLOR = 'yellow'
    const { idPedido } = useParams()
    let [pagoForm, setPagoForm] = useState({
        pedidoId: parseInt(idPedido),
        fechaPago: getFecha(),
        medioPago: { tipo: 'efectivo' },
    })
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
            keycloak.hasRealmRole('ROLE_COMPRADOR') && history.push('tienda')
            setAuth({ keycloak, authenticated })
            getSessionData()
        })
    }, [keycloak])

    let handleChange = (e) => {
        setPagoForm((prev) => ({
            ...prev,
            medioPago: { ...prev.medioPago, [e.target.name]: e.target.value },
        }))
    }

    let validate = () => {
        let errors = []

        if(!pagoForm.medioPago.numeroRecibo){
            errors.push("El campo 'Numero recibo' no puede ser vacío")
        }

        if(!pagoForm.medioPago.observacion){
            errors.push("El campo 'Observacion ' no puede ser vacío")
        }

        if(pagoForm.medioPago.numeroRecibo && isNaN(pagoForm.medioPago.numeroRecibo)){
            errors.push("El campo 'Numero recibo' debe ser un número")
        }

        return errors
    }


    let handleSubmit = async () => {
        validate()
        let response = await axios
            .post(`${path.CUENTA}/pago`, pagoForm, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                withCredentials: true,
            })
            .catch((err) => console.log(err.response))

        if (response) {
            setTimeout(() => history.push('/'), 1500)
            return toast({
                title: 'El pago se realizó exitosamente.',
                description: 'Será redireccionado a la pantalla de inicio.',
                status: 'success',
                isClosable: true,
            })
        } else {
            return toast({
                title: 'Hubo un error en su pago, intente nuevamente.',
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
        <div className="medio-pago-main-col">
            <div className="medio-pago-container">
                <Stack spacing={3}>
                    <Heading className="medio-pago-heading">
                        Registrar pago: Efectivo
                    </Heading>
                </Stack>

                <div className="medio-pago-form">
                    <Input
                        id="numeroRecibo"
                        name="numeroRecibo"
                        onChange={handleChange}
                        className="medio-pago-input"
                        type="text"
                        placeholder="Nro. Recibo"
                    />
                    <Textarea
                        id="observacion"
                        name="observacion"
                        onChange={handleChange}
                        className="medio-pago-input"
                        type="text"
                        placeholder="Observaciones"
                        resize="none"
                    />
                </div>
            </div>

            <div className="medio-pago-btn-group">
                <div className="control-btn-group">
                    <Link className="link-home" to="/alta/pago">
                        <Button variant="solid" size="lg">
                            Atrás
                        </Button>
                    </Link>
                    <Button
                        onClick={handleSubmit}
                        colorScheme={COLOR}
                        variant="solid"
                        size="lg"
                    >
                        Aceptar
                    </Button>
                </div>
            </div>
        </div>
    ) : null
}
