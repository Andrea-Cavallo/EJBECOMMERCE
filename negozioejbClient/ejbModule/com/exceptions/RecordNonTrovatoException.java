package com.exceptions;

public class RecordNonTrovatoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String field;

	public RecordNonTrovatoException(String field) {
	
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
