package br.edu.ifpb.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.controler.SistemaDeTelas;

@SuppressWarnings("serial")
public class PopupCadastroInstituicao extends JFrame{
	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopupInstituicao frame = new PopupInstituicao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public PopupCadastroInstituicao(){
		setAlwaysOnTop(true);
		setTitle("Institui\u00E7\u00E3o");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadas = new JLabel("Cadastro de Institui\u00E7\u00F5es");
		lblCadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadas.setBounds(136, 11, 156, 14);
		contentPane.add(lblCadas);
		
		JLabel lblNomeDaInstituio = new JLabel("Nome da Institui\u00E7\u00E3o:");
		lblNomeDaInstituio.setBounds(10, 56, 156, 14);
		contentPane.add(lblNomeDaInstituio);
		
		textField = new JTextField();
		textField.setBounds(136, 53, 253, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(77, 332, 89, 23);
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.cadastraInstituicao(textField.getText());
				Sistema.validaInstituicao();
				SistemaDeTelas.disposePopupInstituicao();
				dispose();
				textField.setText("");
				JOptionPane.showMessageDialog(null, "Cadastro da Instituição realizado com sucesso!");
				
			}
		});
		contentPane.add(btnSalvar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(253, 332, 89, 23);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		contentPane.add(btnVoltar);
		setMinimumSize(new Dimension(450, 400));
	}
}
