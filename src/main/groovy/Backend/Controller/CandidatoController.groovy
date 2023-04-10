package Backend.Controller

import Backend.Controller.Inteface.CandidatoControllerInterface
import Backend.Model.Entidade.Interface.CandidatoInterface
import Backend.Service.Interface.CandidatoServiceInterface

class CandidatoController implements CandidatoControllerInterface{

    private CandidatoServiceInterface candidatoService

    CandidatoController(CandidatoServiceInterface candidatoService) {
        this.candidatoService = candidatoService
    }

    void recebeDadosNovoCandidato(CandidatoInterface candidato) {
        candidatoService.salvaDadosNovoCandidato(candidato)
    }

    void listaCandidatos() {
        candidatoService.exibeListaCandidatos()
    }


    void exibePerfilCandidato(long cpf) {
        candidatoService.exibirPerfilCandidato(cpf)
    }

    void curtirVaga(long cpf, int idVaga) {
        candidatoService.salvaCurtidaDoCadidato(cpf, idVaga)
    }

    void listaMatchsCandidato(long cpf) {
        candidatoService.exibeListaMatchsCandidato(cpf)
    }
}
