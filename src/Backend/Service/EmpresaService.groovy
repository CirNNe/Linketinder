package Backend.Service

import Backend.Model.DAO.EmpresaDAO
import Backend.Model.Entidade.Empresa

/**
 * Classe responsável pela regra de negócio da aplicação
 */
class EmpresaService {

    EmpresaDAO empresaDAO

    EmpresaService(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO
    }
/**
     * Método responsável por receber e validar o objeto do tipo Empresa
     * @param empresa: objeto que será tratado e validado
     */
    boolean validaDadosCadastroEmpresa(Empresa empresa) {
        if (empresa.emailCorporativo.length() <= 0 ||
            empresa.cnpj <= 0 ||
            empresa.cep <= 0 ||
            !empresa.estado.matches("[A-Za-z]*") ||
            !empresa.pais.matches("[A-Za-z]*")){
            println "OS VALORES PASSADOS SÃO INVÁLIDOS! TENTA NOVAMENTE"
            return false
        } else {
            this.empresaDAO.salvaDadosEmpresa(empresa)
            return true
        }
    }

    /**
     * Método responsável por receber e formatar a lista contendo as empresas
     */
    void formataLeituraEmpresas() {

        List listaDeEmpresas = new ArrayList()

        this.empresaDAO.leListaEmpresas(listaDeEmpresas)

        for(empresa in listaDeEmpresas) {
            println(empresa)
        }
    }
}
