package Backend.Model.DAO

import Backend.Model.Entidade.Candidato
import Backend.Model.Entidade.Competencia
import Backend.Model.Entidade.Match
import java.sql.Date
import java.sql.PreparedStatement
import java.sql.Connection
import java.sql.ResultSet
import java.text.DateFormat
import java.text.SimpleDateFormat

class CandidatoDAO extends GenericDAO<Candidato> {

    VagaDAO vagaDAO = new VagaDAO()

    Object buscaIdCandidato(long cpf) {

        try {
            String sql = "SELECT id FROM candidatos WHERE cpf = ?"

            return super.buscaIdUsuario(sql, cpf)

        } catch (Exception e) {
            println("Erro ao tentar encontrar o id do Candidato." + e)
            return null
        }
    }

    List<Candidato> listarDadosDaTabelaCandidatos() {

        String sql = "SELECT c.id, c.nome, c.data_nascimento, c.email, c.cpf, c.cep, (SELECT p.nome FROM pais AS p WHERE p.id = c.id_pais) AS pais, c.descricao_pessoal, array_agg(comp.nome) as competencias " +
                "FROM candidatos AS c " +
                "INNER JOIN competencias AS comp ON c.id = comp.id_candidato " +
                "GROUP BY c.id, c.nome, c.data_nascimento, c.email, c.cpf, c.cep, pais, c.descricao_pessoal ORDER BY c.id ASC"

        try (Connection conexao = super.conectar()
             PreparedStatement candidatos = conexao.prepareStatement(sql)
             ResultSet resultSet = candidatos.executeQuery()) {

            List<Candidato> listaDeCandidatos = new ArrayList<>()

            while (resultSet.next()) {
                Candidato candidato = new Candidato()
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
            e.printStackTrace()
            if (e instanceof ClassNotFoundException) {
                System.err.println("Verifique o driver de conexão.")
            } else {
                System.err.println("Verifique se o servidor está ativo.")
            }
            System.exit(-42)
            return null
        }
    }

    Candidato lePerfilUnicoCandidato(long cpf) {

        String sql = "SELECT c.id, c.nome, c.data_nascimento, c.email, c.cpf, (SELECT p.nome FROM pais AS p WHERE p.id = c.id_pais) AS pais, c.cep, c.descricao_pessoal, array_agg(comp.nome) as competencias " +
                        "FROM candidatos AS c " +
                        "INNER JOIN competencias AS comp ON c.id = comp.id_candidato " +
                        "WHERE c.cpf = ? " +
                        "GROUP BY c.id, c.nome, c.data_nascimento, c.email, c.cpf, c.cep, pais, c.descricao_pessoal"

        try (Connection conexao = super.conectar()
             PreparedStatement perfilCandidato = conexao.prepareStatement(sql)
             ) {

            perfilCandidato.setLong(1, cpf)
            ResultSet resultSet = perfilCandidato.executeQuery()

            Candidato candidato = new Candidato()

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
            e.printStackTrace()
            if (e instanceof ClassNotFoundException) {
                System.err.println("Verifique o driver de conexão.")
            } else {
                System.err.println("Verifique se o servidor está ativo.")
            }
            System.exit(-42)
            return null
        }
    }

    boolean inserirDadosNaTabelaCanidatos(Candidato candidato) {

        String sql = "INSERT INTO candidatos(nome, data_nascimento, email, cpf, id_pais, cep, descricao_pessoal, senha) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"

        try (Connection conexao = super.conectar()
             PreparedStatement inserirCandidato = conexao.prepareStatement(sql)) {

            inserirCandidato.setString(1, candidato.nome)

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy")
            Date date = new Date(dateFormat.parse(candidato.dataNascimento).getTime());
            inserirCandidato.setDate(2, date)

            inserirCandidato.setString(3, candidato.email)
            inserirCandidato.setLong(4, candidato.cpf)

            int idPais = buscaIdPais(candidato.pais) as int
            inserirCandidato.setInt(5, idPais)

            inserirCandidato.setInt(6, candidato.cep)
            inserirCandidato.setString(7, candidato.descricaoPessoal)
            inserirCandidato.setString(8, candidato.senha)

            inserirCandidato.execute()
            return true
        } catch (Exception e) {
            e.printStackTrace()
            if (e instanceof ClassNotFoundException) {
                System.err.println("Verifique o driver de conexão.")
            } else {
                System.err.println("Verifique se o servidor está ativo.")
            }
            System.exit(-42)
            return null
        }
    }

    Object inserirCompetenciaCandidato(long cpf, Competencia competencia) {

        try {

            int id = buscaIdCandidato(cpf) as int

            String sql = "INSERT INTO competencias(nome, id_candidato) VALUES(?, ?)"

            return super.inserirDadosNaTebelaCompetencias(sql, id, competencia)
        } catch (Exception e) {
            println("Erro ao tentar completar o cadastro. " + e)
            return null
        }
    }

    boolean inserirCurtidaVaga(long cpf, int idVaga) {

        String sql = "INSERT INTO curtida_candidato(id_candidato, id_empresa) VALUES(?, ?)"

        try (Connection conexao = super.conectar()
             PreparedStatement inserirCurtidaCandidato = conexao.prepareStatement(sql)) {

            int idCandidato = buscaIdCandidato(cpf) as int
            int idEmpresa = vagaDAO.buscaIdEmpresaResponsavelVaga(idVaga) as int

            inserirCurtidaCandidato.setInt(1, idCandidato)
            inserirCurtidaCandidato.setInt(2, idEmpresa)

            inserirCurtidaCandidato.execute()
            return true
        } catch (Exception e) {
            e.printStackTrace()
            if (e instanceof ClassNotFoundException) {
                System.err.println("Verifique o driver de conexão.")
            } else {
                System.err.println("Verifique se o servidor está ativo.")
            }
            System.exit(-42)
            return null
        }
    }

    List<Match> leMatchsCandidato(long cpf) {

        String sql = "SELECT DISTINCT c.nome as nome_candidato, e.nome as nome_empresa " +
                "FROM curtida_candidato cc " +
                "JOIN candidatos c ON cc.id_candidato = c.id " +
                "JOIN curtida_empresa ce ON cc.id_candidato = ce.id_candidato AND cc.id_empresa = ce.id_empresa " +
                "JOIN empresas e ON ce.id_empresa = e.id " +
                "WHERE c.id = ?"

        try (Connection conexao = super.conectar()
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
            e.printStackTrace()
            if (e instanceof ClassNotFoundException) {
                System.err.println("Verifique o driver de conexão.")
            } else {
                System.err.println("Verifique se o servidor está ativo.")
            }
            System.exit(-42)
            return null
        }
    }
}
