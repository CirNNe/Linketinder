export function criarTagDivVaga(vaga: any) {
    let div = document.createElement('div')
    div.id = vaga.id

    let ul = document.createElement("ul")
    ul.classList.add("estilo-ul")

    let pNomeVaga = document.createElement('p')
    pNomeVaga.innerHTML = 'Nome:'
    pNomeVaga.classList.add('estilo-p')
    let liNomeVagas = document.createElement('li')
    liNomeVagas.classList.add('dados-usuario')
    liNomeVagas.innerHTML = vaga.nome

    let pEmpresaVaga = document.createElement('p')
    pEmpresaVaga.innerHTML = 'Empresa:'
    pEmpresaVaga.classList.add('estilo-p')
    let liEmpresaVagas = document.createElement('li')
    liEmpresaVagas.classList.add('dados-usuario')
    liEmpresaVagas.innerHTML = vaga.empresa

    let pDescricaoVaga = document.createElement('p')
    pDescricaoVaga.innerHTML = 'Descrição:'
    pDescricaoVaga.classList.add('estilo-p')
    let liDescricaoVagas = document.createElement('li')
    liDescricaoVagas.classList.add('dados-usuario')
    liDescricaoVagas.innerHTML = vaga.descricao

    let pCompetenciasVaga = document.createElement('p')
    pCompetenciasVaga.innerHTML = 'Competências:'
    pCompetenciasVaga.classList.add('estilo-p')
    let liCompetenciasVagas = document.createElement('li')
    liCompetenciasVagas.classList.add('dados-usuario')
    liCompetenciasVagas.innerHTML = vaga.competencias

    let br = document.createElement("br")

    ul.appendChild(pNomeVaga)
    ul.appendChild(liNomeVagas)
    ul.appendChild(pEmpresaVaga)
    ul.appendChild(liEmpresaVagas)
    ul.appendChild(pDescricaoVaga)
    ul.appendChild(liDescricaoVagas)
    ul.appendChild(pCompetenciasVaga)
    ul.appendChild(liCompetenciasVagas)
    div.appendChild(ul)
    div.appendChild(br)

    return div
}