package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * Clase hijo de la clase abstracta Usuario. Contiene los usuarios de las edades entre
 * 0 y 2 a�os.
 * 
 * @author Sergio Andr�s Mej�a Tovar
 * @author Juli�n David Parada Galvis
 */
public class Ni�o0_2 extends Usuario implements Serializable
{

	/**
	 * Valor constante para la correcta serializaci�n de los datos
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos �nicos de la clase Ni�o0_2
	 */
	private String acudiente;
	
	/**
	 * Constructor de la clase Ni�o0_2. Los atributos se cargan con la informaci�n dada por argumento
	 * @param ciudadNacimiento con la ciudad de Nacimiento del usuario
	 * @param email con el email del usuario
	 * @param fechaNacimiento con la fecha del usuario
	 * @param nombre con el nombre del usuario
	 * @param numPasaporte con el n�mero de pasaporte
	 * @param paisNacimiento con el pa�s de nacimiento
	 * @param acudiente con el acudiente del ni�o
	 */
	public Ni�o0_2(String ciudadNacimiento, String email, LocalDate fechaNacimiento, String nombre,
			String numPasaporte, String paisNacimiento, String acudiente)
	{
		super(ciudadNacimiento, email, fechaNacimiento, nombre, numPasaporte, paisNacimiento);
		this.acudiente = acudiente;
	}

	/**
	 * M�todo que calcula el valor de la visa para Ni�o0_2. Se hace un descuento del 90%
	 * @return valor a pagar con descuentos aplicados
	 */
	public double calcularValorVisa()
	{
		double valorBase = this.solicitud.calcularValorVisa();
		return valorBase*0.1;
	}

	/**
	 * M�todo que retorna el acudiente del ni�o
	 * @return objeto de tipo String con el acudiente del ni�o
	 */
	public String getAcudiente()
	{
		return acudiente;
	}
}
