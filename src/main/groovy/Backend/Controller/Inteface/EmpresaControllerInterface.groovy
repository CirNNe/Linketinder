package Backend.Controller.Inteface

import Backend.Model.Entidade.Interface.EmpresaInterface

interface EmpresaControllerInterface {

    void recebeDadosNovaEmpresa(EmpresaInterface empresa)

    void perfilEmpresa(long cnpj)

    void curtirCandidato(long cnpj, int idCandidato)

    void listaMatchsEmpresa(long cnpj)

}
