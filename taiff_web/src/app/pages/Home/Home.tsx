import { Component } from "react";
import { VisorTemperatura } from '../../components/visorTemperatura/VisorTemperatura'
import { Coordenadas } from '../../components/Coordenadas/Coordenadas'
import { Container } from "react-bootstrap";
import { ButtonHome } from "../../components/ButtonHome/ButtonHome";
import logo from "../../../assets/img/logo.png";
import './Home.css';

export class Home extends Component {

    render(){

        return( 
            <>
            <div className="logoTaiff">
            <img src={logo}/> 
            </div>
            <Container fluid>  
                <VisorTemperatura/>
                <Coordenadas/>
                <ButtonHome/>
            </Container>
            </>
        );

    }

}