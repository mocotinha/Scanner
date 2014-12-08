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

@SuppressWarnings("serial")
public class TelaCadastroAluno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

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
		
		textField = new JTextField();
		textField.setBounds(106, 8, 395, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula:");
		lblMatrcula.setBounds(20, 42, 70, 14);
		contentPanel.add(lblMatrcula);
		
		textField_1 = new JTextField();
		textField_1.setBounds(106, 39, 143, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDataNacimento = new JLabel("Data Nacimento:");
		lblDataNacimento.setBounds(259, 42, 95, 14);
		contentPanel.add(lblDataNacimento);
		
		JLabel lblNewLabel = new JLabel("RG:");
		lblNewLabel.setBounds(20, 97, 46, 14);
		contentPanel.add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setBounds(106, 94, 143, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(259, 97, 46, 14);
		contentPanel.add(lblCpf);
		
		textField_4 = new JTextField();
		textField_4.setBounds(328, 94, 173, 20);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNomeDaMe = new JLabel("Nome da M\u00E3e:");
		lblNomeDaMe.setBounds(20, 151, 80, 14);
		contentPanel.add(lblNomeDaMe);
		
		textField_5 = new JTextField();
		textField_5.setBounds(106, 148, 395, 20);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNomeDoPai = new JLabel("Nome do Pai:");
		lblNomeDoPai.setBounds(20, 189, 80, 14);
		contentPanel.add(lblNomeDoPai);
		
		textField_6 = new JTextField();
		textField_6.setBounds(106, 186, 395, 20);
		contentPanel.add(textField_6);
		textField_6.setColumns(10);
		
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
		
		JFormattedTextField formattedTextField;
		try {
			formattedTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));
			formattedTextField.setBounds(363, 39, 138, 20);
			contentPanel.add(formattedTextField);
		} catch (ParseException e) {
			
			JOptionPane.showMessageDialog(this, "Data inválida, deve seguir o formato dd/mm/aaaa");
		}
	}
	
	private class CadastrarListener implements ActionListener{

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
	
	private class ConcluidoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}

