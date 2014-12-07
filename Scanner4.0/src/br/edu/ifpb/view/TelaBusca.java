package br.edu.ifpb.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.model.table.DocumentoDigitalTableModel;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class TelaBusca extends JFrame {

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
					TelaBusca frame = new TelaBusca();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TelaBusca() {
		
		configurePLAF();
		setTitle("Gestão de Documentos - Busca");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(400, 400));
		
		String linha = "pref:grow,pref,10dlu,pref,10dlu,pref,pref:grow";//7 linhas
		String coluna = "pref:grow,pref,5dlu,150dlu,5dlu,pref,pref,5dlu,pref,5dlu,pref,pref:grow";//12 colunas
		
		
		
		FormLayout form = new FormLayout(coluna,linha);
		
		
		FormDebugPanel formDebugPanel = new FormDebugPanel();
		PanelBuilder pb = new PanelBuilder(form,formDebugPanel);
		JTextField tfBusca = new JTextField();
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nome do Aluno", "CPF do Aluno", "RG do Aluno"}));
		comboBox.setBounds(368, 52, 28, 20);
		
		JScrollPane scrollPane = new JScrollPane();
		final JTable table = new JTable();
		table.setModel(new DocumentoDigitalTableModel(Sistema.findAllDocumentos()));
		scrollPane.setViewportView(table);
		
		JButton pesquisar = new JButton("Pesquisar");
		JButton editar = new JButton("Editar");
		editar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.editarDocumentoDigital(((DocumentoDigitalTableModel)table.getModel()).get(table.getSelectedRow()));
				
			}
		});
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				
			}
		});
		
		
		pb.addLabel("Pesquisar: ",CC.xy(2, 2));
		pb.add(tfBusca,CC.xy(4, 2));
		pb.addLabel("Opçao de Busca: ",CC.xy(6, 2));
		pb.add(comboBox,CC.xy(9, 2));
		pb.add(pesquisar,CC.xy(11, 2));
		pb.add(scrollPane,CC.xyw(2, 4, 10));
		pb.add(editar,CC.xyw(2, 6,2));
		pb.add(voltar,CC.xyw(6, 6,2));
		
		
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