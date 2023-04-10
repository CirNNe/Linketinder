package Backend.Model.Entidade.Interface

import Backend.Model.Entidade.Competencia

interface CandidatoInterface {

    String getNome()
    void setNome(String nome)

    String getEmail()
    void setEmail(String email)

    String getDataNascimento()
    void setDataNascimento(String dataNascimento)

    String getDescricaoPessoal()
    void setDescricaoPessoal(String descricaoPessoal)

    String getSenha()
    void setSenha(String senha)

    String getPais()
    void setPais(String pais)

    long getCpf()
    void setCpf(long cpf)

    int getId()
    void setId(int id)

    int getCep()
    void setCep(int cep)

    List<Competencia> getCompetencias()
    void setCompetencias(List<Competencia> competencias)

}
