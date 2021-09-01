import React from "react";
import { Container, Button } from "react-bootstrap";
import { ButtonTaiff } from "../ButtonTaiff/ButtonTaiff";
import { ButtonExcluir } from '../ButtonExcluir/ButtonExcluir';
import './SubHeader.css';


export function SubHeader(props:any){
    return(
        <Container className="subheader">
            <ButtonTaiff funcao='put' value='2' data={props} > Put </ButtonTaiff>
            <ButtonTaiff funcao='novo ponto'> novo ponto </ButtonTaiff>
            <ButtonTaiff funcao='excluir ponto'> excluir ponto </ButtonTaiff>
            <ButtonTaiff funcao='zeroPeca'> zeroPeca </ButtonTaiff>
            <ButtonExcluir value={1}/>
        </Container>
    );
}