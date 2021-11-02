import { useState } from "react";
import { Button, Col, Modal,Row } from "react-bootstrap";
import { APIService } from "../../../../services/API.service";
import './ModalSalvarTeste.css';

export function ModalSalvarTeste(props: any) {

    const [show, setShow] = useState(false);
    const [modelo, setModelo] = useState<string>('');
    const [nomeTeste, setNomeTeste] = useState<string>('');
    const [disabledBtn, setDisabledBtn] = useState<boolean>(true);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const apiService = new APIService();

    function salvarTeste() {

        const teste = {
            modelo: modelo,
            nome_teste: nomeTeste,
            coordenada: JSON.parse(localStorage.getItem('coordenadas')!),
        };

        apiService.criarTeste(teste);
        handleClose();
    }

    function changeIinput() {
        (modelo != '' && nomeTeste != '') ?
            setDisabledBtn(false) :
            setDisabledBtn(true);
    }

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
                        <Col>
                            <label className="salvarTeste-label">Modelo 
                                <input 
                                    onChange={ e => {
                                        setModelo(e.target.value);
                                        changeIinput();
                                    }} 
                                    onBlur={ e => {
                                        setModelo(e.target.value);
                                        changeIinput();
                                    }}
                                    type="text" />
                            </label>
                            </Col>
                        <Col>
                            <label className="salvarTeste-label">Nome do Teste
                                <input 
                                    onChange={ e => {
                                        setNomeTeste(e.target.value);
                                        changeIinput();
                                    }} 
                                    onBlur={ e => {
                                        setNomeTeste(e.target.value);
                                        changeIinput();
                                    }}
                                    type="text"/>
                            </label>
                            </Col>
                    </Row>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="danger" onClick={handleClose}>
                        Cancelar
                    </Button>
                    <Button 
                        disabled={disabledBtn}
                        onClick={() => salvarTeste()}
                        className="btn-primary">
                        Salvar Teste
                    </Button>
                </Modal.Footer>
            </Modal>
        </section>
    );

}