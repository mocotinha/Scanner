package br.edu.ifpb.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.controler.SistemaDeTelas;
import br.edu.ifpb.model.DocumentoDigital;
import br.edu.ifpb.model.Imagem;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;



@SuppressWarnings("serial")
public class TelaCadastroDeDocumento extends JFrame{
	
	private JTextField inst;
	private JLabel confInst;
	private JTextField alu;
	private JLabel confAlu;
	private JLabel confCur;
	private JTextField cur;
	private JTextField tit;
	private JTextField temp;
	@SuppressWarnings("rawtypes")
	private JComboBox tip;
	@SuppressWarnings("rawtypes")
	private JList jl;
	private final JLabel info;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroDeDocumento frame = new TelaCadastroDeDocumento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaCadastroDeDocumento(){
		Sistema.inicializaCadastroDeDocumentos();
		setType(Type.NORMAL);
		configurePLAF();
		setTitle("Gestão de Documentos - Cadastro de Documentos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(400, 400));
		toFront();
		
		String linha = "pref:grow,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,"
				+ "5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,"
				+ "5dlu,pref:grow,5dlu,pref,pref,5dlu:grow";
		
		String coluna = "pref:grow,pref,10dlu,pref,10dlu,pref,10dlu,pref,10dlu,pref,10dlu:grow";
		
		FormLayout form = new FormLayout(coluna,linha);
		FormDebugPanel formDebugPanel = new FormDebugPanel();
		PanelBuilder pb = new PanelBuilder(form,formDebugPanel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Item 1", "Item 2", "Item 3"}));
		comboBox.setBounds(368, 52, 28, 20);
		
		pb.addLabel("Cadasto de Documentos", CC.xyw(3, 1,3));
		
		pb.addLabel("Dados da Instituição:",CC.xy(2, 3));
		pb.addLabel("Nome da Instituição:",CC.xy(2, 5));
		inst = new JTextField("");
		inst.setEnabled(false);
		
		pb.add(inst,CC.xy(4, 5));
		final JButton bIns = new JButton("Buscar Instituição");
		pb.add(bIns,CC.xy(6, 5));
		confInst = new JLabel();
		confInst.setIcon(new ImageIcon(TelaCadastroDeDocumento.class.getResource("/images/icon/warning.png")));
		pb.add(confInst,CC.xy(8, 5));
		
		bIns.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SistemaDeTelas.proximaTela(classe(),bIns.getText());
				
			}

			
		});
			
		
		
		pb.addLabel("Dados do Aluno:",CC.xy(2, 7));
		pb.addLabel("Nome do Aluno:",CC.xy(2, 9));
		alu = new JTextField(" ");
		alu.setEnabled(false);
		pb.add(alu,CC.xy(4, 9));
		final JButton bAlu = new JButton("Buscar Aluno");
		bAlu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SistemaDeTelas.proximaTela(classe(),bAlu.getText());
				
			}
		});
		pb.add(bAlu,CC.xy(6, 9));
		
		confAlu = new JLabel();
		confAlu.setIcon(new ImageIcon(TelaCadastroDeDocumento.class.getResource("/images/icon/warning.png")));
		pb.add(confAlu,CC.xy(8, 9));
		
		
		pb.addLabel("Dados do Curso:",CC.xy(2, 11));
		pb.addLabel("Curso:",CC.xy(2, 13));
		cur = new JTextField(" ");
		cur.setEnabled(false);
		pb.add(cur,CC.xy(4, 13));
		final JButton bCur = new JButton("Buscar Curso");
		
		bCur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(alu.getText().equals(" ")){
					info.setText("Selecione um Aluno antes de selecionar um curso!");
				}else{
//					Sistema.atualizaCursos(((DocumentoDigital)Sistema.getDados("documentoAtual")).getMetadados().getAluno().getId());
					SistemaDeTelas.proximaTela(classe(), bCur.getText());
				}
				
			}
		});
		pb.add(bCur,CC.xy(6, 13));
		
		confCur = new JLabel();
		confCur.setIcon(new ImageIcon(TelaCadastroDeDocumento.class.getResource("/images/icon/warning.png")));
		pb.add(confCur,CC.xy(8, 13));
		
		pb.addLabel("Dados do Documento:",CC.xy(2, 15));
		pb.addLabel("Tipo:",CC.xy(2, 17));
		String[] tiposDeDocumentos = { "Documentos Pessoal", "Documentos Acadêmicos" };
		tip = new JComboBox(tiposDeDocumentos);
		tip.setSelectedIndex(-1);
		pb.add(tip,CC.xy(4, 17));
		
		pb.addLabel("Título:",CC.xy(2, 19));
		tit = new JTextField("");
		pb.add(tit,CC.xy(4, 19));
		
		pb.addLabel("Tempo de Guarda:",CC.xy(2, 21));
		
		temp = new JTextField("");
		pb.add(temp,CC.xy(4, 21));
		
		pb.addLabel("Imagem:",CC.xy(2, 23));
		
		
		pb.addLabel("Lista de Documentos:",CC.xy(2, 25));
		//String[] values = new String[] {"Nenhum Documento Digitalizado"};
		//SelectionInList selectionInList = new SelectionInList(values);
		//jl = new JList();
		//Bindings.bind(jl, selectionInList);
	
		
		jl = new JList();
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
		
		final JScrollPane jsp = new JScrollPane(jl);
		jsp.add(jl);
		jsp.setViewportView(jl);
		
		
		
		pb.add(jsp,CC.xy(4, 25));
		
		JButton salvar = new JButton("Salvar");
		pb.add(salvar,CC.xy(2, 27));
		
		salvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (inst.getText().equals(" ")){ 
					info.setText("Você deve selecionar uma Instituição!");
				}else if(alu.getText().equals(" ")){
					info.setText("Você deve selecionar um Aluno!");
				}else if(cur.getText().equals(" ")){
					info.setText("Você deve selecionar um Curso!");
				}else if(tit.getText().equals("")){
					info.setText("Você deve digitar um titulo!");
				}else if(temp.getText().equals("")){
					info.setText("Você deve digitar o tempo de guarda!");
				}else{
//					Sistema.persiteDocumento(tit.getText(),temp.getText(),tip.getSelectedIndex());
					JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
					limpar();
				}
			}
		});
		
		JButton limpar = new JButton("Limpar");
		limpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpar();
				
			}
		});
		pb.add(limpar,CC.xy(4, 27));
		
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				SistemaDeTelas.telaPrincipal();
				
			}
		});
		pb.add(voltar,CC.xy(6, 27));
		
		final JLabel imgInfo = new JLabel("Nenhuma Imagem Selecionada");
		pb.add(imgInfo,CC.xywh(10, 5, 1, 20));
		
		pb.addLabel("Situção do Scanner",CC.xy(10, 27));
		
		info = new JLabel(" ");
		info.setForeground(Color.RED);
		pb.add(info,CC.xyw(2,28,4));
		
		JButton dig = new JButton("Digitaliza Documento");
		dig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validaParaDigitalizacao()){
					Imagem img = Sistema.digitalizaImagem();
					Sistema.adicionaImagem(tip.getSelectedIndex(),tit.getText(),img);
					int qnt = Sistema.quantidadeDeImagensDigitalizadas(tip.getSelectedIndex());
					final String[] valores = new String[qnt];
					
					final ArrayList<Imagem> imgs = Sistema.getImagensDigitalizadas(tip.getSelectedIndex());
					for(int i=0;i<qnt;i++){
						valores[i] = i+"";
					}
					
					jl = new JList();
					jl.setModel(new AbstractListModel() {
						String[] values = valores;
					
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							return values[index];
						}
					});
					jl.setValueIsAdjusting(true);
					jl.addListSelectionListener(new ListSelectionListener() {
						
						@Override
						public void valueChanged(ListSelectionEvent e) {
							if(imgs.size()>0){
								ImageIcon img = new ImageIcon(imgs.get(jl.getSelectedIndex()).getImagem());
								img.setImage(img.getImage().getScaledInstance(478, 567, 500));
								imgInfo.setText("");
								imgInfo.setIcon(img);
							
							}
						}
					});
				
					jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					((JScrollPane) jsp).setViewportView(jl);

					
				}else{
					info.setForeground(Color.RED);
					info.setText("Os dados anteriores não foram válidados, verifique-os antes da digitalização!");
				}
				
				
			}

			
		});
		pb.add(dig,CC.xy(4, 23));
		
		getContentPane().add(pb.getPanel());
		
		Sistema.setDados("documentoAtual", new DocumentoDigital());
	}
	
	protected void limpar() {
		inst.setText("");
		alu.setText("");
		cur.setText("");
		tit.setText("");
		temp.setText("");
		tip.setSelectedIndex(-1);
		
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

	public void atualizaInstituicao(DocumentoDigital documentoDigital) {
//		inst.setText(documentoDigital.getMetadados().getInstituicao().getNome());
		confInst.setIcon(new ImageIcon(TelaCadastroDeDocumento.class.getResource("/images/icon/confirm.png")));
		
	}

	public void atualizaAluno(DocumentoDigital documentoDigital) {
//		alu.setText(documentoDigital.getMetadados().getAluno().getNome());
		confAlu.setIcon(new ImageIcon(TelaCadastroDeDocumento.class.getResource("/images/icon/confirm.png")));
		
	}

	public void atualizaCurso(DocumentoDigital documentoDigital) {
//		cur.setText(documentoDigital.getMetadados().getAluno().getCursos().get(0).getNome());
		confCur.setIcon(new ImageIcon(TelaCadastroDeDocumento.class.getResource("/images/icon/confirm.png")));
		
	}
	
	private boolean validaParaDigitalizacao() {
		//TODO
		return true;
	}
}
