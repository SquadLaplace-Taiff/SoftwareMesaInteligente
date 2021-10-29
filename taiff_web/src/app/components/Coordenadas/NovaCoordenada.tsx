import { useState } from 'react';
import { coordenadaInput } from '../../interfaces/coordenadaInterface';
import "./NovaCoordenada.css"

function NovaCoordenada({ onSubmit }: any) {

    const [novaCoordenada, setNovaCoordenada] = useState<coordenadaInput>({
        coordenada_x: 0,
        coordenada_y: 0,
        coordenada_z: 0,
        eixo_r: 0,
        tempo: 0,
        zero_peca: false
    });

    function setNovoPonto({ target }: any) {
        setNovaCoordenada(target.value);
    }

    function submit(e: any) {
        e.preventDefault();
        onSubmit(novaCoordenada);
    }

    return (
        <div>
            <form onSubmit={submit}>
                <button className="btn-invisivel" type="submit">
                    Novo ponto
                </button>
            </form>
        </div>
    );
}

export default NovaCoordenada;