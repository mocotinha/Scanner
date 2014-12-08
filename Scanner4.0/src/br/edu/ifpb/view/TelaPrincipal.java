package br.edu.ifpb.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.controler.SistemaDeTelas;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class TelaPrincipal extends JFrame {

	

	
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TelaPrincipal() {
		
		configurePLAF();
		setTitle("Gestão de Documentos - Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(400, 400));
		
		
		String linha = "pref, center:pref:grow";
		String coluna = "pref:grow,left:pref:grow";
		
		FormLayout form = new FormLayout(coluna,linha);
	/*	FormDebugPanel formDebugPanel = new FormDebugPanel();
		PanelBuilder pb = new PanelBuilder(form,formDebugPanel);*/
		
		PanelBuilder pb = new PanelBuilder(form);
		
		JMenu jmAluno = new JMenu("Aluno");
		JMenuItem cadAlunoItem = new JMenuItem("Cadastro de Aluno");
		cadAlunoItem.addActionListener(new CadastroDeAlunoListener());
		JMenuItem listaAlunoItem = new JMenuItem("Busca Aluno");
		listaAlunoItem.addActionListener(new ListaAlunoListener());
		
		jmAluno.add(cadAlunoItem);
		jmAluno.add(listaAlunoItem);
		
		JMenu jmCurso = new JMenu("Curso");
		JMenuItem cadCursoItem = new JMenuItem("Cadastro de Curso");
		cadCursoItem.addActionListener(new CadastroDeCursoListener());
		JMenuItem listaCursosItem = new JMenuItem("Busca Cursos");
		listaCursosItem.addActionListener(new ListaCursoListener());
		jmCurso.add(cadCursoItem);
		jmCurso.add(listaCursosItem);
		
		JMenu jmInst = new JMenu("Instituição");
		JMenuItem cadInstItem = new JMenuItem("Cadastro de Instituição");
		cadInstItem.addActionListener(new CadastroDeInstituicaoListener());
		JMenuItem listaInstItem = new JMenuItem("Busca Instiuição");
		listaInstItem.addActionListener(new ListaInstListener());
		jmInst.add(cadInstItem);
		jmInst.add(listaInstItem);
		
		JMenu jmDocumentos = new JMenu("Dossiê");
		final JMenuItem cadDoc = new JMenuItem("Cadastrar Dossiê");
		cadDoc.addActionListener(new CadastroDossieListener());
		JMenuItem busDoc = new JMenuItem("Buscar Dossiê");
		busDoc.addActionListener(new ListaDossieListener());
		jmDocumentos.add(cadDoc);
		jmDocumentos.add(busDoc);
		

		JMenu jm2 = new JMenu("Usuario");
		JMenuItem cadUsu = new JMenuItem("Cadastrar Usuario");
		cadUsu.addActionListener(new CadastroUsuarioListener());
		JMenuItem gerUsu = new JMenuItem("Gerenciar Usuarios");
		gerUsu.addActionListener(new ListarUsuarioListener());
		jm2.add(cadUsu);
		jm2.add(gerUsu);
		
		JMenuBar jmBar = new JMenuBar();
		jmBar.add(jmAluno);
		jmBar.add(jmCurso);
		jmBar.add(jmDocumentos);
		jmBar.add(jmInst);
		jmBar.add(jm2);
		
		JMenu jm3 = new JMenu("Sair");
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new SairListener());
		jm3.add(sair);
		jmBar.add(jm3);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(TelaLogin.class.getResource("/images/icon/logo.png")));
		logo.setBounds(107, 49, 46, 14);
		
		
		pb.add(jmBar,CC.xyw(1, 1,2));
		pb.add(logo,CC.xy(2, 2));
		
		getContentPane().add(pb.getPanel());
	}
	


	@SuppressWarnings("unused")
	private JFrame getInstance() {
		
		return this;
	}
	
	private static void configurePLAF() {
		  try {
			  UIManager.setLookAndFeel(com.jgoodies.looks.windows.WindowsLookAndFeel.class.getName());
//			 UIManager.setLookAndFeel(new Plastic3DLookAndFeel()); 
		   	  com.jgoodies.looks.Options.setPopupDropShadowEnabled(true);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	

	/*
	 * Ações dos Itens do Menu.  
	 */
	
	
	private class CadastroDeAlunoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.cadastroAluno();
			
		}
		
	}
	
	private class CadastroDeCursoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.cadastroCurso();
			
		}
	}
	
	private class CadastroDeInstituicaoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.cadastroInstituicao();
		}
		
	}
	
	private class ListaAlunoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.listaAlunos();
			
		}
		
	}
	
	private class ListaCursoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.listaCursos();
			
		}
		
	}
	
	private class ListaInstListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.listaInstituicao();
			
		}
		
	}
	
	private class CadastroDossieListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Sistema.cadastroDossie();
			
		}
		
	}
	
	private class ListaDossieListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.listaDossie();
			
		}
		
	}
	
	private class CadastroUsuarioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.cadastroUsuario();
			
		}
		
	}


	private class ListarUsuarioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.listaUsuarios();
			
		}
		
	}
	
	private class SairListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SistemaDeTelas.sair();
			
		}
		
	}
}
