package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fornecedores extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisar;
	private JTextField txtID;
	private JTextField txtCnpj;
	private JTextField txtIe;
	private JTextField txtRazaoSocial;
	private JTextField txtFantasia;
	private JButton btnBuscar;
	private JTextField txtSite;
	private JTextField txtFone;
	private JTextField txtZap;
	private JTextField txtContato;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtCEP;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JComboBox<?> cboUf;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fornecedores frame = new Fornecedores();
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
	public Fornecedores() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Status();
			}
		});
		setTitle("Fornecedores");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedores.class.getResource("/img/supliers.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 598, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Fornecedores:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(6, 7, 88, 14);
		contentPane.add(lblNewLabel);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscarCliente();
			}
		});
		txtPesquisar.setBounds(94, 4, 97, 20);
		contentPane.add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(6, 121, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtID = new JTextField();
		txtID.setBounds(32, 119, 35, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("CNPJ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(157, 120, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtCnpj = new JTextField();
		txtCnpj.setBounds(213, 118, 86, 20);
		contentPane.add(txtCnpj);
		txtCnpj.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("IE");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(324, 119, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtIe = new JTextField();
		txtIe.setBounds(365, 118, 106, 20);
		contentPane.add(txtIe);
		txtIe.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Raz\u00E3o Social");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(8, 171, 86, 14);
		contentPane.add(lblNewLabel_4);

		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setBounds(94, 168, 123, 20);
		contentPane.add(txtRazaoSocial);
		txtRazaoSocial.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Nome da Fantasia");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(227, 171, 139, 14);
		contentPane.add(lblNewLabel_5);

		txtFantasia = new JTextField();
		txtFantasia.setBounds(332, 168, 139, 20);
		contentPane.add(txtFantasia);
		txtFantasia.setColumns(10);

		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisarID();
			}
		});
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/pesquisar.png")));
		btnBuscar.setBounds(88, 115, 52, 51);
		contentPane.add(btnBuscar);

		JLabel lblNewLabel_6 = new JLabel("Site");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(6, 325, 46, 14);
		contentPane.add(lblNewLabel_6);

		txtSite = new JTextField();
		txtSite.setBounds(32, 322, 149, 20);
		contentPane.add(txtSite);
		txtSite.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Fone");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(240, 209, 46, 14);
		contentPane.add(lblNewLabel_7);

		txtFone = new JTextField();
		txtFone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321()-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}

		});
		txtFone.setBounds(271, 206, 115, 20);
		contentPane.add(txtFone);
		txtFone.setColumns(10);

		JLabel WhatsApp = new JLabel("WhatsApp");
		WhatsApp.setFont(new Font("Tahoma", Font.BOLD, 11));
		WhatsApp.setBounds(396, 209, 75, 14);
		contentPane.add(WhatsApp);

		txtZap = new JTextField();
		txtZap.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321()-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}

		});
		txtZap.setBounds(457, 206, 101, 20);
		contentPane.add(txtZap);
		txtZap.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Endere\u00E7o");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(6, 258, 71, 14);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Nome do Contato");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(6, 209, 101, 14);
		contentPane.add(lblNewLabel_9);

		txtContato = new JTextField();
		txtContato.setBounds(115, 206, 115, 20);
		contentPane.add(txtContato);
		txtContato.setColumns(10);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(65, 255, 165, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNumero = new JLabel("N\u00FAmero");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumero.setBounds(253, 258, 46, 14);
		contentPane.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNumero.setBounds(312, 255, 46, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Complemento");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_10.setBounds(368, 258, 81, 14);
		contentPane.add(lblNewLabel_10);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(459, 255, 123, 20);
		contentPane.add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("CEP");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11.setBounds(213, 325, 31, 14);
		contentPane.add(lblNewLabel_11);

		txtCEP = new JTextField();
		txtCEP.addKeyListener(new KeyAdapter() {
			@Override

			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCEP.setBounds(240, 322, 115, 20);
		contentPane.add(txtCEP);
		txtCEP.setColumns(10);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCEP.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtCEP.requestFocus();

				} else {
					buscarCEP();

				}
			}
		});
		btnNewButton_1.setBounds(365, 321, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_12 = new JLabel("Bairro");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_12.setBounds(6, 291, 46, 14);
		contentPane.add(lblNewLabel_12);

		txtBairro = new JTextField();
		txtBairro.setBounds(65, 288, 190, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Cidade");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_13.setBounds(273, 291, 46, 14);
		contentPane.add(lblNewLabel_13);

		txtCidade = new JTextField();
		txtCidade.setBounds(329, 291, 120, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("UF");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_14.setBounds(468, 325, 46, 14);
		contentPane.add(lblNewLabel_14);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(496, 321, 47, 22);
		contentPane.add(cboUf);

		JLabel lblNewLabel_15 = new JLabel("Observa\u00E7\u00F5es");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_15.setBounds(6, 407, 88, 14);
		contentPane.add(lblNewLabel_15);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarFornecedor();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/update.png")));
		btnUpdate.setBounds(390, 522, 64, 64);
		contentPane.add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFornecedor();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/delete.png")));
		btnDelete.setBounds(457, 522, 64, 64);
		contentPane.add(btnDelete);

		btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFornecedor();
			}
		});
		btnCreate.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/create.png")));
		btnCreate.setBounds(322, 522, 64, 64);
		contentPane.add(btnCreate);

		// uso da biblioteca para fazer validações dos campos
		RestrictedTextField validar = new RestrictedTextField(txtID);
		validar.setOnlyNums(true);

		RestrictedTextField validar1 = new RestrictedTextField(txtPesquisar);
		validar1.setLimit(50);

		RestrictedTextField validar2 = new RestrictedTextField(txtCnpj);
		validar2.setLimit(20);
		validar2.setOnlyNums(true);

		RestrictedTextField validar3 = new RestrictedTextField(txtIe);
		validar3.setLimit(20);
		validar3.setOnlyNums(true);

		RestrictedTextField validar4 = new RestrictedTextField(txtRazaoSocial);
		validar4.setOnlyText(true);
		validar4.setLimit(50);
		validar4.setAcceptSpace(true);

		RestrictedTextField validar5 = new RestrictedTextField(txtFantasia);
		validar5.setLimit(50);
		validar5.setOnlyText(true);
		validar5.setAcceptSpace(true);

		RestrictedTextField validar6 = new RestrictedTextField(txtContato);
		validar6.setLimit(30);
		validar6.setOnlyText(true);
		validar6.setAcceptSpace(true);

		RestrictedTextField validar7 = new RestrictedTextField(txtFone);
		validar7.setLimit(15);

		RestrictedTextField validar8 = new RestrictedTextField(txtZap);
		validar8.setLimit(15);

		RestrictedTextField validar9 = new RestrictedTextField(txtSite);
		validar9.setLimit(50);

		RestrictedTextField validar10 = new RestrictedTextField(txtEndereco);
		validar10.setLimit(50);
		validar10.setOnlyText(true);
		validar10.setAcceptSpace(true);

		RestrictedTextField validar11 = new RestrictedTextField(txtComplemento);
		validar11.setLimit(20);
		validar11.setOnlyText(true);
		validar11.setAcceptSpace(true);

		RestrictedTextField validar12 = new RestrictedTextField(txtNumero);
		validar12.setLimit(20);
		validar12.setOnlyNums(true);

		RestrictedTextField validar13 = new RestrictedTextField(txtBairro);
		validar13.setLimit(50);
		validar13.setOnlyText(true);
		validar13.setAcceptSpace(true);

		RestrictedTextField validar14 = new RestrictedTextField(txtCidade);
		validar14.setLimit(50);
		validar14.setOnlyText(true);
		validar14.setAcceptSpace(true);

		RestrictedTextField validar15 = new RestrictedTextField(txtCEP);
		validar15.setLimit(10);

		textPane = new JTextPane();
		textPane.setBounds(62, 432, 409, 79);
		contentPane.add(textPane);

		lblNewLabel_16 = new JLabel("Email");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_16.setBounds(6, 359, 46, 14);
		contentPane.add(lblNewLabel_16);

		txtEmail = new JTextField();
		txtEmail.setBounds(42, 356, 149, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 32, 542, 79);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		validar15.setLimit(50);

	}// fim do construtor

	DAO dao = new DAO();
	private JLabel lblNewLabel_16;
	private JTextField txtEmail;
	private JTable table;

	private void Status() {
		// System.out.println("teste - Janela Ativada");
		// uso da classe conection (JBBC) para estabelecer a conexao
		try {
			Connection con = dao.conectar();
			if (con == null) {
				System.out.println("Erro de conexão");
				// lblStatus.setIcon(new
				// ImageIcon(Usuarios.class.getResource("/img/dboff.png")));
			} else {
				System.out.println("Banco conectado!");
				// lblStatus.setIcon(new
				// ImageIcon(Usuarios.class.getResource("/img/dbon.png")));
			}
			// Nunca esequcer de encerrar a conexão
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}// fim do status

	private void PesquisarID() {
		if (txtID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID");
			txtID.requestFocus();
		} else {
			String read = "select * from fornecedores where idFor = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtID.getText());
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					// txtFornecedores.setText(rs.getString(3));
					txtRazaoSocial.setText(rs.getString(2));
					txtFantasia.setText(rs.getString(3));
					txtCnpj.setText(rs.getString(4));
					txtIe.setText(rs.getString(5));
					txtCEP.setText(rs.getString(6));
					txtEndereco.setText(rs.getString(7));
					txtNumero.setText(rs.getString(8));
					txtComplemento.setText(rs.getString(9));
					txtBairro.setText(rs.getString(10));
					txtCidade.setText(rs.getString(11));
					cboUf.setSelectedItem(rs.getString(12));
					// cboUf.setSelectedItem(12);
					txtContato.setText(rs.getString(13));
					txtFone.setText(rs.getString(14));
					txtZap.setText(rs.getString(15));
					txtEmail.setText(rs.getString(16));
					txtSite.setText(rs.getString(17));
					textPane.setText(rs.getNString(18));
					// habilitar os botoes
					btnDelete.setEnabled(true);
					btnCreate.setEnabled(false);
					btnUpdate.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Id Inexistente");
					limpar();
					btnCreate.setEnabled(true);

				}

				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void excluirFornecedor() {
		// System.out.println("teste de botao excluir);
		// validação
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desde fornecedor ?",
				"Excluir Forcenedor", JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from fornecedores where idFor = ?";
			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				btnCreate.setEnabled(true);
				// execuatr o comandosql e confirmar a exclusão
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso");
				} else {
					JOptionPane.showMessageDialog(null, "Erro na exclusão do forcenedor");
				}
				// encerrar a conexão
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				System.out.println(e1);
				JOptionPane.showMessageDialog(null,
						"Fornecedor não excluído, pois existem produtos vinculados a esse fornecedor");

			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void alterarFornecedor() {
		if (txtRazaoSocial.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Razão Social");
			txtRazaoSocial.requestFocus();

		} else if (txtFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Fantasia!");
			txtFantasia.requestFocus();
		} else if (txtCnpj.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ");
			txtFantasia.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Endereço!");
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Numero!");
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Bairro!");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Cidade!");
			txtCidade.requestFocus();
		} else if (txtContato.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Contato!");
			txtContato.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha A  UF");
			cboUf.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone!");
			txtFone.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Email");
			txtEmail.requestFocus();
		} else {

			// logica principal
			String update = "update fornecedores set razaoSocial = ?, fantasia = ?, cnpj = ?, ie = ?, cep = ?, endereco = ?,  numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, nomeContato = ?, fone = ?, zap = ?, email = ?, site = ? ,obs = ? where idFor = ?";

			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a querry (instrução sql)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(18, txtID.getText());
				pst.setString(1, txtRazaoSocial.getText());
				pst.setString(2, txtFantasia.getText());
				pst.setString(4, txtIe.getText());
				pst.setString(3, txtCnpj.getText());
				pst.setString(5, txtCEP.getText());
				pst.setString(6, txtEndereco.getText());
				pst.setString(7, txtNumero.getText());
				pst.setString(8, txtComplemento.getText());
				pst.setString(9, txtBairro.getText());
				pst.setString(10, txtCidade.getText());
				pst.setNString(11, cboUf.getSelectedItem().toString());
				pst.setString(12, txtContato.getText());
				pst.setString(13, txtFone.getText());
				pst.setString(14, txtZap.getText());
				pst.setString(15, txtEmail.getText());
				pst.setString(16, txtSite.getText());
				pst.setString(17, textPane.getText());
				btnCreate.setEnabled(true);
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do fornecedor atualizados com sucesso");
					limpar();
				}
				// fechar a conexão com o o banco
				con.close();

			}

			catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				System.out.println(e1);
				JOptionPane.showMessageDialog(null, "Fornecedor não atualizado - CNPJ ou IE já em uso");
				txtCnpj.setText(null);
				txtCnpj.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}

	private void adicionarFornecedor() {
		if (txtRazaoSocial.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Razão Social");
			txtRazaoSocial.requestFocus();

		} else if (txtFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Fantasia!");
			txtFantasia.requestFocus();
		} else if (txtCnpj.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ");
			txtFantasia.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Endereço!");
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Numero!");
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Bairro!");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Cidade!");
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha A  UF");
			cboUf.requestFocus();
		} else if (txtContato.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Contato!");
			txtContato.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone!");
			txtFone.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Email");
			txtEmail.requestFocus();
		} else {

			String create = "insert into fornecedores (razaoSocial,fantasia,cnpj,ie,cep,endereco,numero,complemento,bairro,cidade,uf,nomecontato,fone,zap,email,site,obs) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {

				// abrir a conexão
				Connection con = dao.conectar();
				// Preparar a querry(substituição)
				PreparedStatement pst = con.prepareStatement(create);

				pst.setString(1, txtRazaoSocial.getText());
				pst.setString(2, txtFantasia.getText());
				pst.setString(4, txtIe.getText());
				pst.setString(3, txtCnpj.getText());
				pst.setString(5, txtCEP.getText());
				pst.setString(6, txtEndereco.getText());
				pst.setString(7, txtNumero.getText());
				pst.setString(8, txtComplemento.getText());
				pst.setString(9, txtBairro.getText());
				pst.setString(10, txtCidade.getText());
				pst.setString(11, cboUf.getSelectedItem().toString());
				pst.setString(12, txtContato.getText());
				pst.setString(13, txtFone.getText());
				pst.setString(14, txtZap.getText());
				pst.setString(15, txtEmail.getText());
				pst.setString(16, txtSite.getText());
				pst.setString(17, textPane.getText());

				// executar a query e confimar inserção no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Fornecedor adicionado!");

				} else {
					JOptionPane.showMessageDialog(null, "Erro ao adicionar um Fornecedor");
				}
				limpar();

				// encerrar a conexao
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				System.out.println(e1);
				JOptionPane.showMessageDialog(null, "Fornecedor não adicionado - CNPJ ou IE já em uso");
				txtCnpj.setText(null);
				txtCnpj.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}

		}
	}

	private void buscarCEP() {
		String logradouro = "";
		String tipoLogradouro = "";
		String cep = txtCEP.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_Lograudouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				txtEndereco.setText(tipoLogradouro + " " + logradouro);
			}

		} catch (Exception e) {

		}
	}

	/**
	 * M responsavel pela pesquisa avnacada do fornecedor usando filtro
	 */
	private void buscarCliente() {
		String read2 = "select idFor as Id, fantasia as Fornecedor, fone, nomeContato from fornecedores where fantasia like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtPesquisar.getText() + "%");
			ResultSet rs = pst.executeQuery();
			// uso da biblioteca rs2xml para popular a tabela
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void limpar() {
		txtPesquisar.setText(null);
		txtID.setText(null);
		txtCnpj.setText(null);
		txtIe.setText(null);
		txtRazaoSocial.setText(null);
		txtFantasia.setText(null);
		txtContato.setText(null);
		txtFone.setText(null);
		txtZap.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtEmail.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		txtSite.setText(null);
		txtCEP.setText(null);
		cboUf.setSelectedItem(null);
		textPane.setText(null);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);

		((DefaultTableModel) table.getModel()).setRowCount(0);

	}
}// fim do codigo