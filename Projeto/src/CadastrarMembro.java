import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarMembro extends JFrame {

	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField campoEndereco;
	private JTextField campoCPF;
	private JTextField campoEmail;
	private JTextField campoTelefone;


	/**
	 * Create the frame.
	 */
	public CadastrarMembro(Bibliotecario bibliotecario) {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEndereo = new JLabel("Endereço:");
		lblEndereo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEndereo.setBounds(10, 53, 71, 21);
		contentPane.add(lblEndereo);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCpf.setBounds(10, 99, 71, 21);
		contentPane.add(lblCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(10, 145, 71, 21);
		contentPane.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefone.setBounds(10, 189, 71, 21);
		contentPane.add(lblTelefone);
		
		JLabel lblNewLabel_2_1 = new JLabel("Nome:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(10, 11, 71, 21);
		contentPane.add(lblNewLabel_2_1);
		
		campoNome = new JTextField();
		campoNome.setBounds(79, 12, 318, 20);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		campoEndereco = new JTextField();
		campoEndereco.setColumns(10);
		campoEndereco.setBounds(79, 54, 318, 20);
		contentPane.add(campoEndereco);
		
		campoCPF = new JTextField();
		campoCPF.setColumns(10);
		campoCPF.setBounds(79, 100, 318, 20);
		contentPane.add(campoCPF);
		
		campoEmail = new JTextField();
		campoEmail.setColumns(10);
		campoEmail.setBounds(79, 146, 318, 20);
		contentPane.add(campoEmail);
		
		campoTelefone = new JTextField();
		campoTelefone.setColumns(10);
		campoTelefone.setBounds(79, 190, 318, 20);
		contentPane.add(campoTelefone);
		
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = campoNome.getText();
				String endereco = campoEndereco.getText();
				String cpf = campoCPF.getText();
				cpf = cpf.replaceAll("\\D+", "");
				String email = campoEmail.getText();
				String telefone = campoTelefone.getText();
				
				if(!Validacao.validarCPF(cpf))
				{
					JOptionPane.showMessageDialog(null,"CPF inválido.");
					campoNome.setText("");
					campoEndereco.setText("");
					campoCPF.setText("");
					campoEmail.setText("");
					campoTelefone.setText("");
				}
				else
				{
					boolean flag=true;
					for(Usuario i : bibliotecario.getBiblioteca().getListaUsuario())
					{
						if(i.getCpf().equals(cpf))
						{
							JOptionPane.showMessageDialog(null,"CPF já cadastrado.");
							campoNome.setText("");
							campoEndereco.setText("");
							campoCPF.setText("");
							campoEmail.setText("");
							campoTelefone.setText("");
							flag=false;
							break;
						}
					}
					if(flag==true)
					{
						Membro novo = new Membro(nome, endereco, cpf, email, telefone);
						bibliotecario.cadastrarMembro(novo);
						JOptionPane.showMessageDialog(null,"Cadastro com sucesso.");
						dispose();
					}
				}
			}
		});
		btnNewButton.setBounds(171, 227, 112, 23);
		contentPane.add(btnNewButton);
	}
}
