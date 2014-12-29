package br.edu.ifpb.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import br.edu.ifpb.model.Aluno;
import br.edu.ifpb.model.AlunoExistenteException;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class TelaCadastroAluno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nome;
	private JTextField matricula;
	private JTextField rg;
	private JTextField cpf;
	private JTextField mae;
	private JTextField pai;
	private Aluno aluno;
	private JDateChooser dateNascimento;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		nome.setDocument(new LimitarMaiusculas());
		contentPanel.add(nome);
		nome.setColumns(10);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula:");
		lblMatrcula.setBounds(20, 42, 70, 14);
		contentPanel.add(lblMatrcula);
		
		matricula = new JTextField();
		matricula.setBounds(106, 39, 143, 20);
		matricula.setDocument(new LimitarCharacter());
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
		rg.setDocument(new LimitarCharacter(20));
		contentPanel.add(rg);
		rg.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(329, 97, 25, 14);
		contentPanel.add(lblCpf);
		
		cpf = new JTextField();
		cpf.setBounds(366, 94, 135, 20);
		cpf.setDocument(new LimitarCharacter(11));
		contentPanel.add(cpf);
		cpf.setColumns(10);
		
		JLabel lblNomeDaMe = new JLabel("Nome da M\u00E3e:");
		lblNomeDaMe.setBounds(20, 151, 80, 14);
		contentPanel.add(lblNomeDaMe);
		
		mae = new JTextField();
		mae.setBounds(106, 148, 395, 20);
		mae.setDocument(new LimitarMaiusculas());
		contentPanel.add(mae);
		mae.setColumns(10);
		
		JLabel lblNomeDoPai = new JLabel("Nome do Pai:");
		lblNomeDoPai.setBounds(20, 189, 80, 14);
		contentPanel.add(lblNomeDoPai);
		
		pai = new JTextField();
		pai.setBounds(106, 186, 395, 20);
		pai.setDocument(new LimitarMaiusculas());
		contentPanel.add(pai);
		pai.setColumns(10);
		
		JButton btnCadastrar = new JButton("Salvar");
		btnCadastrar.setBounds(47, 261, 91, 23);
		btnCadastrar.addActionListener(new CadastrarListener());
		contentPanel.add(btnCadastrar);
		
		JButton btnLimparDados = new JButton("Novo");
		btnLimparDados.setBounds(207, 261, 124, 23);
		btnLimparDados.addActionListener(new LimparListener());
		contentPanel.add(btnLimparDados);
		
		JButton btnConcluir = new JButton("Cancelar");
		btnConcluir.setBounds(399, 261, 91, 23);
		btnConcluir.addActionListener(new ConcluidoListener());
		contentPanel.add(btnConcluir);
		
		dateNascimento = new JDateChooser("dd/MM/yyyy" , "##/##/####" , ' ' );
		dateNascimento.setBounds(366, 41, 135, 20);
		contentPanel.add(dateNascimento);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"}));
		comboBox.setSelectedIndex(14);
		comboBox.setBounds(274, 92, 46, 25);
		contentPanel.add(comboBox);
		
		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(255, 96, 14, 16);
		contentPanel.add(lblUf);
		
		
	}
	

	
	//Construtor para editar
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		nome.setDocument(new LimitarMaiusculas());
		nome.setText(aluno.getNome());	
		contentPanel.add(nome);
		nome.setColumns(10);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula:");
		lblMatrcula.setBounds(20, 42, 70, 14);
		contentPanel.add(lblMatrcula);
		
		matricula = new JTextField();
		matricula.setBounds(106, 39, 143, 20);
		matricula.setDocument(new LimitarCharacter());
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
		rg.setDocument(new LimitarCharacter(20));
		rg.setText(aluno.getRg());
		contentPanel.add(rg);
		rg.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(259, 97, 46, 14);
		contentPanel.add(lblCpf);
		
		cpf = new JTextField();
		cpf.setBounds(328, 94, 173, 20);
		cpf.setDocument(new LimitarCharacter(11));
		cpf.setText(aluno.getCpf());
		contentPanel.add(cpf);
		cpf.setColumns(10);
		
		JLabel lblNomeDaMe = new JLabel("Nome da M\u00E3e:");
		lblNomeDaMe.setBounds(20, 151, 80, 14);
		contentPanel.add(lblNomeDaMe);
		
		mae = new JTextField();
		mae.setBounds(106, 148, 395, 20);
		mae.setDocument(new LimitarMaiusculas());
		mae.setText(aluno.getMae());
		contentPanel.add(mae);
		mae.setColumns(10);
		
		JLabel lblNomeDoPai = new JLabel("Nome do Pai:");
		lblNomeDoPai.setBounds(20, 189, 80, 14);
		contentPanel.add(lblNomeDoPai);
		
		pai = new JTextField();
		pai.setBounds(106, 186, 395, 20);
		pai.setDocument(new LimitarMaiusculas());
		pai.setText(aluno.getPai());
		contentPanel.add(pai);
		pai.setColumns(10);
		
		JButton btnCadastrar = new JButton("Salvar");
		btnCadastrar.setBounds(47, 261, 91, 23);
		btnCadastrar.addActionListener(new AtualizarListener());
		contentPanel.add(btnCadastrar);
		
		JButton btnLimparDados = new JButton("Novo");
		btnLimparDados.setBounds(207, 261, 124, 23);
		btnLimparDados.addActionListener(new LimparListener());
		contentPanel.add(btnLimparDados);
		
		JButton btnConcluir = new JButton("Cancelar");
		btnConcluir.setBounds(399, 261, 91, 23);
		btnConcluir.addActionListener(new ConcluidoListener());
		contentPanel.add(btnConcluir);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"}));
		comboBox.setSelectedIndex(14);
		comboBox.setBounds(274, 92, 46, 25);
		contentPanel.add(comboBox);
		
		dateNascimento = new JDateChooser("dd/MM/yyyy" , "##/##/####" ,' ');
		
	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date data = null;
		try {
			data = (Date)formatter.parse(aluno.getDataNascimento());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    
		dateNascimento.setDate(data);
		dateNascimento.setBounds(346, 39, 144, 20);
		contentPanel.add(dateNascimento);
		
	}
	
	
	private TelaCadastroAluno classe(){
		return this;
	}
	
	private void limpar(){
		nome.setText("");
		matricula.setText("");
		dateNascimento.setDate(null);
		rg.setText("");
		cpf.setText("");
		mae.setText("");
		pai.setText("");
	}
	
	
	private class AtualizarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(matricula.getText().equals("")||matricula.getText() == null){
				JOptionPane.showMessageDialog(classe(), "A matricula do aluno não pode ser vazia");
				return;
			}
			
			if(dateNascimento.getDate() == null){
				JOptionPane.showMessageDialog(classe(), "A data de Nascimento não deve ser inválida");
				return;
			}
			try{
				aluno.setNome(nome.getText());
				aluno.setMatricula(matricula.getText());
				aluno.setRg(rg.getText());
				aluno.setCpf(cpf.getText());

				Format formatter = new SimpleDateFormat("dd/MM/yyyy");
				String data = formatter.format(dateNascimento.getDate());

				aluno.setUf((String)comboBox.getSelectedItem());
				aluno.setDataNascimento(data);
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
			if(matricula.getText().equals("")||matricula.getText() == null){
				JOptionPane.showMessageDialog(classe(), "A matricula do aluno não pode ser vazia");
				return;
			}
			if(dateNascimento.getDate() == null){
				JOptionPane.showMessageDialog(classe(), "A data de Nascimento não deve ser inválida");
				return;
			}
			try{
				
				Format formatter = new SimpleDateFormat("dd/MM/yyyy");
				String data = formatter.format(dateNascimento.getDate());
				
				Sistema.cadastraAluno(nome.getText(),matricula.getText(),data,rg.getText(),cpf.getText(),mae.getText(),pai.getText(),(String)comboBox.getSelectedItem());
				JOptionPane.showMessageDialog(classe(), "Aluno Cadastrado Com Sucesso");
				limpar();
			}catch (AlunoExistenteException e2) {
				JOptionPane.showMessageDialog(classe(), e2.getMessage());
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

