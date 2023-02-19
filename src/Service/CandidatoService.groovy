package Service

import Model.Entidade.Candidato
import Model.DAO.CandidatoDAO

/**
 * Classe responsável pela regra de negócio da aplicação
 */
class CandidatoService {

    CandidatoDAO candidatoDAO

    CandidatoService(CandidatoDAO candidatoDAO) {
        this.candidatoDAO = candidatoDAO
    }

    /**
     * Método responsável por receber e validar o objeto do tipo Candidato
     * @param candidato: objeto que será tratado e validado
     */

    boolean validaDadosCadastroCandidato(Candidato candidato) {
        if (!candidato.nome.matches("[A-Za-z]*") ||
                                    candidato.email.length() <= 0 ||
                                    candidato.idade > 100 ||
                                    candidato.idade < 18 ||
                                    candidato.cpf <= 0 ||
                                    candidato.cep <= 0){

            println "OS VALORES PASSADOS SÃO INVÁLIDOS! TENTE NOVAMENTE"
            return false
        }
         this.candidatoDAO.salvaDadosCandidato(candidato)
         return true
    }

    /**
     * Método responsável por receber e formatar a lista contendo os candidatos
     */
    void formataLeituraCandidatos() {

        List listaDeCandidatos = new ArrayList()

        this.candidatoDAO.leListaCandidatos(listaDeCandidatos)

        for(candidato in listaDeCandidatos) {
            println(candidato)
        }
    }
}
