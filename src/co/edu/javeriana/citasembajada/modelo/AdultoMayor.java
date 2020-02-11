package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * Clase hijo de la clase abstracta Usuario. Contiene los usuarios adultos mayores
 * (edades mayores a 65 a�os).
 * 
 * @author Sergio Andr�s Mej�a Tovar
 * @author Juli�n David Parada Galvis
 */
public class AdultoMayor extends Usuario implements Serializable
{

	/**
	 * Valor constante para la correcta serializaci�n de los datos
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase Adulto Mayor. Los atributos se cargan con la informaci�n dada por argumento
	 * @param ciudadNacimiento con la ciudad de Nacimiento del usuario
	 * @param email con el email del usuario
	 * @param fechaNacimiento con la fecha del usuario
	 * @param nombre con el nombre del usuario
	 * @param numPasaporte con el n�mero de pasaporte
	 * @param paisNacimiento con el pa�s de nacimiento
	 */
	public AdultoMayor(String ciudadNacimiento, String email, LocalDate fechaNacimiento, String nombre,
			String numPasaporte, String paisNacimiento)
	{
		super(ciudadNacimiento, email, fechaNacimiento, nombre, numPasaporte, paisNacimiento);

	}

	/**
	 * M�todo que calcula el valor de la visa para Adulto Mayor. Se realiza un descuento del 75%
	 * aunque puede hacerse un incremento del 10% si la visa es de turismo 
	 * @return valor a pagar
	 */
	public double calcularValorVisa()
	{
		double valorBase = this.solicitud.calcularValorVisa();
		double valorVisa = valorBase * 0.75;
		if(this.solicitud.getVisaSolicitud() instanceof Turismo)
		{
			valorVisa*=1.1;
		}
		return valorVisa;
	}
}
