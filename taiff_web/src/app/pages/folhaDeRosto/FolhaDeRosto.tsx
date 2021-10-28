import { formFolhaDeRosto } from "../../interfaces/folhaDeRostoIterface";
import { Button, Col, Form, Modal, Row } from "react-bootstrap";
import logo from "../../../assets/img/logo.png";
import GraficoEstatico from "../../components/GraficoEstatico/GraficoEstatico";
import { Tabela } from "../Relatorio/Tabela/Tabela";
import jsPDF from "jspdf";


type propsType = {
    location: {
        state: formFolhaDeRosto
    }
}

export function FolhaDeRosto(props: propsType) {
    console.log(props)
    let data = new Date()
   

    function gerearPdf(){
        let html =  document.querySelector("#sectionFolhaDeRosto");
        let doc = new jsPDF('p','pt','a4', true);
        //doc.html(  html.toString() )
    }
    return (

        <section id="sectionFolhaDeRosto">
            <header>
                <img src={logo}/> 
            </header>
            <h1>Relatorio do Teste: {props.location.state.nomeDoTeste}</h1>
            <div> 
                <h2>Identificação do Tecnico</h2>
                <ul>
                    <li><strong>Executante: </strong> {props.location.state.executor}</li>
                    <li><strong>Data: </strong> {data.toLocaleDateString()}</li>
                    <li><strong>Local: </strong> {props.location.state.local}</li>
                </ul>
            </div>
            <div>
                <h2>Identificação do produto</h2>
                <ul>
                    <li><strong>Tipo de produto: </strong> {props.location.state.tipo}</li>
                    <li><strong>Fabricante: </strong> {props.location.state.fabricante}</li>
                    <li><strong>Modelo em placa: </strong> {props.location.state.modeloEmPlaca}</li>
                    <li><strong>Frequência: </strong> {props.location.state.frequencia}</li>
                    <li><strong>Pais de Fabricação: </strong> {props.location.state.paisFabricado}</li>
                    <li><strong>Modelo Comercial: </strong> {props.location.state.modeloComercial}</li>
                    <li><strong>Tensão: </strong> {props.location.state.tensao}</li>
                    <li><strong>Potência: </strong> {props.location.state.potencia}</li>
                </ul>
            </div>
            <div>
                <h2>Analise Grafica</h2>
                <div>
                    <GraficoEstatico/>
                </div>
            </div>
            <div>
                <h2>Analise Numerica</h2>
                <div>
                    <Tabela/>
                </div>
            </div>
            <div>
                <h2>Notas</h2>
                <p contentEditable>
                </p>
            </div>
            <div>
                <Button>Exporta pdf:</Button>
            </div>

        </section>
    );



}