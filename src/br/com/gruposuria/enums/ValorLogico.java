package br.com.gruposuria.enums;

public enum ValorLogico {
	N("Não"), 
	S("Sim");

	private String descricao;

	private ValorLogico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}