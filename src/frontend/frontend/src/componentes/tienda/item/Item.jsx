import React from "react"
import { useDispatch } from "react-redux"
import { Box, Image, Text, IconButton} from "@chakra-ui/react"
import { AddIcon } from "@chakra-ui/icons"
import { addAction } from "../../../reducers/CartSlice"
import "./item.css"

export let Item = ({ id, name, price }) => {
   const dispatch = useDispatch()

   let url = require("../../../assets/placeholder.png").default

   return (	
		<Box className="card-item" maxW="sm" borderWidth="1px" borderRadius="lg" overflow="hidden" onClick={() => dispatch(addAction({ id, name, price }))}>
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
						{name}
						<Box>
						$ {price}
						</Box>
					</Box>
			</Box>
		</Box>
   )
}

