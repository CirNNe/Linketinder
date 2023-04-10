package Backend.Service.Interface

import Backend.Model.Entidade.Interface.CompetenciaInterface

interface CompetenciaServiceInterface {

    boolean salvaNovaCompetencia(Integer id, long identificacao, List<CompetenciaInterface> listaCompetencias)

}