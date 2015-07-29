package br.com.gruposuria.enums;

public enum StatusAluno {
	D("Interessado"),
	I("Inscrito"),
	C("Confirmado"),
	P("Pago");
	
	private String descricao;
	
	private StatusAluno(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
