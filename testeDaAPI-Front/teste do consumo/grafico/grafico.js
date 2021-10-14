const url = 'http://localhost:8080/temperatura'

function buscarTemperaturas() {
    fetch(`${url}/1`)
        .then(res => res.json())
        .then(resposta => {
            console.log("Gráfico: ")
            console.log(resposta)
        })
        .catch(
            error => console.log('Request failed', error)
        )
}

function dadosTabela() { 
    fetch(`${url}/folhaDeRosto/1`)
    .then(res => res.json())
    .then(resposta => {
        console.log("Tabela: ");
        console.log(resposta)
    })
    .catch(
        error => console.log('Request failed', error)
    )
}

buscarTemperaturas()
dadosTabela()