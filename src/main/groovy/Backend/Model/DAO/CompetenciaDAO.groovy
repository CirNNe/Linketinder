package Backend.Model.DAO

import Backend.Model.DAO.Interface.CandidatoDAOInterface
import Backend.Model.DAO.Interface.CompetenciaDAOInterface
import Backend.Model.DAO.Interface.ConexaoBancoDadosInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.Entidade.Interface.CompetenciaInterface
import Backend.Service.Interface.ValidatorServiceInterface

import java.sql.SQLException

class CompetenciaDAO implements CompetenciaDAOInterface {

    ConexaoBancoDadosInterface conexaoBancoDados
    CandidatoDAOInterface candidatoDAO
    GenericDAOInterface genericDAO
    ValidatorServiceInterface validatorService

    CompetenciaDAO(ConexaoBancoDadosInterface conexaoBancoDados, CandidatoDAOInterface candidatoDAO,
                   GenericDAOInterface genericDAO, ValidatorServiceInterface validatorService) {
        this.conexaoBancoDados = conexaoBancoDados
        this.candidatoDAO = candidatoDAO
        this.genericDAO = genericDAO
        this.validatorService = validatorService
    }

    boolean insereCompetencia(Integer id, long identificacao, CompetenciaInterface competencia) {
        try {
            if(validatorService.validaTipoUsuario(identificacao) == 1) {
                String sql = "INSERT INTO competencias(nome, id_candidato) VALUES(?, ?)"
                genericDAO.insereCompetenciasGeneric(sql, id, competencia)
                return true
            } else if(validatorService.validaTipoUsuario(identificacao) == 2) {
                String sql = "INSERT INTO competencias(nome, id_vagas) VALUES(?, ?)"
                genericDAO.insereCompetenciasGeneric(sql, id, competencia)
                return true
            }
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir competencia no banco de dados " + e)
        }
    }
}
