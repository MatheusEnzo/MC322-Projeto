import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField campoEmail;
	private JTextField campoSenha;


	/**
	 * Create the frame.
	 */
	public Login(Biblioteca biblioteca, Bibliotecario bibliotecario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoEmail = new JTextField();
		campoEmail.setHorizontalAlignment(SwingConstants.CENTER);
		campoEmail.setBounds(10, 56, 414, 37);
		contentPane.add(campoEmail);
		campoEmail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 21, 414, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSenha.setBounds(10, 104, 414, 37);
		contentPane.add(lblSenha);
		
		campoSenha = new JTextField();
		campoSenha.setHorizontalAlignment(SwingConstants.CENTER);
		campoSenha.setColumns(10);
		campoSenha.setBounds(10, 142, 414, 37);
		contentPane.add(campoSenha);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = campoEmail.getText();
        		String senha = campoSenha.getText();
        		Usuario aux = buscarBibliotecario(email, biblioteca);
        		if(aux != null && aux instanceof Bibliotecario)
        		{
        			Bibliotecario auxBibliotecario = (Bibliotecario) aux;
        			if(auxBibliotecario.getSenha().equals(senha))
        			{
        				dispose();
        				InterfaceGrafica interfaceGrafica = new InterfaceGrafica(biblioteca, bibliotecario);
        		        interfaceGrafica.setVisible(true);
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(null,"Email ou senha inválidos.");
            			campoEmail.setText("");
        				campoSenha.setText("");
        			}
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(null,"Email ou senha inválidos.");
        			campoEmail.setText("");
    				campoSenha.setText("");
        		}
			}
		});
		btnNewButton.setBounds(166, 202, 98, 37);
		contentPane.add(btnNewButton);
	}
	
	private Usuario buscarBibliotecario(String bibliotecarioEmail, Biblioteca biblioteca) {
        for (Usuario usuario : biblioteca.getListaUsuario()) {
	        if (usuario.getEmail().equals(bibliotecarioEmail)) {
	        	return usuario;
	        } 
	        else {
	        	continue;
	        }
	  
        }
        return null;
    }
}
