package br.edu.ifpb.controler;

public class NotGetDeviceException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	public NotGetDeviceException() {
		super("N�o foi encontrado nenhum aparelho de Scanner ligado a maquina.");
	}
	public String getMessage(){
		return super.getMessage();
	}
}
