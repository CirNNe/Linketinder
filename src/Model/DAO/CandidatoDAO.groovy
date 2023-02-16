package Model.DAO

import Model.Entidade.Candidato

/**
 * Classe responsável por enviar os dados do candidato para a classe GenericDAO
 */
class CandidatoDAO extends GenericDAO {

    /**
     * Método envia o arquivo em que os dados serão escritos e e os parâmetros do Candidato
     * @param candidato: objeto do tipo candidato que será escrito no arquivo
     */
    boolean salvaDadosCandidato(Candidato candidato) {

        File candidatosTxt = new File('candidatos.txt')

        escreveNaTabela(candidatosTxt, candidato.nome, candidato.email, candidato.idade, candidato.cpf, candidato.cep,
                    candidato.descricaoPessoal, candidato.competencias)

        return true

    }

    /**
     * Método envia o arquivo que será lido e a lista onde serão instanciados os Candidatos
     * @param listaDeCandidatos: lista onde serão instanciados os candidatos
     */
    static leListaCandidatos(List listaDeCandidatos) {

        File candidatosTxt = new File('candidatos.txt')

        List recebeListaDeCandidatos = new ArrayList()

        leATabela(candidatosTxt, recebeListaDeCandidatos)

        listaDeCandidatos.addAll(recebeListaDeCandidatos)

    }
}
