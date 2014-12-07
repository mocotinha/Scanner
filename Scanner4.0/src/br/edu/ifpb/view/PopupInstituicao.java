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
import br.edu.ifpb.model.table.InstituicaoTableModel;

@SuppressWarnings("serial")
public class PopupInstituicao extends JFrame {

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
	public PopupInstituicao() {
		setAlwaysOnTop(true);
		setTitle("Institui\u00E7\u00E3o");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setMinimumSize(new Dimension(450, 400));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 424, 142);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new InstituicaoTableModel(Sistema.getInstituicoes()));
		scrollPane.setViewportView(table);
		
		JButton btnCadastrar = new JButton("Selecionar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setInstituicao(((InstituicaoTableModel) table.getModel()).get(table.getSelectedRow()));
				Sistema.validaInstituicao();
				setVisible(false);
			}
		});
		btnCadastrar.setBounds(10, 232, 100, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnCadIns = new JButton("Cadastrar");
		btnCadIns.setBounds(120, 232, 100, 23);
		btnCadIns.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SistemaDeTelas.telaCadastroInstituicao();
				
				
			}

			
		});
		contentPane.add(btnCadIns);
		
		JButton btnEditarIns = new JButton("Editar");
		btnEditarIns.setBounds(230, 232, 100, 23);
		contentPane.add(btnEditarIns);
		
		textField = new JTextField();
		PromptSupport.setPrompt("Buscar", textField);
		textField.setBounds(10, 11, 317, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(335, 10, 89, 23);
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(new InstituicaoTableModel(Sistema.getInstituicoes(textField.getText())));
				
			}
		});
		contentPane.add(btnBuscar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(345, 232, 89, 23);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		contentPane.add(btnVoltar);
	}
	

}
