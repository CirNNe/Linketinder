package com.example.linketinderwebteste.Backend.Controller

import com.example.linketinderwebteste.Backend.Controller.Inteface.PaisControllerInterface
import com.example.linketinderwebteste.Backend.Service.Interface.PaisServiceInterface
import com.example.linketinderwebteste.Backend.Service.PaisService

class PaisController implements PaisControllerInterface {

    List listaPaises() {
        PaisServiceInterface paisService = new PaisService()
        return paisService.recebeListaPaises()
    }
}
