package br.edu.ifpb.controler;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import br.edu.ifpb.model.DocumentoDigital;

public class SistemaDeTelas {
	static List<JFrame> telas = new ArrayList<JFrame>();
	
	public SistemaDeTelas(){}
	
	public static void addTela(JFrame tela){
		telas.add(tela);
		
	}
	
	public static void proximaTela(JFrame atual){
		atual.setVisible(false);
		JFrame novaTela = telas.get(telas.indexOf(atual)+1);
		novaTela.setVisible(true);
	}

	public static void main() {
		telas.get(0).setVisible(true);
		
	}

	public static void proximaTela(JFrame classe, String text) {
		switch(text){
		case "Cadastrar Documento":
			classe.setVisible(false);
			exibeTela(2);
			break;
		case "Buscar Instituição":
			exibeTela(3);
			break;
		case "Buscar Aluno":
			exibeTela(4);
			break;
		case "Buscar Curso":
			exibeTela(5);
			break;
		}
		
	}

	private static void exibeTela(int i) {
		telas.get(i).setVisible(true);
		
	}
	
	public static void preparaTelaCadastroInstituicao(DocumentoDigital documentoDigital){
		((TelaCadastroDeDocumento) telas.get(2)).atualizaInstituicao(documentoDigital);
		
	}

	public static void preparaTelaCadastroAluno(
			DocumentoDigital documentoDigital) {
		((TelaCadastroDeDocumento) telas.get(2)).atualizaAluno(documentoDigital);
		
	}

	public static void preparaTelaCadastroCurso(
			DocumentoDigital documentoDigital) {
		((TelaCadastroDeDocumento) telas.get(2)).atualizaCurso(documentoDigital);
		
	}

	public static void telaCadastroInstituicao() {
		telas.get(6).setVisible(true);
	}

	public static void telaCadastroAluno() {
		telas.get(7).setVisible(true);
		
	}

	public static void telaCadastroCurso() {
		telas.get(8).setVisible(true);
	}
	
	public static void telaCadastroUsuario(){
		telas.get(10).setVisible(true);
	}

	public static void atualizaTelaCurso(String matricula) {
		((PopupCurso)telas.get(5)).setCursos(matricula);
		
	}

	public static void disposePopupInstituicao() {
		((PopupInstituicao)telas.get(3)).dispose();
		
	}
	
	public static void disposePopupAluno() {
		((PopupAluno)telas.get(4)).dispose();
		
	}
	
	public static void disposePopupCurso() {
		((PopupCurso)telas.get(5)).dispose();
		
	}
	
	public static void telaPrincipal(){
		((TelaPrincipal)telas.get(1)).setVisible(true);
	}

	public static void telaBusca() {
		((TelaBusca) telas.get(9)).setVisible(true);
		
	}

	public static void telaGerenciamentoUsuario() {
		((TelaUsuario) telas.get(11)).setVisible(true);
		
	}

	public static void telaLogin(JFrame tela) {
		tela.dispose();
		SistemaDeTelas.main();
		
	}

	
	
}
