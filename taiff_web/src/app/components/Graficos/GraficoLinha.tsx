import { Line, Chart } from 'react-chartjs-2';
import 'chartjs-adapter-luxon';
import StreamingPlugin from 'chartjs-plugin-streaming';

import { dataConfig, DataSetsConfig } from '../../interfaces/graficoInterface'

import './GraficoLinha.css';

export function GraficoLinha() {

    Chart.register(StreamingPlugin);

    const datasets: dataConfig = [
        {
            label: 'Temperatura 1',
            backgroundColor: 'rgba(54, 162, 235, 0.5)',
            borderColor: 'rgb(54, 162, 235)',
            cubicInterpolationMode: 'monotone',
            fill: false,
            data: []
        },
        {
            label: 'Temperatura ambiente',
            backgroundColor: 'rgba(255, 99, 132, 0.5)',
            borderColor: 'rgb(255, 99, 132)',
            cubicInterpolationMode: 'monotone',
            fill: false,
            data: []
        }
    ];

    return (

        <div className="graphic-container">
            <Line
                data={{ datasets }}
                options={{
                    scales: {
                        x: {
                            type: 'realtime',
                            realtime: {
                                delay: 2000,
                                onRefresh: (chart: any) => {
                                    chart.data.datasets.forEach((dataset: DataSetsConfig) => {
                                        dataset.data.push({
                                            x: Date.now(),
                                            y: (Math.random() + Math.random()) * 10
                                        });
                                    });
                                }
                            }
                        }
                    }
                }}
            />
        </div>
    );
}