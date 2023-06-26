import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalTime;

public class InterfaceGrafica extends JFrame {
	private static JTextArea textArea;
    private static final long serialVersionUID = 1L;

    public InterfaceGrafica() {
    	
    	// Instanciação de um objeto Biblioteca
    	LocalTime horario1 = LocalTime.of(9, 0); //Horario de abertura (09:00)
    	LocalTime horario2 = LocalTime.of(18, 0); //Horario de fechamento (18:00)
    	Biblioteca biblioteca = new Biblioteca("Biblioteca de Bytes da UNICAMP", "Rua Andrade da Silva, 199", horario1, horario2);
    	
    	// Instanciação de um objeto Bibliotecario (um dos possíveis usuários)
    	Bibliotecario bibliotecario = new Bibliotecario("José Pereira", "Rua Arlindo da Cruz, 456", "650.885.770-36", "josepereira@bibliobytes.com", "(11) 99690-3704", biblioteca);
    	biblioteca.getListaUsuario().add(bibliotecario); // Bibliotecario adicionado à lista de usuários da biblioteca
    	
        // Configurações da janela
        setTitle("Gestão da Biblioteca");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Define o ícone da janela
        ImageIcon icon = new ImageIcon("images/icon.png");
        setIconImage(icon.getImage());

        // Cria a barra de menu
        JMenuBar menuBar = new JMenuBar();
        
        // Cria o menu "Arquivo"
        JMenu menuArquivo = new JMenu("Arquivo");
        // Cria o menu "Exibir"
        JMenu menuExibir = new JMenu("Exibir");
        // Cria o menu "Emprestar"
        JMenu menuEmprestar = new JMenu("Emprestar");
        // Cria o submenu "Abrir"
        JMenu menuAbrir = new JMenu("Abrir");
        
        // Cria os itens de menu para o menu "Arquivo"
        JMenuItem itemSalvar = new JMenuItem("Salvar");
        JMenuItem itemSair = new JMenuItem("Sair");
        
        // Adiciona os itens de menu ao menu "Arquivo"
        menuArquivo.add(menuAbrir);
        menuArquivo.add(itemSalvar);
        menuArquivo.addSeparator(); // Adiciona uma linha separadora
        menuArquivo.add(itemSair);
        
        // Adiciona os itens de menu ao submenu "Abrir"
        JMenuItem itemAbrirArtigo = new JMenuItem("Artigos");
        JMenuItem itemAbrirLivro = new JMenuItem("Livros");
        JMenuItem itemAbrirRevista = new JMenuItem("Revistas");
        JMenuItem itemAbrirMembro = new JMenuItem("Membros");
        JMenuItem itemAbrirEmprestimo = new JMenuItem("Emprestimos");
        
        menuAbrir.add(itemAbrirArtigo);
        menuAbrir.add(itemAbrirLivro);
        menuAbrir.add(itemAbrirRevista);
        menuAbrir.add(itemAbrirMembro);
        menuAbrir.add(itemAbrirEmprestimo);

        
        // Adiciona os menus à barra de menu
        menuBar.add(menuArquivo);
        menuBar.add(menuExibir);
        menuBar.add(menuEmprestar);
        
        // Define a barra de menu na janela
        setJMenuBar(menuBar);
        
        // Cria o JTextArea
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 10, 480, 400);
        add(scrollPane);
        
        // Adicione os componentes gráficos aqui

        itemAbrirArtigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância do JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                
                // Abre a janela de seleção de arquivo
                int result = fileChooser.showOpenDialog(InterfaceGrafica.this);
                
                // Verifica se o usuário selecionou um arquivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    File file = fileChooser.getSelectedFile();
                    
                    ArquivoArtigo arquivoArtigo = new ArquivoArtigo();
                    arquivoArtigo.lerArquivoCSV(file, bibliotecario);
                }
            }
        });
        
        itemAbrirLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância do JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                
                // Abre a janela de seleção de arquivo
                int result = fileChooser.showOpenDialog(InterfaceGrafica.this);
                
                // Verifica se o usuário selecionou um arquivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    File file = fileChooser.getSelectedFile();
                    
                    ArquivoLivro arquivoLivro = new ArquivoLivro();
                    arquivoLivro.lerArquivoCSV(file, bibliotecario);
                }
            }
        });
        
        itemAbrirRevista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância do JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                
                // Abre a janela de seleção de arquivo
                int result = fileChooser.showOpenDialog(InterfaceGrafica.this);
                
                // Verifica se o usuário selecionou um arquivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    File file = fileChooser.getSelectedFile();
                    
                    ArquivoRevista arquivoRevista = new ArquivoRevista();
                    arquivoRevista.lerArquivoCSV(file, bibliotecario);
                }
            }
        });
        
        itemAbrirMembro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância do JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                
                // Abre a janela de seleção de arquivo
                int result = fileChooser.showOpenDialog(InterfaceGrafica.this);
                
                // Verifica se o usuário selecionou um arquivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    File file = fileChooser.getSelectedFile();
                    
                    ArquivoMembro arquivoMembro = new ArquivoMembro();
                    arquivoMembro.lerArquivoCSV(file, bibliotecario);
                }
            }
            
            
        });
        

        itemAbrirEmprestimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância do JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                
                // Abre a janela de seleção de arquivo
                int result = fileChooser.showOpenDialog(InterfaceGrafica.this);
                
                // Verifica se o usuário selecionou um arquivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    File file = fileChooser.getSelectedFile();
                    
                    ArquivoEmprestimo arquivoEmprestimo = new ArquivoEmprestimo();
                    arquivoEmprestimo.lerArquivoCSV(file, bibliotecario);
                }
            }
        });
        
        itemSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArquivoArtigo.gravarArtigos(biblioteca);
                ArquivoLivro.gravarLivros(biblioteca);
                ArquivoRevista.gravarRevistas(biblioteca);
                ArquivoMembro.gravarMembros(biblioteca);
                ArquivoEmprestimo.gravarEmprestimos(biblioteca);
            }
        });
        
        itemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela
            }
        });
        
    }

    // Método para exibir mensagens do sistema no JTextArea
    public static void exibirMensagem(String mensagem) {
        textArea.append(mensagem + "\n");
    }
    
    public static void main(String[] args) {
    	
        // Crie e exiba a janela
        InterfaceGrafica interfaceGrafica = new InterfaceGrafica();
        interfaceGrafica.setVisible(true);
    }
}
