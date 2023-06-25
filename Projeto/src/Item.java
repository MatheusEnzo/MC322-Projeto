import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Item {
	private String titulo;
	private String autor;
	private String editora;
	private Date data;
	private String genero;
	private boolean disponivel;
	
	//Construtor
	public Item(String titulo, String autor, String editora, Date data, String genero)
	{
		this.titulo = titulo;
		this.autor = autor;
		this.data = data;
		this.editora = editora;
		this.genero = genero;
		this.disponivel = true;
	}

	//Getters e Setters
	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
    public String toCsvString() {
        String csvString = titulo + ","
        + autor + ","
        + editora + ","
        + data.toString() + ","
        + genero + ",";
        return csvString;
    }
    
    private String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }

	//Determina se o codigo identificador de um Item eh valido
	public boolean Validar(){
        Validacao validacao = new Validacao(this);
        return validacao.validarCodigo();

    }
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Autor: ").append(autor).append("\n");
        sb.append("Editora: ").append(editora).append("\n");
        sb.append("Data de publicação: ").append(formatDate(data)).append("\n");
        sb.append("Gênero: ").append(genero).append("\n");
        sb.append("Disponível: ").append(disponivel ? "Sim" : "Não").append("\n");
        return sb.toString();
    }

}
