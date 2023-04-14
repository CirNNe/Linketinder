package Backend.Controller

import Backend.Controller.Inteface.CompetenciaControllerInterface
import Backend.Model.Entidade.Interface.CompetenciaInterface
import Backend.Service.Interface.CompetenciaServiceInterface

class CompetenciaController implements CompetenciaControllerInterface {

    private CompetenciaServiceInterface competenciaService

    CompetenciaController(CompetenciaServiceInterface competenciaService) {
        this.competenciaService = competenciaService
    }

    boolean recebeDadosNovaCompetencia(Integer id, long identificacao, List<CompetenciaInterface> listaCompetencias) {
        competenciaService.recebeDadosNovaCompetencia(id, identificacao, listaCompetencias)
    }
}
