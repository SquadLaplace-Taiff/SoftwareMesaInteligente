export interface Rampas {
    rampaDescendo?: Rampa;
    rampaSubindo?: Rampa;
}

export interface Rampa {
    duracao: number;
    inicio: string;
    fim: string;
    rampaSubindo: boolean;
    termopar: string;
    tempAmbiente: number;
    tempMaxima: number;
    tempMinima: number;
}