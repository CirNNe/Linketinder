package Backend.Controller.Inteface

import Backend.Model.Entidade.Interface.CandidatoInterface

interface CandidatoControllerInterface {

    void recebeDadosNovoCandidato(CandidatoInterface candidato)

    List listaCandidatos()

    CandidatoInterface exibePerfilCandidato(long cpf)

    void curtirVaga(long cpf, int idVaga)

    List listaMatchsCandidato(long cpf)
}
