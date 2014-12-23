package br.edu.ifpb.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.model.DocumentoAcademico;
import br.edu.ifpb.model.DocumentoDigital;
import br.edu.ifpb.model.Dossie;
import br.edu.ifpb.model.Imagem;

@SuppressWarnings("serial")
public class TelaCadastroDossie extends JDialog {
	private Dossie dossie;
	private JTextField instituicao;
	private JTextField curso;
	private JTextField aluno;
	private JTextField titulo;
	private JTextField classificao;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroDossie dialog = new TelaCadastroDossie(null,null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 * @param dossie 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaCadastroDossie(JFrame principal, Dossie dossie) {
		
		super(principal,"Dossiê",true);
		this.dossie = dossie;
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
		lblTtulo.setBounds(25, 140, 55, 16);
		getContentPane().add(lblTtulo);
		
		titulo = new JTextField();
		titulo.setBounds(139, 138, 350, 20);
		getContentPane().add(titulo);
		titulo.setColumns(10);
		
		JLabel lblClassificao = new JLabel("Classifica\u00E7\u00E3o:");
		lblClassificao.setBounds(25, 168, 93, 16);
		getContentPane().add(lblClassificao);
		
		classificao = new JTextField();
		classificao.setBounds(139, 170, 350, 20);
		getContentPane().add(classificao);
		classificao.setColumns(10);
		
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
		lblTipoDoDocumento.setBounds(25, 112, 112, 16);
		getContentPane().add(lblTipoDoDocumento);
		
		tipo = new JComboBox();
		tipo.setModel(new DefaultComboBoxModel(new String[] {"Documento Acad\u00EAmico", "Documento Pessoal"}));
		tipo.setBounds(139, 108, 350, 25);
		getContentPane().add(tipo);
		
		JButton btnCadastrarDocumento = new JButton("Cadastrar");
		btnCadastrarDocumento.setBounds(25, 522, 159, 26);
		btnCadastrarDocumento.addActionListener(new CadastrarDocumentoListener());
		getContentPane().add(btnCadastrarDocumento);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(429, 522, 98, 26);
		btnLimpar.addActionListener(new LimparListener());
		getContentPane().add(btnLimpar);
		
		JButton btnNovoDossi = new JButton("Novo Dossi\u00EA");
		btnNovoDossi.setBounds(242, 522, 109, 26);
		btnNovoDossi.addActionListener(new NovoDossieListener());
		getContentPane().add(btnNovoDossi);
		
		JButton btnConcludo = new JButton("Conclu\u00EDdo");
		btnConcludo.setBounds(606, 522, 98, 26);
		btnConcludo.addActionListener(new ConcluidoListener());
		getContentPane().add(btnConcludo);
		
		JLabel lblCdigoDoDossi = new JLabel("C\u00F3digo do Dossi\u00EA:");
		lblCdigoDoDossi.setBounds(517, 32, 112, 16);
		getContentPane().add(lblCdigoDoDossi);
		
		codigoDossie = new JTextField(dossie.getId());
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
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(609, 96, 148, 117);
		getContentPane().add(scrollPane_2);
		
		list = new JList();
		list.setModel(new ListaDocumento());
		scrollPane_2.setViewportView(list);
		list.setValueIsAdjusting(true);
		list.addListSelectionListener(new DocumentoSelecionadoListener());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblDocumentos = new JLabel("Documentos:");
		lblDocumentos.setBounds(509, 99, 90, 14);
		getContentPane().add(lblDocumentos);

	}
	
	private class DocumentoSelecionadoListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			DocumentoDigital aux = ((ListaDocumento)list.getModel()).getElementAt(list.getSelectedIndex());
			if(aux instanceof DocumentoAcademico){
				tipo.setSelectedItem(0);
			}else{
				tipo.setSelectedItem(0);
			}
			titulo.setText(aux.getTitulo());
			classificao.setText(aux.getClassificacao());
			descricao.setText(aux.getDescricao());
			//Listar as Imagens
			//TODO
			
			
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

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void actionPerformed(ActionEvent e) {
			Imagem img = Sistema.digitalizaImagem();
			Sistema.adicionaImagem(img);
			
			
			lista = new JList();
			lista.setModel(new ListaImagensModel());
			lista.setValueIsAdjusting(true);
			lista.addListSelectionListener(new SelecionarImagemListener());
		
			lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			((JScrollPane) scrollPane_1).setViewportView(lista);

			
		}
		
	}
	
	private class RemoverImagemDigitalizadaListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class CadastrarDocumentoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class LimparListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class NovoDossieListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ConcluidoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
}
