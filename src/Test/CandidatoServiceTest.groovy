package Test

import Model.DAO.CandidatoDAO
import Model.Entidade.Candidato
import Model.Service.CandidatoService
import org.mockito.Mockito
import static org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CandidatoServiceTest {

    CandidatoService candidatoService = new CandidatoService()

    CandidatoDAO candidatoDAO = Mockito.mock(CandidatoDAO.class) // mocka a classe CandidatoDAO

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

        Mockito.when(candidatoDAO.salvaDadosCandidato(candidato)).thenReturn(true) // emula o método de salvar do
                                                                                      // CandidatoDAO

        assertTrue(candidatoService.validaDadosCadastroCandidato(candidato, candidatoDAO)) // verifica se o método de
                                                                                           // validação do candidatoService
                                                                                           // e o método de salvar do
                                                                                           // candidatoDAO retorna true

    }
}
