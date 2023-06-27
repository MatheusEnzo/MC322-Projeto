import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmprestarItem extends JFrame {

	private JPanel contentPane;
	private JTextField campoTitulo;
	private JTextField campoCPF;

	/**
	 * Create the frame.
	 */
	public EmprestarItem(Bibliotecario bibliotecario) {
		setTitle("Empréstimo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Título do Item");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 43, 414, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblCpfDoMembro = new JLabel("CPF do Membro");
		lblCpfDoMembro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpfDoMembro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCpfDoMembro.setBounds(10, 120, 414, 24);
		contentPane.add(lblCpfDoMembro);
		
		campoTitulo = new JTextField();
		campoTitulo.setBounds(32, 78, 370, 31);
		contentPane.add(campoTitulo);
		campoTitulo.setColumns(10);
		
		campoCPF = new JTextField();
		campoCPF.setColumns(10);
		campoCPF.setBounds(32, 155, 370, 31);
		contentPane.add(campoCPF);
		
		JButton btnNewButton = new JButton("Emprestar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = campoTitulo.getText();
				String cpf = campoCPF.getText();
				cpf = cpf.replaceAll("\\D+", "");
				String resultado = bibliotecario.emprestarItemPorTitulo(cpf, titulo);
				JOptionPane.showMessageDialog(null,resultado);
				campoTitulo.setText("");
				campoCPF.setText("");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(162, 219, 109, 31);
		contentPane.add(btnNewButton);
	}
}
