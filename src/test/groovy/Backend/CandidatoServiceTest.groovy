package Backend

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
        candidato.email = "email@email.com"
        candidato.dataNascimento = '07-11-1997'
        candidato.cpf = 70247122440
        candidato.cep = 54460465
        candidato.pais = 'Brasil'
        candidato.descricaoPessoal = "descrição"
        candidato.senha = 'Senha@senha01'
        List listaCompetencias = ["Python", "Java", "Groovy"]
        candidato.competencias = listaCompetencias

        Mockito.when(candidatoDAO.inserirDadosNaTabelaCanidatos(candidato)).thenReturn(true)

        assertTrue(candidatoService.validaDadosCadastroCandidato(candidato))

    }
}
