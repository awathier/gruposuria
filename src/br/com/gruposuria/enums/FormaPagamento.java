package br.com.gruposuria.enums;

public enum FormaPagamento {
	E("EMPENHO"),
	B("BOLETO"),
	C("CARTAO"),
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
