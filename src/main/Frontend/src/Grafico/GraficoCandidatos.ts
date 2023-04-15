const canvas = document.getElementById("myChart") as HTMLCanvasElement

let listaCandidatosLocalStorage = localStorage.getItem('candidatos') as string
let git: number = 0
let metodologiasAgeis: number = 0
let testes: number = 0
let java: number = 0
let groovy: number = 0
let javascript: number = 0
let typescript: number = 0
if(listaCandidatosLocalStorage) {

    const listaCandidatosJson = JSON.parse(listaCandidatosLocalStorage)
    for(let competencias = 0; competencias < listaCandidatosJson.length; competencias++) {
        let listaCompetencias = listaCandidatosJson[competencias].competencias.split(',')
        for(let capturaCompetencia = 0; capturaCompetencia < listaCompetencias.length; capturaCompetencia++) {
            if(listaCompetencias[capturaCompetencia] == "Git") {
                git += 1
            }
            if(listaCompetencias[capturaCompetencia] == "Metodologias Ageis") {
                metodologiasAgeis += 1
            }
            if(listaCompetencias[capturaCompetencia] == "Testes") {
                testes += 1
            }
            if(listaCompetencias[capturaCompetencia] == "Java") {
                java += 1
            }
            if(listaCompetencias[capturaCompetencia] == "Groovy") {
                groovy += 1
            }
            if(listaCompetencias[capturaCompetencia] == "JavaScript") {
                javascript += 1
            }
            if(listaCompetencias[capturaCompetencia] == "TypeScript") {
                typescript += 1
            }

        }
    }
}

const labels = [
    'Git',
    'Metodologias Ágeis',
    'Testes',
    'Java',
    'Groovy',
    'JavaScript',
    'TypeScript'
]

const data = {
    labels,
    datasets: [{
        data: [git, metodologiasAgeis, testes, java, groovy, javascript, typescript],
        label: "Gráfico Competências dos Candidatos"
    }]
}

const config = {
    type: 'doughnut',
    data: data,
    options: {
        responsive: true,
        soucemap: false
    }
}

const myChart = new Chart(canvas, config)
