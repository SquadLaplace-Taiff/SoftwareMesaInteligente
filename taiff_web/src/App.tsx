import { BrowserRouter, Route } from 'react-router-dom';
import { GraficoLinha } from './app/components/Graficos/GraficoLinha';
import TelaGrafico from './app/pages/TelaGrafico';
import TelaTabela from './app/pages/TelaTabela';
//import { Route, BrowserRouter } from 'react-router-dom';

import './style.css';

function App() {

  return (
    <BrowserRouter>
          <Route path="/" exact component={TelaGrafico}/>
          <Route path="/tabela" component={TelaTabela}/>
          <Route path="/teste" component={GraficoLinha}/> 
    </BrowserRouter>
  );
}

export default App;
