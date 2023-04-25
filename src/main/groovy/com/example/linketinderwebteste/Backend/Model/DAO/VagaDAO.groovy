package com.example.linketinderwebteste.Backend.Model.DAO

import com.example.linketinderwebteste.Backend.Model.DAO.Interface.EmpresaDAOInterface
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.GenericDAOInterface
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.VagaDAOInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.VagaInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Vaga
import groovy.sql.Sql

import java.sql.SQLException

class VagaDAO implements VagaDAOInterface {

    private GenericDAOInterface genericDAO
    private EmpresaDAOInterface empresaDAO

    Sql sql = Sql.newInstance(ConexaoBancoDados.conectar())

    VagaDAO(GenericDAOInterface genericDAO, EmpresaDAOInterface empresaDAO) {
        this.genericDAO = genericDAO
        this.empresaDAO = empresaDAO
    }

    boolean insereDadosVagas(VagaInterface vaga) {
        String comandSql = "INSERT INTO vagas(nome, pais, descricao_vaga, id_empresa) " +
                "VALUES(?, ?, ?, ?)"
        try {
            Integer idEmpresa = empresaDAO.buscaIdEmpresa(vaga.cnpj)
            sql.execute(comandSql, [vaga.nome, vaga.pais, vaga.descricao, idEmpresa])
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir vaga no banco de dados: " + e)
        }
    }

    List<VagaInterface> buscaVagasDaEmpresa(long cnpj) {
        String comandoSql = "SELECT v.id, v.nome, e.nome AS empresa_nome, v.pais, v.descricao_vaga, array_agg(comp.nome) as competencias " +
                "FROM vagas AS v " +
                "INNER JOIN empresas AS e ON e.id = v.id_empresa " +
                "INNER JOIN competencias AS comp ON comp.id_vaga = v.id " +
                "WHERE e.cnpj = ? " +
                "GROUP BY v.id, v.nome, e.nome, v.pais, v.descricao_vaga"
        try {
            List<VagaInterface> listaDeVagas = new ArrayList<>()
            sql.query(comandoSql, [cnpj]) {resultSet ->
                while (resultSet.next()) {
                    VagaInterface vaga = new Vaga()
                    vaga.setId(resultSet.getInt("id"))
                    vaga.setNome(resultSet.getString("nome"))
                    vaga.setEmpresa(resultSet.getString("empresa_nome"))
                    vaga.setPais(resultSet.getString('pais'))
                    vaga.setDescricao(resultSet.getString("descricao_vaga"))
                    vaga.setCompetencias(Arrays.asList(resultSet.getArray("competencias")))
                    listaDeVagas.add(vaga)
                }
            }
            return listaDeVagas
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar buscar as vagas da empresa: " + e)
        }
    }

    List<VagaInterface> buscaVagasGerais() {
        String comandoSql = "SELECT v.id, v.nome, e.nome AS nome_empresa, v.pais, v.descricao_vaga, array_agg(comp.nome) as competencias " +
                "FROM vagas AS v " +
                "INNER JOIN empresas AS e ON e.id = v.id_empresa " +
                "INNER JOIN competencias AS comp ON comp.id_vaga = v.id " +
                "GROUP BY v.id, v.nome, e.nome, v.pais, v.descricao_vaga"
        try {
            List<VagaInterface> listaDeVagas = new ArrayList<>()
            sql.query(comandoSql) {resultSet ->
                while (resultSet.next()) {
                    VagaInterface vaga = new Vaga()
                    vaga.setId(resultSet.getInt("id"))
                    vaga.setNome(resultSet.getString("nome"))
                    vaga.setEmpresa(resultSet.getString("nome_empresa"))
                    vaga.setPais(resultSet.getString('pais'))
                    vaga.setDescricao(resultSet.getString("descricao_vaga"))
                    vaga.setCompetencias(Arrays.asList(resultSet.getArray("competencias")))
                    listaDeVagas.add(vaga)
                }
            }
            return listaDeVagas
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar buscar as vagas gerais: " + e)
        }
    }

    Integer buscaIdEmpresaResponsavelVaga(int idVaga) {
        String comandoSql = "SELECT e.id FROM empresas AS e, vagas AS v WHERE v.id = ? AND e.id = v.id_empresa"
        try {
            List<Integer> idDaVaga = new ArrayList()
            sql.query(comandoSql, [idVaga]) {resultSet ->
                while (resultSet.next()) {
                    Integer resultadoIdUsuario =  resultSet.getInt("id")
                    idDaVaga.add(resultadoIdUsuario)
                }
            }
            return idDaVaga[0] as Integer
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar buscar o id da empresa responsável pela vaga: " + e)
        }
    }

    Integer buscaIdVaga(String nomeVaga) {
        String comandoSql = "SELECT id FROM vagas WHERE nome = ?"
        try {
            List<Integer> idVaga = new ArrayList()
            sql.query(comandoSql, [nomeVaga]) {resultSet ->
                while (resultSet.next()) {
                    int resultadoIdVaga =  resultSet.getInt("id")
                    idVaga.add(resultadoIdVaga)
                }
            }
            return idVaga[0] as Integer
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar buscar id da vaga: " + e)
        }
    }
}
