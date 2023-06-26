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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class ArquivoRevista {
	// Caminho do arquivo onde as revista serão gravadas
    private static final String CAMINHO_ARQUIVO = "Arquivos/revistas.csv";

    // Método para gravar as revistas no arquivo
    public static void gravarRevistas(Biblioteca biblioteca) {
    	List<Item> itens = new ArrayList<>();
    	itens = biblioteca.getListaItem();
    	
        // Cria uma cópia do arquivo atual como backup
        File arquivoBackup = new File(CAMINHO_ARQUIVO + ".backup");
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
    	
    	// Itera sobre os artigos e os escreve no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            // Escreve os nomes das colunas no início do arquivo
            writer.write("Título, Autor, Editora, Data de Publicação, Gênero, ISSN, Exemplares, Disponível");
            writer.newLine();
            
            // Itera sobre as revistas e os escreve no arquivo
            for (Item item : itens) {
            	if (item instanceof Revista) {
            		Revista revista = (Revista) item;
                    writer.write(revista.toCsvString());
                    writer.newLine();
            	}

            }
            System.out.println("Revistas gravadas com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gravar revistas: " + e.getMessage());
        }
    }

    // Método para ler o arquivo
    public String lerArquivo(Bibliotecario bibliotecario) {
        StringBuilder conteudo = new StringBuilder();

        File arquivo = new File(CAMINHO_ARQUIVO);

        // Verifica se o arquivo existe
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;

                // Lê a primeira linha do arquivo (cabeçalho) e descarta
                reader.readLine();

                // Lê cada linha do arquivo a partir da segunda linha
                while ((linha = reader.readLine()) != null) {
                    // Quebra a linha em campos usando a vírgula como separador
                    StringTokenizer tokenizer = new StringTokenizer(linha, ",");

                    // Verifica se há mais elementos antes de chamar nextToken()
                    if (tokenizer.hasMoreTokens()) {
                        String titulo = tokenizer.nextToken();
                        // Verifica novamente antes de chamar nextToken()
                        if (tokenizer.hasMoreTokens()) {
                            String autor = tokenizer.nextToken();
                            // Verifica novamente antes de chamar nextToken()
                            if (tokenizer.hasMoreTokens()) {
                                String editora = tokenizer.nextToken();
                                // Verifica novamente antes de chamar nextToken()
                                if (tokenizer.hasMoreTokens()) {
                                    String dataString = tokenizer.nextToken();
                                    // Verifica novamente antes de chamar nextToken()
                                    if (tokenizer.hasMoreTokens()) {
                                        String genero = tokenizer.nextToken();
                                        // Verifica novamente antes de chamar nextToken()
                                        if (tokenizer.hasMoreTokens()) {
                                            String issn = tokenizer.nextToken();
                                            // Verifica novamente antes de chamar nextToken()
                                            if (tokenizer.hasMoreTokens()) {
                                            	int exemplares = Integer.parseInt((tokenizer.nextToken()));
                                            	
                                                // Cria uma instância de Livro com os dados da linha
                                                Revista revista = new Revista(titulo, autor, editora, parseData(dataString), genero, issn, exemplares);
                                                
                                                // Verifica se a data é nula antes de adicionar a revista à biblioteca
                                                if (revista.getData() != null) {
                                                    // Adiciona a revista à biblioteca
                                                    bibliotecario.adicionarItem(revista);
                                                } else {
                                                    System.out.println("Data de publicação inválida para a revista: " + titulo);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo não encontrado.");
        }

        // Retorna o conteúdo do arquivo como uma string
        System.out.println("A lista de revistas foi lida com sucesso!");
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
