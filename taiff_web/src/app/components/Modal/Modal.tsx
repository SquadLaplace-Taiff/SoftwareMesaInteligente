import React from 'react';
import './Modal.css';


type testeModal = { handleClose:any, show:string, children:any}
export class Modal extends React.Component<testeModal> {

render(){

    return (
      <div className={this.props.show}>
        <section className="modal-main">
          {this.props.children}
          <button onClick={this.props.handleClose}>Close</button>
        </section>
      </div>
    );
  } 
 
}

