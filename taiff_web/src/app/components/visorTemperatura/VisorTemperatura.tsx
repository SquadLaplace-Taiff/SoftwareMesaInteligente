import { useState, Component } from 'react';
import { Button, FormControl, FormGroup, InputGroup, FormLabel } from 'react-bootstrap';
import './visorTemperatura.css';

export function VisorTemperatura(){

        return(

            <div className="contenForm">
                    <InputGroup className="input">
                        <FormLabel className="visor-label">Termopar 1</FormLabel>
                        <FormControl 
                            disabled = {true}
                            className="formInput"
                            type="number"/>
                    </InputGroup>
                    <InputGroup className="input">
                        <FormLabel className="visor-label">Termopar 2</FormLabel>
                        <FormControl
                            disabled = {true} 
                            className="formInput" 
                            type="number"/>
                    </InputGroup>
                    <InputGroup className="input">
                        <FormLabel className="visor-label">Termopar 3</FormLabel>
                        <FormControl 
                            disabled = {true}
                            className="formInput" 
                            type="number"/>
                    </InputGroup>
                    <InputGroup className="input">
                        <FormLabel className="visor-label">Termopar Ambiente</FormLabel>
                        <FormControl 
                            disabled = {true}
                            className="formInput" 
                            type="number"/>
                    </InputGroup>
            </div>
        )
}
