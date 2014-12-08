package br.edu.ifpb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class DocumentoPessoal extends DocumentoDigital {

	@OneToMany(mappedBy="docPes",cascade = CascadeType.ALL)
	private List<Imagem> imagens;
	
	public DocumentoPessoal() {}
	

	public List<Imagem> getImagens() {
		return imagens;
	}
	public void setImagens(List<Imagem> imagens) {
		for (Imagem imagem : imagens) {
			addImagem(imagem);
		}
	}

	private void addImagem(Imagem imagem) {
		imagem.setDocPes(this);
		this.imagens.add(imagem);
		
	}
	
	
	
	
	
}
