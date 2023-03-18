package Backend.Service

import Backend.Model.DAO.EmpresaDAO
import Backend.Model.Entidade.Empresa

class EmpresaService {

    EmpresaDAO empresaDAO

    EmpresaService(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO
    }

    boolean validaDadosCadastroEmpresa(Empresa empresa) {

        if(!empresa.nome.isEmpty() &&
            empresa.emailCorporativo.matches(/\S+@\w+\.\w{2,6}(\.\w{2})?/) &&
            empresa.cnpj.toString().replaceAll(/[^\d]/, '').matches(/^\d{14}$/) &&
            empresa.cep.toString().replaceAll("[^0-9]", "").matches(/[0-9]{8}/) &&
            empresa.pais.matches(/^[a-zA-Z]+$/) &&
            !empresa.descricao.isEmpty() &&
            empresa.senha.matches(/(?=.*[A-Z])(?=.*[!@#$%^&*\-\\+])(?=.*[0-9]).{8,}/)) {

            this.empresaDAO.inserirDadosNaTabelaEmpresas(empresa)
            return true
        } else {
            println("Informações inválidas, por favor, revise os dados e tente novamente.")
            return false
        }
    }

    void validaEFormataLeituraEmpresa(long cnpj) {

        if(cnpj.toString().replaceAll(/[^\d]/, '').matches(/^\d{14}$/)) {

            Empresa empresa = this.empresaDAO.lePerfilUnicoEmpresa(cnpj)

            println("ID: " + empresa.id + "\n" + "Nome: " + empresa.nome + "\n" +
                    "E-mail: " + empresa.emailCorporativo + "\n" +
                    "CNPJ: " + empresa.cnpj + "\n" + "CEP: " + empresa.cep + "\n" +
                    "País: " + empresa.pais + "\n" + "Descrição: " + empresa.descricao)

        } else {
            println("O dado informado está inválido, por favor, revise e tente novamente.")
        }
    }

    void validaCurtidaEmpresa(long cnpj, int idCandidato) {

        if(cnpj.toString().replaceAll(/[^\d]/, '').matches(/^\d{14}$/) &&
            idCandidato > 0) {

            this.empresaDAO.inserirCurtidaCandidato(cnpj, idCandidato)

        } else {
            println("Os dados informados estão inválidos, por favor, revise e tente novamente.")
        }
    }

    void formataLeituraMatchEmpresa(long cnpj) {

        List listaDeMatchs = this.empresaDAO.listarMatchsEmpresa(cnpj)
        for(int posicao = 0; posicao < listaDeMatchs.size(); posicao++) {
            println("Candidato: " + listaDeMatchs[posicao]['nomeCandidato'] + "\n")
        }
    }
}
