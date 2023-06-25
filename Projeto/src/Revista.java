import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Revista extends Item {
	private final String issn;
	private int exemplares;
	private String formato;
	
	public Revista(String titulo, String autor, String editora, Date data, String genero, String issn, String formato)
	{
		super(titulo, autor, editora, data, genero);	
		this.issn = issn;
		this.formato = formato;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getIssn() {
		return issn;
	}
	
    public String toCsvString() {
        String csvString = super.toCsvString();  // Chamada ao método toCsvString() da classe Item
        csvString += "," + issn;  // Adiciona o ISSN ao final da string CSV
        csvString += "," + formato;  // Adiciona o formato ao final da string CSV
        return csvString;
    }
    
    public static Revista fromCsvString(String csvString) throws ParseException {
        String[] values = csvString.split(","); // Divide a string CSV nos valores individuais
        
        // Extrai os valores do array e cria um novo objeto Revista
        String titulo = values[0];
        String autor = values[1];
        String editora = values[2];
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse(values[3]);
        String genero = values[4];
        String issn = values[5];
        String formato = values[6];
        
        return new Revista(titulo, autor, editora, data, genero, issn, formato);
    }
	
}
