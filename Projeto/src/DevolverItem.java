import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DevolverItem extends JFrame {

	private JPanel contentPane;
	private JTextField campoTitulo;
	private JTextField campoCPF;

	/**
	 * Create the frame.
	 */
	public DevolverItem(Bibliotecario bibliotecario) {
		setTitle("Devolução");
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
		
		JButton btnNewButton = new JButton("Devolver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = campoTitulo.getText();
				String cpf = campoCPF.getText();
				cpf = cpf.replaceAll("\\D+", "");
				String resultado = bibliotecario.devolverItemPorTitulo(cpf, titulo);
				JOptionPane.showMessageDialog(null,resultado);
				campoTitulo.setText("");
				campoCPF.setText("");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(163, 219, 110, 31);
		contentPane.add(btnNewButton);
		
		campoTitulo = new JTextField();
		campoTitulo.setBounds(37, 78, 357, 31);
		contentPane.add(campoTitulo);
		campoTitulo.setColumns(10);
		
		campoCPF = new JTextField();
		campoCPF.setColumns(10);
		campoCPF.setBounds(37, 155, 357, 31);
		contentPane.add(campoCPF);
	}

}
