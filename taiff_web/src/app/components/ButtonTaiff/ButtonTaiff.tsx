import { MouseEventHandler } from 'react';
import {Button} from 'react-bootstrap';
import { APIService } from '../../services/API.service';
import './ButtonTaiff.css';

export function ButtonTaiff (props:any){

  const apiService = new APIService();

  let console1 = (verbo:string) => { 
    switch (verbo) {
      case 'teste1':
        console.log('teste1');
        return ;
        break;
  
      case 'teste2':
        console.log('teste2')
        return 2;
        break;
    }
  }

  // document.getElementById("button").addEventListener("click", event => {console1(props.verbo)});

  return(
    <Button className="TaiffButton" variant="secondary" id="button" onClick={() => {console1(props.verbo)}}>{props.name}</Button>
  );

}
