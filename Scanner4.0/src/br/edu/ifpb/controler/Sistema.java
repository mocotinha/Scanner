package br.edu.ifpb.controler;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
//import ifpb.scanner.dao2.*;

import br.edu.ifpb.dao.DAO;
import br.edu.ifpb.dao.DAOAluno;
import br.edu.ifpb.dao.DAOCurso;
import br.edu.ifpb.dao.DAODocumentoDigital;
import br.edu.ifpb.dao.DAOImagem;
import br.edu.ifpb.dao.DAOInstituicao;
import br.edu.ifpb.dao.DAOUsuario;
import br.edu.ifpb.model.Aluno;
import br.edu.ifpb.model.Curso;
import br.edu.ifpb.model.DocumentoAcademico;
import br.edu.ifpb.model.DocumentoDigital;
import br.edu.ifpb.model.DocumentoPessoal;
import br.edu.ifpb.model.Imagem;
import br.edu.ifpb.model.Instituicao;
import br.edu.ifpb.model.Usuario;

public class Sistema {
	
	private static Usuario user = null;
	private static HashMap<String, Object> dados = new HashMap<String,Object>();
	
	public Sistema() {}
	
	public static void setDados(String chave,Object valor){
		dados.put(chave, valor);
	}
	
	public static Object getDados(String chave){
		return dados.get(chave);
	}
	
	public static String md5(String senha){  
		 MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	        md.update(senha.getBytes());
	        BigInteger hash = new BigInteger(1, md.digest());
	        return hash.toString(16);
		
    }

	public static boolean login(String login, char[] password) {
		
		String pass = new String(password);
		DAOUsuario dao = new DAOUsuario();
		Usuario user = null;
		DAO.open();
		DAO.begin();
		try{
			user = dao.findByLogin(login);
			DAO.close();
			if(user.getSenha().equals(md5(pass))){
				setUsuario(user);
				Sistema.user = user;
				return true;
				
			}
		}catch(Exception e){
			return false;
		}
		DAO.close();
		return false;
	}


	public static Imagem digitalizaImagem() {
		SistemaDeDigitalizacao sd = new SistemaDeDigitalizacao();
		try {
			sd.capturaImagem();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedImage bf = sd.getImagemCapturada();
			ImageIO.write(bf, "png", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			Imagem img = new Imagem();
			img.setImagem(imageInByte);
			img.setFormato("png");
			int total = 0;
			for (int i = 0; i < imageInByte.length; i++) {
				total+=imageInByte[i];
			}
			img.setChecksum(total);
			img.setResolucao(bf.getWidth()+"X"+bf.getHeight()+"wxh");
			img.setTamanho(imageInByte.length);
			return img;
		} catch (IOException | NotGetDeviceException e) {
		
			System.out.println("Scanner não identificado!");
		}
		return null;
		
		
	}


	/*public static void cadastraDocumento(String tipoDocumento, String nomeDocumento, String anoDocumento, boolean foto, 
			String nomeInstituicao, String nomeAluno, String dataNascimento, String matriculaAluno, String cpfAluno, String rgAluno, String maeAluno,
			String paiAluno, String nomeCurso, String nivelCurso, String anoInicio, String anoFim, String situacaoCurso,
			ArrayList<Imagem> imagens) {
		
		Metadados met = new Metadados();
		met.setTipoDocumento(tipoDocumento);
		met.setNome(nomeDocumento);
		met.setAnoDocumento(anoDocumento);
		met.setFoto(foto);
		Instituicao instituicao = new Instituicao();
		instituicao.setNome(nomeInstituicao);
		met.setInstituicao(instituicao);
		Aluno aluno = new Aluno();
		aluno.setNome(nomeAluno);
		aluno.setMatricula(matriculaAluno);
		aluno.setCpf(cpfAluno);
		aluno.setRg(rgAluno);
		aluno.setMae(maeAluno);
		aluno.setPai(paiAluno);
		aluno.setDataNascimento(dataNascimento);
		met.setAluno(aluno);
		Curso curso = new Curso();
		curso.setNome(nomeCurso);
		curso.setNivel(nivelCurso);
		curso.setAnoInicio(anoInicio);
		curso.setAnoFim(anoFim);
		curso.setSituacao(situacaoCurso);
		
		
		DocumentoDigital doc = new DocumentoDigital();
		doc.setMetadados(met);
	
		doc.setDataLeitura(new Date());
		doc.setUser(user);
		DAODocumentoDigital dao = new DAODocumentoDigital();
		Acao a = new Acao();
		a.setDataHora(new Date());
		a.setDoc(doc);
		a.setUsu(user);
		TipoAcesso ta = new TipoAcesso();
		ta.setNome("Cadastro");
		a.setTipoAcao(ta);
		doc.setAcao(a);
		DAO.open();
		DAO.begin();
		dao.persist(doc);
		DAO.commit();
		DAO.close();
	}*/


	@SuppressWarnings("unused")
	public static boolean verificarDisponibilidadeDoScanner() {	
		try {
			SistemaDeDigitalizacao sisd = new SistemaDeDigitalizacao();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static List<DocumentoDigital> findAllDocumentos() {
		DAODocumentoDigital dao = new DAODocumentoDigital();
		DAO.open();
		DAO.begin();
		List<DocumentoDigital> docs = dao.findDocumentos();
		DAO.close();
		return docs;
	}

	public static Long getQntImagens(int id) {
		DAOImagem dao = new DAOImagem();
		DAO.open();
		DAO.begin();
		Long qnt = dao.qntRegistros(id);
		DAO.close();
		return qnt;
	}
	
	public static BufferedImage lobToBuffer(Imagem img){
		try {
			InputStream in = new ByteArrayInputStream(img.getImagem());
			return ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*public static void atualizaDocumento(String tipoDocumento, String nomeDocumento, String anoDocumento, boolean foto, 
			String nomeInstituicao, String nomeAluno, String dataNascimento, String matriculaAluno, String cpfAluno, String rgAluno, String maeAluno,
			String paiAluno, String nomeCurso, String nivelCurso, String anoInicio, String anoFim, String situacaoCurso,
			ArrayList<Imagem> imagens) {
		
		Metadados met = new Metadados();
		met.setTipoDocumento(tipoDocumento);
		met.setNome(nomeDocumento);
		met.setAnoDocumento(anoDocumento);
		met.setFoto(foto);
		Instituicao instituicao = new Instituicao();
		instituicao.setNome(nomeInstituicao);
		met.setInstituicao(instituicao);
		Aluno aluno = new Aluno();
		aluno.setNome(nomeAluno);
		aluno.setMatricula(matriculaAluno);
		aluno.setCpf(cpfAluno);
		aluno.setRg(rgAluno);
		aluno.setMae(maeAluno);
		aluno.setPai(paiAluno);
		aluno.setDataNascimento(dataNascimento);
		met.setAluno(aluno);
		Curso curso = new Curso();
		curso.setNome(nomeCurso);
		curso.setNivel(nivelCurso);
		curso.setAnoInicio(anoInicio);
		curso.setAnoFim(anoFim);
		curso.setSituacao(situacaoCurso);
		
		
		DocumentoDigital doc = new DocumentoDigital();
		doc.setMetadados(met);
		
		DAODocumentoDigital dao = new DAODocumentoDigital();
		
		Acao a = new Acao();
		a.setDataHora(new Date());
		a.setDoc(doc);
		a.setUsu(user);
		TipoAcesso ta = new TipoAcesso();
		ta.setNome("Edição dos dados");
		a.setTipoAcao(ta);
		doc.setAcao(a);
		
		
		DAO.open();
		DAO.begin();
		dao.merge(doc);
		DAO.commit();
		DAO.close();
	}*/
	
	public void iniciaTelas(){
		SistemaDeTelas.addTela(new TelaLogin());
		SistemaDeTelas.addTela(new TelaPrincipal());
		SistemaDeTelas.addTela(new TelaCadastroDeDocumento());
		SistemaDeTelas.addTela(new PopupInstituicao());
		SistemaDeTelas.addTela(new PopupAluno());
		SistemaDeTelas.addTela(new PopupCurso());
		SistemaDeTelas.addTela(new PopupCadastroInstituicao());
		SistemaDeTelas.addTela(new PopupCadastraAluno());
		SistemaDeTelas.addTela(new PopupCadastraCurso());
		SistemaDeTelas.addTela(new TelaBusca());
		SistemaDeTelas.addTela(new TelaCadastroDeUsuario());
		SistemaDeTelas.addTela(new TelaUsuario());
	}
	
	public void main(){
		SistemaDeTelas.main();
	}

	public static List<Instituicao> getInstituicoes() {
		
		DAOInstituicao dao = new DAOInstituicao();
		DAO.open();
		DAO.begin();
		List<Instituicao> insts = (List<Instituicao>) dao.findAll();
		DAO.close();
		return insts;
		
	}

	public static List<Aluno> getAlunos() {
		DAOAluno dao = new DAOAluno();
		DAO.open();
		DAO.begin();
		List<Aluno> alunos = (List<Aluno>) dao.findAll();
		DAO.close();
		return alunos;
	}

	public static List<Curso> getCurso(String nome) {
		DAOCurso dao = new DAOCurso();
		return dao.findCursosByAluno(nome);
	}

	public static void setInstituicao(
			Instituicao model) {
		((DocumentoDigital)dados.get("documentoAtual")).getMetadados().setInstituicao(model);
	}

	public static void validaInstituicao() {
		SistemaDeTelas.preparaTelaCadastroInstituicao((DocumentoDigital)dados.get("documentoAtual"));
		
	}

	public static void setAluno(Aluno aluno) {
		((DocumentoDigital)dados.get("documentoAtual")).getMetadados().setAluno(aluno);
		
	}

	public static void validaAluno() {
		SistemaDeTelas.preparaTelaCadastroAluno((DocumentoDigital)dados.get("documentoAtual"));
		
	}

	public static void setCurso(Curso curso) {
		((DocumentoDigital)dados.get("documentoAtual")).getMetadados().getAluno().addCurso(curso);		
	}

	public static void validaCurso() {
		SistemaDeTelas.preparaTelaCadastroCurso((DocumentoDigital)dados.get("documentoAtual"));
		
	}

	public static void inicializaCadastroDeDocumentos(){
		DocumentoPessoal docPes = new DocumentoPessoal();
		dados.put("documentoPessoal", docPes);
		DocumentoAcademico docAcd = new DocumentoAcademico();
		dados.put("documentoAcademico", docAcd);
		
	}
	
	@SuppressWarnings("unchecked")
	public static void adicionaImagem(int selectedIndex, String tit, Imagem img) {
		if(selectedIndex == 0){
			((DocumentoPessoal) dados.get("documentoPessoal")).setTitulo(tit);
			((DocumentoPessoal) dados.get("documentoPessoal")).addImagem(img);
			
			
			
	
		}else if(selectedIndex == 1){			
			((DocumentoAcademico) dados.get("documentoAcademico")).setTitulo(tit);
			((DocumentoAcademico) dados.get("documentoAcademico")).addImagem(img);
			
			
			
			
		}
		if(!dados.containsKey("imagens")){
			ArrayList<Imagem> imgs = new ArrayList<Imagem>();
			imgs.add(img);
			dados.put("imagens", imgs);
		}else{
			((ArrayList<Imagem>) dados.get("imagens")).add(img);
		}
		
	}

	public static int quantidadeDeImagensDigitalizadas(int selectedIndex) {
		if(selectedIndex == 0){
			return ((DocumentoPessoal) dados.get("documentoPessoal")).getImagens().size();
		}else{
			return ((DocumentoAcademico) dados.get("documentoAcademico")).getImagens().size();
		}
		
				
	}


	public static ArrayList<Imagem> getImagensDigitalizadas(int i) {
		
		if(i == 0){
			return (ArrayList<Imagem>) ((DocumentoPessoal) dados.get("documentoPessoal")).getImagens();
		}else{
			return (ArrayList<Imagem>) ((DocumentoAcademico) dados.get("documentoAcademico")).getImagens();
		}
	}

	
	public static void persiteDocumento(String tempoDeGuarda, int i) {
		DocumentoDigital doc = ((DocumentoDigital)dados.get("documentoAtual"));
		Metadados met = doc.getMetadados();
		registraAcao();
		DAO.open();
		DAO.begin();
		DAODocumentoDigital daoDoc = new DAODocumentoDigital();
		DAOInstituicao daoInstituicao = new DAOInstituicao();
		DAOAluno daoAluno = new DAOAluno();
		DAOMetadados daoMet = new DAOMetadados();
		DAOUsuario daoUsuario = new DAOUsuario();
		
		
		daoInstituicao.persitOrMerge(met.getInstituicao());
		
		if(i==0){
			met.getAluno().adicionaDocumentoPessoal((DocumentoPessoal) dados.get("documentoPessoal"));
		}else{
			met.getAluno().adicionaDocumentoAcademico((DocumentoAcademico) dados.get("documentoAcademico"));
		}
		
		daoAluno.persitOrMerge(met.getAluno());
		daoMet.persist(met);
		
		
		user.addDoc(doc);
		daoUsuario.merge(user);
		daoDoc.persist(doc);
		DAO.commit();
		DAO.close();
		
		/*
		
		DAOMetadados daoMet = new DAOMetadados();
		DAODocumentoDigital daoDocumentoDigital = new DAODocumentoDigital();
		DAOCurso daoCurso = new DAOCurso();
		DAOAluno daoAluno = new DAOAluno();
		DAOInstituicao daoInstituicao = new DAOInstituicao();
		
				
		
		DAO.open();
		DAO.begin();
		
		if(i==0){
			doc.getMetadados().getAluno().adicionaDocumentoPessoal((DocumentoPessoal) dados.get("documentoPessoal"));
		}else{
			doc.getMetadados().getAluno().adicionaDocumentoAcademico((DocumentoAcademico) dados.get("documentoAcademico"));
		}
		
		
		Metadados met = new Metadados();
		met.setAluno(doc.getMetadados().getAluno());
		daoAluno.persitOrMerge(met.getAluno());
		
		
		doc.setDataLeitura(new Date());
		doc.setTempoGuarda(tempoDeGuarda);
		doc.setUser(user);
		
		daoInstituicao.persitOrMerge(doc.getMetadados().getInstituicao());
		
		met.setInstituicao(doc.getMetadados().getInstituicao());
		daoMet.persist(met);
		daoDocumentoDigital.persist(doc);
		DAO.commit();
		DAO.close();*/
		
	}

	private static void registraAcao() {
		DAOAcao dao = new DAOAcao();
		Acao a = new Acao();
		a.setDataHora(new Date());
		TipoAcesso ta = new TipoAcesso();
		ta.setNome("Cadastro");
		a.setTipoAcao(ta);
		a.setUsu(user);
		DAO.open();
		DAO.begin();
		dao.persist(a);
		DAO.commit();
		DAO.close();
		
	}

	public static void setUsuario(Usuario user2) {
		dados.put("user", user2);
		
	}

	public static void cadastraInstituicao(String text) {
		Instituicao ins = new Instituicao();
		ins.setNome(text);
		((DocumentoDigital)dados.get("documentoAtual")).getMetadados().setInstituicao(ins);
		
		
		
		
	}

	public static void cadastraAluno(String matricula, String nome,
			String cpf, String rg, String pai, String mae,
			String dataNascimento) {
		Aluno a = new Aluno();
		a.setMatricula(matricula);
		a.setNome(nome);
		a.setCpf(cpf);
		a.setRg(rg);
		a.setMae(mae);
		a.setPai(pai);
		a.setDataNascimento(dataNascimento);
		((DocumentoDigital)dados.get("documentoAtual")).getMetadados().setAluno(a);
	
		
	}

	public static void cadastraCurso(String nome, String anoInicio, String anoFim,
			String situacao, String nivel) {
		Curso curso = new Curso();
		curso.setNome(nome);
		curso.setAnoInicio(anoInicio);
		curso.setAnoFim(anoFim);
		curso.setSituacao(situacao);
		curso.setNivel(nivel);
		curso.setAluno(((DocumentoDigital)dados.get("documentoAtual")).getMetadados().getAluno());
		((DocumentoDigital)dados.get("documentoAtual")).getMetadados().getAluno().addCurso(curso);
		
		
		
		
	}

	public static void atualizaCursos(String matricula) {
		SistemaDeTelas.atualizaTelaCurso(matricula);
		
	}

	public static List<Aluno> buscaAluno(String texto) {
		DAOAluno dao = new DAOAluno();
		return dao.findByNome(texto);
		
	}

	public static List<Instituicao> getInstituicoes(String text) {
		DAOInstituicao dao = new DAOInstituicao();
		DAO.open();
		DAO.begin();
		List<Instituicao> inst = dao.findByNome(text);
		DAO.close();
		return inst;
	}

	public static void editarDocumentoDigital(DocumentoDigital model) {
		// TODO Auto-generated method stub
		
	}

	public static void cadastraUsuario(String text, String text2,
			String string, int selectedIndex) {
		DAOUsuario dao = new DAOUsuario();
		Usuario usu = new Usuario();
		usu.setNome(text);
		usu.setLogin(text2);
		usu.setSenha(md5(string));
		usu.setTipo(selectedIndex);
		DAO.open();
		DAO.begin();
		dao.persist(usu);
		DAO.commit();
		DAO.close();
		
	}

	public static List<Usuario> getUsuarios() {
		DAOUsuario dao = new DAOUsuario();
		DAO.open();
		DAO.begin();
		List<Usuario> us = dao.findAll();
		DAO.close();
		return us;
	}

	public static void removeUsuario(Usuario usuario) {
		//Deve ser verificado o vinculo dos usuario com os documentos.
		//TODO 
		/*DAOUsuario dao = new DAOUsuario();
		DAO.open();
		DAO.begin();
		dao.remove(usuario);
		DAO.commit();
		DAO.close();*/
		
	}


	
} 


