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

public class ArquivoMembro {
	// Caminho do arquivo onde os membros serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/membros.csv";
    private static final String CAMINHO_ARQUIVO_backup = "Arquivos/backup/membros.csv";

    // Método para gravar os membros no arquivo
    public static void gravarMembros(Biblioteca biblioteca) {
    	List<Usuario> usuarios = new ArrayList<>();
    	usuarios = biblioteca.getListaUsuario();
    	
        // Cria uma cópia do arquivo atual como backup
        File arquivoBackup = new File(CAMINHO_ARQUIVO_backup + ".backup");
        File arquivoAtual = new File(CAMINHO_ARQUIVO);
        
        // Verifica se o arquivo atual existe
        if (arquivoAtual.exists()) {
            try {
                Files.copy(arquivoAtual.toPath(), arquivoBackup.toPath(), StandardCopyOption.REPLACE_EXISTING);
                InterfaceGrafica.exibirMensagem("Backup do arquivo anterior criado com sucesso.");
            } catch (IOException e) {
            	InterfaceGrafica.exibirMensagem("Erro ao criar o backup do arquivo anterior: " + e.getMessage());
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
            InterfaceGrafica.exibirMensagem("Membros gravados com sucesso.");
        } catch (IOException e) {
        	InterfaceGrafica.exibirMensagem("Erro ao gravar membros: " + e.getMessage());
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
                    if (campos.length >= 5) {
                        String nome = campos[0].trim();
                        String endereco = campos[1].trim();
                        String cpf = campos[2].trim();
                        String email = campos[3].trim();
                        String telefone = campos[4].trim();

                        // Cria uma instância de Membro com os dados da linha
                        Membro membro = new Membro(nome, endereco, cpf, email, telefone);
                        // Adiciona o membro à biblioteca (ajuste o nome do objeto Bibliotecario conforme necessário)
                        bibliotecario.cadastrarMembro(membro);
                    } else {
                    	InterfaceGrafica.exibirMensagem("Formato inválido da linha no arquivo CSV: " + linha);
                    }
                }
            } catch (IOException e) {
            	InterfaceGrafica.exibirMensagem("Erro ao ler o arquivo: " + e.getMessage());
            }
        } else {
        	InterfaceGrafica.exibirMensagem("Arquivo não encontrado.");
        }

        // Retorna o conteúdo do arquivo como uma string
        InterfaceGrafica.exibirMensagem("A lista de membros foi lida com sucesso!");
        return conteudo.toString();
    }

}
