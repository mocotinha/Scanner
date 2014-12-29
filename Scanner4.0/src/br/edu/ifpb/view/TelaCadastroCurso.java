package br.edu.ifpb.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.model.Curso;
import br.edu.ifpb.model.CursoExistenteException;

@SuppressWarnings("serial")
public class TelaCadastroCurso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nome;
	private JTextField nivel;
	private Curso curso;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;


	/**
	 * @wbp.parser.constructor
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TelaCadastroCurso(JFrame telaPrincipal) {
		
		super(telaPrincipal,"Cadastro de Curso",true);
		setBounds(100, 100, 577, 454);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		JButton btnCadastrar = new JButton("Salvar");
		btnCadastrar.setBounds(44, 325, 121, 23);
		btnCadastrar.addActionListener(new CadastrarListener());
		contentPanel.setLayout(null);
		contentPanel.add(btnCadastrar);
		
		JButton btnLimparDados = new JButton("Novo");
		btnLimparDados.setBounds(204, 325, 152, 23);
		btnLimparDados.addActionListener(new LimparListener());
		contentPanel.add(btnLimparDados);
		
		JButton btnConcluir = new JButton("Cancelar");
		btnConcluir.setBounds(404, 325, 99, 23);
		btnConcluir.addActionListener(new ConcluidoListener());
		contentPanel.add(btnConcluir);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(33, 130, 46, 14);
		contentPanel.add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(141, 127, 351, 20);
		nome.setDocument(new LimitarMaiusculas());
		contentPanel.add(nome);
		nome.setColumns(10);
		
		JLabel lblNvel = new JLabel("N\u00EDvel:");
		lblNvel.setBounds(33, 187, 46, 14);
		contentPanel.add(lblNvel);
		
		nivel = new JTextField();
		nivel.setBounds(141, 184, 351, 20);
		nivel.setDocument(new LimitarMaiusculas());
		contentPanel.add(nivel);
		nivel.setColumns(10);
		
	
		comboBox = new JComboBox(new DefaultComboBoxModel(Sistema.getNomeDasInstituicao()));
		comboBox.setBounds(141, 75, 351, 22);
		contentPanel.add(comboBox);
		
		JLabel lblInstituio = new JLabel("Institui\u00E7\u00E3o:");
		lblInstituio.setBounds(33, 79, 82, 14);
		contentPanel.add(lblInstituio);
		
		
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TelaCadastroCurso(JFrame telaPrincipal,Curso curso) {
		super(telaPrincipal,"Cadastro de Curso",true);
		this.curso = curso;
		setBounds(100, 100, 577, 454);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		JButton btnCadastrar = new JButton("Salvar");
		btnCadastrar.setBounds(44, 325, 121, 23);
		btnCadastrar.addActionListener(new AtualizarListener());
		contentPanel.setLayout(null);
		contentPanel.add(btnCadastrar);
		
		JButton btnLimparDados = new JButton("Novo");
		btnLimparDados.setBounds(204, 325, 152, 23);
		btnLimparDados.addActionListener(new LimparListener());
		contentPanel.add(btnLimparDados);
		
		JButton btnConcluir = new JButton("Cancelar");
		btnConcluir.setBounds(404, 325, 99, 23);
		btnConcluir.addActionListener(new ConcluidoListener());
		contentPanel.add(btnConcluir);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(33, 130, 46, 14);
		contentPanel.add(lblNome);
		
		nome = new JTextField();
		nome.setDocument(new LimitarMaiusculas());
		nome.setText(curso.getNome());
		nome.setBounds(141, 127, 351, 20);	
		contentPanel.add(nome);
		nome.setColumns(10);
		
		JLabel lblNvel = new JLabel("N\u00EDvel:");
		lblNvel.setBounds(33, 187, 46, 14);
		contentPanel.add(lblNvel);
		
		nivel = new JTextField(curso.getNivel());
		nivel.setBounds(141, 184, 351, 20);
		nivel.setDocument(new LimitarMaiusculas());
		contentPanel.add(nivel);
		nivel.setColumns(10);
		
		
		comboBox = new JComboBox(new DefaultComboBoxModel(Sistema.getNomeDasInstituicao()));
		comboBox.setEnabled(false);
		comboBox.setSelectedItem(curso.getInstituicao().getNome());
		comboBox.setBounds(141, 75, 351, 22);
		contentPanel.add(comboBox);
		
		JLabel lblInstituio = new JLabel("Institui\u00E7\u00E3o:");
		lblInstituio.setBounds(33, 79, 82, 14);
		
		contentPanel.add(lblInstituio);
		
		
		
	
		
	
		
		
		
	}
	
	
	
	private TelaCadastroCurso classe(){
		return this;
	}
	
	private void limpar(){
		nome.setText("");
		nivel.setText("");
		
	}
	
	private class AtualizarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(nome.getText().equals("")||nome.getText() == null){
				JOptionPane.showMessageDialog(classe(), "O nome do curso n�o deve ser vazio!");
				return;
			}
			try{
				curso.setNome(nome.getText());
				curso.setNivel(nivel.getText());
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
			if(nome.getText().equals("")||nome.getText() == null){
				JOptionPane.showMessageDialog(classe(), "O nome do curso n�o deve ser vazio!");
				return;
			}
			if(comboBox.getSelectedIndex() == -1){
				JOptionPane.showMessageDialog(classe(), "Voc� deve primeiro ter uma Institui��o cadastrada, a institui��o n�o pode ser vazia!");
				return;
			}
			try{
				Sistema.cadastraCurso(nome.getText(),nivel.getText(),(String)comboBox.getSelectedItem());
				JOptionPane.showMessageDialog(classe(), "Curso Cadastrado Com Sucesso");
				limpar();
			}catch(CursoExistenteException e1){
				JOptionPane.showMessageDialog(classe(), e1.getMessage());
			}catch (Exception ex){
				ex.printStackTrace();
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
