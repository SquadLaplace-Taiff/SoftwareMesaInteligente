import React from "react";
import { Button, Table } from "react-bootstrap";
import { tabela } from '../../../interfaces/tabelaInterface';

export class Tabela extends React.Component<{}, any> {
    constructor(props: any) {
        super(props);
        this.state = {
            resposta: [],
            validarTabela: false
        };
    }

    private url = 'http://localhost:8081/temperatura/rampaSubida';

    chamarTabela() {
        if (!this.state.validarTabela) {
            this.pegarDados();
            this.setState({
                validarTabela: true
            })
        }

    }

    pegarDados() {
        fetch(`${this.url}`)
            .then(res => res.json())
            .then(resposta => {
                this.setState({ resposta: resposta })
            })
            .catch(
                (error: Error) => console.log('error: ' + error.message)
            )
    }


    render() {
        this.chamarTabela();
        return (
            <section>
                {
                    () => {
                        if (localStorage.getItem('tipoTeste') === 'Aquecimento' || localStorage.getItem('tipoTeste') === 'Resfriamento') {
                            return (
                                <Table striped bordered hover>
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Termopar 1</th>
                                            <th>Termopar 2</th>
                                            <th>Termopar 3</th>
                                            <th>Ambiente</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Inicio</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Fim</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Duração</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Mínimo</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Máximo</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Δ Temperatura</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>

                                    </tbody>
                                </Table>
                            );
                        }
                    }
                }

            </section>
        );
    }
}
