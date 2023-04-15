export function salvarEmpresaLocalStorage(empresa: object) {
    let listaEmpresas = localStorage.getItem('empresas') as string
    if(listaEmpresas) {
        let listaEmpresasJson = []
        listaEmpresasJson = JSON.parse(listaEmpresas)
        listaEmpresasJson.push(empresa)
        localStorage.setItem('empresas', JSON.stringify(listaEmpresasJson))
    } else {
        localStorage.setItem('empresas', '[]')
        let listaEmpresasJson = []
        listaEmpresasJson = JSON.parse(listaEmpresas)
        listaEmpresasJson.push(listaEmpresas)
        localStorage.setItem('empresas', JSON.stringify(listaEmpresasJson))
    }
}
