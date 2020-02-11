package co.edu.javeriana.citasembajada.vista;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * Utils es una clase apoyo que contiene métodos estáticos necesarios para ciertas funcionalidades
 * del programa. Si bien los métodos aquí no tienen que ver específicamente con el propósito general
 * del programa, son necesarios para el funcionamiento de este.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julian David Parada Galvis
 *
 */
public class Utils
{
	/**
	 * Método que dadas dos fechas, devuelve la edad de una persona en esa fecha.
	 *  
	 * @param fecha1 que corresponde a la fecha de nacimiento de una persona
	 * @param fecha2 que corresponde al día al que se quiere calcular los años de la persona
	 * @return Entero con el número de años de la persona
	 */
	public static int edadEnAnnos(LocalDate fecha1, LocalDate fecha2)
	{
		int annos, mes, dia;
		
		annos = fecha2.getYear()-fecha1.getYear();
		mes = fecha2.getMonthValue()-fecha1.getMonthValue();
		dia= fecha2.getDayOfMonth()-fecha1.getDayOfMonth();
		if((mes==0 && dia<0) || (mes<0))
			annos--;
		return annos;
	}
	
	/**
	 * Método que actualiza la fecha de cita dada según los horario de oficina. El horario
	 * comprende desde las 8 de la mañana hasta las 4 de la tarde los días Lune a Viernes. Las
	 * citas duran una hora y no pueden pasarse de las 4 de la tarde o ser un sábado o domingo.
	 * 
	 * @param fecha LocalDateTime de la Fecha de la cita a actualizar
	 * @return LocalDateTime de la Fecha actualizada
	 */
	public static LocalDateTime actualizarFecha(LocalDateTime fecha)
	{
		LocalDateTime fechaNueva;
		fechaNueva = fecha.plusHours(1);
		fechaNueva = compararFechaHoraOficina(fechaNueva);
		return fechaNueva;
	}
	
	/**
	 * Método encargado de comparar la fecha y la hora que se le esta asignado a la cita
	 * para la solcitud de visa esta dentro del horario y dias de trabajo de la embajada.
	 * 
	 * @param fechaNueva con la fecha que se le asigno a la cita y se verificara.
	 * @return Con la fecha final de la cita de la solicirtud, puede ser la misma "fechaNueva"
	 * dado el caso que esta se encuntre dentro del horario y los dias de trabajo de la oficina.
	 */
	public static LocalDateTime compararFechaHoraOficina(LocalDateTime fechaNueva)
	{
		if(fechaNueva.getHour()>15||(fechaNueva.getHour()==15&&fechaNueva.getMinute()>0))
		{
			fechaNueva = fechaNueva.plusDays(1);
			if(fechaNueva.getDayOfWeek()==DayOfWeek.SATURDAY)
			{
				fechaNueva = fechaNueva.plusDays(2);
			}
			else if(fechaNueva.getDayOfWeek()==DayOfWeek.SUNDAY)
			{
				fechaNueva = fechaNueva.plusDays(1);
			}
			fechaNueva = LocalDateTime.of(fechaNueva.getYear(), fechaNueva.getMonth(), fechaNueva.getDayOfMonth(), 8, 0);
		}
		return fechaNueva;
	}
}