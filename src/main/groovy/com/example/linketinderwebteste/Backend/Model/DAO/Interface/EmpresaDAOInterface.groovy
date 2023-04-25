package com.example.linketinderwebteste.Backend.Model.DAO.Interface

import com.example.linketinderwebteste.Backend.Model.Entidade.Empresa
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.EmpresaInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Match

interface EmpresaDAOInterface {

    Object buscaIdEmpresa(long cnpj)

    Empresa buscaPerfilUnicoEmpresa(long cnpj)

    boolean insereDadosEmpresas(EmpresaInterface empresa)

    boolean insereCurtidaACandidato(long cnpj, int idCandidato)

    List<Match> buscaMatchsEmpresa(long cnpj)
}