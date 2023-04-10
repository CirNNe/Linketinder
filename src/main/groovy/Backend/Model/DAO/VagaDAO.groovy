package Backend.Model.DAO

import Backend.Model.DAO.Interface.ConexaoBancoDadosInterface
import Backend.Model.DAO.Interface.EmpresaDAOInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.DAO.Interface.PaisDAOInterface
import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.Entidade.Interface.VagaInterface
import Backend.Model.Entidade.Vaga
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaDAO implements VagaDAOInterface {

    private GenericDAOInterface genericDAO
    private EmpresaDAOInterface empresaDAO
    private PaisDAOInterface paisDAO
    private ConexaoBancoDadosInterface conexaoBancoDados

    VagaDAO(GenericDAOInterface genericDAO, EmpresaDAOInterface empresaDAO, PaisDAOInterface paisDAO,
            ConexaoBancoDadosInterface conexaoBancoDados) {
        this.genericDAO = genericDAO
        this.empresaDAO = empresaDAO
        this.paisDAO = paisDAO
        this.conexaoBancoDados = conexaoBancoDados
    }

    boolean insereDadosVagas(long cnpj, VagaInterface vaga) {
        String sql = "INSERT INTO vagas(nome, descricao_vaga, id_empresa, id_pais) " +
                "VALUES(?, ?, ?, ?)"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement inserirVaga = conexao.prepareStatement(sql)) {

            Integer idEmpresa = empresaDAO.buscaIdEmpresa(cnpj)
            Integer idPais = paisDAO.buscaIdPais(vaga.pais)

            inserirVaga.setString(1, vaga.nome)
            inserirVaga.setString(2, vaga.descricao)
            inserirVaga.setInt(3, idEmpresa)
            inserirVaga.setInt(4, idPais)

            inserirVaga.execute()
            inserirVaga.close()
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir vaga no banco de dados: " + e)
        }
    }

    List<VagaInterface> buscaVagasDaEmpresa(long cnpj) {
        String sql = "SELECT v.id, v.nome, v.descricao_vaga, e.nome AS empresa_nome, p.nome AS pais, array_agg(comp.nome) as competencias " +
                "FROM vagas AS v " +
                "INNER JOIN empresas AS e ON e.id = v.id_empresa " +
                "INNER JOIN pais AS p ON v.id_pais = p.id " +
                "INNER JOIN competencias AS comp ON comp.id_vagas = v.id " +
                "WHERE e.cnpj = ? " +
                "GROUP BY v.id, v.nome, v.descricao_vaga, e.nome, p.nome"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement vagas = conexao.prepareStatement(sql)) {

            vagas.setLong(1, cnpj)
            ResultSet resultSet = vagas.executeQuery()

            List<VagaInterface> listaDeVagas = new ArrayList<>()

            while (resultSet.next()) {
                VagaInterface vaga = new Vaga()
                vaga.setId(resultSet.getInt("id"))
                vaga.setNome(resultSet.getString("nome"))
                vaga.setDescricao(resultSet.getString("descricao_vaga"))
                vaga.setEmpresa(resultSet.getString("empresa_nome"))
                vaga.setPais(resultSet.getString('pais'))
                vaga.setCompetencias(Arrays.asList(resultSet.getArray("competencias")))
                listaDeVagas.add(vaga)
            }
            return listaDeVagas
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar ler as vagas da empresa: " + e)
        }
    }

    List<VagaInterface> buscaVagasGerais() {
        String sql = "SELECT v.id, v.nome, v.descricao_vaga, e.nome AS nome_empresa, p.nome AS pais, array_agg(comp.nome) as competencias " +
                "FROM vagas AS v " +
                "INNER JOIN empresas AS e ON e.id = v.id_empresa " +
                "INNER JOIN pais AS p ON v.id_pais = p.id " +
                "INNER JOIN competencias AS comp ON comp.id_vagas = v.id " +
                "GROUP BY v.id, v.nome, v.descricao_vaga, e.nome, p.nome"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement vagas = conexao.prepareStatement(sql)
            ResultSet resultSet = vagas.executeQuery()) {

            List<VagaInterface> listaDeVagas = new ArrayList<>()

            while (resultSet.next()) {
                VagaInterface vaga = new Vaga()
                vaga.setId(resultSet.getInt("id"))
                vaga.setNome(resultSet.getString("nome"))
                vaga.setEmpresa(resultSet.getString("nome_empresa"))
                vaga.setDescricao(resultSet.getString("descricao_vaga"))
                vaga.setPais(resultSet.getString('pais'))
                vaga.setCompetencias(Arrays.asList(resultSet.getArray("competencias")))
                listaDeVagas.add(vaga)
            }
            return listaDeVagas
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar ler as vagas gerais: " + e)
        }
    }

    Integer buscaIdEmpresaResponsavelVaga(int idVaga) {
        String sql = "SELECT e.id FROM empresas AS e, vagas AS v WHERE v.id = ? AND e.id = v.id_empresa"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement buscarIdEmpresa = conexao.prepareStatement(sql)) {

            buscarIdEmpresa.setInt(1, idVaga)
            ResultSet resultSet = buscarIdEmpresa.executeQuery()

            List<Integer> idDaVaga = new ArrayList()

            while (resultSet.next()) {
                Integer resultadoIdUsuario =  resultSet.getInt("id")
                idDaVaga.add(resultadoIdUsuario)
            }
            return idDaVaga[0] as Integer
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar buscar o id da empresa responsável pela vaga: " + e)
        }
    }

    Integer buscaIdVaga(String nomeVaga) {
        String sql = "SELECT id FROM vagas WHERE nome = ?"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement buscarIdVaga = conexao.prepareStatement(sql)) {

            buscarIdVaga.setString(1, nomeVaga)
            ResultSet resultSet = buscarIdVaga.executeQuery()

            List<Integer> idVaga = new ArrayList()

            while (resultSet.next()) {
                int resultadoIdVaga =  resultSet.getInt("id")
                idVaga.add(resultadoIdVaga)
            }
            return idVaga[0] as Integer
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar buscar id da vaga: " + e)
        }
    }
}
