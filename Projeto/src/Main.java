import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
    	// Instanciação de um objeto Biblioteca
    	LocalTime horario1 = LocalTime.of(9, 0); //Horario de abertura (09:00)
    	LocalTime horario2 = LocalTime.of(18, 0); //Horario de fechamento (18:00)
    	Biblioteca biblioteca = new Biblioteca("Biblioteca de Bytes da UNICAMP", "Rua Andrade da Silva, 199", horario1, horario2);
    	
    	// Instanciação de um objeto Bibliotecario (um dos possíveis usuários)
    	Bibliotecario bibliotecario = new Bibliotecario("José Pereira", "Rua Arlindo da Cruz, 456", "650.885.770-36", "josepereira@bibliobytes.com", "(11) 99690-3704", biblioteca);
    	biblioteca.getListaUsuario().add(bibliotecario); // Bibliotecario adicionado à lista de usuários da biblioteca
    	
    	// Leitura dos arquivos que contém os Artigos, Livros, Revistas e Membros da biblioteca
    	ArquivoMembro arquivoMembro = new ArquivoMembro();
        String conteudoArquivoMembro = arquivoMembro.lerArquivo(bibliotecario);
        System.out.println(conteudoArquivoMembro);
    	
    	ArquivoArtigo arquivoArtigo = new ArquivoArtigo();
        String conteudoArquivoArtigo = arquivoArtigo.lerArquivo(bibliotecario);
        System.out.println(conteudoArquivoArtigo);
        
        ArquivoLivro arquivoLivro = new ArquivoLivro();
        String conteudoArquivoLivro = arquivoLivro.lerArquivo(bibliotecario);
        System.out.println(conteudoArquivoLivro);
        
    	ArquivoRevista arquivoRevista = new ArquivoRevista();
        String conteudoArquivoRevista = arquivoRevista.lerArquivo(bibliotecario);
        System.out.println(conteudoArquivoRevista);
        
        ArquivoEmprestimo arquivoEmprestimo = new ArquivoEmprestimo();
        String conteudoArquivoEmprestimo = arquivoEmprestimo.lerArquivo(bibliotecario);
        System.out.println(conteudoArquivoEmprestimo);
               
     
    	// Gravação de arquivos
		// ÚTIL FAZER ALTERAÇÕES NOS MEMBROS,
		// ARTIGOS E LIVROS ANTES DE CHAMAR ESSAS 4 LINHAS ABAIXO
        ArquivoMembro.gravarMembros(biblioteca);
        ArquivoArtigo.gravarArtigos(biblioteca);
        ArquivoLivro.gravarLivros(biblioteca);
        ArquivoRevista.gravarRevistas(biblioteca);
        ArquivoEmprestimo.gravarEmprestimos(biblioteca);
        ///////////////////////////////////////////
        
        // Implementação de um menu iterativo (SUBSTITUIR O QUE PRECISAR PRA IMPLEMENTAR A INTERFACE GRAFICA)
        Scanner scanner = new Scanner(System.in);
        int escolha;
        
        do {
            exibirMenuPrincipal();
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            // Executar a operação escolhida
            switch (escolha) {
                case 1:
                    menuCadastro(scanner, bibliotecario);
                    break;
                case 2:
                    menuListar(scanner, bibliotecario);
                    break;
                case 3:
                    menuGravar(scanner, bibliotecario);
                    break;
                case 0:
                    sair();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 0);
        
        scanner.close();
	}
	
	private static void exibirMenuPrincipal() {
		System.out.println("----- Menu de Operações -----");
		for (MenuOperacoes operacao : MenuOperacoes.values()) {
			System.out.println(operacao.getOperacao() + " - " + operacao.name());
            }
		System.out.println("-----------------------------");
		System.out.print("Escolha uma opção: ");
	}
	
	private static void menuCadastro(Scanner scanner, Bibliotecario bibliotecario) {
	    int escolha; // Valor inicial para a variável escolha

        do {
            exibirMenuCadastro();
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            // Executar a operação de cadastro escolhida
            switch (escolha) {
                case 1:
                	cadastrarMembro(bibliotecario);
                    break;
                case 2:	
                	MenuCadastrarItem(scanner, bibliotecario);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 0);
    }


    
    private static void exibirMenuCadastro() {
        System.out.println("----- Menu de Cadastro -----");
        System.out.println("1 - Cadastrar Membro");
        System.out.println("2 - Cadastrar Item");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");
    }
    
	private static void menuListar(Scanner scanner, Bibliotecario bibliotecario) {
	    int escolha; // Valor inicial para a variável escolha
	    Biblioteca biblioteca = bibliotecario.getBiblioteca();
        StringBuilder sb = new StringBuilder();
        StringBuilder artigos = new StringBuilder();
        StringBuilder livros = new StringBuilder();
        StringBuilder revistas = new StringBuilder();
	    
        do {
            exibirMenuListar();
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            // Executar a operação de cadastro escolhida
            switch (escolha) {
                case 1:              	
                    if (biblioteca.getListaItem().isEmpty()) {
                        sb.append("A biblioteca não possui itens.");
                        System.out.println(sb);
                    } else {
                        for (Item item : biblioteca.getListaItem()) {
                            if (item instanceof Artigo) {
                            	artigos.append(item).append("\n");
                            }
                        }
                    }
                    System.out.println("---- ARTIGOS ----");
                    System.out.println(artigos);
                    break;
                    
                case 2:	
                    if (biblioteca.getListaItem().isEmpty()) {
                        sb.append("A biblioteca não possui itens.");
                        System.out.println(sb);
                    } else {
                        for (Item item : biblioteca.getListaItem()) {
                            if (item instanceof Livro) {
                            	livros.append(item).append("\n");
                            }
                        }
                    }
                    System.out.println("---- LIVROS ----");
                	System.out.println(livros);
                    break;
                    
                case 3:
                    if (biblioteca.getListaItem().isEmpty()) {
                        sb.append("A biblioteca não possui itens.");
                        System.out.println(sb);
                    } else {
                        for (Item item : biblioteca.getListaItem()) {
                            if (item instanceof Revista) {
                            	revistas.append(item).append("\n");
                            }
                        }
                    }
                    System.out.println("---- REVISTAS ----");
                	System.out.println(revistas);
                	break;
                	
                case 4:
                    // Printa os membros da biblioteca
                    System.out.println("---- MEMBROS ----");
                    System.out.println(biblioteca.PrintaListaMembros());
                	break;
                	
                case 5:
                    // Printa os emprestimos da biblioteca
                    System.out.println("---- EMPRÉSTIMOS ----");
                    System.out.println(biblioteca.PrintaListaEmprestimos());
                	break;
                	
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 0);
    }


    
    private static void exibirMenuListar() {
        System.out.println("----- Menu de Cadastro -----");
        System.out.println("1 - Listar Artigos");
        System.out.println("2 - Listar Livros");
        System.out.println("3 - Listar Revistas");
        System.out.println("4 - Listar Membros");
        System.out.println("5 - Listar Emprestimos");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");
    }
	

	private static void menuGravar(Scanner scanner, Bibliotecario bibliotecario) {
	    int escolha; // Valor inicial para a variável escolha
	    Biblioteca biblioteca = bibliotecario.getBiblioteca();
        do {
            exibirMenuGravar();
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
                       
            // Executar a operação de cadastro escolhida
            switch (escolha) {
                case 1:
                	ArquivoArtigo.gravarArtigos(biblioteca);
                    break;
                case 2:	
                	ArquivoLivro.gravarLivros(biblioteca);
                    break;
                case 3:
                	ArquivoRevista.gravarRevistas(biblioteca);
                    break;
                case 4:	
                	ArquivoMembro.gravarMembros(biblioteca);
                    break;
                case 5:
                	ArquivoEmprestimo.gravarEmprestimos(biblioteca);
                    break;
                case 6:	
                	ArquivoArtigo arquivoArtigo = new ArquivoArtigo();
                    String conteudoArquivoArtigo = arquivoArtigo.lerArquivo(bibliotecario);
                    System.out.println(conteudoArquivoArtigo);
                    break;
                case 7:
                    ArquivoLivro arquivoLivro = new ArquivoLivro();
                    String conteudoArquivoLivro = arquivoLivro.lerArquivo(bibliotecario);
                    System.out.println(conteudoArquivoLivro);
                    break;
                case 8:	
                	ArquivoRevista arquivoRevista = new ArquivoRevista();
                    String conteudoArquivoRevista = arquivoRevista.lerArquivo(bibliotecario);
                    System.out.println(conteudoArquivoRevista);

                    break;
                case 9:
                	ArquivoMembro arquivoMembro = new ArquivoMembro();
                    String conteudoArquivoMembro = arquivoMembro.lerArquivo(bibliotecario);
                    System.out.println(conteudoArquivoMembro);
                    break;
                case 10:	
                    ArquivoEmprestimo arquivoEmprestimo = new ArquivoEmprestimo();
                    String conteudoArquivoEmprestimo = arquivoEmprestimo.lerArquivo(bibliotecario);
                    System.out.println(conteudoArquivoEmprestimo);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 0);
    }


    
    private static void exibirMenuGravar() {
        System.out.println("----- Menu de Gravações -----");
        System.out.println("1 - Gravar Artigos");
        System.out.println("2 - Gravar Livros");
        System.out.println("3 - Gravar Revistas");
        System.out.println("4 - Gravar Membros");
        System.out.println("5 - Gravar Empréstimos");
        System.out.println("----- Menu de Leituras -----");
        System.out.println("6 - Ler Artigos");
        System.out.println("7 - Ler Livros");
        System.out.println("8 - Ler Revistas");
        System.out.println("9 - Ler Membros");
        System.out.println("10 - Ler Empréstimos");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");
    }
    
    
    

    private static void cadastrarMembro(Bibliotecario bibliotecario) {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("----- Cadastro de Membro -----");
			System.out.print("Nome: ");
			String nome = scanner.nextLine();

			System.out.print("Endereço: ");
			String endereco = scanner.nextLine();

            Validacao validacao;
            String cpf;
            do{
			System.out.print("CPF: ");
			cpf = scanner.nextLine();
            validacao = new Validacao(cpf);
            } while (!validacao.validarCodigo());

			System.out.print("Email: ");
			String email = scanner.nextLine();

			System.out.print("Telefone: ");
			String telefone = scanner.nextLine();
			
			// Instancia o membro
			Membro membro = new Membro(nome, endereco, cpf, email, telefone);
			bibliotecario.cadastrarMembro(membro);
		}

        System.out.println("Membro cadastrado com sucesso!");

    }
    
    private static void MenuCadastrarItem(Scanner scanner, Bibliotecario bibliotecario) {
    	int escolha;
        do {
    		System.out.println("----- Selecione o tipo de Item -----");
    		System.out.println("1 - Artigo");
            System.out.println("2 - Livro");
            System.out.println("3 - Revista");
            System.out.println("0 - Voltar");
            
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            switch (escolha) {        
            case 1:
            	cadastrarArtigo(bibliotecario);
                break;
            case 2:
            	cadastrarLivro(bibliotecario);
                break;
            case 3:
            	cadastrarRevista(bibliotecario);
                break;
            case 0:
                System.out.println("Voltando ao menu anterior");
                sair();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
            }
    	} while (escolha != 0);
    }
    
    private static void cadastrarArtigo(Bibliotecario bibliotecario) {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("----- Cadastro de Artigo -----");
			System.out.print("Título: ");
			String titulo = scanner.nextLine();

			System.out.print("Autor: ");
			String autor = scanner.nextLine();

			System.out.print("Editora: ");
			String editora = scanner.nextLine();

			System.out.print("Data de publicação: ");
			String data = scanner.nextLine();

			System.out.print("Gênero: ");
			String genero = scanner.nextLine();
			
			System.out.print("Número de exemplares: ");
			int exemplares = scanner.nextInt();	
			scanner.nextLine(); // Consumir a quebra de linha pendente
			
			System.out.print("Identificação DOI: ");
			String doi = scanner.nextLine();	
			
			// Instancia o item
			Artigo artigo = new Artigo(titulo, autor,editora, parseData(data), genero, doi, exemplares);
			bibliotecario.adicionarItem(artigo);
		}

        System.out.println("Artigo cadastrado com sucesso!");

    }
    
    private static void cadastrarLivro(Bibliotecario bibliotecario) {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("----- Cadastro de Livro -----");
			System.out.print("Título: ");
			String titulo = scanner.nextLine();

			System.out.print("Autor: ");
			String autor = scanner.nextLine();

			System.out.print("Editora: ");
			String editora = scanner.nextLine();

			System.out.print("Data de publicação: ");
			String data = scanner.nextLine();

			System.out.print("Gênero: ");
			String genero = scanner.nextLine();
			
			System.out.print("Número de exemplares: ");
			int exemplares = scanner.nextInt();
			scanner.nextLine(); // Consumir a quebra de linha pendente
			
			System.out.print("Identificação ISBN: ");
			String isbn = scanner.nextLine();	
			
			// Instancia o item
			Livro livro = new Livro(titulo, autor,editora, parseData(data), genero, isbn, exemplares);
			bibliotecario.adicionarItem(livro);
		}

        System.out.println("Artigo cadastrado com sucesso!");

    }
    
    private static void cadastrarRevista(Bibliotecario bibliotecario) {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("----- Cadastro de Revista -----");
			System.out.print("Título: ");
			String titulo = scanner.nextLine();

			System.out.print("Autor: ");
			String autor = scanner.nextLine();

			System.out.print("Editora: ");
			String editora = scanner.nextLine();

			System.out.print("Data de publicação: ");
			String data = scanner.nextLine();

			System.out.print("Gênero: ");
			String genero = scanner.nextLine();
			
			System.out.print("Número de exemplares: ");
			int exemplares = scanner.nextInt();
			scanner.nextLine(); // Consumir a quebra de linha pendente
			
			System.out.print("Identificação ISSN: ");
			String issn = scanner.nextLine();	
			
			// Instancia o item
			Revista revista = new Revista(titulo, autor,editora, parseData(data), genero, issn, exemplares);
			bibliotecario.adicionarItem(revista);
		}

        System.out.println("Artigo cadastrado com sucesso!");

    }

    
    
    
    
    
    // Método auxiliar para converter uma string em uma data
    private static Date parseData(String dataString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(dataString);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data.");
            return null;
        }
    }

	private static void sair() {
        System.out.println("Opção 0 - Sair");
        System.out.println("PROGRAMA ENCERRADO");
    }
}
