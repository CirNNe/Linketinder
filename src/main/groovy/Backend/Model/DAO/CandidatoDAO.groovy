package Backend.Model.DAO

import Backend.Model.DAO.Interface.CandidatoDAOInterface
import Backend.Model.DAO.Interface.ConexaoBancoDadosInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.DAO.Interface.PaisDAOInterface
import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.Entidade.Candidato
import Backend.Model.Entidade.Interface.CandidatoInterface
import Backend.Model.Entidade.Match
import java.sql.Connection
import java.sql.Date
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.text.DateFormat
import java.text.SimpleDateFormat

class CandidatoDAO implements CandidatoDAOInterface {

    private ConexaoBancoDadosInterface conexaoBancoDados
    private GenericDAOInterface genericDAO
    private VagaDAOInterface vagaDAO
    private PaisDAOInterface paisDAO

    CandidatoDAO(ConexaoBancoDadosInterface conexaoBancoDados, GenericDAOInterface genericDAO, VagaDAOInterface vagaDAO,
                 PaisDAOInterface paisDAO) {
        this.conexaoBancoDados = conexaoBancoDados
        this.genericDAO = genericDAO
        this.vagaDAO = vagaDAO
        this.paisDAO = paisDAO
    }

    Integer buscaIdCandidato(long cpf) {
        try {
            String sql = "SELECT id FROM candidatos WHERE cpf = ?"
            return genericDAO.buscaIdUsuarioGeneric(sql, cpf)
        } catch (Exception e) {
            throw new Exception("Erro ao tentar encontrar o id do Candidato." + e)
        }
    }

    List<CandidatoInterface> buscaListaCandidatos() {
        String sql = "SELECT c.id, c.nome, c.data_nascimento, c.email, c.cpf, c.cep, (SELECT p.nome FROM pais AS p WHERE p.id = c.id_pais) AS pais, c.descricao_pessoal, array_agg(comp.nome) as competencias " +
                "FROM candidatos AS c " +
                "INNER JOIN competencias AS comp ON c.id = comp.id_candidato " +
                "GROUP BY c.id, c.nome, c.data_nascimento, c.email, c.cpf, c.cep, pais, c.descricao_pessoal ORDER BY c.id ASC"
        try (Connection conexao = conexaoBancoDados.conectar()
             PreparedStatement candidatos = conexao.prepareStatement(sql)
             ResultSet resultSet = candidatos.executeQuery()) {

            List<CandidatoInterface> listaDeCandidatos = new ArrayList<>()

            while (resultSet.next()) {
                CandidatoInterface candidato = new Candidato()
                candidato.setId(resultSet.getInt("id"))
                candidato.setNome(resultSet.getString("nome"))
                candidato.setEmail(resultSet.getString("email"))
                candidato.setDataNascimento(resultSet.getDate("data_nascimento") as String)
                candidato.setCpf(resultSet.getLong("cpf"))
                candidato.setCep(resultSet.getInt("cep"))
                candidato.setPais(resultSet.getString("pais"))
                candidato.setDescricaoPessoal(resultSet.getString("descricao_pessoal"))
                candidato.setCompetencias(Arrays.asList(resultSet.getArray("competencias")))
                listaDeCandidatos.add(candidato)
            }
            return listaDeCandidatos
        } catch (Exception e) {
            throw SQLException("Erro ao tentar buscar lista de candidatos: " + e)
        }
    }

    Candidato buscaPerfilUnicoCandidato(long cpf) {
        String sql = "SELECT c.id, c.nome, c.data_nascimento, c.email, c.cpf, (SELECT p.nome FROM pais AS p WHERE p.id = c.id_pais) AS pais, c.cep, c.descricao_pessoal, array_agg(comp.nome) AS competencias " +
                        "FROM candidatos AS c " +
                        "INNER JOIN competencias AS comp ON c.id = comp.id_candidato " +
                        "WHERE c.cpf = ? " +
                        "GROUP BY c.id, c.nome, c.data_nascimento, c.email, c.cpf, c.cep, pais, c.descricao_pessoal"
        try (Connection conexao = conexaoBancoDados.conectar()
             PreparedStatement perfilCandidato = conexao.prepareStatement(sql)) {

            perfilCandidato.setLong(1, cpf)
            ResultSet resultSet = perfilCandidato.executeQuery()

            CandidatoInterface candidato = new Candidato()
            while (resultSet.next()) {
                candidato.setId(resultSet.getInt("id"))
                candidato.setNome(resultSet.getString("nome"))
                candidato.setEmail(resultSet.getString("email"))
                candidato.setDataNascimento(resultSet.getDate("data_nascimento") as String)
                candidato.setCpf(resultSet.getLong("cpf"))
                candidato.setCep(resultSet.getInt("cep"))
                candidato.setPais(resultSet.getString("pais"))
                candidato.setDescricaoPessoal(resultSet.getString("descricao_pessoal"))
                candidato.setCompetencias(Arrays.asList(resultSet.getArray("competencias")))
            }
            return candidato
        } catch (Exception e) {
            throw new Exception("Erro ao tentar ler perfil do candidato " + e)
        }
    }

    boolean insereDadosCandidato(CandidatoInterface candidato) {
        String sql = "INSERT INTO candidatos(nome, data_nascimento, email, cpf, id_pais, cep, descricao_pessoal, senha) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement inserirCandidato = conexao.prepareStatement(sql)) {

            inserirCandidato.setString(1, candidato.nome)

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy")
            Date date = new Date(dateFormat.parse(candidato.dataNascimento).getTime());
            inserirCandidato.setDate(2, date)

            inserirCandidato.setString(3, candidato.email)
            inserirCandidato.setLong(4, candidato.cpf)

            Integer idPais = paisDAO.buscaIdPais(candidato.pais)
            inserirCandidato.setInt(5, idPais)

            inserirCandidato.setInt(6, candidato.cep)
            inserirCandidato.setString(7, candidato.descricaoPessoal)
            inserirCandidato.setString(8, candidato.senha)

            inserirCandidato.execute()
            inserirCandidato.close()
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir candidato no banco de dados " + e)
        }
    }

    boolean insereCurtiAVaga(long cpf, int idVaga) {
        String sql = "INSERT INTO curtida_candidato(id_candidato, id_empresa) VALUES(?, ?)"

        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement inserirCurtidaCandidato = conexao.prepareStatement(sql)) {

            int idCandidato = buscaIdCandidato(cpf) as int
            int idEmpresa = vagaDAO.buscaIdEmpresaResponsavelVaga(idVaga) as int

            inserirCurtidaCandidato.setInt(1, idCandidato)
            inserirCurtidaCandidato.setInt(2, idEmpresa)

            inserirCurtidaCandidato.execute()
            return true
        } catch (Exception e) {
            throw SQLException("Erro ao tentar inserir curtida à vaga " + e)
        }
    }

    List<Match> buscaMatchsCandidato(long cpf) {
        String sql = "SELECT DISTINCT c.nome as nome_candidato, e.nome as nome_empresa " +
                "FROM curtida_candidato cc " +
                "JOIN candidatos c ON cc.id_candidato = c.id " +
                "JOIN curtida_empresa ce ON cc.id_candidato = ce.id_candidato AND cc.id_empresa = ce.id_empresa " +
                "JOIN empresas e ON ce.id_empresa = e.id " +
                "WHERE c.id = ?"
        try(Connection conexao = conexaoBancoDados.conectar()
            PreparedStatement matchs = conexao.prepareStatement(sql)) {

            int idCandidato = buscaIdCandidato(cpf) as int

            matchs.setInt(1, idCandidato)
            ResultSet resultSet = matchs.executeQuery()

            List<Match> listaMatchs = new ArrayList()

            while (resultSet.next()) {
                Match match = new Match()
                match.setNomeEmpresa(resultSet.getString("nome_empresa"))
                listaMatchs.add(match)
            }
            return listaMatchs
        } catch (Exception e) {
            throw SQLException("Erro ao tentar ler os matchs do candidato " + e)
        }
    }
}
