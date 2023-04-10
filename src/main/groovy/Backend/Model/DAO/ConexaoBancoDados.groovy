package Backend.Model.DAO

import Backend.Model.DAO.Interface.ConexaoBancoDadosInterface

import java.sql.Connection
import java.sql.DriverManager

class ConexaoBancoDados implements ConexaoBancoDadosInterface {

    Connection conectar() {
        Properties propriedades = new Properties()
        final String USER = "higor"
        final String PASSWORD = "4deNovembro"
        final String SSL = "false"
        final String URL_SERVIDOR = "jdbc:postgresql://localhost:5432/linketinder"
        propriedades.setProperty("user", USER) as Connection
        propriedades.setProperty("password", PASSWORD) as Connection
        propriedades.setProperty("ssl", SSL) as Connection
        try {
            return DriverManager.getConnection(URL_SERVIDOR, propriedades)
        } catch (Exception e) {
            throw SQLException("Erro ao tentar acessar o banco de dados: " + e)
        }
    }
}
