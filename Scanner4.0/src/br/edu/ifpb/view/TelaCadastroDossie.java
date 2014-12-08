package br.edu.ifpb.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TelaCadastroDossie extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroDossie dialog = new TelaCadastroDossie(null);
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
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaCadastroDossie(JFrame principal) {
		super(principal,"Dossiê",true);
		setBounds(100, 100, 802, 598);
		getContentPane().setLayout(null);
		
		JLabel lblAluno = new JLabel("Aluno:");
		lblAluno.setBounds(25, 82, 46, 14);
		getContentPane().add(lblAluno);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(139, 30, 350, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblInstituicao = new JLabel("Instituicao:");
		lblInstituicao.setBounds(25, 30, 69, 14);
		getContentPane().add(lblInstituicao);
		
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(25, 55, 55, 16);
		getContentPane().add(lblCurso);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setBounds(139, 56, 350, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setEditable(false);
		textField_2.setBounds(139, 82, 350, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		lblTtulo.setBounds(25, 140, 55, 16);
		getContentPane().add(lblTtulo);
		
		textField_3 = new JTextField();
		textField_3.setBounds(139, 138, 350, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblClassificao = new JLabel("Classifica\u00E7\u00E3o:");
		lblClassificao.setBounds(25, 168, 93, 16);
		getContentPane().add(lblClassificao);
		
		textField_4 = new JTextField();
		textField_4.setBounds(139, 170, 350, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(25, 230, 78, 16);
		getContentPane().add(lblDescrio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 202, 350, 92);
		getContentPane().add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JLabel lblNewLabel = new JLabel("Imagens:");
		lblNewLabel.setBounds(25, 321, 64, 16);
		getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(139, 321, 136, 153);
		getContentPane().add(scrollPane_1);
		
		JList list = new JList();
		scrollPane_1.setViewportView(list);
		
		JLabel lblNenhumaImagemSelecionada = new JLabel("Nenhuma Imagem Selecionada");
		lblNenhumaImagemSelecionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblNenhumaImagemSelecionada.setBounds(516, 140, 258, 345);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Documento Acad\u00EAmico", "Documento Pessoal"}));
		comboBox.setBounds(139, 108, 350, 25);
		getContentPane().add(comboBox);
		
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
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setEditable(false);
		textField_5.setBounds(711, 30, 46, 20);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblQuantidadeDeDocumentos = new JLabel("Quantidade De Documentos:");
		lblQuantidadeDeDocumentos.setBounds(516, 55, 168, 16);
		getContentPane().add(lblQuantidadeDeDocumentos);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setEditable(false);
		textField_6.setBounds(711, 53, 46, 20);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);

	}
	
	private class DigitalizarNovaImagemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
