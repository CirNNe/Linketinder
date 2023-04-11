package Backend.Model.DAO

import Backend.Model.DAO.Interface.ConexaoBancoDadosInterface
import Backend.Model.DAO.Interface.EmpresaDAOInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.DAO.Interface.PaisDAOInterface
import Backend.Model.Entidade.Empresa
import Backend.Model.Entidade.Interface.EmpresaInterface
import Backend.Model.Entidade.Interface.MatchInterface
import Backend.Model.Entidade.Match
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class EmpresaDAO implements EmpresaDAOInterface{

    private ConexaoBancoDadosInterface conexaoBancoDados
    private GenericDAOInterface genericDAO
    private PaisDAOInterface paisDAO

    EmpresaDAO(ConexaoBancoDadosInterface conexaoBancoDados, GenericDAOInterface genericDAO, PaisDAOInterface paisDAO) {
        this.conexaoBancoDados = conexaoBancoDados
        this.genericDAO = genericDAO
        this.paisDAO = paisDAO
    }

    Integer buscaIdEmpresa(long cnpj) {
        try {
            String sql = "SELECT id FROM empresas WHERE cnpj = ?"
            return genericDAO.buscaIdUsuario(sql, cnpj)
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar encontrar id da empresa: " + e)
        }
    }

    Empresa buscaPerfilUnicoEmpresa(long cnpj) {
        String sql = "SELECT e.id, e.nome, e.email, e.cnpj, e.cep, p.nome AS pais, e.descricao_empresa " +
                "FROM empresas AS e, pais AS p " +
                "WHERE e.cnpj = ? AND e.id_pais = p.id " +
                "GROUP BY e.id, e.nome, e.email, e.cnpj, e.cep, pais, e.descricao_empresa ORDER BY e.id ASC"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement perfilEmpresa = conexao.prepareStatement(sql)) {

            perfilEmpresa.setLong(1, cnpj)
            ResultSet resultSet = perfilEmpresa.executeQuery()

            EmpresaInterface empresa = new Empresa()
            while (resultSet.next()) {
                empresa.setId(resultSet.getInt("id"))
                empresa.setNome(resultSet.getString("nome"))
                empresa.setEmail(resultSet.getString("email"))
                empresa.setCnpj(resultSet.getLong("cnpj"))
                empresa.setCep(resultSet.getInt("cep"))
                empresa.setPais(resultSet.getString("pais"))
                empresa.setDescricao(resultSet.getString("descricao_empresa"))
            }
            return empresa
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar encontrar a empresa no banco de dados: " + e)
        }
    }

    boolean insereDadosEmpresas(EmpresaInterface empresa) {
        String sql = "INSERT INTO empresas(nome, cnpj, email, descricao_empresa, id_pais, cep, senha) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement inserirEmpresa = conexao.prepareStatement(sql)) {

            inserirEmpresa.setString(1, empresa.nome)
            inserirEmpresa.setLong(2, empresa.cnpj)
            inserirEmpresa.setString(3, empresa.email)
            inserirEmpresa.setString(4, empresa.descricao)

            Integer idPais = paisDAO.buscaIdPais(empresa.getPais())
            inserirEmpresa.setInt(5, idPais)

            inserirEmpresa.setInt(6, empresa.cep)
            inserirEmpresa.setString(7, empresa.senha)

            inserirEmpresa.execute()
            inserirEmpresa.close()
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir dados da empresa no banco de daddos: " + e)
        }
    }

    boolean insereCurtidaACandidato(long cnpj, int idCandidato) {
        String sql = "INSERT INTO curtida_empresa(id_empresa, id_candidato) VALUES(?, ?)"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement inserirCurtidaEmpresa = conexao.prepareStatement(sql)) {

            Integer idEmpresa = buscaIdEmpresa(cnpj)

            inserirCurtidaEmpresa.setInt(1, idEmpresa)
            inserirCurtidaEmpresa.setInt(2, idCandidato)

            inserirCurtidaEmpresa.execute()
            inserirCurtidaEmpresa.close()
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir curtida da empresa no banco de dados: " + e)
        }
    }

    List<Match> buscaMatchsEmpresa(long cnpj) {
        String sql = "SELECT DISTINCT c.nome as nome_candidato, e.nome as nome_empresa " +
                "FROM curtida_candidato cc " +
                "JOIN candidatos c ON cc.id_candidato = c.id " +
                "JOIN curtida_empresa ce ON cc.id_candidato = ce.id_candidato AND cc.id_empresa = ce.id_empresa " +
                "JOIN empresas e ON ce.id_empresa = e.id " +
                "WHERE e.id = ?"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement matchs = conexao.prepareStatement(sql)) {

            Integer idEmpresa = buscaIdEmpresa(cnpj)
            matchs.setInt(1, idEmpresa)
            ResultSet resultSet = matchs.executeQuery()

            List<Match> listaMatchs = new ArrayList()

            while (resultSet.next()) {
                MatchInterface match = new Match()
                match.setNomeCandidato(resultSet.getString("nome_candidato"))
                listaMatchs.add(match)
            }
            return listaMatchs
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar ler os matchs da empresa: " + e)
        }
    }
}
