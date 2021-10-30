import { useState } from "react";
import { Button, Col, Form, Modal, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import { formFolhaDeRosto } from "../../../../interfaces/folhaDeRostoIterface";




export function ModalFolhaDeRosto() {
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    let novoEstado: formFolhaDeRosto;

    const [folhaDeRosto, setFolhaDeRosto] = useState<formFolhaDeRosto>()

    return (
        <section>
            <Button variant="primary" onClick={handleShow}>
                Gerar Folha de Rosto
            </Button>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Exportar Folha de Rosto</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Row className="mb-3">
                            <Form.Group controlId="formNomeTeste">
                                <Form.Label>Nome do Teste</Form.Label>
                                <Form.Control onChange={(e) => {
                                    novoEstado = {
                                        ...folhaDeRosto,
                                        nomeDoTeste: e.target.value
                                    }
                                    setFolhaDeRosto(novoEstado);
                                }} type="text" placeholder="Nome do Teste" />
                            </Form.Group>
                            <Form.Group controlId="formExecutor">
                                <Form.Label>Executor</Form.Label>
                                <Form.Control onChange={(e) => {
                                    novoEstado = {
                                        ...folhaDeRosto,
                                        executor: e.target.value
                                    }
                                    setFolhaDeRosto(novoEstado);
                                }} type="text" placeholder="Nome do Executor" />
                            </Form.Group>
                            <Form.Group controlId="formLocal">
                                <Form.Label>Local</Form.Label>
                                <Form.Control onChange={(e) => {
                                    novoEstado = {
                                        ...folhaDeRosto,
                                        local: e.target.value
                                    }
                                    setFolhaDeRosto(novoEstado);
                                }} type="text" placeholder="Local da Análise" />
                            </Form.Group>
                        </Row>
                        <Row className="mb-3">
                            <Form.Group controlId="formTipo">
                                <Form.Label>Tipo</Form.Label>
                                <Form.Control onChange={(e) => {
                                    novoEstado = {
                                        ...folhaDeRosto,
                                        tipo: e.target.value
                                    }
                                    setFolhaDeRosto(novoEstado);
                                }} type="text" placeholder="Tipo do Produto" />
                            </Form.Group>
                            <Form.Group controlId="formFabricante">
                                <Form.Label>Fabricante</Form.Label>
                                <Form.Control onChange={(e) => {
                                    novoEstado = {
                                        ...folhaDeRosto,
                                        fabricante: e.target.value
                                    }
                                    setFolhaDeRosto(novoEstado);
                                }} type="text" placeholder="Fabricante" />
                            </Form.Group>
                        </Row>
                        <Row className="mb-3">
                            <Form.Group controlId="formModeloEmPlaca">
                                <Form.Label>Modelo em Placa</Form.Label>
                                <Form.Control onChange={(e) => {
                                    novoEstado = {
                                        ...folhaDeRosto,
                                        modeloEmPlaca: e.target.value
                                    }
                                    setFolhaDeRosto(novoEstado);
                                }} type="text" placeholder="Modelo em Placa" />
                            </Form.Group>
                            <Form.Group controlId="formFrequencia">
                                <Form.Label>Frequencia</Form.Label>
                                <Form.Control onChange={(e) => {
                                    novoEstado = {
                                        ...folhaDeRosto,
                                        frequencia: parseInt(e.target.value)
                                    }
                                    setFolhaDeRosto(novoEstado);
                                }} type="number" placeholder="Frequencia" />
                            </Form.Group>
                        </Row>
                        <Row className="mb-3">
                            <Form.Group controlId="formPaisFabricado">
                                <Form.Label>Pais Fabricado</Form.Label>
                                <Form.Control onChange={(e) => {
                                    novoEstado = {
                                        ...folhaDeRosto,
                                        paisFabricado: e.target.value
                                    }
                                    setFolhaDeRosto(novoEstado);
                                }} type="text" placeholder="Pais Fabricado" />
                            </Form.Group>

                            <Form.Group controlId="formModeloComercial">
                                <Form.Label>Modelo Comercial</Form.Label>
                                <Form.Control onChange={(e) => {
                                    novoEstado = {
                                        ...folhaDeRosto,
                                        modeloComercial: e.target.value
                                    }
                                    setFolhaDeRosto(novoEstado);
                                }} type="text" placeholder="Frequencia" />
                            </Form.Group>
                        </Row>
                        <Row className="mb-3">
                            <Form.Group controlId="formTensao">
                                <Form.Label>Tensão</Form.Label>
                                <Form.Control onChange={(e) => {
                                    novoEstado = {
                                        ...folhaDeRosto,
                                        tensao: parseInt(e.target.value)
                                    }
                                    setFolhaDeRosto(novoEstado);
                                }} type="number" placeholder="Tensão" />
                            </Form.Group>

                            <Form.Group controlId="formPotencia">
                                <Form.Label>Potência</Form.Label>
                                <Form.Control onChange={(e) => {
                                    novoEstado = {
                                        ...folhaDeRosto,
                                        potencia: parseInt(e.target.value)
                                    }
                                    setFolhaDeRosto(novoEstado);
                                }} type="number" placeholder="Potência" />
                            </Form.Group>
                        </Row>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Fechar
                    </Button>
                    <Link  to={{
                        pathname: "/folhaDeRosto",
                        state: folhaDeRosto,
                    }} >
                        Gerar Folha de Rosto
                    </Link>
                </Modal.Footer>
            </Modal>
        </section>
    );
}