import { useState } from 'react';
import ListItem from './ListItem'
import NovaCoordenada from './NovaCoordenada'
import { coordenadaInput } from '../../interfaces/coordenadaInterface'
import "./Lista.css"
import { Container } from 'react-bootstrap';

const Lista = () => {

    const [coordenada, setCoordenada] = useState<coordenadaInput[]>([]);

    function addNovaCoordenada(ponto: coordenadaInput) {
        const coordenadasCopy = Array.from(coordenada);
        coordenadasCopy.push({
            coordenada_x: ponto.coordenada_x,
            coordenada_y: ponto.coordenada_y,
            coordenada_z: ponto.coordenada_z,
            eixo_r: ponto.eixo_r,
            tempo: ponto.tempo,
            zero_peca: ponto.zero_peca
        });
        setCoordenada(coordenadasCopy);
    }

    function updateCoordenada(target: any, index: number) {
        // console.log(target);

        const coordenadasCopy = Array.from(coordenada);

        switch (target.target.name.split("_")[0]) {
            case "coordenadaX":
                coordenadasCopy.splice(index, 1, {
                    ...coordenada[index],
                    coordenada_x: parseInt(target.target.value)
                });
                setCoordenada(coordenadasCopy);
                break;
            case "coordenadaY":
                coordenadasCopy.splice(index, 1, {
                    ...coordenada[index],
                    coordenada_y: parseInt(target.target.value)
                });
                setCoordenada(coordenadasCopy);
                break;
            case "coordenadaZ":
                coordenadasCopy.splice(index, 1, {
                    ...coordenada[index],
                    coordenada_z: parseInt(target.target.value)
                });
                setCoordenada(coordenadasCopy);
                break;
            case "eixoR":
                coordenadasCopy.splice(index, 1, {
                    ...coordenada[index],
                    eixo_r: parseInt(target.target.value)
                });
                setCoordenada(coordenadasCopy);
                break;
            case "tempo":
                coordenadasCopy.splice(index, 1, {
                    ...coordenada[index],
                    tempo: parseInt(target.target.value)
                });
                setCoordenada(coordenadasCopy);
                break;
            default:
                console.log(target.target.checked)
                coordenadasCopy.splice(index, 1, {
                    ...coordenada[index],
                    zero_peca: target.target.checked
                });
                setCoordenada(coordenadasCopy);
                break;
        }

    }

    function deleteCoordenada(index: number) {
        const itensCopy = Array.from(coordenada);
        itensCopy.splice(index, 1);
        setCoordenada(itensCopy);
    }

    return (

        <Container className="container">
            
            {coordenada.map((coordenada: coordenadaInput, index: number) => {
                //console.log(coordenada);

                return (
                    <ListItem
                        key={index}
                        index={index}
                        value={coordenada}
                        onChange={(event: any) => updateCoordenada(event, index)}
                        onDelete={() => deleteCoordenada(index)}
                    />
                )
            })}

            <NovaCoordenada onSubmit={addNovaCoordenada} />

        </Container>

    )
}

export default Lista;