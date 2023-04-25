package com.example.linketinderwebteste.Backend.Service

import com.example.linketinderwebteste.Backend.Model.DAO.Interface.CandidatoDAOInterface
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.EmpresaDAOInterface
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.VagaDAOInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CandidatoInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CompetenciaInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.EmpresaInterface
import com.example.linketinderwebteste.Backend.Service.Interface.ValidatorServiceInterface
import com.example.linketinderwebteste.Backend.Util.Regex.RegexValidaDadosNovaVaga
import com.example.linketinderwebteste.Backend.Util.Regex.RegexValidaDadosNovoUsuario

class ValidatorService implements ValidatorServiceInterface {

    private final CANDIDATO = 1
    private final EMPRESA = 2

    private RegexValidaDadosNovoUsuario regexUsuario
    private RegexValidaDadosNovaVaga regexVaga
    private VagaDAOInterface vagaDAO
    private CandidatoDAOInterface candidatoDAO
    private EmpresaDAOInterface empresaDAO

    ValidatorService(RegexValidaDadosNovoUsuario regexUsuario, RegexValidaDadosNovaVaga regexVaga, VagaDAOInterface vagaDAO,
                        CandidatoDAOInterface candidatoDAO, EmpresaDAOInterface empresaDAO) {
        this.regexUsuario = regexUsuario
        this.regexVaga = regexVaga
        this.vagaDAO = vagaDAO
        this.candidatoDAO = candidatoDAO
        this.empresaDAO = empresaDAO
    }

    boolean validaDadosNovoCandidato(CandidatoInterface candidato) {
        try {
            if(candidato.nome.matches(regexUsuario.getNomePessoaFisica()) &&
                    candidato.email.matches(regexUsuario.getEmail()) &&
                    candidato.dataNascimento.matches(regexUsuario.getNascimento()) &&
                    candidato.cpf.toString().replaceAll("[^0-9]", "").matches(regexUsuario.getCpf()) &&
                    candidato.cep.toString().replaceAll("[^0-9]", "").matches(regexUsuario.getCep()) &&
                    candidato.pais.matches(regexUsuario.getPais()) &&
                    !candidato.descricaoPessoal.isEmpty() &&
                    candidato.senha.matches(regexUsuario.getSenha())) {
                return true
            } else {
                println("Dados do candidato inválidos, por favor, tente novamente!")
                return false
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar validar os dados do novo candidato: " + e)
        }
    }

    boolean validaDadosNovaEmpresa(EmpresaInterface empresa) {
        try {
            if(empresa.nome.matches(regexUsuario.getNomePessoaJuridica()) &&
                    empresa.email.matches(regexUsuario.getEmail()) &&
                    empresa.cnpj.toString().replaceAll("[^0-9]", "").matches(regexUsuario.getCnpj()) &&
                    empresa.cep.toString().replaceAll("[^0-9]", "").matches(regexUsuario.getCep()) &&
                    empresa.pais.matches(regexUsuario.getPais()) &&
                    !empresa.descricao.isEmpty() &&
                    empresa.senha.matches(regexUsuario.getSenha())) {
                return true
            } else {
                println("Dados da empresa inválidos, por favor, tente novamente!")
                return false
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar validar os dados da empresa: " + e)
        }
    }

    boolean validaDadosNovaCompetencia(List listaCompetencias) {
        try {
            List listaCompetenciasValidadas = new ArrayList()
            for(int posicao = 0; posicao < listaCompetencias.size(); posicao++) {
                if(listaCompetencias[posicao] != null) {
                    listaCompetenciasValidadas.add(listaCompetencias[posicao])
                }
            }
            if(listaCompetenciasValidadas.size() == listaCompetencias.size()) {
                return true
            } else {
                println("Competência(s) inválida(s), tente novamente!")
                return false
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar validar os dados da competência: " + e)
        }
    }

    Integer validaTipoUsuario(long identificacao) {
       try {
           if(identificacao.toString().length() == 11) {
               return CANDIDATO
           } else if(identificacao.toString().length() == 14) {
               return EMPRESA
           }
       } catch (Exception e) {
           throw new Exception("Erro ao tentar validar o tipo do usuário: " + e)
       }
    }

    boolean validaCpf(long cpf) {
        if(cpf.toString().replaceAll("[^0-9]", "").matches(/[0-9]{11}/)) {
            return true
        } else {
            println("O cpf informado est? inv?lido, por favor, revise e tente novamente.")
            return false
        }
    }

    boolean validaCnpj(long cnpj) {
        if(cnpj.toString().replaceAll("[^0-9]", "").matches(/[0-9]{14}/)) {
            return true
        } else {
            println("O cnpj informado est? inv?lido, por favor, revise e tente novamente.")
            return false
        }
    }

    boolean validaIdVaga(int idVaga) {
        if(vagaDAO.buscaIdEmpresaResponsavelVaga(idVaga) != null) {
            return true
        } else {
            println("N?o foi encontrado o id da vaga, por favor, revise o id e tente novamente!")
            return false
        }
    }
}
