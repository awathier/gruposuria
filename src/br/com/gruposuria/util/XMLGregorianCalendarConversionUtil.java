package br.com.gruposuria.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Classe para converção entre datas do tipo java.util.Date e
 * XMLGregorianCalendar
 */
public class XMLGregorianCalendarConversionUtil {

	/*
	 * DatatypeFactory cria novos objetos que javax.xml.datatype mapa XML para
	 * objetos Java.
	 */
	private static DatatypeFactory df = null;

	static {
		try {
			df = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			String msg = "Erro ao tentar obter uma nova instância de DatatypeFactory";
			throw new IllegalStateException(msg, e);
		}
	}

	/*
	 * Converte um java.util.Date em uma instância de XMLGregorianCalendar no
	 * formato yyyy-MM-dd, sem os milisegundos
	 */
	public static XMLGregorianCalendar asXMLGregorianCalendar(
			java.util.Date date) {
		if (date == null) {
			return null;
		} else {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);

			XMLGregorianCalendar xmlGregorianCalendar = df
					.newXMLGregorianCalendar();
			xmlGregorianCalendar.setDay(calendar.get(Calendar.DAY_OF_MONTH));
			xmlGregorianCalendar.setMonth(calendar.get(Calendar.MONTH));
			xmlGregorianCalendar.setYear(calendar.get(Calendar.YEAR));

			return xmlGregorianCalendar;
		}
	}

	// Converte um XMLGregorianCalendar para uma instância de java.util.Date
	public static java.util.Date asDate(XMLGregorianCalendar xmlGC) {
		if (xmlGC == null) {
			return null;
		} else {
			return xmlGC.toGregorianCalendar().getTime();
		}
	}

}