import * as React from 'react';

export class APIService extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            res: {}
        };
    };

    testeModel = {};

    myHeaders = new Headers();


    Teste() {
        fetch("http://localhost:8080/teste", { headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': 'http://localhost:3000/' } })
            .then(res => res.json())
            .then(
                (result) => {
                    console.log(result);
                })
            .catch(error => console.log('Authorization failed : ' + error.message))
    };

    TestePost() {
        this.testeModel = {
            modelo: "Teste albino",
            nome_teste: "Nome Teste Albino"
        }

        fetch('http://localhost:8080/teste', {
            method: "POST",
            body: JSON.stringify({
            "modelo": "Teste albino def",
            "nome_teste": "Nome Teste Albino def"
            }),
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*",
                "Origin": "*"
            }
        })
    }

    TestePut() {
        fetch('http://localhost:8080/teste/1', {
            method: "PUT",
            body: JSON.stringify({
            "modelo": "Teste albinoasd",
            "nome_teste": "Nome Teste Albinoasdas",
            "coordenada": [
                {
                    "x": 3,
                    "y": 3,
                    "z": 3,
                    "r": 3,
                    "t": 3,
                },
                {
                    "x": 4,
                    "y": 4,
                    "z": 4,
                    "r": 4,
                    "t": 4,
                },
            ],
            "zeroPeca": {
                "x": 2,
                "y": 2,
                "z": 2,
            }
            }),
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*",
                "Origin": "*"
            }
        })
    }

    TesteDelete() {
        fetch('http://localhost:8080/teste/1', {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*",
                "Origin": "*"
            }
        });
    }
}