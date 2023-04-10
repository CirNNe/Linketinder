package Backend.Model.Entidade.Interface

interface EmpresaInterface {

    String getNome()
    void setNome(String nome)

    String getEmail()
    void setEmail(String email)

    String getDescricao()
    void setDescricao(String descricao)

    String getSenha()
    void setSenha(String senha)

    String getPais()
    void setPais(String pais)

    long getCnpj()
    void setCnpj(long cnpj)

    int getId()
    void setId(int id)

    int getCep()
    void setCep(int cep)

}
