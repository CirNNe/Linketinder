package Backend.Controller.Inteface

import Backend.Model.Entidade.Interface.VagaInterface

interface VagaControllerInterface {

    void recebeDadosVaga(VagaInterface vaga)

    List listaVagasEmpresa(long cnpj)

    List listaVagasGerais()

}