import { VisorTemperatura } from '../../components/visorTemperatura/VisorTemperatura'
import { Coordenadas } from '../../components/Coordenadas/Coordenadas'
import { Container } from "react-bootstrap";
import { ButtonHome } from "../../components/ButtonHome/ButtonHome";
import logo from "../../../assets/img/logo.png";
import './Home.css';

export function Home(props:any) {

    return( 
        <>
            <div className="logoTaiff">
                <img src={logo}/> 
            </div>
            <Container fluid>  
                <Coordenadas/>
                <ButtonHome/>
            </Container>
        </>
    )

}