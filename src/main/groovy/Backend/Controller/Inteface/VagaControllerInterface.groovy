package Backend.Controller.Inteface

import Backend.Model.Entidade.Interface.VagaInterface

interface VagaControllerInterface {

    void recebeDadosVaga(VagaInterface vaga)

    void listaVagasEmpresa(long cnpj)

    void listaVagasGerais()

}