import { Line, Chart } from 'react-chartjs-2';
import { Button } from 'react-bootstrap';
import 'chartjs-adapter-luxon';
import StreamingPlugin from 'chartjs-plugin-streaming';
import { useState } from 'react';


export function GraficoLinha() {

    Chart.register(StreamingPlugin);

    const url = 'http://localhost:8080/temperatura/1';

    const dataAux1: any = []
    const dataAux2: any = []
    const dataAux3: any = []
    const dataAux4: any = []

    // const moment = require('moment');

    const [datasets, setDatasets] = useState<any>();

    fetch(`${url}`)
        .then(res => res.json())
        .then(
            (result) => {
                result.forEach((dados: any) => {
                    // console.log(dados);
                    dataAux1.push({
                        x: dados.dt_leitura,
                        y: dados.termopar_1,
                    });
                    dataAux2.push({
                        x: dados.dt_leitura,
                        y: dados.termopar_2,
                    });
                    dataAux3.push({
                        x: dados.dt_leitura,
                        y: dados.termopar_3,
                    });
                    dataAux4.push({
                        x: dados.dt_leitura,
                        y: dados.termopar_amb,
                    });
                })
            })
        .catch(error => console.log('Authorization failed : ' + error.message))


    console.log(dataAux1);


    function gerarGrafico() { 
        setDatasets([
            {
                label: 'Temperatura 1',
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgb(54, 162, 235)',
                cubicInterpolationMode: 'monotone',
                fill: false,
                data: dataAux1
            },
            {
                label: 'Temperatura 2',
                backgroundColor: 'rgba(255, 8, 0, 0.5)',
                borderColor: 'rgb(255, 8, 0)',
                cubicInterpolationMode: 'monotone',
                fill: false,
                data: dataAux2
            },
            {
                label: 'Temperatura 3',
                backgroundColor: 'rgba(9, 255, 0, 0.5)',
                borderColor: 'rgb(9, 255, 0)',
                cubicInterpolationMode: 'monotone',
                fill: false,
                data: dataAux3
            },
            {
                label: 'Temperatura Ambiente',
                backgroundColor: 'rgba(161, 103, 9, 0.5)',
                borderColor: 'rgb(161, 103, 9)',
                cubicInterpolationMode: 'monotone',
                fill: false,
                data: dataAux4
            }
        ])
    }


    return (
        <div className="graphic-container">
            <Line
                data={{ datasets }}
                options={{
                    scales: {
                        xAxes: [{
                            type: 'realtime'
                        }]
                    }
                }}
            />

            <Button 
            onClick={() => gerarGrafico()}>Gerar gráfico</Button>
        </div>
    );
}