import {mostrarListaVagas} from "./VagaDAO";

const telaOpcoesCandidato = document.querySelector("#conteudo-candidato") as HTMLDivElement
const telaVagas = document.querySelector("#conteudo-vagas") as HTMLDivElement

const botaoOpcaoVagasDisponiveis = document.querySelector("#botao-escolha-vagas") as HTMLButtonElement
if (botaoOpcaoVagasDisponiveis) {
    botaoOpcaoVagasDisponiveis.addEventListener('click', function () {
        mostrarListaVagas()
        telaOpcoesCandidato.style.display = "none"
        telaVagas.style.display = "block"
    })
}
