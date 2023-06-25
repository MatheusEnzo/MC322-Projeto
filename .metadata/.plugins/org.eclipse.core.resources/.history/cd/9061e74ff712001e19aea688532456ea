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

public class ArquivoRevista {
	// Caminho do arquivo onde as revista serão gravadas
    private static final String CAMINHO_ARQUIVO = "Arquivos/revistas.csv";

    // Método para gravar as revistas no arquivo
    public static void gravarRevistas(List<Revista> revistas) {
    	// Itera sobre os artigos e os escreve no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            // Escreve os nomes das colunas no início do arquivo
            writer.write("Título, Autor, Editora, Data de Publicação, Gênero, Páginas, ISSN, Formato");
            writer.newLine();
            
            // Itera sobre os artigos e os escreve no arquivo
            for (Revista revista : revistas) {
                writer.write(revista.toCsvString());
                writer.newLine();
            }
            System.out.println("Revistas gravadas com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gravar revistas: " + e.getMessage());
        }
    }

    // Método para ler os artigos do arquivo
    public static List<Revista> lerRevistas() {
        List<Revista> revistas = new ArrayList<>();
        File arquivo = new File(CAMINHO_ARQUIVO);

        // Verifica se o arquivo existe
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
                String linha;
                // Lê cada linha do arquivo e cria os objetos Revista correspondentes
                while ((linha = reader.readLine()) != null) {
                    try {
                        Revista revista = Revista.fromCsvString(linha);
                        revistas.add(revista);
                    } catch (ParseException e) {
                        System.out.println("Erro ao ler revista: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler revistas: " + e.getMessage());
            }
        }

        return revistas;
    }
    
    // APENAS PARA TESTE, TIRAR ISSO DEPOIS
    public static void main(String[] args) {
        List<Revista> revistas = new ArrayList<>();
        Date data = new Date();
        Revista revista = new Revista("titulo", "autor", "editora", data, "genero", 50, "14359802190", "Digital");
        revistas.add(revista);
        ArquivoRevista.gravarRevistas(revistas);
    }
    
}
