import { useState, Component } from 'react';
import { Button, FormControl, FormGroup, InputGroup, FormLabel, Form } from 'react-bootstrap';
import './Coordenadas.css';
import imgDelete from '../../../assets/img/X.png';

export function Coordenadas(){

        return(

            <Form className="container-coordenadas">
                <div className="row">
                    <div className="col-2">
                        <InputGroup className="input">
                            <FormLabel className="coordenadas-label">Eixo X</FormLabel>
                            <FormControl 
                                disabled = {true}
                                className="formInput"
                                type="number"/>
                        </InputGroup>
                    </div>
                    <div className="col-2">
                        <InputGroup className="input">
                            <FormLabel className="coordenadas-label">Eixo Y</FormLabel>
                            <FormControl 
                                disabled = {true}
                                className="formInput"
                                type="number"/>
                        </InputGroup>
                    </div>
                    <div className="col-2">
                        <InputGroup className="input">
                            <FormLabel className="coordenadas-label">Eixo Z</FormLabel>
                            <FormControl 
                                disabled = {true}
                                className="formInput"
                                type="number"/>
                        </InputGroup>
                    </div>
                    <div className="col-2">
                        <InputGroup className="input">
                            <FormLabel className="coordenadas-label">Eixo R</FormLabel>
                            <FormControl 
                                disabled = {true}
                                className="formInput"
                                type="number"/>
                        </InputGroup>
                    </div>
                    <div className="col-2">
                        <InputGroup className="input">
                            <FormLabel className="coordenadas-label">Tempo</FormLabel>
                            <FormControl 
                                disabled = {true}
                                className="formInput"
                                type="number"/>
                        </InputGroup>
                    </div>

                    <div className="col-2 ultima">
                        <FormLabel className="coordenadas-label">Zero Peça?</FormLabel>
                        <Form.Check
                            type="radio"
                            name="coordenada-zeropeca"
                            id="coordenada-zeropeca-1"
                            />

                        <img src={imgDelete}/>
                    </div>
                
                </div>
            </Form>
        )
}
