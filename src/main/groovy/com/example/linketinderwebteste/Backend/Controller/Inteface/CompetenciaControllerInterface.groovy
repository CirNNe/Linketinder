package com.example.linketinderwebteste.Backend.Controller.Inteface

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CompetenciaInterface

interface CompetenciaControllerInterface {

    boolean recebeDadosNovaCompetencia(long identificacao, List<CompetenciaInterface> listaCompetencias)

}