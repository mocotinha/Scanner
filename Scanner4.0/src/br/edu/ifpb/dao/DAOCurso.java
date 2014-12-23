package br.edu.ifpb.dao;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.model.Curso;

public class DAOCurso extends DAO<Curso>{
	
	public DAOCurso() {}

	@SuppressWarnings("unchecked")
	public List<Curso> findCursosByAluno(String nome) {
		Query q = getManager().createQuery("select c from Curso c where c.aluno.nome like '"+nome+"'");
		List<Curso> resultList = q.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Curso> findByNome(String text) {
		Query q = getManager().createQuery("select c from Curso c where c.nome like '"+text+"%"+"'");
		List<Curso> resultList = q.getResultList();
		return resultList;
	}
	
	public Curso findByNomeSingle(String text) {
		Query q = getManager().createQuery("select c from Curso c where c.nome like :curso");
		q.setParameter("curso",text);
		return (Curso) q.getSingleResult();
	}
}
