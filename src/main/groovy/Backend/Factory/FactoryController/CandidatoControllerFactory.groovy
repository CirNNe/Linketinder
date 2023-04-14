package Backend.Factory.FactoryController

import Backend.Controller.CandidatoController
import Backend.Controller.Inteface.CandidatoControllerInterface
import Backend.Factory.FactoryService.CandidatoServiceFactory

class CandidatoControllerFactory {

    private CandidatoServiceFactory candidatoServiceFactory = new CandidatoServiceFactory()

    CandidatoControllerInterface criaCandidatoController() {
        CandidatoControllerInterface candidatoController = new CandidatoController(candidatoServiceFactory.criaCandidatoService())
        return candidatoController
    }

}
