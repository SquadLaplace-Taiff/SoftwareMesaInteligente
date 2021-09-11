export type dataCoordenadas = {
    x: number, 
    y: any
}

export type DataSetsConfig = {
    label: string,
    backgroundColor?: string,
    borderColor?: string,
    borderDash?: Array<number>,
    cubicInterpolationMode?: string,
    fill?: Boolean,
    data: Array<dataCoordenadas>
}

export type dataConfig = Array<DataSetsConfig>;