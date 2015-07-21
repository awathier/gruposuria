package br.com.gruposuria.enums;

public enum ValorLogico {

	S("SIM"),

	N("N�O");

	private String descricao;

	private ValorLogico(String descricao) {

		this.descricao = descricao;

	}

	public String getDescricao() {

		return this.descricao;

	}

}