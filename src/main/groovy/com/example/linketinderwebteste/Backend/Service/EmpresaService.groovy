package com.example.linketinderwebteste.Backend.Service

import com.example.linketinderwebteste.Backend.Factory.FactoryDAO.EmpresaDAOFactory
import com.example.linketinderwebteste.Backend.Factory.FactoryService.ValidatorServiceFactory
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.EmpresaDAOInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.EmpresaInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.MatchInterface
import com.example.linketinderwebteste.Backend.Service.Interface.EmpresaServiceInterface
import com.example.linketinderwebteste.Backend.Service.Interface.ValidatorServiceInterface

class EmpresaService implements EmpresaServiceInterface {

    private EmpresaDAOInterface empresaDAO = new EmpresaDAOFactory().criaEmpresaDAO()
    private ValidatorServiceInterface validatorService = new ValidatorServiceFactory().criaValidatorService()

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
    }

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
                listaFormatada.add("CandidatoEntidade: " + lista[posicao]['nomeCandidato'])
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
