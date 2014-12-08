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
import br.edu.ifpb.model.table.InstituicaoTableModel;

@SuppressWarnings("serial")
public class TelaBuscaInstituicao extends JDialog {
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBuscaInstituicao dialog = new TelaBuscaInstituicao(null);
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
	public TelaBuscaInstituicao(JFrame principal) {
		super(principal, "Instituições", true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(23, 29, 55, 14);
		getContentPane().add(lblBuscar);
		
		textField = new JTextField();
		textField.setBounds(88, 26, 209, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(326, 23, 98, 26);
		btnBuscar.addActionListener(new BuscarListener());
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 65, 401, 139);
		getContentPane().add(scrollPane);
		
		table = new JTable(new InstituicaoTableModel(Sistema.getInstituicoes()));
		scrollPane.setViewportView(table);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setBounds(23, 224, 98, 26);
		btnSelecionar.addActionListener(new SelecionarListener());
		getContentPane().add(btnSelecionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(166, 224, 98, 26);
		btnEditar.addActionListener(new EditarListener());
		getContentPane().add(btnEditar);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(326, 224, 98, 26);
		btnConcluir.addActionListener(new ConcluidoListener());
		getContentPane().add(btnConcluir);

	}
	
	private class BuscarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			table.setModel(new InstituicaoTableModel(Sistema.getInstituicaoPorNome(textField.getText())));
			
		}
		
	}
	
	private class SelecionarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Sistema.setInstituicao(((InstituicaoTableModel)table.getModel()).get(table.getSelectedRow()));
			dispose();
			
		}
		
	}
	
	private class EditarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.cadastroInstituicao(((InstituicaoTableModel)table.getModel()).get(table.getSelectedRow()));
			table.setModel(new InstituicaoTableModel(Sistema.getInstituicoes()));
			
		}
		
	}
	
	private class ConcluidoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
}
