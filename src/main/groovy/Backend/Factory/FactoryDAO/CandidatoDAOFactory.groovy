package Backend.Factory.FactoryDAO

import Backend.Model.DAO.CandidatoDAO
import Backend.Model.DAO.EmpresaDAO
import Backend.Model.DAO.GenericDAO
import Backend.Model.DAO.Interface.CandidatoDAOInterface
import Backend.Model.DAO.Interface.EmpresaDAOInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.DAO.VagaDAO

class CandidatoDAOFactory {

    private GenericDAOInterface genericDAO = new GenericDAO()
    private VagaDAOFactory vagaDAOFactory = new VagaDAOFactory()

    CandidatoDAOInterface criaCandidatoDAO() {
        CandidatoDAOInterface candidatoDAO = retornaCandidatoDAO()
        return candidatoDAO
    }

    private CandidatoDAOInterface retornaCandidatoDAO() {
        CandidatoDAOInterface candidatoDAO = new CandidatoDAO(genericDAO, vagaDAOFactory.criaVagaDAO())
        return candidatoDAO
    }
}
