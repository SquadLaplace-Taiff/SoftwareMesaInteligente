import {Button} from 'react-bootstrap';
import './ButtonTaiff.css';

export function ButtonTaiff (props:any){
  return(
     <Button className="TaiffButton" variant="secondary">{props.name}</Button>
  );

}
