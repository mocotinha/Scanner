package br.edu.ifpb.dao;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.model.Dossie;


public class DAODossie extends DAO<Dossie>{

	public DAODossie() {}
	
	public Dossie findBySelecionados(int idAluno, int idInstituicao, int idCurso) {
		Query q = getManager().createQuery("select d from Dossie d where d.aluno.id = :idAluno and d.curso.id = :idCurso and d.instituicao.id = :idInstituicao" );
		q.setParameter("idAluno", idAluno);
		q.setParameter("idCurso", idCurso);
		q.setParameter("idInstituicao", idInstituicao);
		return (Dossie) q.getSingleResult();
		
	}

	@SuppressWarnings("unchecked")
	public List<Dossie> findByValores(String text) {
		String texto = text+"%";
		Query q = getManager().createQuery("select d from Dossie d where d.aluno.nome like :Aluno or d.curso.nome like :Curso or d.instituicao.nome like :Instituicao" );
		q.setParameter("Aluno", texto);
		q.setParameter("Curso", texto);
		q.setParameter("Instituicao", texto);
		return q.getResultList();
	}

	


}
