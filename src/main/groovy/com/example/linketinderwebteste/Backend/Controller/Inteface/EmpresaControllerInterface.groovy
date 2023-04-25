package com.example.linketinderwebteste.Backend.Controller.Inteface

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.EmpresaInterface

interface EmpresaControllerInterface {

    void recebeDadosNovaEmpresa(EmpresaInterface empresa)

    EmpresaInterface perfilEmpresa(long cnpj)

    void curtirCandidato(long cnpj, int idCandidato)

    List listaMatchsEmpresa(long cnpj)

}
