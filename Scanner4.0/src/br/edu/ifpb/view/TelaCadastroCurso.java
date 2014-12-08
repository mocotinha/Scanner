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

@SuppressWarnings("serial")
public class TelaCadastroCurso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

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
		
		textField = new JTextField();
		textField.setBounds(141, 36, 351, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNvel = new JLabel("N\u00EDvel:");
		lblNvel.setBounds(33, 96, 46, 14);
		contentPanel.add(lblNvel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(141, 93, 351, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAnoCriao = new JLabel("Ano Cria\u00E7\u00E3o:");
		lblAnoCriao.setBounds(33, 154, 83, 14);
		contentPanel.add(lblAnoCriao);
		
		JFormattedTextField formattedTextField;
		try {
			formattedTextField = new JFormattedTextField(new MaskFormatter("####"));
			formattedTextField.setBounds(141, 151, 73, 20);
			contentPanel.add(formattedTextField);
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Ano Criação Inválido deve seguir o modelo AAAA. EX:2014");
		}
		
		JLabel lblAnoFinalizao = new JLabel("Ano Finaliza\u00E7\u00E3o:");
		lblAnoFinalizao.setBounds(257, 154, 99, 14);
		contentPanel.add(lblAnoFinalizao);
		
		JFormattedTextField formattedTextField_1;
		try {
			formattedTextField_1 = new JFormattedTextField(new MaskFormatter("####"));
			formattedTextField_1.setBounds(366, 151, 73, 23);
			contentPanel.add(formattedTextField_1);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Ano Fim Inválido deve seguir o modelo AAAA. EX:2014");
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
