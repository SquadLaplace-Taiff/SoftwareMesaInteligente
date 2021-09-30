import { BrowserRouter, Route } from 'react-router-dom';
import TelaGrafico from './app/pages/TelaGrafico';
import TelaTabela from './app/pages/TelaTabela';
//import { Route, BrowserRouter } from 'react-router-dom';

import './style.css';

function App() {

  return (
    <BrowserRouter>
          <Route path="/" exact component={TelaGrafico}/>
          <Route path="/tabela" component={TelaTabela}/> 
    </BrowserRouter>
  );
}

export default App;
