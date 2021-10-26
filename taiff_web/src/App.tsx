import { BrowserRouter, Route } from 'react-router-dom';
import { Home } from './app/pages/Home/Home'
import { Graficos } from './app/pages/graficos/Graficos'
import { FolhaDeRosto } from './app/pages/folhaDeRosto/FolhaDeRosto'

import './style.css';

function App() {
  return (
    <BrowserRouter>
        <Route path="/" exact component={Home} />
        <Route path="/graficos" component={Graficos} />
        <Route path="/folhaDeRosto" component={FolhaDeRosto}/> 
    </BrowserRouter>
  );
}

export default App;
