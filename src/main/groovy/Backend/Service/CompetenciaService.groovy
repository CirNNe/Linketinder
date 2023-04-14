package Backend.Service

import Backend.Model.DAO.Interface.CompetenciaDAOInterface
import Backend.Model.Entidade.Interface.CompetenciaInterface
import Backend.Service.Interface.CompetenciaServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface

import java.sql.SQLException

class CompetenciaService implements CompetenciaServiceInterface {

    private CompetenciaDAOInterface competenciaDAO
    private ValidatorServiceInterface validatorService

    CompetenciaService(CompetenciaDAOInterface competenciaDAO, ValidatorServiceInterface validatorService) {
        this.competenciaDAO = competenciaDAO
        this.validatorService = validatorService
    }

    boolean recebeDadosNovaCompetencia(Integer id, long identificacao, List<CompetenciaInterface> listaCompetencias) {
        try {
            if(validatorService.validaDadosNovaCompetencia(listaCompetencias)) {
                filtraCompetenciaDaLista(id, identificacao, listaCompetencias)
            }
            return true
        } catch (Exception e) {
            throw new Exception("Erro ao tentar salvar os dados da nova competencia: " + e)
        }
    }

    boolean filtraCompetenciaDaLista(Integer id, long identificacao, List<CompetenciaInterface> listaCompetencias) {
        try {
            for(int posicao = 0; posicao < listaCompetencias.size(); posicao++) {
                CompetenciaInterface nome = listaCompetencias[posicao]
                salvaNovaCompetencia(id, identificacao, nome)
            }
            return true
        } catch (Exception e) {
            throw new Exception("Erro ao tentar filtrar as competências da lista recebida: " + e)
        }
    }

    boolean salvaNovaCompetencia(Integer id, long identificacao, CompetenciaInterface competencia) {
        try {
            String sql = ""
            if(validatorService.validaTipoUsuario(identificacao) == 1) {
                sql = "INSERT INTO competencias(nome, id_candidato) VALUES(?, ?)"
            } else if(validatorService.validaTipoUsuario(identificacao) == 2) {
                sql = "INSERT INTO competencias(nome, id_vaga) VALUES(?, ?)"
            }
            competenciaDAO.insereCompetencia(sql, id, competencia)
            return true
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar inserir competencia no banco de dados " + e)
        }
    }
}
