package Backend.Controller

import Backend.Model.DAO.VagaDAO
import Backend.Model.Entidade.Competencia
import Backend.Model.Entidade.Vaga
import Backend.Service.VagaService

class VagaController {

    VagaService vagaService = new VagaService(new VagaDAO())

    void recebeDadosVaga(nome, empresa, long cnpj, descricao, pais) {

        Vaga vaga = new Vaga()

        vaga.nome = nome
        vaga.empresa = empresa
        vaga.descricao = descricao
        vaga.pais = pais

        vagaService.validaDadosVaga(cnpj, vaga)
    }

    void recebeDadosCompetenciasVaga(long cnpj, List listaDeCompetencias) {

        Competencia competencia = new Competencia()
        for(int posicao = 0; posicao < listaDeCompetencias.size(); posicao++) {
            competencia.nome = listaDeCompetencias[posicao]
            vagaService.validaCompetenciasVaga(cnpj, competencia)
        }
    }

    void listaVagasEmpresa(long cnpj) {
        vagaService.validaEFormataLeituraVagasDaEmpresa(cnpj)
    }

    void listaVagasGerais() {
        vagaService.formataLeituraVagaGerais()
    }
}
