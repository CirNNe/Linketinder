package Controller

import Model.DAO.EmpresaDAO
import Model.Entidade.Empresa
import Service.EmpresaService

/**
 * Classe usada para a comunicação entre a view e a classe EmpresaService
 */
class EmpresaController {

    EmpresaService empresaService = new EmpresaService(new EmpresaDAO())

    /**
     * Recebe os dados do candidato passados pelo usuário na view e cria um objeto do tipo Empresa
     * @param nome: nome da empresa
     * @param emailCorporativo: email corporativo da empresa
     * @param cnpj: cnpj da empresa
     * @param cep: cep da empresa
     * @param estado: estado em que a empresa existe
     * @param pais: pais em que a empresa extiste
     * @param descricao: descrição da empresa
     */
    void recebeDados(String nome, String emailCorporativo, long cnpj, int cep, String estado, String pais, String descricao) {

        Empresa empresa = new Empresa()

        empresa.nome = nome
        empresa.emailCorporativo = emailCorporativo
        empresa.cnpj = cnpj
        empresa.cep = cep
        empresa.estado = estado
        empresa.pais = pais
        empresa.descricao = descricao

        empresaService.validaDadosCadastroEmpresa(empresa)

    }

    /**
     * Recebe a lista de empresas formatada pela classe EmpresaService
     */
    void listaEmpresas() {
            empresaService.formataLeituraEmpresas()
    }
}
