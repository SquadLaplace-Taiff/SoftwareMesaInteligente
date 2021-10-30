import { Container, Col, Row } from "react-bootstrap";
import GraficoEstatico from "../../components/GraficoEstatico/GraficoEstatico";
import { ModalCsv } from "./Modals/ModalCsv/ModalCsv";
import { ModalFolhaDeRosto } from "./Modals/ModalFolhaDeRosto/ModalFolhaDeRosto";
import { ModalSalvarTeste } from "./Modals/ModalSalvarTeste/ModalSalvarTeste";
import { Tabela } from "./Tabela/Tabela";
import { Header } from "../../components/Header/Header";
import './Relatorio.css'

export function Relatorio() {

    return (
        <section>
            <Header/>
            <Container className="grafico-container">
                <GraficoEstatico/>
            </Container>

            <Container className="tabela-container">
                <Tabela />
            </Container>

            <Container className="container-relatorio">
                    <Row>
                       <Col ><ModalCsv id={1} /></Col> 
                       <Col ><ModalFolhaDeRosto /></Col>
                       <Col><ModalSalvarTeste/></Col>
                    </Row>
            </Container>
            
        </section>
    )
}