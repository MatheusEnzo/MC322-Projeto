import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ArquivoMembro {
	// Caminho do arquivo onde os membros serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/membros.csv";

    // Método para gravar os membros no arquivo
    public static void gravarMembros(Biblioteca biblioteca) {
    	List<Usuario> usuarios = new ArrayList<>();
    	usuarios = biblioteca.getListaUsuario();
    	
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
    	
    	// Itera sobre os membros e os escreve no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
        	// Escreve os nomes das colunas no início do arquivo
            writer.write("Nome, Endereço, CPF, Email, Telefone");
            writer.newLine();

            for (Usuario usuario : usuarios) {
            	if (usuario instanceof Membro) {
            		Membro membro = (Membro) usuario;
                    writer.write(membro.toCsvString());
                    writer.newLine();
            	}

            }
            System.out.println("Membros gravados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gravar membros: " + e.getMessage());
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
                        String nome = tokenizer.nextToken();
                        // Verifica novamente antes de chamar nextToken()
                        if (tokenizer.hasMoreTokens()) {
                            String endereco = tokenizer.nextToken();
                            // Verifica novamente antes de chamar nextToken()
                            if (tokenizer.hasMoreTokens()) {
                                String cpf = tokenizer.nextToken();
                                // Verifica novamente antes de chamar nextToken()
                                if (tokenizer.hasMoreTokens()) {
                                    String email = tokenizer.nextToken();
                                    // Verifica novamente antes de chamar nextToken()
                                    if (tokenizer.hasMoreTokens()) {
                                        String telefone = tokenizer.nextToken();

                                        // Cria uma instância de Membro com os dados da linha
                                        Membro membro = new Membro(nome, endereco, cpf, email, telefone);
                                        // Adiciona o membro à biblioteca
                                        bibliotecario.cadastrarMembro(membro);
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
        System.out.println("A lista de membros foi lida com sucesso!");
        return conteudo.toString();
    }
}
