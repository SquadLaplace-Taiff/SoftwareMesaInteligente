import { Container } from 'react-bootstrap';
import './Search.css';
import searchImg from '../../../assets/img/searchImg.png';
import { APIService } from '../../services/API.service';
import React from 'react';
import {Modal} from '../Modal/Modal';


export class Search extends React.Component<{}, { value: string, show: string }> {

    constructor(props: any) {
        super(props);
        this.state = { value: '', show: 'display-none' };


        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event: any) {
        this.setState({ value: event.target.value });
    }

    handleSubmit(event: any) {

        const apiService = new APIService();
        apiService.buscarTestePorModelo(this.state.value);
        event.preventDefault();
        this.showModal();
        console.log(this.state.value);

    }

    showModal = () => {
        this.setState({ show: "display-block" });
    };

    hideModal = () => {
        this.setState({ show: "display-none" });
    };


    render() {

        return (

            <Container className="containerSearch">
                <form onSubmit={this.handleSubmit}>
                    <h1 className="tituloSearch">Modelo:</h1>
                    <input className="inputSearch" type="text" placeholder="Ex: Secador XLR8" value={this.state.value} onChange={this.handleChange} />
                    <button type="submit" className="searchImg"> <img className="searchImg" src={searchImg} alt="search" /> </button>
                </form>
                <Modal show={this.state.show} handleClose={this.hideModal}>
                    <p>Modal</p>
                    <p>Data</p>
                </Modal>
            </Container>

        );
    }
}






