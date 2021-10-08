const url = 'http://localhost:8080/teste';

var nomeTeste = document.formulario.nometeste.value
var nomeModelo = document.formulario.modelo.value


let zeroPecaX = document.formulario.zeropeca_input_x.value
let zeroPecaY = document.formulario.zeropeca_input_y.value
let zeroPecaZ = document.formulario.zeropeca_input_z.value
let zeroPecaR = document.formulario.zeropeca_input_r.value

let coordenada1X = document.formulario.coordenadas1_input_x.value
let coordenada1Y = document.formulario.coordenadas1_input_y.value
let coordenada1Z = document.formulario.coordenadas1_input_z.value
let coordenada1R = document.formulario.coordenadas1_input_r.value
let coordenada1T = document.formulario.coordenadas1_input_t.value

let coordenada2X = document.formulario.coordenadas2_input_x.value
let coordenada2Y = document.formulario.coordenadas2_input_y.value
let coordenada2Z = document.formulario.coordenadas2_input_z.value
let coordenada2R = document.formulario.coordenadas2_input_r.value
let coordenada2T = document.formulario.coordenadas2_input_t.value

let coordenada3X = document.formulario.coordenadas3_input_x.value
let coordenada3Y = document.formulario.coordenadas3_input_y.value
let coordenada3Z = document.formulario.coordenadas3_input_z.value
let coordenada3R = document.formulario.coordenadas3_input_r.value
let coordenada3T = document.formulario.coordenadas3_input_t.value

let coordenada4X = document.formulario.coordenadas4_input_x.value
let coordenada4Y = document.formulario.coordenadas4_input_y.value
let coordenada4Z = document.formulario.coordenadas4_input_z.value
let coordenada4R = document.formulario.coordenadas4_input_r.value
let coordenada4T = document.formulario.coordenadas4_input_t.value



function criaTeste(){
    let myBody = {
        modelo: document.formulario.modelo.value,
        nome_teste: document.formulario.nometeste.value,
        zeroPeca: {
            x: document.formulario.zeropeca_input_x.value,
            y: document.formulario.zeropeca_input_y.value,
            z: document.formulario.zeropeca_input_z.value,
        },
        coordenada: [{
            x: document.formulario.coordenadas1_input_x.value,
            y: document.formulario.coordenadas1_input_y.value,
            z: document.formulario.coordenadas1_input_z.value,
            r: document.formulario.coordenadas1_input_r.value,
            t: document.formulario.coordenadas1_input_t.value
        },{
            x: document.formulario.coordenadas2_input_x.value,
            y: document.formulario.coordenadas2_input_y.value,
            z: document.formulario.coordenadas2_input_z.value,
            r: document.formulario.coordenadas2_input_r.value,
            t: document.formulario.coordenadas2_input_t.value
        },{
            x: document.formulario.coordenadas3_input_x.value,
            y: document.formulario.coordenadas3_input_y.value,
            z: document.formulario.coordenadas3_input_z.value,
            r: document.formulario.coordenadas3_input_r.value,
            t: document.formulario.coordenadas3_input_t.value
        },{
            x: document.formulario.coordenadas4_input_x.value,
            y: document.formulario.coordenadas4_input_y.value,
            z: document.formulario.coordenadas4_input_z.value,
            r: document.formulario.coordenadas4_input_r.value,
            t: document.formulario.coordenadas4_input_t.value
        }]
    }

    fetch(`http://localhost:8080/teste/19`, {
        method: "put",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(myBody)
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        })
        .catch(
            error => console.log('Request failed', error)
        )
}



var form = document.getElementById('formulario');

form.addEventListener('submit', function(e) {

    e.preventDefault();

});
