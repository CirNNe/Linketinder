package Backend.Factory.FactoryService

import Backend.Factory.FactoryDAO.CandidatoDAOFactory
import Backend.Factory.FactoryDAO.EmpresaDAOFactory
import Backend.Factory.FactoryDAO.VagaDAOFactory
import Backend.Service.Interface.ValidatorServiceInterface
import Backend.Service.ValidatorService
import Backend.Util.Regex.RegexValidaDadosNovaVaga
import Backend.Util.Regex.RegexValidaDadosNovoUsuario

class ValidatorServiceFactory {

    private RegexValidaDadosNovoUsuario regexUsuario = new RegexValidaDadosNovoUsuario()
    private RegexValidaDadosNovaVaga regexVaga = new RegexValidaDadosNovaVaga()
    private VagaDAOFactory vagaDAOFactory = new VagaDAOFactory()
    private CandidatoDAOFactory candidatoDAOFactory = new CandidatoDAOFactory()
    private EmpresaDAOFactory empresaDAOFactory = new EmpresaDAOFactory()

    ValidatorServiceInterface criaValidatorService() {
        ValidatorServiceInterface validatorService = new ValidatorService(regexUsuario, regexVaga,
                                                                                vagaDAOFactory.criaVagaDAO(),
                                                                                candidatoDAOFactory.criaCandidatoDAO(),
                                                                                empresaDAOFactory.criaEmpresaDAO())
        return validatorService
    }

}
