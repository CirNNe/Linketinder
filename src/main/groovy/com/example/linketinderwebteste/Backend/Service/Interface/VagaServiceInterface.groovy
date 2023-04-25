package com.example.linketinderwebteste.Backend.Service.Interface

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.VagaInterface

interface VagaServiceInterface {

    boolean validaDadosNovaVaga(VagaInterface vaga)

    boolean salvaDadosNovaVaga(VagaInterface vaga)

    List<VagaInterface> recebeListaVagasEmpresa(long cnpj)

    List exibeListaVagasEmpresa(long cnpj)

    List<VagaInterface> recebeListaVagasGerais()

    List exibeListaVagasGerais()
}