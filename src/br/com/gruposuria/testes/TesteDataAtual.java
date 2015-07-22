package br.com.gruposuria.testes;

import java.util.Calendar;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.MILLISECOND;

public class TesteDataAtual {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
        cal.set( HOUR_OF_DAY, 0 );
        cal.set( MINUTE, 0 );
        cal.set( SECOND, 0 );
        cal.set( MILLISECOND, 0 );
        System.out.println( cal.getTime() );

	}

}
