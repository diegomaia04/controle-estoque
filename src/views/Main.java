package views;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//os bobejtos abaixo serão manipulados pela classe Login
	JPanel panelUsuarios;
	private JLabel lblData;
	JButton btnUsuarios;
	JButton btnRelatorios;
	JLabel lblUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/lojinha.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				lblData.setText(formatador.format(data));

			}
		});
		setTitle("Controle de estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnUsuarios = new JButton("");
		btnUsuarios.setEnabled(false);
		btnUsuarios.setBounds(10, 9, 137, 124);
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setToolTipText("Usuarios");
		btnUsuarios.setIcon(new ImageIcon(Main.class.getResource("/img/users.png")));
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnUsuarios);

		JButton btnFornecedores = new JButton("");
		btnFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedores forcenedores = new Fornecedores();
				forcenedores.setVisible(true);
			}
		});
		btnFornecedores.setBounds(182, 9, 152, 124);
		btnFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFornecedores.setToolTipText("Fornecedores ");
		btnFornecedores.setIcon(new ImageIcon(Main.class.getResource("/img/supliers.png")));
		contentPane.add(btnFornecedores);

		JButton btnProdutos = new JButton("");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produtos produtos = new Produtos();
				produtos.setVisible(true);				
			}
		});
		btnProdutos.setBounds(387, 11, 146, 122);
		btnProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProdutos.setToolTipText("Produtos");
		btnProdutos.setIcon(new ImageIcon(Main.class.getResource("/img/products.png")));
		contentPane.add(btnProdutos);

		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				cliente.setVisible(true);
			}
		});
		btnClientes.setBounds(10, 166, 137, 122);
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setToolTipText("Clientes ");
		btnClientes.setIcon(new ImageIcon(Main.class.getResource("/img/clientes.png")));
		contentPane.add(btnClientes);

		btnRelatorios = new JButton("");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
						
			}
		});
		btnRelatorios.setEnabled(false);
		btnRelatorios.setBounds(188, 166, 146, 122);
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setToolTipText("Relatorios");
		btnRelatorios.setIcon(new ImageIcon(Main.class.getResource("/img/report.png")));
		contentPane.add(btnRelatorios);
		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);

			}
		});
		btnSobre.setBounds(387, 166, 146, 122);
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setToolTipText("Sobre ");
		btnSobre.setIcon(new ImageIcon(Main.class.getResource("/img/about.png")));
		contentPane.add(btnSobre);

		panelUsuarios = new JPanel();
		panelUsuarios.setBounds(0, 319, 543, 62);
		panelUsuarios.setBackground(SystemColor.textHighlight);
		panelUsuarios.setForeground(SystemColor.textHighlight);
		contentPane.add(panelUsuarios);
		panelUsuarios.setLayout(null);

		lblData = new JLabel("");
		lblData.setForeground(SystemColor.desktop);
		lblData.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblData.setBounds(238, 11, 283, 40);
		panelUsuarios.add(lblData);

		JLabel lblNewLabel = new JLabel("Usu\u00E1rio:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 63, 40);
		panelUsuarios.add(lblNewLabel);

		lblUsuario = new JLabel("");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(79, 11, 143, 40);
		panelUsuarios.add(lblUsuario);
	}// fim do construtor
}// fim do codigo
