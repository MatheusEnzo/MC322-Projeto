import java.util.Date;

public abstract class Livro extends Item {
	private final String isbn;
	private int formato;
	
	public Livro(String titulo, String autor, String editora, Date data, String genero, int paginas, String isbn, int formato)
	{
		super(titulo, autor, editora, data, genero, paginas);
		
		this.isbn = isbn;
		this.formato = formato;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getFormato() {
		return formato;
	}

	public void setFormato(int formato) {
		this.formato = formato;
	}
}
