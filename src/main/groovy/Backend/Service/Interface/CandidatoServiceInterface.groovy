package Backend.Service.Interface

import Backend.Model.Entidade.Interface.CandidatoInterface

interface CandidatoServiceInterface {

    boolean salvaDadosNovoCandidato(CandidatoInterface candidato)

    List exibeListaCandidatos()

    CandidatoInterface exibirPerfilCandidato(long cpf)

    boolean salvaCurtidaDoCadidato(long cpf, int idVaga)

//    void formataLeituraListaDeMathcs(long cpf)

    List exibeListaMatchsCandidato(long cpf)

}