package Backend.Controller.Inteface

import Backend.Model.Entidade.Interface.EmpresaInterface

interface EmpresaControllerInterface {

    void recebeDadosNovaEmpresa(EmpresaInterface empresa)

    EmpresaInterface perfilEmpresa(long cnpj)

    void curtirCandidato(long cnpj, int idCandidato)

    List listaMatchsEmpresa(long cnpj)

}
