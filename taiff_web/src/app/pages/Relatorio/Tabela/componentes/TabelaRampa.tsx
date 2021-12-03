import { useState } from "react";
import { Table } from "react-bootstrap";
import { Rampas, Rampa } from "../../../../interfaces/rampas"

const rampaInit = {
    duracao: 0,
    inicio: '',
    fim: '',
    rampaSubindo: false,
    termopar: '',
    tempAmbiente: 0,
    tempMaxima: 0,
    tempMinima: 0,
}

const tabelaInit ={
    rampaDescendo: rampaInit,
    rampaSubindo: rampaInit
}

export function TabelaRampa(props: any) {

    const [dadosTabela, setDadosTabela] = useState<Array<Rampas>>([tabelaInit]);
    const [pegarDados, setPegarDados] = useState<Boolean>(false);

    const url = 'http://localhost:8081/temperatura/rampaSubida';

    function fetchAPI() {
        fetch(url)
            .then(res => res.json())
            .then(data => {
                setDadosTabela(data)
            })
            .catch(
                (error: Error) => console.log('error: ' + error.message)
            )
    }

    function pegarDadosTempo(time: string) {
        let hora;

        hora = time.split('T')[1];
        return hora;
    }

    function gerarTabela() {
        if (props.tipoTeste === 'Aquecimento' || props.tipoTeste === 'Resfriamento') {
            return (
                <tbody>
                    <tr>
                        <td>Inicio</td>
                        {
                            dadosTabela.map((dados: Rampas) => {
                                return <td>{pegarDadosTempo(dados[(props.tipoTeste === 'Aquecimento') ? "rampaSubindo" : "rampaDescendo"]?.inicio!)}</td>
                            })
                        }
                    </tr>
                    <tr>
                        <td>Fim</td>
                        {
                            dadosTabela.map((dados: Rampas) => {
                                return <td>{pegarDadosTempo(dados[(props.tipoTeste === 'Aquecimento') ? "rampaSubindo" : "rampaDescendo"]?.fim!)}</td>
                            })
                        }
                    </tr>
                    <tr>
                        <td>Duração</td>
                        {
                            dadosTabela.map((dados: Rampas) => {
                                return <td>{dados[(props.tipoTeste === 'Aquecimento') ? "rampaSubindo" : "rampaDescendo"]?.duracao! / 1000 } segundos</td>
                            })
                        }
                    </tr>
                    <tr>
                        <td>Mínimo</td>
                        {
                            dadosTabela.map((dados: Rampas) => {
                                return <td>{dados[(props.tipoTeste === 'Aquecimento') ? "rampaSubindo" : "rampaDescendo"]?.tempMinima.toFixed(2)} ºC</td>
                            })
                        }
                    </tr>
                    <tr>
                        <td>Máximo</td>
                        {
                            dadosTabela.map((dados: Rampas) => {
                                return <td>{dados[(props.tipoTeste === 'Aquecimento') ? "rampaSubindo" : "rampaDescendo"]?.tempMaxima.toFixed(2)} ºC</td>
                            })
                        }
                    </tr>
                    <tr>
                        <td>Δ Temperatura</td>
                        {
                            dadosTabela.map((dados: Rampas) => {
                                return <td>{(dados[(props.tipoTeste === 'Aquecimento') ? "rampaSubindo" : "rampaDescendo"]?.tempMaxima! - dados[(props.tipoTeste === 'Aquecimento') ? "rampaSubindo" : "rampaDescendo"]?.tempMinima!).toFixed(2)} ºC</td>
                            })
                        }
                    </tr>

                </tbody>
            )
        }
    }

    if (!pegarDados) {
        fetchAPI();
        setPegarDados(true)
    }

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th>Média Ambiente: {(dadosTabela[0][(props.tipoTeste === 'Aquecimento') ? "rampaSubindo" : "rampaDescendo"]?.tempAmbiente)?.toFixed(2)} ºC</th>
                    <th>Termopar 1</th>
                    <th>Termopar 2</th>
                    <th>Termopar 3</th>
                </tr>
            </thead>

            {
                gerarTabela() 
            }

        </Table>
    )
}