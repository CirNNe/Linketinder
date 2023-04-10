package Backend.Model.Entidade.Interface

import Backend.Model.Entidade.Competencia

interface VagaInterface {

    String getNome()
    void setNome(String nome)

    String getEmpresa()
    void setEmpresa(String Empresa)

    String getDescricao()
    void setDescricao(String descricao)

    String getPais()
    void setPais(String pais)

    int getId()
    void setId(int id)

    List<Competencia> getCompetencias()
    void setCompetencias(List<Competencia> competencias)
}
