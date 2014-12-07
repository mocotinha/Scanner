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
public class PopupCadastraCurso extends JFrame{
	private JPanel contentPane;
	private JTextField nome;
	private JTextField anoInicio;
	private JTextField anoFim;
	private JTextField situacao;
	private JTextField nivel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopupCadastraCurso frame = new PopupCadastraCurso();
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
	
	public PopupCadastraCurso(){
		setAlwaysOnTop(true);
		setTitle("Curso");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 450, 400);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadas = new JLabel("Cadastro de Cursos");
		lblCadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadas.setBounds(136, 11, 156, 14);
		contentPane.add(lblCadas);
		
		JLabel lblNomeDaInstituio = new JLabel("Curso:");
		lblNomeDaInstituio.setBounds(10, 56, 126, 14);
		contentPane.add(lblNomeDaInstituio);
		
		nome = new JTextField();
		nome.setBounds(136, 53, 253, 20);
		contentPane.add(nome);
		nome.setColumns(10);
		
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(253, 332, 89, 23);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		contentPane.add(btnVoltar);
		
		JLabel lblAnoInicio = new JLabel("Ano Inicio:");
		lblAnoInicio.setBounds(10, 93, 126, 14);
		contentPane.add(lblAnoInicio);
		
		anoInicio = new JTextField();
		anoInicio.setBounds(136, 90, 253, 20);
		contentPane.add(anoInicio);
		anoInicio.setColumns(10);
		
		JLabel lblAnoFim = new JLabel("Ano Fim:");
		lblAnoFim.setBounds(10, 133, 126, 14);
		contentPane.add(lblAnoFim);
		
		anoFim = new JTextField();
		anoFim.setBounds(136, 130, 253, 20);
		contentPane.add(anoFim);
		anoFim.setColumns(10);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituao.setBounds(10, 174, 126, 14);
		contentPane.add(lblSituao);
		
		situacao = new JTextField();
		situacao.setBounds(136, 171, 253, 20);
		contentPane.add(situacao);
		situacao.setColumns(10);
		
		JLabel lblNivel = new JLabel("Nivel:");
		lblNivel.setBounds(10, 219, 126, 14);
		contentPane.add(lblNivel);
		
		nivel = new JTextField();
		nivel.setBounds(136, 216, 253, 20);
		contentPane.add(nivel);
		nivel.setColumns(10);
		setMinimumSize(new Dimension(450, 400));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(77, 332, 89, 23);
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.cadastraCurso(nome.getText(),anoInicio.getText(),anoFim.getText(),situacao.getText(),nivel.getText());
				Sistema.validaCurso();
				SistemaDeTelas.disposePopupCurso();
				dispose();
				JOptionPane.showMessageDialog(null, "Cadastro de Curso realizado com sucesso!");
				
			}
		});
		contentPane.add(btnSalvar);
	}
}
