import React, { useState, useEffect } from 'react'
import { StoreItem } from './item/store-item/StoreItem'
import { Total } from './total/Total'
import keycloak_config from '../../keycloak'
import "./tienda.css"
import { Heading,Button } from '@chakra-ui/react'
import { Cart } from './cart/Cart'
import { useHistory } from 'react-router-dom'

export let Tienda = () => {
	const history = useHistory()
	const [auth, setAuth] = useState({
			keycloak: null,
			authenticated: false,
	})

	let logout = () => {
		keycloak.logout();
	}

	const keycloak = keycloak_config
	useEffect(() => {
		keycloak.init({ onLoad: 'login-required' }).then((authenticated) => {
			setAuth({ keycloak, authenticated })
			if (!keycloak.hasRealmRole('ROLE_COMPRADOR')) {
				history.replace('/')
			}	
		})
	}, [keycloak])

	return auth.keycloak && auth.authenticated ? (
		<div className="tienda-container">
			<Heading>Cree su pedido</Heading>
			<div className="tienda-card-group">
				<StoreItem />
				<div className="cart-container">
						<Cart />
						<Total className="cart-total"/>
				</div>
			</div>
			<div style={{ display: 'flex', justifyContent: 'flex-end', width: '100%'}}>
				<Button onClick={logout}>Logout</Button>
			</div>
		</div>
	) : null
}
