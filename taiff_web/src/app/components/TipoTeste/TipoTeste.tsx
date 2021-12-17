import { useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import './TipoTeste.css';

export function TipoTeste(props: any) {

    function changeTipe(target: any) {
        localStorage.setItem('tipoTeste', target.value);
        if (props.trocarTeste()){
            props.trocarTeste(target.value);
        }
    }

    return (
        <Container>
            <Row>
                <Col className="input-row">
                    <label htmlFor="aquecimento">Aquecimento</label>
                    <input 
                        id="aquecimento" 
                        onChange={(e: any) => changeTipe(e.target)} 
                        type="radio" 
                        name="tipoTeste" 
                        value="Aquecimento" 
                    />
                </Col>
                <Col className="input-row">
                    <label htmlFor="oscilacao">Oscilação</label>
                    <input 
                        id="oscilacao" 
                        onChange={(e: any) => changeTipe(e.target)} 
                        type="radio" name="tipoTeste" 
                        value="Oscilacao" 
                    />
                </Col>
                <Col className="input-row">
                    <label htmlFor="resfriamento">Resfriamento</label>
                    <input 
                        id="resfriamento" 
                        onChange={(e: any) => changeTipe(e.target)} 
                        type="radio" 
                        name="tipoTeste" 
                        value="Resfriamento" 
                    />
                </Col>
            </Row>
        </Container>
        
    );
}