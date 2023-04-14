package Backend.Factory.FactoryDAO

import Backend.Model.DAO.EmpresaDAO
import Backend.Model.DAO.GenericDAO
import Backend.Model.DAO.Interface.EmpresaDAOInterface
import Backend.Model.DAO.Interface.GenericDAOInterface

class EmpresaDAOFactory {

    private GenericDAOInterface genericDAO = new GenericDAO()

    EmpresaDAOInterface criaEmpresaDAO() {
        EmpresaDAOInterface empresaDAO = new EmpresaDAO(genericDAO)
        return empresaDAO
    }
}
