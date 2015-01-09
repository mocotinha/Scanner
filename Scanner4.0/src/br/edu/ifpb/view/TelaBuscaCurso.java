package br.edu.ifpb.view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.controler.SistemaDeTelas;
import br.edu.ifpb.model.table.CursoTableModel;

@SuppressWarnings("serial")
public class TelaBuscaCurso extends JDialog {
	private JTextField textField;
	private JTable table;
	private TelaPrincipal principal;


	public TelaBuscaCurso(TelaPrincipal principal, int tipo) {
		super(principal, "Cursos", true);
		this.principal = principal;
		setBounds(100, 100, 800, 312);
		getContentPane().setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(10, 22, 57, 14);
		getContentPane().add(lblBuscar);
		
		textField = new JTextField();
		textField.setDocument(new LimitarMaiusculas());
		textField.setBounds(65, 19, 563, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.addActionListener(new BuscarListener());
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(655, 16, 98, 26);
		btnBuscar.addActionListener(new BuscarListener());
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 750, 158);
		getContentPane().add(scrollPane);
		
		
		
		table = new JTable(new CursoTableModel(Sistema.getCursos()));
		if(tipo == 1){
			table.addMouseListener(new MouseListener() {
				
				@Override
				public void mousePressed(MouseEvent e) {
					Point p = e.getPoint();
					int row = table.rowAtPoint(p);
					if(e.getClickCount() == 2){
						Sistema.setCurso(((CursoTableModel)table.getModel()).get(row));
						dispose();
						TelaBuscaAluno tba = new TelaBuscaAluno(classePrincipal(),1);
						tba.setVisible(true);
						
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
						SistemaDeTelas.cadastroCurso(((CursoTableModel)table.getModel()).get(row));
						table.setModel(new CursoTableModel(Sistema.getCursos()));
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
	
	private TelaPrincipal classePrincipal(){
		return principal;
	}
	
	private class BuscarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			table.setModel(new CursoTableModel(Sistema.getCursoPorNome(textField.getText())));
			
		}
		
	}
	
	private TelaBuscaCurso classe(){
		return this;
	}
	
	private class SelecionarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(classe(), "Selecione um Curso!");
			}else{
				Sistema.setCurso(((CursoTableModel)table.getModel()).get(table.getSelectedRow()));
				TelaBuscaAluno tba = new TelaBuscaAluno(principal,1);
				tba.setVisible(true);
				dispose();
			}
			
		}
		
	}
	
	private class EditarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(classe(), "Selecione um Curso!");
			}else{
				SistemaDeTelas.cadastroCurso(((CursoTableModel)table.getModel()).get(table.getSelectedRow()));
				table.setModel(new CursoTableModel(Sistema.getCursos()));
			}
			
		}
		
	}
	
	private class ConcluidoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
	
}
