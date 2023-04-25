package com.example.linketinderwebteste.Backend.Service

import com.example.linketinderwebteste.Backend.Factory.FactoryDAO.CandidatoDAOFactory
import com.example.linketinderwebteste.Backend.Factory.FactoryService.ValidatorServiceFactory
import com.example.linketinderwebteste.Backend.Model.DAO.CompetenciaDAO
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.CandidatoDAOInterface
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.CompetenciaDAOInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Competencia
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CompetenciaInterface
import com.example.linketinderwebteste.Backend.Service.Interface.CompetenciaServiceInterface
import com.example.linketinderwebteste.Backend.Service.Interface.ValidatorServiceInterface

import java.sql.SQLException

class CompetenciaService implements CompetenciaServiceInterface {

    private CompetenciaDAOInterface competenciaDAO = new CompetenciaDAO()
    private CandidatoDAOInterface candidatoDAO = new CandidatoDAOFactory().criaCandidatoDAO()
    private ValidatorServiceInterface validatorService = new ValidatorServiceFactory().criaValidatorService()

    boolean recebeDadosNovaCompetencia(long identificacao, List listaCompetencias) {
        try {
            if(validatorService.validaDadosNovaCompetencia(listaCompetencias)) {
                Integer idCandidato = candidatoDAO.buscaIdCandidato(identificacao)
                filtraCompetenciaDaLista(idCandidato, identificacao, listaCompetencias)
            }
            return true
        } catch (Exception e) {
            throw new Exception("Erro ao tentar receber os dados da nova competencia: " + e)
        }
    }

    boolean filtraCompetenciaDaLista(Integer id, long identificacao, List listaCompetencias) {
        try {
            CompetenciaInterface competencia = new Competencia()
            for(int posicao = 0; posicao < listaCompetencias.size(); posicao++) {
                competencia.nome = listaCompetencias[posicao]
                salvaNovaCompetencia(id, identificacao, competencia)
            }
            return true
        } catch (Exception e) {
            throw new Exception("Erro ao tentar filtrar as competências da lista recebida: " + e)
        }
    }

    boolean salvaNovaCompetencia(Integer id, long identificacao, CompetenciaInterface competencia) {
        try {
            String sql = ""
            if(validatorService.validaTipoUsuario(identificacao) == 1) {
                sql = "INSERT INTO competencias(nome, id_candidato) VALUES(?, ?)"
            } else if(validatorService.validaTipoUsuario(identificacao) == 2) {
                sql = "INSERT INTO competencias(nome, id_vaga) VALUES(?, ?)"
            }
            competenciaDAO.insereCompetencia(sql, id, competencia)
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir competencia no banco de dados " + e)
        }
    }
}
