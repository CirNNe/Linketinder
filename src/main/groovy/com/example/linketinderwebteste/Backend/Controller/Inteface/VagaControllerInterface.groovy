package com.example.linketinderwebteste.Backend.Controller.Inteface

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.VagaInterface

interface VagaControllerInterface {

    void recebeDadosVaga(VagaInterface vaga)

    List listaVagasEmpresa(long cnpj)

    List listaVagasGerais()

}