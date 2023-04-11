package Backend.Model.DAO.Interface

import Backend.Model.Entidade.Candidato
import Backend.Model.Entidade.Interface.CandidatoInterface
import Backend.Model.Entidade.Match

interface CandidatoDAOInterface {
    Integer buscaIdCandidato(long cpf)

    List<Candidato> buscaListaCandidatos()

    Candidato buscaPerfilUnicoCandidato(long cpf)

    boolean insereDadosCandidato(CandidatoInterface candidato)

    boolean insereCurtiAVaga(long cpf, int idVaga)

    List<Match> buscaMatchsCandidato(long cpf)
}
