import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    // APENAS PARA TESTE, TIRAR ISSO DEPOIS
    public static void main(String[] args) {
        List<Livro> livros = new ArrayList<>();
        Date data = new Date();
        Livro livro = new Livro("titulo", "autor", "editora", data, "genero", 50, "14359802190", "Físico");
        livros.add(livro);
        ArquivoLivro.gravarLivros(livros);
    }
    
}
