import React from 'react'
import { Image, Box } from "@chakra-ui/react"
import "./main-image.css"

export const MainImage = () => {

	const imageSrc = require('../../assets/Construction-1.svg').default 

	return(
		<Box className="main-image-container">
			<Image src={imageSrc} alt="Construction company"/>
		</Box>
	)
} 