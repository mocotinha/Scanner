package br.edu.ifpb.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.co.mmscomputing.device.twain.TwainFailureException;
import br.edu.ifpb.controler.NotGetDeviceException;
import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.model.DocumentoDigital;
import br.edu.ifpb.model.Dossie;
import br.edu.ifpb.model.Imagem;
import java.awt.Font;

@SuppressWarnings("serial")
public class TelaCadastroDossie extends JDialog {
	private Dossie dossie;
	private JTextField instituicao;
	private JTextField curso;
	private JTextField aluno;
	private JTextField titulo;
	private JTextField codigoDossie;
	private JTextField qntDocumentos;
	@SuppressWarnings("rawtypes")
	private JComboBox tipo;
	@SuppressWarnings("rawtypes")
	private JList list;
	@SuppressWarnings("rawtypes")
	private JList lista;
	private JTextPane descricao;
	private JLabel lblNenhumaImagemSelecionada;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2 ;
	private boolean status = true;
	private JButton selImagem;
	private String tipos[] = new String[] {"Documento Acad\u00EAmico", "Documento Pessoal"};
	private List<String> tipos2 = Arrays.asList(tipos);
	private List<String> tipos3 = new ArrayList<String>(tipos2);

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaCadastroDossie(JFrame principal, Dossie dossie, int acesso) {
		super(principal,"Dossi�",true);
		Sistema.limpaImagens();
		this.dossie = dossie;
		Sistema.setImagens(new ArrayList<Imagem>());
		setBounds(100, 100, 802, 598);
		getContentPane().setLayout(null);
		
		JLabel lblAluno = new JLabel("Aluno:");
		lblAluno.setBounds(25, 82, 46, 14);
		getContentPane().add(lblAluno);
		
		instituicao = new JTextField();
		instituicao.setText(dossie.getInstituicao().getNome());
		instituicao.setEnabled(false);
		instituicao.setEditable(false);
		instituicao.setBounds(139, 30, 350, 20);
		getContentPane().add(instituicao);
		instituicao.setColumns(10);
		
		JLabel lblInstituicao = new JLabel("Instituicao:");
		lblInstituicao.setBounds(25, 30, 69, 14);
		getContentPane().add(lblInstituicao);
		
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(25, 55, 55, 16);
		getContentPane().add(lblCurso);
		
		curso = new JTextField();
		curso.setText(dossie.getCurso().getNome());
		curso.setEnabled(false);
		curso.setEditable(false);
		curso.setBounds(139, 56, 350, 20);
		getContentPane().add(curso);
		curso.setColumns(10);
		
		aluno = new JTextField();
		aluno.setText(dossie.getAluno().getNome());
		aluno.setEnabled(false);
		aluno.setEditable(false);
		aluno.setBounds(139, 82, 350, 20);
		getContentPane().add(aluno);
		aluno.setColumns(10);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		lblTtulo.setBounds(25, 173, 55, 16);
		getContentPane().add(lblTtulo);
		
		titulo = new JTextField();
		titulo.setBounds(139, 171, 350, 20);
		titulo.setDocument(new LimitarMaiusculas());
		getContentPane().add(titulo);
		titulo.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(25, 230, 78, 16);
		getContentPane().add(lblDescrio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 202, 350, 92);
		getContentPane().add(scrollPane);
		
		descricao = new JTextPane();
		scrollPane.setViewportView(descricao);
		
		JLabel lblNewLabel = new JLabel("Imagens:");
		lblNewLabel.setBounds(25, 321, 64, 16);
		getContentPane().add(lblNewLabel);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(139, 321, 136, 153);
		getContentPane().add(scrollPane_1);
		
		lista = new JList();
		lista.setModel(new ListaImagensModel());
		lista.setValueIsAdjusting(true);
		lista.addListSelectionListener(new SelecionarImagemListener());
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(lista);
		
		lblNenhumaImagemSelecionada = new JLabel("Nenhuma Imagem Selecionada");
		lblNenhumaImagemSelecionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblNenhumaImagemSelecionada.setBounds(555, 256, 202, 241);
		
		getContentPane().add(lblNenhumaImagemSelecionada);
		
		JButton btnDigitalizarNovaImagem = new JButton("Digitalizar Nova Imagem");
		btnDigitalizarNovaImagem.addActionListener(new DigitalizarNovaImagemListener());
		btnDigitalizarNovaImagem.setBounds(287, 321, 202, 26);
		getContentPane().add(btnDigitalizarNovaImagem);
		
		JButton btnRemoverImagemDigitalizada = new JButton("Remover Imagem Digitalizada");
		btnRemoverImagemDigitalizada.setBounds(287, 448, 202, 26);
		btnRemoverImagemDigitalizada.addActionListener(new RemoverImagemDigitalizadaListener());
		getContentPane().add(btnRemoverImagemDigitalizada);
		
		JLabel lblTipoDoDocumento = new JLabel("Tipo do Documento:");
		lblTipoDoDocumento.setBounds(25, 145, 112, 16);
		getContentPane().add(lblTipoDoDocumento);
		
		tipo = new JComboBox();
		tipo.setModel(new AbstractComboBoxModel(tipos3));
		tipo.setBounds(139, 141, 350, 25);
		getContentPane().add(tipo);
		
		JButton btnCadastrarDocumento = new JButton("Salvar");
		btnCadastrarDocumento.setBounds(25, 522, 159, 26);
		btnCadastrarDocumento.addActionListener(new CadastrarDocumentoListener());
		getContentPane().add(btnCadastrarDocumento);
		
		JButton btnLimpar = new JButton("Novo Documento");
		btnLimpar.setBounds(429, 522, 136, 26);
		btnLimpar.addActionListener(new LimparListener());
		getContentPane().add(btnLimpar);
		
		if(acesso == 0){
			JButton btnNovoDossi = new JButton("Novo Dossi\u00EA");
			btnNovoDossi.setBounds(242, 522, 109, 26);
			btnNovoDossi.addActionListener(new NovoDossieListener());
			getContentPane().add(btnNovoDossi);
		}
		
		
		JButton btnConcludo = new JButton("Cancelar");
		btnConcludo.setBounds(606, 522, 98, 26);
		btnConcludo.addActionListener(new ConcluidoListener());
		getContentPane().add(btnConcludo);
		
		JLabel lblCdigoDoDossi = new JLabel("C\u00F3digo do Dossi\u00EA:");
		lblCdigoDoDossi.setBounds(517, 32, 112, 16);
		getContentPane().add(lblCdigoDoDossi);
		
		codigoDossie = new JTextField(dossie.getId()+"");
		codigoDossie.setEnabled(false);
		codigoDossie.setEditable(false);
		codigoDossie.setBounds(711, 30, 46, 20);
		getContentPane().add(codigoDossie);
		codigoDossie.setColumns(10);
		
		JLabel lblQuantidadeDeDocumentos = new JLabel("Quantidade De Documentos:");
		lblQuantidadeDeDocumentos.setBounds(516, 55, 168, 16);
		getContentPane().add(lblQuantidadeDeDocumentos);
		
		qntDocumentos = new JTextField(dossie.getDocumentos().size()+"");
		qntDocumentos.setEnabled(false);
		qntDocumentos.setEditable(false);
		qntDocumentos.setBounds(711, 53, 46, 20);
		getContentPane().add(qntDocumentos);
		qntDocumentos.setColumns(10);
		
		scrollPane_2= new JScrollPane();
		scrollPane_2.setBounds(509, 104, 249, 117);
		getContentPane().add(scrollPane_2);
		
		list = new JList();
		list.setModel(new ListaDocumento());
		scrollPane_2.setViewportView(list);
		list.setValueIsAdjusting(true);
		list.addListSelectionListener(new DocumentoSelecionadoListener());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblDocumentos = new JLabel("Documentos Cadastrados:");
		lblDocumentos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDocumentos.setBounds(509, 85, 168, 14);
		getContentPane().add(lblDocumentos);
		
		selImagem = new JButton("Selecionar Imagem de Arquivo");
		selImagem.addActionListener(new SelecionaArquivoListener());
		selImagem.setBounds(287, 384, 202, 26);
		getContentPane().add(selImagem);
		
		JLabel lblDocumentos_1 = new JLabel("Documentos:");
		lblDocumentos_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDocumentos_1.setBounds(25, 120, 90, 14);
		getContentPane().add(lblDocumentos_1);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void atualizaListaDeImagens(){
		lista = new JList();
		lista.setModel(new ListaImagensModel());
		lista.setValueIsAdjusting(true);
		lista.addListSelectionListener(new SelecionarImagemListener());
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		((JScrollPane) scrollPane_1).setViewportView(lista);
	}
	private class SelecionaArquivoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			
			
			JFileChooser chooser = new JFileChooser();
		    chooser.setFileFilter(new ImageFilter());
		    chooser.setAcceptAllFileFilterUsed(false);
		    int returnVal = chooser.showOpenDialog(classe());
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	File a = chooser.getSelectedFile();
		    	Imagem img = new Imagem();
		    	byte imageInByte[] = Sistema.fileToByteArray(classe(), a);
		    	img.setImagem(imageInByte);
		    	img.setFormato(Utils.getExtension(a));
		    	
		    	int total = 0;
				for (int i = 0; i < imageInByte.length; i++) {
					total+=imageInByte[i];
				}
				
				img.setChecksum(total);
				img.setResolucao("Resolu��o Indispon�vel");
				img.setTamanho(imageInByte.length);
				img.setNomeArqEmDisco(a.getName());
				Sistema.adicionaImagem(img);
		    }
		    
			atualizaListaDeImagens();
			
			
		}
		
	}
	
	private class DocumentoSelecionadoListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			limpar();
			DocumentoDigital aux = ((ListaDocumento)list.getModel()).getElementAt(list.getSelectedIndex());

			tipo.setSelectedIndex(tipos3.indexOf(aux.getTipo()));
			Sistema.setImagens(aux.getImagens());
			titulo.setText(aux.getTitulo());
			
			descricao.setText(aux.getDescricao());
			
			status = false;
			atualizaListaDeImagens();
			
			
			
		}
		
	}
	
	private class ListaDocumento extends AbstractListModel<DocumentoDigital>{
		
		private List<DocumentoDigital> dados = dossie.getDocumentos();
		
		@Override
		public DocumentoDigital getElementAt(int arg0) {
			return dados.get(arg0);
		
		}

		@Override
		public int getSize() {
			
			return dados.size();
		}
		
		
		
	}
	

	private class SelecionarImagemListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {

				ImageIcon img = new ImageIcon(((ListaImagensModel)lista.getModel()).getElementAt(lista.getSelectedIndex()).getImagem());
				img.setImage(img.getImage().getScaledInstance(202, 241, 500));
				lblNenhumaImagemSelecionada.setText("");
				lblNenhumaImagemSelecionada.setIcon(img);
				
					
	
			}

		
	}
	
	private class ListaImagensModel extends AbstractListModel<Imagem>{

		private List<Imagem> imgs = Sistema.getImagensDigitalizadas();
		@Override
		public Imagem getElementAt(int index) {
			
			return imgs.get(index);
		}

		@Override
		public int getSize() {
			
			return imgs.size();
		}
		
	}

	
	private class DigitalizarNovaImagemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
				Imagem img;
				try {
					img = Sistema.digitalizaImagem();
					Sistema.adicionaImagem(img);
				} catch (TwainFailureException e1) {
					JOptionPane.showMessageDialog(classe(), "Scanner N�o Identificado! Imposs�vel Digitalizar Imagens!");
					
				} catch (NotGetDeviceException e1) {
					JOptionPane.showMessageDialog(classe(), "Scanner N�o Identificado! Imposs�vel Digitalizar Imagens!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(classe(), "Erro ao converter a Imagem!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(classe(), "N�o Foi Poss�vel Digitalizar a Imagem!");
				}
				
				
			
		
			
			
			
			atualizaListaDeImagens();

			
		}
		
	}
	
	private class RemoverImagemDigitalizadaListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(JOptionPane.showConfirmDialog(classe(), "Deseja remover a imagem selecionada?","Remover Imagem",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				try{
					Sistema.removerImagem((Imagem)lista.getModel().getElementAt(lista.getSelectedIndex()));
					
					atualizaListaDeImagens();
					
					getContentPane().remove(lblNenhumaImagemSelecionada);
					lblNenhumaImagemSelecionada = new JLabel("Nenhuma Imagem Selecionada");
					lblNenhumaImagemSelecionada.setHorizontalAlignment(SwingConstants.CENTER);
					lblNenhumaImagemSelecionada.setBounds(555, 256, 202, 241);
					lblNenhumaImagemSelecionada.setIcon(null);
					getContentPane().add(lblNenhumaImagemSelecionada);
					getContentPane().repaint();
					
					
					
					
					lista.setSelectedIndex(-1);
				}catch(Exception ex){
					JOptionPane.showMessageDialog(classe(), "Nenhuma Imagem foi selecionada para exclus�o!");
				}
				
				
				
			}
				
			
			
			
			
			
		}
		
	}
	
	private TelaCadastroDossie classe(){
		return this;
	}
	
	private void limpar(){
		list.setSelectedIndex(-1);
		titulo.setText("");
		descricao.setText("");
		
		tipo.setSelectedIndex(0);
		status = true;
		getContentPane().remove(lblNenhumaImagemSelecionada);
		lblNenhumaImagemSelecionada = new JLabel("Nenhuma Imagem Selecionada");
		lblNenhumaImagemSelecionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblNenhumaImagemSelecionada.setBounds(555, 256, 202, 241);
		lblNenhumaImagemSelecionada.setIcon(null);
		getContentPane().add(lblNenhumaImagemSelecionada);
		getContentPane().repaint();
		Sistema.setImagens(new ArrayList<Imagem>());
		atualizaListaDeImagens();
	}
	
	
	private class CadastrarDocumentoListener implements ActionListener{

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void actionPerformed(ActionEvent e) {
			if(titulo.getText().equals("") || titulo.getText()==null){
				JOptionPane.showMessageDialog(classe(), "O Titulo do Documento deve ser informado!");
				return;
			}
			try{
				if(status){
					Sistema.cadastraDocumentoAoDossie(dossie,titulo.getText(), descricao.getText(),(String)tipo.getSelectedItem());
					JOptionPane.showMessageDialog(classe(), "Documento Cadastrado com Sucesso!");
				}else{
					Sistema.atualizaDocumentos(dossie,titulo.getText(), descricao.getText(),(String)tipo.getSelectedItem(),list.getSelectedIndex());
					JOptionPane.showMessageDialog(classe(), "Documento Atualizado com Sucesso!");
				}
				
				list = new JList();
				list.setModel(new ListaDocumento());
				list.setValueIsAdjusting(true);
				list.addListSelectionListener(new DocumentoSelecionadoListener());
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				((JScrollPane) scrollPane_2).setViewportView(list);
				limpar();
				dossie = Sistema.atualizaDossie(dossie);
				qntDocumentos.setText(dossie.getDocumentos().size()+"");
			}catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(classe(), "Erro ao cadastrar o documento");
			}
			
		}
			
		
	}
	
	private class LimparListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(JOptionPane.showConfirmDialog(classe(), "Deseja cadastrar um novo Documento?","Novo Documento",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				limpar();
			}	
			
			
			
		}
		
	}
	
	private class NovoDossieListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(JOptionPane.showConfirmDialog(classe(), "Deseja cadastrar um novo Dossi�?","Novo Dossie",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				dispose();
				Sistema.cadastroDossie();
			}
			
			
			
		}
		
	}
	
	private class ConcluidoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
}
