import { useState } from 'react';
import { Button, FormControl, FormGroup, InputGroup, FormLabel } from 'react-bootstrap';
import './Posicao.css';

export function Posicao(){
    const [value, setValue] = useState()
    
    function onInput(values: any) {
        setValue(values);
    }

    return(
        <div className="contenForm">
            <FormGroup className="form">
                <InputGroup className="input">
                    <FormLabel className="eixo">Eixo Y</FormLabel>
                    <FormControl 
                        onChange={e => onInput(e.target.value)}
                        value={value}
                        className="formInput"  
                        type="number"/>                    
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