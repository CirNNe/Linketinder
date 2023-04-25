package com.example.linketinderwebteste.Backend.Viewer

CandidatoViewer candidatoViewer = new CandidatoViewer()
EmpresaViewer empresaViewer = new EmpresaViewer()

Scanner inputOpcaoUsuario = new Scanner(System.in)
int opcaoUsuario = 3
while(opcaoUsuario != 0) {

    println("----- MENU USUÁRIO -----\n" +
            "1 - CANDIDATO\n" +
            "2 - EMPRESA\n" +
            "0 - SAIR")

    opcaoUsuario = inputOpcaoUsuario.nextInt()

    if(opcaoUsuario == 1) {
        candidatoViewer.menuCandidato()
    }

    else if(opcaoUsuario == 2) {
        empresaViewer.menuEmpresa()
    }

    else if (opcaoUsuario == 0) {
        break
    }
}
