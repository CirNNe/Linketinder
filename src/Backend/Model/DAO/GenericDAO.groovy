package Backend.Model.DAO

/**
 * Classe abstrata responsável pelos métodos comuns as outras classes DAO
 */
abstract class GenericDAO<T> {

    /**
     * Método escreve dados no arquivo
     * @param tabelaBD: arquivo onde será escrito os dados
     * @param dados: dados que serão escritos no arquivo
     */
    void escreveNaTabela(File tabelaBD, Object dados) throws Exception {

        try (FileWriter escreverArquivo = new FileWriter(tabelaBD, true)
             BufferedWriter escreveTarefa = new BufferedWriter(escreverArquivo)) {

            escreveTarefa.write(dados as String)
            escreveTarefa.write("\n")

        } catch (Exception error) {
            throw new Exception("Erro ao tentar escrever na tabela: " + error.getMessage())
        }
    }

    /**
     * Método responsável pela leitura dos dados de um arquivo
     * @param tabelaBD: arquivo que será lido
     * @param lista: lista onde serão instanciados os dados do arquivo
     */
    void leATabela(File tabelaBD, List lista) throws Exception {

        try (FileReader leitorArquivo = new FileReader(tabelaBD);
             BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {
            String linha = bufferedReader.readLine();

            while (linha != null && !linha.isEmpty()) {
                lista.add(linha)
                linha = bufferedReader.readLine();
            }

        } catch (Exception error) {
            throw new Exception("Erro ao tentar ler a tabela: " + error.getMessage())
        }
    }
}
