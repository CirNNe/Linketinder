package Backend.Controller

import Backend.Model.DAO.EmpresaDAO
import Backend.Model.Entidade.Empresa
import Backend.Service.EmpresaService

class EmpresaController {

    EmpresaService empresaService = new EmpresaService(new EmpresaDAO())

    void recebeDados(String nome, String emailCorporativo, long cnpj, int cep, String pais, String descricao, String senha) {

        Empresa empresa = new Empresa()

        empresa.nome = nome
        empresa.emailCorporativo = emailCorporativo
        empresa.cnpj = cnpj
        empresa.cep = cep
        empresa.pais = pais
        empresa.descricao = descricao
        empresa.senha = senha

        empresaService.validaDadosCadastroEmpresa(empresa)

    }

    void perfilEmpresa(long cnpj) {
        empresaService.validaEFormataLeituraEmpresa(cnpj)
    }

    void curtirCandidato(long cnpj, int idCandidato) {
        empresaService.validaCurtidaEmpresa(cnpj, idCandidato)
    }

    void listaMatchsEmpresa(long cnpj) {
        empresaService.formataLeituraMatchEmpresa(cnpj)
    }
}
