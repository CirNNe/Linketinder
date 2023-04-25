package com.example.linketinderwebteste.Backend.Viewer

import com.example.linketinderwebteste.Backend.Controller.CandidatoController
import com.example.linketinderwebteste.Backend.Controller.CompetenciaController
import com.example.linketinderwebteste.Backend.Controller.EmpresaController
import com.example.linketinderwebteste.Backend.Controller.Inteface.CandidatoControllerInterface
import com.example.linketinderwebteste.Backend.Controller.Inteface.CompetenciaControllerInterface
import com.example.linketinderwebteste.Backend.Controller.Inteface.EmpresaControllerInterface
import com.example.linketinderwebteste.Backend.Controller.Inteface.PaisControllerInterface
import com.example.linketinderwebteste.Backend.Controller.Inteface.VagaControllerInterface
import com.example.linketinderwebteste.Backend.Controller.PaisController
import com.example.linketinderwebteste.Backend.Controller.VagaController
import com.example.linketinderwebteste.Backend.Model.Entidade.Competencia
import com.example.linketinderwebteste.Backend.Model.Entidade.Empresa
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CompetenciaInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.EmpresaInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.VagaInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Vaga

class EmpresaViewer {

    EmpresaControllerInterface empresaController = new EmpresaController()
    CompetenciaControllerInterface competenciaController = new CompetenciaController()
    PaisControllerInterface paisController = new PaisController()
    VagaControllerInterface vagaController = new VagaController()
    CandidatoControllerInterface candidatoController = new CandidatoController()

    EmpresaInterface empresa = new Empresa()
    VagaInterface vaga = new Vaga()
    CompetenciaInterface competencia = new Competencia()

    void menuEmpresa() {
        Scanner inputOpcaoEmpresa = new Scanner(System.in)
        int opcaoEmpresa = 8

        while (opcaoEmpresa != 0) {

            println("----- MENU EMPRESA -----\n" +
                    "1 - CADASTRAR EMPRESA\n" +
                    "2 - MOSTRAR PERFIL EMPRESA\n" +
                    "3 - CADASTRAR VAGA\n" +
                    "4 - MOSTRAR VAGAS DA EMPRESA\n" +
                    "5 - LISTAR CANDIDATOS\n" +
                    "6 - CURTIR CANDIDATO\n" +
                    "7 - LISTAR MATCH'S\n" +
                    "0 - SAIR")
            opcaoEmpresa = inputOpcaoEmpresa.nextInt()

            if (opcaoEmpresa == 1) {
                formularioCadastroEmpresa()
            } else if (opcaoEmpresa == 2) {
                perfilDaEmpresa()
            } else if (opcaoEmpresa == 3) {
                formularioCadastroVaga()
            } else if (opcaoEmpresa == 4) {
                vagasDaEmpresa()
            } else if (opcaoEmpresa == 5) {
                listaDeCandidatos()
            } else if (opcaoEmpresa == 6) {
                curtirCandidato()
            } else if (opcaoEmpresa == 7) {
                matchsDaEmpresa()
            } else if (opcaoEmpresa == 0) {
                break
            }
        }
    }

    void formularioCadastroEmpresa() {
        Scanner inputEmpresa = new Scanner(System.in)

        println("DIGITE O NOME DA EMPRESA")
        String nome = inputEmpresa.nextLine()

        println("DIGITE O EMAIL CORPORATIVO DA EMPRESA")
        String email = inputEmpresa.nextLine()

        println("DIGITE O CNPJ DA EMPRESA")
        long cnpj = Long.parseLong(inputEmpresa.nextLine())

        println("DIGITE O CEP DA EMPRESA")
        int cep = Integer.parseInt(inputEmpresa.nextLine())

        println('-' * 20)
        List listaPaises = paisController.listaPaises()
        for(int posicao = 0; posicao < listaPaises.size(); posicao++) {
            println(listaPaises[posicao])
        }
        println('-' * 20)
        println("DIGITE O PAÍS DA EMPRESA")
        String pais = inputEmpresa.nextLine()
        while (!listaPaises.contains(pais)) {
            println("País inválido, digite novamente!")
            pais = inputEmpresa.nextLine()
        }

        println("DIGITE A DISCRIÇÃO DA EMPRESA")
        String descricao = inputEmpresa.nextLine()

        println("DIGITE A SENHA DA EMPRESA")
        String senha = inputEmpresa.nextLine()

        empresa.nome = nome
        empresa.email = email
        empresa.cnpj = cnpj
        empresa.cep = cep
        empresa.pais = pais
        empresa.descricao = descricao
        empresa.senha = senha

        if(empresaDAO.buscaIdEmpresa(cnpj) == null) {
            empresaController.recebeDadosNovaEmpresa(empresa)
        } else {
            println("Já existe uma empresa cadastrada com esse cnpj: " + cnpj)
        }
    }

    void perfilDaEmpresa() {
        Scanner inputCNPJ = new Scanner(System.in)

        println("INFORME O CNPJ DA EMPRESA: ")
        long cnpj = inputCNPJ.nextLong()

        println(empresaController.perfilEmpresa(cnpj))
    }

    void formularioCadastroVaga() {
        Scanner inputVaga = new Scanner(System.in)

        println("DIGITE O NOME DA VAGA")
        String nome = inputVaga.nextLine()

        println("DIGITE O CNPJ DA EMPRESA")
        long cnpj = Long.parseLong(inputVaga.nextLine())

        println("DIGITE A DESCRIÇÃO DA VAGA")
        String descricao = inputVaga.nextLine()

        println('-' * 20)
        List listaPaises = paisController.listaPaises()
        for(int posicao = 0; posicao < listaPaises.size(); posicao++) {
            println(listaPaises[posicao])
        }
        println('-' * 20)
        println("DIGITE O PAÍS DE ATUAÇÃO DA VAGA")
        String pais = inputVaga.nextLine()
        while (!listaPaises.contains(pais)) {
            println("País inválido, digite novamente!")
            pais = inputVaga.nextLine()
        }

        List<CompetenciaInterface> listaDeCompetencias = new ArrayList()
        println("DIGITE AS COMPETÊNCIAS PARA VAGA")
        competencia.nome = inputVaga.nextLine()
        listaDeCompetencias << competencia
        while (true) {
            Scanner inputCompetencia = new Scanner(System.in)
            int opcaoCompetencia
            println("DESEJA ADICIONAR OUTRA COMPETÊNCIA?\n" +
                    "1 - SIM\n" +
                    "0 - NÃO")
            opcaoCompetencia = inputCompetencia.nextInt()
            if (opcaoCompetencia == 1) {
                println("DIGITE OUTRA COMPETÊNCIA")
                competencia.nome = inputVaga.nextLine()
                listaDeCompetencias << competencia
            }
            if (opcaoCompetencia == 0) {
                break
            }
        }

        vaga.nome = nome
        vaga.cnpj = cnpj
        vaga.pais = pais
        vaga.descricao = descricao

        if(empresaDAO.buscaIdEmpresa(cnpj) != null) {
            vagaController.recebeDadosVaga(vaga)
            Integer id = vagaDAO.buscaIdVaga(nome)
            competenciaController.recebeDadosNovaCompetencia(id, cnpj, listaDeCompetencias)
        } else {
            println("Não existe empresa cadastrada com esse cnpj: " + cnpj)
        }
    }

    void vagasDaEmpresa() {
        Scanner inputCnpj = new Scanner(System.in)

        println("INFORME O CNPJ DA EMPRESA")
        long cnpj = inputCnpj.nextLong()

        List lista = vagaController.listaVagasEmpresa(cnpj)

        for(int posicao = 0; posicao < lista.size(); posicao++) {
            println(lista[posicao])
            println('-' * 100)
        }
    }

    void listaDeCandidatos() {
        List lista = candidatoController.listaCandidatos()
        for(int posicao = 0; posicao < lista.size(); posicao++) {
            println(lista[posicao])
        }
    }

    void curtirCandidato() {
        Scanner inputCnpj = new Scanner(System.in)

        println("INFORME SEU CNPJ")
        long cnpj = inputCnpj.nextLong()

        println("INFORME O ID DO CANDIDATO QUE DESEJA CURTIR")
        int idCandidato = inputCnpj.nextInt()

        empresaController.curtirCandidato(cnpj, idCandidato)
    }

    void matchsDaEmpresa() {
        Scanner inputCnpjEmpresa = new Scanner(System.in)

        println("INFORME O CNPJ DA EMPRESA:")
        long cnpj = inputCnpjEmpresa.nextLong()

        List lista = empresaController.listaMatchsEmpresa(cnpj)

        for(int posicao = 0; posicao < lista.size(); posicao++) {
            println(lista[posicao])
        }
    }
}
