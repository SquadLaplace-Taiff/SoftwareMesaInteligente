import { ButtonHTMLAttributes } from "react";
import { Button } from 'react-bootstrap';
import { APIService } from "../../services/API.service";

import './ButtonExcluir.css';

type ButtonAttributes = ButtonHTMLAttributes<HTMLButtonElement>;

const apiService = new APIService();

export function ButtonExcluir(props: ButtonAttributes) {
    return (
        <Button className="btn-excluir" variant="danger" {...props} onClick={() => {apiService.deletarTeste(props.value)}}> Excluir </Button>
    );
}