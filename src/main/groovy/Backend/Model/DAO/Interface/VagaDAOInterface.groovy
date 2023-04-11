package Backend.Model.DAO.Interface

import Backend.Model.Entidade.Interface.VagaInterface
import Backend.Model.Entidade.Vaga

interface VagaDAOInterface {

    boolean insereDadosVagas(VagaInterface vaga)

    List<Vaga> buscaVagasDaEmpresa(long cnpj)

    List<Vaga> buscaVagasGerais()

    Integer buscaIdEmpresaResponsavelVaga(int idVaga)

    Integer buscaIdVaga(String nomeVaga)
}