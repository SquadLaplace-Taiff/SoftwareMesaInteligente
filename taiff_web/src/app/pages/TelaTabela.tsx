import { useState } from "react";
import { Button, Table } from "react-bootstrap";
import { tabela } from "../interfaces/tabelaInterface";
import "./telaTabela.css";

export default function TelaTabela() {
    const [dados, setDados] = useState<Array<tabela>>([]);
    const url = 'http://localhost:8080/temperatura/folhaDeRosto/1';
    const urlCSV = 'http://localhost:8080/temperatura/folhaDeRostoCSV/1';

    function gerarCSV() {
        window.open(urlCSV);
    }

    function gerarTabela() {
        fetch(url)
            .then(res => res.json())
            .then(resultado => {
                setDados(resultado);
                // dados = resultado
            })
    }

    return (
        <section id="section-tabela">
            <Table className="tabela" striped bordered hover variant="dark">
                <thead>
                    <tr>
                        <th>Janelas</th>
                        <th>Temp Ambiente</th>
                        <th>Termopar 1</th>
                        <th>Termopar 2</th>
                        <th>Termopar 3</th>
                        <th>Temp Média</th>
                        <th>Temp Corrigida</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        dados.map((teste, index) =>{ return ( 
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{teste.termopar_amb.toFixed(2).replace('.', ',')}</td>
                                <td>{teste.termopar_1.toFixed(2).replace('.', ',')}</td>
                                <td>{teste.termopar_2.toFixed(2).replace('.', ',')}</td>
                                <td>{teste.termopar_3.toFixed(2).replace('.', ',')}</td>
                                <td>{teste.media.toFixed(2).replace('.', ',')}</td>
                                <td>{teste.temperaturaCorrigida.toFixed(2).replace('.', ',')}</td>
                            </tr>
                        )})
                    }
                </tbody>
            </Table>
            <Button onClick={() => gerarCSV()}> Gerar CSV </Button>
            <Button onClick={() => gerarTabela()}> Gerar Tabela </Button>
        </section>
    );
}