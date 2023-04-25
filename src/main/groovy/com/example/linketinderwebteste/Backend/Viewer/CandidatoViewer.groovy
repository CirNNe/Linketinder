package com.example.linketinderwebteste.Backend.Viewer

import com.example.linketinderwebteste.Backend.Controller.CandidatoController
import com.example.linketinderwebteste.Backend.Controller.CompetenciaController
import com.example.linketinderwebteste.Backend.Controller.Inteface.CandidatoControllerInterface
import com.example.linketinderwebteste.Backend.Controller.Inteface.CompetenciaControllerInterface
import com.example.linketinderwebteste.Backend.Controller.Inteface.PaisControllerInterface
import com.example.linketinderwebteste.Backend.Controller.Inteface.VagaControllerInterface
import com.example.linketinderwebteste.Backend.Controller.PaisController
import com.example.linketinderwebteste.Backend.Controller.VagaController
import com.example.linketinderwebteste.Backend.Factory.FactoryDAO.CandidatoDAOFactory
import com.example.linketinderwebteste.Backend.Model.DAO.Interface.CandidatoDAOInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Candidato
import com.example.linketinderwebteste.Backend.Model.Entidade.Competencia
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CandidatoInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CompetenciaInterface

class CandidatoViewer {

    CandidatoControllerInterface candidatoController = new CandidatoController()
    CandidatoDAOInterface candidatoDAO = new CandidatoDAOFactory().criaCandidatoDAO()
    VagaControllerInterface vagaController = new VagaController()
    CompetenciaControllerInterface competenciaController = new CompetenciaController()
    PaisControllerInterface paisController = new PaisController()

    CandidatoInterface candidato = new Candidato()

    void menuCandidato() {
        Scanner inputOpcaoCandidato = new Scanner(System.in)
        int opcaoCandidato = 6

        while (opcaoCandidato != 0) {

            println("----- MENU CANDIDATO -----\n" +
                    "1 - CADASTRAR CANDIDATO\n" +
                    "2 - MOSTRAR PERFIL DO CANDIDATO\n" +
                    "3 - LISTAR VAGAS\n" +
                    "4 - CURTIR VAGA\n" +
                    "5 - LISTAR MATCH'S\n" +
                    "0 - SAIR")
            opcaoCandidato = inputOpcaoCandidato.nextInt()

            if (opcaoCandidato == 1) {
                formularioCadastroCandidato()
            } else if (opcaoCandidato == 2) {
                perfilDoCandidato()
            } else if (opcaoCandidato == 3) {
                vagasDisponiveis()
            } else if (opcaoCandidato == 4) {
                curtirVaga()
            } else if (opcaoCandidato == 5) {
                matchsDoCandidato()
            } else if (opcaoCandidato == 0) {
                break
            }
        }
    }

    void formularioCadastroCandidato() {
        Scanner inputCandidato = new Scanner(System.in)

        println("DIGITE O NOME DO CANDIDATO")
        String nome = inputCandidato.nextLine()

        println("DIGITE O EMAIL DO CANDIDATO")
        String email = inputCandidato.nextLine()

        println("DIGITE A DATA DE NASCIMENTO DO CANDIDATO")
        String dataNascimento = inputCandidato.nextLine()

        println("DIGITE O CPF DO CANDIDATO - SOMENTE NÚMEROS")
        long cpf = Long.parseLong(inputCandidato.nextLine())

        println("DIGITE O CEP DO CANDIDATO - SOMENTE NÚMEROS")
        int cep = Integer.parseInt(inputCandidato.nextLine())

        println('-' * 20)
        List listaPaises = paisController.listaPaises()
        for(int posicao = 0; posicao < listaPaises.size(); posicao++) {
            println(listaPaises[posicao])
        }
        println('-' * 20)
        println("DIGITE O PAÍS DO CANDIDATO")
        String pais = inputCandidato.nextLine()
        while (!listaPaises.contains(pais)) {
            println("País inválido, digite novamente!")
            pais = inputCandidato.nextLine()
        }

        println("DIGITE A DISCRIÇÃO PESSOAL DO CANDIDATO")
        String descricaoPessoal = inputCandidato.nextLine()

        println("DIGITE A SENHA DO CANDIDATO")
        String senha = inputCandidato.nextLine()

        List<CompetenciaInterface> listaDeCompetencias = new ArrayList()
        println("DIGITE AS COMPETÊNCIAS DO CANDIDATO")
        String competencia = inputCandidato.nextLine()
        listaDeCompetencias.add(competencia)

        Scanner inputCompetencia = new Scanner(System.in)
        int opcaoCompetencia = 2
        while (opcaoCompetencia != 0) {
            println("DESEJA ADICIONAR OUTRA COMPETÊNCIA?\n" +
                    "1 - SIM\n" +
                    "0 - NÃO")
            opcaoCompetencia = inputCompetencia.nextInt()
            if (opcaoCompetencia == 1) {
                println("DIGITE OUTRA COMPETÊNCIA")
                competencia = inputCandidato.nextLine()
                listaDeCompetencias << competencia
            }
            if (opcaoCompetencia == 0) {
                break
            }
        }

        candidato.nome = nome
        candidato.email = email
        candidato.dataNascimento = dataNascimento
        candidato.cpf = cpf
        candidato.cep = cep
        candidato.pais = pais
        candidato.descricaoPessoal = descricaoPessoal
        candidato.senha = senha

        if(candidatoDAO.buscaIdCandidato(cpf) == null) {
            candidatoController.recebeDadosNovoCandidato(candidato)
            competenciaController.recebeDadosNovaCompetencia(cpf, listaDeCompetencias)
        } else {
            println("CandidatoEntidade já cadastrado com esse CPF!")
        }
    }

    void perfilDoCandidato() {
        Scanner inputCpf = new Scanner(System.in)

        println("INFORME SEU CPF:")
        long cpf = inputCpf.nextLong()

        println(candidatoController.exibePerfilCandidato(cpf))
    }

    void vagasDisponiveis() {
        List lista = vagaController.listaVagasGerais()

        for(int posicao = 0; posicao < lista.size(); posicao++) {
            println(lista[posicao])
            println('-' * 100)
        }
    }

    void curtirVaga() {
        Scanner inputCurtirVaga = new Scanner(System.in)

        println("INFORME O CPF:")
        long cpf = inputCurtirVaga.nextLong()

        println("INFORME O ID DA VAGA:")
        int idVaga = inputCurtirVaga.nextInt()

        candidatoController.curtirVaga(cpf, idVaga)
    }

    void matchsDoCandidato() {
        Scanner inputCpfCandidato = new Scanner(System.in)

        println("INFORME SEU CPF:")
        long cpf = inputCpfCandidato.nextLong()

        List lista = candidatoController.listaMatchsCandidato(cpf)

        for(int posicao = 0; posicao < lista.size(); posicao++) {
            println(lista[posicao])
        }
    }
}
