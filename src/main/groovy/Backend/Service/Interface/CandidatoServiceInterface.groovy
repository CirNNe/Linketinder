package Backend.Service.Interface

import Backend.Model.Entidade.Interface.CandidatoInterface

interface CandidatoServiceInterface {

    boolean salvaDadosNovoCandidato(CandidatoInterface candidato)

    boolean exibeListaCandidatos()

    boolean exibirPerfilCandidato(long cpf)

    boolean salvaCurtidaDoCadidato(long cpf, int idVaga)

//    void formataLeituraListaDeMathcs(long cpf)

    boolean exibeListaMatchsCandidato(long cpf)

}