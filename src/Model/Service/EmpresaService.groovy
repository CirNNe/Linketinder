package Model.Service


import Model.DAO.EmpresaDAO
import Model.Entidade.Empresa

/**
 * Classe responsável pela regra de negócio da aplicação
 */
class EmpresaService {

    /**
     * Método responsável por receber e validar o objeto do tipo Empresa
     * @param empresa: objeto que será tratado e validado
     */
    static validaDadosCadastroEmpresa(Empresa empresa) {
        if (empresa.emailCorporativo.length() <= 0 ||
            empresa.cnpj <= 0 ||
            empresa.cep <= 0 ||
            !empresa.estado.matches("[A-Za-z]*") ||
            !empresa.pais.matches("[A-Za-z]*")){
            println "OS VALORES PASSADOS SÃO INVÁLIDOS! TENTA NOVAMENTE"
        } else {
            EmpresaDAO.salvaDadosEmpresa(empresa)
        }
    }

    /**
     * Método responsável por receber e formatar a lista contendo as empresas
     */
    static formataLeituraEmpresas() {

        List listaDeEmpresas = new ArrayList()

        EmpresaDAO.leListaEmpresas(listaDeEmpresas)

        for(empresa in listaDeEmpresas) {
            println(empresa)
        }
    }
}
