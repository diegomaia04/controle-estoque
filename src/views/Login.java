package views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatus;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/favicon.png")));
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnAcessar.setBounds(252, 179, 89, 23);
		contentPane.add(btnAcessar);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(12, 154, 48, 48);
		contentPane.add(lblStatus);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(12, 22, 46, 14);
		contentPane.add(lblNewLabel);

		txtLogin = new JTextField();
		txtLogin.setBounds(47, 19, 178, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(12, 84, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(47, 81, 178, 20);
		contentPane.add(txtSenha);

		getRootPane().setDefaultButton(btnAcessar);

	}// fim do construtor

	DAO dao = new DAO();

	// inicio status
	private void Status() {
		// System.out.println("teste - Janela Ativada");
		// uso da classe conection (JBBC) para estabelecer a conexao
		try {
			Connection con = dao.conectar();
			if (con == null) {
				// System.out.println("Erro de conexão");
				lblStatus.setIcon(new ImageIcon(Usuarios.class.getResource("/img/dboff.png")));
			} else {
				// System.out.println("Banco conectado!");
				lblStatus.setIcon(new ImageIcon(Usuarios.class.getResource("/img/dbon.png")));
			}
			// Nunca esequcer de encerrar a conexão
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	} // fim do status

	private void logar() {
		// validação da senha(captura segura)
		String capturaSenha = new String(txtSenha.getPassword());
		// validação de campos obrigatorios
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o seu Login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Informe a sua senha");
			txtSenha.requestFocus();
		} else {
			// logica principal (pesquisar login e senha correspondente)
			String read = "select * from usuarios where login = ? and senha = md5 (?);";
			try {
				// abrir a conexão com o banco de dados
				Connection con = dao.conectar();
				// preparar a querry
				PreparedStatement pst = con.prepareStatement(read);
				// setar os argumentos(?, ?, login e senha)
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				// executar a querry e executar se existir login e senha correspondenet no banco
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					Main main = new Main();
					// a linha abaixo captura o perfil do usuario
					String perfil = rs.getString(5);
					// comportamento de login em função do perfil
					if (perfil.equals("admin")) {
						main.setVisible(true);
						// Alterar a label da tela pricipal (inseriri nome do usuario no rodape)
						// apoio ao entedimento da logica, rs pega diretamente do banco de dados
						// System.out.println(rs.getString(5));
						main.lblUsuario.setText(rs.getString(2));
						//Habilitar os botoes
						main.btnRelatorios.setEnabled(true);
						main.btnUsuarios.setEnabled(true);
						//alterar a cor do rodape
						main.panelUsuarios.setBackground(Color.GREEN);
						// fechar o JFrame
						this.dispose();

					} else {
						main.setVisible(true);
						// Alterar a label da tela pricipal (inseriri nome do usuario no rodape)
						// apoio ao entedimento da logica, rs pega diretamente do banco de dados
						// System.out.println(rs.getString(5));
						main.lblUsuario.setText(rs.getString(2));
						// fechar o JFrame
						this.dispose();

					}

				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou Senha inválidos");

				}
				// encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

}// fim do codigo
