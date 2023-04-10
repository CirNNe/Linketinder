package Backend.Service

import Backend.Model.DAO.CandidatoDAO
import Backend.Model.DAO.EmpresaDAO
import Backend.Model.DAO.Interface.CandidatoDAOInterface
import Backend.Model.DAO.Interface.EmpresaDAOInterface
import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.DAO.VagaDAO
import Backend.Service.Interface.ValidatorServiceInterface
import Backend.Util.Regex.RegexValidaDadosNovaVaga
import Backend.Util.Regex.RegexValidaDadosNovoUsuario
import org.junit.jupiter.api.Test
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class ValidatorServiceTest {

    RegexValidaDadosNovoUsuario regexUsuario = mock(RegexValidaDadosNovoUsuario.class)
    RegexValidaDadosNovaVaga regexVaga = mock(RegexValidaDadosNovaVaga.class)
    VagaDAOInterface vagaDAO = mock(VagaDAO.class)
    CandidatoDAOInterface candidatoDAO = mock(CandidatoDAO.class)
    EmpresaDAOInterface empresaDAO = mock(EmpresaDAO.class)
    ValidatorServiceInterface validatorService = new ValidatorService(regexUsuario, regexVaga, vagaDAO, candidatoDAO, empresaDAO)

    @Test
    void validaDadosNovoCandidatoTest() {

    }

}
