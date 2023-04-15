import {VagaEntidade} from "./VagaEntidade";
import {gerarId} from "../Util/Util";
import {validaDadosVaga} from "../Service/ValidatorService";
import {salvarVagaLocalStorage} from "./VagaDAO";

const telaCadastroVaga = document.querySelector("#conteudo-cadastro-vaga")! as HTMLDivElement
const telaOpcoesEmpresa = document.querySelector("#conteudo-empresa") as HTMLDivElement

const inputNomeNovaVaga = document.querySelector("#nome-nova-vaga") as HTMLInputElement
const inputEmpresaNovaVaga = document.querySelector("#empresa-nova-vaga") as HTMLInputElement
const inputDescricaoNovaVaga = document.querySelector("#descricao-nova-vaga") as HTMLInputElement
const inputCompetenciasNovaVaga = document.getElementsByName("competencias-nova-vaga") as unknown as Array<any>

const botaoOpcaoCadastrarVaga = document.querySelector("#botao-escolha-cadastrar-vaga") as HTMLButtonElement
if(botaoOpcaoCadastrarVaga) {
    botaoOpcaoCadastrarVaga.addEventListener('click', function () {
        telaOpcoesEmpresa.style.display = "none"
        telaCadastroVaga.style.display = "block"
    })
}

const botaoCadastrarNovaVaga = document.querySelector("#botao-cadastrar-nova-vaga")! as HTMLButtonElement
if(botaoCadastrarNovaVaga) {
    botaoCadastrarNovaVaga.addEventListener('click', function () {
        const competenciasSelecionadasNovaVaga = [] as Array<string>
        inputCompetenciasNovaVaga.forEach((competencias) => {
            if(competencias.checked) {
                competenciasSelecionadasNovaVaga.push(competencias.value)
            }
        })

        const vaga = new VagaEntidade(
            gerarId(),
            inputNomeNovaVaga.value,
            inputEmpresaNovaVaga.value,
            inputDescricaoNovaVaga.value,
            competenciasSelecionadasNovaVaga.toString()
        )

        if(validaDadosVaga(vaga)) {
            salvarVagaLocalStorage(vaga)
            inputNomeNovaVaga.value = ""
            inputEmpresaNovaVaga.value = ""
            inputDescricaoNovaVaga.value = ""
            inputCompetenciasNovaVaga.forEach((competencia) => {
                competencia.value = ""
            })
            telaCadastroVaga.style.display = "none"
            telaOpcoesEmpresa.style.display = "block"
            window.location.reload()
        }
    })
}