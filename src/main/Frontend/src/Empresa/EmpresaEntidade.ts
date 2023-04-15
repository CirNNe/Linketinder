export class EmpresaEntidade {

    id: number
    nome: String
    email: String
    cnpj: String
    cep: String
    estado: String
    pais: String
    descricao: String

    constructor(id: number, nome: String, email: String, cnpj: String, cep: String, estado: String, pais: String, descricao: String) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cnpj = cnpj;
        this.cep = cep;
        this.estado = estado;
        this.pais = pais;
        this.descricao = descricao;
    }
}