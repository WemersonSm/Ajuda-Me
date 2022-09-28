package com.senai.ajudame.enums;

public enum TipoPessoas {

	DOADOR(1), RECEBER_AJUDA(2), ADMIN(3);

	private int code;

	private TipoPessoas(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static TipoPessoas valueOf(int code) {
		for (TipoPessoas value : TipoPessoas.values()) {

			if (code == value.getCode()) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo Invalido");
	}
}
