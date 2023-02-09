package Controller

import Model.Entidade.Empresa
import Model.Service.EmpresaService


/**
 * Classe usada para a comunicação entre a view e a classe EmpresaService
 */
class EmpresaController {

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
    static recebeDados(String nome, String emailCorporativo, long cnpj, int cep, String estado, String pais, String descricao) {

        Empresa empresa = new Empresa()

        empresa.nome = nome
        empresa.emailCorporativo = emailCorporativo
        empresa.cnpj = cnpj
        empresa.cep = cep
        empresa.estado = estado
        empresa.pais = pais
        empresa.descricao = descricao

        EmpresaService.validaDadosCadastroEmpresa(empresa)

    }

    /**
     * Recebe a lista de empresas formatada pela classe EmpresaService
     */
    static listaEmpresas() {
        EmpresaService.formataLeituraEmpresas()
    }
}
