package br.edu.ifpb.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.controler.SistemaDeTelas;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class TelaLogin extends JFrame {

	private JLabel info;
	private JTextField login;
	private JPasswordField password;
	

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
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		configurePLAF();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(0 == JOptionPane.showConfirmDialog(null,"Deseja realmente sair do sitema?", "Sair", JOptionPane.YES_NO_OPTION)){
					System.exit(0);
				}
			}
		});
		setTitle("Gestão de Documentos - Login");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(400, 400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(400, 400));
		
		
		
		
		String linha = "fill:10dlu:grow,pref,20dlu,pref,15dlu,pref,2dlu,pref,20dlu,pref,pref,pref,fill:pref:grow,pref";
		String coluna = "fill:pref:grow,right:pref,center:8dlu,90dlu,80dlu,fill:pref:grow";
		FormLayout form = new FormLayout(coluna,linha);
		
		FormDebugPanel formDebugPanel = new FormDebugPanel();
		PanelBuilder pb = new PanelBuilder(form,formDebugPanel);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(TelaLogin.class.getResource("/images/icon/logo.png")));
		logo.setBounds(107, 49, 46, 14);
		pb.add(logo,CC.xyw(2, 2,5));
		pb.addLabel("Gestão de Documentos",CC.xyw(3, 4,3));
		pb.addLabel("Login:",CC.xy(2, 6));
		pb.addLabel("Passoword:",CC.xy(2, 8));
		JButton btnLogin = new JButton("Login");
		pb.add(btnLogin,CC.xy(2, 10));
		JButton btnRecuperarSenha = new JButton("Esqueceu Senha?");
		pb.add(btnRecuperarSenha,CC.xy(4, 10));
		login = new JTextField();
		pb.add(login,CC.xy(4, 6));
		password = new JPasswordField();
		pb.add(password,CC.xy(4, 8));
		info = new JLabel("");
	    info.setForeground(Color.RED);
		pb.add(info,CC.xyw(2,11,3));
		info = new JLabel(" ");
		pb.add(info, CC.xyw(2,12,5));
		pb.addLabel("Desenvolvido por Jozias Rolim & Geferson Ribeiro, com orientação do Prof. Dr. Fausto Veras Ayres",CC.xyw(2,14,5));
		
		btnRecuperarSenha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				info.setForeground(Color.BLUE);
				info.setText("Contate o administrador do Sistema: admin@gda-ifpb.edu.br");
				
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Sistema.login(login.getText(),password.getPassword())){
					//TODO Tela Seguinte: 
					SistemaDeTelas.proximaTela(classe());
					limpar();
				}else{
					info.setForeground(Color.RED);
					info.setText("Login e/ou senha inválidos");
				}
				
			}
		});
		
		
		getContentPane().add(pb.getPanel());
		
		
	}
	
	protected void limpar() {
		login.setText("");
		password.setText("");
		info.setText("");
		
	}

	private static void configurePLAF() {
		  try {
			  UIManager.setLookAndFeel(com.jgoodies.looks.windows.WindowsLookAndFeel.class.getName());
			  //UIManager.setLookAndFeel(new Plastic3DLookAndFeel()); 
		   	  com.jgoodies.looks.Options.setPopupDropShadowEnabled(true);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	
	private JFrame classe(){
		return this;
	}
}
