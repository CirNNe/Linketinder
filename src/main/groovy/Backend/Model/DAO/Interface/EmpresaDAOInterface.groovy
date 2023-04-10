package Backend.Model.DAO.Interface

import Backend.Model.Entidade.Empresa
import Backend.Model.Entidade.Interface.EmpresaInterface
import Backend.Model.Entidade.Match

interface EmpresaDAOInterface {

    Object buscaIdEmpresa(long cnpj)

    Empresa buscaPerfilUnicoEmpresa(long cnpj)

    boolean insereDadosEmpresas(EmpresaInterface empresa)

    boolean insereCurtidaACandidato(long cnpj, int idCandidato)

    List<Match> buscaMatchsEmpresa(long cnpj)



}