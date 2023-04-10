package Backend.Service

import Backend.Model.DAO.Interface.CompetenciaDAOInterface
import Backend.Model.Entidade.Interface.CompetenciaInterface
import Backend.Service.Interface.CompetenciaServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface

class CompetenciaService implements CompetenciaServiceInterface {

    private CompetenciaDAOInterface competenciaDAO
    private ValidatorServiceInterface validatorService

    CompetenciaService(CompetenciaDAOInterface competenciaDAO, ValidatorServiceInterface validatorService) {
        this.competenciaDAO = competenciaDAO
        this.validatorService = validatorService
    }

    boolean salvaNovaCompetencia(Integer id, long identificacao, List<CompetenciaInterface> listaCompetencias) {
        try {
            if(validatorService.validaDadosNovaCompetencia(listaCompetencias)) {
                for(int posicao = 0; posicao < listaCompetencias.size(); posicao++) {
                    CompetenciaInterface nome = listaCompetencias[posicao]
                    competenciaDAO.insereCompetencia(id, identificacao, nome)
                }
                return true
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar salvar os dados da nova competencia: " + e)
        }
    }
}
