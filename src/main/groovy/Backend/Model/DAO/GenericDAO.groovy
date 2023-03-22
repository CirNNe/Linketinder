package Backend.Model.DAO

import Backend.Model.Entidade.Competencia
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class GenericDAO<T> {

    Connection conectar() {
        Properties propriedades = new Properties()
        propriedades.setProperty("user", "higor") as Connection
        propriedades.setProperty("password", "4deNovembro") as Connection
        propriedades.setProperty("ssl", "false") as Connection
        String ULR_SERVIDOR = "jdbc:postgresql://localhost:5432/linketinder"

        try {
            return DriverManager.getConnection(ULR_SERVIDOR, propriedades)
        } catch (Exception e) {
            e.printStackTrace()
            if(e instanceof ClassNotFoundException) {
                println("Verifique o drive de conexão.")
            } else {
                println("Verifique se o servidor está ativo.")
            }
            System.exit(-42)
            return null
        }
    }

    boolean inserirDadosNaTebelaCompetencias(String sql, int id, Competencia competencia) {

        try(Connection conexao = conectar()
            PreparedStatement inserirCompetencia = conexao.prepareStatement(sql)) {

            inserirCompetencia.setString(1, competencia.nome)
            inserirCompetencia.setLong(2, id)

            inserirCompetencia.execute()
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

    Object buscaIdPais(String pais) {

        String sql = "SELECT id FROM pais WHERE nome = ?"

        try (Connection conexao = conectar()
             PreparedStatement buscarId = conexao.prepareStatement(sql)
        ) {

            buscarId.setString(1, pais)
            ResultSet resultSet = buscarId.executeQuery()

            List<Integer> idPais = new ArrayList()

            while (resultSet.next()) {
                int resultadoIdPais =  resultSet.getInt("id")
                idPais.add(resultadoIdPais)
            }
            return idPais[0] as int
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

    Object buscaIdUsuario(String sql, long identificacao) {

        try (Connection conexao = conectar()
             PreparedStatement buscarId = conexao.prepareStatement(sql)) {

            buscarId.setLong(1, identificacao)

            ResultSet resultSet = buscarId.executeQuery()

            List<Integer> idUsuario = new ArrayList()

            while (resultSet.next()) {
                int resultadoIdUsuario =  resultSet.getInt("id")
                idUsuario.add(resultadoIdUsuario)
            }
            return idUsuario[0]
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
