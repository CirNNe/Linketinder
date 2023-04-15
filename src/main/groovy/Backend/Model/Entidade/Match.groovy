package Backend.Model.Entidade

import Backend.Model.Entidade.Interface.MatchInterface

class Match implements MatchInterface {

    String nomeCandidato
    String nomeEmpresa

    @Override
    String toString() {
        return "Nome CandidatoEntidade: " + nomeCandidato + " - " +
                "Nome Empresa: " + nomeEmpresa
    }
}
