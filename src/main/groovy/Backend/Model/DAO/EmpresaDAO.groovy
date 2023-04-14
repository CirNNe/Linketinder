package Backend.Model.DAO

import Backend.Model.DAO.Interface.EmpresaDAOInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.Entidade.Empresa
import Backend.Model.Entidade.Interface.EmpresaInterface
import Backend.Model.Entidade.Interface.MatchInterface
import Backend.Model.Entidade.Match
import groovy.sql.Sql
import java.sql.SQLException

class EmpresaDAO implements EmpresaDAOInterface{

    private GenericDAOInterface genericDAO
    Sql sql = Sql.newInstance(ConexaoBancoDados.conectar())

    EmpresaDAO(GenericDAOInterface genericDAO) {
        this.genericDAO = genericDAO
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
        String comandoSql = "SELECT e.id, e.nome, e.email, e.cnpj, e.cep, e.pais, e.descricao_empresa " +
                "FROM empresas AS e " +
                "WHERE e.cnpj = ? " +
                "GROUP BY e.id, e.nome, e.email, e.cnpj, e.cep, e.pais, e.descricao_empresa ORDER BY e.id ASC"
        try {
            EmpresaInterface empresa = new Empresa()
            sql.query(comandoSql, [cnpj]) {resultSet ->
                while (resultSet.next()) {
                    empresa.setId(resultSet.getInt("id"))
                    empresa.setNome(resultSet.getString("nome"))
                    empresa.setEmail(resultSet.getString("email"))
                    empresa.setCnpj(resultSet.getLong("cnpj"))
                    empresa.setCep(resultSet.getInt("cep"))
                    empresa.setPais(resultSet.getString("pais"))
                    empresa.setDescricao(resultSet.getString("descricao_empresa"))
                }
            }
            return empresa
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar encontrar a empresa no banco de dados: " + e)
        }
    }

    boolean insereDadosEmpresas(EmpresaInterface empresa) {
        String comandoSql = "INSERT INTO empresas(nome, cnpj, email, pais, cep, descricao_empresa, senha) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)"
        try {
            sql.execute(comandoSql, [empresa.nome, empresa.cnpj, empresa.email, empresa.pais,
                                     empresa.cep, empresa.descricao, empresa.senha])
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir dados da empresa no banco de daddos: " + e)
        }
    }

    boolean insereCurtidaACandidato(long cnpj, int idCandidato) {
        String comandoSql = "INSERT INTO curtida_empresa(id_empresa, id_candidato) VALUES(?, ?)"
        try {
            Integer idEmpresa = buscaIdEmpresa(cnpj)
            sql.execute(comandoSql, [idEmpresa, idCandidato])
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir curtida da empresa no banco de dados: " + e)
        }
    }

    List<Match> buscaMatchsEmpresa(long cnpj) {
        String comandSql = "SELECT DISTINCT c.nome as nome_candidato, e.nome as nome_empresa " +
                "FROM curtida_candidato cc " +
                "JOIN candidatos c ON cc.id_candidato = c.id " +
                "JOIN curtida_empresa ce ON cc.id_candidato = ce.id_candidato AND cc.id_empresa = ce.id_empresa " +
                "JOIN empresas e ON ce.id_empresa = e.id " +
                "WHERE e.id = ?"
        try {
            Integer idEmpresa = buscaIdEmpresa(cnpj)
            List<Match> listaMatchs = new ArrayList()
            sql.query(comandSql, [idEmpresa]) {resultSet ->
                while (resultSet.next()) {
                    MatchInterface match = new Match()
                    match.setNomeCandidato(resultSet.getString("nome_candidato"))
                    listaMatchs.add(match)
                }
            }
            return listaMatchs
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar ler os matchs da empresa: " + e)
        }
    }
}
