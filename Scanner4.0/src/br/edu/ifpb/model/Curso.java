package br.edu.ifpb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Curso{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String nivel;
	private String anoInicio;
	private String anoFim;
	private String situacao;
	@ManyToOne
	private Instituicao instituicao;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="curso")
	private List<Dossie> dossies;
	
	
	public Curso() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNivel() {
		return nivel;
	}


	public void setNivel(String nivel) {
		this.nivel = nivel;
	}


	public String getAnoInicio() {
		return anoInicio;
	}


	public void setAnoInicio(String anoInicio) {
		this.anoInicio = anoInicio;
	}


	public String getAnoFim() {
		return anoFim;
	}


	public void setAnoFim(String anoFim) {
		this.anoFim = anoFim;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public Instituicao getInstituicao() {
		return instituicao;
	}


	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}


	public List<Dossie> getDossies() {
		return dossies;
	}


	public void setDossies(List<Dossie> dossies) {
		for (Dossie dossie : dossies) {
			addDossie(dossie);
		}
	}


	private void addDossie(Dossie dossie) {
		dossie.setCurso(this);
		this.dossies.add(dossie);		
	}
	
	
}
