package Backend.Service

import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.Entidade.Interface.VagaInterface
import Backend.Service.Interface.VagaServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface

class VagaService implements VagaServiceInterface {

    private VagaDAOInterface vagaDAO
    private ValidatorServiceInterface validatorService

    VagaService(VagaDAOInterface vagaDAO, ValidatorServiceInterface validatorService) {
        this.vagaDAO = vagaDAO
        this.validatorService = validatorService
    }

    boolean salvaDadosNovaVaga(VagaInterface vaga) {
        try {
            if(validatorService.validaDadosNovaVaga(vaga)) {
            vagaDAO.insereDadosVagas(vaga)
            return true
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar salvar os dados da nova vaga: " + e)
        }
    }

    List<VagaInterface> recebeListaVagasEmpresa(long cnpj) {
        try {
            List<VagaInterface> lista = vagaDAO.buscaVagasDaEmpresa(cnpj)
            return lista
        } catch (Exception e) {
            throw new Exception("Erro ao tentar receber a lista de vagas da empresa: " + e)
        }
    }

    boolean exibeListaVagasEmpresa(long cnpj) {
        try {
            List<VagaInterface> lista = recebeListaVagasEmpresa(cnpj)
            for(int posicao = 0; posicao < lista.size(); posicao++) {
                println(lista[posicao])
                println('-' * 100)
            }
            return true
        } catch (Exception e) {
            throw new Exception("Erro ao tentar exibir a lista de vagas da empresa: " + e)
        }
    }

    List<VagaInterface> recebeListaVagasGerais() {
        try {
            List<VagaInterface> lista = vagaDAO.buscaVagasGerais()
            return lista
        } catch (Exception e) {
            throw new Exception("Erro ao tentar receber a lista de vagas gerais: " + e)
        }
    }

    boolean exibeListaVagasGerais() {
        try {
            List lista = recebeListaVagasGerais()
            for(int posicao = 0; posicao < lista.size(); posicao++) {
                println(lista[posicao])
                println('-' * 100)
            }
            return true
        } catch (Exception e) {
            throw new Exception("Erro ao tentar exibir lista de vagas gerais: " + e)
        }
    }
}
