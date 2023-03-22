package Backend.Service

import Backend.Model.DAO.VagaDAO
import Backend.Model.Entidade.Competencia
import Backend.Model.Entidade.Vaga

class VagaService {

    VagaDAO vagaDAO

    VagaService(VagaDAO vagaDAO) {
        this.vagaDAO = vagaDAO
    }

    void validaDadosVaga(long cnpj, Vaga vaga) {

        if(!vaga.nome.isEmpty() &&
            !vaga.empresa.isEmpty() &&
            !vaga.descricao.isEmpty() &&
            !vaga.pais.isEmpty() &&
            cnpj.toString().replaceAll(/[^\d]/, '').matches(/^\d{14}$/)) {

            vagaDAO.inserirDadosNaTabelaVagas(cnpj, vaga)
        }
    }

    void validaCompetenciasVaga(long cnpj, Competencia competencia) {

        if(cnpj.toString().replaceAll(/[^\d]/, '').matches(/^\d{14}$/) &&
            !competencia.nome.isEmpty()) {

            this.vagaDAO.inserirDadosNaTebelaCompetenciasVaga(cnpj, competencia)
        } else  {
            println("Os dados informados estão inválidos, por favor, revise e tente novamente.")
        }
    }

    void validaEFormataLeituraVagasDaEmpresa(long cnpj) {

        if(cnpj.toString().replaceAll(/[^\d]/, '').matches(/^\d{14}$/)) {

            List listaDeVagas = this.vagaDAO.leVagasDaEmpresa(cnpj)

            for(int posicao = 0; posicao < listaDeVagas.size(); posicao++) {

                println("ID: " + listaDeVagas[posicao]['id'] + " - " +
                        "Nome: " + listaDeVagas[posicao]['nome'] + " - " +
                        "Empresa: " + listaDeVagas[posicao]['empresa'] + " - " +
                        "Descrição: " + listaDeVagas[posicao]['descricao'] + " - " +
                        "País: " + listaDeVagas[posicao]['pais'] + " - " +
                        "Competências: " + listaDeVagas[posicao]['competencias'].toString().replaceAll(/[\[\]{}]/, ''))

                println('-' * 50)

            }
        } else {
            println("O dado informado está inválido, por favor, revise e tente novamente.")
        }
    }

    void formataLeituraVagaGerais() {

        List vagas = this.vagaDAO.leVagasGerais()

        for(int posicao = 0; posicao < vagas.size(); posicao++) {
            int id = vagas[posicao]['id'] as int
            String nome = vagas[posicao]['nome']
            String empresa = vagas[posicao]['empresa'] = "Anônimo"
            String descricao = vagas[posicao]['descricao']
            String pais = vagas[posicao]['pais']
            List competencias = vagas[posicao]['competencias'] as List

            println("ID: " + id + " - " + "Nome: " + nome + " - " +
                    "Empresa: " + empresa + " - " + "Descrição: " +
                    descricao + " - " + "País: " + pais + " - " +
                    "Competências: " + competencias.toString().replaceAll(/[\[\]{}]/, ''))
        }
    }
}
