export default class CandidatoEntidade {

    id: number
    nome: String
    email: String
    dataNascimento: String
    cpf: String
    cep: String
    descricao: String
    linkedin: String
    competencias: String

    constructor(id: number, nome: String, email: String, dataNascimento: String, cpf: String, cep: String, descricao: String, linkedin: String, competencias: String) {
        this.id = id
        this.nome = nome
        this.email = email
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.cep = cep;
        this.descricao = descricao;
        this.linkedin = linkedin;
        this.competencias = competencias;
    }
}
