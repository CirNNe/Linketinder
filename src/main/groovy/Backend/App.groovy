package Backend

import Backend.Controller.CandidatoController
import Backend.Controller.EmpresaController
import Backend.Controller.VagaController

CandidatoController candidatoController = new CandidatoController()
EmpresaController empresaController = new  EmpresaController()
VagaController vagaController = new VagaController()

Scanner inputOpcaoUsuario = new Scanner(System.in)
int opcaoUsuario = 3
while(opcaoUsuario != 0) {

    println("----- MENU USUÁRIO -----\n" +
            "1 - CANDIDATO\n" +
            "2 - EMPRESA\n" +
            "0 - SAIR")

    opcaoUsuario = inputOpcaoUsuario.nextInt()

    if(opcaoUsuario == 1) {

        Scanner inputOpcaoCandidato = new Scanner(System.in)
        int opcaoCandidato = 3

        while(opcaoCandidato != 0) {

            println("----- MENU CANDIDATO -----\n" +
                    "1 - CADASTRAR CANDIDATO\n" +
                    "2 - MOSTRAR PERFIL DO CANDIDATO\n" +
                    "3 - LISTAR VAGAS\n" +
                    "4 - CURTIR VAGA\n" +
                    "5 - LISTAR MATCH'S\n" +
                    "0 - SAIR")

            opcaoCandidato = inputOpcaoCandidato.nextInt()

            if (opcaoCandidato == 1) {

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

                println("DIGITE O PAÍS DO CANDIDATO")
                String idPais = inputCandidato.nextLine()

                println("DIGITE A DISCRIÇÃO PESSOAL DO CANDIDATO")
                String descricaoPessoal = inputCandidato.nextLine()

                println("DIGITE A SENHA DO CANDIDATO")
                String senha = inputCandidato.nextLine()

                List listaDeCompetencias = new ArrayList()
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

                candidatoController.recebeDadosCandidatos(nome, email, dataNascimento, cpf, cep, idPais, descricaoPessoal, senha)
                candidatoController.recebeDadosCompetenciasCandidato(cpf, listaDeCompetencias)

            }

            else if (opcaoCandidato == 2) {

                Scanner inputCpf = new Scanner(System.in)

                println("INFORME SEU CPF:")
                long cpf = inputCpf.nextLong()

                candidatoController.perfilCandidato(cpf)

            }

            else if (opcaoCandidato == 3) {

                vagaController.listaVagasGerais()

            }

            else if (opcaoCandidato == 4) {

                Scanner inputCurtirVaga = new Scanner(System.in)

                println("INFORME O CPF:")
                long cpf = inputCurtirVaga.nextLong()

                println("INFORME O ID DA VAGA:")
                int idVaga = inputCurtirVaga.nextInt()

                candidatoController.curtirVaga(cpf, idVaga)

            }

            else if (opcaoCandidato == 5) {

                Scanner inputCpfCandidato = new Scanner(System.in)

                println("INFORME SEU CPF:")
                long cpf = inputCpfCandidato.nextLong()

                candidatoController.listaMatchsCandidato(cpf)

            }

            else if (opcaoCandidato == 0) {
                break
            }
        }
    }

    else if(opcaoUsuario == 2) {

        Scanner inputOpcaoEmpresa = new Scanner(System.in)
        int opcaoEmpresa = 3

        while(opcaoEmpresa != 0) {

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

                Scanner inputEmpresa = new Scanner(System.in)

                println("DIGITE O NOME DA EMPRESA")
                String nome = inputEmpresa.nextLine()

                println("DIGITE O EMAIL CORPORATIVO DA EMPRESA")
                String emailCorporativo = inputEmpresa.nextLine()

                println("DIGITE O CNPJ DA EMPRESA")
                long cnpj = Long.parseLong(inputEmpresa.nextLine())

                println("DIGITE O CEP DA EMPRESA")
                Integer cep = Integer.parseInt(inputEmpresa.nextLine())

                println("DIGITE O PAÍS DA EMPRESA")
                String pais = inputEmpresa.nextLine()

                println("DIGITE A DISCRIÇÃO DA EMPRESA")
                String descricaoEmpresa = inputEmpresa.nextLine()

                println("DIGITE A SENHA DA EMPRESA")
                String senha = inputEmpresa.nextLine()

                empresaController.recebeDados(nome, emailCorporativo, cnpj, cep, pais, descricaoEmpresa, senha)

            }

            else if (opcaoEmpresa == 2) {
                Scanner inputCNPJ = new Scanner(System.in)

                println("INFORME O CNPJ DA EMPRESA: ")
                long cnpj = inputCNPJ.nextLong()

                empresaController.perfilEmpresa(cnpj)
            }

            else if (opcaoEmpresa == 3) {

                Scanner inputVaga = new Scanner(System.in)

                println("DIGITE O NOME DA VAGA")
                String nome = inputVaga.nextLine()

                println("DIGITE O NOME DA EMPRESA")
                String empresa = inputVaga.nextLine()

                println("DIGITE O CNPJ DA EMPRESA")
                long cnpj = Long.parseLong(inputVaga.nextLine())

                println("DIGITE A DESCRIÇÃO DA VAGA")
                String descricao = inputVaga.nextLine()

                println("DIGITE O PAÍS DE ATUAÇÃO")
                String pais = inputVaga.nextLine()

                List listaDeCompetencias = new ArrayList()
                println("DIGITE AS COMPETÊNCIAS PARA VAGA")
                String competenciaVaga = inputVaga.nextLine()
                listaDeCompetencias.add(competenciaVaga)

                while (true) {
                    Scanner inputCompetencia = new Scanner(System.in)
                    int opcaoCompetencia
                    println("DESEJA ADICIONAR OUTRA COMPETÊNCIA?\n" +
                            "1 - SIM\n" +
                            "0 - NÃO")
                    opcaoCompetencia = inputCompetencia.nextInt()
                    if (opcaoCompetencia == 1) {
                        println("DIGITE OUTRA COMPETÊNCIA")
                        competencia = inputVaga.nextLine()
                        listaDeCompetencias << competenciaVaga
                    }
                    if (opcaoCompetencia == 0) {
                        break
                    }
                }
                vagaController.recebeDadosVaga(nome, empresa, cnpj, descricao, pais)
                vagaController.recebeDadosCompetenciasVaga(cnpj, listaDeCompetencias)
            }

            else if (opcaoEmpresa == 4) {

                Scanner inputCnpj = new Scanner(System.in)

                println("INFORME O CNPJ DA EMPRESA")
                long cnpj = inputCnpj.nextLong()

                vagaController.listaVagasEmpresa(cnpj)

            }

            else if (opcaoEmpresa == 5) {
                candidatoController.listaCandidatos()
            }

            else if (opcaoEmpresa == 6) {

                Scanner inputCnpj = new Scanner(System.in)

                println("INFORME SEU CNPJ")
                long cnpj = inputCnpj.nextLong()

                println("INFORME O ID DO CANDIDATO QUE DESEJA CURTIR")
                int idCandidato = inputCnpj.nextInt()

                empresaController.curtirCandidato(cnpj, idCandidato)

            }

            else if (opcaoEmpresa == 7) {

                Scanner inputCnpjEmpresa = new Scanner(System.in)

                println("INFORME O CNPJ DA EMPRESA:")
                long cnpj = inputCnpjEmpresa.nextLong()

                empresaController.listaMatchsEmpresa(cnpj)

            }

            else if (opcaoEmpresa == 0) {
                break
            }
        }
    }
}
