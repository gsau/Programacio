import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.CardLayout;

import javax.swing.JTextField;
import javax.swing.JEditorPane;

import java.awt.BorderLayout;

import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;

import java.awt.Button;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.JTable;

import java.awt.TextField;

import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DadesPersonals {

	private JFrame frmGestiDePersones;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JTextField textField;
	//private static String dni,nom,cognom1,cognom2,edat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DadesPersonals window = new DadesPersonals();
					window.frmGestiDePersones.setVisible(true);
					carregarDades();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DadesPersonals() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static File carregarDades(){
		File f= new File("dades.csv");
		if(f.exists()){
			return f;
		}else{
			try {
				f.createNewFile();
				return f;
			} catch (IOException e) {
				System.out.println("Error:");
			}
			return f;
		}
	}
	public static void afegirPersona(File f) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(f, true));
			out.write(textField.getText()+","+textField_1.getText()+","+textField_2.getText()+","+textField_3.getText()+","+textField_4.getText());
			out.newLine();
		} catch (IOException e) {
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println("Error: " + e);
				}

			}
		}

	}
	public static void netejarPersona(){
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
	}
	public static boolean buscaUsuari(File f) {
		BufferedReader entrada = null;
		try {
			entrada = new BufferedReader(new FileReader(f));
			String[] linea;
			while (entrada.ready()) {
				linea = entrada.readLine().split(",");
				if (linea[0].equals(textField.getText()) && linea[1].equals(textField_1.getText())&& linea[2].equals(textField_2.getText())&& linea[3].equals(textField_3.getText())&& linea[4].equals(textField_4.getText())) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					System.out.println("Error: " + e);
				}

			}
		}

		return false;
	}
	public static void borraJugador() {
		String liniaDelete=textField.getText()+","+textField_1.getText()+","+textField_2.getText()+","+textField_3.getText()+","+textField_4.getText();
		try {
			if (!buscaUsuari(carregarDades())) {
				System.out.println("L'usuari no existeix");
			} else {
				File fitxerTemp = new File(carregarDades().getAbsolutePath() + ".temp");
				BufferedReader in = new BufferedReader(new FileReader(carregarDades()));
				PrintWriter pw = new PrintWriter(new FileWriter(fitxerTemp));
				String line = null;

				while ((line = in.readLine()) != null) {
					if (!line.trim().equals(liniaDelete)) {
						pw.print(line + "\r\n");
						pw.flush();
					}
				}
				pw.close();
				in.close();
				if (!carregarDades().delete()) {
					return;
				}
				if (!fitxerTemp.renameTo(carregarDades())) {
				}
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void initialize() {
		frmGestiDePersones = new JFrame();
		frmGestiDePersones.setBackground(Color.YELLOW);
		BorderLayout borderLayout = (BorderLayout) frmGestiDePersones.getContentPane().getLayout();
		borderLayout.setHgap(6);
		frmGestiDePersones.setTitle("Gesti\u00F3 de persones");
		
		JToolBar toolBar = new JToolBar();
		frmGestiDePersones.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnNou = new JButton("Nou");
		toolBar.add(btnNou);
		
		JButton btnEsborra = new JButton("Esborra");
		btnEsborra.setEnabled(false);
		
		toolBar.add(btnEsborra);
		
		JButton btnEdita = new JButton("Edita");
		btnEdita.setEnabled(false);
		toolBar.add(btnEdita);
		
		JButton btnPrimer = new JButton("Primer");
		btnPrimer.setEnabled(false);
		toolBar.add(btnPrimer);
		
		JButton btnDarrer = new JButton("Darrer");
		btnDarrer.setEnabled(false);
		toolBar.add(btnDarrer);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setEnabled(false);
		toolBar.add(btnAnterior);
		
		JButton btnSegent = new JButton("Seg\u00FCent");
		btnSegent.setEnabled(false);
		toolBar.add(btnSegent);
		
		JButton btnDesa = new JButton("Desa");
		btnDesa.setEnabled(false);
		toolBar.add(btnDesa);
		
		JButton btnCancella = new JButton("Cancel\u00B7la");
		btnCancella.setEnabled(false);
		toolBar.add(btnCancella);
		
		
		int numero=0;
		JLabel lblRegistre = new JLabel("Registre"+numero+"de"+numero );
		toolBar.add(lblRegistre);
		
		JPanel panel = new JPanel();
		frmGestiDePersones.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblDni = new JLabel("DNI");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel label = new JLabel("");
		
		JLabel label_1 = new JLabel("");
		
		JLabel label_2 = new JLabel("");
		
		JLabel label_3 = new JLabel("");
		
		JLabel lblNom = new JLabel("NOM");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel label_4 = new JLabel("");
		
		JLabel label_5 = new JLabel("");
		
		JLabel label_6 = new JLabel("");
		
		JLabel label_7 = new JLabel("");
		
		JLabel lblCognom = new JLabel("Cognom");
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		JLabel label_8 = new JLabel("");
		
		JLabel label_9 = new JLabel("");
		
		JLabel label_10 = new JLabel("");
		
		JLabel label_11 = new JLabel("");
		
		JLabel lblSegonCognom = new JLabel("Segon Cognom");
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		JLabel label_12 = new JLabel("");
		
		JLabel label_13 = new JLabel("");
		
		JLabel label_14 = new JLabel("");
		
		JLabel label_15 = new JLabel("");
		
		JLabel lblEdat = new JLabel("Edat");
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		btnNou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnDesa.setEnabled(true);
				btnCancella.setEnabled(true);
				textField.setEditable(true);
				textField_1.setEditable(true);
				textField_2.setEditable(true);
				textField_3.setEditable(true);
				textField_4.setEditable(true);
			}
		});
		btnDesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afegirPersona(carregarDades());
			}
		});
		btnEsborra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borraJugador();
			}
		});
		btnCancella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				netejarPersona();
			}
		});
		JLabel label_16 = new JLabel("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblEdat, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblSegonCognom, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblCognom, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNom, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDni, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
									.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
								.addComponent(label_8, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_12, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_16, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDni, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNom, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCognom, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSegonCognom, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEdat, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}
}
