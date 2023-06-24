import java.util.ArrayList;

public class Membro extends Usuario {
	private int limite;
	private ArrayList<Item> emprestimos;
	private ArrayList<Item> historico;
	private int suspensao;
	
	public Membro(String nome, String endereco, String cpf, String email, String telefone)
	{
		super(nome, endereco, cpf, email, telefone);
		
		this.limite = 5;
		this.emprestimos = new ArrayList<Item>();
		this.historico = new ArrayList<Item>();
		this.suspensao = 0;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public ArrayList<Item> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(ArrayList<Item> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public ArrayList<Item> getHistorico() {
		return historico;
	}

	public void setHistorico(ArrayList<Item> historico) {
		this.historico = historico;
	}

	public int getSuspensao() {
		return suspensao;
	}

	public void setSuspensao(int suspensao) {
		this.suspensao = suspensao;
	}
	
}
