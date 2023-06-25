import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Livro extends Item {
	private final String isbn;
	private int exemplares;
	
    //Construtor
	public Livro(String titulo, String autor, String editora, Date data, String genero, String isbn, int exemplares)
	{
		super(titulo, autor, editora, data, genero, exemplares);
		this.isbn = isbn;
	}

    //Getters e Setters
	public String getIsbn() {
		return isbn;
	}
	
	public int getExemplares() {
		return exemplares;
	}

	public void setExemplares(int exemplares) {
		this.exemplares = exemplares;
	}

	//Determina se um Livro tem isbn válido
    public boolean isValido(){
        Validacao validacao = new Validacao(this);
        return validacao.validarCodigo();
    }

	public String toCsvString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = dateFormat.format(super.getData());
        
	    String disponivelString = super.isDisponivel() ? "Sim" : "Não";
	    
        return super.getTitulo() + "," + super.getAutor() + "," + super.getEditora() + "," + dataString + "," + super.getGenero() + "," + isbn + "," + super.getExemplares() +"," + disponivelString;
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
        int exemplares = Integer.parseInt(values[6]);
        
        return new Livro(titulo, autor, editora, data, genero, isbn, exemplares);
    }
    
    public String toString() {
    	String output = super.toString();
    	output += "ISBN: " + isbn + "\n";
    	return output;
    }
}
