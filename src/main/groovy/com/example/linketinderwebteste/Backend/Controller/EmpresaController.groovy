package com.example.linketinderwebteste.Backend.Controller

import com.example.linketinderwebteste.Backend.Controller.Inteface.EmpresaControllerInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.EmpresaInterface
import com.example.linketinderwebteste.Backend.Service.EmpresaService
import com.example.linketinderwebteste.Backend.Service.Interface.EmpresaServiceInterface

class EmpresaController implements EmpresaControllerInterface {

    void recebeDadosNovaEmpresa(EmpresaInterface empresa) {
        EmpresaServiceInterface empresaService = new EmpresaService()
        empresaService.salvaDadosNovaEmpresa(empresa)
    }

    EmpresaInterface perfilEmpresa(long cnpj) {
        EmpresaServiceInterface empresaService = new EmpresaService()
        return empresaService.exibeEmpresa(cnpj)
    }

    void curtirCandidato(long cnpj, int idCandidato) {
        EmpresaServiceInterface empresaService = new EmpresaService()
        empresaService.salvaCurtidaEmpresa(cnpj, idCandidato)
    }

    List listaMatchsEmpresa(long cnpj) {
        EmpresaServiceInterface empresaService = new EmpresaService()
        return empresaService.exibeListaMatchsEmpresa(cnpj)
    }
}
