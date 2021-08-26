import { clearAction } from '../../../reducers/CartSlice'
import { useSelector, useDispatch } from 'react-redux'
import { Button, Text } from '@chakra-ui/react'
import { useHistory } from 'react-router-dom'
import { path } from '../../../pathConfig'
import Cookies from 'js-cookie'
import axios from 'axios'
import React from 'react'
import './total.css'

export let Total = () => {
    const cart = useSelector((state) => state)
    const history = useHistory()
    const token = Cookies.get('token')
    const dispatch = useDispatch()
    let totalPrice = cart.reduce((acum, elem) => {
        acum += elem.cantidad * elem.precio
        return acum
    }, 0)

    const handleSubmit = () => {
        let detallePedido = cart && cart.map((prod) => ({
            cantidad: prod.cantidad,
            precio: prod.precio,
            producto: { id: prod.id, descripcion: prod.descripcion},
        }))

        let body = {detallePedido}

        console.log(body)
        
        const postDetalle = async () => {
			const response = await axios
			.post(`${path.PAGO}`, body, {
                headers: {
                    'Content-Type': 'application/json',
                }
			})
			.catch((err) => {console.log(err.response?.data)})
			window.location = response?.data
		}
		postDetalle()
    }

    return (
        <div className="cart-total">
            <div className="total-value">
                <Text variant="h5">Total:</Text>
                <Text variant="h5">${totalPrice}</Text>
            </div>
            <div className="total-button-group">
                <Button
                    colorScheme="blue"
                    variant="outline"
                    onClick={() => dispatch(clearAction())}
                >
                    Limpiar pedido
                </Button>
                <Button colorScheme="blue" onClick={handleSubmit}>
                    Pagar pedido
                </Button>
            </div>
        </div>
    )
}
