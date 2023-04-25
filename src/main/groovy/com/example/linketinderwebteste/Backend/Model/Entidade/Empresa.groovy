package com.example.linketinderwebteste.Backend.Model.Entidade

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.EmpresaInterface

class Empresa implements EmpresaInterface {
    String nome
    String email
    String descricao
    String senha
    String pais
    long cnpj
    int id
    int cep

    @Override
    String toString() {
        return "ID: " + id + ' - ' +
                "Nome: " + nome + ' - ' +
                "E-mail: " + email + ' - ' +
                "CNPJ: " + cnpj + ' - ' +
                "CEP: " + cep + ' - ' +
                "País: " + pais + ' - ' +
                "Descrição: " + descricao
    }
}
