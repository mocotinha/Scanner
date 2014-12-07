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
}
