import React from "react";
import { Button, Table } from "react-bootstrap";
import { tabela } from '../../../interfaces/tabelaInterface';

export class Tabela extends React.Component<{}, any> {
    constructor(props: any) {
        super(props);
        this.state = {
            resposta: []
        };
    }
 
    private url = 'http://localhost:8080/temperatura/folhaDeRosto';

    pegarDados(id: number) { 
        fetch(`${this.url}`)
            .then(res => res.json())
            .then(resposta => {
                this.setState({resposta: resposta})
            })
            .catch(
                (error: Error) => console.log('error: ' + error.message)
            )

        console.log(this.state.resposta);
    }

    render() {
        return (
            <section>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>Janela</th>
                            <th>Ambiente</th>
                            <th>Termopar 1</th>
                            <th>Termopar 2</th>
                            <th>Termopar 3</th>
                            <th>Temperatura Média</th>
                            <th>Temperatura Corrigida</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.resposta.map( (janela: tabela, index: number) => { 
                                return(
                                    <tr key={index}>
                                        <td>{ index + 1 }</td>
                                        <td>{ janela.termopar_amb.toFixed(2).replace('.', ',') }</td>
                                        <td>{ janela.termopar_1.toFixed(2).replace('.', ',') }</td>
                                        <td>{ janela.termopar_2.toFixed(2).replace('.', ',') }</td>
                                        <td>{ janela.termopar_3.toFixed(2).replace('.', ',') }</td>
                                        <td>{ janela.media.toFixed(2).replace('.', ',') }</td>
                                        <td>{ janela.temperaturaCorrigida.toFixed(2).replace('.', ',') }</td>
                                    </tr>
                                );
                            })
                        }
                    </tbody>
                </Table>

                <Button onClick={() => this.pegarDados(1)}>Dados</Button>
            </section>
        );
    }
}
