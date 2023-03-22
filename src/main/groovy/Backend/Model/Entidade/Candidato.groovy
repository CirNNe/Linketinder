package Backend.Model.Entidade

class Candidato {
    String nome, email, descricaoPessoal, senha, dataNascimento, pais
    long cpf
    int id, cep
    List competencias


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
