import CandidatoEntidade from "./CandidatoEntidade";
import {insereCandidatoLocalStorage} from "./CandidatoDAO";
import {validaDadosCandidato} from "../Service/ValidatorService";
import {gerarId} from "../Util/Util";

const telaOpcoesCandidato = document.querySelector("#conteudo-candidato") as HTMLDivElement
const telaCadastroCandidato = document.querySelector("#conteudo-cadastro-candidato") as HTMLDivElement

const botaoOpcaoCadastrarCandidato = document.querySelector("#botao-escolha-cadatrar-candidato") as HTMLButtonElement
if (botaoOpcaoCadastrarCandidato) {
    botaoOpcaoCadastrarCandidato.addEventListener('click', function () {
        telaOpcoesCandidato.style.display = "none"
        telaCadastroCandidato.style.display = "block"
    })
}

const inputNomeNovoCandidato = document.querySelector("#nome-novo-candidato") as HTMLInputElement
const inputEmailNovoCandidato = document.querySelector("#email-novo-candidato") as HTMLInputElement
const inputIdadeNovoCandidato = document.querySelector("#idade-novo-candidato") as HTMLInputElement
const inputCpfNovoCandidato = document.querySelector("#cpf-novo-candidato") as HTMLInputElement
const inputCepNovoCandidato = document.querySelector("#cep-novo-candidato") as HTMLInputElement
const inputDescricaoPessoalNovoCandidato = document.querySelector("#descricao-pessoal-novo-candidato") as HTMLInputElement
const inputLikedinNovoCandidato = document.querySelector("#linkedin-novo-candidato") as HTMLInputElement
const inputCompetencias = document.getElementsByName("competencias-novo-candidato") as unknown as Array<any>

const botaoCadastrarNovoCandidato = document.querySelector("#botao-cadastrar-novo-candidato") as HTMLButtonElement
if (botaoCadastrarNovoCandidato) {
    botaoCadastrarNovoCandidato.addEventListener('click', function () {
        cadastraCandidato()
    })
}
function cadastraCandidato() {
    const competenciasSelecionadas = [] as Array<string>
    inputCompetencias.forEach((competencias) => {
        if(competencias.checked) {
            competenciasSelecionadas.push(competencias.value)
        }
    })

    const candidato = new CandidatoEntidade(
        gerarId(),
        inputNomeNovoCandidato.value,
        inputEmailNovoCandidato.value,
        inputIdadeNovoCandidato.value,
        inputCpfNovoCandidato.value,
        inputCepNovoCandidato.value,
        inputDescricaoPessoalNovoCandidato.value,
        inputLikedinNovoCandidato.value,
        competenciasSelecionadas.toString()
    )

    if(validaDadosCandidato(candidato)) {
        insereCandidatoLocalStorage(candidato)
        inputNomeNovoCandidato.value = ""
        inputEmailNovoCandidato.value = ""
        inputIdadeNovoCandidato.value = ""
        inputCpfNovoCandidato.value = ""
        inputCepNovoCandidato.value = ""
        inputDescricaoPessoalNovoCandidato.value = ""
        inputLikedinNovoCandidato.value = ""
        inputCompetencias.forEach((competencia) => {
            competencia.value = ""
        })
        telaCadastroCandidato.style.display = "none"
        telaOpcoesCandidato.style.display = "block"
        window.location.reload()
    }
}