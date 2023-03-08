package Backend.Model.DAO

import Backend.Model.Entidade.Candidato

/**
 * Classe responsável por enviar os dados do candidato para a classe GenericDAO
 */
class CandidatoDAO extends GenericDAO<Candidato> {

    /**
     * Método envia o arquivo em que os dados serão escritos e e os parâmetros do Candidato
     * @param candidato: objeto do tipo candidato que será escrito no arquivo
     */
    boolean salvaDadosCandidato(Candidato candidato) {

        try {
            File candidatosTxt = new File('candidatos.txt')
            super.escreveNaTabela(candidatosTxt, candidato.toString())
            return true
        } catch (Exception error) {
            println(error)
            return false
        }
    }

    /**
     * Método envia o arquivo que será lido e a lista onde serão instanciados os Candidatos
     * @param listaDeCandidatos: lista onde serão instanciados os candidatos
     */
    boolean leListaCandidatos(List listaDeCandidatos) {

        try {
            File candidatosTxt = new File('candidatos.txt')

            List recebeListaDeCandidatos = []

            super.leATabela(candidatosTxt, recebeListaDeCandidatos)

            listaDeCandidatos.addAll(recebeListaDeCandidatos)

            return true
        } catch (Exception error) {
            println("ERRO AO TENTAR LER A LISTA DE CANDIDATOS SALVOS! ERRO: " + error)
            return false
        }
    }
}
