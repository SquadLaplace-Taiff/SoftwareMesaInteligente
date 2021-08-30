import * as React from 'react';

export class APIService extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            url: "http://localhost:8080/teste",
            myHeaders: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*",
                "Origin": "*"
            }
        };
    };

    buscarTodosTestes() {
        fetch(this.state.url, { 
            headers: this.state.myHeaders 
            })
            .then(res => res.json())
            .then(
                (result) => {
                    console.log(result);
                })
            .catch(error => console.log('Authorization failed : ' + error.message))
    };
    
    buscarTestePorId(id) {
        fetch(`${this.state.url}/id/${id}`, { 
            headers: this.state.myHeaders 
            })
            .then(res => res.json())
            .then(
                (result) => {
                    console.log(result);
                })
            .catch(error => console.log('Authorization failed : ' + error.message))
    };

    buscarTestePorModelo(modelo) {
        fetch(`${this.state.url}/${modelo}`, { 
            headers: this.state.myHeaders 
            })
            .then(res => res.json())
            .then(
                (result) => {
                    console.log(result);
                })
            .catch(error => console.log('Authorization failed : ' + error.message))
    };

    criarTeste(data) {
        fetch(this.state.url, {
            method: "POST",
            body: JSON.stringify(data),
            headers: this.state.myHeaders
        })
    }

    atualizarTeste(id, data) {
        fetch(`${this.state.url}/${id}`, {
            method: "PUT",
            body: JSON.stringify(data),
            headers: this.state.myHeaders
        })
    }

    deletarTeste(id) {
        fetch(`${this.state.url}/${id}`, {
            method: "DELETE",
            headers: this.state.myHeaders
        });
    }
}