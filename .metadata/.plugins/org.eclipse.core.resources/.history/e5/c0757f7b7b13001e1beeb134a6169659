
public class Validacao {
    private String codigo;
    private String tipo; //define a operacao de acordo com classe

    //Construtor
    Validacao(Item item){
        if (item instanceof Livro){
            this.tipo = "Livro";
            this.codigo = ((Livro) item).getIsbn();
        }
        else if (item instanceof Revista){
            this.tipo = "Revista";
            this.codigo = ((Revista) item).getIssn();
        }
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

    //Verifica se 10o digito condiz com 9 primeiros
    public boolean algoritmoIsbn2001(String isbn){
        int soma = 0, checkdigit;
        if (isbn.charAt(8) == 'X')
            checkdigit = 10;
        else
            checkdigit = isbn.charAt(8) - 48;
        for (int i = 0; i < 8; i++)
            soma += (i + 1) * (isbn.charAt(i) - 48);
        int resto = (soma % 11);
        if (resto == 0)
            return (checkdigit == 0);
        else
            return (checkdigit == 11 - resto);
    }

      //Verifica se 13o digito condiz com 12 primeiros
      public boolean algoritmoIsbn2005(String issn){
        int soma = 0, mult = 1, checkdigit;
        if (issn.charAt(8) == 'X')
            checkdigit = 10;
        else
            checkdigit = issn.charAt(8) - 48;
        for (int i = 0; i < 8; i++){
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
        int soma = 0, mult = 8, checkdigit;
        if (issn.charAt(8) == 'X')
            checkdigit = 10;
        else
            checkdigit = issn.charAt(8) - 48;
        for (int i = 0; i < 8; i++){
            soma += mult * (issn.charAt(i) - 48);
            mult --;
        }
        int resto = (soma % 11);
        if (resto == 0)
            return (checkdigit == 0);
        else
            return (checkdigit == 11 - resto);
    }

}