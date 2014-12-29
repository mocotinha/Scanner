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

	public Aluno findByMatricula(String matricula) {
		Query q = getManager().createQuery("select a from Aluno a where a.matricula like :matricula" );
		q.setParameter("matricula", matricula);
		return (Aluno) q.getSingleResult();
		
	}

	public Aluno findByInfo(String matricula, String nome, String dataNascimento) {
		Query q = getManager().createQuery("select a from Aluno a where a.matricula like :matricula and a.nome like :nome and a.dataNascimento like :dataNascimento" );
		q.setParameter("matricula", matricula);
		q.setParameter("nome", nome);
		q.setParameter("dataNascimento", dataNascimento);
		return (Aluno) q.getSingleResult();
	}

	


}
