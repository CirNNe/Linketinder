package Backend.Model.Entidade

import Backend.Model.Entidade.Interface.CandidatoInterface

class Candidato implements CandidatoInterface {
    String nome
    String email
    String dataNascimento
    String descricaoPessoal
    String senha
    String pais
    long cpf
    int id
    int cep
    List<Competencia> competencias

    @Override
    String toString() {
        return 'ID: ' + id + ' - ' +
                'Nome: ' + nome + ' - ' +
                'E-mail: ' + email + ' - ' +
                'Data de Nascimento: ' + dataNascimento + ' - ' +
                'CPF: ' + cpf + ' - ' +
                'CEP: ' + cep + ' - ' +
                'País: ' + pais + ' - ' +
                'Descrição Pessoal: ' + descricaoPessoal + ' - ' +
                'Competências: ' + competencias
    }
}
