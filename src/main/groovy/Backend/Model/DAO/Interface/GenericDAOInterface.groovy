package Backend.Model.DAO.Interface

import Backend.Model.Entidade.Interface.CompetenciaInterface

interface GenericDAOInterface {

    boolean insereCompetenciasGeneric(String sql, int id, CompetenciaInterface competencia)

    Integer buscaIdUsuarioGeneric(String sql, long identificacao)

}