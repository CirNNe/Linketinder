package Backend.Service.Interface

import Backend.Model.Entidade.Interface.EmpresaInterface

interface EmpresaServiceInterface {

    boolean salvaDadosNovaEmpresa(EmpresaInterface empresa)

    boolean exibeEmpresa(long cnpj)

    boolean salvaCurtidaEmpresa(long cnpj, int idCandidato)

    boolean exibeListaMatchsEmpresa(long cnpj)
}
