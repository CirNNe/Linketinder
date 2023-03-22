package Backend.Controller

import Backend.Model.DAO.CandidatoDAO
import Backend.Model.Entidade.Candidato
import Backend.Model.Entidade.Competencia
import Backend.Service.CandidatoService

class CandidatoController {

    CandidatoService candidatoService = new CandidatoService(new CandidatoDAO())
    CandidatoDAO candidatoDAO = new CandidatoDAO()

    void recebeDadosCandidatos(nome, email, dataNascimento, cpf, cep, String pais, descricaoPessoal, senha) {

        Candidato candidato = new Candidato()

        candidato.nome = nome
        candidato.email = email
        candidato.dataNascimento = dataNascimento
        candidato.cpf = cpf
        candidato.cep = cep
        candidato.pais = pais
        candidato.descricaoPessoal = descricaoPessoal
        candidato.senha = senha

        candidatoService.validaDadosCadastroCandidato(candidato)
    }

    void recebeDadosCompetenciasCandidato(long cpf, List listaDeCompetencias) {

        Competencia competencia = new Competencia()
        for(int posicao = 0; posicao < listaDeCompetencias.size(); posicao++) {
            competencia.nome = listaDeCompetencias[posicao]
            candidatoDAO.inserirCompetenciaCandidato(cpf, competencia)
        }
    }

    void listaCandidatos() {
        candidatoService.formataLeituraCandidatos()
    }

    void perfilCandidato(cpf) {
        candidatoService.validaEFormataLeituraPerfilCandidato(cpf)
    }

    void curtirVaga(long cpf, int idVaga) {
        candidatoService.validaCurtidaDoCadidato(cpf, idVaga)
    }

    void listaMatchsCandidato(long cpf) {
        candidatoService.formataLeituraListaDeMathcs(cpf)
    }
}
