package Backend.Model.DAO

import Backend.Model.DAO.Interface.ConexaoBancoDadosInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class GenericDAO implements GenericDAOInterface{

    private ConexaoBancoDadosInterface conexaoBancoDados

    GenericDAO(ConexaoBancoDadosInterface conexaoBancoDados) {
        this.conexaoBancoDados = conexaoBancoDados
    }

    Integer buscaIdUsuario(String sql, long identificacao) {
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement buscarId = conexao.prepareStatement(sql)) {

            buscarId.setLong(1, identificacao)
            ResultSet resultSet = buscarId.executeQuery()

            List<Integer> idUsuario = new ArrayList<>()

            while (resultSet.next()) {
                int resultadoIdUsuario =  resultSet.getInt("id")
                idUsuario.add(resultadoIdUsuario)
            }
            return idUsuario[0] as Integer
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar buscar id do usuário: " + e)
        }
    }
}
