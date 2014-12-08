package br.edu.ifpb.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.model.Curso;

@SuppressWarnings("serial")
public class TelaCadastroCurso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nome;
	private JTextField nivel;
	private JFormattedTextField anoCriacao;
	private JFormattedTextField anoFim;
	private Curso curso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaCadastroCurso dialog = new TelaCadastroCurso(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaCadastroCurso(JFrame telaPrincipal) {
		
		super(telaPrincipal,"Cadastro de Curso",true);
		setBounds(100, 100, 577, 454);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(44, 325, 121, 23);
		btnCadastrar.addActionListener(new CadastrarListener());
		contentPanel.setLayout(null);
		contentPanel.add(btnCadastrar);
		
		JButton btnLimparDados = new JButton("Limpar Dados");
		btnLimparDados.setBounds(204, 325, 152, 23);
		btnLimparDados.addActionListener(new LimparListener());
		contentPanel.add(btnLimparDados);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(404, 325, 99, 23);
		btnConcluir.addActionListener(new ConcluidoListener());
		contentPanel.add(btnConcluir);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(33, 39, 46, 14);
		contentPanel.add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(141, 36, 351, 20);
		contentPanel.add(nome);
		nome.setColumns(10);
		
		JLabel lblNvel = new JLabel("N\u00EDvel:");
		lblNvel.setBounds(33, 96, 46, 14);
		contentPanel.add(lblNvel);
		
		nivel = new JTextField();
		nivel.setBounds(141, 93, 351, 20);
		contentPanel.add(nivel);
		nivel.setColumns(10);
		
		JLabel lblAnoCriao = new JLabel("Ano Cria\u00E7\u00E3o:");
		lblAnoCriao.setBounds(33, 154, 83, 14);
		contentPanel.add(lblAnoCriao);
		
		
		try {
			anoCriacao = new JFormattedTextField(new MaskFormatter("####"));
			anoCriacao.setBounds(141, 151, 73, 20);
			contentPanel.add(anoCriacao);
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Ano Criação Inválido deve seguir o modelo AAAA. EX:2014");
		}
		
		JLabel lblAnoFinalizao = new JLabel("Ano Finaliza\u00E7\u00E3o:");
		lblAnoFinalizao.setBounds(257, 154, 99, 14);
		contentPanel.add(lblAnoFinalizao);
		
	
		try {
			anoFim = new JFormattedTextField(new MaskFormatter("####"));
			anoFim.setBounds(366, 151, 73, 23);
			contentPanel.add(anoFim);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Ano Fim Inválido deve seguir o modelo AAAA. EX:2014");
		}	
	}
	
	public TelaCadastroCurso(JFrame telaPrincipal,Curso curso) {
		super(telaPrincipal,"Cadastro de Curso",true);
		this.curso = curso;
		setBounds(100, 100, 577, 454);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		JButton btnCadastrar = new JButton("Atualizar");
		btnCadastrar.setBounds(44, 325, 121, 23);
		btnCadastrar.addActionListener(new AtualizarListener());
		contentPanel.setLayout(null);
		contentPanel.add(btnCadastrar);
		
		JButton btnLimparDados = new JButton("Limpar Dados");
		btnLimparDados.setBounds(204, 325, 152, 23);
		btnLimparDados.addActionListener(new LimparListener());
		contentPanel.add(btnLimparDados);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(404, 325, 99, 23);
		btnConcluir.addActionListener(new ConcluidoListener());
		contentPanel.add(btnConcluir);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(33, 39, 46, 14);
		contentPanel.add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(141, 36, 351, 20);
		nome.setText(curso.getNome());
		contentPanel.add(nome);
		nome.setColumns(10);
		
		JLabel lblNvel = new JLabel("N\u00EDvel:");
		lblNvel.setBounds(33, 96, 46, 14);
		contentPanel.add(lblNvel);
		
		nivel = new JTextField();
		nivel.setBounds(141, 93, 351, 20);
		nivel.setText(curso.getNivel());
		contentPanel.add(nivel);
		nivel.setColumns(10);
		
		JLabel lblAnoCriao = new JLabel("Ano Cria\u00E7\u00E3o:");
		lblAnoCriao.setBounds(33, 154, 83, 14);
		contentPanel.add(lblAnoCriao);
		
		
		try {
			anoCriacao = new JFormattedTextField(new MaskFormatter("####"));
			anoCriacao.setText(curso.getAnoInicio());
			anoCriacao.setBounds(141, 151, 73, 20);
			contentPanel.add(anoCriacao);
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Ano Criação Inválido deve seguir o modelo AAAA. EX:2014");
		}
		
		JLabel lblAnoFinalizao = new JLabel("Ano Finaliza\u00E7\u00E3o:");
		lblAnoFinalizao.setBounds(257, 154, 99, 14);
		contentPanel.add(lblAnoFinalizao);
		
	
		try {
			anoFim = new JFormattedTextField(new MaskFormatter("####"));
			anoFim.setBounds(366, 151, 73, 23);
			anoFim.setText(curso.getAnoFim());
			contentPanel.add(anoFim);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Ano Fim Inválido deve seguir o modelo AAAA. EX:2014");
		}
		
		
	}
	
	
	
	private TelaCadastroCurso classe(){
		return this;
	}
	
	private void limpar(){
		nome.setText("");
		nivel.setText("");
		anoCriacao.setText("");
		anoFim.setText("");
	}
	
	private class AtualizarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try{
				curso.setNome(nome.getText());
				curso.setNivel(nivel.getText());
				curso.setAnoInicio(anoCriacao.getText());
				curso.setAnoFim(anoFim.getText());
				Sistema.atualizaCurso(curso);
				JOptionPane.showMessageDialog(classe(), "Curso Atualizado com Sucesso!");
				dispose();
				
			}catch(Exception ex){
				JOptionPane.showMessageDialog(classe(), "Erro ao atualizar Curso!");
			}
		
			
		}
		
	}
	
	private class CadastrarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				Sistema.cadastraCurso(nome.getText(),nivel.getText(),anoCriacao.getText(), anoFim.getText());
				JOptionPane.showMessageDialog(classe(), "Curso Cadastrado Com Sucesso");
				limpar();
			}catch (Exception ex){
				JOptionPane.showMessageDialog(classe(), "Erro ao Cadastrar o Curso");
			}
			
			
		}
		
	}
	
	private class LimparListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			limpar();
			
		}
		
	}
	
	private class ConcluidoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}

}
