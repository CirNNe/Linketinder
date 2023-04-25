package com.example.linketinderwebteste.Backend.Controller

import com.example.linketinderwebteste.Backend.Controller.Inteface.CandidatoControllerInterface
import com.example.linketinderwebteste.Backend.Controller.Inteface.CompetenciaControllerInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Candidato
import com.example.linketinderwebteste.Backend.Model.Entidade.Competencia
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CandidatoInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CompetenciaInterface
import com.example.linketinderwebteste.Backend.Service.CandidatoService
import com.example.linketinderwebteste.Backend.Service.CompetenciaService
import com.example.linketinderwebteste.Backend.Service.Interface.CandidatoServiceInterface
import com.example.linketinderwebteste.Backend.Service.Interface.CompetenciaServiceInterface
import groovy.json.JsonSlurper
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/candidato")
class CandidatoController extends HttpServlet implements CandidatoControllerInterface{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BufferedReader leitor = req.reader
            Map<String, String> dadosCandidato = new JsonSlurper().parseText(leitor.text) as Map<String, String>

            CandidatoInterface candidato = new Candidato()
            candidato.nome = dadosCandidato["nome"]
            candidato.email = dadosCandidato["email"]
            candidato.dataNascimento = dadosCandidato["nascimento"]
            candidato.pais = dadosCandidato["pais"]
            candidato.cpf = Long.parseLong(dadosCandidato["cpf"])
            candidato.cep = Integer.parseInt(dadosCandidato["cep"])
            candidato.descricaoPessoal = dadosCandidato["descricao"]
            candidato.senha = dadosCandidato["senha"]

            List listaCompetencias = dadosCandidato["competencias"] as List

            CandidatoServiceInterface candidatoService = new CandidatoService()
            candidatoService.salvaDadosNovoCandidato(candidato)
            CompetenciaServiceInterface competenciaService = new CompetenciaService()
            competenciaService.recebeDadosNovaCompetencia(Long.parseLong(dadosCandidato["cpf"]), listaCompetencias)

            resp.setStatus(201, "Candidato cadastrado com sucesso!")
        } catch (Exception e) {
            resp.sendError(400, "Dados inválidos, por favor, tente novamente! Erro: " + e)
        }
    }

    List listaCandidatos() {
        CandidatoServiceInterface candidatoService = new CandidatoService()
        return candidatoService.exibeListaCandidatos()
    }

    CandidatoInterface exibePerfilCandidato(long cpf) {
        CandidatoServiceInterface candidatoService = new CandidatoService()
        return candidatoService.exibirPerfilCandidato(cpf)
    }

    void curtirVaga(long cpf, int idVaga) {
        CandidatoServiceInterface candidatoService = new CandidatoService()
        candidatoService.salvaCurtidaDoCadidato(cpf, idVaga)
    }

    List listaMatchsCandidato(long cpf) {
        CandidatoServiceInterface candidatoService = new CandidatoService()
        return candidatoService.exibeListaMatchsCandidato(cpf)
    }
}
