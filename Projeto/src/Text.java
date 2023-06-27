import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Text extends JFrame {



	/**
	 * Create the frame.
	 */
	public Text(String lista) {
		JFrame frame = new JFrame();
		JTextArea textArea = new JTextArea(lista);
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.getContentPane().add(scrollPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
