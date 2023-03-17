package Backend.Model.DAO

import Backend.Model.Entidade.Empresa
import Backend.Model.Entidade.Match
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class EmpresaDAO extends GenericDAO<Empresa> {

    Object buscaIdEmpresa(long cnpj) {

        try {
            String sql = "SELECT id FROM empresas WHERE cnpj = ?"

            return super.buscaIdUsuario(sql, cnpj)

        } catch (Exception e) {
            println("Erro ao tentar encontrar o id da Empresa." + e)
            return null
        }

    }

    Empresa lePerfilUnicoEmpresa(long cnpj) {

        String sql = "SELECT e.id, e.nome, e.email, e.cnpj, e.cep, p.nome AS pais, e.descricao_empresa " +
                "FROM empresas AS e, pais AS p " +
                "WHERE e.cnpj = ? AND e.id_pais = p.id " +
                "GROUP BY e.id, e.nome, e.email, e.cnpj, e.cep, pais, e.descricao_empresa ORDER BY e.id ASC"

        try (Connection conexao = super.conectar()
             PreparedStatement empresas = conexao.prepareStatement(sql)) {

            empresas.setLong(1, cnpj)

            ResultSet resultSet = empresas.executeQuery()

            Empresa empresa = new Empresa()

            while (resultSet.next()) {
                empresa.setId(resultSet.getInt("id"))
                empresa.setNome(resultSet.getString("nome"))
                empresa.setEmailCorporativo(resultSet.getString("email"))
                empresa.setCnpj(resultSet.getLong("cnpj"))
                empresa.setCep(resultSet.getInt("cep"))
                empresa.setPais(resultSet.getString("pais"))
                empresa.setDescricao(resultSet.getString("descricao_empresa"))
            }
            return empresa
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

    boolean inserirDadosNaTabelaEmpresas(Empresa empresa) {

        String sql = "INSERT INTO empresas(nome, cnpj, email, descricao_empresa, id_pais, cep, senha) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)"

        try (Connection conexao = super.conectar()
             PreparedStatement inserirEmpresa = conexao.prepareStatement(sql)) {

            inserirEmpresa.setString(1, empresa.nome)
            inserirEmpresa.setLong(2, empresa.cnpj)
            inserirEmpresa.setString(3, empresa.emailCorporativo)
            inserirEmpresa.setString(4, empresa.descricao)

            int idPais = buscaIdPais(empresa.pais) as int
            inserirEmpresa.setInt(5, idPais)

            inserirEmpresa.setInt(6, empresa.cep)
            inserirEmpresa.setString(7, empresa.senha)

            inserirEmpresa.execute()
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

    boolean inserirCurtidaCandidato(long cnpj, int idCandidato) {

        String sql = "INSERT INTO curtida_empresa(id_empresa, id_candidato) VALUES(?, ?)"

        try (Connection conexao = super.conectar()
             PreparedStatement inserirCurtidaEmpresa = conexao.prepareStatement(sql)) {

            int idEmpresa = buscaIdEmpresa(cnpj) as int

            inserirCurtidaEmpresa.setInt(1, idEmpresa)
            inserirCurtidaEmpresa.setInt(2, idCandidato)

            inserirCurtidaEmpresa.execute()
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

    List<Match> listarMatchsEmpresa(long cnpj) {

        String sql = "SELECT DISTINCT c.nome as nome_candidato, e.nome as nome_empresa " +
                "FROM curtida_candidato cc " +
                "JOIN candidatos c ON cc.id_candidato = c.id " +
                "JOIN curtida_empresa ce ON cc.id_candidato = ce.id_candidato AND cc.id_empresa = ce.id_empresa " +
                "JOIN empresas e ON ce.id_empresa = e.id " +
                "WHERE e.id = ?"

        try (Connection conexao = super.conectar()
             PreparedStatement matchs = conexao.prepareStatement(sql)) {

            int idEmpresa = buscaIdEmpresa(cnpj) as int

            matchs.setInt(1, idEmpresa)
            ResultSet resultSet = matchs.executeQuery()

            List<Match> listaMatchs = new ArrayList()

            while (resultSet.next()) {
                Match match = new Match()
                match.setNomeCandidato(resultSet.getString("nome_candidato"))
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
