package Backend.Util.Regex

class RegexValidaDadosNovoUsuario {
    String nomePessoaFisica = /^[a-zA-Z ]+$/
    String nomePessoaJuridica = /^[a-zA-Z0-9 ]+$/
    String nascimento = /^(\d{2})-(\d{2})-(\d{4})$/
    String cpf = /^[0-9]{11}$/
    String cnpj = /^[0-9]{14}$/
    String email = /^\S+@\w+\.\w{2,6}(\.\w{2})?$/
    String cep = /^[0-9]{8}$/
    String pais = /^[a-zA-Z]+$/
    String senha = /^(?=.*[A-Z])(?=.*[!@#$%^&*\-\\+])(?=.*[0-9]).{8,}$/
}
