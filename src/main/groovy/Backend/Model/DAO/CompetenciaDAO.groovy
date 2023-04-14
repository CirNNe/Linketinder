package Backend.Model.DAO

import Backend.Model.DAO.Interface.CompetenciaDAOInterface
import Backend.Model.Entidade.Interface.CompetenciaInterface
import groovy.sql.Sql
import java.sql.SQLException

class CompetenciaDAO implements CompetenciaDAOInterface {

    Sql sql = Sql.newInstance(ConexaoBancoDados.conectar())

    boolean insereCompetencia(String comandoSql, int id, CompetenciaInterface competencia) {
        try {
            sql.execute(comandoSql, [competencia.nome, id])
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir dados na tabela compet�ncias: " + e)
        }
    }
}
