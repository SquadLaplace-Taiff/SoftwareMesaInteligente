import { render } from '@testing-library/react';
import React from 'react';
import { Button, FormControl, FormGroup, InputGroup, FormLabel } from 'react-bootstrap';
import './Posicao.css';
import { APIService } from "../../services/API.service";

export function Posicao(){
    return(
        <div className="contenForm">
           <FormGroup className="form">
                <InputGroup className="input">
                    <FormLabel className="eixo">Eixo Y</FormLabel>
                    <FormControl className="formInput"  type="number"/>                    
                </InputGroup>
                <InputGroup className="input">
                    <FormLabel className="eixo">Eixo X</FormLabel>
                    <FormControl className="formInput" type="number"/>
                </InputGroup>
                <InputGroup className="input">
                    <FormLabel className="eixo">Eixo Z</FormLabel>   
                    <FormControl className="formInput" type="number"/>
                </InputGroup>
                <InputGroup className="input">
                    <FormLabel className="eixo">Eixo R</FormLabel>
                    <FormControl className="formInput" type="number"/>
                </InputGroup>
                <InputGroup className="input">
                    <FormLabel className="eixo">Eixo T</FormLabel>
                    <FormControl className="formInput" type="number"/>
                </InputGroup>
           </FormGroup>
        </div>
    );


}