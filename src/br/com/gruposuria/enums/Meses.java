package br.com.gruposuria.enums;

public enum Meses {
	JANEIRO(1, "JANEIRO"),
	FEVEREIRO(2, "FEVEREIRO"),
	MARCO(3, "MARÇO"),
	ABRIL(4, "ABRIL"),
	MAIO(5, "MAIO"),
	JUNHO(6, "JUNHO"),
	JULHO(7, "JULHO"),
	AGOSTO(8, "AGOSTO"),
	SETEMBRO(9, "SETEMBRO"),
	OUTUBRO(10, "OUTUBRO"),
	NOVEMBRO(11, "NOVEMBRO"),
	DEZEMBRO(12, "DEZEMBRO");

    private int codigo;
    private String mes;
    
    Meses(int codigo, String mes){
        this.codigo = codigo;
        this.mes = mes;
    }

    public int getCodigo(){
        return this.codigo;
    }
    
    public String getMes(){
        return this.mes;
    }

}