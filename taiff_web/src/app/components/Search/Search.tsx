import { Container } from 'react-bootstrap';
import './Search.css';
import searchImg from '../../../assets/img/searchImg.png';


export function Search() {
    return (

        <Container className="containerSearch">
            <h1 className="tituloSearch">Modelo</h1>
            <input className="inputSearch" type="text" placeholder="Ex: Secador XLR8" />
            <button type="submit" className="searchImg"> <img className="searchImg" src={searchImg} alt="search" /> </button>
        </Container>
    );
}