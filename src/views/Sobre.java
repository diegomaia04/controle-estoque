package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import java.awt.SystemColor;

public class Sobre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre frame = new Sobre();
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
	public Sobre() {
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/about.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 557, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Projeto de Controle de Estoque");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 244, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Sobre.class.getResource("/img/MIT.png")));
		lblNewLabel_1.setBounds(0, 108, 300, 185);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("AUTOR :");
		lblNewLabel_3.setBounds(264, 237, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Diego Maia Fernandes");
		lblNewLabel_4.setBounds(320, 237, 169, 14);
		contentPane.add(lblNewLabel_4);
		
		JTextPane txtpnProjetoDeEstoque = new JTextPane();
		txtpnProjetoDeEstoque.setBackground(SystemColor.menu);
		txtpnProjetoDeEstoque.setText("Projeto de estoque baseado em estoques reais, a partir do eclipse, tambem \u00E9 usado o banco de dados do MySQL");
		txtpnProjetoDeEstoque.setBounds(10, 48, 280, 49);
		contentPane.add(txtpnProjetoDeEstoque);
		
		JLabel lblNewLabel_2 = new JLabel("Vers\u00E3o: 1.0");
		lblNewLabel_2.setBounds(264, 261, 158, 14);
		contentPane.add(lblNewLabel_2);
		
		this.dispose();
	}
	
	
}
