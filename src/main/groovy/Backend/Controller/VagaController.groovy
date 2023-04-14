package Backend.Controller

import Backend.Controller.Inteface.VagaControllerInterface
import Backend.Model.Entidade.Interface.VagaInterface
import Backend.Service.Interface.VagaServiceInterface

class VagaController implements VagaControllerInterface {

    private VagaServiceInterface vagaService

    VagaController(VagaServiceInterface vagaService) {
        this.vagaService = vagaService
    }

    void recebeDadosVaga(VagaInterface vaga) {
        vagaService.salvaDadosNovaVaga(vaga)
    }

    List listaVagasEmpresa(long cnpj) {
        return vagaService.exibeListaVagasEmpresa(cnpj)
    }

    List listaVagasGerais() {
        return vagaService.exibeListaVagasGerais()
    }
}
