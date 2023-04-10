package Backend.Controller

import Backend.Controller.Inteface.EmpresaControllerInterface
import Backend.Model.Entidade.Interface.EmpresaInterface
import Backend.Service.Interface.EmpresaServiceInterface

class EmpresaController implements EmpresaControllerInterface{

    private EmpresaServiceInterface empresaService

    EmpresaController(EmpresaServiceInterface empresaService) {
        this.empresaService = empresaService
    }

    void recebeDadosNovaEmpresa(EmpresaInterface empresa) {
        empresaService.salvaDadosNovaEmpresa(empresa)
    }

    void perfilEmpresa(long cnpj) {
        empresaService.exibeEmpresa(cnpj)
    }

    void curtirCandidato(long cnpj, int idCandidato) {
        empresaService.salvaCurtidaEmpresa(cnpj, idCandidato)
    }

    void listaMatchsEmpresa(long cnpj) {
        empresaService.exibeListaMatchsEmpresa(cnpj)
    }
}
