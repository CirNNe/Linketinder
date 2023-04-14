package Backend.Factory.FactoryService

import Backend.Factory.FactoryDAO.EmpresaDAOFactory
import Backend.Service.EmpresaService
import Backend.Service.Interface.EmpresaServiceInterface

class EmpresaServiceFactory {

    private EmpresaDAOFactory empresaDAOFactory = new EmpresaDAOFactory()
    private ValidatorServiceFactory validatorServiceFactory = new ValidatorServiceFactory()

    EmpresaServiceInterface criaEmpresaService() {
        EmpresaServiceInterface empresaService = new EmpresaService(empresaDAOFactory.criaEmpresaDAO(),
                                                        validatorServiceFactory.criaValidatorService())
        return empresaService
    }

}
