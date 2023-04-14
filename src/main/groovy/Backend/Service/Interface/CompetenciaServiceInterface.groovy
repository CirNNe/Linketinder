package Backend.Service.Interface

import Backend.Model.Entidade.Interface.CompetenciaInterface

interface CompetenciaServiceInterface {

    boolean recebeDadosNovaCompetencia(Integer id, long identificacao, List<CompetenciaInterface> listaCompetencias)

    boolean filtraCompetenciaDaLista(Integer id, long identificacao, List<CompetenciaInterface> listaCompetencias)

    boolean salvaNovaCompetencia(Integer id, long identificacao, CompetenciaInterface competencia)

}