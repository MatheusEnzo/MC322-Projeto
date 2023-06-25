import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Revista extends Item {
	private final String issn;
	private int exemplares;
	
    //Construtor
	public Revista(String titulo, String autor, String editora, Date data, String genero, String issn, int exemplares)
	{
		super(titulo, autor, editora, data, genero, exemplares);	
		this.issn = issn;
	}

    //Getters e Setters
	public String getIssn() {
		return issn;
	}
	
	public int getExemplares() {
		return exemplares;
	}

	public void setExemplares(int exemplares) {
		this.exemplares = exemplares;
	}
	
	//Determina se uma revista tem issn válido
    public boolean isValido(){
        Validacao validacao = new Validacao(this);
        return validacao.validarCodigo();
    }
	
	public String toCsvString() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    String dataString = dateFormat.format(super.getData());

	    String disponivelString = super.isDisponivel() ? "Sim" : "Não";

	    return super.getTitulo() + "," + super.getAutor() + "," + super.getEditora() + "," + dataString + "," + super.getGenero() + "," + issn + ","+ super.getExemplares() + "," + disponivelString;
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
        int exemplares = Integer.parseInt(values[6]);
        
        return new Revista(titulo, autor, editora, data, genero, issn, exemplares);
    }
    
    public String toString() {
    	String output = super.toString();
    	output += "ISSN: " + issn + "\n";
    	return output;
    }
	
}
