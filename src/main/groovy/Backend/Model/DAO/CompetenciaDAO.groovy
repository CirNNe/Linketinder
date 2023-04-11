package Backend.Model.DAO

import Backend.Model.DAO.Interface.CandidatoDAOInterface
import Backend.Model.DAO.Interface.CompetenciaDAOInterface
import Backend.Model.DAO.Interface.ConexaoBancoDadosInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.Entidade.Interface.CompetenciaInterface
import Backend.Service.Interface.ValidatorServiceInterface

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

class CompetenciaDAO implements CompetenciaDAOInterface {

    private ConexaoBancoDadosInterface conexaoBancoDados
    private CandidatoDAOInterface candidatoDAO
    private GenericDAOInterface genericDAO
    private ValidatorServiceInterface validatorService

    CompetenciaDAO(ConexaoBancoDadosInterface conexaoBancoDados, CandidatoDAOInterface candidatoDAO,
                   GenericDAOInterface genericDAO, ValidatorServiceInterface validatorService) {
        this.conexaoBancoDados = conexaoBancoDados
        this.candidatoDAO = candidatoDAO
        this.genericDAO = genericDAO
        this.validatorService = validatorService
    }

    boolean insereCompetencia(String sql, int id, CompetenciaInterface competencia) {
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement inserirDado = conexao.prepareStatement(sql)) {

            inserirDado.setString(1, competencia.nome)
            inserirDado.setInt(2, id)
            inserirDado.executeUpdate()
            inserirDado.close()
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir dados na tabela competências: " + e)
        }
    }
}
