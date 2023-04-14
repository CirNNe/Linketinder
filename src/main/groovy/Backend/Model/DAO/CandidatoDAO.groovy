package Backend.Model.DAO

import Backend.Model.DAO.Interface.CandidatoDAOInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.Entidade.Candidato
import Backend.Model.Entidade.Interface.CandidatoInterface
import Backend.Model.Entidade.Match
import groovy.sql.Sql
import java.sql.Date
import java.sql.SQLException
import java.text.DateFormat
import java.text.SimpleDateFormat

class CandidatoDAO implements CandidatoDAOInterface {

    private GenericDAOInterface genericDAO
    private VagaDAOInterface vagaDAO
    Sql sql = Sql.newInstance(ConexaoBancoDados.conectar())

    CandidatoDAO(GenericDAOInterface genericDAO, VagaDAOInterface vagaDAO) {
        this.genericDAO = genericDAO
        this.vagaDAO = vagaDAO
    }

    Integer buscaIdCandidato(long cpf) {
        try {
            String sql = "SELECT id FROM candidatos WHERE cpf = ?"
            return genericDAO.buscaIdUsuario(sql, cpf)
        } catch (Exception e) {
            throw new Exception("Erro ao tentar encontrar o id do Candidato." + e)
        }
    }

    List<CandidatoInterface> buscaListaCandidatos() {
        String comandSql = "SELECT c.id, c.nome, c.data_nascimento, c.email, c.cpf, c.cep, c.pais, c.descricao_pessoal, array_agg(comp.nome) as competencias " +
                "FROM candidatos AS c " +
                "INNER JOIN competencias AS comp ON c.id = comp.id_candidato " +
                "GROUP BY c.id, c.nome, c.data_nascimento, c.email, c.cpf, c.cep, c.pais, c.descricao_pessoal ORDER BY c.id ASC"
        try {
            List<CandidatoInterface> listaDeCandidatos = new ArrayList<>()
            sql.query(comandSql) {resultSet ->
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
            }
            return listaDeCandidatos
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar buscar lista de candidatos: " + e)
        }
    }

    Candidato buscaPerfilUnicoCandidato(long cpf) {
        String comandoSql = "SELECT c.id, c.nome, c.data_nascimento, c.email, c.cpf, c.pais, c.cep, c.descricao_pessoal, array_agg(comp.nome) AS competencias " +
                        "FROM candidatos AS c " +
                        "INNER JOIN competencias AS comp ON c.id = comp.id_candidato " +
                        "WHERE c.cpf = ? " +
                        "GROUP BY c.id, c.nome, c.data_nascimento, c.email, c.cpf, c.cep, c.pais, c.descricao_pessoal"
        try {
            CandidatoInterface candidato = new Candidato()
            sql.query(comandoSql, [cpf]) {resultSet ->
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
            }
            return candidato
        } catch (Exception e) {
            throw new Exception("Erro ao tentar ler perfil do candidato " + e)
        }
    }

    boolean insereDadosCandidato(CandidatoInterface candidato) {
        String comandoSql = "INSERT INTO candidatos(nome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy")
            Date date = new Date(dateFormat.parse(candidato.dataNascimento).getTime());

            sql.execute(comandoSql, [candidato.nome, date, candidato.email, candidato.cpf,
                                     candidato.pais, candidato.cep, candidato.descricaoPessoal, candidato.senha])
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir candidato no banco de dados " + e)
        }
    }

    boolean insereCurtiAVaga(long cpf, int idVaga) {
        String comandSql = "INSERT INTO curtida_candidato(id_candidato, id_empresa) VALUES(?, ?)"

        try {
            int idCandidato = buscaIdCandidato(cpf) as int
            int idEmpresa = vagaDAO.buscaIdEmpresaResponsavelVaga(idVaga) as int

            sql.execute(comandSql, [idCandidato, idEmpresa])

            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir curtida à vaga " + e)
        }
    }

    List<Match> buscaMatchsCandidato(long cpf) {
        String comandoSql = "SELECT DISTINCT c.nome as nome_candidato, e.nome as nome_empresa " +
                "FROM curtida_candidato cc " +
                "JOIN candidatos c ON cc.id_candidato = c.id " +
                "JOIN curtida_empresa ce ON cc.id_candidato = ce.id_candidato AND cc.id_empresa = ce.id_empresa " +
                "JOIN empresas e ON ce.id_empresa = e.id " +
                "WHERE c.id = ?"
        try {
            Integer idCandidato = buscaIdCandidato(cpf)
            List<Match> listaMatchs = new ArrayList()

            sql.query(comandoSql, [idCandidato]) {resultSet ->
                while (resultSet.next()) {
                    Match match = new Match()
                    match.setNomeEmpresa(resultSet.getString("nome_empresa"))
                    listaMatchs.add(match)
                }
            }
            return listaMatchs
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar ler os matchs do candidato " + e)
        }
    }
}
