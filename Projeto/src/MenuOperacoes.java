
public enum MenuOperacoes {
    CADASTRAR(1),
    LISTAR(2),
    GRAVAR_LER_ARQUIVO(3),
    REMOVER(4),
    EMPRESTAR_DEVOLVER(5),
    SAIR(0);

    public int operacao;

    MenuOperacoes(int operacao) {
        this.operacao = operacao;
    }
    
    public int getOperacao() {
        return this.operacao;
    }
}
