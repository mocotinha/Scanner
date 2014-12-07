package br.edu.ifpb.aplication;

import br.edu.ifpb.controler.Sistema;
import br.edu.ifpb.dao.DAO;
import br.edu.ifpb.dao.DAOUsuario;
import br.edu.ifpb.model.Usuario;

public class Application {
	public static void main(String[] args) {
		Sistema sys = new Sistema();
		sys.iniciaTelas();
		//cadastraUser();
		sys.main();
		
		
	}

	private static void cadastraUser() {
		DAOUsuario dao = new DAOUsuario();
		DAO.open();
		DAO.begin();
		Usuario user = new Usuario();
		user.setNome("Fausto");
		user.setLogin("admin");
		user.setSenha(Sistema.md5("123"));
		dao.persist(user);
		DAO.commit();
		DAO.close();

		
	}
}
