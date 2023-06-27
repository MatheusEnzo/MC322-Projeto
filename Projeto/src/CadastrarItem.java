import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CadastrarItem extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoTitulo;
	private JTextField campoAutor;
	private JTextField campoEditora;
	private JTextField campoGenero;
	private JTextField campoData;
	private JTextField campoID;
	private JButton btnNewButton;


	/**
	 * Create the frame.
	 */
	public CadastrarItem(Bibliotecario bibliotecario) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);


		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Título:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 16, 42, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAutor.setBounds(10, 42, 42, 20);
		getContentPane().add(lblAutor);
		
		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEditora.setBounds(10, 73, 49, 20);
		getContentPane().add(lblEditora);
		
		JLabel lblDataDePublicao = new JLabel("Data de Publicação (dd/mm/aaaa): ");
		lblDataDePublicao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataDePublicao.setBounds(10, 104, 199, 20);
		getContentPane().add(lblDataDePublicao);
		
		JLabel lblGnero = new JLabel("Gênero:");
		lblGnero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGnero.setBounds(10, 135, 49, 20);
		getContentPane().add(lblGnero);
		
		JLabel lblIsbnissndoi = new JLabel("ISBN/ISSN/DOI:");
		lblIsbnissndoi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIsbnissndoi.setBounds(10, 166, 93, 20);
		getContentPane().add(lblIsbnissndoi);
		
		campoTitulo = new JTextField();
		campoTitulo.setBounds(62, 14, 362, 20);
		getContentPane().add(campoTitulo);
		campoTitulo.setColumns(10);
		
		campoAutor = new JTextField();
		campoAutor.setColumns(10);
		campoAutor.setBounds(62, 43, 362, 20);
		getContentPane().add(campoAutor);
		
		campoEditora = new JTextField();
		campoEditora.setColumns(10);
		campoEditora.setBounds(62, 74, 362, 20);
		getContentPane().add(campoEditora);
		
		campoGenero = new JTextField();
		campoGenero.setColumns(10);
		campoGenero.setBounds(62, 135, 362, 20);
		getContentPane().add(campoGenero);
		
		campoData = new JTextField();
		campoData.setBounds(219, 105, 205, 20);
		getContentPane().add(campoData);
		campoData.setColumns(10);
		
		campoID = new JTextField();
		campoID.setBounds(113, 167, 311, 20);
		getContentPane().add(campoID);
		campoID.setColumns(10);
		
		btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = campoTitulo.getText();
				String autor = campoAutor.getText();
				String editora = campoEditora.getText();			
				String data = campoData.getText();
				String genero = campoGenero.getText();
				String id = campoID.getText();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				boolean flag=true;
				
                try {
                	Date formato = sdf.parse(data);
                        
                    if(Validacao.verificarISBN(id))
    				{
    					Livro novo = new Livro(titulo, autor, editora, formato, genero, id, 1);
    					bibliotecario.adicionarItem(novo);
    					JOptionPane.showMessageDialog(null,"Cadastro com sucesso.");
    					dispose();
    				}
    				else if(Validacao.verificarISSN(id))
    				{
    					Revista novo = new Revista(titulo, autor, editora, formato, genero, id, 1);	
    					bibliotecario.adicionarItem(novo);
    					JOptionPane.showMessageDialog(null,"Cadastro com sucesso.");
    					dispose();
    				}
    				else if(Validacao.verificarDOI(id))
    				{
    					Artigo novo = new Artigo(titulo, autor, editora, formato, genero, id, 1);
    					bibliotecario.adicionarItem(novo);
    					JOptionPane.showMessageDialog(null,"Cadastro com sucesso.");
    					dispose();
    				}
    				else
    				{
    					JOptionPane.showMessageDialog(null,"ISBN/ISSB/DOI inválido.");
    					campoTitulo.setText("");
    					campoAutor.setText("");
    					campoEditora.setText("");
    					campoData.setText("");
    					campoID.setText("");
    					campoGenero.setText("");
    				}
                    
                } catch (ParseException e1) {
                    	flag=false;
                    }
				
				if(flag==false)
				{
					JOptionPane.showMessageDialog(null,"Data no formato errado.");
					campoTitulo.setText("");
					campoAutor.setText("");
					campoEditora.setText("");
					campoData.setText("");
					campoID.setText("");
					campoGenero.setText("");
				}
			}
		});
		btnNewButton.setBounds(168, 227, 109, 23);
		getContentPane().add(btnNewButton);
		
	}
}
