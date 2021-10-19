import { Component } from "react";
import { VisorTemperatura } from '../../components/visorTemperatura/VisorTemperatura'
import { Coordenadas } from '../../components/Coordenadas/Coordenadas'
import { Container } from "react-bootstrap";

export class Home extends Component {

    render(){

        return( 
            
            <Container fluid>
                <VisorTemperatura/>
                <Coordenadas/>
            </Container>
            
        );

    }

}