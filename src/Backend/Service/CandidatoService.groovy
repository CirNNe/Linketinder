package Backend.Service

import Backend.Model.Entidade.Candidato
import Backend.Model.DAO.CandidatoDAO

class CandidatoService {

    CandidatoDAO candidatoDAO

    CandidatoService(CandidatoDAO candidatoDAO) {
        this.candidatoDAO = candidatoDAO
    }

    boolean validaDadosCadastroCandidato(Candidato candidato) {

        if(candidato.nome.matches(/^[a-zA-Z ]+$/) &&
            candidato.email.matches(/\S+@\w+\.\w{2,6}(\.\w{2})?/) &&
            candidato.dataNascimento.matches(/^(\d{2})-(\d{2})-(\d{4})$/) &&
            candidato.cpf.toString().replaceAll("[^0-9]", "").matches(/[0-9]{11}/) &&
            candidato.cep.toString().replaceAll("[^0-9]", "").matches(/[0-9]{8}/) &&
            candidato.pais.matches(/^[a-zA-Z]+$/) &&
            !candidato.descricaoPessoal.isEmpty() &&
            candidato.senha.matches(/(?=.*[A-Z])(?=.*[!@#$%^&*\-\\+])(?=.*[0-9]).{8,}/)) {
            this.candidatoDAO.inserirDadosNaTabelaCanidatos(candidato)
            return true
        } else {
            println("Informações inválidas, por favor, revise os dados e tente novamente.")
            return false
        }
    }

    void formataLeituraCandidatos() {

        List<Candidato> listaDeCandidatos = this.candidatoDAO.listarDadosDaTabelaCandidatos()

        for(int posicao = 0; posicao < listaDeCandidatos.size(); posicao++) {

            int id = listaDeCandidatos[posicao]['id'] as int
            String nome = listaDeCandidatos[posicao]['nome'] = 'Anônimo'
            String pais = listaDeCandidatos[posicao]['pais']
            String descricao = listaDeCandidatos[posicao]['descricaoPessoal']
            List competencias = listaDeCandidatos[posicao]['competencias'] as List

            println('ID: ' + id + ' - ' + 'Nome: ' + nome + ' - ' + ' País: ' + pais + ' - ' +
                    ' Descrição Pessoal: ' + descricao + ' - ' + ' Competências: ' +
                    competencias.toString().replaceAll(/[\[\]{}]/, ''))

            println('-' * 50)

        }
    }

    void validaEFormataLeituraPerfilCandidato(long cpf) {

        if(cpf.toString().replaceAll("[^0-9]", "").matches(/[0-9]{11}/)) {

            Candidato candidato = this.candidatoDAO.lePerfilUnicoCandidato(cpf)

            println("ID: " + candidato.id + "\n" + "Nome: " + candidato.nome + "\n" +
                    "E-mail: " + candidato.email + "\n" + "Data de Nascimento: " + candidato.dataNascimento + "\n" +
                    "CPF: " + candidato.cpf + "\n" + "CEP: " + candidato.cep + "\n" + "País: " + candidato.pais + "\n" +
                    "Descrição Pessoal: " + candidato.descricaoPessoal + "\n" +
                    "Competências: " + candidato.competencias.toString().replaceAll(/[\[\]{}]/, ''))
        } else {
            println("O dado informado está inválido, por favor, revise e tente novamente.")
        }
    }

    void validaCurtidaDoCadidato(long cpf, int idVaga) {

        if(cpf.toString().replaceAll("[^0-9]", "").matches(/[0-9]{11}/) &&
            idVaga > 0) {

            this.candidatoDAO.inserirCurtidaVaga(cpf, idVaga)

        } else {
            println("Os dados informados estão inválidos, por favor, revise e tente novamente.")
        }
    }

    void formataLeituraListaDeMathcs(long cpf) {

        List listaMatchs = this.candidatoDAO.leMatchsCandidato(cpf)
        for(int posicao = 0; posicao < listaMatchs.size(); posicao++) {
            println("Empresa: " + listaMatchs[posicao]['nomeEmpresa'] + "\n")
        }
    }
}
