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

    List listaCandidatos() {
        return candidatoService.exibeListaCandidatos()
    }


    CandidatoInterface exibePerfilCandidato(long cpf) {
        return candidatoService.exibirPerfilCandidato(cpf)
    }

    void curtirVaga(long cpf, int idVaga) {
        candidatoService.salvaCurtidaDoCadidato(cpf, idVaga)
    }

    List listaMatchsCandidato(long cpf) {
        return candidatoService.exibeListaMatchsCandidato(cpf)
    }
}
