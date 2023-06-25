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

public class ArquivoLivro {
	// Caminho do arquivo onde os livros serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/livros.csv";

    // Método para gravar os livros no arquivo
    public static void gravarLivros(Biblioteca biblioteca) {
    	List<Item> itens = new ArrayList<>();
    	itens = biblioteca.getListaItem();
    	
    	// Itera sobre os artigos e os escreve no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            // Escreve os nomes das colunas no início do arquivo
            writer.write("Título, Autor, Editora, Data de Publicação, Gênero, ISBN, Exemplares, Disponível");
            writer.newLine();
            
            // Itera sobre os livros e os escreve no arquivo
            for (Item item : itens) {
            	if (item instanceof Livro) {
            		Livro livro = (Livro) item;
                    writer.write(livro.toCsvString());
                    writer.newLine();
            	}

            }
            System.out.println("Livros gravados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gravar livros: " + e.getMessage());
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
                                            String isbn = tokenizer.nextToken();
                                            // Verifica novamente antes de chamar nextToken()
                                            if (tokenizer.hasMoreTokens()) {
                                                int exemplares = Integer.parseInt(tokenizer.nextToken());
                                                
                                                // Cria uma instância de Livro com os dados da linha
                                                Livro livro = new Livro(titulo, autor, editora, parseData(dataString), genero, isbn, exemplares);
                                                
                                                // Verifica se a data é nula antes de adicionar o livro à biblioteca
                                                if (livro.getData() != null) {
                                                    // Adiciona o livro à biblioteca
                                                    bibliotecario.adicionarItem(livro);
                                                } else {
                                                    System.out.println("Data de publicação inválida para o livro: " + titulo);
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
        return conteudo.toString();
    }

    
    // Método auxiliar para converter uma string em uma data
    private Date parseData(String dataString) {
        try {
            SimpleDateFormat dateFormat;

            // Verifica se a data está no formato "dd/MM/yyyy" ou "MM/yyyy"
            if (dataString.matches("\\d{2}/\\d{2}/\\d{4}")) {
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            } else if (dataString.matches("\\d{2}/\\d{4}")) {
                dateFormat = new SimpleDateFormat("MM/yyyy");
            } else {
                System.out.println("Formato de data inválido: " + dataString);
                return null;
            }

            return dateFormat.parse(dataString);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data: " + e.getMessage());
            return null;
        }
    }

    
    // !!!!! APENAS PARA TESTE, TIRAR ISSO DEPOIS E IMPLEMENTAR NA CLASSE MAIN !!!!!
    public static void main(String[] args) {
    	// Teste da leitura de arquivos
    	LocalTime horario1 = LocalTime.of(9, 0); //HORARIO DE ABERTURA DA BIBLIOTECA
    	LocalTime horario2 = LocalTime.of(18, 0); //HORARIO DE FECHAMENTO DA BIBLIOTECA
    	Biblioteca biblioteca = new Biblioteca("Biblioteca teste", "Rua A, 123", horario1, horario2);
    	Bibliotecario bibliotecario = new Bibliotecario("Jose", "Rua B, 456", "12854091607", "emailteste@gmail.com", "(00) 912345678", biblioteca);
    	biblioteca.getListaUsuario().add(bibliotecario);
    	
        ArquivoLivro arquivo = new ArquivoLivro();
        String conteudoArquivo = arquivo.lerArquivo(bibliotecario);
        System.out.println(conteudoArquivo);
        
        System.out.println("---- LIVROS ----");
        System.out.println(biblioteca.PrintaListaItens());
        
    	//Teste da gravação de arquivos
        ArquivoLivro.gravarLivros(biblioteca);

    }
    
}
