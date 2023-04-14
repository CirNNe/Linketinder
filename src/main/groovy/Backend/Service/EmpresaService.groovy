package Backend.Service

import Backend.Model.DAO.Interface.EmpresaDAOInterface
import Backend.Model.Entidade.Interface.EmpresaInterface
import Backend.Model.Entidade.Interface.MatchInterface
import Backend.Service.Interface.EmpresaServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface

class EmpresaService implements EmpresaServiceInterface {

    private EmpresaDAOInterface empresaDAO
    private ValidatorServiceInterface validatorService

    EmpresaService(EmpresaDAOInterface empresaDAO, ValidatorServiceInterface validatorService) {
        this.empresaDAO = empresaDAO
        this.validatorService = validatorService
    }

    boolean salvaDadosNovaEmpresa(EmpresaInterface empresa) {
        try {
            if(validatorService.validaDadosNovaEmpresa(empresa)) {
                empresaDAO.insereDadosEmpresas(empresa)
                return true
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar salvar dados da nova empresa: " + e)
        }
    }

    EmpresaInterface recebeDadosEmpresas(long cnpj) {
        try {
            if(validatorService.validaCnpj(cnpj)) {
                EmpresaInterface empresa = empresaDAO.buscaPerfilUnicoEmpresa(cnpj)
                return empresa
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar receber os dados da empresa: " + e)
        }
    }

    EmpresaInterface exibeEmpresa(long cnpj) {
        try {
            EmpresaInterface empresa = recebeDadosEmpresas(cnpj)
            return empresa
        } catch (Exception e) {
            throw new Exception("Erro ao tentar exibir os dados da empresa: " + e)
        }
    }

    boolean salvaCurtidaEmpresa(long cnpj, int idCandidato) {
        try{
            if(validatorService.validaCnpj(cnpj)) {
                empresaDAO.insereCurtidaACandidato(cnpj, idCandidato)
            }
            println("Curtida realizada com sucesso!")
            return true
        } catch (Exception e) {
            throw new Exception("Erro ao tentar salvar curtida ao candidato: " + e)
        }
    } /** TODO: revisar após refatorar Vagas **/

    List<MatchInterface> recebeListaMatchsEmpresa(long cnpj) {
        try {
            if(validatorService.validaCnpj(cnpj)) {
                List<MatchInterface> lista = empresaDAO.buscaMatchsEmpresa(cnpj)
                return lista
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar receber a lista de matchs da empresa: " + e)
        }
    }

    List formataListaMatchsEmpresa(long cnpj) {
        try {
            List<MatchInterface> lista = recebeListaMatchsEmpresa(cnpj)
            List listaFormatada = new ArrayList()
            for(int posicao = 0; posicao < lista.size(); posicao++) {
                listaFormatada.add("Candidato: " + lista[posicao]['nomeCandidato'])
            }
            return listaFormatada
        } catch (Exception e) {
            throw new Exception("Erro ao tentar formatar a lista de matchs da empresa: " + e)
        }
    }

    List exibeListaMatchsEmpresa(long cnpj) {
        try {
            List lista = formataListaMatchsEmpresa(cnpj)
            return lista
        } catch (Exception e) {
            throw new Exception("Erro ao tentar exibir a lista de matchs da empresa: " + e)
        }
    }
}
