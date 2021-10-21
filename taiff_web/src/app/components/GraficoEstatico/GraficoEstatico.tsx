import React from 'react';
import { Line } from 'react-chartjs-2';
import { Button } from 'react-bootstrap';
import { useState } from 'react';
import { coordenadas, datasets, dataType, janelas, temperaturas } from '../../interfaces/graficoEstatico';
import "./graficoEstatico.css";

export default function GraficoEstatico() {
    
    const urlTemperatuas = 'http://localhost:8080/temperatura/1';
    const urlJanelas = 'http://localhost:8080/temperatura/janelas/1'

    const [labels, setLabels] = useState<String[]>([]);
    const [termopar1, setTermopar1] = useState<coordenadas[]>([]);
    const [termopar2, setTermopar2] = useState<coordenadas[]>([]);
    const [termopar3, setTermopar3] = useState<coordenadas[]>([]);
    const [termoparAmb, setTermoparAmb] = useState<coordenadas[]>([]);
    const [janelas, setJanelas] = useState<Array<coordenadas[]>>([]);

    const urlCSV = 'http://localhost:8080/temperatura/temperaturasCSV/1';
    function gerarCSV(){
        //fetch(urlCSV)  
        window.open(urlCSV);
    }


    const data: dataType = {
        labels: labels,
        datasets: [
            {
                label: 'Termopar 1',
                data: termopar1,
                fill: false,
                backgroundColor: '#ff6384',
                borderColor: '#ff6384',
            },
            {
                label: 'Termopar 2',
                data: termopar2,
                fill: false,
                backgroundColor: '#005e91',
                borderColor: '#005e91',
            },
            {
                label: 'Termopar 3',
                data: termopar3,
                fill: false,
                backgroundColor: '#13cf16',
                borderColor: '#13cf16',
            },
            {
                label: 'Termopar Ambiente',
                data: termoparAmb,
                fill: false,
                backgroundColor: '#E8CC20',
                borderColor: '#E8CC20',
            },
            // {
            //     label: "Janela 1",
            //     data: janelas,
            //     hidden: true,
            //     fill: false,
            //     backgroundColor: '#e80000',
            //     borderColor: '#e80000',
            // },
        ],
    };

    const options = {
        animation: false,
        animations: {
            colors: false,
            x: false,
        },
        transitions: {
            active: {
                animation: {
                    duration: 0
                }
            }
        },
        parsing: {
            xAxisKey: 'x',
            yAxisKey: 'y'
        },
    };

    function gerarGrafico() {
        fetch(`${urlTemperatuas}`)
            .then(res => res.json())
            .then(
                (result: temperaturas[]) => {
                    setLabels(
                        result.map(
                            (objeto: temperaturas) => objeto.linha.toString()
                        )
                    );

                    for (let i = 1; i <= data.datasets.length; i++) {
                        switch (i) {
                            case 1:
                                setTermopar1(result.map((temperaturas: temperaturas) => {
                                    return {
                                        x: temperaturas.linha.toString(),
                                        y: temperaturas.termopar_1
                                    }
                                }));
                                break;
                            case 2:
                                setTermopar2(result.map((temperaturas: temperaturas) => {
                                    return {
                                        x: temperaturas.linha.toString(),
                                        y: temperaturas.termopar_2
                                    }
                                }));
                                break;
                            case 3:
                                setTermopar3(result.map((temperaturas: temperaturas) => {
                                    return {
                                        x: temperaturas.linha.toString(),
                                        y: temperaturas.termopar_3
                                    }
                                }));
                                break;
                            case 4:
                                setTermoparAmb(result.map((temperaturas: temperaturas) => {
                                    return {
                                        x: temperaturas.linha.toString(),
                                        y: temperaturas.termopar_amb
                                    }
                                }));
                                break;
                        }
                    }
                })

        // fetch(`${urlJanelas}`)
        //     .then(res => res.json())
        //     .then((janelas: Array<janelas>) => {


        //         setJanelas(janelas.map((janela: janelas) => {
        //             let janelasLista: Array<coordenadas> = [];

        //             for (let i = 1; i <= 4; i++) {

        //                 switch (i) {
        //                     case 1:
        //                         janelasLista.push({
        //                             x: janela.valorInicial.toString(),
        //                             y: 20
        //                         })
        //                         break;
        //                     case 2:
        //                         janelasLista.push({
        //                             x: janela.valorInicial.toString(),
        //                             y: 120
        //                         })
        //                         break;
        //                     case 3:
        //                         janelasLista.push({
        //                             x: janela.valorFinal.toString(),
        //                             y: 20
        //                         })
        //                         break;
        //                     case 4:
        //                         janelasLista.push({
        //                             x: janela.valorFinal.toString(),
        //                             y: 120
        //                         })
        //                         break;
        //                 }

        //             }
        //             return janelasLista;
        //         }
        //         ))
        //     })        

    }

    return (

        <section id="section-grafico">
            <Line className="grafico" data={data} options={options} />
            <div className='button-container'>
                <Button onClick={() => gerarGrafico()}>Gerar gráfico</Button>
                <Button onClick={() => gerarCSV()}>Gerar CSV</Button> 
                <Button href='/tabela'>Ir para folha de rosto</Button>   
            </div>
        </section>

    );

}