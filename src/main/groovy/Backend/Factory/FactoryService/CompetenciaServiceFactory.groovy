package Backend.Factory.FactoryService

import Backend.Model.DAO.CompetenciaDAO
import Backend.Model.DAO.Interface.CompetenciaDAOInterface
import Backend.Service.CompetenciaService
import Backend.Service.Interface.CompetenciaServiceInterface

class CompetenciaServiceFactory {

    CompetenciaDAOInterface competenciaDAO = new CompetenciaDAO()
    ValidatorServiceFactory validatorServiceFactory = new ValidatorServiceFactory()

    CompetenciaServiceInterface criaCompetenciaService() {

        CompetenciaServiceInterface competenciaService = new CompetenciaService(competenciaDAO,
                                                                        validatorServiceFactory.criaValidatorService())
        return competenciaService
    }
}
