package Backend.Service

import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.DAO.VagaDAO
import Backend.Model.Entidade.Competencia
import Backend.Model.Entidade.Interface.VagaInterface
import Backend.Model.Entidade.Vaga
import Backend.Service.Interface.VagaServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface
import Backend.Util.Regex.RegexValidaDadosNovaVaga
import Backend.Util.Regex.RegexValidaDadosNovoUsuario
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class VagaServiceTest {

    VagaDAOInterface vagaDAO = mock(VagaDAO.class)
    ValidatorServiceInterface validatorService = mock(ValidatorService.class)
    RegexValidaDadosNovoUsuario regexUsuario = mock(RegexValidaDadosNovoUsuario.class)
    RegexValidaDadosNovaVaga regexVaga = mock(RegexValidaDadosNovaVaga.class)
    VagaServiceInterface vagaService = new VagaService(vagaDAO, validatorService, regexVaga, regexUsuario)

    @Test
    void exibeListaVagasEmpresaTest() {

        long cnpj = 12345678912345
        List<VagaInterface> lista = new ArrayList<>()
        VagaInterface vaga = new Vaga()
        vaga.nome = "Vaguinha"
        vaga.empresa = "ZG SOlutions"
        vaga.cnpj = cnpj
        vaga.pais = "Brasil"
        vaga.competencias = ["Java", "Groovy"] as List<Competencia>
        vaga.descricao = "Descricao"
        lista.add(vaga)

        when(vagaService.recebeListaVagasEmpresa(cnpj)).thenReturn(lista)

        List resultado = vagaService.exibeListaVagasEmpresa(cnpj)

        assertEquals(resultado, lista)
    }
}
