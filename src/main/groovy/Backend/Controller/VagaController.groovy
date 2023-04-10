package Backend.Controller

import Backend.Controller.Inteface.VagaControllerInterface
import Backend.Model.Entidade.Interface.VagaInterface
import Backend.Service.Interface.VagaServiceInterface

class VagaController implements VagaControllerInterface {

    private VagaServiceInterface vagaService

    VagaController(VagaServiceInterface vagaService) {
        this.vagaService = vagaService
    }

    void recebeDadosVaga(long cnpj, VagaInterface vaga) {
        vagaService.salvaDadosNovaVaga(cnpj, vaga)
    }

    void listaVagasEmpresa(long cnpj) {
        vagaService.exibeListaVagasEmpresa(cnpj)
    }

    void listaVagasGerais() {
        vagaService.exibeListaVagasGerais()
    }
}
