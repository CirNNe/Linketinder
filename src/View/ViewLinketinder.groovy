package View

import Controller.CandidatoController
import Controller.EmpresaController
import Model.Entidade.Candidato
import Model.Entidade.Empresa

CandidatoController candidatoController = new CandidatoController()
EmpresaController empresaController = new  EmpresaController()


Scanner inputOpcao = new Scanner(System.in)
int opcao = 5

while (opcao != 0) {
    println("----- MENU PRINCIPAL -----\n" +
            "1 - CADASTRAR CANDIDATO\n" +
            "2 - LISTAR CANDIDATOS\n" +
            "3 - CADASTRAR EMPRESA\n" +
            "4 - LISTAR EMPRESAS\n" +
            "0 - SAIR")
    opcao = inputOpcao.nextInt()

    if (opcao == 1) {

        Scanner inputCandidato = new Scanner(System.in)
        Scanner inputIdade = new Scanner(System.in)
        Scanner inputCpf = new Scanner(System.in)
        Scanner inputcep = new Scanner(System.in)

        List listaDeCompetencias = new ArrayList()

        println("DIGITE O NOME DO CANDIDATO")
        String nome = inputCandidato.nextLine()

        println("DIGITE O EMAIL DO CANDIDATO")
        String email = inputCandidato.nextLine()

        println("DIGITE A IDADE DO CANDIDATO")
        int idade = inputIdade.nextInt()

        println("DIGITE O CPF DO CANDIDATO")
        long cpf = inputCpf.nextLong()

        println("DIGITE O CEP DO CANDIDATO")
        int cep = inputcep.nextInt()

        println("DIGITE A DISCRIÇÃO PESSOAL DO CANDIDATO")
        String descricaoPessoal = inputCandidato.nextLine()

        println("DIGITE AS COMPETÊNCIAS DO CANDIDATO")
        String competencia = inputCandidato.nextLine()
        listaDeCompetencias.add(competencia)

        while (true) {
            Scanner inputCompetencia = new Scanner(System.in)
            int opcaoCompetencia
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

        candidatoController.recebeDados(nome, email, idade, cpf, cep, descricaoPessoal, listaDeCompetencias)
    }

    if (opcao == 2) {
        candidatoController.listaCandidatos()
    }

    if (opcao == 3) {

        Scanner inputEmpresa = new Scanner(System.in)
        Scanner inputCnpj = new Scanner(System.in)
        Scanner inputCep = new Scanner(System.in)

        println("DIGITE O NOME DA EMPRESA")
        String nome = inputEmpresa.nextLine()

        println("DIGITE O EMAIL CORPORATIVO DA EMPRESA")
        String emailCorporativo = inputEmpresa.nextLine()

        println("DIGITE O CNPJ DA EMPRESA")
        long cnpj = inputCnpj.nextLong()

        println("DIGITE O CEP DA EMPRESA")
        Integer cep = inputCep.nextInt()

        println("DIGITE O ESTADO DA EMPRESA")
        String estado = inputEmpresa.nextLine()

        println("DIGITE O PAÍS DA EMPRESA")
        String pais = inputEmpresa.nextLine()

        println("DIGITE A DISCRIÇÃO DA EMPRESA")
        String descricaoEmpresa = inputEmpresa.nextLine()

        empresaController.recebeDados(nome, emailCorporativo, cnpj, cep, estado, pais, descricaoEmpresa)

    }

    if (opcao == 4) {
        empresaController.listaEmpresas()
    }

    if (opcao == 0) {
        break
    }
}
