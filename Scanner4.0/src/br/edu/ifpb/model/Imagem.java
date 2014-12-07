package br.edu.ifpb.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Imagem{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Lob
	@Basic(fetch=FetchType.EAGER)
	private byte imagem[] ;
	private String resolucao;
	private int checksum;
	private String formato;
	private String nomeArqEmDisco;
	private double tamanho;
	@ManyToOne
	private DocumentoPessoal docPes;
	@ManyToOne
	private DocumentoAcademico docAca;
	
	public Imagem(){}
	
	public int getId() {
		return id;
	}
	
	public DocumentoPessoal getDocPes() {
		return docPes;
	}

	public void setDocPes(DocumentoPessoal docPes) {
		this.docPes = docPes;
	}

	public DocumentoAcademico getDocAc() {
		return docAca;
	}

	public void setDocAc(DocumentoAcademico docAc) {
		this.docAca = docAc;
	}


	public DocumentoPessoal getDoc() {
		return this.docPes;
	}
	public void setDoc(DocumentoPessoal docPes) {
		this.docPes = docPes;
		
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public String getResolucao() {
		return resolucao;
	}
	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}
	public int getChecksum() {
		return checksum;
	}
	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getNomeArqEmDisco() {
		return nomeArqEmDisco;
	}
	public void setNomeArqEmDisco(String nomeArqEmDisco) {
		this.nomeArqEmDisco = nomeArqEmDisco;
	}
	public double getTamanho() {
		return tamanho;
	}
	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}
	
	public void setId(int id) {
		this.id = id;
		
	}
	
	
	
}
