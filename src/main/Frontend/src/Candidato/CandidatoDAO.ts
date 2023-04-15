import {criaTagDivCandidato} from "./CandidatoService";

const telaListaCandidatos = document.querySelector("#conteudo-lista-candidatos") as HTMLDivElement
export function insereCandidatoLocalStorage(candidato: object) {
    let listaCandidatos = localStorage.getItem('candidatos') as string
    if(listaCandidatos) {
        let listaCandidatosJson = []
        listaCandidatosJson = JSON.parse(listaCandidatos)
        listaCandidatosJson.push(candidato)
        localStorage.setItem('candidatos', JSON.stringify(listaCandidatosJson))
    }
    else {
        localStorage.setItem('candidatos', '[]')
        let listaCandidatosJson = []
        listaCandidatosJson = JSON.parse(listaCandidatos)
        listaCandidatosJson.push(candidato)
        localStorage.setItem('candidatos', JSON.stringify(listaCandidatosJson))
    }
}

export function mostrarListaCandidatos() {
    const listaCandidatosLocalStorage = localStorage.getItem('candidatos') as string
    const listaCandidatos = JSON.parse(listaCandidatosLocalStorage)
    if(listaCandidatosLocalStorage) {
        for (let posicao = 0; posicao < listaCandidatos.length; posicao++) {
            listaCandidatos[posicao].nome = "Anônimo"
            let div = criaTagDivCandidato(listaCandidatos[posicao])
            telaListaCandidatos.appendChild(div)
        }
    }
}
