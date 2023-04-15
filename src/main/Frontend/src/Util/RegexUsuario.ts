export class RegexUsuario {
    nomePessoaFisicaRegex: RegExp = /^[a-zA-Z ]+$/gi
    nomePessoaJuridicaRegex: RegExp = /^[\p{L}\d\s]+$/u
    nascimentoRegex: RegExp = /^(\d{2})-(\d{2})-(\d{4})$/
    cpfRegex: RegExp = /^[0-9]{11}$/
    cnpjRegex: RegExp = /^[0-9]{14}$/
    emailRegex: RegExp = /^\S+@\w+\.\w{2,6}(\.\w{2})?$/
    cepRegex: RegExp = /^[0-9]{8}$/
    estadoRegex: RegExp = /^([A-Za-zְ-תאת])+$/gi
    paisRegex: RegExp = /^[a-zA-Z]+$/
    senhaRegex: RegExp = /^(?=.*[A-Z])(?=.*[!@#$%^&*\-\\+])(?=.*[0-9]).{8,}$/
    descricaoRegex: RegExp = /^.{3,250}$/gi
    linkedinRegex: RegExp = /(http:\/\/)?(www\.)?linkedin\.com\/in\/\w+/g
}
