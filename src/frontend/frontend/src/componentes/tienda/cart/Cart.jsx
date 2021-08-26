import React from 'react'
import { useSelector } from "react-redux"
import { CartItem } from '../item/cart-item/CartItem'
import { Text } from '@chakra-ui/react'
import './cart.css'

export let Cart = () => {
	const cartList = useSelector((state) => state)

	return(
		<div>
         {cartList && !!cartList.length && (
					 <div>
						 	<div className="cart-description">
								<Text>Nombre</Text>
								<Text>Precio</Text>
								<Text>Cantidad</Text>
								<Text>Borrar</Text>
							</div>
							<div className="cart-container">
										{cartList && cartList.map((item) => (
											<CartItem
													key={item.id}
													id={item.id}
													nombre={item.nombre}
													descripcion={item.descripcion}
													precio={item.precio}
											/>
										))}
							</div>
					 </div>
         )}
		</div>
	)
}
