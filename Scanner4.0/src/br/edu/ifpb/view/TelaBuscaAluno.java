package br.edu.ifpb.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.controler.SistemaDeTelas;
import br.edu.ifpb.model.table.AlunoTableModel;

@SuppressWarnings("serial")
public class TelaBuscaAluno extends JDialog {
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBuscaCurso dialog = new TelaBuscaCurso(null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public TelaBuscaAluno(JFrame principal) {
		super(principal, "Alunos", true);
		setBounds(100, 100, 800, 312);
		getContentPane().setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(10, 22, 57, 14);
		getContentPane().add(lblBuscar);
		
		textField = new JTextField();
		textField.setBounds(65, 19, 563, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(655, 16, 98, 26);
		btnBuscar.addActionListener(new BuscarListener());
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 750, 158);
		getContentPane().add(scrollPane);
		
		
		table = new JTable(new AlunoTableModel(Sistema.getAlunos()));
		scrollPane.setViewportView(table);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setBounds(48, 230, 98, 26);
		btnSelecionar.addActionListener(new SelecionarListener());
		getContentPane().add(btnSelecionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(305, 230, 98, 26);
		btnEditar.addActionListener(new EditarListener());
		getContentPane().add(btnEditar);
		
		JButton btnConcluido = new JButton("Concluido");
		btnConcluido.setBounds(623, 230, 98, 26);
		btnConcluido.addActionListener(new ConcluidoListener());
		getContentPane().add(btnConcluido);

	}
	
	private class BuscarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			table.setModel(new AlunoTableModel(Sistema.getAlunosPorNome(textField.getText())));
			
		}
		
	}
	
	private class SelecionarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Sistema.setAluno(((AlunoTableModel)table.getModel()).get(table.getSelectedRow()));
			dispose();
			
		}
		
	}
	
	private class EditarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.cadastroAluno(((AlunoTableModel)table.getModel()).get(table.getSelectedRow()));
			table.setModel(new AlunoTableModel(Sistema.getAlunos()));
			
		}
		
	}
	
	private class ConcluidoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
	
}
