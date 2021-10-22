function delay(delayInms) {
    return new Promise(resolve => {
        setTimeout(() => {
            resolve(2);
        }, delayInms);
    });
}

var myHeaders = new Headers();

myHeaders.append("Content-Type", "application/json");

async function executar() {
    for (i = 1; i <= 50; i++) {

        var myDate = new Date, myFormat = [
            myDate.getMonth() + 1,
            myDate.getDate(),
            myDate.getFullYear()
        ].join('/') + ' ' + [
            myDate.getHours(),
            myDate.getMinutes(),
            myDate.getSeconds()
        ].join(':');

        myDate = myDate.toISOString();

        console.log(myDate);

        let myBody = {
            dt_leitura: myDate,
            coordenadaId: 5,
            termopar_1: Math.random() * (115 - 100) + 100,
            termopar_2: Math.random() * (115 - 100) + 100,
            termopar_3: Math.random() * (115 - 100) + 100,
            termopar_amb: Math.random() * (25 - 20) + 20,
            x : 30,
            y : 15,
            z: 18,
            r : 9,
            
        }

        fetch("http://localhost:8080/temperatura",
            {
                method: "post",
                body: JSON.stringify(myBody),
                headers: myHeaders
            }).then(console.log('Requisição ' + i)).catch(error => console.log(error))
        let espere = await delay(100);
    }

    // Era so para teste.
    // fetch("http://localhost:8080/temperatura/3",
    //         {
    //             method: "get",
    //             headers: myHeaders
    //         }).then(data=> data.json())
    //         .then(data=> (console.log('Resposta ' + data)).catch(error => console.log(error)));
}

