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
	
    // Método para cadastrar um membro (usuário)
    public void cadastrarMembro(Usuario usuario) {
        listaUsuario.add(usuario);
        System.out.println("Membro cadastrado com sucesso: " + usuario.getNome());
    }

    // Método para remover um membro (usuário)
    public boolean removerMembro(Membro membro) {
        boolean removido = listaUsuario.remove(membro);
        if (removido) {
            System.out.println("Membro removido com sucesso: " + membro.getNome());
            return true;
        } else {
            System.out.println("Não foi possível remover o membro: " + membro.getNome());
            return false;
        }
    }

    // Método para adicionar um item à biblioteca
    public void adicionarItem(Item item) {
        listaItem.add(item);
        System.out.println("Item adicionado com sucesso: " + item.getTitulo());
    }

    // Método para remover um item da biblioteca
    public boolean removerItem(Item item) {
        boolean removido = listaItem.remove(item);
        if (removido) {
            System.out.println("Item removido com sucesso: " + item.getTitulo());
            return true;
        } else {
            System.out.println("Não foi possível remover o item: " + item.getTitulo());
            return false;
        }
    }

    // Método para emprestar um item para um membro (usuário)
    public void emprestarItem(Membro membro, Item item) {
        if (listaItem.contains(item)) {
            if (item.isDisponivel()) {
                item.setDisponivel(false);
                membro.adicionarItemEmprestado(item);
                System.out.println("Item emprestado para " + membro.getNome() + ": " + item.getTitulo());
            } else {
                System.out.println("O item já está emprestado: " + item.getTitulo());
            }
        } else {
            System.out.println("O item não está disponível na biblioteca: " + item.getTitulo());
        }
    }


    // Método para devolver um item emprestado por um membro (usuário)
    public void devolverItem(Membro membro, Item item) {
        if (membro.getEmprestimos().contains(item)) {
            item.setDisponivel(true);
            membro.removerItemEmprestado(item);
            System.out.println("Item devolvido por " + membro.getNome() + ": " + item.getTitulo());
        } else {
            System.out.println("O item não está em posse de " + membro.getNome() + ": " + item.getTitulo());
        }
    }
}
