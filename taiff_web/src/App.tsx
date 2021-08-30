import { Header } from './app/components/Header/Header';
import { Search } from './app/components/Search/Search';
import { ButtonTaiff } from './app/components/ButtonTaiff/ButtonTaiff';
import { ButtonExcluir } from './app/components/ButtonExcluir/ButtonExcluir';

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
      <Header />
      <ButtonTaiff funcao='put' value='2' data={data} > Put </ButtonTaiff>
      <ButtonTaiff funcao='novo ponto'> novo ponto </ButtonTaiff>
      <ButtonTaiff funcao='excluir ponto'> excluir ponto </ButtonTaiff>
      <ButtonTaiff funcao='zeroPeca'> zeroPeca </ButtonTaiff>
      <ButtonExcluir value={1}/>
      <Search />
    </section>

  );
}

export default App;
