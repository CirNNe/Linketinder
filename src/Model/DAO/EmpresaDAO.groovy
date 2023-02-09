package Model.DAO

import Model.Entidade.Empresa

/**
 * Classe responsável por enviar os dados da empresa para a classe GenericDAO
 */
class EmpresaDAO {

    /**
     * Método envia o arquivo em que os dados serão escritos e e os parâmetros da Empresa
     * @param empresa: objeto do tipo empresa que será escrito no arquivo
     */
    static salvaDadosEmpresa(Empresa empresa) {

        File empresasTxt = new File('empresas.txt')

        GenericDAO.escreveNaTabela(empresasTxt, empresa.nome, empresa.emailCorporativo, empresa.cnpj, empresa.cep,
                                    empresa.estado, empresa.pais, empresa.descricao)
    }

    /**
     * Método envia o arquivo que será lido e a lista onde serão instanciados as Empresas
     * @param listaDeEmpresas: lista onde serão instanciados as empresas
     */
    static leListaEmpresas(List listaDeEmpresas) {

        File empresasTxt = new File('empresas.txt')

        List recebeListaDeEmpresas = new ArrayList()

        GenericDAO.leATabela(empresasTxt, recebeListaDeEmpresas)

        listaDeEmpresas.addAll(recebeListaDeEmpresas)

    }

}
