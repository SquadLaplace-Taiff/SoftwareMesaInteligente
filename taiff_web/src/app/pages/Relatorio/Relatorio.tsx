import { Container } from "react-bootstrap";
import GraficoEstatico from "../../components/GraficoEstatico/GraficoEstatico";
import { ModalCsv } from "./Modals/ModalCsv/ModalCsv";
import { ModalFolhaDeRosto } from "./Modals/ModalFolhaDeRosto/ModalFolhaDeRosto";
import { Tabela } from "./Tabela/Tabela";

export function Relatorio() {

    return (
        <section>
            <Container className="grafico-container">
                <GraficoEstatico/>
            </Container>

            <Container className="tabela-container">
                <Tabela />
            </Container>


            <ModalCsv id={1} />
            <ModalFolhaDeRosto />
        </section>
    )
}