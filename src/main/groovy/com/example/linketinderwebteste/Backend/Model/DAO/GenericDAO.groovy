package com.example.linketinderwebteste.Backend.Model.DAO

import com.example.linketinderwebteste.Backend.Model.DAO.Interface.GenericDAOInterface
import groovy.sql.Sql

import java.sql.SQLException

class GenericDAO implements GenericDAOInterface{

    Sql sql = Sql.newInstance(ConexaoBancoDados.conectar())

    Integer buscaIdUsuario(String comandoSql, long identificacao) {
        try {
            List<Integer> idUsuario = new ArrayList<>()
            sql.query(comandoSql, [identificacao]) { resultSet ->
                while (resultSet.next()) {
                    int resultadoIdUsuario =  resultSet.getInt("id")
                    idUsuario.add(resultadoIdUsuario)
                }
            }
            return idUsuario[0]
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar buscar id do usuário: " + e)
        }
    }
}
