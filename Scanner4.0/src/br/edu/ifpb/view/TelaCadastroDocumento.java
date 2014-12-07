package br.edu.ifpb.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class TelaCadastroDocumento extends JFrame {

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
					TelaCadastroDocumento frame = new TelaCadastroDocumento();
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
	public TelaCadastroDocumento() {
		configurePLAF();
		setTitle("Gestão de Documentos - Cadastro de Documentos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(400, 400));
		
		
		String linha = "pref, pref:grow";
		String coluna = "fill:pref:grow";
		
		FormLayout form = new FormLayout(coluna,linha);
		FormDebugPanel formDebugPanel = new FormDebugPanel();
		PanelBuilder pb = new PanelBuilder(form,formDebugPanel);
		
		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu("Documento");
		JMenuItem cadDoc = new JMenuItem("Cadastrar Documento");
		JMenuItem busDoc = new JMenuItem("Buscar Documentos");
		jm.add(cadDoc);
		jm.add(busDoc);
		jmb.add(jm);
		
		JMenu jm2 = new JMenu("Usuario");
		JMenuItem cadUsu = new JMenuItem("Cadastrar Usuario");
		JMenuItem gerUsu = new JMenuItem("Gerenciar Usuarios");
		jm2.add(cadUsu);
		jm2.add(gerUsu);
		jmb.add(jm2);
		
		JMenu jm3 = new JMenu("Sair");
		JMenuItem sair = new JMenuItem("Sair");
		jm3.add(sair);
		jmb.add(jm3);
		
		JPanel cadastro = new JPanel();
		
		painelCadastroDocumentos(cadastro);
		
		
		pb.add(jmb,CC.xy(1, 1));
		pb.add(cadastro,CC.xy(1, 2));
		
		getContentPane().add(pb.getPanel());
	}
	
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	private void painelCadastroDocumentos(JPanel cadastro) {
		
		String linha = "pref:grow,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,"
				+ "5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,"
				+ "5dlu,50dlu,5dlu,pref,5dlu:grow";
		
		String coluna = "pref:grow,10dlu,pref,10dlu,pref,10dlu,pref,10dlu,pref,10dlu:grow";
		
		FormLayout form = new FormLayout(coluna,linha);
		FormDebugPanel formDebugPanel = new FormDebugPanel();
		PanelBuilder pb = new PanelBuilder(form,formDebugPanel);
		pb.addLabel("Cadasto de Documentos", CC.xyw(3, 1,3));
		
		pb.addLabel("Dados da Instituição:",CC.xy(1, 3));
		pb.addLabel("Nome da Instituição:",CC.xy(1, 5));
		JTextField inst = new JTextField();
		pb.add(inst,CC.xy(3, 5));
		JButton bIns = new JButton("Buscar");
		pb.add(bIns,CC.xy(5, 5));
		pb.addLabel("OK",CC.xy(7, 5));
		
		pb.addLabel("Dados do Aluno:",CC.xy(1, 7));
		pb.addLabel("Nome do Aluno:",CC.xy(1, 9));
		JTextField alu = new JTextField();
		pb.add(alu,CC.xy(3, 9));
		JButton bAlu = new JButton("Buscar");
		pb.add(bAlu,CC.xy(5, 9));
		pb.addLabel("OK",CC.xy(7, 9));
		
		pb.addLabel("Dados do Curso:",CC.xy(1, 11));
		pb.addLabel("Curso:",CC.xy(1, 13));
		JTextField cur = new JTextField();
		pb.add(cur,CC.xy(3, 13));
		JButton bCur = new JButton("Buscar");
		pb.add(bCur,CC.xy(5, 15));
		pb.addLabel("OK",CC.xy(7, 15));
		
		pb.addLabel("Dados do Documento:",CC.xy(1, 15));
		pb.addLabel("Tipo:",CC.xy(1, 17));
		JTextField tip = new JTextField();
		pb.add(tip,CC.xy(3, 17));
		
		pb.addLabel("Título:",CC.xy(1, 19));
		JTextField tit = new JTextField();
		pb.add(tit,CC.xy(3, 19));
		
		pb.addLabel("Tempo de Guarda:",CC.xy(1, 21));
		JTextField temp = new JTextField();
		pb.add(temp,CC.xy(3, 21));
		
		pb.addLabel("Imagem:",CC.xy(1, 23));
		JButton dig = new JButton("Digitaliza Documento");
		pb.add(dig,CC.xy(3, 23));
		
		pb.addLabel("Lista de Documentos:",CC.xy(1, 25));
		JScrollPane jsp = new JScrollPane();
		JList<String> jl = new JList<>();
		
		jl.setValueIsAdjusting(true);
		jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	



	
	
		jl.setModel(new AbstractListModel() {
			String[] values = new String[] {"Nenhum Documento Digitalizado"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		jsp.setViewportView(jl);
		
		
		jsp.add(jl);
		pb.add(jsp,CC.xy(3, 25));
		
		JButton salvar = new JButton("Salvar");
		pb.add(salvar,CC.xy(1, 27));
		
		JButton limpar = new JButton("Limpar");

		pb.add(limpar,CC.xy(3, 27));
		
		JButton cancelar = new JButton("Cancelar");
		pb.add(cancelar,CC.xy(5, 27));
		
		pb.addLabel("Nenhuma Imagem Selecionada",CC.xywh(9, 5, 1, 20));
		
		pb.addLabel("Situção do Scanner",CC.xy(9, 27));
		
		cadastro.add(pb.getPanel());
		
		
		
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
