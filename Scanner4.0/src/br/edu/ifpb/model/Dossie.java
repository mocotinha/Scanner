package br.edu.ifpb.model;

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
public class Dossie {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataIncio;
	@ManyToOne
	private Instituicao instituicao;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="dossie",fetch=FetchType.EAGER)
	private List<DocumentoDigital> documentos;
	@ManyToOne
	private Aluno aluno;
	@ManyToOne
	private Curso curso;
	
	public Dossie() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataIncio() {
		return dataIncio;
	}

	public void setDataIncio(Date dataIncio) {
		this.dataIncio = dataIncio;
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
		doc.setDossie(this);
		this.documentos.add(doc);
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}
