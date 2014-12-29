package br.edu.ifpb.view;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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


	public TelaBuscaAluno(JFrame principal, int tipo) {
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
		
		if(tipo == 1){
			table.addMouseListener(new MouseListener() {
				
				@Override
				public void mousePressed(MouseEvent e) {
					Point p = e.getPoint();
					int row = table.rowAtPoint(p);
					if(e.getClickCount() == 2){
						Sistema.setAluno(((AlunoTableModel)table.getModel()).get(row));
						dispose();
						Sistema.validacaoParaCadastroDossie();
					}
					
				}
				public void mouseClicked(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
			});
		}else{
		
			table.addMouseListener(new MouseListener() {
				
				@Override
				public void mousePressed(MouseEvent e) {
					Point p = e.getPoint();
					int row = table.rowAtPoint(p);
					if(e.getClickCount() == 2){
						SistemaDeTelas.cadastroAluno(((AlunoTableModel)table.getModel()).get(row));
						table.setModel(new AlunoTableModel(Sistema.getAlunos()));
					}
					
				}
				public void mouseClicked(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
			});
		
		}
		
		scrollPane.setViewportView(table);
		
		if(tipo == 1){
			JButton btnSelecionar = new JButton("Selecionar");
			btnSelecionar.setBounds(48, 230, 98, 26);
			btnSelecionar.addActionListener(new SelecionarListener());
			getContentPane().add(btnSelecionar);
		}
		
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(305, 230, 98, 26);
		btnEditar.addActionListener(new EditarListener());
		getContentPane().add(btnEditar);
		
		JButton btnConcluido = new JButton("Cancelar");
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
	private TelaBuscaAluno classe(){
		return this;
	}
	
	private class SelecionarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(classe(), "Selecione um Aluno!");
			}else{
				Sistema.setAluno(((AlunoTableModel)table.getModel()).get(table.getSelectedRow()));
				dispose();
				Sistema.validacaoParaCadastroDossie();
			}
			
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
