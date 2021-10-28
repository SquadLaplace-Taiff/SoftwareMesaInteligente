import { Container } from "react-bootstrap";
import { ButtonHome } from "../../components/ButtonHome/ButtonHome";
import logo from "../../../assets/img/logo.png";
import './Home.css';
import Lista from '../../components/Coordenadas/Lista';

export function Home(props:any) {

    return( 
        <>
            <div className="logoTaiff">
                <img src={logo}/> 
            </div>
            <Container fluid>  
                <Lista/>
                <ButtonHome/>
            </Container>
        </>
    )

}