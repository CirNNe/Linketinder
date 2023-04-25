package com.example.linketinderwebteste.Backend.Controller

import com.example.linketinderwebteste.Backend.Controller.Inteface.CompetenciaControllerInterface
import com.example.linketinderwebteste.Backend.Service.CompetenciaService
import com.example.linketinderwebteste.Backend.Service.Interface.CompetenciaServiceInterface

class CompetenciaController implements CompetenciaControllerInterface {

    boolean recebeDadosNovaCompetencia(long identificacao, List listaCompetencias) {
        CompetenciaServiceInterface competenciaService = new CompetenciaService()
        competenciaService.recebeDadosNovaCompetencia(identificacao, listaCompetencias)
    }
}
