package Backend.Model.Entidade

/**
 * Classe responsável pela definição do objeto do tipo Candidato
 */
class Candidato {
    String nome, email, descricaoPessoal
    long cpf
    int idade, cep
    List competencias


    @Override
    String toString() {
        return nome + ';' +
                email + ';' +
                idade + ';' +
                cpf + ';' +
                cep + ';' +
                descricaoPessoal + ';' +
                competencias
    }
}
