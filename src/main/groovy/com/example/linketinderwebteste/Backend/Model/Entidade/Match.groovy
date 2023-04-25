package com.example.linketinderwebteste.Backend.Model.Entidade

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.MatchInterface

class Match implements MatchInterface {

    String nomeCandidato
    String nomeEmpresa

    @Override
    String toString() {
        return "Nome CandidatoEntidade: " + nomeCandidato + " - " +
                "Nome Empresa: " + nomeEmpresa
    }
}
