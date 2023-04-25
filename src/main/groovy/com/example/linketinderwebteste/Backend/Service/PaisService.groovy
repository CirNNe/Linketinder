package com.example.linketinderwebteste.Backend.Service

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.PaisInterface
import com.example.linketinderwebteste.Backend.Model.Entidade.Pais
import com.example.linketinderwebteste.Backend.Service.Interface.PaisServiceInterface

class PaisService implements PaisServiceInterface {

    private PaisInterface pais = new Pais()

    List recebeListaPaises() {
        try {
            List lista = pais.getPaises()
            return lista
        } catch (Exception e) {
            throw new Exception("Erro ao tentar receber a lista de países: " + e)
        }
    }
}
