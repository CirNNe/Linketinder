package com.example.linketinderwebteste.Backend.Service.Interface

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.EmpresaInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.MatchInterface

interface EmpresaServiceInterface {

    boolean salvaDadosNovaEmpresa(EmpresaInterface empresa)

    EmpresaInterface recebeDadosEmpresas(long cnpj)

    EmpresaInterface exibeEmpresa(long cnpj)

    boolean salvaCurtidaEmpresa(long cnpj, int idCandidato)

    List<MatchInterface> recebeListaMatchsEmpresa(long cnpj)

    List formataListaMatchsEmpresa(long cnpj)

    List exibeListaMatchsEmpresa(long cnpj)
}
