package br.edu.ifpb.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.controler.SistemaDeTelas;
import br.edu.ifpb.model.table.UsuarioTableModel;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class TelaUsuario extends JFrame {

	private JTable table;
	private static final long serialVersionUID = 1L;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario frame = new TelaUsuario();
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
	public TelaUsuario(){
		//Setar o Look and feel
		configurePLAF();
		setTitle("Gestão de Documentos - Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(400, 400));
		
		//dlu / (center,top,right,left,fill):(pref,xdlu):grow -Fill é o comportamento dele dentro do td, pref: td ao conteudo, grow -cresce junto com a tela
		
		String linha = "pref:grow,pref,10dlu,pref,10dlu,pref, pref:grow";
		String coluna = "pref:grow,pref,50dlu,pref,pref:grow";
		
		FormLayout form = new FormLayout(coluna,linha);

		FormDebugPanel formDebugPanel = new FormDebugPanel();
		PanelBuilder pb = new PanelBuilder(form,formDebugPanel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 424, 142);

		table = new JTable();
		table.setModel(new UsuarioTableModel(Sistema.getUsuarios()));
		scrollPane.setViewportView(table);
		
		JButton remover = new JButton("Remover");
		remover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Sistema.removeUsuario(((UsuarioTableModel) table.getModel()).get(table.getSelectedRow()));
				JOptionPane.showMessageDialog(null, "Usuario removido com sucesso(Deve-se verificar vinculos com documentos)");
				
			}
		});
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SistemaDeTelas.telaPrincipal();
				
			}
		});
		
		
		pb.addLabel("Gerenciamento de Usuarios",CC.xyw(2, 2,3));
		pb.add(scrollPane,CC.xyw(2, 4,3));
		pb.add(remover,CC.xy(2,6));
		pb.add(voltar,CC.xy(4,6));
		
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
