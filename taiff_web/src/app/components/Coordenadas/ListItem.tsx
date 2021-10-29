import React from 'react';
import { Container } from 'react-bootstrap';
import { coordenadaInput } from '../../interfaces/coordenadaInterface'
import "./ListItem.css"

type coordenadaAuxiliar = {
    onChange: any;
    onDelete: any;
    value: coordenadaInput;
    index: number;
}

const ListItem = (props: coordenadaAuxiliar) => {
    console.log(props.value);

    return (
        <Container className="Item-container container">
            <div>
                <label className="novoponto-label">
                    X
                    <input
                        type='number'
                        className="novoponto-input"
                        value={props.value.coordenada_x}
                        onChange={props.onChange}
                        name={`coordenadaX_${props.index}`}
                    />
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
                </label>
                <label className="novoponto-label">
                    Tempo
                    <input
                        type='number'
                        className="novoponto-input"
                        value={props.value.tempo}
                        onChange={props.onChange}
                        name={`tempo_${props.index}`}
                    />
                </label>
                <input
                    className="novoponto-input zero-peca"
                    checked={props.value.zero_peca}
                    onChange={props.onChange}
                    type="radio"
                    name='check-box'
                />
                <button className="btn-invisivel" onClick={props.onDelete}>Excluir</button>
            </div>
        </Container>
    );
};

export default ListItem;