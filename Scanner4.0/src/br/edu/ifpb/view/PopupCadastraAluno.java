package br.edu.ifpb.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.controler.SistemaDeTelas;



@SuppressWarnings("serial")
public class PopupCadastraAluno extends JFrame{
	private JPanel contentPane;
	private JTextField matricula;
	private JTextField nome;
	private JTextField cpf;
	private JTextField rg;
	private JTextField pai;
	private JTextField mae;
	private JTextField dataNascimento;
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
	
	public PopupCadastraAluno(){
		setAlwaysOnTop(true);
		setTitle("Aluno");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 450, 400);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadas = new JLabel("Cadastro de Aluno");
		lblCadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadas.setBounds(136, 11, 156, 14);
		contentPane.add(lblCadas);
		
		JLabel lblNomeDaInstituio = new JLabel("Matricula:");
		lblNomeDaInstituio.setBounds(10, 56, 127, 14);
		contentPane.add(lblNomeDaInstituio);
		
		matricula = new JTextField();
		matricula.setBounds(136, 53, 253, 20);
		contentPane.add(matricula);
		matricula.setColumns(10);
		

		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(253, 332, 89, 23);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		contentPane.add(btnVoltar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 81, 127, 14);
		contentPane.add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(136, 78, 253, 20);
		contentPane.add(nome);
		nome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 106, 127, 14);
		contentPane.add(lblCpf);
		
		cpf = new JTextField();
		cpf.setBounds(136, 106, 253, 20);
		contentPane.add(cpf);
		cpf.setColumns(10);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setBounds(10, 131, 127, 14);
		contentPane.add(lblRg);
		
		rg = new JTextField();
		rg.setBounds(136, 131, 253, 20);
		contentPane.add(rg);
		rg.setColumns(10);
		
		JLabel lblNomePai = new JLabel("Nome Pai:");
		lblNomePai.setBounds(10, 159, 127, 14);
		contentPane.add(lblNomePai);
		
		pai = new JTextField();
		pai.setBounds(136, 156, 253, 20);
		contentPane.add(pai);
		pai.setColumns(10);
		
		JLabel lblNomeDaMe = new JLabel("Nome da M\u00E3e:");
		lblNomeDaMe.setBounds(10, 184, 127, 14);
		contentPane.add(lblNomeDaMe);
		
		mae = new JTextField();
		mae.setBounds(136, 181, 253, 20);
		contentPane.add(mae);
		mae.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(10, 212, 127, 14);
		contentPane.add(lblDataDeNascimento);
		
		dataNascimento = new JTextField();
		dataNascimento.setBounds(136, 209, 253, 20);
		contentPane.add(dataNascimento);
		dataNascimento.setColumns(10);
		setMinimumSize(new Dimension(450, 400));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(77, 332, 89, 23);
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.cadastraAluno(matricula.getText(),nome.getText(),cpf.getText(),rg.getText(),pai.getText(),mae.getText(),dataNascimento.getText());
				matricula.setText("");
				nome.setText("");
				cpf.setText("");
				rg.setText("");
				pai.setText("");
				mae.setText("");
				dataNascimento.setText("");
				Sistema.validaAluno();
				SistemaDeTelas.disposePopupAluno();
				dispose();
				JOptionPane.showMessageDialog(null, "Cadastro de Aluno realizado com sucesso!");
			}
		});
		contentPane.add(btnSalvar);
	}
}
