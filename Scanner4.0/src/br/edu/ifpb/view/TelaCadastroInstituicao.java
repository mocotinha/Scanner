package br.edu.ifpb.view;

import java.awt.EventQueue;
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

@SuppressWarnings("serial")
public class TelaCadastroInstituicao extends JDialog {
	private JTextField nome;
	private Instituicao instituicao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroInstituicao dialog = new TelaCadastroInstituicao(null);
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
	public TelaCadastroInstituicao(JFrame principal) {
		super(principal, "Cadastro de Instituições", true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(25, 36, 70, 14);
		getContentPane().add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(105, 33, 282, 20);
		getContentPane().add(nome);
		nome.setColumns(10);
		
		JButton btnCadastro = new JButton("Cadastrar");
		btnCadastro.setBounds(25, 209, 103, 23);
		btnCadastro.addActionListener(new CadastrarListener());
		getContentPane().add(btnCadastro);
		
		JButton btnLimpar = new JButton("Limpar Dados");
		btnLimpar.setBounds(150, 209, 126, 23);
		btnLimpar.addActionListener(new LimparListener());
		getContentPane().add(btnLimpar);
		
		JButton btnNewButton = new JButton("Concluir");
		btnNewButton.setBounds(310, 209, 89, 23);
		btnNewButton.addActionListener(new ConcluidoListener());
		getContentPane().add(btnNewButton);

	}
	
	public TelaCadastroInstituicao(JFrame jFrame, Instituicao instituicao) {
		super(jFrame, "Cadastro de Instituições", true);
		this.instituicao = instituicao;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(25, 36, 70, 14);
		getContentPane().add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(105, 33, 282, 20);
		nome.setText(this.instituicao.getNome());
		getContentPane().add(nome);
		nome.setColumns(10);
		
		JButton btnCadastro = new JButton("Atualizar");
		btnCadastro.setBounds(25, 209, 103, 23);
		btnCadastro.addActionListener(new AtualizarListener());
		getContentPane().add(btnCadastro);
		
		JButton btnLimpar = new JButton("Limpar Dados");
		btnLimpar.setBounds(150, 209, 126, 23);
		btnLimpar.addActionListener(new LimparListener());
		getContentPane().add(btnLimpar);
		
		JButton btnNewButton = new JButton("Concluir");
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
			try{
				instituicao.setNome(nome.getText());
				Sistema.atualizaInstituicao(instituicao);
				dispose();
				JOptionPane.showMessageDialog(classe(), "Instituição Atualizada com sucesso");
				
			}catch (Exception ex){
				JOptionPane.showMessageDialog(classe(), "Não foi possível atualizar a Instituição");
			}
			
		}
		
	}
	
	private class CadastrarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				Sistema.cadastraInstituicao(nome.getText());
				JOptionPane.showMessageDialog(classe(), "Instituição Cadastrada com Sucesso!");
				limpar();
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
