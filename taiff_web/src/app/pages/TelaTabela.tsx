import { useState } from "react";
import { Button, Table } from "react-bootstrap";
import { tabela } from "../interfaces/tabelaInterface";
import "./telaTabela.css";

export default function TelaTabela() {
    const [dados, setDados] = useState<Array<tabela>>([]);
    const url = 'http://localhost:8080/temperatura/folhaDeRosto/1';
    const urlCSV = 'http://localhost:8080/temperatura/folhaDeRostoCSV/1';
    
    function gerarCSV(){
        //fetch(urlCSV)  
        window.open(urlCSV);
    }

    function retornarLinhas(Tabela: tabela, Linha: number) {
        return (
            <tr>
                <td>{Linha}</td>
                <td>{Tabela.termopar_1}</td>
                <td>{Tabela.termopar_2}</td>
                <td>{Tabela.termopar_3}</td>
                <td>{Tabela.termopar_amb}</td>
                <td>{Tabela.media}</td>
                <td>{Tabela.temperaturaCorrigida}</td>
            </tr>
        );
    }

    function gerarTabela() {
        fetch(url)
            .then(res => res.json())
            .then(dados => {
                setDados(dados)
            })
    }



    return (
        <section id="section-tabela">
            <Table className="tabela" striped bordered hover>
                <thead>
                    <tr>
                        <th>Janelas</th>
                        <th>Temp Ambiente</th>
                        <th>Termopar 1</th>
                        <th>Termopar 2</th>
                        <th>Termopar 3</th>
                        <th>Temp Média</th>
                        <th>Temp Corrigida</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>113,53</td>
                        <td>94,50</td>
                        <td>111,67</td>
                        <td>26,02</td>
                        <td>106,57</td>
                        <td>105,55</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>25,87</td>
                        <td>114,37</td>
                        <td>95,19</td>
                        <td>116,23</td>
                        <td>108,60</td>
                        <td>107,72</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>25,47</td>
                        <td>112,15</td>
                        <td>96,34</td>
                        <td>118,65</td>
                        <td>109,05</td>
                        <td>108,57</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>25,73</td>
                        <td>107,63</td>
                        <td>99,13</td>
                        <td>108,38</td>
                        <td>105,05</td>
                        <td>104,31</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>24,70</td>
                        <td>112,23</td>
                        <td>104,27</td>
                        <td>121,86</td>
                        <td>112,79</td>
                        <td>113,08</td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <td>26,19</td>
                        <td>113,28</td>
                        <td>105,503</td>
                        <td>121,51</td>
                        <td>113,43</td>
                        <td>112,24</td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <td>26,47</td>
                        <td>115,35</td>
                        <td>102,92</td>
                        <td>113,64</td>
                        <td>110,64</td>
                        <td>109,16</td>
                    </tr>
                    <tr>
                        <td>8</td>
                        <td>25,48</td>
                        <td>114,49</td>
                        <td>101,34</td>
                        <td>120,62</td>
                        <td>112,15</td>
                        <td>111,66</td>
                    </tr>
                    <tr>
                        <td>9</td>
                        <td>25,39</td>
                        <td>114,36</td>
                        <td>99,83</td>
                        <td>114,57</td>
                        <td>109,59</td>
                        <td>109,19</td>
                    </tr>
                    <tr>
                        <td>Média</td>
                        <td>25,70</td>
                        <td>113,04</td>
                        <td>99,89</td>
                        <td>116,35</td>
                        <td>109,76</td>
                        <td>109,05</td>
                    </tr>
                </tbody>
            </Table>
            <Button onClick={() => gerarCSV()}> Gerar CSV </Button>
        </section>
    );
}