package Backend.Controller.Inteface

import Backend.Model.Entidade.Interface.CandidatoInterface

interface CandidatoControllerInterface {

    void recebeDadosNovoCandidato(CandidatoInterface candidato)

    void listaCandidatos()

    void exibePerfilCandidato(long cpf)

    void curtirVaga(long cpf, int idVaga)

    void listaMatchsCandidato(long cpf)
}
