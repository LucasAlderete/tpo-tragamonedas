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

	JLabel lblIngreseMonto, lblNumeroComprobante, lblNumeroTicket, lblComprobante;
	JTextField txtMontoCredito, txtNumComprobante;
	JButton btnComprarCredito, btnCobrarComprobante;
	

	public ventanaCaja() {

		setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		setFont(new Font("Baskerville Old Face", Font.PLAIN, 26));
		setSize(700, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(null);

		lblIngreseMonto = new JLabel("Ingrese monto credito");
		lblIngreseMonto.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseMonto.setBounds(0, 100, 350, 14);
		getContentPane().add(lblIngreseMonto);

		txtMontoCredito = new JTextField();
		txtMontoCredito.setBounds(75, 125, 200, 20);
		getContentPane().add(txtMontoCredito);
		txtMontoCredito.setColumns(10);

		btnComprarCredito = new JButton("Comprar");
		btnComprarCredito.setBounds(75, 155, 200, 23);
		getContentPane().add(btnComprarCredito);
		btnComprarCredito.addActionListener(this);

		lblNumeroComprobante = new JLabel("Ingrese Nº de comprobante");
		lblNumeroComprobante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroComprobante.setBounds(350, 100, 350, 14);
		getContentPane().add(lblNumeroComprobante);

		txtNumComprobante = new JTextField();
		txtNumComprobante.setBounds(425, 125, 200, 20);
		getContentPane().add(txtNumComprobante);
		txtNumComprobante.setColumns(10);

		btnCobrarComprobante = new JButton("Retirar dinero");
		btnCobrarComprobante.setBounds(425, 155, 200, 20);
		getContentPane().add(btnCobrarComprobante);
		btnCobrarComprobante.addActionListener(this);

		JLabel lblTitulo = new JLabel("Caja");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 24, 700, 20);
		getContentPane().add(lblTitulo);

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
		lblNumeroTicket.setText("Nº Ticket: " + ticket.getNumeroTicket());
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
