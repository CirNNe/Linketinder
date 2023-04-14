package Backend.Service.Interface

import Backend.Model.Entidade.Interface.CandidatoInterface
import Backend.Model.Entidade.Interface.MatchInterface

interface CandidatoServiceInterface {

    boolean salvaDadosNovoCandidato(CandidatoInterface candidato)

    List<CandidatoInterface> recebeListaCandidatos()

    List formataLeituraListaCandidatos()

    List exibeListaCandidatos()

    CandidatoInterface recebeDadosCandidato(long cpf)

    CandidatoInterface exibirPerfilCandidato(long cpf)

    boolean salvaCurtidaDoCadidato(long cpf, int idVaga)

    List<MatchInterface> recebeListaMatchsCandidato(long cpf)

    List<MatchInterface> formataListaMatchsCandidato(long cpf)

    List exibeListaMatchsCandidato(long cpf)

}