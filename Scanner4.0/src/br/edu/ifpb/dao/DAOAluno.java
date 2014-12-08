package br.edu.ifpb.dao;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.model.Aluno;

public class DAOAluno extends DAO<Aluno>{

	public DAOAluno() {}

	@SuppressWarnings("unchecked")
	public List<Aluno> findByNome(String texto) {
		
		Query q = getManager().createQuery("select distinct a from Aluno a where a.nome like '"+texto+"%'" );
		return q.getResultList();
		
		
	}


}
