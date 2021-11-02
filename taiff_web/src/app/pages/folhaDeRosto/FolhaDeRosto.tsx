import { formFolhaDeRosto } from "../../interfaces/folhaDeRostoIterface";
import { Button, Container } from "react-bootstrap";
import GraficoEstatico from "../../components/GraficoEstatico/GraficoEstatico";
import { Tabela } from "../Relatorio/Tabela/Tabela";
import jsPDF from "jspdf";
import html2canvas from 'html2canvas';
import "./FolhaDeRosto.css"
import { Header } from "../../components/Header/Header";

type propsType = {
    location: {
        state: formFolhaDeRosto
    }
}

export function FolhaDeRosto(props: propsType) {
    console.log(props)
    let data = new Date()

    function gerarPdf(){
        const html =  document.getElementById("sectionFolhaDeRosto")!;
        html2canvas(html)
        .then((canvas) => {
            const imgData = canvas.toDataURL('image/png');
            const pdf = new jsPDF();
            pdf.addImage(imgData, 'JPEG', 0, 0, 210, 200);
            pdf.save("download.pdf");
        })
        
    }
    return (

        <section id="sectionFolhaDeRosto">

            <Header/>
            <h1 className="fdr-titulo">Relatório do Teste: {props.location.state.nomeDoTeste}</h1>
            <Container> 
                <h2 className="fdr-subtitulo">Identificação do Técnico</h2>
                <ul className="fdr-lista">
                    <li className="fdr-item"><strong>Executante: </strong> {props.location.state.executor}</li>
                    <li className="fdr-item"><strong>Data: </strong> {data.toLocaleDateString()}</li>
                    <li className="fdr-item"><strong>Local: </strong> {props.location.state.local}</li>
                </ul>
            </Container>
            <Container>
                <h2 className="fdr-subtitulo">Identificação do produto</h2>
                <ul className="fdr-lista">
                    <li className="fdr-item"><strong>Tipo de produto: </strong> {props.location.state.tipo}</li>
                    <li className="fdr-item"><strong>Fabricante: </strong> {props.location.state.fabricante}</li>
                    <li className="fdr-item"><strong>Modelo em placa: </strong> {props.location.state.modeloEmPlaca}</li>
                    <li className="fdr-item"><strong>Frequência: </strong> {props.location.state.frequencia}</li>
                    <li className="fdr-item"><strong>País de Fabricação: </strong> {props.location.state.paisFabricado}</li>
                    <li className="fdr-item"><strong>Modelo Comercial: </strong> {props.location.state.modeloComercial}</li>
                    <li className="fdr-item"><strong>Tensão: </strong> {props.location.state.tensao}</li>
                    <li className="fdr-item"><strong>Potência: </strong> {props.location.state.potencia}</li>
                </ul>
            </Container>
            <Container>
                <h2 className="fdr-subtitulo">Análise Gráfica</h2>
                <div className="fdr-background">
                    <GraficoEstatico/>
                </div>
            </Container>
            <Container>
                <h2 className="fdr-subtitulo">Análise Numérica</h2>
                <div className="fdr-background">
                    <Tabela/>
                </div>
            </Container>
            <Container>
                <h2 className="fdr-subtitulo">Notas</h2>
                <p className="fdr-background conteudo-editavel" contentEditable></p>
            </Container>
            <Container className="fdr-flex">
                <Button className="btn-primary" onClick={() => gerarPdf()}>Exportar PDF</Button>
            </Container>

        </section>
    );



}