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
    public static void gravarArtigos(List<Artigo> artigos) {
    	// Itera sobre os artigos e os escreve no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            // Escreve os nomes das colunas no início do arquivo
            writer.write("Título, Autor, Editora, Data de Publicação, Gênero, DOI");
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
                                            
                                            // Cria uma instância de Livro com os dados da linha
                                            Artigo artigo = new Artigo(titulo, autor, editora, parseData(dataString), genero, doi);
                                            // Adiciona o livro à biblioteca
                                            bibliotecario.adicionarItem(artigo);
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(dataString);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data.");
            return null;
        }
    }

    
    // APENAS PARA TESTE, TIRAR ISSO DEPOIS
    public static void main(String[] args) {        
    	//Teste da gravação de arquivos
        List<Artigo> artigos = new ArrayList<>();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data1, data2, data3;
        try {
            data1 = dateFormat.parse("01/01/2003");
            data2 = dateFormat.parse("11/07/2000");
            data3 = dateFormat.parse("22/12/1990");
            Artigo artigo1 = new Artigo("titulo1", "autor1", "editora1", data1, "genero1", "10.1435/9802190"); //Doi válido
            Artigo artigo2 = new Artigo("titulo2", "autor2", "editora2", data2, "genero2", "10.993/53302156"); //Doi inválido
            Artigo artigo3 = new Artigo("titulo3", "autor3", "editora3", data3, "genero3", "76.5983/02128"); //Doi inválido
            artigos.add(artigo1);
            artigos.add(artigo2);
            artigos.add(artigo3);
            ArquivoArtigo.gravarArtigos(artigos);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data: " + e.getMessage());
        }
        
    	// Teste da leitura de arquivos
    	LocalTime horario1 = LocalTime.of(9, 0);
    	LocalTime horario2 = LocalTime.of(18, 0);
    	Biblioteca biblioteca = new Biblioteca("Biblioteca teste", "Rua A, 123", horario1, horario2);
    	Bibliotecario bibliotecario = new Bibliotecario("Jose", "Rua B, 456", "12854091607", "emailteste@gmail.com", "(00) 912345678", biblioteca);
    	biblioteca.getListaUsuario().add(bibliotecario);
    	
        ArquivoArtigo arquivo = new ArquivoArtigo();
        String conteudoArquivo = arquivo.lerArquivo(bibliotecario);
        System.out.println(conteudoArquivo);
        
        System.out.println("---- ARTIGOS ----");
        System.out.println(biblioteca.PrintaListaItens());

    }
    
    
}
