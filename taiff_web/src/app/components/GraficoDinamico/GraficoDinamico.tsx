import { Line, Chart } from 'react-chartjs-2';
import 'chartjs-adapter-luxon';
import StreamingPlugin from 'chartjs-plugin-streaming';

Chart.register(StreamingPlugin);

export function GraficoDinamico(props: any) {
    return (
        <Line
            data={{
                datasets: [{
                    cubicInterpolationMode: 'monotone',
                    label: 'Termopar 1',
                    backgroundColor: 'rgba(255, 99, 132, 0.5)',
                    borderColor: 'rgb(255, 99, 132)',
                    lineBorderWidth: 0.7,
                    fill: false,
                    data: []
                }, {
                    label: 'Termopar 2',
                    backgroundColor: 'rgba(54, 162, 235, 0.5)',
                    borderColor: 'rgb(54, 162, 235)',
                    lineBorderWidth: 0.7,
                    cubicInterpolationMode: 'monotone',
                    fill: false,
                    data: []
                },
                {
                    label: 'Termopar 3',
                    backgroundColor: 'rgba(17, 146, 0, 0.5)',
                    borderColor: 'rgb(17, 146, 0)',
                    lineBorderWidth: 0.7,
                    cubicInterpolationMode: 'monotone',
                    fill: false,
                    data: []
                },
                {
                    label: 'Termopar Ambiente',
                    backgroundColor: 'rgba(255, 166, 0, 0.5)',
                    borderColor: 'rgb(255, 166, 0)',
                    lineBorderWidth: 0.7,
                    cubicInterpolationMode: 'monotone',
                    fill: false,
                    data: []
                }]
            }}
            options={{
                scales: {
                    x: {
                        ticks: {
                            min: 0,
                            max: 200
                        },
                        type: 'realtime',
                        realtime: {
                            duration: 20000,  // data in the past 20000 ms will be displayed
                            refresh: 1000,    // onRefresh callback will be called every 1000 ms
                            delay: 1000,      // delay of 1000 ms, so upcoming values are known before plotting a line
                            pause: false,     // chart is not paused
                            ttl: undefined,   // data will be automatically deleted as it disappears off the chart
                            frameRate: 10,    // data points are drawn 30 times every second
                            onRefresh: (chart: any) => {
                                if (chart.data.datasets[0].data.length > 0) {
                                    // console.log("cheguei1")
                                    fetch(`http://localhost:8081/temperatura/${localStorage.getItem("dataLeitura")}`)
                                        .then(res => res.json())
                                        .then(resultado => {
                                            localStorage.setItem("dataLeitura", resultado.dt_leitura)
                                            // console.log(chart.data.datasets[0].data)
                                            chart.data.datasets.forEach((dataset: any, index: number) => {
                                                dataset.data.push({
                                                    x: Date.now(),
                                                    y: resultado[`termopar_${(index == 3) ? "amb" : index + 1}`]
                                                });

                                            });
                                        })
                                        .catch(error => window.location.href="/relatorio" )
                                        
                                } else {
                                    // console.log(chart.data.datasets[0].data.length)
                                    fetch("http://localhost:8081/temperatura/2021-09-13T00:00:00.090000")
                                        .then(res => res.json())
                                        .then(resultado => {
                                                localStorage.setItem("dataLeitura", resultado.dt_leitura)
                                                chart.data.datasets.forEach((dataset: any, index: number) => {

                                                    dataset.data.push({
                                                        x: Date.now(),
                                                        y: resultado[`termopar_${index + 1}`]
                                                    });

                                                });
                                                
                                            
                                        })
                                        .catch(error => window.location.href="/relatorio" )
                                }
                            }
                        }
                    }
                }
            }}
        />
    );
}
