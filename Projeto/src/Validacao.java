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
            return validarLivro(this.codigo);
            case "Revista":
            return validarRevista(this.codigo);
            case "Artigo":
            return validarArtigo(this.codigo);
            case "CPF":
            {
            boolean valido = validarCPF(this.codigo);
            if (!valido)
                System.out.println("Credenciais inválidas. . . Tente novamente");
            return valido;
            }
            default:
            return false;
        }
    }

    //Verifica a validade do isbn
    public boolean validarLivro(String codigo){
        //Remover caracteres nao numericos
        String isbn = codigo.replaceAll("[^0-9,X]", "");
        //Verificar se contem 10 ou 13 digitos
        if (isbn.length() == 10)
            return algoritmoIsbn2001(isbn);
        else if (isbn.length() == 13)
            return algoritmoIsbn2005(isbn);
        return false;
    }

    //Verifica a validade do issn
    public boolean validarRevista(String codigo){
        //Remover caracteres nao numericos
        String issn = codigo.replaceAll("[^0-9,X]", "");
        //Verificar se contem 8 digitos
        if (issn.length() == 8)
            return algoritmoIssn(issn);
        return false;
    }

    //Verifica formato do Doi
    public boolean validarArtigo(String codigo){
        String[] doi = codigo.split("/"); // Divide a string codigo
        // Extrai os valores do array e separa o prefixo
        String prefixo = doi[0];
        //O guia oficial DOI indica que os mesmos devem estar no formato "doi:10.nnnn[...]/[sufixo]", com o numero de 'n's >= 4
        if (prefixo.length() < 11)
            return false;
        else if (prefixo.charAt(4) != '1' || prefixo.charAt(5) != '0')
            return false;
        return true;
    }

    //Verifica validade do CPF de Usuario
    public boolean validarCPF(String cpf){
        //Remover Caracteres Nao Numericos
		String numeros = cpf.replaceAll("[^0-9]", "");
		//Verificar Se Contem 11 Digitos 
		if (numeros.length() != 11)
			return false;
		//Verificar Se Todos os Digitos Sao Iguais
		if (repetidos(numeros))
			return false;
		//Calculando Algoritmo de Verificacao
		return algoritmoCPF(numeros);
		}
	
    //Verifica se todos os digitos numéricos são idênticos
	public boolean repetidos(String numeros) {
		int l = numeros.length();
		for (int i = 1; i < l; i++)
			if (numeros.charAt(0) != numeros.charAt(i))
				return false;
		return true;
	}
		
	//Verifica se 10o e 11o digitos são condizentes com os 9 primeiros
    public boolean algoritmoCPF(String numeros) {
		//Verificando 10o Digito
		int soma = 0, mult = 10;
		for (int i = 0; i < 9; i++) //Subtrai 48 de acordo com tabela ASCII 
			soma += (mult - i) * (numeros.charAt(i) - 48); //multiplica 1os 9 digitos por multiplicador descendo de 10-2 
		int resto = (soma % 11); //resto da soma por 11
		if (resto <= 2)
			if (numeros.charAt(9) - 48 != 0) //se resto é menor ou igual do que 2, 10o digito deve ser 0
				return false;
		else if (numeros.charAt(9) - 48 != 11 - resto) //se resto é maior do que 2, 10o digito deve ser 11 menos resto
			return false;
			
		//Verificando 11o Digito
		soma = 0;
        mult = 11;
		for (int i = 0; i < 10; i++) //Mesmo processo, porem agora indo ate o 10o digito e multiplicador desce de 11-2
			soma += (mult - i) * (numeros.charAt(i) - 48);
		resto = (soma % 11);
		if (resto <= 2)
			return (numeros.charAt(10) - 48 == 0);
		else
            return (numeros.charAt(10) - 48 == 11 - resto);
    }

    //Verifica se 10o digito condiz com 9 primeiros
    public boolean algoritmoIsbn2001(String isbn){
        //Soma dos 9 primeiros digitos multiplicados pela posição da esquerda para a direita. O módulo 11 da soma deve ser 0
        int soma = 0, checkdigit;
        if (isbn.charAt(9) == 'X')
            checkdigit = 10;
        else
            checkdigit = isbn.charAt(9) - 48;
        for (int i = 0; i < 9; i++)
            soma += (i + 1) * (isbn.charAt(i) - 48);
        int resto = (soma % 11);
        if (resto == 0)
            return (checkdigit == 0);
        else
            return (checkdigit == 11 - resto);
    }

      //Verifica se 13o digito condiz com 12 primeiros
      public boolean algoritmoIsbn2005(String issn){
        //Multiplica alternadamente cada um dos 12 digitos por 1 ou 3. O módulo 10 da soma total deve ser 0
        int soma = 0, mult = 1, checkdigit;
        if (issn.charAt(12) == 'X')
            checkdigit = 10;
        else
            checkdigit = issn.charAt(12) - 48;
        for (int i = 0; i < 12; i++){
            soma += mult * (issn.charAt(i) - 48);
            if (mult == 1)
                mult = 3;
            else
                mult = 1;
        }
        int resto = (soma % 10);
        if (resto == 0)
            return (checkdigit == 0);
        else
            return (checkdigit == 10 - resto);
    }

    //Verifica se 8o digito condiz com 7 primeiros
    public boolean algoritmoIssn(String issn){
        //Soma dos oito digitos multiplicados pela posição da direita para a esquerda. O módulo 11 da soma deve ser 0
        int soma = 0, mult = 8, checkdigit;
        if (issn.charAt(7) == 'X')
            checkdigit = 10;
        else
            checkdigit = issn.charAt(7) - 48;
        for (int i = 0; i < 7; i++)
            soma += (mult - i) * (issn.charAt(i) - 48);
        int resto = (soma % 11);
        if (resto == 0)
            return (checkdigit == 0);
        else
            return (checkdigit == 11 - resto);
    }

}