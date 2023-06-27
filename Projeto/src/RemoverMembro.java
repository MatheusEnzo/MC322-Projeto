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

public class RemoverMembro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoCPF;

	/**
	 * Create the frame.
	 */
	public RemoverMembro(Bibliotecario bibliotecario) {
		setTitle("Remover Membro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CPF");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 66, 414, 28);
		contentPane.add(lblNewLabel);
		
		campoCPF = new JTextField();
		campoCPF.setBounds(59, 105, 314, 36);
		contentPane.add(campoCPF);
		campoCPF.setColumns(10);
		
		JButton btnNewButton = new JButton("Remover");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = campoCPF.getText();
				cpf = cpf.replaceAll("\\D+", "");
				if(bibliotecario.removerMembro(cpf))
				{
					JOptionPane.showMessageDialog(null,"Membro removido.");
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Não foi possível remover.");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(171, 196, 99, 28);
		contentPane.add(btnNewButton);
	}

}
