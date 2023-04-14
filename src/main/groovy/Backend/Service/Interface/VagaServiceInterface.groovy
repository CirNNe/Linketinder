package Backend.Service.Interface

import Backend.Model.Entidade.Interface.VagaInterface

interface VagaServiceInterface {

    boolean salvaDadosNovaVaga(VagaInterface vaga)

    List exibeListaVagasEmpresa(long cnpj)

    List exibeListaVagasGerais()
}