package com.example.linketinderwebteste.Backend.Model.Entidade

import com.example.linketinderwebteste.Backend.Model.Entidade.Interface.PaisInterface

class Pais implements PaisInterface {

    List<String> paises = ['Brasil',
                           'Argentina',
                           'Estados Unidos',
                           'Canada',
                           'Portugual']

    @Override
    String toString() {
        return 'Paises: ' + '\n' +
                '1 - ' + paises[0] + '\n' +
                '2 - ' + paises[1] + '\n' +
                '3 - ' + paises[2] + '\n' +
                '4 - ' + paises[3] + '\n' +
                '5 - ' + paises[4]
    }
}
