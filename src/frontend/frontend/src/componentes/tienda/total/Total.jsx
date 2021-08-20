import React from "react"
import { useSelector, useDispatch } from "react-redux"
import { Button, Text } from "@chakra-ui/react"
import { clearAction } from "../../../reducers/CartSlice"
import "./total.css"

export let Total = () => {
   const dispatch = useDispatch()
   const cart = useSelector((state) => state)
   let totalPrice = cart.reduce((acum, elem) => {
      acum += elem.quantity * elem.price
      return acum
   }, 0)

   return (
		 <div className="cart-total">
				<div className="total-value">
					<Text variant="h5">Total:</Text>
					<Text variant="h5">${totalPrice}</Text>
				</div>
				<Button color="secondary" onClick={() => dispatch(clearAction())}>
					Limpiar pedido
				</Button>
		 </div>
   )
}
