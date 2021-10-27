import React, { useState } from 'react';
import { coordenadaInput } from '../../interfaces/coordenadaInterface';

const NewTaskInput = ({ onSubmit }: any) => {

    const [newItem, setNewItem] = useState<coordenadaInput>({
        coordenada_x: 0,
        coordenada_y: 0,
        coordenada_z: 0,
        eixo_r: 0,
        tempo: 0,
        zero_peca: ""
    });

    function setNewTask({ target }: any) {
        setNewItem(target.value);
    }

    function submit(e: any) {
        e.preventDefault();
        onSubmit(newItem);
    }

    return (
        <div>
            <form onSubmit={submit}>
                <button type="submit">
                    Adicionar
                </button>
            </form>
        </div>
    )
};

export default NewTaskInput;