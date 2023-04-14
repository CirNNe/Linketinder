package Backend.Factory.FactoryController

import Backend.Controller.CompetenciaController
import Backend.Controller.Inteface.CompetenciaControllerInterface
import Backend.Factory.FactoryService.CompetenciaServiceFactory

class CompetenciaControllerFactory {

    private CompetenciaServiceFactory competenciaServiceFactory = new CompetenciaServiceFactory()

    CompetenciaControllerInterface criaCompetenciaController() {

        CompetenciaControllerInterface competenciaController = new CompetenciaController(competenciaServiceFactory.criaCompetenciaService())
        return competenciaController
    }
}
