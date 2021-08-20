import React, { useState, useEffect, useCallback } from 'react'
import {
    Avatar,
    Text,
    Button,
    Heading,
    Tabs,
    TabList,
    Tab,
    TabPanel,
    TabPanels,
    Table,
    Thead,
    Tbody,
    Td,
    Th,
    Tr,
    IconButton
} from '@chakra-ui/react'
import { Link } from 'react-router-dom'
import keycloak_config from '../../keycloak'
import Cookies from 'js-cookie'
import axios from 'axios'
import { path } from '../../pathConfig'
import { ArrowBackIcon, ArrowForwardIcon } from '@chakra-ui/icons'
import './Home.css'

export const Home = () => {
    const [username, setUsername] = useState('')
    const [pedidos, setPedidos] = useState([])
    const [pagos, setPagos] = useState([])
    const [auth, setAuth] = useState({
        keycloak: null,
        authenticated: false,
    })
    const keycloak = keycloak_config
    const token = Cookies.get('token')

    let loadPedidos = useCallback(async () => {
        let responsePedidos = await axios
            .get(`${path.PEDIDO}/pedido`, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                withCredentials: true,
            })
            .catch((err) => console.log(err.response))
        responsePedidos && setPedidos(responsePedidos.data?.content)
        // eslint-disable-next-line
    }, [])

    let loadPagos = useCallback(async () => {
        let responsePagos = await axios
            .get(`${path.CUENTA}/pago`, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                withCredentials: true,
            })
            .catch((err) => console.log(err.response))
        responsePagos && setPagos(responsePagos.data)
        // eslint-disable-next-line
    }, [])

    useEffect(() => {
        let getSessionData = async () => {
            let { username } = await keycloak.loadUserProfile()
            let token = await keycloak.token
            Cookies.set('token', token)
            setUsername(username)
            await loadPedidos()
            await loadPagos()
        }
        keycloak.init({ onLoad: 'login-required' }).then((authenticated) => {
            setAuth({ keycloak, authenticated })
            getSessionData()
        })
        // eslint-disable-next-line
    }, [keycloak, loadPagos, loadPedidos])

    return auth.keycloak && auth.authenticated ? (
        <>
            <div className="home-container">
                <div className="home-details">
                    <div className="">
                        <Heading size="lg">Panel principal</Heading>
                    </div>
                    <div className="home-user-details">
                        <div className="home-user-container">
                            <Text id="home-user">{username}</Text>
                            <Text id="home-role">ROL: {'USUARIO'}</Text>
                        </div>
                        <Avatar
                            bg="red.500"
                            size="md"
                            name={username}
                            color="white"
                        />
                    </div>
                </div>
                <div className="home-panel">
                    <Heading size="md">Registrar</Heading>
                    <div className="home-registro">
                        <Link to="/alta/cliente">
                            <Button size="lg" colorScheme="yellow">
                                Cliente
                            </Button>
                        </Link>
                        <Link to="/alta/producto">
                            <Button size="lg" colorScheme="yellow">
                                Producto
                            </Button>
                        </Link>
                        <Link to="/alta/pago">
                            <Button size="lg" colorScheme="yellow">
                                Pago
                            </Button>
                        </Link>
                        <Link to="/alta/pedido">
                            <Button size="lg" colorScheme="yellow">
                                Pedido
                            </Button>
                        </Link>
                        <Link to="/alta/obra">
                            <Button size="lg" colorScheme="yellow">
                                Obra
                            </Button>
                        </Link>
                    </div>
                </div>
                <Tabs
                    className="home-tabs"
                    variant="soft-rounded"
                    colorScheme="yellow"
                >
                    <TabList className="home-tab-header">
                        <Tab onClick={loadPedidos}>Pedidos</Tab>
                        <Tab onClick={loadPagos}>Pagos</Tab>
                    </TabList>
                    <TabPanels className="home-tab-panel">
                        <TabPanel>
                            <Table>
                                <Thead>
                                    <Tr>
                                        <Th>Id</Th>
                                        <Th>Estado</Th>
                                        <Th>Fecha</Th>
                                        <Th>Id Obra</Th>
                                        <Th>Descripcion obra</Th>
                                        <Th>Id Cliente</Th>
                                        <Th>Costo total</Th>
                                    </Tr>
                                </Thead>
                                <Tbody>
                                    {pedidos &&
                                        pedidos.map((e, i) => (
                                            <Tr key={i} id={`pedido-elem-${i}`}>
                                                <Td>{e.id}</Td>
                                                <Td>{e.estadoPedido}</Td>
                                                <Td>{e.fechaPedido}</Td>
                                                <Td>{e.obra.id}</Td>
                                                <Td>{e.obra.descripcion}</Td>
                                                <Td>{e.obra.clienteId}</Td>
                                                <Td>$ {e.costoTotal}</Td>
                                            </Tr>
                                        ))}
                                </Tbody>
                            </Table>
                        </TabPanel>
                        <TabPanel className="home-tab-panel-2">
                            <Table>
                                <Thead>
                                    <Tr>
                                        <Th>Id</Th>
                                        <Th>Id Pedido</Th>
                                        <Th>Fecha del pago</Th>
                                        <Th>Medio pago</Th>
                                        <Th>Observacion</Th>
                                    </Tr>
                                </Thead>
                                <Tbody>
                                    {pagos &&
                                        pagos.map((e, i) => (
                                            <Tr key={i} id={`pago-elem-${i}`}>
                                                <Td>{e.id}</Td>
                                                <Td>{e.pedidoId}</Td>
                                                <Td>{e.fechaPago}</Td>
                                                <Td>{e.medioPago.tipo}</Td>
                                                <Td>
                                                    {e.medioPago.observacion}
                                                </Td>
                                            </Tr>
                                        ))}
                                </Tbody>
                            </Table>
                        </TabPanel>
                    </TabPanels>
                </Tabs>
            </div> 
        </>
    ) : null
}
