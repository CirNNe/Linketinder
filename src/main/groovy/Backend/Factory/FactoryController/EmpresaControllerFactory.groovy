package Backend.Factory.FactoryController

import Backend.Controller.EmpresaController
import Backend.Controller.Inteface.EmpresaControllerInterface
import Backend.Factory.FactoryService.EmpresaServiceFactory

class EmpresaControllerFactory {

    private EmpresaServiceFactory empresaServiceFactory = new EmpresaServiceFactory()

    EmpresaControllerInterface criaEmpresaController() {
        EmpresaControllerInterface empresaController = new EmpresaController(empresaServiceFactory.criaEmpresaService())
        return empresaController
    }
}
