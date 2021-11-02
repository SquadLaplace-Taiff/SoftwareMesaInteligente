export type Teste = {
    modelo: string, 
    nome_teste: string,
    coordenadas?: Array<coordenada>,
}

export type coordenada = {
    coordenada_x: number, 
    coordenada_y: number,
    coordenada_z: number,
    eixo_r: number,
    trempo?: number,
    zero_peca?: boolean
}