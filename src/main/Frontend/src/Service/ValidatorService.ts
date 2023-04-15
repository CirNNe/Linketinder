import {RegexUsuario} from "../Util/RegexUsuario";
export function validaDadosCandidato(candidato: any) {
    if (!candidato.nome.match(new RegexUsuario().nomePessoaFisicaRegex)) {
        alert("Nome inv�lido!")
    } else if (!candidato.email.match(new RegexUsuario().emailRegex)) {
        alert("E-mail inv�lido!")
    } else if (!candidato.cpf.match(new RegexUsuario().cpfRegex)) {
        alert("CPF inv�lido")
    } else if (!candidato.cep.match(new RegexUsuario().cepRegex)) {
        alert("CEP inv�lido!")
    } else if (!candidato.linkedin.match(new RegexUsuario().linkedinRegex)) {
        alert("Linkedin inv�lido!")
    } else if(!candidato.descricao.match(new RegexUsuario().descricaoRegex)) {
        alert("Descri��o pessoal inv�lida!")
    } else {
        return candidato
    }
}

export function validaDadosEmpresa(empresa: any) {
    if (!empresa.nome.match(new RegexUsuario().nomePessoaJuridicaRegex)) {
        alert("Nome inv�lido!")
    } else if (!empresa.email.match(new RegexUsuario().emailRegex)) {
        alert("E-mail inv�lido!")
    } else if (!empresa.cnpj.match(new RegexUsuario().cnpjRegex)) {
        alert("CNPJ inv�lido")
    } else if (!empresa.cep.match(new RegexUsuario().cepRegex)) {
        alert("CEP inv�lido!")
    } else if (!empresa.estado.match(new RegexUsuario().estadoRegex)) {
        alert("Estado inv�lido!")
    } else if (!empresa.pais.match(new RegexUsuario().paisRegex)) {
        alert("Pa�s inv�lido!")
    } else if(!empresa.descricao.match(new RegexUsuario().descricaoRegex)) {
        alert("Descri��o inv�lida!")
    } else {
        return empresa
    }
}

export function validaDadosVaga(vaga: any) {
    if (!vaga.nome.match(new RegexUsuario().nomePessoaJuridicaRegex)) {
        alert("Nome inv�lido!")
    } else if (!vaga.empresa.match(new RegexUsuario().nomePessoaJuridicaRegex)) {
        alert("Empresa inv�lida!")
    } else if(!vaga.descricao.match(new RegexUsuario().descricaoRegex)) {
        alert("Descri��o inv�lida!")
    } else {
        return vaga
    }
}
