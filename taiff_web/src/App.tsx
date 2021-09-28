import { Header } from './app/components/Header/Header';
import { Search } from './app/components/Search/Search';
import { ButtonTaiff } from './app/components/ButtonTaiff/ButtonTaiff';
import { ButtonExcluir } from './app/components/ButtonExcluir/ButtonExcluir';
import { Posicao } from './app/components/Posicao/Posicao';
import { SubHeader } from './app/components/SubHeader/SubHeader';
import { GraficoLinha } from './app/components/Graficos/GraficoLinha';
import GraficoEstatico from './app/components/GraficoEstatico/GraficoEstatico';

import './style.css';
import { teste } from './app/interfaces/interfaces';

function App() {

  let data: teste = {
    modelo: 'teste',
    nome_teste: 'nome_teste',
    coordenadas: [ 
      {
        x: 3,
        y: 3,
        z: 3,
        r: 4,
        t: 5
      }
    ]
  }
  return (
    <section>
      <GraficoEstatico />
    </section>

  );
}

export default App;
