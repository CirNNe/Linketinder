package Backend.Viewer

import Backend.Controller.CandidatoController
import Backend.Controller.CompetenciaController
import Backend.Controller.EmpresaController
import Backend.Controller.Inteface.CandidatoControllerInterface
import Backend.Controller.Inteface.CompetenciaControllerInterface
import Backend.Controller.Inteface.EmpresaControllerInterface
import Backend.Controller.Inteface.PaisControllerInterface
import Backend.Controller.Inteface.VagaControllerInterface
import Backend.Controller.PaisController
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
import Backend.Model.Entidade.Competencia
import Backend.Model.Entidade.Empresa
import Backend.Model.Entidade.Interface.CompetenciaInterface
import Backend.Model.Entidade.Interface.EmpresaInterface
import Backend.Model.Entidade.Interface.VagaInterface
import Backend.Model.Entidade.Vaga
import Backend.Service.CandidatoService
import Backend.Service.CompetenciaService
import Backend.Service.EmpresaService
import Backend.Service.Interface.CandidatoServiceInterface
import Backend.Service.Interface.CompetenciaServiceInterface
import Backend.Service.Interface.EmpresaServiceInterface
import Backend.Service.Interface.PaisServiceInterface
import Backend.Service.Interface.VagaServiceInterface
import Backend.Service.Interface.ValidatorServiceInterface
import Backend.Service.PaisService
import Backend.Service.VagaService
import Backend.Service.ValidatorService
import Backend.Util.Regex.RegexValidaDadosNovaVaga
import Backend.Util.Regex.RegexValidaDadosNovoUsuario

class EmpresaViewer {

    ConexaoBancoDadosInterface conexaoBancoDados = new ConexaoBancoDados()
    GenericDAOInterface genericDAO = new GenericDAO(conexaoBancoDados)

    PaisDAOInterface paisDAO = new PaisDAO(conexaoBancoDados)
    RegexValidaDadosNovoUsuario regexUsuario = new RegexValidaDadosNovoUsuario()
    RegexValidaDadosNovaVaga regexVaga = new RegexValidaDadosNovaVaga()

    EmpresaDAOInterface empresaDAO = new EmpresaDAO(conexaoBancoDados, genericDAO, paisDAO)

    VagaDAOInterface vagaDAO = new VagaDAO(genericDAO, empresaDAO, paisDAO, conexaoBancoDados)
    VagaServiceInterface vagaService = new VagaService(vagaDAO, validatorService)

    ValidatorServiceInterface validatorService = new ValidatorService(regexUsuario, regexVaga, vagaDAO, candidatoDAO,
                                                                        empresaDAO, paisDAO)

    CandidatoDAOInterface candidatoDAO = new CandidatoDAO(conexaoBancoDados, genericDAO, vagaDAO, paisDAO)
    CandidatoServiceInterface candidatoService = new CandidatoService(candidatoDAO, validatorService)

    CompetenciaDAOInterface competenciaDAO = new CompetenciaDAO(conexaoBancoDados, candidatoDAO, genericDAO, validatorService)
    CompetenciaServiceInterface competenciaService = new CompetenciaService(competenciaDAO, validatorService)

    PaisServiceInterface paisService = new PaisService(validatorService, paisDAO)

    CandidatoControllerInterface candidatoController = new CandidatoController(candidatoService)
    VagaControllerInterface vagaController = new VagaController(vagaService)

    EmpresaServiceInterface empresaService = new EmpresaService(empresaDAO, validatorService)
    EmpresaControllerInterface empresaController = new EmpresaController(empresaService)
    CompetenciaControllerInterface competenciaController = new CompetenciaController(competenciaService)
    PaisControllerInterface paisController = new PaisController(paisService)

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
        paisController.listaPaises()
        println('-' * 20)
        println("DIGITE O PAÍS DA EMPRESA")
        String pais = inputEmpresa.nextLine()
        while (!validatorService.validaEscolhaPais(pais)) {
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

        empresaController.perfilEmpresa(cnpj)
    }

    void formularioCadastroVaga() {
        Scanner inputVaga = new Scanner(System.in)

        println("DIGITE O NOME DA VAGA")
        String nome = inputVaga.nextLine()

//        println("DIGITE O NOME DA EMPRESA")
//        String empresa = inputVaga.nextLine()

        println("DIGITE O CNPJ DA EMPRESA")
        long cnpj = Long.parseLong(inputVaga.nextLine())

        println("DIGITE A DESCRIÇÃO DA VAGA")
        String descricao = inputVaga.nextLine()

        println('-' * 20)
        paisController.listaPaises()
        println('-' * 20)
        println("DIGITE O PAÍS DE ATUAÇÃO")
        String pais = inputVaga.nextLine()
        while (!validatorService.validaEscolhaPais(pais)) {
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
        vaga.descricao = descricao
        vaga.pais = pais

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

        vagaController.listaVagasEmpresa(cnpj)
    }

    void listaDeCandidatos() {
        candidatoController.listaCandidatos()
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

        empresaController.listaMatchsEmpresa(cnpj)
    }
}
