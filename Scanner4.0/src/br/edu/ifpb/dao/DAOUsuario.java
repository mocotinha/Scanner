package br.edu.ifpb.dao;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.model.Usuario;

public class DAOUsuario extends DAO<Usuario> {
	public DAOUsuario(){}

	public Usuario findByLogin(String login) {
		try{
			Query q = getManager().createQuery("select u from Usuario u where u.login= '" + login +"'");
			return (Usuario) q.getSingleResult();
		}
		catch(PersistenceException e){
			return null;
		}
		
	}
}
