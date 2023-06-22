package com.senai.ajudame.enums;

public enum DoacaoCategoria {
	SAUDE(0), ALIMENTAR(1), HABITACIONAL(2),VESTIMENTA(3),VETERINARIA(4),MOVEIS(5), OUTROS(6);

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
