
public enum MenuOperacoes {
    CADASTRAR(1),
    LISTAR(2),
    REMOVER(3),
    SAIR(0);

    public int operacao;

    MenuOperacoes(int operacao) {
        this.operacao = operacao;
    }
    
    public int getOperacao() {
        return this.operacao;
    }
}
