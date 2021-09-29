export type dataType = {
    labels: Array<String>,
    datasets: Array<datasets>
}

export type datasets = {
    label?: String | null,
    hidden?: boolean,
    data: Array<coordenadas> | Array<janelas>,
    fill: Boolean,
    backgroundColor: String,
    borderColor: String
}

export type coordenadas = {
    x: String,
    y: Number,
}

export type temperaturas = {
    termopar_1: Number,
    termopar_2: Number,
    termopar_3: Number,
    termopar_amb: Number,
    linha: Number
}

export type janelas = {
    valorInicial: Number,
    valorFinal: Number,
}