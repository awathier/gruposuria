package br.com.gruposuria.enums;

public enum StatusTurma {
	P("Prevista"),
	A("Aberta"),
	C("Confirmada"),
	U("Ultimas Vagas"),
	E("Encerrada"),
	I("Interesse");	
	
	private String descricao;
	
	private StatusTurma(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}

