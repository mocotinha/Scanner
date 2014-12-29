package br.edu.ifpb.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.model.Instituicao;
import br.edu.ifpb.model.InstituicaoExistenteException;

@SuppressWarnings("serial")
public class TelaCadastroInstituicao extends JDialog {
	private JTextField nome;
	private Instituicao instituicao;


	public TelaCadastroInstituicao(JFrame principal) {
		super(principal, "Cadastro de Institui��es", true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(25, 36, 70, 14);
		getContentPane().add(lblNome);
		
		nome = new JTextField();
		nome.setDocument(new LimitarMaiusculas());
		nome.setBounds(105, 33, 282, 20);
		getContentPane().add(nome);
		nome.setColumns(10);
		
		JButton btnCadastro = new JButton("Salvar");
		btnCadastro.setBounds(25, 209, 103, 23);
		btnCadastro.addActionListener(new CadastrarListener());
		getContentPane().add(btnCadastro);
		
		JButton btnLimpar = new JButton("Novo");
		btnLimpar.setBounds(150, 209, 126, 23);
		btnLimpar.addActionListener(new LimparListener());
		getContentPane().add(btnLimpar);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(310, 209, 89, 23);
		btnNewButton.addActionListener(new ConcluidoListener());
		getContentPane().add(btnNewButton);

	}
	
	public TelaCadastroInstituicao(JFrame jFrame, Instituicao instituicao) {
		super(jFrame, "Cadastro de Institui��es", true);
		this.instituicao = instituicao;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(25, 36, 70, 14);
		getContentPane().add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(105, 33, 282, 20);
		nome.setDocument(new LimitarMaiusculas());
		nome.setText(this.instituicao.getNome());
		getContentPane().add(nome);
		nome.setColumns(10);
		
		JButton btnCadastro = new JButton("Salvar");
		btnCadastro.setBounds(25, 209, 103, 23);
		btnCadastro.addActionListener(new AtualizarListener());
		getContentPane().add(btnCadastro);
		
		JButton btnLimpar = new JButton("Novo");
		btnLimpar.setBounds(150, 209, 126, 23);
		btnLimpar.addActionListener(new LimparListener());
		getContentPane().add(btnLimpar);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(310, 209, 89, 23);
		btnNewButton.addActionListener(new ConcluidoListener());
		getContentPane().add(btnNewButton);
	}

	private TelaCadastroInstituicao classe(){
		return this;
	}
	
	private void limpar(){
		nome.setText("");
	}
	
	private class AtualizarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(nome.getText().equals("")||nome.getText() == null){
				JOptionPane.showMessageDialog(classe(), "O nome da Institui��o n�o deve ser vazio!");
				return;
			}
			try{
				instituicao.setNome(nome.getText());
				Sistema.atualizaInstituicao(instituicao);
				dispose();
				JOptionPane.showMessageDialog(classe(), "Institui��o Atualizada com sucesso");
				
			}catch (Exception ex){
				JOptionPane.showMessageDialog(classe(), "N�o foi poss�vel atualizar a Institui��o");
			}
			
		}
		
	}
	
	private class CadastrarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(nome.getText().equals("")||nome.getText() == null){
				JOptionPane.showMessageDialog(classe(), "O nome da Institui��o n�o deve ser vazio!");
				return;
			}
			try{
				Sistema.cadastraInstituicao(nome.getText());
				JOptionPane.showMessageDialog(classe(), "Institui��o Cadastrada com Sucesso!");
				limpar();
			}catch (InstituicaoExistenteException ex){
				JOptionPane.showMessageDialog(classe(), ex.getMessage());
			}catch(Exception ex){
				JOptionPane.showMessageDialog(classe(), "Erro ao cadastrar a Instituicao!");
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
