package br.com.gruposuria.enums;

public enum StatusContato {
	A("A RESPONDER"),
	R("AGUARDANDO RESPOSTA"),
	E("EM NEGOCIACAO"),
	D("DIFICULDADE EM FECHAR"),
	N("NOTA DE EMPRENHO"),
	P("PAGO"),
	C("CONTRATO");
	
	private String descricao;
	
	private StatusContato(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
