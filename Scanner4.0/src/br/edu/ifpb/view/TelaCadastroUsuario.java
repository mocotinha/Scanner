package br.edu.ifpb.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.model.Usuario;

@SuppressWarnings("serial")
public class TelaCadastroUsuario extends JDialog {
	private JTextField nome;
	private JTextField login;
	private JPasswordField password;
	private JPasswordField password2;
	@SuppressWarnings("rawtypes")
	private JComboBox tipo;
	private Usuario us;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaCadastroUsuario dialog = new TelaCadastroUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaCadastroUsuario(JFrame principal) {
		super(principal, "Cadastro de Usuários", true);
		setBounds(100, 100, 481, 315);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(21, 25, 67, 14);
		getContentPane().add(lblNome);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(21, 62, 67, 14);
		getContentPane().add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(21, 100, 67, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar Password:");
		lblConfirmarPassword.setBounds(21, 141, 127, 14);
		getContentPane().add(lblConfirmarPassword);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(21, 188, 46, 14);
		getContentPane().add(lblTipo);
		
		tipo = new JComboBox();
		tipo.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Arquivista"}));
		tipo.setBounds(150, 185, 196, 20);
		getContentPane().add(tipo);
		
		nome = new JTextField();
		nome.setBounds(150, 22, 196, 20);
		getContentPane().add(nome);
		nome.setColumns(10);
		
		login = new JTextField();
		login.setBounds(150, 59, 196, 20);
		getContentPane().add(login);
		login.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(150, 97, 196, 17);
		getContentPane().add(password);
		
		password2 = new JPasswordField();
		password2.setBounds(149, 138, 197, 20);
		getContentPane().add(password2);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(36, 243, 103, 23);
		btnCadastrar.addActionListener(new CadastrarListener());
		getContentPane().add(btnCadastrar);
		
		JButton btnNewButton = new JButton("Limpar Dados");
		btnNewButton.setBounds(157, 243, 118, 23);
		btnNewButton.addActionListener(new LimparListener());
		getContentPane().add(btnNewButton);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(298, 241, 98, 26);
		btnConcluir.addActionListener(new ConcluidoListener());
		getContentPane().add(btnConcluir);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaCadastroUsuario(JFrame principal, Usuario us) {
		super(principal, "Cadastro de Usuários", true);
		this.us = us;
		setBounds(100, 100, 481, 315);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(21, 25, 67, 14);
		getContentPane().add(lblNome);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(21, 62, 67, 14);
		getContentPane().add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(21, 100, 67, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar Password:");
		lblConfirmarPassword.setBounds(21, 141, 127, 14);
		getContentPane().add(lblConfirmarPassword);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(21, 188, 46, 14);
		getContentPane().add(lblTipo);
		
		tipo = new JComboBox();
		tipo.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Arquivista"}));
		tipo.setBounds(150, 185, 196, 20);
		getContentPane().add(tipo);
		
		nome = new JTextField();
		nome.setBounds(150, 22, 196, 20);
		nome.setText(us.getNome());
		getContentPane().add(nome);
		nome.setColumns(10);
		
		login = new JTextField();
		login.setBounds(150, 59, 196, 20);
		login.setText(us.getLogin());
		getContentPane().add(login);
		login.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(150, 97, 196, 17);
		password.setEnabled(false);
		password.setEditable(false);
		getContentPane().add(password);
		
		password2 = new JPasswordField();
		password2.setBounds(149, 138, 197, 20);
		password2.setEnabled(false);
		password2.setEditable(false);
		getContentPane().add(password2);
		
		JButton btnCadastrar = new JButton("Atualizar");
		btnCadastrar.setBounds(36, 243, 103, 23);
		btnCadastrar.addActionListener(new AtualizarListener());
		getContentPane().add(btnCadastrar);
		
		JButton btnNewButton = new JButton("Limpar Dados");
		btnNewButton.setBounds(157, 243, 118, 23);
		btnNewButton.addActionListener(new LimparListener());
		getContentPane().add(btnNewButton);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(298, 241, 98, 26);
		btnConcluir.addActionListener(new ConcluidoListener());
		getContentPane().add(btnConcluir);
		
	}
	
	
	
	private TelaCadastroUsuario classe(){
		return this;
	}
	
	private void limpar(){
		nome.setText("");
		login.setText("");
		password.setText("");
		password2.setText("");
	}
	
	private class AtualizarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				us.setNome(nome.getText());
				us.setLogin(login.getText());
				if(tipo.getSelectedItem().equals("Administrador")){
					us.setTipo(1);
				}else{
					us.setTipo(0);
				}
				Sistema.atualizaUsuario(us);
				dispose();
				JOptionPane.showMessageDialog(classe(), "Usuário Atualizado com sucesso");
				
			}catch (Exception ex){
				JOptionPane.showMessageDialog(classe(), "Não foi possível atualizar o usuário");
			}
			
		}
		
		
	}
	
	private class CadastrarListener implements ActionListener{

		/*
		 * Tipo 1 representa Administrador e Tipo 2 representa Arquivista
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				if(tipo.getSelectedItem().equals("Administrador")){
					Sistema.cadastraUsuario(nome.getText(), login.getText(), new String(password.getPassword()),1);
				}else{
					Sistema.cadastraUsuario(nome.getText(), login.getText(), new String(password.getPassword()),0);
				}
				
				JOptionPane.showMessageDialog(classe(), "Usuário Cadastrado com Sucesso!");
				limpar();
			}catch (Exception ex){
				JOptionPane.showMessageDialog(classe(), "Erro ao cadastrar o Usuario!");
			}
			
			
		}
		
	}
	
	private class LimparListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			limpar();
			
		}
		
	}
	
	private class ConcluidoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
}
