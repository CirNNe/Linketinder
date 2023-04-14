package Backend.Service.Interface

import Backend.Model.Entidade.Interface.VagaInterface

interface VagaServiceInterface {

    boolean validaDadosNovaVaga(VagaInterface vaga)

    boolean salvaDadosNovaVaga(VagaInterface vaga)

    List<VagaInterface> recebeListaVagasEmpresa(long cnpj)

    List exibeListaVagasEmpresa(long cnpj)

    List<VagaInterface> recebeListaVagasGerais()

    List exibeListaVagasGerais()
}