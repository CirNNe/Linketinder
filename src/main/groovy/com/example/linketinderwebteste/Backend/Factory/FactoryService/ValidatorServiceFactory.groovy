package com.example.linketinderwebteste.Backend.Factory.FactoryService

import com.example.linketinderwebteste.Backend.Factory.FactoryDAO.CandidatoDAOFactory
import com.example.linketinderwebteste.Backend.Factory.FactoryDAO.EmpresaDAOFactory
import com.example.linketinderwebteste.Backend.Factory.FactoryDAO.VagaDAOFactory
import com.example.linketinderwebteste.Backend.Service.Interface.ValidatorServiceInterface
import com.example.linketinderwebteste.Backend.Service.ValidatorService
import com.example.linketinderwebteste.Backend.Util.Regex.RegexValidaDadosNovaVaga
import com.example.linketinderwebteste.Backend.Util.Regex.RegexValidaDadosNovoUsuario

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
