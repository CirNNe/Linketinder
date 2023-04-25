package com.example.linketinderwebteste.Backend.Factory.FactoryDAO

import com.example.linketinderwebteste.Backend.Model.DAO.CandidatoDAO
import com.example.linketinderwebteste.Backend.Model.DAO.GenericDAO
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.CandidatoDAOInterface
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.GenericDAOInterface

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
