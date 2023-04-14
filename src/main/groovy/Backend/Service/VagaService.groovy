package Backend.Service

import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.Entidade.Interface.VagaInterface
import Backend.Service.Interface.VagaServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface
import Backend.Util.Regex.RegexValidaDadosNovaVaga
import Backend.Util.Regex.RegexValidaDadosNovoUsuario

class VagaService implements VagaServiceInterface {

    private VagaDAOInterface vagaDAO
    private ValidatorServiceInterface validatorService
    private RegexValidaDadosNovaVaga regexVaga
    private RegexValidaDadosNovoUsuario regexUsuario

    VagaService(VagaDAOInterface vagaDAO, ValidatorServiceInterface validatorService, RegexValidaDadosNovaVaga regexVaga,
                RegexValidaDadosNovoUsuario regexUsuario) {
        this.vagaDAO = vagaDAO
        this.validatorService = validatorService
        this.regexVaga = regexVaga
        this.regexUsuario = regexUsuario
    }

    boolean validaDadosNovaVaga(VagaInterface vaga) {
        try {
            if(vaga.nome.matches(regexVaga.getNomeVaga()) &&
                    vaga.cnpj.toString().replaceAll("[^0-9]", "").matches(regexUsuario.getCnpj()) &&
                    !vaga.descricao.isEmpty() &&
                    vaga.pais.matches(regexUsuario.getPais())) {
                return true
            } else {
                println("Dados da vaga inválidos, por favor, tente novamente!")
                return false
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar validar os dados da nova vaga: " + e)
        }
    }

    boolean salvaDadosNovaVaga(VagaInterface vaga) {
        try {
            if(validaDadosNovaVaga(vaga)){
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

    List exibeListaVagasEmpresa(long cnpj) {
        try {
            List<VagaInterface> lista = recebeListaVagasEmpresa(cnpj)
            return lista
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

    List exibeListaVagasGerais() {
        try {
            List lista = recebeListaVagasGerais()
            return lista
        } catch (Exception e) {
            throw new Exception("Erro ao tentar exibir lista de vagas gerais: " + e)
        }
    }
}
