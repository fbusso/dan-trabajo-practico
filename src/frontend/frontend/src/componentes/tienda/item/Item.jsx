import React from "react"
import { useDispatch } from "react-redux"
import { Box, Image, Text, Badge } from "@chakra-ui/react"
import { addAction } from "../../../reducers/CartSlice"
import "./item.css"

export let Item = ({ id, nombre, descripcion, precio }) => {
   const dispatch = useDispatch()

   let url = require("../../../assets/placeholder.png").default

   return (	
		<Box className="card-item" maxW="sm" borderWidth="1px" borderRadius="lg" overflow="hidden" onClick={() => dispatch(addAction({ id, nombre, descripcion, precio }))}>
			<div className="image-container">
				<Image className="item-image" src={url} alt={'producto'} objectFit="fill"/>
				<div className="image-overlay"/>
			</div>

			<Box p="6">
					<Box
						fontWeight="semibold"
						as="h4"
						lineHeight="tight"
						isTruncated
						textAlign="center"
						className="item-footer">
						<Text className="item-name">
							{nombre}
						</Text>

					</Box>

					<Box>
						<Text fontSize="small" className="item-description">{descripcion}</Text>
					</Box>

					<Badge className="item-price" colorScheme="blue">
						$ {precio}
						</Badge>
			</Box>
		</Box>
   )
}

