package Controller

import Model.Entidade.Candidato
import Model.Service.CandidatoService

/**
 * Classe usada para a comunicação entre a view e a classe CandidatoService
 */
class CandidatoController {

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
    static recebeDados(String nome, email, int idade, long cpf, int cep, String descricaoPessoal, List competencias) {

        Candidato candidato = new Candidato()

        candidato.nome = nome
        candidato.email = email
        candidato.idade = idade
        candidato.cpf = cpf
        candidato.cep = cep
        candidato.descricaoPessoal = descricaoPessoal
        candidato.competencias = competencias

        CandidatoService.validaDadosCadastroCandidato(candidato)

    }
    /**
    * Recebe a lista de candidatos formatada pela classe CandidatoService
    */
    static listaCandidatos() {
        CandidatoService.formataLeituraCandidatos()
    }

}
