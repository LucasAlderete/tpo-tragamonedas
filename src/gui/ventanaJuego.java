package gui;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import controller.Controlador;
import negocio.Comprobante;
import util.Strings;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class ventanaJuego extends JFrame implements ActionListener {

	private static final long serialVersionUID = 6208995886501749965L;

	private Container contenedor;

	int velocidad;
	Icon icono;
	JLabel lblCasilla1, lblCredito, lblComprobante, lblSlot1, lblSlot2, lblSlot3, lblEstado, lblMensaje, lblRecaudacion;
	JButton btnTirar, btnVerCredito, btnIngresarCredito, btnRetirarDinero;

	static int contador = 0;
	private JTextField txtFieldIngresarCredito;
	Timer timer;
	Timer timerStopper;

	List<String> frutas = List.of("frutilla", "banana", "guinda", "pera", "sandia", "uva");

	public ventanaJuego() {
		setTitle("Tragamonedas");
		setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		setFont(new Font("Baskerville Old Face", Font.PLAIN, 26));
		setSize(700, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.velocidad = 0;
		getContentPane().setLayout(null);

		btnTirar = new JButton("Tirar");
		btnTirar.setForeground(UIManager.getColor("TextArea.caretForeground"));
		btnTirar.setToolTipText("");
		btnTirar.setBounds(20, 422, 126, 73);
		getContentPane().add(btnTirar);
		btnTirar.addActionListener(this);

		btnIngresarCredito = new JButton("Ingresar Credito");
		btnIngresarCredito.addActionListener(this);

		btnIngresarCredito.setBounds(365, 422, 126, 73);
		getContentPane().add(btnIngresarCredito);

		btnRetirarDinero = new JButton("Retirar Dinero");
		btnRetirarDinero.addActionListener(this);

		btnRetirarDinero.setBounds(537, 422, 126, 73);
		getContentPane().add(btnRetirarDinero);

		JPanel panel = new JPanel();
		panel.setBounds(20, 58, 643, 146);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblSlot1 = new JLabel("");
		lblSlot1.setBounds(65, 27, 120, 108);
		panel.add(lblSlot1);

		lblSlot2 = new JLabel("");
		lblSlot2.setBounds(258, 27, 120, 108);
		panel.add(lblSlot2);

		lblSlot3 = new JLabel("");
		lblSlot3.setBounds(471, 27, 120, 108);
		panel.add(lblSlot3);

		lblCredito = new JLabel("Credito :     0.0");
		lblCredito.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredito.setBounds(179, 423, 126, 72);
		getContentPane().add(lblCredito);

		txtFieldIngresarCredito = new JTextField();
		txtFieldIngresarCredito.setHorizontalAlignment(SwingConstants.LEFT);
		txtFieldIngresarCredito.setBounds(388, 391, 118, 20);
		getContentPane().add(txtFieldIngresarCredito);
		txtFieldIngresarCredito.setColumns(10);

		JLabel lblNewLabel = new JLabel("Numero Ticket Credito:");
		lblNewLabel.setBounds(265, 385, 113, 14);
		getContentPane().add(lblNewLabel);

		lblComprobante = new JLabel("");
		lblComprobante.setBounds(20, 337, 595, 20);
		getContentPane().add(lblComprobante);

		lblEstado = new JLabel("");
		lblEstado.setBounds(20, 306, 595, 20);
		getContentPane().add(lblEstado);

		lblMensaje = new JLabel("");
		lblMensaje.setBounds(20, 261, 595, 20);
		getContentPane().add(lblMensaje);

		lblRecaudacion = new JLabel("");
		lblRecaudacion.setBounds(44, 215, 595, 20);
		getContentPane().add(lblRecaudacion);

	}

	public void obtenerDatosJuego() {

	}

	public void animar() {
		if (timerStopper != null && timerStopper.isRunning()) {
			timerStopper.stop();
		}
		timer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random r1 = new Random();
				Random r2 = new Random();
				Random r3 = new Random();
				int pos1 = r1.nextInt(5);
				int pos2 = r2.nextInt(5);
				int pos3 = r3.nextInt(5);

				ImageIcon image1 = new ImageIcon(getClass().getResource(frutas.get(pos1) + ".png"));
				ImageIcon image2 = new ImageIcon(getClass().getResource(frutas.get(pos2) + ".png"));
				ImageIcon image3 = new ImageIcon(getClass().getResource(frutas.get(pos3) + ".png"));

				ImageIcon imageIcon1 = new ImageIcon(image1.getImage().getScaledInstance(lblSlot1.getWidth(),
						lblSlot1.getHeight(), Image.SCALE_DEFAULT));
				ImageIcon imageIcon2 = new ImageIcon(image2.getImage().getScaledInstance(lblSlot1.getWidth(),
						lblSlot1.getHeight(), Image.SCALE_DEFAULT));
				ImageIcon imageIcon3 = new ImageIcon(image3.getImage().getScaledInstance(lblSlot1.getWidth(),
						lblSlot1.getHeight(), Image.SCALE_DEFAULT));

				lblSlot1.setIcon(imageIcon1);
				lblSlot2.setIcon(imageIcon2);
				lblSlot3.setIcon(imageIcon3);

			}
		});

		if(timer != null && !timer.isRunning()) {
			timer.start();	
		}

		timerStopper = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (timer.isRunning()) {
					timer.stop();
					getResultado();
					btnTirar.setEnabled(true);
				}

			}
		});
		

		timerStopper.start();

	}

	public void CargarCredito() {
		int retorno;
		try {
			retorno = Integer.parseInt(txtFieldIngresarCredito.getText());
			Controlador.getInstance().inicilizarMaquina();
			Controlador.getInstance().ingresarCredito(retorno);
			lblCredito.setText("credito: " + Controlador.getInstance().getCreditoMaquina());
		} catch (Exception e) {

		}

	}

	public void generarComprobante() {
		Comprobante comprobante = Controlador.getInstance().generarComprobante();
		lblComprobante.setText("su comprobante es : " + comprobante.getNumeroComprobante());
		lblCredito.setText("credito: " + Controlador.getInstance().getCreditoMaquina());
	}

	public void getResultado() {
		MaquinaView maquinaView = Controlador.getInstance().getMaquinaView();

		int pos1 = frutas.indexOf(maquinaView.getCombinacion().get(0).getFruta().getNombre().toLowerCase());
		int pos2 = frutas.indexOf(maquinaView.getCombinacion().get(1).getFruta().getNombre().toLowerCase());
		int pos3 = frutas.indexOf(maquinaView.getCombinacion().get(2).getFruta().getNombre().toLowerCase());

		ImageIcon image1 = new ImageIcon(getClass().getResource(frutas.get(pos1) + ".png"));
		ImageIcon image2 = new ImageIcon(getClass().getResource(frutas.get(pos2) + ".png"));
		ImageIcon image3 = new ImageIcon(getClass().getResource(frutas.get(pos3) + ".png"));

		ImageIcon imageIcon1 = new ImageIcon(
				image1.getImage().getScaledInstance(lblSlot1.getWidth(), lblSlot1.getHeight(), Image.SCALE_DEFAULT));
		ImageIcon imageIcon2 = new ImageIcon(
				image2.getImage().getScaledInstance(lblSlot1.getWidth(), lblSlot1.getHeight(), Image.SCALE_DEFAULT));
		ImageIcon imageIcon3 = new ImageIcon(
				image3.getImage().getScaledInstance(lblSlot1.getWidth(), lblSlot1.getHeight(), Image.SCALE_DEFAULT));

		lblSlot1.setIcon(imageIcon1);
		lblSlot2.setIcon(imageIcon2);
		lblSlot3.setIcon(imageIcon3);

		lblCredito.setText(String.valueOf(maquinaView.getCredito()));

		if (!Strings.isNullOrEmpty(maquinaView.getMsj())) {
			lblMensaje.setText("Mensaje: " + maquinaView.getMsj());
		}
		if (!Strings.isNullOrEmpty(maquinaView.getEstado())) {
			lblEstado.setText(maquinaView.getEstado());
		}
		
	}

	public void jugar() {
		btnTirar.setEnabled(false);
		Controlador.getInstance().inicilizarMaquina();
		Controlador.getInstance().jugar();
		
		if (Controlador.getInstance().sePuedeJugar()) {
			animar();	
		}

		MaquinaView maquinaView = Controlador.getInstance().getMaquinaView();

		lblCredito.setText(String.valueOf(maquinaView.getCredito()));

		if (!Strings.isNullOrEmpty(maquinaView.getMsj())) {
			lblMensaje.setText("Mensaje: " + maquinaView.getMsj());
		}
		if (!Strings.isNullOrEmpty(maquinaView.getEstado())) {
			lblEstado.setText("Estado jugada: " + maquinaView.getEstado());
		}

	}

	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource() == btnTirar) {
			jugar();
		}

		if (evento.getSource() == btnIngresarCredito) {
			CargarCredito();
		}

		if (evento.getSource() == btnRetirarDinero) {
			generarComprobante();
		}

	}
}