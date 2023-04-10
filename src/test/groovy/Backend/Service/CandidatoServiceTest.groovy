package Backend.Service

import Backend.Model.DAO.CandidatoDAO
import Backend.Model.DAO.Interface.CandidatoDAOInterface
import Backend.Model.Entidade.Candidato
import Backend.Model.Entidade.Competencia
import Backend.Model.Entidade.Interface.CandidatoInterface
import Backend.Service.Interface.CandidatoServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class CandidatoServiceTest {

    CandidatoDAOInterface candidatoDAO = mock(CandidatoDAO.class)
    ValidatorServiceInterface validatorService = mock(ValidatorService.class)
    CandidatoServiceInterface candidatoService = new CandidatoService(candidatoDAO, validatorService)

    @Test
    void salvaDadosNovoCandidatoTest() {

        CandidatoInterface candidato = new Candidato()
        candidato.nome = "Higor"
        candidato.email = "email@email.com"
        candidato.dataNascimento = "07-11-1997"
        candidato.cpf = 70247122440
        candidato.cep = 54460465
        candidato.pais = "Brasil"
        candidato.descricaoPessoal = "Descricao"
        candidato.competencias = ['Java', 'Groovy'] as List<Competencia>

        when(validatorService.validaDadosNovoCandidato(candidato)).thenReturn(true)
        when(candidatoDAO.insereDadosCandidato(candidato)).thenReturn(true)

        boolean resultado = candidatoService.salvaDadosNovoCandidato(candidato)

        Assertions.assertTrue(resultado)
    }

    @Test
    void recebeListaCandidatos() {
        List<CandidatoInterface> lista = new ArrayList<>()

        CandidatoInterface candidato1 = new Candidato()
        candidato1.nome = "Higor"
        candidato1.email = "email@email.com"
        candidato1.dataNascimento = "07-11-1997"
        candidato1.cpf = 70247122440
        candidato1.cep = 54460465
        candidato1.pais = "Brasil"
        candidato1.descricaoPessoal = "Descricao"
        candidato1.competencias = ['Java', 'Groovy'] as List<Competencia>

        CandidatoInterface candidato2 = new Candidato()
        candidato2.nome = "Higor"
        candidato2.email = "email@email.com"
        candidato2.dataNascimento = "07-11-1997"
        candidato2.cpf = 70247122440
        candidato2.cep = 54460465
        candidato2.pais = "Brasil"
        candidato2.descricaoPessoal = "Descricao"
        candidato2.competencias = ['Java', 'Groovy'] as List<Competencia>

        lista.add(candidato1)
        lista.add(candidato2)

        when(candidatoDAO.buscaListaCandidatos()).thenReturn(lista)

        Assertions.assertEquals(lista, candidatoDAO.buscaListaCandidatos())
    }

    @Test
    void formataLeituraListaCandidatos() {
        List<CandidatoInterface> lista = new ArrayList<>()

        CandidatoInterface candidato1 = new Candidato()
        candidato1.nome = "Higor"
        candidato1.email = "email@email.com"
        candidato1.dataNascimento = "07-11-1997"
        candidato1.cpf = 70247122440
        candidato1.cep = 54460465
        candidato1.pais = "Brasil"
        candidato1.descricaoPessoal = "Descricao"
        candidato1.competencias = ['Java', 'Groovy'] as List<Competencia>

        CandidatoInterface candidato2 = new Candidato()
        candidato2.nome = "Higor Cirne"
        candidato2.email = "cirne@email.com"
        candidato2.dataNascimento = "07-11-1997"
        candidato2.cpf = 70247122440
        candidato2.cep = 54460465
        candidato2.pais = "Brasil"
        candidato2.descricaoPessoal = "Descricao"
        candidato2.competencias = ['TypeScript', 'Gradle'] as List<Competencia>

        lista.add(candidato1)
        lista.add(candidato2)

        when(candidatoService.recebeListaCandidatos()).thenReturn(lista)

        List<CandidatoInterface> resultado = candidatoService.formataLeituraListaCandidatos()
        List listaExemplo = [["ID: " + 0, "Nome: " + 'Anônimo', "País: " + 'Brasil', "Descrição: " + 'Descricao',
                              "Competências: " + ['Java', 'Groovy'].toString().replaceAll(/[\[\]{}]/, '')],
                             ["ID: " + 0, "Nome: " + 'Anônimo', "País: " + 'Brasil', "Descrição: " + 'Descricao',
                              "Competências: " + ['TypeScript', 'Gradle'].toString().replaceAll(/[\[\]{}]/, '')]]

        Assertions.assertEquals(resultado, listaExemplo)
    }

    @Test
    void salvaCurtidaDoCadidato() {
        long cpf = 70247122440
        Integer idVaga = 1

        when(validatorService.validaCpf(cpf)).thenReturn(true)
        when(validatorService.validaIdVaga(idVaga)).thenReturn(true)
        when(candidatoDAO.insereCurtiAVaga(cpf, idVaga)).thenReturn(true)

        boolean resultado = candidatoService.salvaCurtidaDoCadidato(cpf, idVaga)

        Assertions.assertTrue(resultado)
    }
}

