package br.edu.ifpb.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class TelaCadastroDeUsuario extends JFrame {

	private JTextField nome;
	private JTextField login;
	private JPasswordField pass;
	private JPasswordField pass2;
	private JComboBox<String> tipo;
	private JLabel info;
	private static final long serialVersionUID = 1L;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroDeUsuario frame = new TelaCadastroDeUsuario();
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
	public TelaCadastroDeUsuario() {
		//Setar o Look and feel
		configurePLAF();
		setTitle("Gestão de Documentos - Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(400, 400));
		
		
		String linha = "pref:grow,pref,10dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,20dlu,pref,5dlu,pref,pref:grow";
		String coluna = "pref:grow,pref,5dlu,80dlu,pref:grow";
		
		FormLayout form = new FormLayout(coluna,linha);
		
	
		FormDebugPanel formDebugPanel = new FormDebugPanel();
		PanelBuilder pb = new PanelBuilder(form,formDebugPanel);
		
		pb.addLabel("Cadastro de Usuários",CC.xyw(2, 2,4));
		pb.addLabel("Nome:", CC.xy(2, 4));
		pb.addLabel("Login:", CC.xy(2, 6));
		pb.addLabel("Senha:", CC.xy(2, 8));
		pb.addLabel("Confirmar Senha:", CC.xy(2, 10));
		pb.addLabel("Tipo de Acesso:", CC.xy(2, 12));
		
		nome = new JTextField("");
		login = new JTextField("");
		pass = new JPasswordField();
		pass2 = new JPasswordField();
		String [] tiposDeAcesso = {"Administrador","Visualizador"};
		tipo = new JComboBox<String>(tiposDeAcesso);
		info = new JLabel("");
		info.setForeground(Color.RED);
		JButton cadastro = new JButton("Cadastrar");
		cadastro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Entrei");
				if(login.getText().equals("")||nome.getText().equals("")||new String(pass.getPassword()).equals("")){
					info.setText("Campo vazio, todos os campos devem ser preenchidos.");
				}else if(!new String(pass.getPassword()).equals(new String(pass2.getPassword()))){
					info.setText("As senhas devem ser iguais");
				}else{
					//verificar existencia do usuario, pois não deve haver usuarios com logins iguais 
					Sistema.cadastraUsuario(nome.getText(), login.getText(), new String(pass.getPassword()),tipo.getSelectedIndex());
					JOptionPane.showMessageDialog(null, "Usuário Cadastrado com sucesso!");
					limpar();
				}
				
			}
		});
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpar();
				SistemaDeTelas.telaPrincipal();
				
			}
		});
		
		
		pb.add(nome,CC.xy(4, 4));
		pb.add(login,CC.xy(4, 6));
		pb.add(pass,CC.xy(4, 8));
		pb.add(pass2,CC.xy(4, 10));
		pb.add(tipo,CC.xy(4, 12));
		pb.add(cadastro,CC.xy(2, 14));
		pb.add(voltar,CC.xy(4, 14));
		pb.add(info,CC.xyw(2,16,4));
		
		getContentPane().add(pb.getPanel());
	}
	
	protected void limpar() {
		nome.setText("");
		login.setText("");
		info.setText("");
		pass.setText("");
		pass2.setText("");
		tipo.setSelectedIndex(0);
		
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

}
