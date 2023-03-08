package Backend.Model.DAO

import Backend.Model.Entidade.Empresa

/**
 * Classe responsável por enviar os dados da empresa para a classe GenericDAO
 */
class EmpresaDAO extends GenericDAO<Empresa> {

    /**
     * Método envia o arquivo em que os dados serão escritos e e os parâmetros da Empresa
     * @param empresa: objeto do tipo empresa que será escrito no arquivo
     */
    boolean salvaDadosEmpresa(Empresa empresa) {

        try {
            File empresasTxt = new File('empresas.txt')

            super.escreveNaTabela(empresasTxt, empresa.toString())
            return true
        } catch (Exception error) {
            println("ERRO AO TENTAR SALVAR OS DADOS DA EMPRESA NA TABELA! ERRO: " + error)
            return false
        }
    }

    /**
     * Método envia o arquivo que será lido e a lista onde serão instanciados as Empresas
     * @param listaDeEmpresas: lista onde serão instanciados as empresas
     */
    boolean leListaEmpresas(List listaDeEmpresas) {

        try {
            File empresasTxt = new File('empresas.txt')

            List recebeListaDeEmpresas = new ArrayList()

            super.leATabela(empresasTxt, recebeListaDeEmpresas)

            listaDeEmpresas.addAll(recebeListaDeEmpresas)
            return true
        } catch (Exception error) {
            print("ERRO AO TENTAR LER A LISTA DE EMPRESAS SALVAS! ERRO: " + error)
            return false
        }
    }
}
