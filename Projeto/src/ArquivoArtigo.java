import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class ArquivoArtigo {
	// Caminho do arquivo onde os artigos serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/artigos.csv";

    // Método para gravar os artigos no arquivo
    public static void gravarArtigos(Biblioteca biblioteca) {
    	List<Item> itens = new ArrayList<>();
    	itens = biblioteca.getListaItem();
    	
    	// Itera sobre os artigos e os escreve no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
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
                                            String doi = tokenizer.nextToken();
                                            // Verifica novamente antes de chamar nextToken()
                                            if (tokenizer.hasMoreTokens()) {
                                            	int exemplares = Integer.parseInt((tokenizer.nextToken()));
                                            	
                                                // Cria uma instância de Livro com os dados da linha
                                                Artigo artigo = new Artigo(titulo, autor, editora, parseData(dataString), genero, doi, exemplares);
                                                
                                                // Verifica se a data é nula antes de adicionar o artigo à biblioteca
                                                if (artigo.getData() != null) {
                                                    // Adiciona o livro à biblioteca
                                                    bibliotecario.adicionarItem(artigo);
                                                } else {
                                                    System.out.println("Data de publicação inválida para o artigo: " + titulo);
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
