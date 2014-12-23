package br.edu.ifpb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Instituicao{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="instituicao")
	private List<Dossie> dossies;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="instituicao")
	private List<Aluno> alunos;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="instituicao")
	private List<Curso> cursos;
	
	
	public Instituicao() {}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Dossie> getDossies() {
		return dossies;
	}

	
	public void setDossies(List<Dossie> dossies) {
		this.dossies = dossies;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	

}
