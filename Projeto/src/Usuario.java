
public abstract class Usuario {
	private String nome;
	private String endereco;
	private final String cpf;
	private String email;
	private String telefone;
	
	public Usuario(String nome, String endereco, String cpf, String email, String telefone)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}
	
    public String toCsvString() {
        String csvString = nome + ","
        + endereco + ","
        + cpf + ","
        + email + ","
        + telefone;
        return csvString;
    }
	
    
    @Override
    public String toString() {
        String output = "Nome: " + nome + "\n";
        output += "Endereço: " + endereco + "\n";
        output += "CPF:  " + cpf + "\n";
        output += "Email:  " + email + "\n";
        output += "Telefone:  " + telefone + "\n";
        return output;
    }

}
