import java.time.LocalDate;

public class Emprestimo {
    private Item itemEmprestado;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    //Construtor
    public Emprestimo(Item itemEmprestado, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.itemEmprestado = itemEmprestado;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Item getItem() {
        return itemEmprestado;
    }

    //Getters e Setters
    public void setItem(Item item) {
        this.itemEmprestado = item;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
