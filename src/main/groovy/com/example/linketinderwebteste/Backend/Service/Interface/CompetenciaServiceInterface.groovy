package com.example.linketinderwebteste.Backend.Service.Interface

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CompetenciaInterface

interface CompetenciaServiceInterface {

    boolean recebeDadosNovaCompetencia(long identificacao, List<CompetenciaInterface> listaCompetencias)

    boolean filtraCompetenciaDaLista(Integer id, long identificacao, List<CompetenciaInterface> listaCompetencias)

    boolean salvaNovaCompetencia(Integer id, long identificacao, CompetenciaInterface competencia)

}