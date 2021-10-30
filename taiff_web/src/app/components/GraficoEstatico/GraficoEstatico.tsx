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
    const [validaGrafico, setValidaGrafico] = useState<boolean>(false);

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
    function chamarGrafico(){
        if(!validaGrafico){
            gerarGrafico();
            setValidaGrafico(true); 
        }
    }

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
                }).catch(error => console.log("Failed to fetch" + error) );

    }
    chamarGrafico();

    return (

        <section id="section-grafico">
            <Line className="grafico" data={data} options={options} />
        </section>

    );

}