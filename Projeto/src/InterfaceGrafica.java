import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class InterfaceGrafica extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextArea textArea;

    public InterfaceGrafica(Biblioteca biblioteca, Bibliotecario bibliotecario) {

        // Configurações da janela
        setTitle("Gestão da Biblioteca");
        setSize(519, 498);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Define o ícone da janela
        ImageIcon icon = new ImageIcon("images/icon.png");
        setIconImage(icon.getImage());

        // Cria a barra de menu
        JMenuBar menuBar = new JMenuBar();
        
        // Cria o menu "Arquivo"
        JMenu menuArquivo = new JMenu("Arquivo");
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
        
        // Define a barra de menu na janela
        setJMenuBar(menuBar);
        getContentPane().setLayout(null);
        
        JButton listarEmprestimos = new JButton("Listar Emprestimos");
        listarEmprestimos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Text(biblioteca.PrintaListaEmprestimos());
        	}
        });
        listarEmprestimos.setFont(new Font("Tahoma", Font.PLAIN, 14));
        listarEmprestimos.setBounds(47, 106, 160, 41);
        getContentPane().add(listarEmprestimos);
        
        JButton cadastrarItem = new JButton("Cadastrar Item");
        cadastrarItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastrarItem janela = new CadastrarItem(bibliotecario);
        		janela.setVisible(true);
        	}
        });
        cadastrarItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cadastrarItem.setBounds(280, 106, 147, 41);
        getContentPane().add(cadastrarItem);
        
        JButton cadastrarMembro = new JButton("Cadastrar Membro");
        cadastrarMembro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastrarMembro janela = new CadastrarMembro(bibliotecario);
        		janela.setVisible(true);
        	}
        });
        cadastrarMembro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cadastrarMembro.setBounds(53, 182, 147, 41);
        getContentPane().add(cadastrarMembro);
        
        JButton removerItem = new JButton("Remover Item");
        removerItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RemoverItem janela = new RemoverItem(bibliotecario);
        		janela.setVisible(true);
        	}
        });
        removerItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        removerItem.setBounds(280, 182, 147, 41);
        getContentPane().add(removerItem);
        
        JButton removerUsuario = new JButton("Remover Membro");
        removerUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RemoverMembro janela = new RemoverMembro(bibliotecario);
        		janela.setVisible(true);
        	}
        });
        removerUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        removerUsuario.setBounds(53, 262, 147, 41);
        getContentPane().add(removerUsuario);
        
        JButton listarItens = new JButton("Listar Itens");
        listarItens.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Text(biblioteca.PrintaListaItens());
        	}
        });
        listarItens.setFont(new Font("Tahoma", Font.PLAIN, 14));
        listarItens.setBounds(53, 36, 147, 41);
        getContentPane().add(listarItens);
        
        JButton listarMembros = new JButton("Listar Membros");
        listarMembros.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Text(biblioteca.PrintaListaMembros());
        	}
        });
        listarMembros.setFont(new Font("Tahoma", Font.PLAIN, 14));
        listarMembros.setBounds(280, 36, 147, 41);
        getContentPane().add(listarMembros);
        
        JButton emprestar = new JButton("Emprestar Item");
        emprestar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		EmprestarItem janela = new EmprestarItem(bibliotecario);
        		janela.setVisible(true);
        	}
        });
        emprestar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        emprestar.setBounds(280, 262, 147, 41);
        getContentPane().add(emprestar);
        
        JButton devolver = new JButton("Devolver Item");
        devolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DevolverItem janela = new DevolverItem(bibliotecario);
        		janela.setVisible(true);
        	}
        });
        devolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
        devolver.setBounds(53, 353, 147, 41);
        getContentPane().add(devolver);
        
        // Cria o JTextArea
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(280, 353, 147, 41);
        getContentPane().add(scrollPane);
        
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
                ArquivoArtigo.gravarArtigos(biblioteca, bibliotecario);
//                ArquivoLivro.gravarLivros(biblioteca);
//                ArquivoRevista.gravarRevistas(biblioteca);
//                ArquivoMembro.gravarMembros(biblioteca);
//                ArquivoEmprestimo.gravarEmprestimos(biblioteca);
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
    public void exibirMensagem(String mensagem) {
        textArea.append(mensagem + "\n");
    }
    // Método para exibir mensagens do sistema no JTextArea
    public void exibirMensagemSb(StringBuilder mensagem) {
        textArea.append(mensagem + "\n");
    }
}