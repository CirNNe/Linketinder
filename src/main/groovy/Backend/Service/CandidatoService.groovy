package Backend.Service

import Backend.Model.DAO.Interface.CandidatoDAOInterface
import Backend.Model.Entidade.Interface.CandidatoInterface
import Backend.Model.Entidade.Interface.MatchInterface
import Backend.Service.Interface.CandidatoServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface

class CandidatoService implements CandidatoServiceInterface {

    private CandidatoDAOInterface candidatoDAO
    private ValidatorServiceInterface validatorService

    CandidatoService(CandidatoDAOInterface candidatoDAO, ValidatorServiceInterface validatorServiceInterface) {
        this.candidatoDAO = candidatoDAO
        this.validatorService = validatorServiceInterface
    }

    boolean salvaDadosNovoCandidato(CandidatoInterface candidato) {
        try {
            if(validatorService.validaDadosNovoCandidato(candidato)) {
                candidatoDAO.insereDadosCandidato(candidato)
                return true
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar salvar os dados do novo candidato: " + e)
        }
    }

    List<CandidatoInterface> recebeListaCandidatos() {
        try {
            List<CandidatoInterface> listaDeCandidatos = candidatoDAO.buscaListaCandidatos()
            return listaDeCandidatos
        } catch (Exception e) {
            throw new Exception("Erro ao tentar receber a lista de candidatos: " + e)
        }
    }

    List formataLeituraListaCandidatos() {
        List<CandidatoInterface> lista = recebeListaCandidatos()
        List listaFormatada = new ArrayList()

        for(int posicao = 0; posicao < lista.size(); posicao++) {
            int id = lista[posicao]['id']
            String nome = lista[posicao]['nome'] = 'Anônimo'
            String pais = lista[posicao]['pais']
            String descricao = lista[posicao]['descricaoPessoal']
            List competencias = lista[posicao]['competencias'] as List
            listaFormatada.addAll(["ID: " + id + ' - ' + "Nome: " + nome + ' - ' + "País: " + pais + ' - ' +
                                    "Descrição: " + descricao + ' - ' +
                                    "Competências: " + competencias.toString().replaceAll(/[\[\]{}]/, '')])
        }
        return listaFormatada
    }

    List exibeListaCandidatos() {
        try {
            List lista = formataLeituraListaCandidatos()
            return lista
        } catch (Exception e) {
            throw new Exception("Erro ao tentar exibir a lista de candidatos: " + e)
        }
    }

    CandidatoInterface recebeDadosCandidato(long cpf){
        try {
            if(validatorService.validaCpf(cpf)) {
                CandidatoInterface candidato = candidatoDAO.buscaPerfilUnicoCandidato(cpf)
                return candidato
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar receber os dados do perfil do candidato: " + e)
        }
    }

    CandidatoInterface exibirPerfilCandidato(long cpf) {
        try {
            CandidatoInterface candidato = recebeDadosCandidato(cpf)
            return candidato
        } catch (Exception e) {
            throw new Exception("Erro ao tentar exibir os dados do candidato: " + e)
        }
    }

    boolean salvaCurtidaDoCadidato(long cpf, int idVaga) {
        try {
            if(validatorService.validaCpf(cpf) && validatorService.validaIdVaga(idVaga)) {
                candidatoDAO.insereCurtiAVaga(cpf, idVaga)
                return true
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar realizar a curtidada à vaga: " + e)
        }
        return false
    } /** TODO: revisar após refatorar Vagas **/

    List<MatchInterface> recebeListaMatchsCandidato(long cpf) {
        try {
            if(validatorService.validaCpf(cpf)) {
                List<MatchInterface> lista = candidatoDAO.buscaMatchsCandidato(cpf)
                return lista
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar receber a lista de matchs do candidato: " + e)
        }
    }

    List<MatchInterface> formataListaMatchsCandidato(long cpf) {
        try {
            List<MatchInterface> lista =  recebeListaMatchsCandidato(cpf)
            List listaFormatada = new ArrayList()
            for(int posicao = 0; posicao < lista.size(); posicao++) {
                listaFormatada.add("Empresa: " + lista[posicao]['nomeEmpresa'])
            }
            return listaFormatada
        } catch (Exception e) {
            throw new Exception("Erro ao tentar formatar lista de matchs do candidato: " + e)
        }
    }

    List exibeListaMatchsCandidato(long cpf) {
        try {
            List lista = formataListaMatchsCandidato(cpf)
            return lista
        } catch (Exception e) {
            throw new Exception("Erro ao tentar exibir a lista de matchs: " + e)
        }
    }
}
