import { Container } from "react-bootstrap";
import { ButtonHome } from "../../components/ButtonHome/ButtonHome";
import './Home.css';
import Lista from '../../components/Coordenadas/Lista';
import { Header } from "../../components/Header/Header";
import { APIService } from "../../services/API.service";
import { TipoTeste } from "../../components/TipoTeste/TipoTeste";

export function Home(props:any) {

    const apiService = new APIService();

    function deletarTemperaturas() {
        apiService.deletarTemperaturaTemporarias();  
        localStorage.removeItem('tipoTeste'); 
    }

    deletarTemperaturas();

    return( 
        <>
            <Header/>
            <Container fluid>  
                <Lista/>
                <ButtonHome/>
                <TipoTeste/>
            </Container> 
        </>
    )

}