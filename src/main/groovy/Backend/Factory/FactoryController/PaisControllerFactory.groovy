package Backend.Factory.FactoryController

import Backend.Controller.Inteface.PaisControllerInterface
import Backend.Controller.PaisController
import Backend.Factory.FactoryService.PaisServiceFactory

class PaisControllerFactory {

    private PaisServiceFactory paisServiceFactory = new PaisServiceFactory()

    PaisControllerInterface criaPaisController() {
        PaisControllerInterface paisController = new PaisController(paisServiceFactory.criaPaisService())
        return paisController
    }
}
