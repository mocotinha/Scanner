package br.edu.ifpb.view;

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
import br.edu.ifpb.model.Aluno;

@SuppressWarnings("serial")
public class TelaCadastroAluno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nome;
	private JTextField matricula;
	private JFormattedTextField dataNascimento;
	private JTextField rg;
	private JTextField cpf;
	private JTextField mae;
	private JTextField pai;
	private Aluno aluno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaCadastroAluno dialog = new TelaCadastroAluno(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaCadastroAluno(JFrame telaPrincipal) {
		super(telaPrincipal,"Cadastro de Aluno",true);
		setBounds(100, 100, 568, 389);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 14, 560, 348);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 11, 46, 14);
		contentPanel.add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(106, 8, 395, 20);
		contentPanel.add(nome);
		nome.setColumns(10);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula:");
		lblMatrcula.setBounds(20, 42, 70, 14);
		contentPanel.add(lblMatrcula);
		
		matricula = new JTextField();
		matricula.setBounds(106, 39, 143, 20);
		contentPanel.add(matricula);
		matricula.setColumns(10);
		
		JLabel lblDataNacimento = new JLabel("Data Nacimento:");
		lblDataNacimento.setBounds(259, 42, 95, 14);
		contentPanel.add(lblDataNacimento);
		
		JLabel lblNewLabel = new JLabel("RG:");
		lblNewLabel.setBounds(20, 97, 46, 14);
		contentPanel.add(lblNewLabel);
		
		rg = new JTextField();
		rg.setBounds(106, 94, 143, 20);
		contentPanel.add(rg);
		rg.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(259, 97, 46, 14);
		contentPanel.add(lblCpf);
		
		cpf = new JTextField();
		cpf.setBounds(328, 94, 173, 20);
		contentPanel.add(cpf);
		cpf.setColumns(10);
		
		JLabel lblNomeDaMe = new JLabel("Nome da M\u00E3e:");
		lblNomeDaMe.setBounds(20, 151, 80, 14);
		contentPanel.add(lblNomeDaMe);
		
		mae = new JTextField();
		mae.setBounds(106, 148, 395, 20);
		contentPanel.add(mae);
		mae.setColumns(10);
		
		JLabel lblNomeDoPai = new JLabel("Nome do Pai:");
		lblNomeDoPai.setBounds(20, 189, 80, 14);
		contentPanel.add(lblNomeDoPai);
		
		pai = new JTextField();
		pai.setBounds(106, 186, 395, 20);
		contentPanel.add(pai);
		pai.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(47, 261, 91, 23);
		btnCadastrar.addActionListener(new CadastrarListener());
		contentPanel.add(btnCadastrar);
		
		JButton btnLimparDados = new JButton("Limpar Dados");
		btnLimparDados.setBounds(207, 261, 124, 23);
		btnLimparDados.addActionListener(new LimparListener());
		contentPanel.add(btnLimparDados);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(399, 261, 91, 23);
		btnConcluir.addActionListener(new ConcluidoListener());
		contentPanel.add(btnConcluir);
		
		
		try {
			dataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
			dataNascimento.setBounds(363, 39, 138, 20);
			contentPanel.add(dataNascimento);
		} catch (ParseException e) {
			
			JOptionPane.showMessageDialog(this, "Data inválida, deve seguir o formato dd/mm/aaaa");
		}
	}
	

	
	//Construtor para editar
	public TelaCadastroAluno(JFrame telaPrincipal, Aluno aluno) {
		super(telaPrincipal,"Cadastro de Aluno",true);
		this.aluno = aluno;
		setBounds(100, 100, 568, 389);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 14, 560, 348);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 11, 46, 14);
		contentPanel.add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(106, 8, 395, 20);
		nome.setText(aluno.getNome());
		contentPanel.add(nome);
		nome.setColumns(10);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula:");
		lblMatrcula.setBounds(20, 42, 70, 14);
		contentPanel.add(lblMatrcula);
		
		matricula = new JTextField();
		matricula.setBounds(106, 39, 143, 20);
		matricula.setText(aluno.getMatricula());
		contentPanel.add(matricula);
		matricula.setColumns(10);
		
		JLabel lblDataNacimento = new JLabel("Data Nacimento:");
		lblDataNacimento.setBounds(259, 42, 95, 14);
		contentPanel.add(lblDataNacimento);
		
		JLabel lblNewLabel = new JLabel("RG:");
		lblNewLabel.setBounds(20, 97, 46, 14);
		contentPanel.add(lblNewLabel);
		
		rg = new JTextField();
		rg.setBounds(106, 94, 143, 20);
		rg.setText(aluno.getRg());
		contentPanel.add(rg);
		rg.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(259, 97, 46, 14);
		contentPanel.add(lblCpf);
		
		cpf = new JTextField();
		cpf.setBounds(328, 94, 173, 20);
		cpf.setText(aluno.getCpf());
		contentPanel.add(cpf);
		cpf.setColumns(10);
		
		JLabel lblNomeDaMe = new JLabel("Nome da M\u00E3e:");
		lblNomeDaMe.setBounds(20, 151, 80, 14);
		contentPanel.add(lblNomeDaMe);
		
		mae = new JTextField();
		mae.setBounds(106, 148, 395, 20);
		mae.setText(aluno.getMae());
		contentPanel.add(mae);
		mae.setColumns(10);
		
		JLabel lblNomeDoPai = new JLabel("Nome do Pai:");
		lblNomeDoPai.setBounds(20, 189, 80, 14);
		contentPanel.add(lblNomeDoPai);
		
		pai = new JTextField();
		pai.setBounds(106, 186, 395, 20);
		pai.setText(aluno.getPai());
		contentPanel.add(pai);
		pai.setColumns(10);
		
		JButton btnCadastrar = new JButton("Atualizar");
		btnCadastrar.setBounds(47, 261, 91, 23);
		btnCadastrar.addActionListener(new AtualizarListener());
		contentPanel.add(btnCadastrar);
		
		JButton btnLimparDados = new JButton("Limpar Dados");
		btnLimparDados.setBounds(207, 261, 124, 23);
		btnLimparDados.addActionListener(new LimparListener());
		contentPanel.add(btnLimparDados);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(399, 261, 91, 23);
		btnConcluir.addActionListener(new ConcluidoListener());
		contentPanel.add(btnConcluir);
		
		
		try {
			dataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
			dataNascimento.setBounds(363, 39, 138, 20);
			dataNascimento.setText(aluno.getDataNascimento());
			contentPanel.add(dataNascimento);
		} catch (ParseException e) {
			
			JOptionPane.showMessageDialog(this, "Data inválida, deve seguir o formato dd/mm/aaaa");
		}
	}
	
	
	private TelaCadastroAluno classe(){
		return this;
	}
	
	private void limpar(){
		nome.setText("");
		matricula.setText("");
		dataNascimento.setText("");
		rg.setText("");
		cpf.setText("");
		mae.setText("");
		pai.setText("");
	}
	
	
	private class AtualizarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				aluno.setNome(nome.getText());
				aluno.setMatricula(matricula.getText());
				aluno.setRg(rg.getText());
				aluno.setCpf(cpf.getText());
				aluno.setDataNascimento(dataNascimento.getText());
				aluno.setMae(mae.getText());
				aluno.setPai(pai.getText());
				Sistema.atualizaAluno(aluno);
				dispose();
				JOptionPane.showMessageDialog(classe(), "Aluno Atualizado com sucesso");
				
			}catch (Exception ex){
				JOptionPane.showMessageDialog(classe(), "Não foi possível atualizar o aluno");
			}
			
			
		}
		
	}
	private class CadastrarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				Sistema.cadastraAluno(nome.getText(),matricula.getText(),dataNascimento.getText(),rg.getText(),cpf.getText(),mae.getText(),pai.getText());
				JOptionPane.showMessageDialog(classe(), "Aluno Cadastrado Com Sucesso");
				limpar();
			}catch (Exception ex){
				JOptionPane.showMessageDialog(classe(), "Erro ao Cadastrar o Aluno");
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

