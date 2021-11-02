import { Container } from "react-bootstrap";
import { ButtonHome } from "../../components/ButtonHome/ButtonHome";
import './Home.css';
import Lista from '../../components/Coordenadas/Lista';
import { Header } from "../../components/Header/Header";
import { APIService } from "../../services/API.service";

export function Home(props:any) {

    const apiService = new APIService();

    function deletarTemperaturas() {
        apiService.deletarTemperaturaTemporarias();   
    }

    deletarTemperaturas();

    return( 
        <>
            <Header/>
            <Container fluid>  
                <Lista/>
                <ButtonHome/>
            </Container> 
        </>
    )

}