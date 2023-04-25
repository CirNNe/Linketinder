package Backend.Service

import com.example.linketinderwebteste.Backend.Model.DAO.Interface.CandidatoDAOInterface
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.EmpresaDAOInterface
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.VagaDAOInterface
import com.example.linketinderwebteste.Backend.Service.Interface.ValidatorServiceInterface
import com.example.linketinderwebteste.Backend.Service.ValidatorService
import com.example.linketinderwebteste.Backend.Util.Regex.RegexValidaDadosNovaVaga
import com.example.linketinderwebteste.Backend.Util.Regex.RegexValidaDadosNovoUsuario
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import static org.mockito.Mockito.mock

class ValidatorServiceTest {

    RegexValidaDadosNovoUsuario regexUsuario = mock(RegexValidaDadosNovoUsuario.class)
    RegexValidaDadosNovaVaga regexVaga = mock(RegexValidaDadosNovaVaga.class)
    VagaDAOInterface vagaDAO = mock(VagaDAO.class)
    CandidatoDAOInterface candidatoDAO = mock(CandidatoDAO.class)
    EmpresaDAOInterface empresaDAO = mock(EmpresaDAO.class)
    ValidatorServiceInterface validatorService = new ValidatorService(regexUsuario, regexVaga, vagaDAO, candidatoDAO,
                                                                        empresaDAO)

    @Test
    void validaTipoUsuarioTest() {

        long identificacao = 12345678912345

        Integer tipoUsuario = validatorService.validaTipoUsuario(identificacao)

        Assertions.assertEquals(2, tipoUsuario)

    }

    @Test
    void validaCpfTest() {
        long cpf = 70247122440

        boolean resultado = validatorService.validaCpf(cpf)

        Assertions.assertTrue(resultado)
    }

    @Test
    void validaCnpjTest() {
        long cnpj = 12345678912345

        boolean resultado = validatorService.validaCnpj(cnpj)

        Assertions.assertTrue(resultado)
    }
}
