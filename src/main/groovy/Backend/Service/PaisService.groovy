package Backend.Service

import Backend.Model.DAO.Interface.PaisDAOInterface
import Backend.Service.Interface.PaisServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface

class PaisService implements PaisServiceInterface {

    private ValidatorServiceInterface validatorService
    private PaisDAOInterface paisDAO

    PaisService(ValidatorServiceInterface validatorService, PaisDAOInterface paisDAO) {
        this.validatorService = validatorService
        this.paisDAO = paisDAO
    }

    List recebeListaPaises() {
        try {
            List lista = paisDAO.buscaListaPaises()
            return lista
        } catch (Exception e) {
            throw new Exception("Erro ao tentar receber a lista de países: " + e)
        }
    }

    boolean exibeListaPaises() {
        try {
            List lista = recebeListaPaises()
            for(int posicao = 0; posicao < lista.size(); posicao++) {
                println(lista[posicao])
            }
            return true
        } catch (Exception e) {
            throw new Exception("Erro ao tentar exibir a lista de países: " + e)
        }
    }
}
