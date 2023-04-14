package Backend.Factory.FactoryService

import Backend.Model.Entidade.Interface.PaisInterface
import Backend.Model.Entidade.Pais
import Backend.Service.Interface.PaisServiceInterface
import Backend.Service.PaisService

class PaisServiceFactory {

    private ValidatorServiceFactory validatorServiceFactory = new ValidatorServiceFactory()
    private PaisInterface pais = new Pais()

    PaisServiceInterface criaPaisService() {
        PaisServiceInterface paisService = new PaisService(validatorServiceFactory.criaValidatorService(), pais)
        return paisService
    }
}
