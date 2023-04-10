package Backend.Model.DAO.Interface

import Backend.Model.Entidade.Interface.CompetenciaInterface
import Backend.Model.Entidade.Interface.VagaInterface
import Backend.Model.Entidade.Vaga

interface VagaDAOInterface {

    boolean insereDadosVagas(long cnpj, VagaInterface vaga)

//    boolean insereCompetenciasVaga(long cnpj, CompetenciaInterface competencia)

    List<Vaga> buscaVagasDaEmpresa(long cnpj)

    List<Vaga> buscaVagasGerais()

    Object buscaIdEmpresaResponsavelVaga(int idVaga)

    Object buscaIdVaga(String nomeVaga)

}