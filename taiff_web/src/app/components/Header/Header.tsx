import { Container } from "react-bootstrap";
import { Link } from "react-router-dom";
import logo from "../../../assets/img/logo.png";
import "./Header.css"

export function Header(){
    return(
        <Container fluid className="header-container">
            <Link className="logoLink" to="/">
                <img src={logo}/> 
            </Link>
        </Container>
    )
}