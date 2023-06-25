import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Artigo extends Item {
	private final String doi;
	
    //Construtor
	public Artigo(String titulo, String autor, String editora, Date data, String genero, String doi)
	{
		super(titulo, autor, editora, data, genero);
		this.doi = doi;
	}

    //Getters e Setters
	public String getDoi() {
		return doi;
	}
	
    //Determina se um artigo tem doi válido
    public boolean isValido(){
        Validacao validacao = new Validacao(this);
        return validacao.validarCodigo();
    }
    
    public String toCsvString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = dateFormat.format(super.getData());
        return super.getTitulo() + "," + super.getAutor() + "," + super.getEditora() + "," + dataString + "," + super.getGenero() + "," + doi;
    }
    
    public static Artigo fromCsvString(String csvString) throws ParseException {
        String[] values = csvString.split(","); // Divide a string CSV nos valores individuais
        
        // Extrai os valores do array e cria um novo objeto Artigo
        String titulo = values[0];
        String autor = values[1];
        String editora = values[2];
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse(values[3]);
        String genero = values[4];
        String doi = values[5];
        
        return new Artigo(titulo, autor, editora, data, genero, doi);
    }
    
    public String toString() {
    	String output = super.toString();
    	output += "DOI: " + doi + "\n";
    	return output;
    }
}
