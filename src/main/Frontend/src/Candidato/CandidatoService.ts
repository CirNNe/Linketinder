export function criaTagDivCandidato(candidato: any) {
    let div = document.createElement('div')
    div.id = candidato.id

    let ul = document.createElement("ul")
    ul.classList.add("estilo-ul")

    let pNomeCandidato = document.createElement('p')
    pNomeCandidato.innerHTML = 'Nome:'
    pNomeCandidato.classList.add('estilo-p')

    let liNomeCandidato = document.createElement('li')
    liNomeCandidato.classList.add('dados-usuario')
    liNomeCandidato.innerHTML = candidato.nome

    let pEmailEmpresa = document.createElement('p')
    pEmailEmpresa.innerHTML = 'E-mail:'
    pEmailEmpresa.classList.add('estilo-p')

    let liEmailEmpresa = document.createElement('li')
    liEmailEmpresa.classList.add('dados-usuario')
    liEmailEmpresa.innerHTML = candidato.email

    let pIdadeEmpresa = document.createElement('p')
    pIdadeEmpresa.innerHTML = 'Idade:'
    pIdadeEmpresa.classList.add('estilo-p')

    let liIdadeCandidato = document.createElement('li')
    liIdadeCandidato.classList.add('dados-usuario')
    liIdadeCandidato.innerHTML = candidato.idade

    let pCpfCandidato = document.createElement('p')
    pCpfCandidato.innerHTML = 'CPF:'
    pCpfCandidato.classList.add('estilo-p')

    let liCpfCandidato = document.createElement('li')
    liCpfCandidato.classList.add('dados-usuario')
    liCpfCandidato.innerHTML = candidato.cpf

    let pCepCandidato = document.createElement('p')
    pCepCandidato.innerHTML = 'CEP:'
    pCepCandidato.classList.add('estilo-p')

    let liCepCandidato = document.createElement('li')
    liCepCandidato.classList.add('dados-usuario')
    liCepCandidato.innerHTML = candidato.cep

    let pDescricaoCandidato = document.createElement('p')
    pDescricaoCandidato.innerHTML = 'Descrição Pessoal:'
    pDescricaoCandidato.classList.add('estilo-p')

    let liDescricaoCandidato = document.createElement('li')
    liDescricaoCandidato.classList.add('dados-usuario')
    liDescricaoCandidato.innerHTML = candidato.descricao

    let pLinkedinCandidato = document.createElement('p')
    pLinkedinCandidato.innerHTML = 'Linkedin:'
    pLinkedinCandidato.classList.add('estilo-p')

    let liLinkedinCandidato = document.createElement('li')
    liLinkedinCandidato.classList.add('dados-usuario')
    liLinkedinCandidato.innerHTML = candidato.linkedin

    let pCompetenciasCandidato = document.createElement('p')
    pCompetenciasCandidato.innerHTML = 'Competências:'
    pCompetenciasCandidato.classList.add('estilo-p')

    let liCompetenciasCandidato = document.createElement('li')
    liCompetenciasCandidato.classList.add('dados-usuario')
    liCompetenciasCandidato.innerHTML = candidato.competencias

    let br = document.createElement("br")

    ul.appendChild(pNomeCandidato)
    ul.appendChild(liNomeCandidato)
    ul.appendChild(pEmailEmpresa)
    ul.appendChild(liEmailEmpresa)
    ul.appendChild(pIdadeEmpresa)
    ul.appendChild(liIdadeCandidato)
    ul.appendChild(pCpfCandidato)
    ul.appendChild(liCpfCandidato)
    ul.appendChild(pCepCandidato)
    ul.appendChild(liCepCandidato)
    ul.appendChild(pDescricaoCandidato)
    ul.appendChild(liDescricaoCandidato)
    ul.appendChild(pCompetenciasCandidato)
    ul.appendChild(liCompetenciasCandidato)
    div.appendChild(ul)
    div.appendChild(br)

    return div
}