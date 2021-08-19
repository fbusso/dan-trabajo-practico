import React from 'react'
import { StoreItem } from './item/store-item/StoreItem'
import { Total } from './total/Total'
import "./tienda.css"
import { Heading } from '@chakra-ui/react'
import { Cart } from './cart/Cart'

export let Tienda = () => {
	return (
		<div className="tienda-container">
			<Heading>Cree su pedido</Heading>
			<div className="tienda-card-group">
				<StoreItem />
				<div className="cart-container">
						<Cart />
						<Total className="cart-total"/>
				</div>
			</div>
		</div>
	)
}
