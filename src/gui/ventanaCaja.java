package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controller.Controlador;
import negocio.Ticket;

public class ventanaCaja extends JFrame implements ActionListener {

	JLabel lblIngreseMonto, lblIngreseNumTicket, lblNumeroTicket, lblComprobante;
	JTextField txtMontoCredito, txtNumComprobante;
	JButton btnComprarCredito, btnCobrarComprobante;
	

	public ventanaCaja() {

		setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		setFont(new Font("Baskerville Old Face", Font.PLAIN, 26));
		setSize(700, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(null);

		lblIngreseMonto = new JLabel("Ingrese Monto Credito :");
		lblIngreseMonto.setBounds(41, 100, 130, 14);
		getContentPane().add(lblIngreseMonto);

		txtMontoCredito = new JTextField();
		txtMontoCredito.setBounds(41, 125, 123, 20);
		getContentPane().add(txtMontoCredito);
		txtMontoCredito.setColumns(10);

		btnComprarCredito = new JButton("Comprar Credito");
		btnComprarCredito.setBounds(41, 156, 123, 23);
		getContentPane().add(btnComprarCredito);
		btnComprarCredito.addActionListener(this);

		lblIngreseNumTicket = new JLabel("Ingrese Numero De Ticket:");
		lblIngreseNumTicket.setBounds(285, 100, 137, 14);
		getContentPane().add(lblIngreseNumTicket);

		txtNumComprobante = new JTextField();
		txtNumComprobante.setBounds(290, 125, 113, 20);
		getContentPane().add(txtNumComprobante);
		txtNumComprobante.setColumns(10);

		btnCobrarComprobante = new JButton("Cobrar Ticket");
		btnCobrarComprobante.setBounds(290, 156, 113, 23);
		getContentPane().add(btnCobrarComprobante);
		btnCobrarComprobante.addActionListener(this);

		JLabel lblNewLabel_2 = new JLabel("CAJA --Tragamonedas");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(97, 24, 241, 20);
		getContentPane().add(lblNewLabel_2);

		lblNumeroTicket = new JLabel("");
		lblNumeroTicket.setBounds(30, 190, 161, 60);
		getContentPane().add(lblNumeroTicket);

		lblComprobante = new JLabel("");
		lblComprobante.setBounds(255, 190, 280, 60);
		getContentPane().add(lblComprobante);
	}

	public void generarTicket() {
		int retorno;
		
		retorno = Integer.parseInt(txtMontoCredito.getText());

		Ticket ticket = Controlador.getInstance().generarTicket(retorno);
		lblNumeroTicket.setText("numero de ticket: " + ticket.getNumeroTicket());
	}

	public void retirarDinero() {
		String retorno;
		retorno = txtNumComprobante.getText();
		Controlador.getInstance().retirarDinero(retorno);
		lblComprobante.setText("Dinero Cobrado - ticket usado!");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		// TODO Auto-generated method stub

		if (evento.getSource() == btnComprarCredito) {
			generarTicket();
		}
		
		if (evento.getSource()== btnCobrarComprobante) {
			retirarDinero();
		}

	}
}
