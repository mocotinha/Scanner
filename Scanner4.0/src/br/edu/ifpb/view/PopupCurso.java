package br.edu.ifpb.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
import br.edu.ifpb.model.Curso;
import br.edu.ifpb.model.table.CursoTableModel;

@SuppressWarnings("serial")
public class PopupCurso extends JFrame {

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
	public PopupCurso() {
		setAlwaysOnTop(true);
		setTitle("Institui\u00E7\u00E3o");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setMinimumSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 564, 142);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new CursoTableModel((List<Curso>)new ArrayList<Curso>()));
		scrollPane.setViewportView(table);
		
		JButton selecionarCurso = new JButton("Selecionar");
		selecionarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setCurso(((CursoTableModel) table.getModel()).get(table.getSelectedRow()));
				Sistema.validaCurso();
				setVisible(false);
			}
		});
		selecionarCurso.setBounds(10, 232, 115, 23);
		contentPane.add(selecionarCurso);
		
		JButton cadastrarCurso = new JButton("Cadastrar");
		cadastrarCurso.setBounds(153, 232, 115, 23);
		cadastrarCurso.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SistemaDeTelas.telaCadastroCurso();
				
			}
		});
		contentPane.add(cadastrarCurso);
		
		JButton editarCutso = new JButton("Editar");
		editarCutso.setBounds(299, 232, 115, 23);
		contentPane.add(editarCutso);
		
		textField = new JTextField();
		PromptSupport.setPrompt("Buscar", textField);
		
		textField.setBounds(10, 11, 417, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(437, 10, 115, 23);
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				table.setModel(new CursoTableModel(Sistema.getCursoByName(textField.getText())));
				
			}
		});
		contentPane.add(btnBuscar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(437, 232, 137, 23);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		contentPane.add(btnVoltar);
	}

	public void setCursos(int id) {
//		table.setModel(new CursoTableModel(Sistema.getCurso(id)));
		
	}
}
