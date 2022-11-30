package gui;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

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
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import controaldor.Controlador;
import view.AvionView;
import view.GloboView;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;


public class ventanaJuego extends JFrame implements ActionListener {


	private static final long serialVersionUID = 6208995886501749965L;
		
	private Container contenedor;
	JPanel panel1 ;
	JPanel panel2;
	JPanel panel3;
	JPanel relleno3;
	JPanel relleno4;
	Timer timer;
	TimerTask tarea;
	int velocidad ;
	Icon icono; 
	JLabel lblCasilla1;
	JButton btnTirar,btnVerCredito,btnIngresarCredito,btnRetirarDinero;

	static int contador = 0;
	private JTextField textField;
	
	public ventanaJuego() {
		setTitle("\t\t\t\tTPO-IOO\t\tTRAGAMONEDAS SLOT MACHINE");
		setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		setFont(new Font("Baskerville Old Face", Font.PLAIN, 26));
		setSize(700,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.velocidad =0;
		this.timer= new Timer();
		getContentPane().setLayout(null);
		
		JButton btnTirar = new JButton("Tirar");
		btnTirar.setForeground(UIManager.getColor("Button.background"));
		btnTirar.setToolTipText("");
		btnTirar.setBounds(20, 422, 126, 73);
		getContentPane().add(btnTirar);
		btnTirar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			
			};	});
	
		JButton btnVerCredito = new JButton("Ver Credito");
		btnVerCredito.setBounds(190, 422, 126, 73);
		getContentPane().add(btnVerCredito);
		
		JButton btnIngresarCredito = new JButton("Ingresar Credito");
		btnIngresarCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIngresarCredito.setBounds(365, 422, 126, 73);
		getContentPane().add(btnIngresarCredito);
		
		JButton btnRetirarDinero = new JButton("Retirar Dinero");
		btnRetirarDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRetirarDinero.setBounds(537, 422, 126, 73);
		getContentPane().add(btnRetirarDinero);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 58, 643, 146);
		getContentPane().add(panel);
		panel.setLayout(null);
		
	
		JButton lblCasilla1 = new JButton();
		lblCasilla1.addActionListener(this );
		lblCasilla1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCasilla1.setToolTipText("hola");
		lblCasilla1.setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		lblCasilla1.setText("hola como va\r\n");
		lblCasilla1.setBackground(UIManager.getColor("ToolBar.dockingForeground"));
		lblCasilla1.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\tpo-tragamonedas\\media\\cereza.jpeg"));
		lblCasilla1.setBounds(57, 26, 122, 84);
		panel.add(lblCasilla1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(UIManager.getColor("List.selectionBackground"));
		lblNewLabel.setBackground(UIManager.getColor("List.selectionBackground"));
		lblNewLabel.setBounds(254, 26, 122, 84);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(461, 26, 122, 84);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(342, 306, 192, 98);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 24, 172, 63);
		textField.setForeground(new Color(0, 0, 0));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(textField);
		textField.setBackground(UIManager.getColor("TextField.darkShadow"));
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(173, 322, 149, 85);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 16, 118, 58);
		panel_2.add(textArea);
		textArea.setBackground(UIManager.getColor("ComboBox.foreground"));
	
		
		/*moverFoto(){
			timer = new Timer(20, new ActionListener() {
		*}
			
			@Override
		*	public void actionPerformed(ActionEvent e) {
		*		Controlador.getInstancia().moverGlobo();
		*		GloboView auxGlobo = Controlador.getInstancia().getGlobo();
		*		lblGlobo.setBounds(auxGlobo.getX(), auxGlobo.getY(), 100, 100);
			}
		}/);
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent key) {
				//JOptionPane.showMessageDialog(null, "presionada " + key.getKeyCode() );
				Controlador.getInstancia().moverAvion(key.getKeyCode());
				AvionView auxAvion = Controlador.getInstancia().getAvion();
				lblAvion.setBounds(auxAvion.getX(), auxAvion.getY(), 100, 100);
			}
		});*/
		
	}
	
	public void  obtenerDatosJuego() {
		
	}
	
	
	public void moverFoto() {
		 this.tarea = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("se completo");
				switch(contador) {
				case 1: 
					contador = 1;
					icono = new ImageIcon(getClass().getResource("/media/cereza.jpeg"));
					lblCasilla1.setIcon(icono);
				break;
				case 2: 
					contador = 2;
					icono = new ImageIcon(getClass().getResource("/media/frutilla.jpeg"));
					lblCasilla1.setIcon(icono);
				break;
				case 3:
					contador= 3;
					icono = new ImageIcon(getClass().getResource("/media/pera.png"));
					lblCasilla1.setIcon(icono);
				break;	
				}
				
				
			}};
		
			timer.scheduleAtFixedRate(tarea,0, 0);
	}
		
	public void mostrarResultadoFotos() {
		
		
		
	}
	
	public void actionPerformed(ActionEvent evento) {
		
		if (evento.getSource()== btnTirar)
		{	mostrarResultadoFotos();
			moverFoto();
	
		}		
	}
}

