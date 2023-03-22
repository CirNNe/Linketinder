package Backend.Model.Entidade

class Vaga {

    String nome, empresa, descricao, pais
    int id
    List competencias

    @Override
    String toString() {
        return "ID: " + id + " - " +
                "Nome: " + nome + " - " +
                "Empresa: " + empresa + " - " +
                "Descrição: " + descricao + " - " +
                "País: " + pais + " - " +
                "Competências: " + competencias
    }
}
