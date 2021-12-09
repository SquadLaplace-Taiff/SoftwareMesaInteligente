import React from 'react';
import { Container } from 'react-bootstrap';
import { coordenadaInput } from '../../interfaces/coordenadaInterface'
import "./ListItem.css"

type coordenadaAuxiliar = {
    onChange: any;
    onDelete: any;
    value: coordenadaInput;
    index: number;
    zeroPeca: boolean;
}

const ListItem = (props: coordenadaAuxiliar) => {
    console.log(props.value);

    function isZeroPeca() {
        if (!props.zeroPeca) {
            return (
                <label className="novoponto-label">
                    Tempo
                    <input
                        type='number'
                        className="novoponto-input"
                        value={props.value.tempo}
                        onChange={props.onChange}
                        name={`tempo_${props.index}`}
                    />
                    s
                </label>
            )

        }
    }

        return (
            <Container className="Item-container container">
                <label className="novoponto-label">
                    X
                    <input
                        type='number'
                        className="novoponto-input"
                        value={props.value.coordenada_x}
                        onChange={props.onChange}
                        name={`coordenadaX_${props.index}`}
                    />
                    mm
                </label>
                <label className="novoponto-label">
                    Y
                    <input
                        type='number'
                        className="novoponto-input"
                        value={props.value.coordenada_y}
                        onChange={props.onChange}
                        name={`coordenadaY_${props.index}`}
                    />
                    mm
                </label>
                <label className="novoponto-label">
                    Z
                    <input
                        type='number'
                        className="novoponto-input"
                        value={props.value.coordenada_z}
                        onChange={props.onChange}
                        name={`coordenadaZ_${props.index}`}
                    />
                    mm
                </label>
                <label className="novoponto-label">
                    R
                    <input
                        type='number'
                        className="novoponto-input"
                        value={props.value.eixo_r}
                        onChange={props.onChange}
                        name={`eixoR_${props.index}`}
                    />
                    mm
                </label>

                {
                    isZeroPeca()
                }
                <button className="btn-invisivel" onClick={props.onDelete}>Limpar</button>
            </Container>
        );
    };
    export default ListItem;