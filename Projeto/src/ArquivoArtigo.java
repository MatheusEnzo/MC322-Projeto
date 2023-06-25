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

public class ArquivoArtigo {
	// Caminho do arquivo onde os artigos serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/artigos.csv";

    // Método para gravar os artigos no arquivo
    public static void gravarArtigos(List<Artigo> artigos) {
    	// Itera sobre os artigos e os escreve no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            // Escreve os nomes das colunas no início do arquivo
            writer.write("Título, Autor, Editora, Data de Publicação, Gênero, Páginas, DOI");
            writer.newLine();
            
            // Itera sobre os artigos e os escreve no arquivo
            for (Artigo artigo : artigos) {
                writer.write(artigo.toCsvString());
                writer.newLine();
            }
            System.out.println("Artigos gravados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gravar artigos: " + e.getMessage());
        }
    }

    // Método para ler os artigos do arquivo
    public static List<Artigo> lerArtigos() {
        List<Artigo> artigos = new ArrayList<>();
        File arquivo = new File(CAMINHO_ARQUIVO);

        // Verifica se o arquivo existe
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
                String linha;
                // Lê cada linha do arquivo e cria os objetos Artigo correspondentes
                while ((linha = reader.readLine()) != null) {
                    try {
                        Artigo artigo = Artigo.fromCsvString(linha);
                        artigos.add(artigo);
                    } catch (ParseException e) {
                        System.out.println("Erro ao ler artigo: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler artigos: " + e.getMessage());
            }
        }

        return artigos;
    }
    
    // APENAS PARA TESTE, TIRAR ISSO DEPOIS
    public static void main(String[] args) {
        List<Artigo> artigos = new ArrayList<>();
        Date data = new Date();
        Artigo artigo = new Artigo("titulo", "autor", "editora", data, "genero", "14359802190");
        artigos.add(artigo);
        ArquivoArtigo.gravarArtigos(artigos);
    }
    
}
