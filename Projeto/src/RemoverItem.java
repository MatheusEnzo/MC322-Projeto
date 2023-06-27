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

public class RemoverItem extends JFrame {

	private JPanel contentPane;
	private JTextField campoTitulo;

	/**
	 * Create the frame.
	 */
	public RemoverItem(Bibliotecario bibliotecario) {
		setTitle("Remoção de Item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Título do Item");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 45, 414, 39);
		contentPane.add(lblNewLabel);
		
		campoTitulo = new JTextField();
		campoTitulo.setBounds(20, 95, 404, 39);
		contentPane.add(campoTitulo);
		campoTitulo.setColumns(10);
		
		JButton btnNewButton = new JButton("Remover");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = campoTitulo.getText();
				if(bibliotecario.removerItemPorTitulo(titulo))
				{
					JOptionPane.showMessageDialog(null,"Item removido.");
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Não foi possível remover o item.");
					campoTitulo.setText("");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(165, 174, 112, 39);
		contentPane.add(btnNewButton);
	}
}
