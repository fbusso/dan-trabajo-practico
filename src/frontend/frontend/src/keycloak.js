import Keycloak from 'keycloak-js'

const keycloak = Keycloak({
    realm: 'tpdan',
    url: 'http://localhost:8080/auth/',
    clientId: 'app-dan',
})

export default keycloak
