package Backend.Service

import Backend.Model.DAO.CompetenciaDAO
import Backend.Model.DAO.Interface.CompetenciaDAOInterface
import Backend.Model.Entidade.Competencia
import Backend.Model.Entidade.Interface.CompetenciaInterface
import Backend.Service.Interface.CompetenciaServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class CompetenciaServiceTest {

    CompetenciaDAOInterface competenciaDAO = mock(CompetenciaDAO.class)
    ValidatorServiceInterface validatorService = mock(ValidatorService.class)
    CompetenciaServiceInterface competenciaService = new CompetenciaService(competenciaDAO, validatorService)

    @Test
    void salvaNovaCompetencia() {

        List<CompetenciaInterface> lista = new ArrayList<>()
        CompetenciaInterface competencia = new Competencia()
        competencia.nome = "Java"
        lista.add(competencia)
        Integer idVaga = 1
        long identificacao = 70247122440

        when(validatorService.validaDadosNovaCompetencia(lista)).thenReturn(true)
        when(competenciaDAO.insereCompetencia(idVaga, identificacao, competencia.nome as CompetenciaInterface)).thenReturn(true)

        boolean resultado = competenciaService.salvaNovaCompetencia(idVaga, identificacao, lista)

        Assertions.assertTrue(resultado)
    }

}
