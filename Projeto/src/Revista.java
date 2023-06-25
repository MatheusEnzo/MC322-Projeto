import java.util.Date;

public abstract class Revista extends Item {
	private final String issn;
	private int exemplares;
	private int formato;
	
	public Revista(String titulo, String autor, String editora, Date data, String genero, int paginas, String issn, int formato)
	{
		super(titulo, autor, editora, data, genero, paginas);	
		this.issn = issn;
		this.formato = formato;
	}

	public int getFormato() {
		return formato;
	}

	public void setFormato(int formato) {
		this.formato = formato;
	}

	public String getIssn() {
		return issn;
	}
	
}
