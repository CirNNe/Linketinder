import {mostrarListaCandidatos} from "./CandidatoDAO";

const telaOpcoesEmpresa = document.querySelector("#conteudo-empresa") as HTMLDivElement
const telaListaCandidatos = document.querySelector("#conteudo-lista-candidatos") as HTMLDivElement

const botaoOpcaoListaCandidatos = document.querySelector("#botao-escolha-lista-candidatos") as HTMLButtonElement
if(botaoOpcaoListaCandidatos) {
    botaoOpcaoListaCandidatos.addEventListener('click', function () {
        mostrarListaCandidatos()
        telaOpcoesEmpresa.style.display = 'none'
        telaListaCandidatos.style.display = 'block'
    })
}
