import { Container } from "react-bootstrap";
import logo from "../../../assets/img/logo.png";
import "./Header.css"

export function Header(){
    return(
        <Container fluid className="header-container">
            <img src={logo}/> 
        </Container>
    )
}