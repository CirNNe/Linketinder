package com.example.linketinderwebteste.Backend.Model.DAO.Interface

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CompetenciaInterface

interface CompetenciaDAOInterface {

    boolean insereCompetencia(String sql, int id, CompetenciaInterface competencia)

}