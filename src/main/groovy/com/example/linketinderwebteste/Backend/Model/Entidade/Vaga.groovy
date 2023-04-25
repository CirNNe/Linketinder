package com.example.linketinderwebteste.Backend.Model.Entidade

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.VagaInterface

class Vaga implements VagaInterface {

    int id
    String nome
    String empresa
    long cnpj
    String pais
    String descricao
    List<Competencia> competencias

    @Override
    String toString() {
        return "ID: " + id + " - " +
                "Nome: " + nome + " - " +
                "Empresa: " + empresa + " - " +
                "CNPJ: " + cnpj + " - " +
                "País: " + pais + " - " +
                "Descrição: " + descricao + " - " +
                "Competências: " + competencias.toString().replaceAll(/[\[\]{}]/, '')
    }
}
