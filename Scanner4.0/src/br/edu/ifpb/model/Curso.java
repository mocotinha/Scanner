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
	
	
	@ManyToOne
	private Instituicao instituicao;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="curso")
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
