import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.swing.JEditorPane;

public class Projekat {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLayeredPane layeredPane;
	private JTextField txtPoljePretraga;
	private JTable table;
	private JPanel spisakPutnikaPanel;
	private JPanel aerodromPanel;
	private JTable table_1;
	private JTable table_2;
	private JTextField polazakTxt;
	private JTextField dolazakTxt;
	private JTextField brojMestaTxt;
	private JTextField vremePolaskaTxt;
	private JTextField vremeDolaskaTxt;
	private JTextField cenaTxt;
	private JPanel dodajLetPanel;
	private JTable table_3;
	private JPanel prikaziKartePanel;
	private JTable table_4;
	private JTextField textField_1;
	private JLabel kupovinaLabel;
	private JPanel kupiKartuPanel;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Projekat window = new Projekat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void promeniPanel(JPanel panel) {
		
		layeredPane.removeAll();
		layeredPane.add(panel);
		panel.show(true);
		layeredPane.repaint();
		layeredPane.revalidate();
		
	}

	public Connection conn() {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/aerodrom";
			Class.forName(driver);
			return DriverManager.getConnection(url, "root", "");
		} catch(Exception ex) {
			System.out.println("Greska prilikom povezivanja");
		}
		return null; 
	}
	

	

	/**
	 * Create the application.
	 */
	public Projekat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(SystemColor.desktop);
		frame.setBounds(100, 100, 580, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 577, 370);
		frame.getContentPane().add(layeredPane);
		
		JPanel pristupSistemuPanel = new JPanel();
		pristupSistemuPanel.setBackground(SystemColor.desktop);
		layeredPane.setLayer(pristupSistemuPanel, 1);
		pristupSistemuPanel.setBounds(0, 0, 577, 370);
		layeredPane.add(pristupSistemuPanel);
		pristupSistemuPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(SystemColor.control);
		textField.setBounds(338, 130, 184, 28);
		pristupSistemuPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Korisničko \r\nime: ");
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(195, 112, 140, 58);
		pristupSistemuPanel.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(SystemColor.control);
		passwordField.setBounds(338, 175, 184, 28);
		pristupSistemuPanel.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("Lozinka:");
		lblNewLabel_1.setForeground(SystemColor.window);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(205, 177, 121, 18);
		pristupSistemuPanel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Prijavite se");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String korisnicko_ime = textField.getText();
				String lozinka= passwordField.getText();
				if(korisnicko_ime.equals("") || lozinka.equals("")) {
					JOptionPane.showMessageDialog(pristupSistemuPanel, "Morate uneti korisničko ime i lozinku!");
				}
				else if(!(korisnicko_ime.trim().equals("") || lozinka.trim().equals(""))) {
					try {
						Connection con = conn();
						Statement stmt = con.createStatement();
						String sql="SELECT * FROM korisnik WHERE korisnicko_ime='"+korisnicko_ime+"' AND lozinka='"+lozinka+"'";
						ResultSet rs=stmt.executeQuery(sql);
						if(rs.next()) {
							stmt.close();
							rs.close();
							con.close();
							promeniPanel(aerodromPanel);
						}
						else{
							JOptionPane.showMessageDialog(pristupSistemuPanel,"Pogrešno korisničko ime ili lozinka");
							stmt.close();
							rs.close();
							con.close();
						}
					} catch (Exception ex) {
						System.out.print(ex);
					}
				}
			}
		});
		btnNewButton.setBounds(205, 244, 169, 31);
		pristupSistemuPanel.add(btnNewButton);
		
		JLabel lblNewLabel_14 = new JLabel("Online aerodrom");
		lblNewLabel_14.setForeground(SystemColor.window);
		lblNewLabel_14.setBounds(195, 37, 350, 58);
		pristupSistemuPanel.add(lblNewLabel_14);
		lblNewLabel_14.setBackground(SystemColor.activeCaption);
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setFont(new Font("Dialog", Font.BOLD, 40));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, -45, 175, 415);
		pristupSistemuPanel.add(panel);
		
		JLabel lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setIcon(new ImageIcon(Projekat.class.getResource("/images/slikaaa.jpg")));
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel.add(lblNewLabel_15);
		
		JButton btnOtkai = new JButton("Otkaži");
		btnOtkai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		btnOtkai.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnOtkai.setBackground(SystemColor.activeCaption);
		btnOtkai.setBounds(384, 244, 169, 31);
		pristupSistemuPanel.add(btnOtkai);
		
		JPanel spisakSvihLetovaPanel = new JPanel();
		spisakSvihLetovaPanel.setLayout(null);
		spisakSvihLetovaPanel.setBackground(Color.BLACK);
		spisakSvihLetovaPanel.setBounds(0, 0, 577, 370);
		layeredPane.add(spisakSvihLetovaPanel);
		
		aerodromPanel = new JPanel();
		aerodromPanel.setBackground(SystemColor.desktop);
		layeredPane.setLayer(aerodromPanel, 0);
		aerodromPanel.setBounds(0, 0, 577, 370);
		layeredPane.add(aerodromPanel);
		aerodromPanel.setLayout(null);
		aerodromPanel.show(false);
		
		JButton pretraga = new JButton("Spisak putnika");
		pretraga.setForeground(SystemColor.window);
		pretraga.setBackground(SystemColor.desktop);
		pretraga.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pretraga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				promeniPanel(spisakPutnikaPanel);
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Projekat.class.getResource("/images/pngtree-avatar-icon-profile-icon-member-login-vector-isolated-png-image_1978396.jpg")));
		lblNewLabel_2.setBounds(-37, 0, 308, 203);
		aerodromPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_16 = new JLabel("Dobrodošli");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_16.setBounds(83, 194, 182, 55);
		aerodromPanel.add(lblNewLabel_16);
		
		JButton odjava = new JButton("Odjavi se");
		odjava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        System.exit(0);
			}
		});
		odjava.setForeground(SystemColor.desktop);
		odjava.setFont(new Font("Times New Roman", Font.BOLD, 20));
		odjava.setBackground(SystemColor.window);
		odjava.setBounds(0, 311, 274, 55);
		aerodromPanel.add(odjava);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.window);
		panel_1.setBounds(0, 0, 274, 366);
		aerodromPanel.add(panel_1);
		pretraga.setBounds(275, 0, 308, 46);
		aerodromPanel.add(pretraga);
		
		JButton kupovina_karte = new JButton("Kupi kartu");
		kupovina_karte.setForeground(SystemColor.window);
		kupovina_karte.setBackground(SystemColor.desktop);
		kupovina_karte.setFont(new Font("Times New Roman", Font.BOLD, 20));
		kupovina_karte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				promeniPanel(kupiKartuPanel);
			}
		});
		kupovina_karte.setBounds(275, 80, 308, 46);
		aerodromPanel.add(kupovina_karte);
		
		JButton kupljene_karte = new JButton("Kupljene karte");
		kupljene_karte.setForeground(SystemColor.window);
		kupljene_karte.setBackground(SystemColor.desktop);
		kupljene_karte.setFont(new Font("Times New Roman", Font.BOLD, 20));
		kupljene_karte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				promeniPanel(prikaziKartePanel);
			}
		});
		kupljene_karte.setBounds(275, 160, 308, 46);
		aerodromPanel.add(kupljene_karte);
		
		JButton dodavanje_leta = new JButton("Dodaj let");
		dodavanje_leta.setForeground(SystemColor.window);
		dodavanje_leta.setBackground(SystemColor.desktop);
		dodavanje_leta.setFont(new Font("Times New Roman", Font.BOLD, 20));
		dodavanje_leta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				promeniPanel(dodajLetPanel);
			}
		});
		dodavanje_leta.setBounds(275, 240, 308, 46);
		aerodromPanel.add(dodavanje_leta);
		
		JButton letovi = new JButton("Spisak letova");
		letovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promeniPanel(spisakSvihLetovaPanel);
			}
		});
		letovi.setForeground(Color.WHITE);
		letovi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		letovi.setBackground(Color.BLACK);
		letovi.setBounds(275, 320, 308, 46);
		aerodromPanel.add(letovi);
		
		spisakPutnikaPanel = new JPanel();
		spisakPutnikaPanel.setForeground(SystemColor.window);
		spisakPutnikaPanel.setBackground(SystemColor.desktop);
		layeredPane.setLayer(spisakPutnikaPanel, 0);
		spisakPutnikaPanel.setBounds(0, 0, 577, 370);
		layeredPane.add(spisakPutnikaPanel);
		spisakPutnikaPanel.setLayout(null);
		spisakPutnikaPanel.show(false);
		
		JLabel lblNewLabel_3 = new JLabel("Pretraga putnika");
		lblNewLabel_3.setForeground(SystemColor.window);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 11, 236, 62);
		spisakPutnikaPanel.add(lblNewLabel_3);
		
		txtPoljePretraga = new JTextField();
		txtPoljePretraga.setBackground(SystemColor.window);
		txtPoljePretraga.setToolTipText("");
		txtPoljePretraga.setBounds(10, 300, 132, 37);
		spisakPutnikaPanel.add(txtPoljePretraga);
		txtPoljePretraga.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("Pretra\u017Ei");
		btnNewButton_6.setBackground(SystemColor.controlHighlight);
		btnNewButton_6.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String br_pasosa = txtPoljePretraga.getText().trim();
				Connection conn = conn();
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Broj pasosa");
				model.addColumn("Ime");
				model.addColumn("Prezime");
				model.addColumn("Novac");
				if(br_pasosa.equals("") ) {JOptionPane.showMessageDialog(spisakPutnikaPanel,"Morate uneti broj pasosa!");}
				else {
					try {
						String query = "SELECT broj_pasosa, ime, prezime, novac from putnik WHERE broj_pasosa='"+br_pasosa+"'";
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(query);
						int cnt = 0;
						while(rs.next()) {
							model.addRow(new Object[] {
									rs.getInt("broj_pasosa"),
									rs.getString("ime"),
									rs.getString("prezime"),
									rs.getInt("novac"),
									cnt++
							});
							
						}
						rs.close();
						st.close();
						conn.close();
						if(cnt == 0) {JOptionPane.showMessageDialog(spisakPutnikaPanel, "Ne postoji putnik sa tim brojem pasosa.");}
						else {
							table_1.setModel(model);
							table_1.setAutoResizeMode(0);
							table_1.getColumnModel().getColumn(0).setPreferredWidth(137);
							table_1.getColumnModel().getColumn(1).setPreferredWidth(137);
							table_1.getColumnModel().getColumn(2).setPreferredWidth(137);
							table_1.getColumnModel().getColumn(3).setPreferredWidth(137);
						}
						
					} catch(SQLException ex) {
						System.out.println("Greska: " + ex);
					}
				}
			}
		});
		btnNewButton_6.setBounds(152, 300, 89, 37);
		spisakPutnikaPanel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Prika\u017Ei sve");
		btnNewButton_7.setBackground(SystemColor.controlHighlight);
		btnNewButton_7.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(spisakPutnikaPanel.isShowing()) {
						Connection conn = conn();
						DefaultTableModel model = new DefaultTableModel();
						model.addColumn("Broj pasosa");
						model.addColumn("Ime");
						model.addColumn("Prezime");
						model.addColumn("Novac");
						try {
							String query = "SELECT broj_pasosa, ime, prezime, novac from putnik";
							Statement st = conn.createStatement();
							ResultSet rs = st.executeQuery(query);
							while(rs.next()) {
								model.addRow(new Object[] {
										rs.getInt("broj_pasosa"),
										rs.getString("ime"),
										rs.getString("prezime"),
										rs.getInt("novac")
								});
							}
							rs.close();
							st.close();
							conn.close();
							
							table_1.setModel(model);
							table_1.setAutoResizeMode(0);
							table_1.getColumnModel().getColumn(0).setPreferredWidth(137);
							table_1.getColumnModel().getColumn(1).setPreferredWidth(137);
							table_1.getColumnModel().getColumn(2).setPreferredWidth(137);
							table_1.getColumnModel().getColumn(3).setPreferredWidth(137);

				    }	
				    catch (SQLException ex)
				    {
				    	System.out.println("Greska: " + ex);
				    }
				}
			}
		});
		btnNewButton_7.setBounds(384, 21, 183, 31);
		spisakPutnikaPanel.add(btnNewButton_7);
		
		JLabel lblNewLabel_4 = new JLabel("Unesite broj paso\u0161a");
		lblNewLabel_4.setForeground(SystemColor.window);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 265, 132, 35);
		spisakPutnikaPanel.add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 557, 197);
		spisakPutnikaPanel.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table_1.setBackground(SystemColor.activeCaption);
		scrollPane.setViewportView(table_1);
		
		JButton nazad_1 = new JButton("Nazad");
		nazad_1.setBackground(SystemColor.controlHighlight);
		nazad_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nazad_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtPoljePretraga.setText("");
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Broj pasosa");
				model.addColumn("Ime");
				model.addColumn("Prezime");
				model.addColumn("Novac");
				table_1.setModel(model);
				table_1.setAutoResizeMode(0);
				table_1.getColumnModel().getColumn(0).setPreferredWidth(137);
				table_1.getColumnModel().getColumn(1).setPreferredWidth(137);
				table_1.getColumnModel().getColumn(2).setPreferredWidth(137);
				table_1.getColumnModel().getColumn(3).setPreferredWidth(137);
				promeniPanel(aerodromPanel);
			}
		});
		nazad_1.setBounds(456, 300, 111, 31);
		spisakPutnikaPanel.add(nazad_1);
		
		JButton btnNewButton_14 = new JButton("Obrisi putnika");
		btnNewButton_14.setBackground(SystemColor.controlHighlight);
		btnNewButton_14.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_14.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
				if(table_1.getSelectedRow() != -1) {
					Connection con = conn();
					int broj_pasosa = Integer.parseInt(table_1.getModel().getValueAt(table_1.getSelectedRow(), 0).toString());
					try {
						String query = "DELETE from putnik where broj_pasosa='"+ broj_pasosa +"'";
						Statement st = con.createStatement();
						st.executeUpdate(query);
						st.close();
						
						JOptionPane.showMessageDialog(spisakPutnikaPanel, "Uspesno ste obrisali putnika!");
						DefaultTableModel model = new DefaultTableModel();
						model.addColumn("Broj pasosa");
						model.addColumn("Ime");
						model.addColumn("Prezime");
						model.addColumn("Novac");
						
						
							query = "SELECT broj_pasosa, ime, prezime, novac from putnik";
							st = con.createStatement();
							ResultSet rs = st.executeQuery(query);
							while(rs.next()) {
								model.addRow(new Object[] {
										rs.getString("broj_pasosa"),
										rs.getString("ime"),
										rs.getString("prezime"),
										rs.getString("novac"),
										
								});
							}
							rs.close();
							st.close();
							con.close();
							
				table_1.setModel(model);
				table_1.setAutoResizeMode(0);
				table_1.getColumnModel().getColumn(0).setPreferredWidth(137);
				table_1.getColumnModel().getColumn(1).setPreferredWidth(137);
				table_1.getColumnModel().getColumn(2).setPreferredWidth(137);
				table_1.getColumnModel().getColumn(3).setPreferredWidth(137);

				    }	
					
					catch (SQLException ex)
					{
						System.out.println("Greska: " + ex);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(spisakPutnikaPanel, "Niste odabrali putnika kog zelite da obrisete!");
				}
			}
		
			
		});
		btnNewButton_14.setBounds(302, 300, 144, 31);
		spisakPutnikaPanel.add(btnNewButton_14);
		
	
		
		dodajLetPanel = new JPanel();
		dodajLetPanel.setForeground(SystemColor.window);
		layeredPane.setLayer(dodajLetPanel, 0);
		
		dodajLetPanel.setBackground(SystemColor.desktop);
		dodajLetPanel.setBounds(0, 0, 577, 370);
		layeredPane.add(dodajLetPanel);
		dodajLetPanel.setLayout(null);
		
		JLabel sviLetoviLabel = new JLabel("spisak letova");
		sviLetoviLabel.setForeground(SystemColor.window);
		sviLetoviLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		sviLetoviLabel.setBounds(10, 0, 283, 77);
		spisakSvihLetovaPanel.add(sviLetoviLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 68, 557, 253);
		spisakSvihLetovaPanel.add(scrollPane_1);
		
		table_2 = new JTable();
		table_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table_2.setBackground(SystemColor.activeCaption);
		scrollPane_1.setViewportView(table_2);
		dodajLetPanel.show(false);
		
		JButton nazad_2 = new JButton("Nazad");
		nazad_2.setForeground(SystemColor.desktop);
		nazad_2.setBackground(SystemColor.controlHighlight);
		nazad_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nazad_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				polazakTxt.setText("");
				dolazakTxt.setText("");
				brojMestaTxt.setText("");
				vremePolaskaTxt.setText("");
				vremeDolaskaTxt.setText("");
				cenaTxt.setText("");
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("ID leta");
				model.addColumn("Ime leta");
				model.addColumn("Od");
				model.addColumn("Do");
				model.addColumn("Broj mesta");
				model.addColumn("Vreme polaska");
				model.addColumn("Vreme dolaska");
				model.addColumn("Cena leta");
				table_2.setModel(model);
				table_2.setAutoResizeMode(0);
				table_2.getColumnModel().getColumn(0).setPreferredWidth(67);
				table_2.getColumnModel().getColumn(1).setPreferredWidth(70);
				table_2.getColumnModel().getColumn(2).setPreferredWidth(70);
				table_2.getColumnModel().getColumn(3).setPreferredWidth(70);
				table_2.getColumnModel().getColumn(4).setPreferredWidth(70);
				table_2.getColumnModel().getColumn(5).setPreferredWidth(70);
				table_2.getColumnModel().getColumn(6).setPreferredWidth(70);
				table_2.getColumnModel().getColumn(7).setPreferredWidth(70);
				promeniPanel(aerodromPanel);
			}
		});
		nazad_2.setBounds(476, 329, 91, 31);
		dodajLetPanel.add(nazad_2);
		
		polazakTxt = new JTextField();
		polazakTxt.setForeground(SystemColor.desktop);
		polazakTxt.setBackground(SystemColor.window);
		polazakTxt.setBounds(88, 60, 155, 31);
		dodajLetPanel.add(polazakTxt);
		polazakTxt.setColumns(10);
		
		dolazakTxt = new JTextField();
		dolazakTxt.setForeground(SystemColor.desktop);
		dolazakTxt.setBackground(SystemColor.window);
		dolazakTxt.setBounds(88, 150, 155, 31);
		dodajLetPanel.add(dolazakTxt);
		dolazakTxt.setColumns(10);
		
		brojMestaTxt = new JTextField();
		brojMestaTxt.setForeground(SystemColor.desktop);
		brojMestaTxt.setBackground(SystemColor.window);
		brojMestaTxt.setBounds(88, 230, 155, 31);
		dodajLetPanel.add(brojMestaTxt);
		brojMestaTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Polazak od:");
		lblNewLabel_5.setForeground(SystemColor.window);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(10, 60, 107, 25);
		dodajLetPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Do:");
		lblNewLabel_6.setForeground(SystemColor.window);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(10, 150, 107, 14);
		dodajLetPanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Broj mesta: ");
		lblNewLabel_7.setForeground(SystemColor.window);
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(10, 230, 107, 22);
		dodajLetPanel.add(lblNewLabel_7);
		
		vremePolaskaTxt = new JTextField();
		vremePolaskaTxt.setForeground(SystemColor.desktop);
		vremePolaskaTxt.setBackground(SystemColor.window);
		vremePolaskaTxt.setBounds(358, 60, 155, 31);
		dodajLetPanel.add(vremePolaskaTxt);
		vremePolaskaTxt.setColumns(10);
		
		vremeDolaskaTxt = new JTextField();
		vremeDolaskaTxt.setBackground(SystemColor.window);
		vremeDolaskaTxt.setBounds(358, 150, 155, 31);
		dodajLetPanel.add(vremeDolaskaTxt);
		vremeDolaskaTxt.setColumns(10);
		
		cenaTxt = new JTextField();
		cenaTxt.setBackground(SystemColor.window);
		cenaTxt.setBounds(358, 230, 155, 31);
		dodajLetPanel.add(cenaTxt);
		cenaTxt.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Vreme polaska: ");
		lblNewLabel_8.setForeground(SystemColor.window);
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(258, 60, 131, 25);
		dodajLetPanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Vreme dolaska: ");
		lblNewLabel_9.setForeground(SystemColor.window);
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(258, 150, 109, 14);
		dodajLetPanel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Cena leta: ");
		lblNewLabel_10.setForeground(SystemColor.window);
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(261, 230, 76, 14);
		dodajLetPanel.add(lblNewLabel_10);
		
		JButton btnNewButton_8 = new JButton("Dodaj");
		btnNewButton_8.setForeground(SystemColor.desktop);
		btnNewButton_8.setBackground(SystemColor.controlHighlight);
		btnNewButton_8.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String polazak = polazakTxt.getText().trim();
				String dolazak = dolazakTxt.getText().trim();
				String broj_mesta = brojMestaTxt.getText().trim();
				String vremePolazak = vremePolaskaTxt.getText().trim();
				String vremeDolazak = vremeDolaskaTxt.getText().trim();
				String cena = cenaTxt.getText().trim();
				
				if(polazak.equals("") || dolazak.equals("") || broj_mesta.equals("") || vremePolazak.equals("") || vremeDolazak.equals("") || cena.equals("")) {
					JOptionPane.showMessageDialog(dodajLetPanel, "Niste popunili sva polja!");
				}
				else {
					polazakTxt.setText("");
					dolazakTxt.setText("");
					brojMestaTxt.setText("");
					vremePolaskaTxt.setText("");
					vremeDolaskaTxt.setText("");
					cenaTxt.setText("");
					int id = 0;
					
					if(dodajLetPanel.isShowing()) {
						try {
							Connection conn = conn();
							String query = "SELECT * FROM let";
							Statement st =conn.createStatement();
							ResultSet rs =  st.executeQuery(query);
							while(rs.next()) {
								
								int id2 = rs.getInt("id_leta");
								if(id2 > id) {
									id = id2; 
								}
							}
							id++;
							query = "INSERT INTO let VALUES('" + id + "','" + polazak + "->" + dolazak + "','" + polazak + "','" + dolazak + "','" + broj_mesta + "','" +  vremePolazak + "','" + vremeDolazak + "','"+ cena + "')";
			    	     	st =conn.createStatement();
				    	     
			                int rows=st.executeUpdate(query);
			                
			                st.close();
			                conn.close();
			                
			                JOptionPane.showMessageDialog(dodajLetPanel, "Uspesno ste dodali let!");
							
						} catch(Exception e2) {
							System.out.println(e2);
						}
					}
				}
			}
		});
		btnNewButton_8.setBounds(10, 329, 109, 31);
		dodajLetPanel.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Prika\u017Ei sve");
		btnNewButton_9.setBackground(SystemColor.controlHighlight);
		btnNewButton_9.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = conn();
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("ID leta");
				model.addColumn("Ime leta");
				model.addColumn("Od");
				model.addColumn("Do");
				model.addColumn("Broj mesta");
				model.addColumn("Vreme polaska");
				model.addColumn("Vreme dolaska");
				model.addColumn("Cena leta");
				try {
					String query = "SELECT * from let";
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(query);
					while(rs.next()) {
						model.addRow(new Object[] {
								rs.getInt("id_leta"),
								rs.getString("ime_leta"),
								rs.getString("od"),
								rs.getString("do"),
								rs.getString("br_mesta"),
								rs.getString("vreme_polaska"),
								rs.getString("vreme_dolaska"),
								rs.getInt("cena")
						});
					}
					rs.close();
					st.close();
					conn.close();
					
					table_2.setModel(model);
					table_2.setAutoResizeMode(0);
					table_2.getColumnModel().getColumn(0).setPreferredWidth(67);
					table_2.getColumnModel().getColumn(1).setPreferredWidth(70);
					table_2.getColumnModel().getColumn(2).setPreferredWidth(70);
					table_2.getColumnModel().getColumn(3).setPreferredWidth(70);
					table_2.getColumnModel().getColumn(4).setPreferredWidth(70);
					table_2.getColumnModel().getColumn(5).setPreferredWidth(70);
					table_2.getColumnModel().getColumn(6).setPreferredWidth(70);
					table_2.getColumnModel().getColumn(7).setPreferredWidth(70);

		    }	
			catch (SQLException ex)
			{
				System.out.println("Greska: " + ex);
			}
			}
		});
		btnNewButton_9.setBounds(10, 332, 107, 31);
		spisakSvihLetovaPanel.add(btnNewButton_9);
		
		JButton nazad_1_1 = new JButton("Nazad");
		nazad_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promeniPanel(aerodromPanel);
			}
		});
		nazad_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nazad_1_1.setBackground(SystemColor.controlHighlight);
		nazad_1_1.setBounds(404, 332, 146, 39);
		spisakSvihLetovaPanel.add(nazad_1_1);
		
		JButton btnNewButton_13 = new JButton("Obrisi let"); 
		btnNewButton_13.setBackground(SystemColor.controlHighlight);
		btnNewButton_13.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_2.getSelectedRow() != -1) {
					Connection con = conn();
					int id_leta = Integer.parseInt(table_2.getModel().getValueAt(table_2.getSelectedRow(), 0).toString());
					try {
						String query = "DELETE from let where id_leta='"+ id_leta +"'";
						Statement st = con.createStatement();
						st.executeUpdate(query);
						st.close();
						
						JOptionPane.showMessageDialog(dodajLetPanel, "Uspesno ste obrisali let!");
						DefaultTableModel model = new DefaultTableModel();
						model.addColumn("ID leta");
						model.addColumn("Ime leta");
						model.addColumn("Od");
						model.addColumn("Do");
						model.addColumn("Broj mesta");
						model.addColumn("Vreme polaska");
						model.addColumn("Vreme dolaska");
						model.addColumn("Cena leta");
						
							query = "SELECT * from let";
							st = con.createStatement();
							ResultSet rs = st.executeQuery(query);
							while(rs.next()) {
								model.addRow(new Object[] {
										rs.getInt("id_leta"),
										rs.getString("ime_leta"),
										rs.getString("od"),
										rs.getString("do"),
										rs.getString("br_mesta"),
										rs.getString("vreme_polaska"),
										rs.getString("vreme_dolaska"),
										rs.getInt("cena")
								});
							}
							rs.close();
							st.close();
							con.close();
							
							table_2.setModel(model);
							table_2.setAutoResizeMode(0);
							table_2.getColumnModel().getColumn(0).setPreferredWidth(67);
							table_2.getColumnModel().getColumn(1).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(2).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(3).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(4).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(5).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(6).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(7).setPreferredWidth(70);

				    }	
					
					catch (SQLException ex)
					{
						System.out.println("Greska: " + ex);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(dodajLetPanel, "Niste odabrali let koji zelite da obrisete!");
				}
			}
		});
		btnNewButton_13.setBounds(136, 332, 107, 31);
		spisakSvihLetovaPanel.add(btnNewButton_13);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("");
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(207, 10, 194, 37);
		spisakSvihLetovaPanel.add(textField_2);
		
		JButton btnNewButton_6_1 = new JButton("Pretraži");
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id_leta = textField_2.getText().trim();
				Connection conn = conn();
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("ID leta");
				model.addColumn("Ime leta");
				model.addColumn("Od");
				model.addColumn("Do");
				model.addColumn("Broj mesta");
				model.addColumn("Vreme polaska");
				model.addColumn("Vreme dolaska");
				model.addColumn("Cena leta");
				if(id_leta.equals("") ) {JOptionPane.showMessageDialog(spisakPutnikaPanel,"Morate uneti broj leta!");}
				else {
					try {
						String query = "SELECT * from let WHERE id_leta='"+id_leta+"'";
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(query);
						int cnt = 0;
						while(rs.next()) {
							model.addRow(new Object[] {
									rs.getInt("id_leta"),
									rs.getString("ime_leta"),
									rs.getString("od"),
									rs.getString("do"),
									rs.getString("br_mesta"),
									rs.getString("vreme_polaska"),
									rs.getString("vreme_dolaska"),
									rs.getInt("cena"),
									cnt++
							});
							
						}
						rs.close();
						st.close();
						conn.close();
						if(cnt == 0) {JOptionPane.showMessageDialog(spisakSvihLetovaPanel, "Ne postoji let.");}
						else {
							table_2.setModel(model);
							table_2.setAutoResizeMode(0);
							table_2.getColumnModel().getColumn(0).setPreferredWidth(67);
							table_2.getColumnModel().getColumn(1).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(2).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(3).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(4).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(5).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(6).setPreferredWidth(70);
							table_2.getColumnModel().getColumn(7).setPreferredWidth(70);
						}
						
					} catch(SQLException ex) {
						System.out.println("Greska: " + ex);
					}
				}
					
			}
		});
		btnNewButton_6_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_6_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_6_1.setBounds(435, 10, 132, 39);
		spisakSvihLetovaPanel.add(btnNewButton_6_1);
		
		kupiKartuPanel = new JPanel();
		kupiKartuPanel.setForeground(SystemColor.window);
		kupiKartuPanel.setBackground(SystemColor.desktop);
		layeredPane.setLayer(kupiKartuPanel, 0);
		kupiKartuPanel.setBounds(0, 0, 577, 370);
		layeredPane.add(kupiKartuPanel);
		kupiKartuPanel.setLayout(null);
		kupiKartuPanel.show(false);
		
		JLabel lblNewLabel_11 = new JLabel("KUPI KARTU");
		lblNewLabel_11.setForeground(SystemColor.window);
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_11.setBounds(218, 10, 156, 46);
		kupiKartuPanel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Spisak letova");
		lblNewLabel_12.setForeground(SystemColor.window);
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_12.setBounds(10, 64, 89, 17);
		kupiKartuPanel.add(lblNewLabel_12);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 91, 557, 219);
		kupiKartuPanel.add(scrollPane_3);
		
		table_4 = new JTable();
		table_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table_4.setBackground(SystemColor.activeCaption);
		scrollPane_3.setViewportView(table_4);
		
		JButton nazad_4 = new JButton("Nazad");
		nazad_4.setBackground(SystemColor.controlHighlight);
		nazad_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nazad_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setText("");
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("ID leta"); 
				model.addColumn("Ime leta");
				model.addColumn("Od");
				model.addColumn("Do");
				model.addColumn("Broj mesta");
				model.addColumn("Vreme polaska");
				model.addColumn("Vreme dolaska");
				model.addColumn("Cena leta");
				table_4.setModel(model);
				table_4.setAutoResizeMode(0);
				table_4.getColumnModel().getColumn(0).setPreferredWidth(67);
				table_4.getColumnModel().getColumn(1).setPreferredWidth(70);
				table_4.getColumnModel().getColumn(2).setPreferredWidth(70);
				table_4.getColumnModel().getColumn(3).setPreferredWidth(70);
				table_4.getColumnModel().getColumn(4).setPreferredWidth(70);
				table_4.getColumnModel().getColumn(5).setPreferredWidth(70);
				table_4.getColumnModel().getColumn(6).setPreferredWidth(70);
				table_4.getColumnModel().getColumn(7).setPreferredWidth(70);
				promeniPanel(aerodromPanel);
			}
		});
		nazad_4.setBounds(478, 328, 89, 31);
		kupiKartuPanel.add(nazad_4);
		
		JButton btnNewButton_11 = new JButton("Kupi");
		btnNewButton_11.setBackground(SystemColor.controlHighlight);
		btnNewButton_11.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String br_pasosa = textField_1.getText().trim();
				if(br_pasosa.equals("")) { JOptionPane.showMessageDialog(kupiKartuPanel,"Niste ukucali broj pasosa putnika!");}
				else {
					try {
						Connection conn = conn();
						String query = "SELECT * from putnik WHERE broj_pasosa='"+br_pasosa+"'";
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(query);
						
						String id_p = null;
						String ime = null;
						String prezime = null; 
						int br_pas = 0;
						int novac = -1;
						
						boolean flag = false;
						
						
						while(rs.next()) {
							flag = true;
							id_p = rs.getString("id_p");
							ime = rs.getString("ime");
							prezime = rs.getString("prezime");
							br_pas = rs.getInt("broj_pasosa");
							novac = rs.getInt("novac");
						}
						
						if(flag == false) {
							JOptionPane.showMessageDialog(kupiKartuPanel, "Ne postoji putnik sa unetim brojem pasosa.");
							st.close();
							rs.close();
							conn.close();
						}
							
						else {
							int cena_leta;
								
							int row = table_4.getSelectedRow();
								
							if(row != -1) {
								cena_leta = Integer.parseInt(table_4.getModel().getValueAt(row, 7).toString());
								if(novac >= cena_leta) {
									
									int id_leta = Integer.parseInt(table_4.getModel().getValueAt(row, 0).toString());
									String ime_leta = table_4.getModel().getValueAt(row, 1).toString();
									String pol = table_4.getModel().getValueAt(row, 2).toString();
									String dol = table_4.getModel().getValueAt(row, 3).toString();
									String br_mesta = table_4.getModel().getValueAt(row, 4).toString();
									String vreme_pol = table_4.getModel().getValueAt(row, 5).toString();
									String vreme_dol = table_4.getModel().getValueAt(row, 6).toString();
									novac = novac - cena_leta;
									
									
									int id_karte = 0;
									query = "SELECT * FROM karta";
									st =conn.createStatement();
									rs =  st.executeQuery(query);
									while(rs.next()) {
										
										int id2 = rs.getInt("id_karte");
										if(id2 > id_karte) {
											id_karte = id2;
										}
									}
									
									id_karte++;
									query = "INSERT INTO karta VALUES('" + id_leta + "','" + ime_leta + "','" + pol + "','" + dol + "','" +  vreme_pol + "','" + vreme_dol + "','" + br_mesta + "','" + ime + "','" + prezime + "','" + id_p + "','" + br_pas + "','"+ novac + "','"+ id_karte + "','"+ cena_leta + "')";
									st =conn.createStatement() ;
							    	     
						            int rows=st.executeUpdate(query);
						                
						            st.close();
						            rs.close();
						            conn.close();
						            
						            JOptionPane.showMessageDialog(kupiKartuPanel, "Kupili ste kartu!");
						            
						            Connection conn2 = conn();
						            query = "SELECT * FROM putnik WHERE broj_pasosa='"+br_pasosa+"'";
									st =conn2.createStatement();
									rs =  st.executeQuery(query);
									int parice = 0;
									while(rs.next()) {
										parice = rs.getInt("novac");
									}
									
									parice = parice - cena_leta;
									query = "UPDATE putnik SET novac='"+parice+"' WHERE broj_pasosa='"+br_pasosa+"'";
									st =conn2.createStatement();
									st.executeUpdate(query);
									
									st.close();
						            rs.close();
						            conn.close();
								}
								else {
									JOptionPane.showMessageDialog(kupiKartuPanel, "Putnik nema dovoljno sredstava!");
									st.close();
									rs.close();
									conn.close();
								}
							}
							else { JOptionPane.showMessageDialog(kupiKartuPanel, "Niste odabrali let.");}
						}
					}
							
					catch (Exception e3) { System.out.println(e3);}
				}
			}
		});
		btnNewButton_11.setBounds(285, 328, 89, 31);
		kupiKartuPanel.add(btnNewButton_11);
		
		JLabel lblNewLabel_13 = new JLabel("Broj pasosa putnika");
		lblNewLabel_13.setForeground(SystemColor.window);
		lblNewLabel_13.setBackground(SystemColor.desktop);
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_13.setBounds(10, 330, 131, 29);
		kupiKartuPanel.add(lblNewLabel_13);
		
		textField_1 = new JTextField();
		textField_1.setBackground(SystemColor.window);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_1.setBounds(151, 333, 113, 23);
		kupiKartuPanel.add(textField_1);
		textField_1.setColumns(10);
		
		kupovinaLabel = new JLabel("");
		kupovinaLabel.setForeground(SystemColor.window);
		kupovinaLabel.setBounds(308, 319, 160, 40);
		kupiKartuPanel.add(kupovinaLabel);
		
		JButton btnNewButton_12 = new JButton("Prika\u017Ei letove");
		btnNewButton_12.setBackground(SystemColor.controlHighlight);
		btnNewButton_12.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = conn();
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("ID leta");
				model.addColumn("Ime leta");
				model.addColumn("Od");
				model.addColumn("Do");
				model.addColumn("Broj mesta");
				model.addColumn("Vreme polaska");
				model.addColumn("Vreme dolaska");
				model.addColumn("Cena leta");
				try {
					String query = "SELECT * from let";
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(query);
					while(rs.next()) {
						model.addRow(new Object[] {
								rs.getInt("id_leta"),
								rs.getString("ime_leta"),
								rs.getString("od"),
								rs.getString("do"),
								rs.getString("br_mesta"),
								rs.getString("vreme_polaska"),
								rs.getString("vreme_dolaska"),
								rs.getInt("cena")
						});
					}
					rs.close();
					st.close();
					conn.close();
					
					table_4.setModel(model);
					table_4.setAutoResizeMode(0);
					table_4.getColumnModel().getColumn(0).setPreferredWidth(67);
					table_4.getColumnModel().getColumn(1).setPreferredWidth(70);
					table_4.getColumnModel().getColumn(2).setPreferredWidth(70);
					table_4.getColumnModel().getColumn(3).setPreferredWidth(70);
					table_4.getColumnModel().getColumn(4).setPreferredWidth(70);
					table_4.getColumnModel().getColumn(5).setPreferredWidth(70);
					table_4.getColumnModel().getColumn(6).setPreferredWidth(70);
					table_4.getColumnModel().getColumn(7).setPreferredWidth(70);

		    }	
		    catch (Exception e1)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e1.getMessage());
		    }
			}
		});
		btnNewButton_12.setBounds(109, 57, 126, 31);
		kupiKartuPanel.add(btnNewButton_12);
		
	
	

		
		prikaziKartePanel = new JPanel();
		prikaziKartePanel.setForeground(SystemColor.window);
		prikaziKartePanel.setBackground(SystemColor.desktop);
		layeredPane.setLayer(prikaziKartePanel, 0);
		prikaziKartePanel.setBounds(0, 0, 577, 370);
		layeredPane.add(prikaziKartePanel);
		prikaziKartePanel.setLayout(null);
		prikaziKartePanel.show(false);
		
		JLabel spisakKarataLabel = new JLabel("SPISAK PRODATIH KARATA");
		spisakKarataLabel.setForeground(SystemColor.window);
		spisakKarataLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		spisakKarataLabel.setBounds(178, 11, 216, 31);
		prikaziKartePanel.add(spisakKarataLabel);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 62, 557, 255);
		prikaziKartePanel.add(scrollPane_2);
		
		table_3 = new JTable();
		table_3.setBackground(SystemColor.activeCaption);
		table_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scrollPane_2.setViewportView(table_3);
		
		JButton nazad_3 = new JButton("Nazad");
		nazad_3.setBackground(SystemColor.controlHighlight);
		nazad_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nazad_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("ID leta");
				model.addColumn("Ime leta");
				model.addColumn("Polazak");
				model.addColumn("Dolazak");
				model.addColumn("Vreme polaska");
				model.addColumn("Vreme dolaska");
				model.addColumn("Broj mesta");
				model.addColumn("Ime");
				model.addColumn("Prezime");
				model.addColumn("ID putnika");
				model.addColumn("Broj pasosa");
				model.addColumn("Novac");
				model.addColumn("ID karte");
				model.addColumn("Cena karte");
				table_3.setModel(model);
				table_3.setAutoResizeMode(0);
				table_3.getColumnModel().getColumn(0).setPreferredWidth(60);
				table_3.getColumnModel().getColumn(1).setPreferredWidth(125);
				table_3.getColumnModel().getColumn(2).setPreferredWidth(125);
				table_3.getColumnModel().getColumn(3).setPreferredWidth(125);
				table_3.getColumnModel().getColumn(4).setPreferredWidth(125);
				table_3.getColumnModel().getColumn(5).setPreferredWidth(125);
				table_3.getColumnModel().getColumn(6).setPreferredWidth(125);
				table_3.getColumnModel().getColumn(7).setPreferredWidth(125);
				table_3.getColumnModel().getColumn(8).setPreferredWidth(125);
				table_3.getColumnModel().getColumn(9).setPreferredWidth(60);
				table_3.getColumnModel().getColumn(10).setPreferredWidth(125);
				table_3.getColumnModel().getColumn(11).setPreferredWidth(125);
				table_3.getColumnModel().getColumn(12).setPreferredWidth(60);
				table_3.getColumnModel().getColumn(13).setPreferredWidth(125);
				promeniPanel(aerodromPanel);
			}
		});
		nazad_3.setBounds(446, 328, 121, 31);
		prikaziKartePanel.add(nazad_3);
		
		JButton btnNewButton_10 = new JButton("Prika\u017Ei karte");
		btnNewButton_10.setBackground(SystemColor.controlHighlight);
		btnNewButton_10.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(prikaziKartePanel.isShowing()) {
					Connection conn = conn();
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("ID leta");
					model.addColumn("Ime leta");
					model.addColumn("Polazak");
					model.addColumn("Dolazak");
					model.addColumn("Vreme polaska");
					model.addColumn("Vreme dolaska");
					model.addColumn("Broj mesta");
					model.addColumn("Ime");
					model.addColumn("Prezime");
					model.addColumn("ID putnika");
					model.addColumn("Broj pasosa");
					model.addColumn("Novac");
					model.addColumn("ID karte");
					model.addColumn("Cena karte");
					
					try {
						String query = "SELECT * from karta";
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(query);
						while(rs.next()) {
							model.addRow(new Object[] {
									rs.getString("id_leta"),
									rs.getString("ime_leta"),
									rs.getString("od"),
									rs.getString("do"),
									rs.getString("vreme_polaska"),
									rs.getString("vreme_dolaska"),
									rs.getString("br_mesta"),
									rs.getString("ime"),
									rs.getString("prezime"),
									rs.getString("id_p"),
									rs.getString("broj_pasosa"),
									rs.getString("novac"),
									rs.getString("id_karte"),
									rs.getString("cena")
							});
						}
						rs.close();
						st.close();
						conn.close();
						
						table_3.setModel(model);
						table_3.setAutoResizeMode(0);
						table_3.getColumnModel().getColumn(0).setPreferredWidth(60);
						table_3.getColumnModel().getColumn(1).setPreferredWidth(125);
						table_3.getColumnModel().getColumn(2).setPreferredWidth(125);
						table_3.getColumnModel().getColumn(3).setPreferredWidth(125);
						table_3.getColumnModel().getColumn(4).setPreferredWidth(125);
						
						table_3.getColumnModel().getColumn(5).setPreferredWidth(125);
						table_3.getColumnModel().getColumn(6).setPreferredWidth(125);
						table_3.getColumnModel().getColumn(7).setPreferredWidth(125);
						table_3.getColumnModel().getColumn(8).setPreferredWidth(125);
						table_3.getColumnModel().getColumn(9).setPreferredWidth(60);
					
						table_3.getColumnModel().getColumn(10).setPreferredWidth(125);
						table_3.getColumnModel().getColumn(11).setPreferredWidth(125);
						table_3.getColumnModel().getColumn(12).setPreferredWidth(60);
						table_3.getColumnModel().getColumn(13).setPreferredWidth(125);


			    }	
				catch (SQLException ex)
				{
					System.out.println("Greska: " + ex);
				}
			}
			}
		});
		btnNewButton_10.setBounds(10, 328, 121, 31);
		prikaziKartePanel.add(btnNewButton_10);
	
		
		
	}
}
