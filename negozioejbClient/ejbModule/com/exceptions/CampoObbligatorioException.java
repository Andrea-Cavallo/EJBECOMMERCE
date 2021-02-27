package com.exceptions;

public class CampoObbligatorioException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String campo;
	
	public CampoObbligatorioException(String campo){
		this.campo=campo;
	}
	
	public String getCampo() {
		return campo;
	}
	

}
