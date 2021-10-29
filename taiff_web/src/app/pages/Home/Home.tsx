import { Container } from "react-bootstrap";
import { ButtonHome } from "../../components/ButtonHome/ButtonHome";
import './Home.css';
import Lista from '../../components/Coordenadas/Lista';
import { Header } from "../../components/Header/Header";

export function Home(props:any) {

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