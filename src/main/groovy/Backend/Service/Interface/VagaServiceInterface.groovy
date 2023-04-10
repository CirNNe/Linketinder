package Backend.Service.Interface

import Backend.Model.Entidade.Interface.CompetenciaInterface
import Backend.Model.Entidade.Interface.VagaInterface

interface VagaServiceInterface {

    boolean salvaDadosNovaVaga(long cnpj, VagaInterface vaga)

    boolean exibeListaVagasEmpresa(long cnpj)

    boolean exibeListaVagasGerais()

//    void validaDadosVaga(long cnpj, VagaInterface vaga)

//    void validaCompetenciasVaga(long cnpj, CompetenciaInterface competencia)

//    void validaEFormataLeituraVagasDaEmpresa(long cnpj)

//    void formataLeituraVagaGerais()

}