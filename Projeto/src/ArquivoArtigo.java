import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArquivoArtigo {
	// Caminho do arquivo onde os artigos serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/artigos.csv";
    private static final String CAMINHO_ARQUIVO_backup = "Arquivos/backup/artigos.csv";

    // Método para gravar os artigos no arquivo
    public static void gravarArtigos(Biblioteca biblioteca) {
        List<Item> itens = biblioteca.getListaItem();

        // Cria uma cópia do arquivo atual como backup
        File arquivoBackup = new File(CAMINHO_ARQUIVO_backup + ".backup");
        File arquivoAtual = new File(CAMINHO_ARQUIVO);

        // Verifica se o arquivo atual existe
        if (arquivoAtual.exists()) {
            try {
                Files.copy(arquivoAtual.toPath(), arquivoBackup.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Backup do arquivo anterior criado com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao criar o backup do arquivo anterior: " + e.getMessage());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoAtual))) {
            // Escreve os nomes das colunas no início do arquivo
            writer.write("Título, Autor, Editora, Data de Publicação, Gênero, DOI, Exemplares, Disponível");
            writer.newLine();

            // Itera sobre os artigos e os escreve no arquivo
            for (Item item : itens) {
                if (item instanceof Artigo) {
                    Artigo artigo = (Artigo) item;
                    writer.write(artigo.toCsvString());
                    writer.newLine();
                }
            }
            System.out.println("Artigos gravados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gravar artigos: " + e.getMessage());
        }
    }

    
 // Método para ler o arquivo CSV
    public String lerArquivoCSV(File arquivo, Bibliotecario bibliotecario) {
        StringBuilder conteudo = new StringBuilder();

        // Verifica se o arquivo existe
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;

                // Lê a primeira linha do arquivo (cabeçalho) e descarta
                reader.readLine();

                // Lê cada linha do arquivo a partir da segunda linha
                while ((linha = reader.readLine()) != null) {
                    // Quebra a linha em campos usando a vírgula como separador
                    String[] campos = linha.split(",");

                    // Verifica se há campos suficientes
                    if (campos.length >= 8) {
                        String titulo = campos[0].trim();
                        String autor = campos[1].trim();
                        String editora = campos[2].trim();
                        String dataString = campos[3].trim();
                        String genero = campos[4].trim();
                        String doi = campos[5].trim();
                        int exemplares = Integer.parseInt(campos[6].trim());                      

                        // Cria uma instância de Artigo com os dados da linha
                        Artigo artigo = new Artigo(titulo, autor, editora, parseData(dataString), genero, doi, exemplares);

                        // Verifica se a data é nula antes de adicionar o artigo à biblioteca
                        if (artigo.getData() != null) {
                            // Adiciona o artigo à biblioteca (você pode ajustar o nome do objeto Bibliotecario conforme necessário)
                            bibliotecario.adicionarItem(artigo);
                        } else {
                            System.out.println("Data de publicação inválida para o artigo: " + titulo);
                        }
                    } else {
                        System.out.println("Formato inválido da linha no arquivo CSV: " + linha);
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo não encontrado.");
        }

        // Retorna o conteúdo do arquivo como uma string
        System.out.println("A lista de artigos foi lida com sucesso!");
        return conteudo.toString();
    }

    
    // Método auxiliar para converter uma string em uma data
    private Date parseData(String dataString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(dataString);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data.");
            return null;
        }
    }

    
}
