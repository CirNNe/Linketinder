package Backend.Service

import Backend.Model.Entidade.Interface.PaisInterface
import Backend.Service.Interface.PaisServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface

class PaisService implements PaisServiceInterface {

    private ValidatorServiceInterface validatorService
    private PaisInterface pais

    PaisService(ValidatorServiceInterface validatorService, PaisInterface pais) {
        this.validatorService = validatorService
        this.pais = pais
    }

    List recebeListaPaises() {
        try {
            List lista = pais.getPaises()
            return lista
        } catch (Exception e) {
            throw new Exception("Erro ao tentar receber a lista de países: " + e)
        }
    }
}
