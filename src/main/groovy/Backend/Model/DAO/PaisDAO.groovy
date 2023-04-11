package Backend.Model.DAO

import Backend.Model.DAO.Interface.ConexaoBancoDadosInterface
import Backend.Model.DAO.Interface.PaisDAOInterface
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class PaisDAO implements PaisDAOInterface{

    private ConexaoBancoDadosInterface conexaoBancoDados

    PaisDAO(ConexaoBancoDadosInterface conexaoBancoDados) {
        this.conexaoBancoDados = conexaoBancoDados
    }

    Integer buscaIdPais(String pais) {
        String sql = "SELECT id FROM pais WHERE nome = ?"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement buscarId = conexao.prepareStatement(sql)) {

            buscarId.setString(1, pais)
            ResultSet resultSet = buscarId.executeQuery()

            List<Integer> idPais = new ArrayList()

            while (resultSet.next()) {
                int resultadoIdPais =  resultSet.getInt("id")
                idPais.add(resultadoIdPais)
            }
            return idPais[0] as Integer
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar buscar o id do país: " + e)
        }
    }

    List buscaListaPaises() {
        String sql = "SELECT * FROM pais"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement buscarPaises = conexao.prepareStatement(sql)
            ResultSet resultSet = buscarPaises.executeQuery()) {
            List paises = new ArrayList()
            while(resultSet.next()) {
                String pais = resultSet.getString("nome")
                paises.add(pais)
            }
            return paises
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar buscar a lista de países no banco de dados: " + e)
        }
    }
}
