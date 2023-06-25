import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Livro extends Item {
	private final String isbn;
	private int exemplares;
	private String formato;
	
    //Construtor
	public Livro(String titulo, String autor, String editora, Date data, String genero, String isbn, String formato)
	{
		super(titulo, autor, editora, data, genero);
		this.isbn = isbn;
		this.formato = formato;
	}

    //Getters e Setters
	public String getIsbn() {
		return isbn;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}
	
    public String toCsvString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = dateFormat.format(super.getData());
        return super.getTitulo() + "," + super.getAutor() + "," + super.getEditora() + "," + dataString + "," + super.getGenero() + "," + isbn + "," + formato;
    }

    
    public static Livro fromCsvString(String csvString) throws ParseException {
        String[] values = csvString.split(","); // Divide a string CSV nos valores individuais
        
        // Extrai os valores do array e cria um novo objeto Livro
        String titulo = values[0];
        String autor = values[1];
        String editora = values[2];
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse(values[3]);
        String genero = values[4];
        String isbn = values[5];
        String formato = values[6];
        
        return new Livro(titulo, autor, editora, data, genero, isbn, formato);
    }
}
