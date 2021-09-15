import { useState } from 'react';
import { Button } from 'react-bootstrap';
import { APIService } from '../../services/API.service';
import { teste } from '../../interfaces/interfaces';
import './ButtonTaiff.css';


export function ButtonTaiff(props: any) {

  const apiService = new APIService();

  const [count, setCount] = useState(0);
  const [data, setData] = useState<teste>();

  function funcaoButton(values: string) {
    switch (values) {
      case 'put':
        return console.log(`put value: ${props.value} data: ${JSON.stringify(props.data)}`);
        // return apiService.atualizarTeste(props.value, props.data);
      case 'novo ponto':
        console.log('novo ponto');
        return setCount(count + 1);
      case 'excluir ponto':
        console.log('excluir ponto');
        return setCount(count - 1);
      case 'zeroPeca':
        console.log('zeroPeca');
        return setData(props.data);
    }
  }



  return (
    <Button
      className="TaiffButton"
      variant="secondary"
      id="button"
      onClick={() => funcaoButton(props.funcao)} {...props} />
  );

}
