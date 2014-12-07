package br.edu.ifpb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DocumentoAcademico extends DocumentoDigital{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToMany(mappedBy="docAca",cascade = CascadeType.ALL)
	private List<Imagem> imagens;
	
	
	public DocumentoAcademico() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		for (Imagem imagem : imagens) {
			addImagem(imagem);
		}
	}

	private void addImagem(Imagem imagem) {
		imagem.setDocAc(this);
		this.imagens.add(imagem);
		
	}
	
	

}
