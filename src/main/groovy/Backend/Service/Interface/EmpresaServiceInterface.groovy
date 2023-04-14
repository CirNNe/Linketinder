package Backend.Service.Interface

import Backend.Model.Entidade.Interface.EmpresaInterface

interface EmpresaServiceInterface {

    boolean salvaDadosNovaEmpresa(EmpresaInterface empresa)

    EmpresaInterface exibeEmpresa(long cnpj)

    boolean salvaCurtidaEmpresa(long cnpj, int idCandidato)

    List exibeListaMatchsEmpresa(long cnpj)
}
