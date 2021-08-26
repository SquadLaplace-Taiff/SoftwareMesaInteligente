import {Button} from 'react-bootstrap';
import { APIService } from '../../services/API.service';
import './ButtonTaiff.css';

export function ButtonTaiff (props:any){

  const apiService = new APIService();

  let clicou = () => { apiService.Teste()};

  return(
    <Button className="TaiffButton" variant="secondary" onClick={clicou}>{props.name}</Button>
  );

}
