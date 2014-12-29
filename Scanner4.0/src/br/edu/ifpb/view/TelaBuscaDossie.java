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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.controler.SistemaDeTelas;
import br.edu.ifpb.model.table.DossieTableModel;

@SuppressWarnings("serial")
public class TelaBuscaDossie extends JDialog {
	private JTextField textField;
	private JTable table;


	public TelaBuscaDossie(JFrame principal) {
		super(principal, "Dossiê", true);
		
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
		btnBuscar.addActionListener(new BuscarDossieActionListener());
		btnBuscar.setBounds(655, 16, 98, 26);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 750, 158);
		getContentPane().add(scrollPane);
		
		
		table = new JTable(new DossieTableModel(Sistema.getDossies()));
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				Point p = e.getPoint();
				int row = table.rowAtPoint(p);
				if(e.getClickCount() == 2){
					SistemaDeTelas.cadastraDossie(((DossieTableModel)table.getModel()).get(row));
					table.setModel(new DossieTableModel(Sistema.getDossies()));
				}
				
			}
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		scrollPane.setViewportView(table);
		
		/*JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setBounds(48, 230, 98, 26);
		btnSelecionar.addActionListener(new SelecionarListener());
		getContentPane().add(btnSelecionar);*/
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(305, 230, 98, 26);
		btnEditar.addActionListener(new EditarListener());
		getContentPane().add(btnEditar);
		
		JButton btnConcluido = new JButton("Cancelar");
		btnConcluido.setBounds(623, 230, 98, 26);
		btnConcluido.addActionListener(new ConcluidoListener());
		getContentPane().add(btnConcluido);

	}
	
	private class BuscarDossieActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			table.setModel(new DossieTableModel(Sistema.getDossiePorValor(textField.getText())));
			
		}
		
	}
	
	/*private class SelecionarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}*/
	
	private class EditarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.cadastraDossie(((DossieTableModel)table.getModel()).get(table.getSelectedRow()));
			table.setModel(new DossieTableModel(Sistema.getDossies()));
			
		}
		
	}
	
	private class ConcluidoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
	
}
