package br.com.gruposuria.enums;

public enum TipoContato {
	T("TELEFONE"),
	E("EMAIL"),
	M("MARKETING DIRETO"),
	W("WHATSAPP");

	private String descricao;
	
	private TipoContato(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
