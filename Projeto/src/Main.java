import java.time.LocalTime;
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
               
        // Printa os membros da biblioteca
        System.out.println("---- MEMBROS ----");
        System.out.println(biblioteca.PrintaListaMembros());
        
        // Printa os itens da biblioteca
		biblioteca.PrintaListaItens();
     
    	// Gravação de arquivos
		// ÚTIL FAZER ALTERAÇÕES NOS MEMBROS,
		// ARTIGOS E LIVROS ANTES DE CHAMAR ESSAS 4 LINHAS ABAIXO
        ArquivoMembro.gravarMembros(biblioteca);
        ArquivoArtigo.gravarArtigos(biblioteca);
        ArquivoLivro.gravarLivros(biblioteca);
        ArquivoRevista.gravarRevistas(biblioteca);
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
        int escolha;

        do {
            exibirMenuCadastro();
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            // Executar a operação de cadastro escolhida
            switch (escolha) {
                case 1:
                    MenuCadastrarMembro(scanner, bibliotecario);
                    break;
                case 3:
                    System.out.println("Voltando ao menu principal...");
                    sair();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 3);
    }
    
    private static void exibirMenuCadastro() {
        System.out.println("----- Menu de Cadastro -----");
        System.out.println("1 - Cadastrar Membro");
        System.out.println("2 - Cadastrar Item");
        System.out.println("3 - Voltar");
        System.out.print("Escolha uma opção: ");
    }
	
    private static void MenuCadastrarMembro(Scanner scanner, Bibliotecario bibliotecario) {
    	int escolha;
    	
    	do {
    		System.out.println("----- Cadastrar Membro -----");
    		System.out.println("1 - Cadastrar membro");
            System.out.println("0 - Voltar");
            
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            switch (escolha) {        
            case 1:
                cadastrarMembro(bibliotecario);
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

    private static void cadastrarMembro(Bibliotecario bibliotecario) {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("----- Cadastro de Membro -----");
			System.out.print("Nome: ");
			String nome = scanner.nextLine();

			System.out.print("Endereço: ");
			String endereco = scanner.nextLine();

			System.out.print("CPF: ");
			String cpf = scanner.nextLine();

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
    
    private static void sair() {
        System.out.println("Opção 0 - Sair");
        System.out.println("PROGRAMA ENCERRADO");
    }
}
