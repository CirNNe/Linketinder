package com.example.linketinderwebteste.Backend.Factory.FactoryDAO

import com.example.linketinderwebteste.Backend.Model.DAO.GenericDAO
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.GenericDAOInterface
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.VagaDAOInterface
import com.example.linketinderwebteste.Backend.Model.DAO.VagaDAO

class VagaDAOFactory {

    private EmpresaDAOFactory empresaDAOFactory = new EmpresaDAOFactory()
    private GenericDAOInterface genericDAO = new GenericDAO()

    VagaDAOInterface criaVagaDAO() {
        VagaDAOInterface vagaDAO = new VagaDAO(genericDAO, empresaDAOFactory.criaEmpresaDAO())
        return vagaDAO
    }
}
