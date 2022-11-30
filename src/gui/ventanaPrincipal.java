package gui;

import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;

public class ventanaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = -545986197425389375L;
	private Container contenedor;
	private ventanaPrincipal miVentanaPrincipal;
	JButton btnJugar, btnCaja, btnSalir;
	JLabel Titulo;

	public ventanaPrincipal() {
		setSize(700, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iniciarComponentes();

	}

	private void iniciarComponentes() {
		contenedor = getContentPane();
		contenedor.setLayout(null);

		btnJugar = new JButton();
		btnJugar.setText("Jugar");
		btnJugar.setBounds(20, 150, 200, 25);
		btnJugar.addActionListener(this);

		btnCaja = new JButton();
		btnCaja.setText("Caja");
		btnCaja.setBounds(241, 150, 200, 25);
		btnCaja.addActionListener(this);

		btnSalir = new JButton();
		btnSalir.setText("Salir");
		btnSalir.setBounds(463, 150, 200, 25);
		btnSalir.addActionListener(this);

		Titulo = new JLabel();
		Titulo.setFont(getFont());
		Titulo.setText("TPO Tragamonedas ");
		Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		Titulo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("ToolBar.dockingForeground"),
				UIManager.getColor("activeCaption")));
		Titulo.setBounds(97, 37, 500, 40);

		contenedor.add(Titulo);
		contenedor.add(btnCaja);
		contenedor.add(btnJugar);
		contenedor.add(btnSalir);

	}

	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == btnJugar) {

			ventanaJuego miVentanaJuego = new ventanaJuego();
			miVentanaJuego.setVisible(true);

		}
		
		if(evento.getSource()== btnCaja) {
			ventanaCaja miVentanaCaja = new ventanaCaja();
			miVentanaCaja.setVisible(true);
		}

	}

}
