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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
//import ifpb.scanner.dao2.*;







import br.edu.ifpb.dao.DAO;
import br.edu.ifpb.dao.DAOAluno;
import br.edu.ifpb.dao.DAOCurso;
import br.edu.ifpb.dao.DAODocumentoDigital;
import br.edu.ifpb.dao.DAOInstituicao;
import br.edu.ifpb.dao.DAOUsuario;
import br.edu.ifpb.model.Aluno;
import br.edu.ifpb.model.Curso;
import br.edu.ifpb.model.DocumentoDigital;
import br.edu.ifpb.model.Imagem;
import br.edu.ifpb.model.Instituicao;
import br.edu.ifpb.model.Usuario;

public class Sistema {
	
	private static Sistema sys;
	@SuppressWarnings("unused")
	private static Usuario user;
	private static Map<String, Object> dados = new HashMap<String,Object>();
	
	private Sistema() {}
	
	
	public static void iniciarTelas(){
		SistemaDeTelas sdt = SistemaDeTelas.getInstance();
		sdt.iniciaTelas();
	}
	
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
		SistemaDeDigitalizacao sd = SistemaDeDigitalizacao.getInstance();
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

	public static boolean verificarDisponibilidadeDoScanner() {	
		try {
			//TODO verificar se o scanner está disponível
			@SuppressWarnings("unused")
			SistemaDeDigitalizacao sisd = SistemaDeDigitalizacao.getInstance();
			return true;
		} catch (Exception e) {
			return false;
		}
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

	public static List<Instituicao> getInstituicoes() {
		
		DAOInstituicao dao = new DAOInstituicao();
		DAO.open();
		DAO.begin();
		List<Instituicao> insts = (List<Instituicao>) dao.findAll();
		DAO.close();
		return insts;
		
	}
	
	public static List<DocumentoDigital> getDocumentosDigitais(){
		DAODocumentoDigital dao = new DAODocumentoDigital();
		DAO.open();
		DAO.begin();
		List<DocumentoDigital> docDig = (List<DocumentoDigital>) dao.findAll();
		DAO.close();
		return docDig;
	}

	public static List<Aluno> getAlunos() {
		DAOAluno dao = new DAOAluno();
		DAO.open();
		DAO.begin();
		List<Aluno> alunos = (List<Aluno>) dao.findAll();
		DAO.close();
		return alunos;
	}

	public static List<Curso> getCurso(String nomeDoAluno) {
		DAOCurso dao = new DAOCurso();
		return dao.findCursosByAluno(nomeDoAluno);
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

	public static List<Usuario> getUsuarios() {
		DAOUsuario dao = new DAOUsuario();
		DAO.open();
		DAO.begin();
		List<Usuario> us = dao.findAll();
		DAO.close();
		return us;
	}

	public static List<Curso> getCursos() {
		List<Curso> cursos;
		DAOCurso dao = new DAOCurso();
		DAO.open();
		DAO.begin();
		cursos = dao.findAll();
		DAO.close();
		return cursos;
	}

	public static Sistema getInstance() {
		if(sys == null){
			sys = new Sistema();
			return sys;
		}else{
			return sys;
		}
		
	}


	
	public void iniciar() {
		iniciarTelas();
		
	}


	public static void cadastraAluno(String nome, String matricula,String dataNascimento, String rg,
			String cpf, String mae, String pai) {
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setMatricula(matricula);
		aluno.setRg(rg);
		aluno.setCpf(cpf);
		aluno.setMae(mae);
		aluno.setPai(pai);
		aluno.setDataNascimento(dataNascimento);
		
		//TODO verificar se o aluno já existe
		
		DAOAluno dao = new DAOAluno();
		DAO.open();
		DAO.begin();
		dao.persist(aluno);
		DAO.commit();
		DAO.close();
		
	}


	public static void cadastraCurso(String nome, String nivel, String anoCriacao,
			String anoFim) {
		Curso cur = new Curso();
		cur.setNome(nome);
		cur.setNivel(nivel);
		cur.setAnoInicio(anoCriacao);
		cur.setAnoFim(anoFim);
		
		//TODO verificar se o curso já existe
		
		DAOCurso dao = new DAOCurso();
		DAO.open();
		DAO.begin();
		dao.persist(cur);
		DAO.commit();
		DAO.close();
		
	}


	public static void cadastraInstituicao(String nome) {
		Instituicao inst = new Instituicao();
		inst.setNome(nome);
		
		
		//TODO verificar se institção já existe
		
		DAOInstituicao dao = new DAOInstituicao();
		DAO.open();
		DAO.begin();
		dao.persist(inst);
		DAO.commit();
		DAO.close();
		
	}


	public static void cadastraUsuario(String nome, String login, String pass, int tipo) {
		Usuario us = new Usuario();
		us.setNome(nome);
		us.setLogin(login);
		us.setSenha(md5(pass));
		us.setTipo(tipo);
		
		//TODO verificar se o usuario já existe
		
		DAOUsuario dao = new DAOUsuario();
		DAO.open();
		DAO.begin();
		dao.persist(us);
		DAO.commit();
		DAO.close();
		
	}


	public static void setAluno(Aluno aluno) {
		setDados("aluno", aluno);
		
		
	}


	public static void atualizaAluno(Aluno aluno) {
		DAOAluno dao = new DAOAluno();
		DAO.open();
		DAO.begin();
		dao.merge(aluno);
		DAO.commit();
		DAO.close();
		
	}


	public static List<Aluno> getAlunosPorNome(String nome) {
		DAOAluno dao = new DAOAluno();
		List<Aluno> alunos = new ArrayList<Aluno>();
		DAO.open();
		DAO.begin();
		alunos = dao.findByNome(nome);
		DAO.close();
		return alunos;
	}


	public static List<Curso> getCursoPorNome(String text) {
		DAOCurso dao = new DAOCurso();
		List<Curso> cursos = new ArrayList<Curso>();
		DAO.open();
		DAO.begin();
		cursos = dao.findByNome(text);
		DAO.close();
		return cursos;
	}


	public static void setCurso(Curso curso) {
		setDados("curso", curso);
		
	}


	public static void atualizaCurso(Curso curso) {
		DAOCurso dao = new DAOCurso();
		DAO.open();
		DAO.begin();
		dao.merge(curso);
		DAO.commit();
		DAO.close();
		
	}


	public static List<Instituicao> getInstituicaoPorNome(String text) {
		DAOInstituicao dao = new DAOInstituicao();
		List<Instituicao> inst = new ArrayList<Instituicao>();
		DAO.open();
		DAO.begin();
		inst = dao.findByNome(text);
		DAO.close();
		return inst;
	}


	public static void setInstituicao(Instituicao instituicao) {
		setDados("instituicao", instituicao);
		
	}


	public static void atualizaInstituicao(Instituicao instituicao) {
		DAOInstituicao dao = new DAOInstituicao();
		DAO.open();
		DAO.begin();
		dao.merge(instituicao);
		DAO.commit();
		DAO.close();
		
	}


	
} 


