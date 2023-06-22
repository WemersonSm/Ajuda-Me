package com.senai.ajudame.enums;

public enum PessoaCategoria {

	DOADOR(0), RECEBER_AJUDA(1), ADMIN(2);

	private int code;

	private PessoaCategoria(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PessoaCategoria valueOf(int code) {
		for (PessoaCategoria value : PessoaCategoria.values()) {

			if (code == value.getCode()) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo Invalido");
	}
}
