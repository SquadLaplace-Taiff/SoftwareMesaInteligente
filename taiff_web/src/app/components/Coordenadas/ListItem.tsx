import React from 'react';
import { coordenadaInput } from '../../interfaces/coordenadaInterface'

type coordenadaAuxiliar = {
    onChange: any;
    onDelete: any;
    value: coordenadaInput;
    index: number;
}

const ListItem = (props: coordenadaAuxiliar) => {
    console.log(props.value);

    return (
        <div className="Item-container">
            <div>
                <label>
                    X
                    <input
                        type='number'
                        className="Item-field"
                        value={props.value.coordenada_x}
                        onChange={props.onChange}
                        name={`coordenadaX_${props.index}`}
                    />
                </label>
                <label>
                    Y
                    <input
                        type='number'
                        className="Item-field"
                        value={props.value.coordenada_y}
                        onChange={props.onChange}
                        name={`coordenadaY_${props.index}`}
                    />
                </label>
                <label>
                    Z
                    <input
                        type='number'
                        className="Item-field"
                        value={props.value.coordenada_z}
                        onChange={props.onChange}
                        name={`coordenadaZ_${props.index}`}
                    />
                </label>
                <label>
                    R
                    <input
                        type='number'
                        className="Item-field"
                        value={props.value.eixo_r}
                        onChange={props.onChange}
                        name={`eixoR_${props.index}`}
                    />
                </label>
                <label>
                    Tempo
                    <input
                        type='number'
                        className="Item-field"
                        value={props.value.tempo}
                        onChange={props.onChange}
                        name={`tempo_${props.index}`}
                    />
                </label>
                <input
                    className="Item-field"
                    checked={props.value.zero_peca}
                    onChange={props.onChange}
                    type="radio"
                    name='check-box'
                />
                <button onClick={props.onDelete}>Excluir</button>
            </div>
        </div>
    );
};

export default ListItem;