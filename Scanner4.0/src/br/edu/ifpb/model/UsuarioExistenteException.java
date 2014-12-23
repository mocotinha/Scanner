package br.edu.ifpb.model;

@SuppressWarnings("serial")
public class UsuarioExistenteException extends Exception {
	public UsuarioExistenteException(String text) {
		super(text);
	}

}
