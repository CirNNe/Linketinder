package Backend.Test

import Backend.Model.DAO.CandidatoDAO
import Backend.Model.Entidade.Candidato
import Backend.Service.CandidatoService
import org.junit.jupiter.api.Test
import org.mockito.Mockito

import static org.junit.jupiter.api.Assertions.assertTrue

class CandidatoServiceTest {

    CandidatoDAO candidatoDAO = Mockito.mock(CandidatoDAO.class)

    CandidatoService candidatoService = new CandidatoService(candidatoDAO)

    @Test
    void testSalvarCandidatoValidado() {

        Candidato candidato = new Candidato()
        candidato.nome = "Higor"
        candidato.email = "email"
        candidato.idade = 25
        candidato.cpf = 70247122440
        candidato.cep = 54460465
        candidato.descricaoPessoal = "descrição"
        List listaCompetencias = ["Python", "Java", "Groovy"]
        candidato.competencias = listaCompetencias

        Mockito.when(candidatoDAO.salvaDadosCandidato(candidato)).thenReturn(true)

        assertTrue(candidatoService.validaDadosCadastroCandidato(candidato))

    }
}
