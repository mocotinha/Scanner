package br.edu.ifpb.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.model.Instituicao;

public class DAOInstituicao extends DAO<Instituicao>{

	public DAOInstituicao() {}

	@SuppressWarnings("unchecked")
	public List<Instituicao> findByNome(String text) {
		Query q = getManager().createQuery("select i from Instituicao i where i.nome like '"+text+"%'" );
		return q.getResultList();
	}

	public void persitOrMerge(Instituicao instituicao) {
		try{
			findPorNome(instituicao.getNome());
			merge(instituicao);
		
			  
		}catch(NoResultException e){
			persist(instituicao);
		}
	}

	public Instituicao findPorNome(String nome) throws NoResultException{
		Query q = getManager().createQuery("select distinct i from Instituicao i where i.nome like '"+nome+"%'" );
		return (Instituicao) q.getSingleResult();
	}
}
