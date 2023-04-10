package Backend.Model.DAO.Interface

import Backend.Model.Entidade.Interface.CompetenciaInterface

interface CompetenciaDAOInterface {

    boolean insereCompetencia(Integer id, long identificacao, CompetenciaInterface competencia)

}