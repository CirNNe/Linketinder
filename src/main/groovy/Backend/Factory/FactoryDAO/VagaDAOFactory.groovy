package Backend.Factory.FactoryDAO

import Backend.Model.DAO.GenericDAO
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.DAO.VagaDAO

class VagaDAOFactory {

    private EmpresaDAOFactory empresaDAOFactory = new EmpresaDAOFactory()
    private GenericDAOInterface genericDAO = new GenericDAO()

    VagaDAOInterface criaVagaDAO() {
        VagaDAOInterface vagaDAO = new VagaDAO(genericDAO, empresaDAOFactory.criaEmpresaDAO())
    }

}
