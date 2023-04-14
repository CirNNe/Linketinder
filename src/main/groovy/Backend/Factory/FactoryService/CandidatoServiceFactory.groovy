package Backend.Factory.FactoryService

import Backend.Factory.FactoryDAO.CandidatoDAOFactory
import Backend.Model.DAO.CandidatoDAO
import Backend.Model.DAO.EmpresaDAO
import Backend.Model.DAO.GenericDAO
import Backend.Model.DAO.Interface.CandidatoDAOInterface
import Backend.Model.DAO.Interface.EmpresaDAOInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.DAO.VagaDAO
import Backend.Service.CandidatoService
import Backend.Service.Interface.CandidatoServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface
import Backend.Service.ValidatorService
import Backend.Util.Regex.RegexValidaDadosNovaVaga
import Backend.Util.Regex.RegexValidaDadosNovoUsuario

class CandidatoServiceFactory {

    private ValidatorServiceFactory validatorServiceFactory = new ValidatorServiceFactory()

    private CandidatoDAOFactory candidatoDAOFactory = new CandidatoDAOFactory()

    CandidatoServiceInterface criaCandidatoService() {
        CandidatoServiceInterface candidatoService = new CandidatoService(candidatoDAOFactory.criaCandidatoDAO(),
                                                                            validatorServiceFactory.criaValidatorService())
        return candidatoService
    }
}
