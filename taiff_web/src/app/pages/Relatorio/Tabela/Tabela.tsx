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

    private url = 'http://localhost:8081/temperatura/folhaDeRosto';

    chamarTabela(){
        if(!this.state.validarTabela){
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
                this.setState({resposta: resposta})
            })
            .catch(
                (error: Error) => console.log('error: ' + error.message)
            )
    }

    
    render() {
        this.chamarTabela();
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
            </section>
        );
    }
}
