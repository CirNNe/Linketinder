package com.example.linketinderwebteste.Backend.Model.DAO

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConexaoBancoDados {

    static Connection instanciaConexao

    private ConexaoBancoDados() {}

    static Connection conectar() {
        if(instanciaConexao == null) {
            Properties propriedades = new Properties()
            final String USER = "higor"
            final String PASSWORD = "4deNovembro"
            final String SSL = "false"
            final String URL_SERVIDOR = "jdbc:postgresql://localhost:5432/linketinder"
            propriedades.setProperty("user", USER) as Connection
            propriedades.setProperty("password", PASSWORD) as Connection
            propriedades.setProperty("ssl", SSL) as Connection
            try {
                Class.forName("org.postgresql.Driver")
                instanciaConexao = DriverManager.getConnection(URL_SERVIDOR, propriedades)
            } catch (Exception e) {
                throw new SQLException("Erro ao tentar acessar o banco de dados: " + e)
            }
            return instanciaConexao
        }
        return instanciaConexao
    }
}
