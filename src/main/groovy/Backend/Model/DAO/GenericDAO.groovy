package Backend.Model.DAO

import Backend.Model.DAO.Interface.ConexaoBancoDadosInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.Entidade.Interface.CompetenciaInterface
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class GenericDAO implements GenericDAOInterface{

    private ConexaoBancoDadosInterface conexaoBancoDados

    GenericDAO(ConexaoBancoDadosInterface conexaoBancoDados) {
        this.conexaoBancoDados = conexaoBancoDados
    }

    boolean insereCompetenciasGeneric(String sql, int id, CompetenciaInterface competencia) {
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement inserirDado = conexao.prepareStatement(sql)) {

            inserirDado.setString(1, competencia.nome)
            inserirDado.setInt(2, id)
            inserirDado.executeUpdate()
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir dados na tabela competências: " + e)
        }
    }

    Integer buscaIdUsuarioGeneric(String sql, long identificacao) {
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
