import { Button, FormControl,InputGroup, FormLabel, Form, Row, Container, Col } from 'react-bootstrap';
import './Coordenadas.css';
import imgDelete from '../../../assets/img/X.png';
import { useState } from 'react';

export function Coordenadas(){
    const [myArray, updateMyArray] = useState<any[]>([]);


    function maisUmaLinha(){
        let ponto = myArray
        ponto.push(<div>oi</div>)
        updateMyArray(ponto);
        console.log(myArray);
    }

        return(
            
            <Form className="container-coordenadas container">
                <Container>
                <Row>
                    <Col>
                        <InputGroup className="input">
                            <FormLabel className="coordenadas-label">Eixo X</FormLabel>
                            <FormControl 
                                className="formInput"
                                type="number"/>
                        </InputGroup>
                    </Col>
                    <Col>
                        <InputGroup className="input">
                            <FormLabel className="coordenadas-label">Eixo Y</FormLabel>
                            <FormControl 
                                className="formInput"
                                type="number"/>
                        </InputGroup>
                    </Col>
                    <Col>
                        <InputGroup className="input">
                            <FormLabel className="coordenadas-label">Eixo Z</FormLabel>
                            <FormControl 
                                className="formInput"
                                type="number"/>
                        </InputGroup>
                    </Col>
                    <Col>
                        <InputGroup className="input">
                            <FormLabel className="coordenadas-label">Eixo R</FormLabel>
                            <FormControl 
                                className="formInput"
                                type="number"/>
                        </InputGroup>
                    </Col>
                    <Col>
                        <InputGroup className="input">
                            <FormLabel className="coordenadas-label">Tempo</FormLabel>
                            <FormControl 
                                className="formInput"
                                type="number"/>
                        </InputGroup>
                    </Col>
                    <Col className="ultima">
                        <FormLabel className="coordenadas-label">Zero Peça?</FormLabel>
                        <Form.Check
                            type="radio"
                            name="coordenada-zeropeca"
                            id="coordenada-zeropeca-1"
                            />
                            <img src={imgDelete} className="imgX"/>
                    </Col>
                </Row>
                { myArray.map((Teste:any) => Teste)
                }
                <Row>
                    <Button className="novo-ponto" variant="light" onClick={ () => maisUmaLinha()}>Novo ponto</Button>
                </Row>
                </Container>
            </Form>
    )
}
