package com.example.linketinderwebteste.Backend.Model.DAO.Interface

import com.example.linketinderwebteste.Backend.Model.Entidade.Candidato
import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.CandidatoInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Match

interface CandidatoDAOInterface {
    Integer buscaIdCandidato(long cpf)

    List<Candidato> buscaListaCandidatos()

    Candidato buscaPerfilUnicoCandidato(long cpf)

    boolean insereDadosCandidato(CandidatoInterface candidato)

    boolean insereCurtiAVaga(long cpf, int idVaga)

    List<Match> buscaMatchsCandidato(long cpf)
}
