import { useState } from 'react';
import ListItem from './ListItem'
import NovaCoordenada from './NovaCoordenada'
import { coordenadaInput } from '../../interfaces/coordenadaInterface'
import "./Lista.css"
import { Col, Container, Row } from 'react-bootstrap';

let zeroPecaInit = {
    coordenada_x: 0,
    coordenada_y: 0,
    coordenada_z: 0,
    eixo_r: 0,
    tempo: 0,
    zero_peca: true
}

let coordenadaInit = {
    coordenada_x: 0,
    coordenada_y: 0,
    coordenada_z: 0,
    eixo_r: 0,
    tempo: 0,
    zero_peca: false
}

const Lista = () => {

    const [coordenada, setCoordenada] = useState<coordenadaInput>(coordenadaInit);
    const [zeroPeca, setZeroPeca] = useState<coordenadaInput>(zeroPecaInit);

    function updateCoordenada(target: any) {
        switch (target.target.name.split("_")[0]) {
            case "coordenadaX":
                setCoordenada({
                    ...coordenada,
                    coordenada_x: parseInt(target.target.value)
                });
                break;
            case "coordenadaY":
                setCoordenada({
                    ...coordenada,
                    coordenada_y: parseInt(target.target.value)
                });
                break;
            case "coordenadaZ":
                setCoordenada({
                    ...coordenada,
                    coordenada_z: parseInt(target.target.value)
                });
                break;
            case "eixoR":
                setCoordenada({
                    ...coordenada,
                    eixo_r: parseInt(target.target.value)
                });
                break;
            case "tempo":
                setCoordenada({
                    ...coordenada,
                    tempo: parseInt(target.target.value)
                });
                break;
            default:
                setCoordenada({
                    ...coordenada,
                    zero_peca: false
                });
                break;
            }

        localStorage.setItem('coordenada', JSON.stringify(coordenada));

    }

    function deleteCoordenada() {
        setCoordenada(coordenadaInit);
        localStorage.setItem('coordenadas', JSON.stringify(coordenada));
    }

    function updateZeroPeca(target: any) {

        switch (target.target.name.split("_")[0]) {
            case "coordenadaX":
                setZeroPeca({
                    ...zeroPeca,
                    coordenada_x: parseInt(target.target.value)
                });
                break;
            case "coordenadaY":
                setZeroPeca({
                    ...zeroPeca,
                    coordenada_y: parseInt(target.target.value)
                });
                break;
            case "coordenadaZ":
                setZeroPeca({
                    ...zeroPeca,
                    coordenada_z: parseInt(target.target.value)
                });
                break;
            case "eixoR":
                setZeroPeca({
                    ...zeroPeca,
                    eixo_r: parseInt(target.target.value)
                });
                break;
            case "tempo":
                setZeroPeca({
                    ...zeroPeca,
                    tempo: parseInt(target.target.value)
                });
                break;
            default:
                setZeroPeca({
                    ...zeroPeca,
                    zero_peca: true
                });
                break;
            }
        localStorage.setItem('zeroPeca', JSON.stringify(zeroPeca));
    }

    function deleteZeroPeca() {
        setZeroPeca(zeroPecaInit);
    }

    return (

        <Container className="container">

            <Row>
                <h2 className="title-input">Zero Peça</h2>
                <ListItem 
                    key={0}
                    index={0}
                    value={zeroPeca}
                    onChange={(event: any) => updateZeroPeca(event)}
                    onDelete={() => deleteZeroPeca()}
                    zeroPeca={true}
                    />
            </Row>
            <Row>
                <h2 className="title-input">Coordenada</h2>
                <ListItem 
                    zeroPeca={false}
                    key={0}
                    index={0}
                    value={coordenada}
                    onChange={(event: any) => updateCoordenada(event)}
                    onDelete={() => deleteCoordenada()}
                    />
            </Row>

        </Container>

    )
}

export default Lista;