import React from 'react'
import {
	Input,
	Text,
	IconButton
} from "@chakra-ui/react"
import { DeleteIcon } from "@chakra-ui/icons"
import "./cart-item.css"
import { deleteAction, setQuantityAction } from '../../../../reducers/CartSlice'
import { useDispatch, useSelector } from 'react-redux'

export let CartItem = ({ id, name, price }) => {

	const dispatch = useDispatch()
	let item = useSelector((state) => state.find((elem) => elem.id === id))
	let quantity = item.quantity

	let setQuantityHandler = (e) => {
		let value = e.target.value || 0
		dispatch(setQuantityAction({ id, quantity: parseInt(value) }))
	}

	return(
		<div className="cart-item">
			<Text>{name}</Text>
			<Text>$ {price}</Text>
			<div className="cart-number-item">
				<Input onChange={e => setQuantityHandler(e)} value={quantity} type="number" />
			</div>
			<IconButton colorScheme="red" icon={<DeleteIcon/>} size="sm" onClick={() => dispatch(deleteAction(id))}/>
		</div>
	)
}

