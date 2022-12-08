package views;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Cursor;

public class Relatorios extends JDialog {

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
					Relatorios dialog = new Relatorios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Relatorios() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Relat\u00F3rios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorios.class.getResource("/img/report.png")));
		setBounds(100, 100, 468, 278);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.setBorder(null);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setToolTipText("Reposi\u00E7\u00E3o");
		btnNewButton.setIcon(new ImageIcon(
				Relatorios.class.getResource("/img/331515_bag_cleaning_janitor_replacement_icon (1).png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reposicaoEstoque();
			}
		});
		btnNewButton.setBounds(160, 124, 115, 89);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setToolTipText("Produtos Vencidos");
		btnNewButton_1.setIcon(
				new ImageIcon(Relatorios.class.getResource("/img/3556076_alarm_clock_delete_remove_ui_icon.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutosV();
			}
		});
		btnNewButton_1.setBounds(303, 143, 134, 63);
		getContentPane().add(btnNewButton_1);
		

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBackground(SystemColor.menu);
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setToolTipText("Total de Produtos");
		btnNewButton_2.setIcon(new ImageIcon(
				Relatorios.class.getResource("/img/3213289_business_marketing_product_strategy_icon.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtoT();
			}
		});
		btnNewButton_2.setBounds(10, 124, 115, 82);
		getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setBackground(SystemColor.menu);
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setToolTipText("Produtos + Fornecedores ");
		btnNewButton_3.setIcon(new ImageIcon(Relatorios.class.getResource("/img/43573_clients_icon.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioFornecedor();
			}
		});
		btnNewButton_3.setBounds(23, 24, 87, 63);
		getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setBackground(SystemColor.menu);
		btnNewButton_4.setToolTipText("Produto e seu Fornecedor");
		btnNewButton_4.setIcon(new ImageIcon(
				Relatorios.class.getResource("/img/45019_cap_male_man_man with cap_provider_icon (1).png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtoF();
			}
		});
		btnNewButton_4.setBounds(337, 24, 87, 74);
		getContentPane().add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_5.setBackground(SystemColor.menu);
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.setBorder(null);
		btnNewButton_5.setToolTipText("Pre\u00E7o de vendas");
		btnNewButton_5.setIcon(
				new ImageIcon(Relatorios.class.getResource("/img/3209420_buy_coin_cost_money_price_icon.png")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Xprodutos();
			}
		});
		btnNewButton_5.setBounds(185, 24, 78, 63);
		getContentPane().add(btnNewButton_5);

	}// fim do cosntrutor

	DAO dao = new DAO();

	// método responsável pela impressão do relatório de reposição
	private void reposicaoEstoque() {
		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("reposicao.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// documetn.add(adicionar um paragrafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Reposição de estoque"));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(5); // (5) colunas
			// cabeçalho da tabela
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Validade"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Estoque"));
			PdfPCell col5 = new PdfPCell(new Paragraph("Estoque mínimo"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			tabela.addCell(col5);

			// Acessar o banco de dados
			String relReposicao = "select codigo,produto,date_format(dataval,'%d/%m/%Y'), estoque, estoquemin from produtos where estoque < estoquemin";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relReposicao);
				ResultSet rs = pst.executeQuery();
				// enquanto houver dados na tabela do banco de dados(obter o valor)
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não, serve ´para fchar o doc
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("reposicao.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void relatorioFornecedor() {
		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 40f, 30f, 30f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("Produtos e Fornecedores.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// documetn.add(adicionar um paragrafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Produtos e Fornecedores"));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(9); // (5) colunas

			PdfPCell col2 = new PdfPCell(new Paragraph("Barcode"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col5 = new PdfPCell(new Paragraph("Fabricante"));
			PdfPCell col7 = new PdfPCell(new Paragraph("Data de Validade"));
			PdfPCell col8 = new PdfPCell(new Paragraph("Estoque"));
			PdfPCell col12 = new PdfPCell(new Paragraph("Custo"));
			PdfPCell col15 = new PdfPCell(new Paragraph("Codigo"));
			PdfPCell col16 = new PdfPCell(new Paragraph("Razão Social"));
			PdfPCell col25 = new PdfPCell(new Paragraph("Cidade"));

			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col5);
			tabela.addCell(col7);
			tabela.addCell(col8);
			tabela.addCell(col12);
			tabela.addCell(col15);
			tabela.addCell(col16);	
			tabela.addCell(col25);

			// Acessar o banco de dados
			String proFornecedores = "select * from produtos inner join fornecedores\r\n"
					+ "on produtos.idFor = fornecedores.idFor;";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(proFornecedores);
				ResultSet rs = pst.executeQuery();
				// enquanto houver dados na tabela do banco de dados(obter o valor)
				while (rs.next()) {

					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(5));
					tabela.addCell(rs.getString(7));
					tabela.addCell(rs.getString(8));

				
					tabela.addCell(rs.getString(12));
					tabela.addCell(rs.getString(15));
					tabela.addCell(rs.getString(16));
				
					tabela.addCell(rs.getString(25));

				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não, serve ´para fchar o doc
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("Produtos e Fornecedores.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void produtoF() {
		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("Produto.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// documetn.add(adicionar um paragrafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Produto e seu fornecedor"));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(3); // (5) colunas
			// cabeçalho da tabela
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));

			PdfPCell col4 = new PdfPCell(new Paragraph("Fornecedor"));

			tabela.addCell(col1);
			tabela.addCell(col2);

			tabela.addCell(col4);

			// Acessar o banco de dados
			String produtoF = "select codigo, produtos.produto, \r\n" + " fornecedores.razaoSocial\r\n"
					+ "from produtos inner join fornecedores on produtos.idFor = fornecedores.idFor;";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(produtoF);
				ResultSet rs = pst.executeQuery();
				// enquanto houver dados na tabela do banco de dados(obter o valor)
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));

					tabela.addCell(rs.getString(3));

				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não, serve ´para fchar o doc
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("Produto.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void produtoT() {
		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("Total.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// documetn.add(adicionar um paragrafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Total de produtos"));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(1); // (5) colunas
			// cabeçalho da tabela
			PdfPCell col1 = new PdfPCell(new Paragraph("Total de produtos"));

			tabela.addCell(col1);

			// Acessar o banco de dados
			String Total = "select sum(estoque * custo) from produtos;";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(Total);
				ResultSet rs = pst.executeQuery();
				// enquanto houver dados na tabela do banco de dados(obter o valor)
				while (rs.next()) {
					tabela.addCell(rs.getString(1));

				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não, serve ´para fchar o doc
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("Total.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void Xprodutos() {
		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("Venda.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// documetn.add(adicionar um paragrafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Preço de Vendas"));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4); // (5) colunas
			// cabeçalho da tabela
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Custo"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Venda"));

			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);

			// Acessar o banco de dados
			String relReposicao = "select  codigo, produto,custo,\r\n" + "(custo + (custo * lucro)/100) from produtos;";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relReposicao);
				ResultSet rs = pst.executeQuery();
				// enquanto houver dados na tabela do banco de dados(obter o valor)
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));

				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não, serve ´para fchar o doc
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("Venda.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void ProdutosV() {
		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("Vencimento.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// documetn.add(adicionar um paragrafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Dias de vencimento dos produtos "));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(5); // (5) colunas
			// cabeçalho da tabela
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Localização"));
			PdfPCell col4 = new PdfPCell(new Paragraph("data de Validade"));
			PdfPCell col5 = new PdfPCell(new Paragraph("Dias vencidos "));

			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			tabela.addCell(col5);

			// Acessar o banco de dados
			String relReposicao = "select codigo, produto, localizacao,date_format(dataval,'%d/%m/%Y'),datediff(dataval, curdate()) from produtos where  datediff(dataval, curdate()) < 0";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relReposicao);
				ResultSet rs = pst.executeQuery();
				// enquanto houver dados na tabela do banco de dados(obter o valor)
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));

				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não, serve ´para fchar o doc
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("Vencimento.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}// fim do codigo
