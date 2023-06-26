import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private Item itemEmprestado;
    private Membro membro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    //Construtor
    public Emprestimo(Item itemEmprestado, Membro membro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.itemEmprestado = itemEmprestado;
        this.membro = membro;
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

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
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
    
    public String toCsvString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataEmprestimoString = dataEmprestimo.format(dateFormatter);
        String dataDevolucaoString = dataDevolucao.format(dateFormatter);

        return itemEmprestado.getTitulo() + "," + membro.getCpf() + "," + dataEmprestimoString + "," + dataDevolucaoString;
    }

    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataEmprestimoString = dataEmprestimo.format(formatter);
        String dataDevolucaoString = dataDevolucao.format(formatter);

        StringBuilder sb = new StringBuilder();
        sb.append("Item emprestado: ").append(itemEmprestado.getTitulo()).append("\n");
        sb.append("Membro: ").append(membro.getCpf()).append("\n");
        sb.append("Data de empréstimo: ").append(dataEmprestimoString).append("\n");
        sb.append("Data de devolução: ").append(dataDevolucaoString).append("\n");

        return sb.toString();
    }
    
}
