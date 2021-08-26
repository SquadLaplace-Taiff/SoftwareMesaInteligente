const url = 'http://localhost:8080/teste/Teste%20albino%20asd';

// Conexão com a API
fetch(url)
    .then(response => response.json())
    .then(data => {
        getData(data);
    })
    .catch(
        error => console.log('Request failed', error)
    )

// Pega os dados da requisição(GET)
function getData(data) {
    const dataBase = data;
    generateCoordenadas(dataBase);
    gerarNomeTeste(dataBase);
    gerarZeroPeca(dataBase);
}

// Gera as coordenadas no front
function generateCoordenadas(dataBase) {
    let section = document.getElementById("coordenadas");

    dataBase.coordenada.forEach((data, i) => {
        console.log(data);

        let div = document.createElement("div");
        div.className = "eixo-container"
        //Input
        div.innerHTML = `
        <div class="eixo-input">
            <label for="eixo-x-${i}">Eixo X</label>
            <input id="eixo-x-${i}" class="eixo" value="${data.x}"/>
        </div>

        <div class="eixo-input">
            <label for="eixo-y-${i}">Eixo Y</label>
            <input id="eixo-y-${i}" class="eixo" value="${data.y}"/>
        </div>

        <div class="eixo-input">
            <label for="eixo-z-${i}">Eixo Z</label>
            <input id="eixo-z-${i}" class="eixo" value="${data.z}"/>
        </div>

        <div class="eixo-input">
            <label for="eixo-r-${i}">Eixo R</label>
            <input id="eixo-r-${i}" class="eixo" value="${data.r}"/>
        </div>

        <div class="eixo-input">
            <label for="eixo-t-${i}">Eixo T</label>
            <input id="eixo-t-${i}" class="eixo" value="${data.t}"/>
        </div>
        `;

        section.appendChild(div)
    });
}

function gerarNomeTeste(data) { 
    teste = document.getElementById('nome-teste');

    teste.innerHTML = data.nome_teste;
}

function gerarZeroPeca(data) {
    let section = document.getElementById("coordenadas");

    let div = document.createElement("div");
    div.className = "zeroPeca-container"
    div.innerHTML = `
    <span>Zero Peça</span>
    <div class="zeroPeca-input">
        <label for="zeroPeca-x">Eixo X</label>
        <input id="zeroPeca-x" class="eixo" value="${data.zeroPeca.x}"/>
    </div>
    <div class="zeroPeca-input">
        <label for="zeroPeca-x">Eixo Y</label>
        <input id="zeroPeca-x" class="eixo" value="${data.zeroPeca.y}"/>
    </div>
    <div class="zeroPeca-input">
        <label for="zeroPeca-x">Eixo Z</label>
        <input id="zeroPeca-x" class="eixo" value="${data.zeroPeca.z}"/>
    </div>
    `;

    section.appendChild(div);
}

