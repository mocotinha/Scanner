package br.edu.ifpb.view;



import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.FormLayout;

public class ModeloTelaPura extends JFrame {

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
					ModeloTelaPura frame = new ModeloTelaPura();
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
	public ModeloTelaPura() {
		//Setar o Look and feel
		configurePLAF();
		setTitle("Gestão de Documentos - Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(400, 400));
		
		//dlu / (center,top,right,left,fill):(pref,xdlu):grow -Fill é o comportamento dele dentro do td, pref: td ao conteudo, grow -cresce junto com a tela
		
		String linha = "";
		String coluna = "";
		
		FormLayout form = new FormLayout(coluna,linha);
		
		/*
		 * Para Teste de criação
		 * 
		 * import com.jgoodies.forms.debug.FormDebugPanel;
		 * FormDebugPanel formDebugPanel = new FormDebugPanel();
		 * PanelBuilder pb = new PanelBuilder(form,formDebugPanel);
		 */
		
		FormDebugPanel formDebugPanel = new FormDebugPanel();
		PanelBuilder pb = new PanelBuilder(form,formDebugPanel);
		
		//TODO
		/*
		 * Adicionar os componentes no pb
		 */
		
		getContentPane().add(pb.getPanel());
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
