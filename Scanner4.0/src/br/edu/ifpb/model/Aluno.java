package br.edu.ifpb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
/*
 * Classe que representa o aluno da Instituicao
 * Autor: Jozias Rolim
 */
@Entity
public class Aluno{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String matricula;
	private String cpf;
	private String rg;
	private String uf;
	private String mae;
	private String pai;
	private String dataNascimento;
	@ManyToOne
	private Instituicao instituicao;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="aluno")
	private List<Dossie> dossies;
	
	public Aluno() {}

	public int getId() {
		return id;
	}
	

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	//Referência biderecional para a persistência
	private void addDossie(Dossie dossie) {
		dossie.setAluno(this);
		this.dossies.add(dossie);
		
	}
	
	
	
}
