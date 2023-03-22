package Backend.Model.DAO

import Backend.Model.Entidade.Competencia
import Backend.Model.Entidade.Vaga
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class VagaDAO extends GenericDAO<Vaga>{

    EmpresaDAO empresaDAO = new EmpresaDAO()

    boolean inserirDadosNaTabelaVagas(long cnpj, Vaga vaga) {

        String sql = "INSERT INTO vagas(nome, descricao_vaga, id_empresa, id_pais) " +
                "VALUES(?, ?, ?, ?)"

        try (Connection conexao = super.conectar()
             PreparedStatement inserirVaga = conexao.prepareStatement(sql)) {

            int id = empresaDAO.buscaIdEmpresa(cnpj) as int
            int idPais = buscaIdPais(vaga.pais) as int

            inserirVaga.setString(1, vaga.nome)
            inserirVaga.setString(2, vaga.descricao)
            inserirVaga.setInt(3, id)
            inserirVaga.setInt(4, idPais)

            inserirVaga.execute()
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

    boolean inserirDadosNaTebelaCompetenciasVaga(long cnpj, Competencia competencia) {

        try {

            int id = buscaIdVaga(cnpj) as int

            String sql = "INSERT INTO competencias(nome, id_vagas) VALUES(?, ?)"

            return super.inserirDadosNaTebelaCompetencias(sql, id, competencia)

        } catch (Exception e) {
            println("Erro ao tentar completar o cadastro. " + e)
            return null
        }
    }

    List<Vaga> leVagasDaEmpresa(long cnpj) {

        String sql = "SELECT v.id, v.nome, v.descricao_vaga, e.nome AS empresa_nome, p.nome AS pais, array_agg(comp.nome) as competencias " +
                "FROM vagas AS v " +
                "INNER JOIN empresas AS e ON e.id = v.id_empresa " +
                "INNER JOIN pais AS p ON v.id_pais = p.id " +
                "INNER JOIN competencias AS comp ON comp.id_vagas = v.id " +
                "WHERE e.cnpj = ? " +
                "GROUP BY v.id, v.nome, v.descricao_vaga, e.nome, p.nome"

        try (Connection conexao = super.conectar()
             PreparedStatement vagas = conexao.prepareStatement(sql)) {

            vagas.setLong(1, cnpj)

            ResultSet resultSet = vagas.executeQuery()

            List<Vaga> listaDeVagas = new ArrayList<>()

            while (resultSet.next()) {
                Vaga vaga = new Vaga()
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

    List<Vaga> leVagasGerais() {

        String sql = "SELECT v.id, v.nome, v.descricao_vaga, e.nome AS nome_empresa, p.nome AS pais, array_agg(comp.nome) as competencias " +
                "FROM vagas AS v " +
                "INNER JOIN empresas AS e ON e.id = v.id_empresa " +
                "INNER JOIN pais AS p ON v.id_pais = p.id " +
                "INNER JOIN competencias AS comp ON comp.id_vagas = v.id " +
                "GROUP BY v.id, v.nome, v.descricao_vaga, e.nome, p.nome"

        try (Connection conexao = super.conectar()
             PreparedStatement vagas = conexao.prepareStatement(sql)
             ResultSet resultSet = vagas.executeQuery()) {

            List<Vaga> listaDeVagas = new ArrayList<>()

            while (resultSet.next()) {
                Vaga vaga = new Vaga()
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

    Object buscaIdEmpresaResponsavelVaga(int idVaga) {

        String sql = "SELECT e.id FROM empresas AS e, vagas AS v WHERE v.id = ? AND e.id = v.id_empresa"

        try (Connection conexao = conectar()
             PreparedStatement buscarIdEmpresa = conexao.prepareStatement(sql)) {

            buscarIdEmpresa.setInt(1, idVaga)

            ResultSet resultSet = buscarIdEmpresa.executeQuery()

            List<Integer> idDaVaga = new ArrayList()

            while (resultSet.next()) {
                int resultadoIdUsuario =  resultSet.getInt("id")
                idDaVaga.add(resultadoIdUsuario)
            }
            return idDaVaga[0] as int
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

    Object buscaIdVaga(long cnpj) {

        int idEmpresa = empresaDAO.buscaIdEmpresa(cnpj) as int

        String sql = "SELECT v.id FROM vagas AS v, empresas AS e WHERE v.id_empresa = ?"

        try (Connection conexao = conectar()
             PreparedStatement buscarIdVaga = conexao.prepareStatement(sql)) {

            buscarIdVaga.setInt(1, idEmpresa)

            ResultSet resultSet = buscarIdVaga.executeQuery()

            List<Integer> idVaga = new ArrayList()

            while (resultSet.next()) {
                int resultadoIdUsuario =  resultSet.getInt("id")
                idVaga.add(resultadoIdUsuario)
            }
            return idVaga[0]
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
