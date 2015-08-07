package br.com.gruposuria.enums;

public enum GrupoAcesso {
	A("ADMINISTRADOR"),
	C("CONSULTOR"),
	F("PESSOA_FISICA"),
	J("PESSOA_JURIDICA");

	private String descricao;
	
	private GrupoAcesso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
