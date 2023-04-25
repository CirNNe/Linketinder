package com.example.linketinderwebteste.Backend.Controller.Inteface

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CandidatoInterface

interface CandidatoControllerInterface {

    List listaCandidatos()

    CandidatoInterface exibePerfilCandidato(long cpf)

    void curtirVaga(long cpf, int idVaga)

    List listaMatchsCandidato(long cpf)
}
