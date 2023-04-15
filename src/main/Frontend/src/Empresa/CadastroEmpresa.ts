
import {validaDadosEmpresa} from "../Service/ValidatorService";
import {salvarEmpresaLocalStorage} from "./EmpresaDAO";
import {EmpresaEntidade} from "./EmpresaEntidade";
import {gerarId} from "../Util/Util";

const telaOpcoesEmpresa = document.querySelector("#conteudo-empresa") as HTMLDivElement
const telaCadastroEmpresa = document.querySelector("#conteudo-cadastro-empresa") as HTMLDivElement

const botaoOpcaoCadastrarEmpresa = document.querySelector("#botao-escolha-cadastrar-empresa") as HTMLButtonElement


if(botaoOpcaoCadastrarEmpresa) {
    botaoOpcaoCadastrarEmpresa.addEventListener('click', function () {
        telaOpcoesEmpresa.style.display = "none"
        telaCadastroEmpresa.style.display = "block"
    })
}

const inputNomeNovaEmpresa = document.querySelector("#nome-nova-empresa") as HTMLInputElement
const inputEmailNovaEmpresa = document.querySelector("#email-nova-empresa") as HTMLInputElement
const inputCnpjNovaEmpresa = document.querySelector("#cnpj-nova-empresa") as HTMLInputElement
const inputCepNovaEmpresa = document.querySelector("#cep-nova-empresa") as HTMLInputElement
const inputEstadoNovaEmpresa = document.querySelector("#estado-nova-empresa") as HTMLInputElement
const inputPaisNovaEmpresa = document.querySelector("#pais-nova-empresa") as HTMLInputElement
const inputDescricaoNovaEmpresa = document.querySelector("#descricao-nova-empresa") as HTMLInputElement

const botaoCadastrarNovaEmpresa = document.querySelector("#botao-cadastrar-nova-empresa")! as HTMLButtonElement
if(botaoCadastrarNovaEmpresa) {
    botaoCadastrarNovaEmpresa.addEventListener('click', function () {

        const empresa = new EmpresaEntidade(
            gerarId(),
            inputNomeNovaEmpresa.value,
            inputEmailNovaEmpresa.value,
            inputCnpjNovaEmpresa.value,
            inputCepNovaEmpresa.value,
            inputEstadoNovaEmpresa.value,
            inputPaisNovaEmpresa.value,
            inputDescricaoNovaEmpresa.value
        )

        if(validaDadosEmpresa(empresa)) {
            salvarEmpresaLocalStorage(empresa)
            inputNomeNovaEmpresa.value = ""
            inputEmailNovaEmpresa.value = ""
            inputCnpjNovaEmpresa.value = ""
            inputCepNovaEmpresa.value = ""
            inputEstadoNovaEmpresa.value = ""
            inputPaisNovaEmpresa.value = ""
            inputDescricaoNovaEmpresa.value = ""
            telaCadastroEmpresa.style.display = 'none'
            telaOpcoesEmpresa.style.display = 'block'
            window.location.reload()
        }
    })
}
