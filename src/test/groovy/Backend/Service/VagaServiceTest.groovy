package Backend.Service

import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.DAO.VagaDAO
import Backend.Model.Entidade.Competencia
import Backend.Model.Entidade.Interface.VagaInterface
import Backend.Model.Entidade.Vaga
import Backend.Service.Interface.VagaServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertTrue
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class VagaServiceTest {

    VagaDAOInterface vagaDAO = mock(VagaDAO.class)
    ValidatorServiceInterface validatorService = mock(ValidatorService.class)
    VagaServiceInterface vagaService = new VagaService(vagaDAO, validatorService)

    @Test
    void salvaDadosNovaVagaTest() {

        VagaInterface vaga = new Vaga()
        vaga.nome = "Tester Jr"
        vaga.empresa = "ZG ZG"
        vaga.pais = "Brasil"
        vaga.descricao = "Descricao"
        vaga.competencias = ["Java", "Groovy"] as List<Competencia>
        long cnpj = 12345678912345

        when(validatorService.validaDadosNovaVaga(vaga)).thenReturn(true)
        when(vagaDAO.insereDadosVagas(cnpj, vaga)).thenReturn(true)

        boolean resultado = vagaService.salvaDadosNovaVaga(cnpj, vaga)

        assertTrue(resultado)
    }
}
