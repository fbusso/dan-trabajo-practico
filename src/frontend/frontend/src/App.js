import './App.css'
import { AltaPedido } from './componentes/alta-pedido/AltaPedido'
import { AltaProducto } from './componentes/alta-producto/AltaProducto'
import { RegistroCliente } from './componentes/registro-cliente/RegistroCliente'
import { RegistroObra } from './componentes/registro-obra/RegistroObra'
import { RegistroPago } from './componentes/registro-pago/RegistroPago'
import { Transferencia } from './componentes/registro-pago/Transferencia'
import { Efectivo } from './componentes/registro-pago/Efectivo'
import { Cheque } from './componentes/registro-pago/Cheque'
import { Tienda } from './componentes/tienda/Tienda'
import { Home } from './componentes/home/Home'

import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'

function App() {
    return (
        <Router>
            <div className="App">
                <div className="main-box">
                    <Switch>
                        <Route path="/tienda">
                            <Tienda />
                        </Route>
                        <Route path="/alta/pedido">
                            <AltaPedido />
                        </Route>
                        <Route path="/alta/producto">
                            <AltaProducto />
                        </Route>
                        <Route path="/alta/cliente">
                            <RegistroCliente />
                        </Route>
                        <Route path="/alta/obra">
                            <RegistroObra />
                        </Route>
                        <Route path="/alta/pago/:idPedido/transferencia">
                            <Transferencia />
                        </Route>
                        <Route path="/alta/pago/:idPedido/efectivo">
                            <Efectivo />
                        </Route>
                        <Route path="/alta/pago/:idPedido/cheque">
                            <Cheque />
                        </Route>
                        <Route path="/alta/pago">
                            <RegistroPago />
                        </Route>
                        <Route path="/">
                            <Home />
                        </Route>
                    </Switch>
                </div>
            </div>
        </Router>
    )
}

export default App
