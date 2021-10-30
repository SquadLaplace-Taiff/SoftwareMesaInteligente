import { useState } from "react";
import { Button, Col, Modal,Row } from "react-bootstrap";
import './ModalSalvarTeste.css';

export function ModalSalvarTeste(props: any) {

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
        <section>
            <Button variant="primary" onClick={handleShow}>
                Salvar Teste
            </Button>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Salvar Teste</Modal.Title>
                </Modal.Header>
                <Modal.Body className="modal-links">
                    <Row>
                        <Col><label className="salvarTeste-label">Modelo <input type="text" /></label></Col>
                        <Col><label className="salvarTeste-label">Nome do Teste<input type="text" /></label></Col>
                    </Row>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="danger" onClick={handleClose}>
                        Cancelar
                    </Button>
                    <Button className="btn-primary">
                        Salvar Teste
                    </Button>
                </Modal.Footer>
            </Modal>
        </section>
    );

}