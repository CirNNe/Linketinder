package Backend.Service.Interface

import Backend.Model.Entidade.Interface.CompetenciaInterface

interface CompetenciaServiceInterface {

    boolean recebeNovaCompetencia(Integer id, long identificacao, List<CompetenciaInterface> listaCompetencias)

}