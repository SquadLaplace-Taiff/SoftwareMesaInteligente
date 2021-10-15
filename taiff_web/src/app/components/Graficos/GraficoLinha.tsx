import { Line, Chart } from 'react-chartjs-2';
import { Button } from 'react-bootstrap';
import 'chartjs-adapter-luxon';
import StreamingPlugin from 'chartjs-plugin-streaming';
import { Component, useState } from 'react';

Chart.register(StreamingPlugin);

export class GraficoLinha extends Component {
    render() {
        return (
            <Line
                data={{
                    datasets: [{
                        label: 'Dataset 1',
                        backgroundColor: 'rgba(255, 99, 132, 0.5)',
                        borderColor: 'rgb(255, 99, 132)',
                        borderDash: [8, 4],
                        fill: true,
                        data: []
                    }, {
                        label: 'Dataset 2',
                        backgroundColor: 'rgba(54, 162, 235, 0.5)',
                        borderColor: 'rgb(54, 162, 235)',
                        cubicInterpolationMode: 'monotone',
                        fill: true,
                        data: []
                    }]
                }}
                options={{
                    scales: {
                        x: {
                            type: 'realtime',
                            realtime: {
                                delay: 2000,
                                onRefresh: (chart:any) => {
                                    //console.log("cheguei")
                                    if (chart.data.datasets[0].data.length > 0) {
                                        console.log("cheguei1")
                                        fetch(`http://localhost:8080/temperatura/${chart.data.datasets[0].data.pop().x.replace('T' , ' ')}/1`)
                                            .then(res => res.json())
                                            .then(resultado => {

                                                resultado.forEach((leitura:any) => {
                                                    chart.data.datasets.forEach((dataset:any, index:number) => {
                                                        dataset.data.push({
                                                            x: leitura.dt_leitura,
                                                            y: leitura.termopar_1
                                                        });
                                                    });
                                                });
                                            })
                                    } else {
                                        console.log(chart.data.datasets[0].data.length)
                                        fetch("http://localhost:8080/temperatura/2021-09-13%2000:00:00.090000/1")
                                        .then(res => res.json())
                                            .then(resultado => {

                                                resultado.forEach((leitura:any) => {
                                            
                                                    chart.data.datasets.forEach((dataset:any, index:number) => {
                                                        
                                                        dataset.data.push({
                                                            
                                                            x: leitura.dt_leitura,
                                                            y: leitura.termopar_1
                                                        });
                                        
                                                    });
                                                });
                                            })
                                    }
                                }
                            }
                        }
                    }
                }}
            />
        );
    }
}
