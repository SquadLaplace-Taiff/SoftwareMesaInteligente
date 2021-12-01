import { useState } from "react";
import { Table } from "react-bootstrap";
import { Rampas, Rampa } from "../../../../interfaces/rampas" 

export function TabelaRampa(props:any) {

    const [dadosTabela, setDadosTabela] = useState<any>();
    const [pegarDados, setPegarDados] = useState<Boolean>(false);

    const url = 'http://localhost:8081/temperatura/rampaSubida';

    let teste:Array<any> = [];
    
    function fetchAPI() {
        fetch(url)
            .then(res => res.json())
            .then(data => {
                
                atribuir(data)
                setDadosTabela(data);

            })
            .catch(
                (error: Error) => console.log('error: ' + error.message)
            )
    }

    async function atribuir(data:any){
        if (props.tipoTeste === 'Aquecimento'){
            
            //data.forEach((dadoRampa:any) => {
            //    teste.push(dadoRampa.rampaSubindo);
            //});
            await setDadosTabela('oi');
            console.log(dadosTabela + "teste");
            
        }
    }
    
    if (!pegarDados){
        fetchAPI();
        setPegarDados(true)
    }

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th></th>
                    <th>Termopar 1</th>
                    <th>Termopar 2</th>
                    <th>Termopar 3</th>
                    <th>Ambiente</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Inicio</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Fim</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Duração</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Mínimo</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Máximo</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Δ Temperatura</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>

            </tbody>
        </Table>
    )
}