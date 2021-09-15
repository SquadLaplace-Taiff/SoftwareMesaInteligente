import { Line, Chart } from 'react-chartjs-2';
import { Button } from 'react-bootstrap';
import 'chartjs-adapter-luxon';
import StreamingPlugin from 'chartjs-plugin-streaming';
import { useState } from 'react';
import moment from 'moment';

export function GraficoLinha() {

    Chart.register(StreamingPlugin);

    const url = 'http://localhost:8080/temperatura/1';

    const dataAux: any = []

    // const moment = require('moment');

    const [datasets, setDatasets] = useState<any>();

    fetch(`${url}`)
        .then(res => res.json())
        .then(
            (result) => {
                result.forEach((dados: any) => {
                    // console.log(dados);
                    dataAux.push({
                        x: dados.dt_leitura,
                        y: dados.termopar_1,
                    });
                })
            })
        .catch(error => console.log('Authorization failed : ' + error.message))


    console.log(dataAux);


    function gerarGrafico() { 
        setDatasets([
            {
                label: 'Temperatura 1',
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgb(54, 162, 235)',
                cubicInterpolationMode: 'monotone',
                fill: false,
                data: dataAux
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