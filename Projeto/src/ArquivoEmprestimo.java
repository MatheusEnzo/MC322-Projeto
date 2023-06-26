import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ArquivoEmprestimo {
	// Caminho do arquivo onde os emprestimos serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/emprestimos.csv";
    

    // Método para gravar os emprestimos no arquivo
    public static void gravarEmprestimos(Biblioteca biblioteca) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        emprestimos = biblioteca.getListaEmprestimo();

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
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
        	// Escreve os nomes das colunas no início do arquivo
            writer.write("Item emprestado, CPF do membro, Data do empréstimo, Data da devolução");
            writer.newLine();

            // Itera sobre os emprestimos e os escreve no arquivo
            for (Emprestimo emprestimo : emprestimos) {
            	writer.write(emprestimo.toCsvString());
                writer.newLine();

            }
            System.out.println("Empréstimos gravados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gravar empréstimos: " + e.getMessage());
        }
    }

    // Método para ler o arquivo
    public String lerArquivo(Bibliotecario bibliotecario) {
        StringBuilder conteudo = new StringBuilder();
        Biblioteca biblioteca = bibliotecario.getBiblioteca();
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
                        String itemTitulo = tokenizer.nextToken();
                        // Verifica se há mais elementos antes de chamar nextToken()
                        if (tokenizer.hasMoreTokens()) {
                            String membroCPF = tokenizer.nextToken();
                            // Verifica se há mais elementos antes de chamar nextToken()
                            if (tokenizer.hasMoreTokens()) {
                            	String dataEmprestimoString = tokenizer.nextToken();
                                LocalDate dataEmprestimo = LocalDate.parse(dataEmprestimoString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                // Verifica se há mais elementos antes de chamar nextToken()
                                if (tokenizer.hasMoreTokens()) {
                                    String dataDevolucaoString = tokenizer.nextToken();
                                    LocalDate dataDevolucao = LocalDate.parse(dataDevolucaoString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                    
                                    Item itemEmprestado = buscarItemPorTitulo(itemTitulo, biblioteca);
                                    Membro membro = buscarMembroPorCPF(membroCPF, biblioteca);

                                    // Cria uma instância de Emprestimo com os dados da linha
                                    Emprestimo emprestimo = new Emprestimo(itemEmprestado, membro, dataEmprestimo, dataDevolucao);
                                    
                                    biblioteca.getListaEmprestimo().add(emprestimo); // Emprestimo adicionado à lista de emprestimos da biblioteca
                                    membro.getEmprestimos().add(emprestimo); // Emprestimo adicionado à lista de emprestimos do membro
                            		int numero = itemEmprestado.getExemplares() - 1;
                            		itemEmprestado.setExemplares(numero);
                            		if(itemEmprestado.getExemplares()==0)
                            		{
                            			itemEmprestado.setDisponivel(false);
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

        System.out.println("A lista de empréstimos foi lida com sucesso!");
        return conteudo.toString();
    }

    private Item buscarItemPorTitulo(String itemTitulo, Biblioteca biblioteca) {
        for (Item item : biblioteca.getListaItem()) {
            if (item.getTitulo().equals(itemTitulo)) {
                return item;
            }
        }
        return null;
    }


    private Membro buscarMembroPorCPF(String membroCPF, Biblioteca biblioteca) {
        for (Usuario usuario : biblioteca.getListaUsuario()) { // Percorre a lista de usuarios da biblioteca
        	if (usuario instanceof Membro) { // Caso o usuario for um Membro
        		Membro membro = (Membro) usuario; // Conversão para membro
        		if (membro.getCpf().equals(membroCPF)) { // Verifica se o CPF do membro passado existe na lista
        			return membro;
        		} 
        		else {
        			continue;
        		}
        	}
        	else {
        		continue;
        	}
        }
        return null;
    }

}