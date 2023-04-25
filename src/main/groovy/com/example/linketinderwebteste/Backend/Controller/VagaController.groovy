package com.example.linketinderwebteste.Backend.Controller

import com.example.linketinderwebteste.Backend.Controller.Inteface.VagaControllerInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.VagaInterface
import com.example.linketinderwebteste.Backend.Service.Interface.VagaServiceInterface
import com.example.linketinderwebteste.Backend.Service.VagaService

class VagaController implements VagaControllerInterface {

    void recebeDadosVaga(VagaInterface vaga) {
        VagaServiceInterface vagaService = new VagaService()
        vagaService.salvaDadosNovaVaga(vaga)
    }

    List listaVagasEmpresa(long cnpj) {
        VagaServiceInterface vagaService = new VagaService()
        return vagaService.exibeListaVagasEmpresa(cnpj)
    }

    List listaVagasGerais() {
        VagaServiceInterface vagaService = new VagaService()
        return vagaService.exibeListaVagasGerais()
    }
}
