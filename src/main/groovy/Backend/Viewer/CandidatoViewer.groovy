package Backend.Viewer

import Backend.Controller.CandidatoController
import Backend.Controller.CompetenciaController
import Backend.Controller.Inteface.CandidatoControllerInterface
import Backend.Controller.Inteface.CompetenciaControllerInterface
import Backend.Controller.Inteface.VagaControllerInterface
import Backend.Controller.VagaController
import Backend.Model.DAO.CandidatoDAO
import Backend.Model.DAO.CompetenciaDAO
import Backend.Model.DAO.ConexaoBancoDados
import Backend.Model.DAO.EmpresaDAO
import Backend.Model.DAO.GenericDAO
import Backend.Model.DAO.Interface.CandidatoDAOInterface
import Backend.Model.DAO.Interface.CompetenciaDAOInterface
import Backend.Model.DAO.Interface.ConexaoBancoDadosInterface
import Backend.Model.DAO.Interface.EmpresaDAOInterface
import Backend.Model.DAO.Interface.GenericDAOInterface
import Backend.Model.DAO.Interface.PaisDAOInterface
import Backend.Model.DAO.Interface.VagaDAOInterface
import Backend.Model.DAO.PaisDAO
import Backend.Model.DAO.VagaDAO
import Backend.Model.Entidade.Candidato
import Backend.Model.Entidade.Competencia
import Backend.Model.Entidade.Empresa
import Backend.Model.Entidade.Interface.CandidatoInterface
import Backend.Model.Entidade.Interface.CompetenciaInterface
import Backend.Model.Entidade.Interface.EmpresaInterface
import Backend.Model.Entidade.Interface.VagaInterface
import Backend.Model.Entidade.Vaga
import Backend.Service.CandidatoService
import Backend.Service.CompetenciaService
import Backend.Service.Interface.CandidatoServiceInterface
import Backend.Service.Interface.CompetenciaServiceInterface
import Backend.Service.Interface.VagaServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface
import Backend.Service.VagaService
import Backend.Service.ValidatorService
import Backend.Util.Regex.RegexValidaDadosNovaVaga
import Backend.Util.Regex.RegexValidaDadosNovoUsuario

class CandidatoViewer {

    ConexaoBancoDadosInterface conexaoBancoDados = new ConexaoBancoDados()
    GenericDAOInterface genericDAO = new GenericDAO(conexaoBancoDados)

    PaisDAOInterface paisDAO = new PaisDAO(conexaoBancoDados)
    RegexValidaDadosNovoUsuario regexUsuario = new RegexValidaDadosNovoUsuario()
    RegexValidaDadosNovaVaga regexVaga = new RegexValidaDadosNovaVaga()

    EmpresaDAOInterface empresaDAO = new EmpresaDAO(conexaoBancoDados, genericDAO, paisDAO)

    VagaDAOInterface vagaDAO = new VagaDAO(genericDAO, empresaDAO, paisDAO, conexaoBancoDados)
    VagaServiceInterface vagaService = new VagaService(vagaDAO, validatorService)

    ValidatorServiceInterface validatorService = new ValidatorService(regexUsuario, regexVaga, vagaDAO, candidatoDAO, empresaDAO)
    CompetenciaDAOInterface competenciaDAO = new CompetenciaDAO(conexaoBancoDados, candidatoDAO, genericDAO, validatorService)
    CompetenciaServiceInterface competenciaService = new CompetenciaService(competenciaDAO, validatorService)

    CandidatoDAOInterface candidatoDAO = new CandidatoDAO(conexaoBancoDados, genericDAO, vagaDAO, paisDAO)
    CandidatoServiceInterface candidatoService = new CandidatoService(candidatoDAO, validatorService)

    CandidatoControllerInterface candidatoController = new CandidatoController(candidatoService)
    VagaControllerInterface vagaController = new VagaController(vagaService)
    CompetenciaControllerInterface competenciaController = new CompetenciaController(competenciaService)

    CandidatoInterface candidato = new Candidato()
    CompetenciaInterface competencia = new Competencia()

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

        println("DIGITE O PAÍS DO CANDIDATO")
        String pais = inputCandidato.nextLine()

        println("DIGITE A DISCRIÇÃO PESSOAL DO CANDIDATO")
        String descricaoPessoal = inputCandidato.nextLine()

        println("DIGITE A SENHA DO CANDIDATO")
        String senha = inputCandidato.nextLine()

        List<CompetenciaInterface> listaDeCompetencias = new ArrayList()
        println("DIGITE AS COMPETÊNCIAS DO CANDIDATO")
        competencia.nome = inputCandidato.nextLine()
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
                competencia.nome = inputCandidato.nextLine()
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
            Integer id = candidatoDAO.buscaIdCandidato(cpf)
            competenciaController.recebeDadosNovaCompetencia(id, cpf, listaDeCompetencias)
        } else {
            println("Candidato já cadastrado com esse CPF!")
        }
    }

    void perfilDoCandidato() {
        Scanner inputCpf = new Scanner(System.in)

        println("INFORME SEU CPF:")
        long cpf = inputCpf.nextLong()

        candidatoController.exibePerfilCandidato(cpf)
    }

    void vagasDisponiveis() {
        vagaController.listaVagasGerais()
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

        candidatoController.listaMatchsCandidato(cpf)
    }
}
