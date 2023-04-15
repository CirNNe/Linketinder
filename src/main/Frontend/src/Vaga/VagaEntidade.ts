export class VagaEntidade {
    id: number
    nome: String
    empresa: String
    descricao: String
    competencias: String

    constructor(id: number, nome: String, empresa: String, descricao: String, competencias: String) {
        this.id = id;
        this.nome = nome;
        this.empresa = empresa;
        this.descricao = descricao;
        this.competencias = competencias;
    }
}