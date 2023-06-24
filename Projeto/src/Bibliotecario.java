import java.util.List;

public class Bibliotecario extends Usuario {
    private Biblioteca biblioteca;

    public Bibliotecario(String nome, String endereco, String cpf, String email, String telefone, Biblioteca biblioteca) {
        super(nome, endereco, cpf, email, telefone);
        this.biblioteca = biblioteca;
    }

    public void cadastrarMembro(Membro membro) {
        biblioteca.cadastrarMembro(membro);
        System.out.println("Membro cadastrado com sucesso: " + membro.getNome());
    }

    public void removerMembro(Membro membro) {
        boolean removido = biblioteca.removerMembro(membro);
        if (removido) {
            System.out.println("Membro removido com sucesso: " + membro.getNome());
        } else {
            System.out.println("Não foi possível remover o membro: " + membro.getNome());
        }
    }

    public void adicionarItem(Livro livro) {
        biblioteca.adicionarItem(livro);
        System.out.println("Item adicionado com sucesso: " + livro.getTitulo());
    }

    public void removerItem(Livro livro) {
        boolean removido = biblioteca.removerItem(livro);
        if (removido) {
            System.out.println("Item removido com sucesso: " + livro.getTitulo());
        } else {
            System.out.println("Não foi possível remover o item: " + livro.getTitulo());
        }
    }

    public void realizarEmprestimo(Membro membro, Item item) {
        biblioteca.emprestarItem(membro, item);
    }

    public void realizarDevolucao(Membro membro, Item item) {
        biblioteca.devolverItem(membro, item);
    }

    public List<Livro> pesquisarLivrosPorTitulo(String titulo) {
        return biblioteca.pesquisarLivrosPorTitulo(titulo);
    }

    public List<Livro> pesquisarLivrosDisponiveis() {
        return biblioteca.pesquisarLivrosDisponiveis();
    }
}
