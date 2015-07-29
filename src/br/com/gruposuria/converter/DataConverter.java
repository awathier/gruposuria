package br.com.gruposuria.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("dataConverter")
public class DataConverter implements Converter {
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
         Date data = new Date();
         
         data = getDateTime(value);

         return data;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
         Date data = (Date) value;
         String dataFormatada = "";
         
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
         
         if (data != null && !data.equals("")) {
              dataFormatada = dateFormat.format(data);
         }
         
         return dataFormatada;
    }
    
	private Date getDateTime(String data) { 
		Date dataFormatada = null;
		
		if (data!= null && !data.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			try {
				return dateFormat.parse(data);
			} catch (ParseException e) {
				//
			}
		}
		
		return dataFormatada;
	}

}