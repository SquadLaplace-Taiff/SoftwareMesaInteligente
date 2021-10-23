import { BrowserRouter, Route } from 'react-router-dom';
import {APIService} from './app/services/API.service';
import { Home } from './app/pages/Home/Home'
import { Graficos } from './app/pages/graficos/Graficos'
import { FolhaDeRosto } from './app/pages/folhaDeRosto/FolhaDeRosto'

//import { Route, BrowserRouter } from 'react-router-dom';

import './style.css';
import { useState } from 'react';
export function teste(){
  console.log("aqui")
} 

function App() {
 // const[ temperatura, setTeperatura ] = useState()

    
  return (
    <BrowserRouter>
          <Route path="/" exact component={Home}/>
          <Route path="/graficos" render={ ( ) => <Graficos data={3} />} />
          <Route path="/folhaDeRosto" component={FolhaDeRosto}/> 
    </BrowserRouter>
  );
}

export default App;
