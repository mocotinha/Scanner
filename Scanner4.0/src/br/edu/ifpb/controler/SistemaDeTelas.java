package br.edu.ifpb.controler;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.edu.ifpb.model.Aluno;
import br.edu.ifpb.model.Curso;
import br.edu.ifpb.model.Dossie;
import br.edu.ifpb.model.Instituicao;
import br.edu.ifpb.model.Usuario;
import br.edu.ifpb.view.TelaBuscaAluno;
import br.edu.ifpb.view.TelaBuscaCurso;
import br.edu.ifpb.view.TelaBuscaDossie;
import br.edu.ifpb.view.TelaBuscaInstituicao;
import br.edu.ifpb.view.TelaBuscaUsuario;
import br.edu.ifpb.view.TelaCadastroAluno;
import br.edu.ifpb.view.TelaCadastroCurso;
import br.edu.ifpb.view.TelaCadastroDossie;
import br.edu.ifpb.view.TelaCadastroInstituicao;
import br.edu.ifpb.view.TelaCadastroUsuario;
import br.edu.ifpb.view.TelaLogin;
import br.edu.ifpb.view.TelaPrincipal;

public class SistemaDeTelas {
	private static SistemaDeTelas sys;
	private static Map<String,JFrame> telas = new TreeMap<String,JFrame>();
	private final static String TELA_PRINCIPAL = "TelaPrincipal"; 
	
	private SistemaDeTelas(){}
	
	public static void addTela(String nome,JFrame tela){
		telas.put(nome, tela);
		
	}

	public static SistemaDeTelas getInstance() {
		if(sys == null){
			sys = new SistemaDeTelas();
			return sys;
		}else{
			return sys;
		}
	
	}

	public void iniciaTelas() {
		addTela("TelaLogin",new TelaLogin());
		addTela(TELA_PRINCIPAL,new TelaPrincipal());
		telas.get("TelaLogin").setVisible(true);
		
	}

	public static void proximaTela(JFrame classe) {
		if(classe instanceof TelaLogin){
			classe.dispose();
			telas.get(TELA_PRINCIPAL).setVisible(true);
		}
		
		
	}

	public static void cadastroAluno() {
		TelaCadastroAluno tca = new TelaCadastroAluno(telas.get(TELA_PRINCIPAL));
		tca.setVisible(true);
		
	}
	
	public static void cadastroAluno(Aluno aluno){
		TelaCadastroAluno tca = new TelaCadastroAluno(telas.get(TELA_PRINCIPAL),aluno);
		tca.setVisible(true);
	}

	public static void cadastroCurso() {
		TelaCadastroCurso tcc = new TelaCadastroCurso(telas.get(TELA_PRINCIPAL));
		tcc.setVisible(true);
		
		
	}

	public static void cadastroInstituicao() {
		TelaCadastroInstituicao tci = new TelaCadastroInstituicao(telas.get(TELA_PRINCIPAL));
		tci.setVisible(true);
		
	}

	public static void listaAlunos() {
		TelaBuscaAluno tba = new TelaBuscaAluno(telas.get(TELA_PRINCIPAL),0);
		tba.setVisible(true);
		
	}

	public static void listaCursos() {
		TelaBuscaCurso tbc = new TelaBuscaCurso(telas.get(TELA_PRINCIPAL),0);
		tbc.setVisible(true);
		
	}

	public static void listaInstituicao() {
		TelaBuscaInstituicao tbi = new TelaBuscaInstituicao(telas.get(TELA_PRINCIPAL),0);
		tbi.setVisible(true);
		
	}

	public static void cadastroDossie() {
		TelaBuscaInstituicao tbi = new TelaBuscaInstituicao(telas.get(TELA_PRINCIPAL),1);
		tbi.setVisible(true);
				
	}

	public static void listaDossie() {
		TelaBuscaDossie tbd = new TelaBuscaDossie(telas.get(TELA_PRINCIPAL));
		tbd.setVisible(true);
		
	}

	public static void cadastroUsuario() {
		TelaCadastroUsuario tcu = new TelaCadastroUsuario(telas.get(TELA_PRINCIPAL));
		tcu.setVisible(true);
		
	}

	public static void listaUsuarios() {
		TelaBuscaUsuario tbu = new TelaBuscaUsuario(telas.get(TELA_PRINCIPAL));
		tbu.setVisible(true);
		
	}

	public static void sair() {
		if(JOptionPane.showConfirmDialog(telas.get(TELA_PRINCIPAL), "Deseja realmente sair do sistema?","Sair",2) == JOptionPane.YES_OPTION){
			System.exit(0);
		}
		
		
	}

	public static void cadastroCurso(Curso curso) {
		TelaCadastroCurso tcc = new TelaCadastroCurso(telas.get(TELA_PRINCIPAL),curso);
		tcc.setVisible(true);
		
	}

	public static void cadastroInstituicao(Instituicao instituicao) {
		TelaCadastroInstituicao tci = new TelaCadastroInstituicao(telas.get(TELA_PRINCIPAL),instituicao);
		tci.setVisible(true);
		
	}

	public static void cadastraUsuario(Usuario usuario) {
		TelaCadastroUsuario tcu = new TelaCadastroUsuario(telas.get(TELA_PRINCIPAL),usuario);
		tcu.setVisible(true);
		
	}

	public static void cadastraDossie(Dossie dossie) {
		TelaCadastroDossie tcd = new TelaCadastroDossie(telas.get(TELA_PRINCIPAL),dossie,0);
		tcd.setVisible(true);
		
	}
	
	public static void editaDossie(Dossie dossie) {
		TelaCadastroDossie tcd = new TelaCadastroDossie(telas.get(TELA_PRINCIPAL),dossie,1);
		tcd.setVisible(true);
		
	}

	
	

	
	
}
