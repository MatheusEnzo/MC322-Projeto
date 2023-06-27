
public class Validacao {
    private String codigo;
    private String tipo; //define a operacao de acordo com classe

    //Construtores
    Validacao(Livro livro){
        this.tipo = "Livro";
        this.codigo = livro.getIsbn();
    }
    
    Validacao(Revista revista){
        this.tipo = "Revista";
        this.codigo = revista.getIssn();
    }

    Validacao(Artigo artigo){
        this.tipo = "Artigo";
        this.codigo = artigo.getDoi();
    }

    Validacao(String cpf){
        this.tipo = "CPF";
        this.codigo = cpf;
    }
	
    //Getters e Setters
    public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

    public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	//Desambiguacao para verificar validade de cada tipo
    public boolean validarCodigo(){
        switch(this.tipo){
            case "Livro":
            	return verificarISBN(this.codigo);
            case "Revista":
            	return verificarISSN(this.codigo);
            case "Artigo":
            	return verificarDOI(this.codigo);
            case "CPF":
            {
	            boolean valido = validarCPF(this.codigo);
	            if (!valido) {
	                InterfaceGrafica.exibirMensagem("CPF inválido. . . Tente novamente");
	            }
	            return valido;
            }
            default:
            return false;
        }
    }
	
    
	// Método para validar o ISBN 
	public static boolean verificarISBN(String isbn) {
	    // Removendo hífens e espaços em branco do ISBN
	    String isbnLimpo = isbn.replaceAll("-", "").replaceAll("\\s+", "");

	    // Verificando se o ISBN possui 10 ou 13 dígitos
	    if (isbnLimpo.length() != 10 && isbnLimpo.length() != 13) {
	        return false;
	    }

	    // Realizando a verificação do dígito verificador
	    int soma = 0;
	    int multiplicador = 10;
	    for (int i = 0; i < isbnLimpo.length() - 1; i++) {
	        char c = isbnLimpo.charAt(i);
	        if (!Character.isDigit(c)) {
	            return false;
	        }
	        int digito = Character.getNumericValue(c);
	        soma += digito * multiplicador;
	        multiplicador--;
	    }

	    int digitoVerificador = Character.getNumericValue(isbnLimpo.charAt(isbnLimpo.length() - 1));

	    if (isbnLimpo.length() == 10) {
	        return (soma % 11 == digitoVerificador);
	    } else { // ISBN com 13 dígitos
	        return ((soma % 10 == 0 && digitoVerificador == 0) || (10 - (soma % 10) == digitoVerificador));
	    }
	}

	// Método para validar o ISSN 
	public static boolean verificarISSN(String issn) {
	    // Removendo hífens e espaços em branco do ISSN
	    String issnLimpo = issn.replaceAll("-", "").replaceAll("\\s+", "");

	    // Verificando se o ISSN possui 8 dígitos
	    if (issnLimpo.length() != 8) {
	        return false;
	    }

	    // Realizando a verificação do dígito verificador
	    int soma = 0;
	    int multiplicador = 8;
	    for (int i = 0; i < issnLimpo.length() - 1; i++) {
	        char c = issnLimpo.charAt(i);
	        if (!Character.isDigit(c)) {
	            return false;
	        }
	        int digito = Character.getNumericValue(c);
	        soma += digito * multiplicador;
	        multiplicador--;
	    }

	    int digitoVerificador = Character.getNumericValue(issnLimpo.charAt(issnLimpo.length() - 1));

	    int resto = soma % 11;
	    int resultado = (resto == 0) ? 0 : 11 - resto;

	    return (resultado == digitoVerificador);
	}

	// Método para validar o DOI 
	public static boolean verificarDOI(String doi) {
	    // Removendo espaços em branco do DOI
	    String doiLimpo = doi.replaceAll("\\s+", "");

	    // Verificando se o DOI começa com "10." (padrão de prefixo)
	    if (!doiLimpo.startsWith("10.")) {
	        return false;
	    }

	    // Verificando se há pelo menos um caractere após o prefixo
	    if (doiLimpo.length() <= 3) {
	        return false;
	    }

	    // Verificando se o DOI contém um "/" separando o prefixo do sufixo
	    int separadorIndex = doiLimpo.indexOf("/");
	    if (separadorIndex <= 3 || separadorIndex == doiLimpo.length() - 1) {
	        return false;
	    }

	    // Verificando se o prefixo e o sufixo são alfanuméricos
	    String prefixo = doiLimpo.substring(0, separadorIndex);
	    String sufixo = doiLimpo.substring(separadorIndex + 1);
	    if (!prefixo.matches("[A-Za-z0-9]+") || !sufixo.matches("[A-Za-z0-9]+")) {
	        return false;
	    }

	    // Verificando se o DOI contém pelo menos um ponto no sufixo
	    if (!sufixo.contains(".")) {
	        return false;
	    }

	    // Verificação concluída, o DOI é considerado válido
	    return true;
	}
	
	
	// Método para validar o CPF
    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("\\D+", ""); // Removendo caracteres não numéricos do CPF
        if (cpf.length() != 11) { // Verificando se o CPF tem 11 dígitos
            return false;
        }
        // Verificando se todos os dígitos são iguais
        boolean todosDigitosIguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosDigitosIguais = false;
                break;
            }
        }
        if (todosDigitosIguais) {
        	InterfaceGrafica.exibirMensagem("O CPF fornecido pelo cliente é inválido");
            return false;
        }
        // Calculando os dígitos verificadores
        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * peso;
            peso--;
        }
        int resto = soma % 11;
        int primeiroDigitoVerificador = (resto < 2) ? 0 : (11 - resto);

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * peso;
            peso--;
        }
        resto = soma % 11;
        int segundoDigitoVerificador = (resto < 2) ? 0 : (11 - resto);

        // Verificando se os dígitos verificadores calculados são iguais aos dígitos verificadores do CPF
        if (primeiroDigitoVerificador == Character.getNumericValue(cpf.charAt(9)) &&
                segundoDigitoVerificador == Character.getNumericValue(cpf.charAt(10))) {
            return true;
        } else {
        	InterfaceGrafica.exibirMensagem("O CPF fornecido pelo cliente é inválido");
            return false;
        }
    }
	
}