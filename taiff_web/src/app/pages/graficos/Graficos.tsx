import React from "react";
import { Button, Container, Modal } from "react-bootstrap";
import { GraficoDinamico } from "../../components/GraficoDinamico/GraficoDinamico";
import GraficoEstatico from "../../components/GraficoEstatico/GraficoEstatico";
import { ModalCsv } from "./Modals/ModalCsv/ModalCsv";
import { ModalFolhaDeRosto } from "./Modals/ModalFolhaDeRosto/ModalFolhaDeRosto";
import { Tabela } from "./Tabela/Tabela";

import './Graficos.css';


export class Graficos extends React.Component<{data: any}, any> {

    constructor(props: any) {
        super(props);
        this.state = {
            graficoDinamico: true
        };
    }

    componentWillUnmount() {
        this.GraficoDinamico();
    }

    GraficoEstatico() {
        return (
            <GraficoEstatico />
        );
    }

    GraficoDinamico() {
        return (
            <GraficoDinamico/>
            // <h1>Imagine um grafico aqui</h1>
        );
    }

    TrocarGrafico() {
        (this.state.graficoDinamico) ? this.setState({ graficoDinamico: false }) : this.setState({ graficoDinamico: true });
    }
    
    render() {
        return (
            <section className="section-graficos">

                <h1 className="title h1">Temperaturas</h1>

                <Container className="grafico-container">
                    {
                        (this.state.graficoDinamico) ? this.GraficoDinamico() : this.GraficoEstatico()
                    }
                </Container>

                <Container className="tabela-container">
                    <Tabela />
                </Container>

                <Button onClick={() => this.TrocarGrafico()}>Trocar gráfico</Button>
                <ModalCsv id={1}/>
                <ModalFolhaDeRosto/>

            </section>

        );
    }

}