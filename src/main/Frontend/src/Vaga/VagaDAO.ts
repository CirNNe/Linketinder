import {criarTagDivVaga} from "./VagaService";

const telaVagas = document.querySelector("#conteudo-vagas") as HTMLDivElement
export function salvarVagaLocalStorage(vaga: object) {
    let listaVagas = localStorage.getItem('vagas') as string
    if(listaVagas) {
        let listaVagasJson = []
        listaVagasJson = JSON.parse(listaVagas)
        listaVagasJson.push(vaga)
        localStorage.setItem('vagas', JSON.stringify(listaVagasJson))
    }
    else {
        localStorage.setItem('vagas', '[]')
        let listaVagasJson = []
        listaVagasJson = JSON.parse(listaVagas)
        listaVagasJson.push(vaga)
        localStorage.setItem('vagas', JSON.stringify(listaVagasJson))
    }
}
export function mostrarListaVagas() {
    const listaVagasLocalStorage = localStorage.getItem('vagas') as string
    const listaVagas = JSON.parse(listaVagasLocalStorage)
    if (listaVagasLocalStorage) {
        for(let posicao = 0; posicao < listaVagas.length; posicao++) {
            listaVagas[posicao].empresa = "Anônimo"
            let div = criarTagDivVaga(listaVagas[posicao])
            telaVagas.appendChild(div)
        }
    }
}
