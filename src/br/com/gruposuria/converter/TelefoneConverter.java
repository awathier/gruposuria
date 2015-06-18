package br.com.gruposuria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 String telefone = value;
	     if (value!= null && !value.equals(""))
	    	 telefone = value.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\ ", "").replaceAll("\\-", "");
	
	     return telefone;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        String telefone = (String) modelValue;
        StringBuilder telefoneFormatado = new StringBuilder();
        if(telefone.length() > 9){
        	telefoneFormatado.append("(");
        	telefoneFormatado.append(telefone.substring(0, 2));
        	telefoneFormatado.append(") ");
        	telefoneFormatado.append(telefone.substring(2, 6));
        	telefoneFormatado.append("-");
        	telefoneFormatado.append(telefone.substring(6, telefone.length()));
        } else {
        	telefoneFormatado.append(telefone);
        	System.out.println("Telefone Invalido!");
        }

        return telefoneFormatado.toString();
    }

}