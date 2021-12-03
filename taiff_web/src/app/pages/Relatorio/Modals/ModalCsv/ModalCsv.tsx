import { useState } from "react";
import { Button, Modal } from "react-bootstrap";
import './ModalCsv.css'

export function ModalCsv(props: any) {

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
        <section>
            <Button variant="primary" onClick={handleShow}>
                Gerar CSV
            </Button>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Exportar CSV</Modal.Title>
                </Modal.Header>
                <Modal.Body className="modal-links">
                    <a href={`http://localhost:8081/temperatura/temperaturasCSV`}> Gerar CSV com os dados de Temperatura </a>
                    <a href={`http://localhost:8081/temperatura/folhaDeRostoCSV`}> Gerar CSV com os dados de Médias de Temperaturas </a>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Fechar
                    </Button>
                </Modal.Footer>
            </Modal>
        </section>
    );

}