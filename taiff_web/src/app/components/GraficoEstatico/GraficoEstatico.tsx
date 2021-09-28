import React from 'react';
import { Line } from 'react-chartjs-2';
import { Button } from 'react-bootstrap';
import { useState } from 'react';


export default function GraficoEstatico(){

    const url = 'http://localhost:8080/temperatura/1';

    const [labels, setLabels] = useState<any[]>([]);
    const [datasetsData, setDatasetsData] = useState<any[]>([]);

    type dataType  = {
        labels:any[],
        datasets:any[]
    }

    const data:dataType = {
        labels: labels,
        datasets: [
            {
                label: 'Termopar 1',
                data: datasetsData,
                fill: false,
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgba(255, 99, 132, 0.2)',
            },
        ],
    };

    
    console.log(data)

    const options = {
        scales: {
            yAxes: [
                {
                    ticks: {
                        beginAtZero: true,
                    },
                },
            ],
        },
    };

    function gerarGrafico(){
        fetch(`${url}`)
            .then(res => res.json())
            .then(
                (result:any[]) => {
                    setLabels(
                        result.map(
                            (objeto:any) => objeto.linha.toString()));

                    setDatasetsData(
                            result.map(
                                temperatura1 => temperatura1.termopar_1
                                ))
            })
    }

        return(

            <div>

                <Line data={data} options={options} />

                <Button 
                onClick={() => gerarGrafico()}>Gerar gráfico</Button>
            </div>

        );

} 