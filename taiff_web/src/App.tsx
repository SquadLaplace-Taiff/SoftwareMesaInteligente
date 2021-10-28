import { BrowserRouter, Route } from 'react-router-dom';
import { Home } from './app/pages/Home/Home'
import { Grafico } from './app/pages/grafico/Grafico'
import { FolhaDeRosto } from './app/pages/folhaDeRosto/FolhaDeRosto'

import './style.css';
import Lista from './app/components/Coordenadas/Lista';
import { Relatorio } from './app/pages/Relatorio/Relatorio';

function App() {
  return (
    <BrowserRouter>
        <Route path="/" exact component={Home} />
        <Route path="/grafico" component={Grafico} />
        <Route path="/relatorio" component={Relatorio}/>
        <Route path="/folhaDeRosto" component={FolhaDeRosto}/> 
    </BrowserRouter>
  );
}

export default App;
