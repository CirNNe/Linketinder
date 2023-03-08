package Backend.Controller

import Backend.Model.DAO.CandidatoDAO
import Backend.Model.Entidade.Candidato
import Backend.Service.CandidatoService

/**
 * Classe usada para a comunicação entre a view e a classe CandidatoService
 */
class CandidatoController {

    CandidatoService candidatoService = new CandidatoService(new CandidatoDAO())

    /**
     * Recebe os dados do candidato passados pelo usuário na view e cria um objeto do tipo Candidato
     * @param nome: nome do candidato
     * @param email: email do candidato
     * @param idade: idade do candidato
     * @param cpf: cpf do candidato
     * @param cep: cep do candidato
     * @param descricaoPessoal: descrição pessoal do candidato
     * @param competencias: lista de competências do candidato
     */
    void recebeDados(String nome, email, int idade, long cpf, int cep, String descricaoPessoal, List competencias) {

        Candidato candidato = new Candidato()

        candidato.nome = nome
        candidato.email = email
        candidato.idade = idade
        candidato.cpf = cpf
        candidato.cep = cep
        candidato.descricaoPessoal = descricaoPessoal
        candidato.competencias = competencias

        candidatoService.validaDadosCadastroCandidato(candidato)

    }
    /**
    * Recebe a lista de candidatos formatada pela classe CandidatoService
    */
    void listaCandidatos() {
            candidatoService.formataLeituraCandidatos()
    }
}
