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
import br.edu.ifpb.model.table.UsuarioTableModel;

@SuppressWarnings("serial")
public class TelaBuscaUsuario extends JDialog {
	private JTable table;
	private JTextField textField;


	public TelaBuscaUsuario(JFrame principal) {
		super(principal, "Usuarios", true);
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
		
		
		table = new JTable(new UsuarioTableModel(Sistema.getUsuarios()));
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				Point p = e.getPoint();
				int row = table.rowAtPoint(p);
				if(e.getClickCount() == 2){
					SistemaDeTelas.cadastraUsuario(((UsuarioTableModel)table.getModel()).get(row));
					table.setModel(new UsuarioTableModel(Sistema.getUsuarios()));
				}
				
			}
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		scrollPane.setViewportView(table);
		
		
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
			table.setModel(new UsuarioTableModel(Sistema.getUsuariosPorNome(textField.getText())));
			
		}
		
	}
	private TelaBuscaUsuario classe(){
		return this;
	}
	
	
	private class EditarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(classe(), "Selecione um Usuário!");
			}else{
				SistemaDeTelas.cadastraUsuario(((UsuarioTableModel)table.getModel()).get(table.getSelectedRow()));
				table.setModel(new UsuarioTableModel(Sistema.getUsuarios()));
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
