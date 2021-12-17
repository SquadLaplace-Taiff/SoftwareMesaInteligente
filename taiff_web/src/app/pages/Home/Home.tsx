import { Container } from "react-bootstrap";
import { ButtonHome } from "../../components/ButtonHome/ButtonHome";
import './Home.css';
import Lista from '../../components/Coordenadas/Lista';
import { Header } from "../../components/Header/Header";
import { APIService } from "../../services/API.service";
import { TipoTeste } from "../../components/TipoTeste/TipoTeste";
import { useState } from "react";

export function Home(props: any) {

    const [tipoTeste, setTipoTeste] = useState('');

    const apiService = new APIService();

    function deletarTemperaturas() {
        apiService.deletarTemperaturaTemporarias();
        localStorage.removeItem('tipoTeste');
    }

    function trocarTeste(teste?: string) {
        (teste) ? setTipoTeste(teste) : setTipoTeste(localStorage.getItem('tipoTeste')!);
    }

    deletarTemperaturas();

    return (
        <>
            <Header />
            <Container fluid>
                <Lista />
                <ButtonHome />
                <TipoTeste trocarTeste={() => trocarTeste()} />
            </Container>
        </>
    )

}