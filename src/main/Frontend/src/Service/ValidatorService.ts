import {RegexUsuario} from "../Util/RegexUsuario";
export function validaDadosCandidato(candidato: any) {
    if (!candidato.nome.match(new RegexUsuario().nomePessoaFisicaRegex)) {
        alert("Nome inválido!")
    } else if (!candidato.email.match(new RegexUsuario().emailRegex)) {
        alert("E-mail inválido!")
    } else if (!candidato.cpf.match(new RegexUsuario().cpfRegex)) {
        alert("CPF inválido")
    } else if (!candidato.cep.match(new RegexUsuario().cepRegex)) {
        alert("CEP inválido!")
    } else if (!candidato.linkedin.match(new RegexUsuario().linkedinRegex)) {
        alert("Linkedin inválido!")
    } else if(!candidato.descricao.match(new RegexUsuario().descricaoRegex)) {
        alert("Descrição pessoal inválida!")
    } else {
        return candidato
    }
}

export function validaDadosEmpresa(empresa: any) {
    if (!empresa.nome.match(new RegexUsuario().nomePessoaJuridicaRegex)) {
        alert("Nome inválido!")
    } else if (!empresa.email.match(new RegexUsuario().emailRegex)) {
        alert("E-mail inválido!")
    } else if (!empresa.cnpj.match(new RegexUsuario().cnpjRegex)) {
        alert("CNPJ inválido")
    } else if (!empresa.cep.match(new RegexUsuario().cepRegex)) {
        alert("CEP inválido!")
    } else if (!empresa.estado.match(new RegexUsuario().estadoRegex)) {
        alert("Estado inválido!")
    } else if (!empresa.pais.match(new RegexUsuario().paisRegex)) {
        alert("País inválido!")
    } else if(!empresa.descricao.match(new RegexUsuario().descricaoRegex)) {
        alert("Descrição inválida!")
    } else {
        return empresa
    }
}

export function validaDadosVaga(vaga: any) {
    if (!vaga.nome.match(new RegexUsuario().nomePessoaJuridicaRegex)) {
        alert("Nome inválido!")
    } else if (!vaga.empresa.match(new RegexUsuario().nomePessoaJuridicaRegex)) {
        alert("Empresa inválida!")
    } else if(!vaga.descricao.match(new RegexUsuario().descricaoRegex)) {
        alert("Descrição inválida!")
    } else {
        return vaga
    }
}
