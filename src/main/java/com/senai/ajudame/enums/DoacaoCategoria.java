package com.senai.ajudame.enums;

public enum DoacaoCategoria {
	SAUDE(1), ALIMETAR(2), HABITACIONAL(3), OUTROS(4);

	private int code;

	private DoacaoCategoria(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static DoacaoCategoria valueOf(int code) {
		for (DoacaoCategoria value : DoacaoCategoria.values()) {

			if (code == value.getCode()) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo Invalido");
	}

}
