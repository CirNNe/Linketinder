package Backend.Service.Interface

import Backend.Model.Entidade.Interface.VagaInterface

interface VagaServiceInterface {

    boolean salvaDadosNovaVaga(VagaInterface vaga)

    boolean exibeListaVagasEmpresa(long cnpj)

    boolean exibeListaVagasGerais()
}