package br.com.gruposuria.enums;

public enum FormaPagamento {
	B("BOLETO"),
	C("CARTAO"),
	E("EMPRENHO"),
	D("DEPOSITO"),
	T("TRANSFERENCIA"),
	I("INDEFINIDO");

	private String descricao;
	
	private FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
