package br.edu.ifpb.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.edu.ifpb.controler.Sistema;

@SuppressWarnings("serial")
public class TelaClassificacao extends JDialog {

	
	private JTextField textField;


	public TelaClassificacao(TelaPrincipal principal) {
		super(principal, "Classificação do Dossiê");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		
		JLabel lblClassificaoDoDossi = new JLabel("Classifica\u00E7\u00E3o do Dossi\u00EA:");
		lblClassificaoDoDossi.setBounds(38, 74, 148, 16);
		getContentPane().add(lblClassificaoDoDossi);
		
		textField = new JTextField();
		textField.setBounds(196, 71, 154, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnClassificarODossi = new JButton("Classificar o Dossi\u00EA");
		btnClassificarODossi.addActionListener(new ClassificarListener());
		btnClassificarODossi.setBounds(120, 146, 165, 23);
		getContentPane().add(btnClassificarODossi);
		
		JLabel lblObsDeveSeguir = new JLabel("OBS: Deve seguir a tabela de classifica\u00E7\u00E3o de Documentos. ");
		lblObsDeveSeguir.setHorizontalAlignment(SwingConstants.CENTER);
		lblObsDeveSeguir.setBounds(10, 204, 414, 16);
		getContentPane().add(lblObsDeveSeguir);
		
		JLabel lblncampoUtilizadosPor = new JLabel("Campo utilizados por profissionais do arquivo.");
		lblncampoUtilizadosPor.setBounds(82, 231, 268, 16);
		getContentPane().add(lblncampoUtilizadosPor);
		
	}
	
	private class ClassificarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Sistema.classificaDossie(textField.getText());
			dispose();
			Sistema.validacaoParaCadastroDossie();
			
			
		}
		
	}
}
