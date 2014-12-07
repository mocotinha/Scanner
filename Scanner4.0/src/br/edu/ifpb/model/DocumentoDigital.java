package br.edu.ifpb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DocumentoDigital {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String classificacao;
	private String descricao;
	private Date dataLeitura;
	
	@ManyToOne
	private Usuario userResponsavel;
	@ManyToOne
	private Dossie dossie;
	
	public DocumentoDigital() {}
	
	public void setDossie(Dossie dossie) {
		this.dossie = dossie;
		
	}

	public void setUsuario(Usuario usuario) {
		this.userResponsavel = usuario;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public Date getDataLeitura() {
		return dataLeitura;
	}

	public void setDataLeitura(Date dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	public Usuario getUserResponsavel() {
		return userResponsavel;
	}

	public void setUserResponsavel(Usuario userResponsavel) {
		this.userResponsavel = userResponsavel;
	}

	public Dossie getDossie() {
		return dossie;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	

}
