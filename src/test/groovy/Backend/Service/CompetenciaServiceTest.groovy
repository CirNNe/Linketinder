package Backend.Service

import com.example.linketinderwebteste.Backend.Model.DAO.Interface.CompetenciaDAOInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Competencia
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CompetenciaInterface
import com.example.linketinderwebteste.Backend.Service.CompetenciaService
import com.example.linketinderwebteste.Backend.Service.Interface.CompetenciaServiceInterface
import com.example.linketinderwebteste.Backend.Service.Interface.ValidatorServiceInterface
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import static org.mockito.Mockito.mock

class CompetenciaServiceTest {

    CompetenciaDAOInterface competenciaDAO = mock(CompetenciaDAO.class)
    ValidatorServiceInterface validatorService = mock(ValidatorService.class)
    CompetenciaServiceInterface competenciaService = new CompetenciaService(competenciaDAO, validatorService)

    @Test
    void filtraCompetenciaDaLista() {

        Integer id = 1
        long identificacao = 11111111111
        CompetenciaInterface commpetencia = new Competencia()
        commpetencia.nome = "Java"
        List<CompetenciaInterface> listaCompetencias = new ArrayList<>()
        listaCompetencias.add(commpetencia)

        boolean resultado = competenciaService.filtraCompetenciaDaLista(id, identificacao, listaCompetencias)

        Assertions.assertTrue(resultado)

    }
}
