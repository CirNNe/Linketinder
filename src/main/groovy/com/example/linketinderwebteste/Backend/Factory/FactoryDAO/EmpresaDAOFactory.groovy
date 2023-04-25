package com.example.linketinderwebteste.Backend.Factory.FactoryDAO

import com.example.linketinderwebteste.Backend.Model.DAO.EmpresaDAO
import com.example.linketinderwebteste.Backend.Model.DAO.GenericDAO
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.EmpresaDAOInterface
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.GenericDAOInterface

class EmpresaDAOFactory {

    private GenericDAOInterface genericDAO = new GenericDAO()

    EmpresaDAOInterface criaEmpresaDAO() {
        EmpresaDAOInterface empresaDAO = new EmpresaDAO(genericDAO)
        return empresaDAO
    }
}
