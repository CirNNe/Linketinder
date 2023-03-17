package Backend.Model.Entidade

class Empresa {
    String nome, emailCorporativo, pais, descricao, senha
    long cnpj
    int id, cep

    @Override
    String toString() {
        return id + " - " +
                nome + ' - ' +
                emailCorporativo + ' - ' +
                cnpj + ' - ' +
                cep + ' - ' +
                pais + ' - ' +
                descricao
    }
}
