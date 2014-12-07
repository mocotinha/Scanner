package br.edu.ifpb.dao;

import javax.persistence.Query;

import br.edu.ifpb.model.Imagem;

public class DAOImagem extends DAO<Imagem>{
	public DAOImagem(){}
	
	public Long qntRegistros(int id){
		//TODO Refazer essa query
		Query q = getManager().createQuery("select count(i) from Imagem i where i.doc.id = "+id);
		
		
		return (Long) q.getSingleResult();
	}
}
