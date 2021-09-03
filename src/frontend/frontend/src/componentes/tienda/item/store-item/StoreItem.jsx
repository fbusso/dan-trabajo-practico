import React, { useEffect, useState } from 'react'
import { path } from '../../../../pathConfig'
import Cookies from 'js-cookie'
import { Item } from '../Item'
import './store-item.css'
import axios from 'axios'

export let StoreItem = () => {
	const token = Cookies.get('token')
	const [ productos, setProductos ] = useState([])

	useEffect(() => {
		const getItems = async () => {
			const response = await axios
			.get(`${path.PRODUCTO}/material`, {
					headers: {
							'Content-Type': 'application/json',
							Authorization: `Bearer ${token}`,
					},
					withCredentials: true,
			})
			.catch((err) => {console.log(err.response?.data)})

			setProductos(response?.data.content)
		}
		getItems()
	}, [])

	return(
		<div className="item-list">
			{productos?.length && productos.map((item) => (
				<Item
					key={item.id}
					id={item.id}
					nombre={item.nombre}
					descripcion={item.descripcion}
					precio={item.precio}
				/>
			))}
		</div>
	)
}
