import java.time.LocalDate;

public class Bibliotecario extends Usuario {
	private String senha;
    private Biblioteca biblioteca;

    //Construtor
    public Bibliotecario(String nome, String endereco, String cpf, String email, String telefone, Biblioteca biblioteca, String senha) {
        super(nome, endereco, cpf, email, telefone);
        this.biblioteca = biblioteca;
        this.senha = senha;
    }

    //Getters e Setters
	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

    public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// Método para cadastrar um membro (usuário)
    public void cadastrarMembro(Usuario usuario) {
        biblioteca.getListaUsuario().add(usuario);
    }

    // Método para remover um membro (usuário)
    public boolean removerMembro(String cpf) {
    	
        for(int i=0;i<biblioteca.getListaUsuario().size();i++) {
        	String test = biblioteca.getListaUsuario().get(i).getCpf().replaceAll("\\D+", "");
        	if(test.equals(cpf))
        	{
        		for(Emprestimo j : biblioteca.getListaEmprestimo())
        		{
        			if(j.getMembro().equals(biblioteca.getListaUsuario().get(i)))
        			{
        				return false;
        			}
        		}
        		biblioteca.getListaUsuario().remove(i);
        		return true;
        	}
        }
        return false;
    }

    // Método para adicionar um item à biblioteca
    public void adicionarItem(Item item) {
        if(biblioteca.getListaItem().contains(item))
        {
        	for(int i=0; i<biblioteca.getListaItem().size(); i++)
        	{
        		if(biblioteca.getListaItem().get(i).equals(item))
        		{
        			int exemplares = biblioteca.getListaItem().get(i).getExemplares();
        			biblioteca.getListaItem().get(i).setExemplares(exemplares+1);
        		}
        	}
        }
        else
        {
        	biblioteca.getListaItem().add(item);
        }
    }
    
    // Método para remover um item da biblioteca pelo título
    public boolean removerItemPorTitulo(String titulo) {
        for (Item item : biblioteca.getListaItem()) { // Percorre a lista de itens da biblioteca
            if (item.getTitulo().equalsIgnoreCase(titulo)) { // Verifica se o título do item corresponde ao título fornecido
                if (item.getExemplares() > 0) { // Verifica se há exemplares disponíveis do item
                    int exemplares = item.getExemplares(); // Obtém o número de exemplares do item
                    item.setExemplares(exemplares - 1); // Reduz o número de exemplares em 1
                    if (exemplares - 1 == 0) { // Verifica se o número de exemplares após a redução é igual a zero
                        for (Emprestimo emprestimo : biblioteca.getListaEmprestimo()) { // Percorre a lista de empréstimos da biblioteca
                            if (emprestimo.getItem().equals(item)) { // Verifica se o item está emprestado
                                item.setDisponivel(false); // Define o item como indisponível
                                return true; // Retorna verdadeiro indicando que o item foi removido
                            }
                        }
                        biblioteca.getListaItem().remove(item); // Remove o item da lista de itens da biblioteca
                        return true; // Retorna verdadeiro indicando que o item foi removido
                    }
                    return true; // Retorna verdadeiro indicando que o item foi removido
                } else {
                    return false; // Retorna falso indicando que não há exemplares disponíveis do item
                }
            }
        }
        return false; // Retorna falso indicando que o item não foi encontrado na biblioteca
    }

    
 // Método para emprestar um item para um membro (usuário) pelo CPF
    public String emprestarItemPorTitulo(String cpf, String nomeItem) {
        Membro membro = null;
        Biblioteca biblioteca = this.getBiblioteca();
        
        // Procurar o membro pelo CPF
        for (Usuario usuario : biblioteca.getListaUsuario()) {
        	if (usuario instanceof Membro) {
        		Membro membro1 = (Membro) usuario;
        		String test = membro1.getCpf();
        		test = test.replaceAll("\\D+", "");
                if (test.equals(cpf)) {
                    membro = membro1;
                    break;
                }
        	}
        }

        if (membro != null) {
            boolean itemEncontrado = false;
            // Procurar o item pelo nome
            for (Item item : biblioteca.getListaItem()) {
                if (item.getTitulo().equalsIgnoreCase(nomeItem)) {
                    itemEncontrado = true;
                    if (membro.getEmprestimos().size() == membro.getLimite()) {
                        return "Limite máximo de empréstimos do membro atingido.";
                    } else {
                        if (item.isDisponivel()) {
                            LocalDate dataAtual = LocalDate.now();
                            Emprestimo novo = new Emprestimo(item, membro, dataAtual, dataAtual.plusDays(7));
                            biblioteca.getListaEmprestimo().add(novo);
                            membro.getEmprestimos().add(novo);
                            membro.getHistorico().add(novo);
                            int numero = item.getExemplares() - 1;
                            item.setExemplares(numero);
                            if (numero == 0) {
                                item.setDisponivel(false);
                            }
                            return "Empréstimo realizado.";
                        } else {
                            return "O item já está emprestado.";
                        }
                    }
                }
            }
            if (!itemEncontrado) {
                return "Item não encontrado na biblioteca.";
            }
        }
        return "Membro não encontrado.";
    }

    
    // Método para devolver um item emprestado por um membro (usuário) pelo CPF e título do item
    public String devolverItemPorTitulo(String cpf, String tituloItem) {
        Membro membro = null;
        Biblioteca biblioteca = this.getBiblioteca();
        
        // Procurar o membro pelo CPF
        for (Usuario usuario : biblioteca.getListaUsuario()) {
        	if (usuario instanceof Membro) {
        		Membro membro1 = (Membro) usuario;
        		String test = membro1.getCpf();
        		test = test.replaceAll("\\D+", "");
                if (test.equals(cpf)) {
                    membro = membro1;
                    break;
                }
        	}
        }

        if (membro != null) {
            Emprestimo emprestimoEncontrado = null;
            // Procurar o empréstimo pelo título do item
            for (Emprestimo emprestimo : biblioteca.getListaEmprestimo()) {
                if (emprestimo.getMembro().equals(membro) && emprestimo.getItem().getTitulo().equalsIgnoreCase(tituloItem)) {
                    emprestimoEncontrado = emprestimo;
                    break;
                }
            }

            if (emprestimoEncontrado != null) {
                biblioteca.getListaEmprestimo().remove(emprestimoEncontrado);
                membro.getEmprestimos().remove(emprestimoEncontrado);

                Item itemDevolvido = emprestimoEncontrado.getItem();
                for (Item item : biblioteca.getListaItem()) {
                    if (item.equals(itemDevolvido)) {
                        int exemplares = item.getExemplares();
                        item.setExemplares(exemplares + 1);
                        item.setDisponivel(true);
                        break;
                    }
                }

                return "Item devolvido.";
            } else {
                return "O membro não possui empréstimo do item com tal título.";
            }
        } 
        return "Membro não encontrado,";
    }

    
    
}