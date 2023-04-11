package Backend.Model.DAO.Interface

import Backend.Model.Entidade.Interface.CompetenciaInterface

interface CompetenciaDAOInterface {

    boolean insereCompetencia(String sql, int id, CompetenciaInterface competencia)

}