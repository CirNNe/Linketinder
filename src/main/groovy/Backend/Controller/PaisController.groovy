package Backend.Controller

import Backend.Controller.Inteface.PaisControllerInterface
import Backend.Service.Interface.PaisServiceInterface

class PaisController implements PaisControllerInterface {

    private PaisServiceInterface paisService

    PaisController(PaisServiceInterface paisService) {
        this.paisService = paisService
    }

    List listaPaises() {
        return paisService.recebeListaPaises()
    }
}
