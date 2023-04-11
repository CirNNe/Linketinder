package Backend.Service.Interface

import Backend.Model.Entidade.Interface.CandidatoInterface
import Backend.Model.Entidade.Interface.CompetenciaInterface
import Backend.Model.Entidade.Interface.EmpresaInterface
import Backend.Model.Entidade.Interface.VagaInterface

interface ValidatorServiceInterface {

    boolean validaDadosNovoCandidato(CandidatoInterface candidato)

    boolean validaDadosNovaCompetencia(List<CompetenciaInterface> listaCompetencias)

    boolean validaDadosNovaEmpresa(EmpresaInterface empresa)

    boolean validaDadosNovaVaga(VagaInterface vaga)

    Integer validaTipoUsuario(long identificacao)

    boolean validaCpf(long cpf)

    boolean validaCnpj(long cnpj)

    boolean validaIdVaga(int id)

}