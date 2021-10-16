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
                                duration: 20000,  // data in the past 20000 ms will be displayed
                                refresh: 5000,    // onRefresh callback will be called every 1000 ms
                                delay: 5000,      // delay of 1000 ms, so upcoming values are known before plotting a line
                                pause: false,     // chart is not paused
                                ttl: undefined,   // data will be automatically deleted as it disappears off the chart
                                frameRate: 1,    // data points are drawn 30 times every second
                                onRefresh: (chart: any) => {
                                    let dtLeitura;
                                    if (chart.data.datasets[0].data.length > 0) {
                                        console.log("cheguei1")
                                        fetch(`http://localhost:8080/temperatura/2021-10-16T00:30:45.959/4`)
                                            .then(res => res.json())
                                            .then(resultado => {
                                                dtLeitura = resultado[0].dt_leitura
                                                console.log(chart.data.datasets[0].data)
                                                //resultado.forEach((leitura: any) => {
                                                    chart.data.datasets[0].data.push({
                                                        x: Date.now(),
                                                        y: resultado[0].termopar_1
                                                    });
                                                    
                                                //});
                                            })
                                    } else {
                                        console.log(chart.data.datasets[0].data.length)
                                        fetch("http://localhost:8080/temperatura/2021-09-13T00:00:00.090000/4")
                                            .then(res => res.json())
                                            .then(resultado => {

                                                resultado.forEach((leitura: any) => {
                                                    dtLeitura = resultado[0].dt_leitura     
                                                    chart.data.datasets.forEach((dataset: any, index: number) => {

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
