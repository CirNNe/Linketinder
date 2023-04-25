package com.example.linketinderwebteste.Backend.Model.DAO.Interface

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.VagaInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Vaga

interface VagaDAOInterface {

    boolean insereDadosVagas(VagaInterface vaga)

    List<Vaga> buscaVagasDaEmpresa(long cnpj)

    List<Vaga> buscaVagasGerais()

    Integer buscaIdEmpresaResponsavelVaga(int idVaga)

    Integer buscaIdVaga(String nomeVaga)
}