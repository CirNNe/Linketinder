package Backend.Factory.FactoryController

import Backend.Controller.Inteface.VagaControllerInterface
import Backend.Controller.VagaController
import Backend.Factory.FactoryService.VagaServiceFactory
import Backend.Service.ValidatorService

class VagaControllerFactory {

    private VagaServiceFactory vagaServiceFactory = new VagaServiceFactory()

    VagaControllerInterface criaVagaController() {

        VagaControllerInterface vagaController = new VagaController(vagaServiceFactory.criaVagaService())

    }

}
