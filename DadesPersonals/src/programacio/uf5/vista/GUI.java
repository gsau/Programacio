package programacio.uf5.vista;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class GUI {
	private JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private JLabel lblRegistre;
	private int compt = 1;

	public GUI() {
		initialize();
		carregaPrimer();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setSize(700, 200);
					window.frame.setTitle("Gestió Persones");
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 542, 203);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JToolBar toolBar = new JToolBar();
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 1;
		gbc_toolBar.gridy = 0;
		frame.getContentPane().add(toolBar, gbc_toolBar);

		final JButton btnNou = new JButton("Nou");
		btnNou.setEnabled(true);
		toolBar.add(btnNou);

		final JButton btnEdita = new JButton("Edita");

		btnEdita.setEnabled(true);
		toolBar.add(btnEdita);

		final JButton btnEsborra = new JButton("Esborra");
		btnEsborra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editaOEsborraFitxer();
				netejarText();
			}
		});
		btnEsborra.setEnabled(true);
		toolBar.add(btnEsborra);

		final JButton btnPrimer = new JButton("Primer");
		btnPrimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregaPrimer();
				compt = 1;
				actualitzarRegistre();
			}

		});
		btnPrimer.setEnabled(true);
		toolBar.add(btnPrimer);

		final JButton btnDarrer = new JButton("Darrer");
		btnDarrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				compt = carregarFitxerCsv().size();
				actualitzarRegistre();
				ArrayList<String> p = new ArrayList<String>();
				p = carregarFitxerCsv();
				String utlima = p.get(p.size() - 1);
				String[] ultim = utlima.split(",");
				textField.setText(ultim[0]);
				textField_1.setText(ultim[1]);
				textField_2.setText(ultim[2]);
				textField_3.setText(ultim[3]);
				textField_4.setText(ultim[4]);
				String actual = (textField.getText() + ","
						+ textField_1.getText() + "," + textField_2.getText()
						+ "," + textField_3.getText() + "," + textField_4
						.getText());
				if (p.size() > 0) {
					int pos = 0;
					int i = 0;
					boolean trobat = false;
					while (i < p.size() || trobat == false) {
						if (p.get(i).equals(actual)) {
							pos = i;
							trobat = true;
						}
						i++;
					}
					if (p.size() > 0) {
					}
				}
			}
		});
		btnDarrer.setEnabled(true);
		toolBar.add(btnDarrer);

		final JButton btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> p = new ArrayList<String>();
				p = carregarFitxerCsv();
				String actual = (textField.getText() + ","
						+ textField_1.getText() + "," + textField_2.getText()
						+ "," + textField_3.getText() + "," + textField_4
						.getText());
				int pos = 0;
				int i = 0;
				if (compt == 0) {
				} else if (compt == 1) {
					compt = 1;
				} else {
					compt -= 1;
				}
				actualitzarRegistre();
				boolean trobat = false;
				while (i < p.size() || trobat == false) {
					if (p.get(i).equals(actual)) {
						pos = i;
						trobat = true;
					}
					i++;
				}
				if (pos == 0) {
					String seguent = p.get(p.size() - 1);
					String[] seg = seguent.split(",");
					textField.setText(seg[0]);
					textField_1.setText(seg[1]);
					textField_2.setText(seg[2]);
					textField_3.setText(seg[3]);
					textField_4.setText(seg[4]);
				} else {
					String seguent = p.get(pos - 1);
					String[] seg = seguent.split(",");
					textField.setText(seg[0]);
					textField_1.setText(seg[1]);
					textField_2.setText(seg[2]);
					textField_3.setText(seg[3]);
					textField_4.setText(seg[4]);
				}
			}

		});
		btnAnterior.setEnabled(true);
		toolBar.add(btnAnterior);
		final JButton btnSeguent = new JButton("Seguent");
		btnSeguent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> p = new ArrayList<String>();
				p = carregarFitxerCsv();
				String actual = (textField.getText() + ","
						+ textField_1.getText() + "," + textField_2.getText()
						+ "," + textField_3.getText() + "," + textField_4
						.getText());
				int pos = 0;
				int i = 0;
				boolean trobat = false;
				while (i < p.size() || trobat == false) {
					if (p.get(i).equals(actual)) {
						pos = i;
						trobat = true;
					}
					i++;
				}
				if (carregarFitxerCsv().size() <= compt) {
					compt = carregarFitxerCsv().size();
				} else {
					compt += 1;
				}
				actualitzarRegistre();
				if (pos == p.size() - 1) {
					String seguent = p.get(0);
					String[] seg = seguent.split(",");
					textField.setText(seg[0]);
					textField_1.setText(seg[1]);
					textField_2.setText(seg[2]);
					textField_3.setText(seg[3]);
					textField_4.setText(seg[4]);
				} else {
					String seguent = p.get(pos + 1);
					String[] seg = seguent.split(",");
					textField.setText(seg[0]);
					textField_1.setText(seg[1]);
					textField_2.setText(seg[2]);
					textField_3.setText(seg[3]);
					textField_4.setText(seg[4]);
				}

			}
		});
		btnSeguent.setEnabled(true);
		toolBar.add(btnSeguent);

		JButton btnDesa = new JButton("Desa");
		btnDesa.setEnabled(false);
		toolBar.add(btnDesa);

		JButton btnCancela = new JButton("Cancela");
		btnCancela.setEnabled(false);
		toolBar.add(btnCancela);
		if (carregarFitxerCsv().isEmpty()) {
			btnSeguent.setEnabled(false);
			btnAnterior.setEnabled(false);
			btnDarrer.setEnabled(false);
			btnPrimer.setEnabled(false);
			btnEdita.setEnabled(false);
			btnEsborra.setEnabled(false);
		}
		btnNou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				netejarText();
				btnDesa.setEnabled(true);
				btnCancela.setEnabled(true);
				btnEdita.setEnabled(false);
				btnEsborra.setEnabled(false);
				btnAnterior.setEnabled(false);
				btnSeguent.setEnabled(false);
				btnPrimer.setEnabled(false);
				btnDarrer.setEnabled(false);
				btnNou.setEnabled(false);
			}
		});
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				netejarText();
				btnDesa.setEnabled(false);
				btnCancela.setEnabled(false);
				btnEdita.setEnabled(true);
				btnEsborra.setEnabled(true);
				btnAnterior.setEnabled(true);
				btnSeguent.setEnabled(true);
				btnPrimer.setEnabled(true);
				btnDarrer.setEnabled(true);
				btnNou.setEnabled(true);
			}
		});
		btnDesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDesa.setEnabled(false);
				btnCancela.setEnabled(false);
				btnEdita.setEnabled(true);
				btnEsborra.setEnabled(true);
				btnAnterior.setEnabled(true);
				btnSeguent.setEnabled(true);
				btnPrimer.setEnabled(true);
				btnDarrer.setEnabled(true);
				btnNou.setEnabled(true);
				afegirPersona();
				compt++;
				actualitzarRegistre();
			}
		});
		btnEdita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAnterior.setEnabled(false);
				btnSeguent.setEnabled(false);
				btnPrimer.setEnabled(false);
				btnNou.setEnabled(false);
				btnDesa.setEnabled(true);
				btnCancela.setEnabled(true);
				btnEsborra.setEnabled(false);
				btnDarrer.setEnabled(false);
				compt--;
			}
		});
		lblRegistre = new JLabel("Registre" + compt + " de "
				+ carregarFitxerCsv().size());
		toolBar.add(lblRegistre);
		JLabel lblNewLabel = new JLabel("DNI");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNom = new JLabel("Nom");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.anchor = GridBagConstraints.EAST;
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 0;
		gbc_lblNom.gridy = 2;
		frame.getContentPane().add(lblNom, gbc_lblNom);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		frame.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblPrimerCognom = new JLabel("Primer Cognom");
		GridBagConstraints gbc_lblPrimerCognom = new GridBagConstraints();
		gbc_lblPrimerCognom.anchor = GridBagConstraints.EAST;
		gbc_lblPrimerCognom.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrimerCognom.gridx = 0;
		gbc_lblPrimerCognom.gridy = 3;
		frame.getContentPane().add(lblPrimerCognom, gbc_lblPrimerCognom);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		frame.getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblSegonCognom = new JLabel("Segon Cognom");
		GridBagConstraints gbc_lblSegonCognom = new GridBagConstraints();
		gbc_lblSegonCognom.anchor = GridBagConstraints.EAST;
		gbc_lblSegonCognom.insets = new Insets(0, 0, 5, 5);
		gbc_lblSegonCognom.gridx = 0;
		gbc_lblSegonCognom.gridy = 4;
		frame.getContentPane().add(lblSegonCognom, gbc_lblSegonCognom);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 4;
		frame.getContentPane().add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblEdat = new JLabel("Edat");
		GridBagConstraints gbc_lblEdat = new GridBagConstraints();
		gbc_lblEdat.anchor = GridBagConstraints.EAST;
		gbc_lblEdat.insets = new Insets(0, 0, 0, 5);
		gbc_lblEdat.gridx = 0;
		gbc_lblEdat.gridy = 5;
		frame.getContentPane().add(lblEdat, gbc_lblEdat);

		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 5;
		frame.getContentPane().add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
	}

	/**
	 * Aquí amb aquest subprograma em carrega en un arraiList el fitxer existent
	 * .csv
	 * @return ArrayList plena
	 */
	public static ArrayList<String> carregarFitxerCsv() {
		ArrayList<String> arraiListPersona = new ArrayList<String>();
		try {
			FileReader llegirFit = new FileReader(SeleccionaFitxer());
			BufferedReader fit = new BufferedReader(llegirFit);
			String linia;
			while ((linia = fit.readLine()) != null) {
				arraiListPersona.add(linia);
			}
			if (null != llegirFit) {
				llegirFit.close();
			}
		} catch (Exception e) {

		}
		return arraiListPersona;
	}
/**
 * Aquest subprograma l'utilitzo per editar o esborrar registres dins el fitxer .csv
 */
	public void editaOEsborraFitxer() {
		ArrayList<String> p = new ArrayList<String>();
		p = carregarFitxerCsv();
		String liniaUltima = p.get(p.size() - 1);
		String[] ultima = liniaUltima.split(",");
		String liniaActual = (textField.getText() + "," + textField_1.getText()
				+ "," + textField_2.getText() + "," + textField_3.getText()
				+ "," + textField_4.getText());
		int i = 0;
		boolean trobat = false;
		while (i < p.size() || trobat == false) {
			if (p.get(i).equals(liniaActual)) {
				p.remove(i);
				trobat = true;
			}
			i++;
		}
		FileWriter fitxer;
		PrintWriter pw;
		try {
			fitxer = new FileWriter(SeleccionaFitxer());
			pw = new PrintWriter(fitxer);
			for (int z = 0; z < p.size(); z++) {
				pw.println(p.get(z));
			}
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * Agafo el primer registre del fitxer i el mostro quan s'em obre el programa 
 */
	private void carregaPrimer() {
		ArrayList<String> p = new ArrayList<String>();
		p = carregarFitxerCsv();
		if (p.isEmpty()) {
			netejarText();
			compt = 0;
		} else {
			compt = 1;
			String primera = p.get(0);
			String[] prim = primera.split(",");
			textField.setText(prim[0]);
			textField_1.setText(prim[1]);
			textField_2.setText(prim[2]);
			textField_3.setText(prim[3]);
			textField_4.setText(prim[4]);
			if (p.size() > 0) {
				String actual = (textField.getText() + ","
						+ textField_1.getText() + "," + textField_2.getText()
						+ "," + textField_3.getText() + "," + textField_4
						.getText());
				int pos = 0;
				int i = 0;
				boolean trobat = false;
				while (i < p.size() || trobat == false) {
					if (p.get(i).equals(actual)) {
						pos = i;
						trobat = true;
					}
					i++;
				}
			}
		}

	}

	private void netejarText() {
		GUI.textField.setText("");
		GUI.textField_1.setText("");
		GUI.textField_2.setText("");
		GUI.textField_3.setText("");
		GUI.textField_4.setText("");
	}
	/**
	 * Actualitzo el Label que m'indica el número de regisre i el total.
	 */
	private void actualitzarRegistre() {
		if (carregarFitxerCsv().isEmpty()) {
			compt = 0;
		}
		this.lblRegistre.setText("Registre " + compt + " de "
				+ carregarFitxerCsv().size());
	}
	/**
	 * Creo o carrego el fitxer .csv
	 * @return
	 */
	public static File SeleccionaFitxer() {
		File fitxer = new File("prova.csv");
		if (!fitxer.exists()) {
			try {
				fitxer.createNewFile();
				return fitxer;
			} catch (IOException e) {
			}

		}
		return fitxer;
	}
	/**
	 * Afegeixo un registre dins el fitxer .csv
	 */
	public static void afegirPersona() {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(SeleccionaFitxer(), true));
			out.write(textField.getText() + "," + textField_1.getText() + ","
					+ textField_2.getText() + "," + textField_3.getText() + ","
					+ textField_4.getText());
			out.newLine();
		} catch (IOException e) {

		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
