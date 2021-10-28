import React from "react";
import { Container} from "react-bootstrap";
import { GraficoDinamico } from "../../components/GraficoDinamico/GraficoDinamico";

import './Graficos.css';


export class Grafico extends React.Component<{data: any}, any> {

    constructor(props: any) {
        super(props);
    }
    
    render() {
        return (
            <section className="section-graficos">

                <h1 className="title h1">Temperaturas</h1>

                <Container className="grafico-container">
                    <GraficoDinamico/>
                </Container>


            </section>

        );
    }

}