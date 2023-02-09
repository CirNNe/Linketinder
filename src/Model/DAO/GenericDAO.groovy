package Model.DAO

/**
 * Classe abstrata responsável pelos métodos comuns as outras classes DAO
 */
abstract class GenericDAO {

    /**
     * Método escreve dados no arquivo
     * @param tabelaBD: arquivo onde será escrito os dados
     * @param parametros: parâmetros que serão escritos no arquivo
     */
    static escreveNaTabela(File tabelaBD, Object... parametros) {

        try (FileWriter escreverArquivo = new FileWriter(tabelaBD, true)
             BufferedWriter escreveTarefa = new BufferedWriter(escreverArquivo)) {

            for (int contador = 0; contador < parametros.size(); contador++) {
                escreveTarefa.write(parametros[contador] as String + ";")
            }
            escreveTarefa.write("\n")

        } catch (Exception error) {
            println("ERRO AO TENTAR SALVA NO BANCO DE DADOS! ERRO: " + error)
        }

    }

    /**
     * Método responsável pela leitura dos dados de um arquivo
     * @param tabelaBD: arquivo que será lido
     * @param lista: lista onde serão instanciados os dados do arquivo
     */
    static leATabela(File tabelaBD, List lista) {

        try (FileReader leitorArquivo = new FileReader(tabelaBD);
             BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

            String linha = bufferedReader.readLine();
            while (linha != null && !linha.isEmpty()) {

                lista.add(linha)
                linha = bufferedReader.readLine();

            }
        }
    }
}
