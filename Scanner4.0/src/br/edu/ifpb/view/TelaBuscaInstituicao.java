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
import br.edu.ifpb.model.table.InstituicaoTableModel;

@SuppressWarnings("serial")
public class TelaBuscaInstituicao extends JDialog {
	private JTextField textField;
	private JTable table;
	private TelaPrincipal principal;


	public TelaBuscaInstituicao(TelaPrincipal principal, int tipo) {
		super(principal, "Instituições", true);
		this.principal = principal;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(23, 29, 55, 14);
		getContentPane().add(lblBuscar);
		
		textField = new JTextField();
		textField.setDocument(new LimitarMaiusculas());
		textField.setBounds(88, 26, 209, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.addActionListener(new BuscarListener());
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(326, 23, 98, 26);
		btnBuscar.addActionListener(new BuscarListener());
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 65, 401, 139);
		getContentPane().add(scrollPane);
		
		table = new JTable(new InstituicaoTableModel(Sistema.getInstituicoes()));
		if(tipo==1){
			table.addMouseListener(new MouseListener() {
				
				@Override
				public void mousePressed(MouseEvent e) {
					Point p = e.getPoint();
					int row = table.rowAtPoint(p);
					if(e.getClickCount() == 2){
						Sistema.setInstituicao(((InstituicaoTableModel)table.getModel()).get(row));
						dispose();
						TelaBuscaCurso tbc = new TelaBuscaCurso(classePrincipal(),1);
						tbc.setVisible(true);
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
						SistemaDeTelas.cadastroInstituicao(((InstituicaoTableModel)table.getModel()).get(row));
						table.setModel(new InstituicaoTableModel(Sistema.getInstituicoes()));
						
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
			btnSelecionar.setBounds(23, 224, 98, 26);
			btnSelecionar.addActionListener(new SelecionarListener());
			getContentPane().add(btnSelecionar);
		}
		
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(166, 224, 98, 26);
		btnEditar.addActionListener(new EditarListener());
		getContentPane().add(btnEditar);
		
		JButton btnConcluir = new JButton("Cancelar");
		btnConcluir.setBounds(326, 224, 98, 26);
		btnConcluir.addActionListener(new ConcluidoListener());
		getContentPane().add(btnConcluir);

	}
	
	private TelaPrincipal classePrincipal(){
		return this.principal;
	}
	
	private class BuscarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			table.setModel(new InstituicaoTableModel(Sistema.getInstituicaoPorNome(textField.getText())));
			
		}
		
	}

	private TelaBuscaInstituicao classe(){
		return this;
	}
	
	private class SelecionarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(classe(), "Selecione uma Instituição!");
			}else{
				Sistema.setInstituicao(((InstituicaoTableModel)table.getModel()).get(table.getSelectedRow()));
				dispose();
				TelaBuscaCurso tbc = new TelaBuscaCurso(principal,1);
				tbc.setVisible(true);
			}
			
			
			
		}
		
	}
	
	private class EditarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(classe(), "Selecione uma Instituição!");
			}else{
				SistemaDeTelas.cadastroInstituicao(((InstituicaoTableModel)table.getModel()).get(table.getSelectedRow()));
				table.setModel(new InstituicaoTableModel(Sistema.getInstituicoes()));
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
