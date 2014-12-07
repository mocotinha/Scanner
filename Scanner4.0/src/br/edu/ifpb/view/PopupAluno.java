package br.edu.ifpb.view;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.xswingx.PromptSupport;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.controler.SistemaDeTelas;
import br.edu.ifpb.model.table.AlunoTableModel;

@SuppressWarnings("serial")
public class PopupAluno extends JFrame {

	private JPanel contentPane;
	private JTable table;
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
	public PopupAluno() {
		
		setAlwaysOnTop(true);
		setTitle("Institui\u00E7\u00E3o");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setMinimumSize(new Dimension(900, 400));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 864, 199);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new AlunoTableModel(Sistema.getAlunos()));
		scrollPane.setViewportView(table);
		
		JButton btnCadastrar = new JButton("Selecionar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setAluno(((AlunoTableModel) table.getModel()).get(table.getSelectedRow()));
				Sistema.validaAluno();
				setVisible(false);
			}
		});
		btnCadastrar.setBounds(27, 307, 115, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnCadastrarAluno = new JButton("Cadastrar");
		btnCadastrarAluno.setBounds(211, 307, 115, 23);
		btnCadastrarAluno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SistemaDeTelas.telaCadastroAluno();
				
			}
		});
		contentPane.add(btnCadastrarAluno);
		
		JButton btnEditarAluno = new JButton("Editar");
		btnEditarAluno.setBounds(440, 307, 125, 23);
		contentPane.add(btnEditarAluno);
		
		
		textField = new JTextField();
		PromptSupport.setPrompt("Buscar", textField);
		textField.setBounds(10, 11, 614, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(667, 10, 89, 23);
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(new AlunoTableModel(Sistema.buscaAluno(textField.getText())));
				
			}
		});
		contentPane.add(btnBuscar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(683, 307, 143, 23);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		contentPane.add(btnVoltar);
	}
}
