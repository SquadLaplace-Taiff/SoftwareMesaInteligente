import { Container, Col, Row } from "react-bootstrap";
import GraficoEstatico from "../../components/GraficoEstatico/GraficoEstatico";
import { ModalCsv } from "./Modals/ModalCsv/ModalCsv";
import { ModalFolhaDeRosto } from "./Modals/ModalFolhaDeRosto/ModalFolhaDeRosto";
import { ModalSalvarTeste } from "./Modals/ModalSalvarTeste/ModalSalvarTeste";
import { Tabela } from "./Tabela/Tabela";
import { Header } from "../../components/Header/Header";
import './Relatorio.css'
import { TipoTeste } from "../../components/TipoTeste/TipoTeste";
import { useState } from "react";

export function Relatorio() {

    const [tipoTeste, setTipoTeste] = useState('');

    function trocarTeste(teste?: string) {
        (teste) ? setTipoTeste(teste) : setTipoTeste(localStorage.getItem('tipoTeste')!);
    }

    return (
        <section>
            <Header />
            <Container className="grafico-container">
                <GraficoEstatico />
            </Container>

            <Container className="tabela-container">
                <Row className="tipoTeste">
                    <TipoTeste trocarTeste={() => trocarTeste()} />
                </Row>
                <Tabela tipoTeste={tipoTeste} />
            </Container>

            <Container className="container-relatorio">
                <Row>
                    <Col ><ModalCsv id={1} /></Col>
                    <Col ><ModalFolhaDeRosto /></Col>
                    {/* <Col><ModalSalvarTeste/></Col> */}
                </Row>
            </Container>

        </section>
    )
}