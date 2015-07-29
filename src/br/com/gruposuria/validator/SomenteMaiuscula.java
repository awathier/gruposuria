package br.com.gruposuria.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("SomenteMaiuscula")
public class SomenteMaiuscula implements Validator{

	public void validate(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		
		String valor = value.toString();
		//���������������������������������������������������
		
		if (!valor.matches("^[A-Z0-9������������������������� ]*$")) {
			FacesMessage msg =  new FacesMessage("Somente letra mai�scula.", "Somente letra mai�scula.");
  			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
  			throw new ValidatorException(msg);
		}
		
	}
	
}
