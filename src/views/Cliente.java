package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class Cliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Status();
			}
		});
		setTitle("Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cliente.class.getResource("/img/clientes.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 542, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(11, 138, 46, 14);
		contentPane.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setBounds(37, 139, 40, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Cliente:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscarCliente();
			}
		});
		txtNome.setBounds(55, 8, 133, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Email:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(202, 191, 64, 14);
		contentPane.add(lblNewLabel_1_1);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
	
		});

		txtEmail.setBounds(261, 189, 189, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Endere\u00E7o");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(4, 220, 64, 14);
		contentPane.add(lblNewLabel_3);

		txtEndereco = new JTextField();
		txtEndereco.setText("");
		txtEndereco.setBounds(77, 221, 277, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Numero");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(7, 246, 46, 14);
		contentPane.add(lblNewLabel_4);

		txtNumero = new JTextField();
		txtNumero.setBounds(57, 244, 82, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Complemento:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(144, 246, 116, 14);
		contentPane.add(lblNewLabel_6);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(231, 244, 189, 20);
		contentPane.add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Cidade:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(6, 276, 46, 14);
		contentPane.add(lblNewLabel_7);

		txtCidade = new JTextField();
		txtCidade.setBounds(52, 274, 116, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Bairro:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(176, 275, 46, 14);
		contentPane.add(lblNewLabel_8);

		txtBairro = new JTextField();
		txtBairro.setBounds(218, 272, 158, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Telefone:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_10.setBounds(4, 194, 70, 14);
		contentPane.add(lblNewLabel_10);

		txtZap = new JTextField();
		txtZap.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtZap.setBounds(74, 189, 116, 20);
		contentPane.add(txtZap);
		txtZap.setColumns(10);

		RestrictedTextField validar = new RestrictedTextField(txtId);
		validar.setOnlyNums(true);

		RestrictedTextField validar1 = new RestrictedTextField(txtEndereco);
		validar1.setOnlyText(true);
		validar1.setAcceptSpace(true);
		validar1.setLimit(80);

		RestrictedTextField validar2 = new RestrictedTextField(txtNome);
		validar2.setOnlyText(true);
		validar2.setAcceptSpace(true);

		RestrictedTextField validar4 = new RestrictedTextField(txtComplemento);
		validar4.setOnlyText(false);
		validar4.setAcceptSpace(true);

		RestrictedTextField validar5 = new RestrictedTextField(txtCidade);
		validar5.setOnlyText(true);
		validar5.setAcceptSpace(true);

		RestrictedTextField validar6 = new RestrictedTextField(txtBairro);
		validar6.setOnlyText(true);
		validar6.setAcceptSpace(true);

		RestrictedTextField validar7 = new RestrictedTextField(txtNumero);
		validar7.setOnlyNums(true);

		RestrictedTextField validar8 = new RestrictedTextField(txtNumero);
		validar8.setLimit(25);
		
		RestrictedTextField validar9 = new RestrictedTextField(txtEmail);
		validar9.setLimit(50);
		

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarCliente();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Cliente.class.getResource("/img/pesquisar.png")));
		btnNewButton.setBounds(93, 130, 76, 49);
		contentPane.add(btnNewButton);

		btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCliente();
			}
		});
		btnCreate.setIcon(new ImageIcon(Cliente.class.getResource("/img/create.png")));
		btnCreate.setBounds(312, 315, 64, 64);
		contentPane.add(btnCreate);

		btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setIcon(new ImageIcon(Cliente.class.getResource("/img/delete.png")));
		btnDelete.setBounds(386, 315, 64, 64);
		contentPane.add(btnDelete);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateCliente();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setIcon(new ImageIcon(Cliente.class.getResource("/img/update.png")));
		btnUpdate.setBounds(452, 315, 64, 64);
		contentPane.add(btnUpdate);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 478, 79);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"idFor", "nome", "Email", "cidade"
			}
		)

		);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_2 = new JLabel("CEP:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(4, 307, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtCep = new JTextField();
		txtCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCep.setBounds(37, 304, 131, 20);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		btnNewButton_1 = new JButton("Buscar CEP");
		btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCEP();
			}
		});
		btnNewButton_1.setBounds(186, 303, 120, 23);
		contentPane.add(btnNewButton_1);

	}// fim do cosntrutor

	DAO dao = new DAO();
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtZap;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtCidade;
	private JTextField txtBairro;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnCreate;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtCep;
	private JButton btnNewButton_1;

	private void Status() {

		try {
			Connection con = dao.conectar();
			if (con == null) {
				System.out.println("Erro de conexão");

			} else {
				System.out.println("Banco conectado!");

			}
			// Nunca esequcer de encerrar a conexão
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void pesquisarCliente() {
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID");
			txtId.requestFocus();
		} else {
			String read = "select * from cliente where idFor = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtId.getText());
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					// txtFornecedores.setText(rs.getString(3));
					txtNome.setText(rs.getString(2));
					txtEmail.setText(rs.getString(3));
					txtNumero.setText(rs.getString(4));
					txtEndereco.setText(rs.getString(5));
					txtComplemento.setText(rs.getString(6));
					txtBairro.setText(rs.getString(7));
					txtCidade.setText(rs.getString(8));
					txtZap.setText(rs.getString(9));
					txtCep.setText(rs.getString(10));
					// habilitar os botoes
					btnDelete.setEnabled(true);
					btnCreate.setEnabled(false);
					btnUpdate.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Id Inexistente");
					limpar();
					// btnCreate.setEnabled(true);

				}

				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void UpdateCliente() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do cliente");
			txtNome.requestFocus();

		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Bairro");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Cidade");
			txtCidade.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Email");
			txtEmail.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Numero do Local");
			txtEmail.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Endereço");
			txtEndereco.requestFocus();
		} else if (txtZap.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o numero de WhatsApp");
			txtZap.requestFocus();
		} else {

			String update = "update cliente set  nome = ?, email = ?, numero = ?, Endereco = ?, complemento =?, bairro = ?, cidade = ?, zap = ?, cep = ? where idFor = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(10, txtId.getText());
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtEmail.getText());
				pst.setString(3, txtNumero.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCidade.getText());
				pst.setString(8, txtZap.getText());
				pst.setString(9, txtCep.getText());
				// iniciar a query
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do cliente atualizados com sucesso");
					limpar();
				}
				con.close();

			
			} catch (Exception e1) {
				System.out.println(e1);
			}
		}
	}

	private void excluirCliente() {
		// System.out.println("teste de botao excluir);
		// validação
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desde Cliente?", "Excluir Cliente",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from cliente where idFor = ?";
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
					JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
				} else {
					JOptionPane.showMessageDialog(null, "Erro na exclusão do Cliente");
				}
				// encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void addCliente() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do cliente");
			txtNome.requestFocus();
		}

		else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Bairro");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Cidade");
			txtCidade.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Email");
			txtEmail.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Numero do Local");
			txtEmail.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Endereço");
			txtEndereco.requestFocus();
		} else if (txtZap.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o numero de WhatsApp");
			txtZap.requestFocus();
		} else {

			// System.out.println("teste adicionar ");
			String create = "insert into cliente(nome, email, numero, Endereco, complemento, bairro, cidade, zap, cep) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// Preparar a querry(substituição)
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtEmail.getText());
				pst.setString(3, txtNumero.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCidade.getText());
				pst.setString(8, txtZap.getText());
				pst.setString(9, txtCep.getText());

				// executar a query e confimar inserção no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado!");

				} else {
					JOptionPane.showMessageDialog(null, "Erro ao adicionar um Cliente");
				}
				limpar();

				// encerrar a conexao
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				System.out.println(e1);
				JOptionPane.showMessageDialog(null, "CPF já em uso");
				txtEmail.setText(null);
				txtEmail.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void buscarCliente() {
		String read = "select iDFor as idFor, nome , email, cidade from cliente where nome like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtNome.getText() + "%");
			ResultSet rs = pst.executeQuery();
			// uso da biblioteca rs2xml para popular a tabela
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void buscarCEP() {
		String logradouro = "";
		String tipoLogradouro = "";
		String cep = txtCep.getText();
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

	private void limpar() {
		txtId.setText(null);
		txtNome.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		txtEmail.setText(null);
		txtNumero.setText(null);
		txtEndereco.setText(null);
		txtZap.setText(null);
		txtCep.setText(null);
		btnCreate.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);

	}
}// fim do codigo
