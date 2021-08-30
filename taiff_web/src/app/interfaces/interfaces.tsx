export type teste = {
    modelo: string, 
    nome_teste: string,
    coordenadas?: Array<coordenada>,
    zeroPeca?: coordenada
}

export type coordenada = {
    x: number, 
    y: number,
    z: number,
    r: number,
    t?: number,
}