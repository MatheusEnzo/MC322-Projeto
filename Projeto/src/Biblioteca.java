import java.time.LocalTime;
import java.util.ArrayList;

public class Biblioteca {
	private String nome;
	private String endereco;
	private LocalTime horarioAbertura;
	private LocalTime horarioFechamento;
	private ArrayList<Item> listaItem;
	private ArrayList<Usuario> listaUsuario;
	
	public Biblioteca(String nome, String endereco, LocalTime abertura, LocalTime fechamento)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.horarioAbertura = abertura;
		this.horarioFechamento = fechamento;
		this.listaItem = new ArrayList<Item>();
		this.listaUsuario = new ArrayList<Usuario>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LocalTime getHorarioAbertura() {
		return horarioAbertura;
	}

	public void setHorarioAbertura(LocalTime horarioAbertura) {
		this.horarioAbertura = horarioAbertura;
	}

	public LocalTime getHorarioFechamento() {
		return horarioFechamento;
	}

	public void setHorarioFechamento(LocalTime horarioFechamento) {
		this.horarioFechamento = horarioFechamento;
	}

	public ArrayList<Item> getListaItem() {
		return listaItem;
	}

	public void setListaItem(ArrayList<Item> listaItem) {
		this.listaItem = listaItem;
	}

	public ArrayList<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
}
