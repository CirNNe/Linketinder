package Backend.Model.Entidade

import Backend.Model.Entidade.Interface.VagaInterface

class Vaga implements VagaInterface {

    String nome
    String empresa
    String descricao
    String pais
    int id
    List<Competencia> competencias

    @Override
    String toString() {
        return "ID: " + id + " - " +
                "Nome: " + nome + " - " +
                "Empresa: " + empresa + " - " +
                "Descrição: " + descricao + " - " +
                "País: " + pais + " - " +
                "Competências: " + competencias.toString().replaceAll(/[\[\]{}]/, '')
    }
}
