import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class ArquivoLivro {
	// Caminho do arquivo onde os livros serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/livros.csv";

    // Método para gravar os livros no arquivo
    public static void gravarLivros(List<Livro> livros) {
    	// Itera sobre os artigos e os escreve no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            // Escreve os nomes das colunas no início do arquivo
            writer.write("Título, Autor, Editora, Data de Publicação, Gênero, Páginas, ISBN, Formato");
            writer.newLine();
            
            // Itera sobre os livros e os escreve no arquivo
            for (Livro livro : livros) {
                writer.write(livro.toCsvString());
                writer.newLine();
            }
            System.out.println("Livros gravados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gravar livros: " + e.getMessage());
        }
    }

    // Método para ler os artigos do arquivo
    public static List<Livro> lerLivros() {
        List<Livro> livros = new ArrayList<>();
        File arquivo = new File(CAMINHO_ARQUIVO);

        // Verifica se o arquivo existe
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
                String linha;
                // Lê cada linha do arquivo e cria os objetos Livro correspondentes
                while ((linha = reader.readLine()) != null) {
                    try {
                        Livro livro = Livro.fromCsvString(linha);
                        livros.add(livro);
                    } catch (ParseException e) {
                        System.out.println("Erro ao ler artigo: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler livros: " + e.getMessage());
            }
        }

        return livros;
    }
    
    public String lerArquivo() {
        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;

            // Lê cada linha do arquivo
            while ((linha = reader.readLine()) != null) {
                // Quebra a linha em campos usando a vírgula como separador
                StringTokenizer tokenizer = new StringTokenizer(linha, ",");
                
                // Extrai os campos da linha
                String titulo = tokenizer.nextToken();
                String autor = tokenizer.nextToken();
                String editora = tokenizer.nextToken();
                Date data = parseData(tokenizer.nextToken());
                String genero = tokenizer.nextToken();
                String isbn = tokenizer.nextToken();
                String formato = tokenizer.nextToken();

                // Cria uma instância de Livro com os dados da linha
                Livro livro = new Livro(titulo, autor, editora, data, genero, isbn, formato);

                // Adiciona o livro ao conteúdo
                conteudo.append(livro.toString()).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
        }

        // Retorna o conteúdo do arquivo como uma string
        return conteudo.toString();
    }

    // Método auxiliar para converter uma string em uma data
    private Date parseData(String dataString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
            return dateFormat.parse(dataString);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data.");
            return null;
        }
    }

    
    // APENAS PARA TESTE, TIRAR ISSO DEPOIS
    public static void main(String[] args) {
//        List<Livro> livros = new ArrayList<>();
//        Date data = new Date();
//        Livro livro = new Livro("titulo", "autor", "editora", data, "genero", "14359802190", "Físico");
//        livros.add(livro);
//        ArquivoLivro.gravarLivros(livros);
        ArquivoLivro arquivo = new ArquivoLivro();
        String conteudoArquivo = arquivo.lerArquivo();
        System.out.println(conteudoArquivo);
    }
    
}
