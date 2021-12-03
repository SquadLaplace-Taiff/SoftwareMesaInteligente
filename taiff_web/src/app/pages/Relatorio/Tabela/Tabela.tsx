import React from "react";
import { Button } from "react-bootstrap";
import { TipoTeste } from "../../../components/TipoTeste/TipoTeste";
import { tabela } from '../../../interfaces/tabelaInterface';
import { TabelaRampa } from './componentes/TabelaRampa'

export class Tabela extends React.Component<{tipoTeste?: string}, any> {
    constructor(props: any) {
        super(props);
        this.state = {
            resposta: [],
            validarTabela: false
        };
    }

    

    chamarTabela() {
        if (!this.state.validarTabela) {
            this.setState({
                validarTabela: true
            })
        }

    }


    retornarTabela(resposta:any) {
            return (
                <TabelaRampa tipoTeste={(this.props.tipoTeste) ? this.props.tipoTeste : localStorage.getItem('tipoTeste')} />
            );
    }

    render() {
        this.chamarTabela();
        return (
            <section>

                {
                    this.retornarTabela(this.state.resposta)
                }

            </section>
        );
    }
}
