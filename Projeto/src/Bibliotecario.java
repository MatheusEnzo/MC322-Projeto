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
            System.out.println("Membro removido com sucesso.");
            return true;
        } else {
            System.out.println("Não foi possível remover o membro.");
            return false;
        }
    }

    // Método para adicionar um item à biblioteca
    public void adicionarItem(Item item) {
        biblioteca.getListaItem().add(item);
    }

    // Método para remover um item da biblioteca
    public boolean removerItem(Item item) {
        boolean removido = biblioteca.getListaItem().remove(item);
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
        if (biblioteca.getListaItem().contains(item)) {
            if(membro.getEmprestimos().size() == membro.getLimite())
            {
            	System.out.println("Limite máximo de empréstimos atingido.");
            }
            else
        	{
            	if (item.isDisponivel()) {
            		LocalDate dataAtual = LocalDate.now();
            		Emprestimo novo = new Emprestimo(item, membro, dataAtual, dataAtual.plusDays(7));
            		biblioteca.getListaEmprestimo().add(novo);
            		membro.getEmprestimos().add(novo);
            		System.out.println("Item emprestado para " + membro.getNome() + ": " + item.getTitulo());
            		int numero = item.getExemplares() - 1;
            		item.setExemplares(numero);
            		if(item.getExemplares()==0)
            		{
            			item.setDisponivel(false);
            		}
            	}
            	else {
            		System.out.println("O item já está emprestado: " + item.getTitulo());
            	}
        	}
        } 
        else {
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