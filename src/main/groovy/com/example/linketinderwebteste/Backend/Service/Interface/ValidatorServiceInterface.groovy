package com.example.linketinderwebteste.Backend.Service.Interface

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CandidatoInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CompetenciaInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.EmpresaInterface

interface ValidatorServiceInterface {

    boolean validaDadosNovoCandidato(CandidatoInterface candidato)

    boolean validaDadosNovaCompetencia(List<CompetenciaInterface> listaCompetencias)

    boolean validaDadosNovaEmpresa(EmpresaInterface empresa)

    Integer validaTipoUsuario(long identificacao)

    boolean validaCpf(long cpf)

    boolean validaCnpj(long cnpj)

    boolean validaIdVaga(int id)

}