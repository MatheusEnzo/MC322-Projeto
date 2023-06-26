import java.text.ParseException;
import java.util.ArrayList;

public class Membro extends Usuario {
	private int limite;
	private ArrayList<Emprestimo> emprestimos;
	private ArrayList<Emprestimo> historico;
	private int suspensao;
	
	//Construtor
	public Membro(String nome, String endereco, String cpf, String email, String telefone)
	{
		super(nome, endereco, cpf, email, telefone);
		
		this.limite = 5;
		this.emprestimos = new ArrayList<Emprestimo>();
		this.historico = new ArrayList<Emprestimo>();
		this.suspensao = 0;
	}

	//Getters e Setters
	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public ArrayList<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public ArrayList<Emprestimo> getHistorico() {
		return historico;
	}

	public void setHistorico(ArrayList<Emprestimo> historico) {
		this.historico = historico;
	}

	public int getSuspensao() {
		return suspensao;
	}

	public void setSuspensao(int suspensao) {
		this.suspensao = suspensao;
	}
    
    public String toCsvString() {
        String csvString = super.toCsvString();  // Chamada ao método toCsvString() da classe Usuario
        return csvString;
    }
    
    public static Membro fromCsvString(String csvString) throws ParseException {
        String[] values = csvString.split(","); // Divide a string CSV nos valores individuais
        
        // Extrai os valores do array e cria um novo objeto Membro
        String nome = values[0];
        String endereco = values[1];
        String cpf = values[2];
        String email = values[3];
        String telefone = values[4];
        
        return new Membro(nome, endereco, cpf, email, telefone);
    }
	
}