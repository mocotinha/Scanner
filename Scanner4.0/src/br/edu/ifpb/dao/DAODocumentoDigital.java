package br.edu.ifpb.dao;


import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.model.DocumentoDigital;

public class DAODocumentoDigital extends DAO<DocumentoDigital>{
	public DAODocumentoDigital(){}
	
	public DocumentoDigital findByTitulo(String titulo){
		
		
		Query q = getManager().createQuery("select d from DocumentoDigital d join d.metadados m  where m.titulo like '"+titulo+"'" );
		DocumentoDigital doc = (DocumentoDigital)q.getSingleResult();
		return doc;
	}
	public Long qntRegistros(){
		Query q = getManager().createQuery("select count(d.id) from DocumentoDigital d");
		return (Long) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<DocumentoDigital> findPorDocumentosParecido(String titulo){
		Query q = getManager().createQuery("select d from DocumentoDigital d Join d.metadados m where m.descricaoDoDocumento like "
				+ "(select m1.descricaoDoDocumento from DocumentoDigital d1 Join d1.metadados m1 where m1.titulo like '"+titulo+"')");
		List<DocumentoDigital> resultList = q.getResultList();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentoDigital> findDocumentos() {
		Query q = getManager().createQuery("select distinct d from DocumentoDigital d join Metadados m  join Instituicao i "
				+ "join Aluno a join Curso c join Usuario u");//Precisa acrescentar as imagens
		return (List<DocumentoDigital>) q.getResultList();
		

	}
}
