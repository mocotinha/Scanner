package br.edu.ifpb.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import br.edu.ifpb.controler.SistemaDeTelas;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class TelaPrincipal extends JFrame {

	

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
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		
		configurePLAF();
		setTitle("Gestão de Documentos - Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(400, 400));
		
		
		String linha = "pref, center:pref:grow";
		String coluna = "pref:grow,left:pref:grow";
		
		FormLayout form = new FormLayout(coluna,linha);
		FormDebugPanel formDebugPanel = new FormDebugPanel();
		PanelBuilder pb = new PanelBuilder(form,formDebugPanel);
		
		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu("Documento");
		final JMenuItem cadDoc = new JMenuItem("Cadastrar Documento");
		JMenuItem busDoc = new JMenuItem("Buscar Documentos");
		busDoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SistemaDeTelas.telaBusca();
				
			}
		});
		jm.add(cadDoc);
		jm.add(busDoc);
		jmb.add(jm);
		
		JMenu jm2 = new JMenu("Usuario");
		JMenuItem cadUsu = new JMenuItem("Cadastrar Usuario");
		cadUsu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SistemaDeTelas.telaCadastroUsuario();
				
			}
		});
		JMenuItem gerUsu = new JMenuItem("Gerenciar Usuarios");
		gerUsu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SistemaDeTelas.telaGerenciamentoUsuario();
				
			}
		});
		jm2.add(cadUsu);
		jm2.add(gerUsu);
		jmb.add(jm2);
		
		JMenu jm3 = new JMenu("Sair");
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SistemaDeTelas.telaLogin(tela());
				
			}
		});
		jm3.add(sair);
		jmb.add(jm3);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(TelaLogin.class.getResource("/images/icon/logo.png")));
		logo.setBounds(107, 49, 46, 14);
		
		
		pb.add(jmb,CC.xyw(1, 1,2));
		pb.add(logo,CC.xy(2, 2));
		
		cadDoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SistemaDeTelas.proximaTela(classe(),cadDoc.getText());
				
			}

			
		});
		
		getContentPane().add(pb.getPanel());
	}
	
	protected JFrame tela() {
		
		return this;
	}

	private JFrame classe() {
		
		return this;
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
