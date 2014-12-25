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
import javax.swing.JOptionPane;

import uk.co.mmscomputing.device.twain.TwainFailureException;
import br.edu.ifpb.dao.DAO;
import br.edu.ifpb.dao.DAOAluno;
import br.edu.ifpb.dao.DAOCurso;
import br.edu.ifpb.dao.DAODocumentoDigital;
import br.edu.ifpb.dao.DAODossie;
import br.edu.ifpb.dao.DAOInstituicao;
import br.edu.ifpb.dao.DAOUsuario;
import br.edu.ifpb.model.Aluno;
import br.edu.ifpb.model.AlunoExistenteException;
import br.edu.ifpb.model.Curso;
import br.edu.ifpb.model.CursoExistenteException;
import br.edu.ifpb.model.DocumentoAcademico;
import br.edu.ifpb.model.DocumentoDigital;
import br.edu.ifpb.model.DocumentoPessoal;
import br.edu.ifpb.model.Dossie;
import br.edu.ifpb.model.Imagem;
import br.edu.ifpb.model.Instituicao;
import br.edu.ifpb.model.InstituicaoExistenteException;
import br.edu.ifpb.model.Usuario;
import br.edu.ifpb.model.UsuarioExistenteException;

public class Sistema {
	
	private static Sistema sys;
	private static Usuario user;
	private static Map<String, Object> dados = new HashMap<String,Object>();
	private static List<Imagem> imgs = new ArrayList<Imagem>();
	
	
	private Sistema() {}
	
	
	public static void iniciarTelas(){
		SistemaDeTelas sdt = SistemaDeTelas.getInstance();
		sdt.iniciaTelas();
		setDados("aluno", null);
		setDados("curso", null);
		setDados("instituicao", null);
		
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
		}catch(TwainFailureException e){
			JOptionPane.showMessageDialog(null,"Scanner não identificado");
		} catch (IOException | NotGetDeviceException e) {
			JOptionPane.showMessageDialog(null,"Erro no Scanner");
			
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
			String cpf, String mae, String pai) throws Exception {
		DAOAluno dao = new DAOAluno();
		DAO.open();
		DAO.begin();
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setMatricula(matricula);
		aluno.setRg(rg);
		aluno.setCpf(cpf);
		aluno.setMae(mae);
		aluno.setPai(pai);
		aluno.setDataNascimento(dataNascimento);
		Aluno aux;
		try{
			aux = dao.findByMatricula(matricula);
		}catch(Exception e){
			dao.persist(aluno);
			DAO.flush();
			DAO.commit();
			DAO.close();
			return;
			
		}
		DAO.close();
		throw new AlunoExistenteException("Erro: Aluno já existe na base de dados!\nCom nome: "+aux.getNome()+"\nNumero de Registro: "+ aux.getId());	
	}


	public static void cadastraCurso(String nome, String nivel, String instituicao) throws CursoExistenteException {
		Curso cur = new Curso();
		cur.setNome(nome);
		cur.setNivel(nivel);
		DAOCurso dao = new DAOCurso();
		DAOInstituicao daoI = new DAOInstituicao();
		DAO.open();
		DAO.begin();  
		cur.setInstituicao(daoI.findPorNome(instituicao));
		Curso aux;
		try{
			aux = dao.findByNomeSingle(nome);
		}catch(Exception e){
			dao.persist(cur);
			DAO.flush();
			DAO.commit();
			DAO.close();
			return;	
		}
		DAO.close();
		throw new CursoExistenteException("Erro: Curso existente\nNumero de Registro: "+aux.getId());	
	}

	public static void cadastraInstituicao(String nome) throws InstituicaoExistenteException {
		Instituicao inst = new Instituicao();
		inst.setNome(nome);
		DAOInstituicao dao = new DAOInstituicao();
		DAO.open();
		DAO.begin();
		Instituicao aux;
		try{
			aux = dao.findPorNome(nome);
		}catch(Exception e){
			dao.persist(inst);
			DAO.flush();
			DAO.commit();
			DAO.close();
			return;	
		}
		
		DAO.close();
		throw new InstituicaoExistenteException("Erro: Instituição existente\nNumero de Registro: "+aux.getId());
		
	}


	public static void cadastraUsuario(String nome, String login, String pass, int tipo) throws UsuarioExistenteException {
		Usuario us = new Usuario();
		us.setNome(nome);
		us.setLogin(login);
		us.setSenha(md5(pass));
		us.setTipo(tipo);
		DAOUsuario dao = new DAOUsuario();
		DAO.open();
		DAO.begin();
		Usuario aux;
		try{
			aux = dao.findByLogin(login);
		}catch(Exception e){
			dao.persist(us);
			DAO.flush();
			DAO.commit();
			DAO.close();
			return;	
		}
		
		DAO.close();
		throw new UsuarioExistenteException("Erro: Usuário existente\nNumero de Registro: "+aux.getId());
		
		
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


	public static List<Usuario> getUsuariosPorNome(String text) {
		DAOUsuario dao = new DAOUsuario();
		List<Usuario> us = new ArrayList<Usuario>();
		DAO.open();
		DAO.begin();
		us = dao.findByNome(text);
		DAO.close();
		return us;
	}


	public static void atualizaUsuario(Usuario us) {
		DAOUsuario dao = new DAOUsuario();
		DAO.open();
		DAO.begin();
		dao.merge(us);
		DAO.commit();
		DAO.close();
		
	}


	public static void cadastroDossie() {
		limpaRegistroDossie();
		SistemaDeTelas.cadastroDossie();
		Dossie dossie = new Dossie();
		if(dados.get("aluno") == null){
			JOptionPane.showMessageDialog(null, "Você Não Selecionou um Aluno");
			return;
		}
		
		if(dados.get("instituicao") == null){
			JOptionPane.showMessageDialog(null, "Você Não Selecionou uma Instituição");
			return;
		}
		
		if(dados.get("curso") == null){
			JOptionPane.showMessageDialog(null, "Você Não Selecionou um Curso");
			return;
		}
		
		dossie.setAluno((Aluno) dados.get("aluno"));
		dossie.setInstituicao((Instituicao) dados.get("instituicao"));
		dossie.setCurso((Curso) dados.get("curso"));
//		dados.put("dossie", dossie);
		limpaRegistroDossie();
		DAODossie dao = new DAODossie();
		DAO.open();
		DAO.begin();
		Dossie aux;
		try{
			dao.findBySelecionados(dossie.getAluno().getId(),dossie.getInstituicao().getId(), dossie.getCurso().getId());
		}catch(Exception e){
			dao.persist(dossie);
		}
		DAO.flush();
		DAO.commit();
		aux = dao.findBySelecionados(dossie.getAluno().getId(),dossie.getInstituicao().getId(), dossie.getCurso().getId());
		DAO.close();
		
		SistemaDeTelas.cadastraDossie(aux);
		
		
	}


	private static void limpaRegistroDossie() {
		dados.put("aluno", null);
		dados.put("instituicao", null);
		dados.put("curso", null);
		
	}


	public static void adicionaImagem(Imagem img) {
		imgs.add(img);
		
	}


	public static int getQntImagensDigitalizadas() {
		
		return imgs.size();
	}


	public static List<Imagem> getImagensDigitalizadas() {
		
		return imgs;
	}


	public static String[] getNomeDasInstituicao() {
		List<Instituicao> aux = getInstituicoes();
		String [] aux2 = new String [aux.size()];
		for(int i=0; i < aux.size();i++){
			aux2[i] = aux.get(i).getNome();
		}
		
		return aux2;
	}


	public static void limpaImagens() {
		imgs.clear();
		
	}


	public static void cadastraDocumentoAoDossie(Dossie dossie, String titulo,
			String classificacao, String descricao, String tipo) {
		DocumentoDigital doc;
		
		if(tipo.equals("Documento Pessoal")){
			doc = new DocumentoPessoal();
		}else{
			doc = new DocumentoAcademico();
		}
		
		doc.setTitulo(titulo);
		doc.setDescricao(descricao);
		doc.setClassificacao(classificacao);
		doc.setUserResponsavel(user);
		dossie.addDocumentoDigital(doc);
		DAODossie dao = new DAODossie();
		DAO.open();
		DAO.begin();
		dao.merge(dossie);
		DAO.commit();
		DAO.close();
		
	}


	public static Dossie atualizaDossie(Dossie dossie) {
		DAODossie dao = new DAODossie();
		DAO.open();
		DAO.begin();
		Dossie aux = dao.findBySelecionados(dossie.getAluno().getId(),dossie.getInstituicao().getId(), dossie.getCurso().getId());
		DAO.close();
		return aux;
	}


	public static void atualizaDocumentos(Dossie dossie, String titulo, String classificacao, String descricao, String tipo, int index) {
		DocumentoDigital doc = dossie.getDocumentos().get(index);
		
		if(!((doc instanceof DocumentoPessoal && tipo.equals("Documento Pessoal")) || (doc instanceof DocumentoAcademico && !tipo.equals("Documento Pessoal")))){
			dossie.getDocumentos().remove(doc);
			if(tipo.equals("Documento Pessoal")){
				doc = new DocumentoPessoal();
			}else{
				doc = new DocumentoAcademico();
			}
			dossie.addDocumentoDigital(doc);
		}
		
		doc.setTitulo(titulo);
		doc.setDescricao(descricao);
		doc.setClassificacao(classificacao);
		
		
		DAODossie dao = new DAODossie();
		DAO.open();
		DAO.begin();
		dao.merge(dossie);
		DAO.commit();
		DAO.close();
		
	}




	
} 


