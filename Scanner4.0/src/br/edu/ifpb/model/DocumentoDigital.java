package br.edu.ifpb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DocumentoDigital{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String classificacao;
	private String descricao;
	private String tipo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLeitura;
	@OneToMany(mappedBy="doc",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Imagem> imagens = new ArrayList<Imagem>();
	@ManyToOne
	private Usuario userResponsavel;
	@ManyToOne
	private Dossie dossie;
	
	public DocumentoDigital() {}
	
	public void setDossie(Dossie dossie) {
		this.dossie = dossie;
		
	}

	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	@Override
	public String toString() {
		
		return titulo;
	}

	
	public List<Imagem> getImagens() {
		return imagens;
	}
	public void setImagens(List<Imagem> imagens) {
		for (int i =0; i < imagens.size(); i++) {
			addImagem(imagens.get(i));
		}
	}

	private void addImagem(Imagem imagem) {
		imagem.setDoc(this);
		this.imagens.add(imagem);
		
	}

	

	
	
	

}
