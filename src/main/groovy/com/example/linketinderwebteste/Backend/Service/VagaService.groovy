package com.example.linketinderwebteste.Backend.Service

import com.example.linketinderwebteste.Backend.Factory.FactoryDAO.VagaDAOFactory
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.VagaDAOInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.VagaInterface
import com.example.linketinderwebteste.Backend.Service.Interface.VagaServiceInterface
import com.example.linketinderwebteste.Backend.Util.Regex.RegexValidaDadosNovaVaga
import com.example.linketinderwebteste.Backend.Util.Regex.RegexValidaDadosNovoUsuario

class VagaService implements VagaServiceInterface {

    private VagaDAOInterface vagaDAO = new VagaDAOFactory().criaVagaDAO()
    private RegexValidaDadosNovaVaga regexVaga = new RegexValidaDadosNovaVaga()
    private RegexValidaDadosNovoUsuario regexUsuario = new RegexValidaDadosNovoUsuario()

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
