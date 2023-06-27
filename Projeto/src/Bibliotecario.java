import java.time.LocalDate;

public class Bibliotecario extends Usuario {
    private Biblioteca biblioteca;

    //Construtor
    public Bibliotecario(String nome, String endereco, String cpf, String email, String telefone, Biblioteca biblioteca) {
        super(nome, endereco, cpf, email, telefone);
        this.biblioteca = biblioteca;
    }

    //Getters e Setters
	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

    // Método para cadastrar um membro (usuário)
    public void cadastrarMembro(Usuario usuario) {
        biblioteca.getListaUsuario().add(usuario);
    }

    // Método para remover um membro (usuário)
    public boolean removerMembro(String cpf) {
        boolean removido=false;
    
        for(int i=0;i<biblioteca.getListaUsuario().size();i++) {
        	if(biblioteca.getListaUsuario().get(i).getCpf().equals(cpf))
        	{
        		biblioteca.getListaUsuario().remove(i);
        		removido = true;
        		break;
        	}
        }
        if (removido) {
            InterfaceGrafica.exibirMensagem("Membro removido com sucesso.");
            return true;
        } else {
            InterfaceGrafica.exibirMensagem("Não foi possível remover o membro.");
            return false;
        }
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

    // Método para remover um item da biblioteca
    public boolean removerItem(Item item) {
        if(biblioteca.getListaItem().contains(item))
        {
        	for(int i=0; i<biblioteca.getListaItem().size(); i++)
        	{
        		if(biblioteca.getListaItem().get(i).equals(item))
        		{
        			if(biblioteca.getListaItem().get(i).getExemplares() > 0)
        			{
        				int exemplares = biblioteca.getListaItem().get(i).getExemplares();
        				biblioteca.getListaItem().get(i).setExemplares(exemplares-1);
        				if(exemplares-1 == 0)
        				{
        					for(int j=0; j<biblioteca.getListaEmprestimo().size(); j++)
        					{
        						if(biblioteca.getListaEmprestimo().get(j).getItem().equals(item))
        						{
        							biblioteca.getListaItem().get(i).setDisponivel(false);
        							return true;
        						}
        					}
        					biblioteca.getListaItem().remove(item);
        					return true;
        				}
        				return true;
        			}
        			else
        			{
        				return false;
        			}
        		}
        	}
        }
        return false;
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
                        InterfaceGrafica.exibirMensagem("Item removido com sucesso");
                        return true; // Retorna verdadeiro indicando que o item foi removido
                    }
                    InterfaceGrafica.exibirMensagem("Item removido com sucesso");
                    return true; // Retorna verdadeiro indicando que o item foi removido
                } else {
                	InterfaceGrafica.exibirMensagem("Não há exemplares do item para remover.");
                    return false; // Retorna falso indicando que não há exemplares disponíveis do item
                }
            }
        }
        InterfaceGrafica.exibirMensagem("Não foi possível remover o item.");
        return false; // Retorna falso indicando que o item não foi encontrado na biblioteca
    }


    // Método para emprestar um item para um membro (usuário)
    public void emprestarItem(Membro membro, Item item) {
        if (biblioteca.getListaItem().contains(item)) {
            if(membro.getEmprestimos().size() == membro.getLimite())
            {
            	InterfaceGrafica.exibirMensagem("Limite máximo de empréstimos atingido.");
            }
            else
        	{
            	for(int i=0; i<biblioteca.getListaItem().size(); i++)
            	{
            		if(biblioteca.getListaItem().get(i).equals(item))
            		{
            			if(biblioteca.getListaItem().get(i).isDisponivel())
            			{
                    		LocalDate dataAtual = LocalDate.now();
                    		Emprestimo novo = new Emprestimo(item, membro, dataAtual, dataAtual.plusDays(7));
                    		biblioteca.getListaEmprestimo().add(novo);
                    		membro.getEmprestimos().add(novo);
                    		membro.getHistorico().add(novo);
                    		InterfaceGrafica.exibirMensagem("Item emprestado para " + membro.getNome() + ": " + item.getTitulo());
                    		int numero = item.getExemplares() - 1;
                    		biblioteca.getListaItem().get(i).setExemplares(numero);
                    		if(numero == 0)
                    		{
                    			biblioteca.getListaItem().get(i).setDisponivel(false);
                    		}
                    	}
                    	else {
                    		InterfaceGrafica.exibirMensagem("O item já está emprestado: " + item.getTitulo());
                    	}
            		}
            	}
        	}
        } 
        else {
            InterfaceGrafica.exibirMensagem("O item não está disponível na biblioteca: " + item.getTitulo());
        }
    }
    
 // Método para emprestar um item para um membro (usuário) pelo CPF
    public void emprestarItemPorTitulo(String cpf, String nomeItem) {
        Membro membro = null;
        Biblioteca biblioteca = this.getBiblioteca();
        
        // Procurar o membro pelo CPF
        for (Usuario usuario : biblioteca.getListaUsuario()) {
        	if (usuario instanceof Membro) {
        		Membro membro1 = (Membro) usuario;
                if (membro1.getCpf().equals(cpf)) {
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
                        InterfaceGrafica.exibirMensagem("Limite máximo de empréstimos atingido.");
                    } else {
                        if (item.isDisponivel()) {
                            LocalDate dataAtual = LocalDate.now();
                            Emprestimo novo = new Emprestimo(item, membro, dataAtual, dataAtual.plusDays(7));
                            biblioteca.getListaEmprestimo().add(novo);
                            membro.getEmprestimos().add(novo);
                            membro.getHistorico().add(novo);
                            InterfaceGrafica.exibirMensagem("Item emprestado para " + membro.getNome() + ": " + item.getTitulo());
                            int numero = item.getExemplares() - 1;
                            item.setExemplares(numero);
                            if (numero == 0) {
                                item.setDisponivel(false);
                            }
                        } else {
                            InterfaceGrafica.exibirMensagem("O item já está emprestado: " + item.getTitulo());
                        }
                    }
                    break;
                }
            }
            if (!itemEncontrado) {
                InterfaceGrafica.exibirMensagem("O item não está disponível na biblioteca: " + nomeItem);
            }
        } else {
            InterfaceGrafica.exibirMensagem("Membro não encontrado com o CPF: " + cpf);
        }
    }



    // Método para devolver um item emprestado por um membro (usuário)
    public void devolverItem(Membro membro, Emprestimo emprestimo) {
        biblioteca.getListaEmprestimo().remove(emprestimo);
        membro.getEmprestimos().remove(emprestimo);
        for(int i=0; i<biblioteca.getListaItem().size(); i++)
        {
        	if(biblioteca.getListaItem().get(i).equals(emprestimo.getItem()))
        	{
        		int exemplares = biblioteca.getListaItem().get(i).getExemplares();
        		biblioteca.getListaItem().get(i).setExemplares(exemplares+1);
        		biblioteca.getListaItem().get(i).setDisponivel(true);
        	}
        }
        InterfaceGrafica.exibirMensagem("Item devolvido: " + emprestimo.getItem().getTitulo());
    }
    
    // Método para devolver um item emprestado por um membro (usuário) pelo CPF e título do item
    public void devolverItemPorTitulo(String cpf, String tituloItem) {
        Membro membro = null;
        Biblioteca biblioteca = this.getBiblioteca();
        
        // Procurar o membro pelo CPF
        for (Usuario usuario : biblioteca.getListaUsuario()) {
        	if (usuario instanceof Membro) {
        		Membro membro1 = (Membro) usuario;
                if (membro1.getCpf().equals(cpf)) {
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

                InterfaceGrafica.exibirMensagem("Item devolvido: " + itemDevolvido.getTitulo());
            } else {
                InterfaceGrafica.exibirMensagem("O membro não possui empréstimo do item com título: " + tituloItem);
            }
        } else {
            InterfaceGrafica.exibirMensagem("Membro não encontrado com o CPF: " + cpf);
        }
    }

    
    
}