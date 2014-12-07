package br.edu.ifpb.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String login;
	private String senha;
	private int tipo;
	
	@OneToMany(mappedBy="userResponsavel")
	private List<DocumentoDigital> documentos;
	
	
	public Usuario() {}


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


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public int getTipo() {
		return tipo;
	}


	public void setTipo(int tipo) {
		this.tipo = tipo;
	}


	public List<DocumentoDigital> getDocumentos() {
		return documentos;
	}


	public void setDocumentos(List<DocumentoDigital> documentos) {
		for (DocumentoDigital documentoDigital : documentos) {
			addDocumentoDigital(documentoDigital);
		}
	}
	
	public void addDocumentoDigital(DocumentoDigital doc){
		doc.setUsuario(this);
		this.documentos.add(doc);
	}
}
