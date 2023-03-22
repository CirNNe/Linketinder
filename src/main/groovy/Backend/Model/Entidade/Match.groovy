package Backend.Model.Entidade

class Match {

    String nomeCandidato, nomeEmpresa

    @Override
    String toString() {
        return "Nome Candidato: " + nomeCandidato + " - " +
                "Nome Empresa: " + nomeEmpresa
    }
}
