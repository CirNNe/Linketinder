package Backend.Factory.FactoryService

import Backend.Factory.FactoryDAO.VagaDAOFactory
import Backend.Service.Interface.VagaServiceInterface
import Backend.Service.VagaService
import Backend.Util.Regex.RegexValidaDadosNovaVaga
import Backend.Util.Regex.RegexValidaDadosNovoUsuario

class VagaServiceFactory {

    private VagaDAOFactory vagaDAOFactory = new VagaDAOFactory()
    private ValidatorServiceFactory validatorServiceFactory = new ValidatorServiceFactory()
    private RegexValidaDadosNovaVaga regexVaga = new RegexValidaDadosNovaVaga()
    private RegexValidaDadosNovoUsuario regexUsuario = new RegexValidaDadosNovoUsuario()

    VagaServiceInterface criaVagaService() {
        VagaServiceInterface vagaService = new VagaService(vagaDAOFactory.criaVagaDAO(),
                                                                validatorServiceFactory.criaValidatorService(),
                                                                regexVaga, regexUsuario)
        return vagaService
    }

}
