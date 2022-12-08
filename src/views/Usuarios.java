package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.DAO;
import views.Usuarios;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import Atxy2k.CustomTextField.RestrictedTextField;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Construtor
	 */

	public Usuarios() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/pesquisar.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Status();
			}
		});
		setResizable(false);
		setTitle("Usu\u00E1rios");
		setBounds(100, 100, 410, 336);
		getContentPane().setLayout(null);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(66, 116, 69, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(10, 116, 46, 20);
		getContentPane().add(lblId);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsuario.setBounds(10, 58, 46, 20);
		getContentPane().add(lblUsuario);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLogin.setBounds(10, 11, 46, 20);
		getContentPane().add(lblLogin);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSenha.setBounds(10, 147, 46, 20);
		getContentPane().add(lblSenha);

		txtLogin = new JTextField();
		txtLogin.setBounds(64, 11, 146, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(66, 63, 146, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		btnPesquisar = new JButton("");
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisar.png")));
		btnPesquisar.setBounds(290, 43, 48, 41);
		getContentPane().add(btnPesquisar);

		btnLimpar = new JButton("");
		btnLimpar.setEnabled(false);
		btnLimpar.setToolTipText("Limpar");
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBorderPainted(false);
		btnLimpar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/delete.png")));
		btnLimpar.setBounds(10, 209, 88, 73);
		getContentPane().add(btnLimpar);

		btnCreate = new JButton("");
		btnCreate.setEnabled(false);
		btnCreate.setToolTipText("Criar");
		btnCreate.setContentAreaFilled(false);
		btnCreate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/create.png")));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContato();

			}
		});
		btnCreate.setBorderPainted(false);
		btnCreate.setBounds(108, 209, 88, 73);
		getContentPane().add(btnCreate);

		// Uso da tecla <Enter> junto com um botão
		getRootPane().setDefaultButton(btnPesquisar);
		RestrictedTextField usuario = new RestrictedTextField(txtUsuario);
		usuario.setLimit(50);
		usuario.setOnlyText(true);
		usuario.setAcceptSpace(true);
		RestrictedTextField id = new RestrictedTextField(txtId);
		id.setOnlyNums(true);

		btnDeletar = new JButton("");
		btnDeletar.setEnabled(false);
		btnDeletar.setToolTipText("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();
			}
		});
		btnDeletar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/delete.png")));
		btnDeletar.setContentAreaFilled(false);
		btnDeletar.setBorderPainted(false);
		btnDeletar.setBounds(304, 233, 34, 35);
		getContentPane().add(btnDeletar);

		btnUptade = new JButton("");
		btnUptade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verificar se o checkBox esta selecionado
				// Para verificar se não esta selecionado use NOT(!)
				if (!chckSenha.isSelected()) {
					alterarContatoSenha();
				} else {
					alterarContato();

				}
			}
		});
		btnUptade.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update.png")));
		btnUptade.setToolTipText("Alterar");
		btnUptade.setEnabled(false);
		btnUptade.setContentAreaFilled(false);
		btnUptade.setBorderPainted(false);
		btnUptade.setBounds(206, 209, 88, 73);
		getContentPane().add(btnUptade);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(64, 147, 148, 20);
		getContentPane().add(txtPassword);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPerfil.setBounds(220, 11, 46, 20);
		getContentPane().add(lblPerfil);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboPerfil.setBounds(261, 10, 93, 22);
		getContentPane().add(cboPerfil);

		chckSenha = new JCheckBox("Alterar senha");
		chckSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// fazer o check na caixa Jcheckbox
				txtPassword.setEditable(true);
				txtPassword.setText(null);
				txtPassword.requestFocus();
				txtPassword.setBackground(Color.red);
			}
		});
		chckSenha.setVisible(false);
		chckSenha.setBounds(218, 163, 136, 23);
		getContentPane().add(chckSenha);
		id.setOnlyNums(true);

	}// fim do construtor

	/*
	 * Status
	 */

	// criar um objeto para acessar o meteodo conectar() da classe DAO
	DAO dao = new DAO();
	private JTextField txtId;
	private JTextField txtLogin;
	private JTextField txtUsuario;
	private JButton btnCreate;
	private JButton btnLimpar;
	private JButton btnPesquisar;
	private JButton btnDeletar;
	private JButton btnUptade;
	private JPasswordField txtPassword;
	private JComboBox<?> cboPerfil;
	private JCheckBox chckSenha;

	private void Status() {
		// System.out.println("teste - Janela Ativada");
		// uso da classe conection (JBBC) para estabelecer a conexao
		try {
			Connection con = dao.conectar();
			if (con == null) {
				// System.out.println("Erro de conexão");
				// lblStatus.setIcon(new
				// ImageIcon(Usuarios.class.getResource("/img/dboff.png")));
			} else {
				// System.out.println("Banco conectado!");
				// lblStatus.setIcon(new
				// ImageIcon(Usuarios.class.getResource("/img/dbon.png")));
			}
			// Nunca esequcer de encerrar a conexão
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}// fim do status

	/**
	 * Pesquisa do usuario por Login
	 */
	private void pesquisarUsuario() {
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usuario");
			txtLogin.requestFocus();
		} else {
			String read = "select * from usuarios where login = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					txtUsuario.setText(rs.getString(2));
					txtId.setText(rs.getString(1));
					txtPassword.setText(rs.getNString(4));
					cboPerfil.setSelectedItem(rs.getString(5));
					// exibir a caixa checkbox
					chckSenha.setVisible(true);
					// desativar a edição da senha
					txtPassword.setEditable(false);
					// habilitar botoes e alterar e excluir
					btnDeletar.setEnabled(true);
					btnCreate.setEnabled(true);
					btnLimpar.setEnabled(true);
					btnUptade.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Usuário Inexistente");
					txtUsuario.setText(null);
					txtLogin.setText(null);
					txtPassword.setText(null);
					txtLogin.setText(null);
					txtLogin.requestFocus();
					btnCreate.setEnabled(true);

				}
				// fechar a conexão

				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				System.out.println(e1);
				JOptionPane.showMessageDialog(null, "Usuario não adicionado - Login já em uso");
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}

		}
	}

	private void excluirContato() {
		// System.out.println("teste de botao excluir);
		// validação
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desde contato ?", "Excluir Contato",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where id = ?";
			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				// execuatr o comandosql e confirmar a exclusão
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Contato excluido com sucesso");
				} else {
					JOptionPane.showMessageDialog(null, "Erro na exclusão do usuario");
				}
				// encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void alterarContato() {
		// System.out.println("teste");

		// validação
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuario");
			txtUsuario.requestFocus();

		} else if (txtPassword.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Senha!");
			txtPassword.requestFocus();
		} else {

			// logica principal
			String update = "update usuarios set usuario = ? , login = ?," + "perfil = ? where id= ?;";

			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a querry (instrução sql)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setNString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtId.getText());
				// executar a query e confimar inserção no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do contato atualizados com sucesso");
					limpar();
				}
				// encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	@SuppressWarnings("deprecation")
	private void alterarContatoSenha() {
		// System.out.println("teste");

		// validação
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuario");
			txtUsuario.requestFocus();

		} else if (txtPassword.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Senha!");
			txtPassword.requestFocus();
		} else {

			// logica principal
			String update = "update usuarios set usuario = ? , login = ?," + "senha = md5(?), perfil = ? where id= ?;";

			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a querry (instrução sql)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				String capturaSenha = new String(txtPassword.getPassword());
				pst.setString(3, capturaSenha);
				pst.setNString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtId.getText());
				// executar a query e confimar inserção no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do contato atualizados com sucesso");
					limpar();
				}
				// encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	void adicionarContato() {
		// validação de campos obrigadotria
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuario");
			txtUsuario.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtLogin.requestFocus();
		} else {

			// System.out.println("teste adicionar ");
			String creat = "insert into usuarios (usuario,login,senha, perfil) values(?,?,md5(?),?)";
			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// Preparar a querry(substituição)
				PreparedStatement pst = con.prepareStatement(creat);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				// captura segura de senha
				String capturaSenha = new String(txtPassword.getPassword());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());

				// executar a query e confimar inserção no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuario adicionado!");

				} else {
					JOptionPane.showMessageDialog(null, "Erro ao adicionar um Usuario");
				}
				limpar();

				// encerrar a conexao
				con.close();

			}catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				System.out.println(e1);
				JOptionPane.showMessageDialog(null, "Usuario não adicionado - Login já em uso");
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

//Limpar os setores
	private void limpar() {
		
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtPassword.setText(null);
		txtId.requestFocus();
		txtPassword.setEditable(true);
		cboPerfil.setSelectedItem("");
		btnCreate.setEnabled(false);
		btnLimpar.setEnabled(false);
		btnDeletar.setEnabled(false);
		btnUptade.setEnabled(false);
		btnPesquisar.setEnabled(true);
		txtPassword.setBackground(Color.white);
		chckSenha.setSelected(false); // desmarcar a caixa check
		chckSenha.setVisible(false);

	}
} // fim do codigo
