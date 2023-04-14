package Backend.Service

import Backend.Model.DAO.EmpresaDAO
import Backend.Model.DAO.Interface.EmpresaDAOInterface
import Backend.Model.Entidade.Empresa
import Backend.Model.Entidade.Interface.EmpresaInterface
import Backend.Model.Entidade.Interface.MatchInterface
import Backend.Model.Entidade.Match
import Backend.Service.Interface.EmpresaServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class EmpresaServiceTest {

    EmpresaDAOInterface empresaDAO = mock(EmpresaDAO.class)
    ValidatorServiceInterface validatorService = mock(ValidatorService.class)
    EmpresaServiceInterface empresaService = new EmpresaService(empresaDAO, validatorService)

    @Test
    void salvaDadosNovaEmpresaTest() {

        EmpresaInterface empresa = new Empresa()
        empresa.nome = "ZG ZG"
        empresa.email = "email@email.com"
        empresa.cnpj = 12345678912345
        empresa.cep = 54460465
        empresa.pais = "Brasil"
        empresa.descricao = "Descricao"

        when(validatorService.validaDadosNovaEmpresa(empresa)).thenReturn(true)
        when(empresaDAO.insereDadosEmpresas(empresa)).thenReturn(true)

        boolean resultado = empresaService.salvaDadosNovaEmpresa(empresa)

        Assertions.assertTrue(resultado)
    }

    @Test
    void recebeDadosEmpresasTest() {

        EmpresaInterface empresa = new Empresa()
        empresa.nome = "ZG ZG"
        empresa.email = "email@email.com"
        empresa.cnpj = 12345678912345
        empresa.cep = 54460465
        empresa.pais = "Brasil"
        empresa.descricao = "Descricao"

        when(validatorService.validaCnpj(empresa.cnpj)).thenReturn(true)
        when(empresaDAO.buscaPerfilUnicoEmpresa(empresa.cnpj)).thenReturn(empresa)

        String resultado = empresaService.recebeDadosEmpresas(empresa.cnpj)

        Assertions.assertEquals(resultado, "ID: " + 0 + " - " + "Nome: " + "ZG ZG" + " - " +
                "E-mail: " + "email@email.com" + " - " + "CNPJ: " + 12345678912345 + " - " + "CEP: " + 54460465 + " - " +
                "País: " + "Brasil" + " - " + "Descrição: " + "Descricao")

    }

    @Test
    void formataListaMatchsEmpresaTest() {

        MatchInterface match1 = new Match()
        match1.nomeCandidato = "Higor"
        match1.nomeEmpresa = "ZG Solutions"

        List<MatchInterface> lista = new ArrayList<>()
        lista.add(match1)

        long cnpj = 12345678912345

        when(validatorService.validaCnpj(cnpj)).thenReturn(true)
        when(empresaService.recebeListaMatchsEmpresa(cnpj)).thenReturn(lista)

        List resultado = empresaService.formataListaMatchsEmpresa(cnpj)

        Assertions.assertEquals(resultado, ["Candidato: " + "Higor"])

    }

}
