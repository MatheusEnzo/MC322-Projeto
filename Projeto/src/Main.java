import java.io.File;
import java.time.LocalTime;

public class Main {
	
	public static void main(String[] args) {
    	
		// Instanciação de um objeto Biblioteca
    	LocalTime horario1 = LocalTime.of(9, 0); //Horario de abertura (09:00)
    	LocalTime horario2 = LocalTime.of(18, 0); //Horario de fechamento (18:00)
    	Biblioteca biblioteca = new Biblioteca("Biblioteca de Bytes da UNICAMP", "Rua Andrade da Silva, 199", horario1, horario2);
    	
    	// Instanciação de um objeto Bibliotecario (um dos possíveis usuários)
    	Bibliotecario bibliotecario = new Bibliotecario("José Pereira", "Rua Arlindo da Cruz, 456", "650.885.770-36", "josepereira@bibliobytes.com", "(11) 99690-3704", biblioteca, "1234");
    	biblioteca.getListaUsuario().add(bibliotecario); // Bibliotecario adicionado à lista de usuários da biblioteca
    	
    	// Caminhos dos arquivos a serem lidos automaticamente
        String caminhoArtigo = "Arquivos/artigos.csv";
        String caminhoLivro = "Arquivos/livros.csv";
        String caminhoRevista = "Arquivos/revistas.csv";
        String caminhoMembro = "Arquivos/membros.csv";
        String caminhoEmprestimo = "Arquivos/emprestimos.csv";

        // Leitura automática dos arquivos
        ArquivoArtigo arquivoArtigo = new ArquivoArtigo();
        arquivoArtigo.lerArquivoCSV(new File(caminhoArtigo), bibliotecario);

        ArquivoLivro arquivoLivro = new ArquivoLivro();
        arquivoLivro.lerArquivoCSV(new File(caminhoLivro), bibliotecario);

        ArquivoRevista arquivoRevista = new ArquivoRevista();
        arquivoRevista.lerArquivoCSV(new File(caminhoRevista), bibliotecario);

        ArquivoMembro arquivoMembro = new ArquivoMembro();
        arquivoMembro.lerArquivoCSV(new File(caminhoMembro), bibliotecario);

        ArquivoEmprestimo arquivoEmprestimo = new ArquivoEmprestimo();
        arquivoEmprestimo.lerArquivoCSV(new File(caminhoEmprestimo), bibliotecario);
        
    	
        // Crie e exiba a janela
    	Login login = new Login(biblioteca, bibliotecario);
    	login.setVisible(true);
    }
}
