package Backend.Controller.Inteface

import Backend.Model.Entidade.Interface.CompetenciaInterface

interface CompetenciaControllerInterface {

    boolean recebeDadosNovaCompetencia(Integer id, long identificacao, List<CompetenciaInterface> listaCompetencias)

}