import logo from '../../../assets/img/logo.png';
import { Container } from 'react-bootstrap';
import './Header.css';

export function Header() {
    return (
        
        <Container className="containerHeader"> 
            <img className="logoTaiff" src={logo} alt="" /> 
        </Container>
    );
}