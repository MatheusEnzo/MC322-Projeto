import java.time.LocalTime;

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
        
        

	}
}
