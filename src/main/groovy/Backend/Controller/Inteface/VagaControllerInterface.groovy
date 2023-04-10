package Backend.Controller.Inteface

import Backend.Model.Entidade.Interface.VagaInterface

interface VagaControllerInterface {

    void recebeDadosVaga(long cnpj, VagaInterface vaga)

//    void recebeDadosCompetenciasVaga(long cnpj, List listaDeCompetencias)

    void listaVagasEmpresa(long cnpj)

    void listaVagasGerais()

}