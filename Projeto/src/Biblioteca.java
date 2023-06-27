import java.time.LocalTime;
import java.util.ArrayList;

public class Biblioteca {
	private String nome;
	private String endereco;
	private LocalTime horarioAbertura;
	private LocalTime horarioFechamento;
	private ArrayList<Item> listaItem;
	private ArrayList<Emprestimo> listaEmprestimo;
	private ArrayList<Usuario> listaUsuario;
	
	//Construtor
	public Biblioteca(String nome, String endereco, LocalTime abertura, LocalTime fechamento)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.horarioAbertura = abertura;
		this.horarioFechamento = fechamento;
		this.listaItem = new ArrayList<Item>();
		this.listaEmprestimo = new ArrayList<Emprestimo>();
		this.listaUsuario = new ArrayList<Usuario>();
	}

	public ArrayList<Emprestimo> getListaEmprestimo() {
		return listaEmprestimo;
	}

	public void setListaEmprestimo(ArrayList<Emprestimo> listaEmprestimo) {
		this.listaEmprestimo = listaEmprestimo;
	}

	//Getters e Setters
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
	
	public String pesquisarLivrosPorTitulo(String titulo) {
		 for (Item i : listaItem)
	        {
	        	if(i.getTitulo().equals(titulo))
	        	{
	        		return i.toString();
	        	}
	        }
		 return "Item não encontrado";
	}

    public ArrayList<Item> listarItensDisponiveis() {
        ArrayList<Item> listaDisponiveis = new ArrayList<Item>();
    	for (Item i : listaItem)
        {
        	if(i.isDisponivel())
        	{
        		listaDisponiveis.add(i);
        	}
        }
    	return listaDisponiveis;
    }
    
    public String PrintaListaItens() {
        StringBuilder sb = new StringBuilder();
        StringBuilder artigos = new StringBuilder();
        StringBuilder livros = new StringBuilder();
        StringBuilder revistas = new StringBuilder();

        if (listaItem.isEmpty()) {
            sb.append("A biblioteca não possui itens.");
            System.out.println(sb);
        } else {
            for (Item item : listaItem) {
                if (item instanceof Artigo) {
                	artigos.append(item).append("\n");
                }
            }
            for (Item item : listaItem) {
                if (item instanceof Livro) {
                	livros.append(item).append("\n");
                }
            }
            for (Item item : listaItem) {
                if (item instanceof Revista) {
                	revistas.append(item).append("\n");
                }
            }
        }
        String s = "---- ARTIGOS ----\n";
        s += artigos;
    	s += "\n---- LIVROS ----\n";
    	s += livros;
    	s += "\n---- REVISTAS ----\n";
    	s += revistas;
    	return s;

    }

    
    public String PrintaListaMembros() {
        StringBuilder sb = new StringBuilder();

        if (listaUsuario.isEmpty()) {
            sb.append("A biblioteca não possui usuários.");
        } else {
            boolean possuiMembros = false;
            for (Usuario usuario : listaUsuario) {
                if (usuario instanceof Membro) {
                    sb.append(usuario).append("\n");
                    possuiMembros = true;
                }
            }
            if (!possuiMembros) {
                sb.append("A biblioteca não possui membros.");
            }
        }

        System.out.println(sb);
        return sb.toString();
    }

    public String PrintaListaEmprestimos() {
        StringBuilder sb = new StringBuilder();

        if (listaEmprestimo.isEmpty()) {
            sb.append("A biblioteca não possui empréstimos.");
        } else {
            for (Emprestimo emprestimo : listaEmprestimo) {
                sb.append(emprestimo).append("\n");
            }
        }

        return sb.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Endereço: ").append(endereco).append("\n");
        sb.append("Horário de Abertura: ").append(horarioAbertura).append("\n");
        sb.append("Horário de Fechamento: ").append(horarioFechamento).append("\n");
        
        sb.append("Lista de Itens:\n");
        for (Item item : listaItem) {
            sb.append(item).append("\n");
        }
        
        sb.append("Lista de Usuários:\n");
        for (Usuario usuario : listaUsuario) {
            sb.append(usuario).append("\n");
        }
        
        return sb.toString();
    }
}