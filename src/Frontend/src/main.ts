document.addEventListener('DOMContentLoaded', function () {

    /* CANDIDATO */
    const telaOpcoesCandidato = document.querySelector("#conteudo-candidato") as HTMLDivElement
    const telaCadastroCandidato = document.querySelector("#conteudo-cadastro-candidato") as HTMLDivElement
    const telaVagas = document.querySelector("#conteudo-vagas") as HTMLDivElement

    const botaoOpcaoCadastrarCandidato = document.querySelector("#botao-escolha-cadatrar-candidato") as HTMLButtonElement
    const botaoOpcaoVagasDisponiveis = document.querySelector("#botao-escolha-vagas") as HTMLButtonElement
    const botaoCadastrarNovoCandidato = document.querySelector("#botao-cadastrar-novo-candidato") as HTMLButtonElement

    const inputNomeNovoCandidato = document.querySelector("#nome-novo-candidato") as HTMLInputElement
    const inputEmailNovoCandidato = document.querySelector("#email-novo-candidato") as HTMLInputElement
    const inputIdadeNovoCandidato = document.querySelector("#idade-novo-candidato") as HTMLInputElement
    const inputCpfNovoCandidato = document.querySelector("#cpf-novo-candidato") as HTMLInputElement
    const inputCepNovoCandidato = document.querySelector("#cep-novo-candidato") as HTMLInputElement
    const inputDescricaoPessoalNovoCandidato = document.querySelector("#descricao-pessoal-novo-candidato") as HTMLInputElement
    const inputCompetencias = document.getElementsByName("competencias-novo-candidato") as unknown as Array<any>

    /* EMPRESA */
    const telaOpcoesEmpresa = document.querySelector("#conteudo-empresa") as HTMLDivElement
    const telaCadastroEmpresa = document.querySelector("#conteudo-cadastro-empresa") as HTMLDivElement
    const telaCadastroVaga = document.querySelector("#conteudo-cadastro-vaga")! as HTMLDivElement
    const telaListaCandidatos = document.querySelector("#conteudo-lista-candidatos") as HTMLDivElement

    const botaoOpcaoCadastrarEmpresa = document.querySelector("#botao-escolha-cadastrar-empresa") as HTMLButtonElement
    const botaoOpcaoCadastrarVaga = document.querySelector("#botao-escolha-cadastrar-vaga") as HTMLButtonElement
    const botaoOpcaoListaCandidatos = document.querySelector("#botao-escolha-lista-candidatos") as HTMLButtonElement
    const botaoCadastrarNovaVaga = document.querySelector("#botao-cadastrar-nova-vaga")! as HTMLButtonElement

    const inputNomeNovaVaga = document.querySelector("#nome-nova-vaga") as HTMLInputElement
    const inputEmpresaNovaVaga = document.querySelector("#empresa-nova-vaga") as HTMLInputElement
    const inputDescricaoNovaVaga = document.querySelector("#descricao-nova-vaga") as HTMLInputElement
    const inputCompetenciasNovaVaga = document.getElementsByName("competencias-nova-vaga") as unknown as Array<any>




    /* EMPRESA */
    if (botaoOpcaoCadastrarEmpresa) {
        botaoOpcaoCadastrarEmpresa.addEventListener('click', function () {
            telaOpcoesEmpresa.style.display = "none"
            telaCadastroEmpresa.style.display = "block"
        })
    }

    if (botaoOpcaoCadastrarVaga) {
        botaoOpcaoCadastrarVaga.addEventListener('click', function () {
            telaOpcoesEmpresa.style.display = "none"
            telaCadastroVaga.style.display = "block"
        })
    }

    if(botaoCadastrarNovaVaga) {
        botaoCadastrarNovaVaga.addEventListener('click', function () {
            const competenciasSelecionadasNovaVaga = [] as Array<string>
            inputCompetenciasNovaVaga.forEach((competencias) => {
                if(competencias.checked) {
                    competenciasSelecionadasNovaVaga.push(competencias.value)
                }
            })

            let vaga = {
                id: gerarId(),
                nome: inputNomeNovaVaga.value,
                empresa: inputEmpresaNovaVaga.value,
                descricao: inputDescricaoNovaVaga.value,
                competencias: competenciasSelecionadasNovaVaga.toString()
            }
            salvarVagaLocalStorage(vaga)
            inputNomeNovaVaga.value = ""
            inputEmpresaNovaVaga.value = ""
            inputDescricaoNovaVaga.value = ""
            inputCompetenciasNovaVaga.forEach((competencia) => {
                competencia.value = ""
            })
            telaCadastroVaga.style.display = "none"
            telaOpcoesEmpresa.style.display = "block"
        })
    }

    function salvarVagaLocalStorage(vaga: object) {
        let listaVagas = localStorage.getItem('vagas') as string
        if(listaVagas) {
            let listaVagasJson = []
            listaVagasJson = JSON.parse(listaVagas)
            listaVagasJson.push(vaga)
            localStorage.setItem('vagas', JSON.stringify(listaVagasJson))
        }
        else {
            localStorage.setItem('vagas', '[]')
            let listaVagasJson = []
            listaVagasJson = JSON.parse(listaVagas)
            listaVagasJson.push(vaga)
            localStorage.setItem('vagas', JSON.stringify(listaVagasJson))
        }
    }

    function mostrarListaVagas() {
        const listaVagasLocalStorage = localStorage.getItem('vagas') as string
        const listaVagas = JSON.parse(listaVagasLocalStorage)
        if (listaVagasLocalStorage) {
            for(let posicao = 0; posicao < listaVagas.length; posicao++) {
                let div = criarTagDivVaga(listaVagas[posicao])
                telaVagas.appendChild(div)
            }
        }
    }

    if(botaoOpcaoVagasDisponiveis) {
        botaoOpcaoVagasDisponiveis.addEventListener('click', function () {
            mostrarListaVagas()
            telaOpcoesCandidato.style.display = 'none'
            telaVagas.style.display = 'block'
        })
    }

    /* CANDIDATO */

    if (botaoOpcaoCadastrarCandidato) {
        botaoOpcaoCadastrarCandidato.addEventListener('click', function () {
            telaOpcoesCandidato.style.display = "none"
            telaCadastroCandidato.style.display = "block"
        })
    }

    if (botaoOpcaoVagasDisponiveis) {
        botaoOpcaoVagasDisponiveis.addEventListener('click', function () {
            telaOpcoesCandidato.style.display = "none"
            telaVagas.style.display = "block"
        })
    }

    if (botaoCadastrarNovoCandidato) {
        botaoCadastrarNovoCandidato.addEventListener('click', function () {
            const competenciasSelecionadas = [] as Array<string>
            inputCompetencias.forEach((competencias) => {
                if(competencias.checked) {
                    competenciasSelecionadas.push(competencias.value)
                }
            })
            let candidato = {
                id: gerarId(),
                nome: inputNomeNovoCandidato.value,
                email: inputEmailNovoCandidato.value,
                idade: inputIdadeNovoCandidato.value,
                cpf: inputCpfNovoCandidato.value,
                cep: inputCepNovoCandidato.value,
                descricao: inputDescricaoPessoalNovoCandidato.value,
                competencias: competenciasSelecionadas.toString()
            }
            salvarCandidatoLocalStorage(candidato)
            inputNomeNovoCandidato.value = ""
            inputEmailNovoCandidato.value = ""
            inputIdadeNovoCandidato.value = ""
            inputCpfNovoCandidato.value = ""
            inputCepNovoCandidato.value = ""
            inputDescricaoPessoalNovoCandidato.value = ""
            inputCompetencias.forEach((competencia) => {
                competencia.value = ""
            })
            telaCadastroCandidato.style.display = "none"
            telaOpcoesCandidato.style.display = "block"
        })
    }

    function salvarCandidatoLocalStorage(candidato: object) {
        let listaCandidatos = localStorage.getItem('candidatos') as string
        if(listaCandidatos) {
            let listaCandidatosJson = []
            listaCandidatosJson = JSON.parse(listaCandidatos)
            listaCandidatosJson.push(candidato)
            localStorage.setItem('candidatos', JSON.stringify(listaCandidatosJson))
        }
        else {
            localStorage.setItem('candidatos', '[]')
            let listaCandidatosJson = []
            listaCandidatosJson = JSON.parse(listaCandidatos)
            listaCandidatosJson.push(candidato)
            localStorage.setItem('candidatos', JSON.stringify(listaCandidatosJson))
        }
    }

    function mostrarListaCandidatos() {
        const listaCandidatosLocalStorage = localStorage.getItem('candidatos') as string
        const listaCandidatos = JSON.parse(listaCandidatosLocalStorage)
        if(listaCandidatosLocalStorage) {
            for (let posicao = 0; posicao < listaCandidatos.length; posicao++) {
                let div = criaTagDivCandidato(listaCandidatos[posicao])
                telaListaCandidatos.appendChild(div)
            }
        }
    }

    if(botaoOpcaoListaCandidatos) {
        botaoOpcaoListaCandidatos.addEventListener('click', function () {
            mostrarListaCandidatos()
            telaOpcoesEmpresa.style.display = 'none'
            telaListaCandidatos.style.display = 'block'
        })
    }

    function gerarId() {
        return Math.floor(Math.random() * 3000)
    }

    function criaTagDivCandidato(candidato: any) {
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

        let pEmailCandidato = document.createElement('p')
        pEmailCandidato.innerHTML = 'E-mail:'
        pEmailCandidato.classList.add('estilo-p')

        let liEmailCandidato = document.createElement('li')
        liEmailCandidato.classList.add('dados-usuario')
        liEmailCandidato.innerHTML = candidato.email

        let pIdadeCandidato = document.createElement('p')
        pIdadeCandidato.innerHTML = 'Idade:'
        pIdadeCandidato.classList.add('estilo-p')

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

        let pCompetenciasCandidato = document.createElement('p')
        pCompetenciasCandidato.innerHTML = 'Competências:'
        pCompetenciasCandidato.classList.add('estilo-p')

        let liCompetenciasCandidato = document.createElement('li')
        liCompetenciasCandidato.classList.add('dados-usuario')
        liCompetenciasCandidato.innerHTML = candidato.competencias

        let br = document.createElement("br")

        ul.appendChild(pNomeCandidato)
        ul.appendChild(liNomeCandidato)
        ul.appendChild(pEmailCandidato)
        ul.appendChild(liEmailCandidato)
        ul.appendChild(pIdadeCandidato)
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

    function criarTagDivVaga(vaga: any) {
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

})
