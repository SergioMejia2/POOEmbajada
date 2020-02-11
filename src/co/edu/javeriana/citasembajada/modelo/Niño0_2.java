package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * Clase hijo de la clase abstracta Usuario. Contiene los usuarios de las edades entre
 * 0 y 2 años.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */
public class Niño0_2 extends Usuario implements Serializable
{

	/**
	 * Valor constante para la correcta serialización de los datos
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos únicos de la clase Niño0_2
	 */
	private String acudiente;
	
	/**
	 * Constructor de la clase Niño0_2. Los atributos se cargan con la información dada por argumento
	 * @param ciudadNacimiento con la ciudad de Nacimiento del usuario
	 * @param email con el email del usuario
	 * @param fechaNacimiento con la fecha del usuario
	 * @param nombre con el nombre del usuario
	 * @param numPasaporte con el número de pasaporte
	 * @param paisNacimiento con el país de nacimiento
	 * @param acudiente con el acudiente del niño
	 */
	public Niño0_2(String ciudadNacimiento, String email, LocalDate fechaNacimiento, String nombre,
			String numPasaporte, String paisNacimiento, String acudiente)
	{
		super(ciudadNacimiento, email, fechaNacimiento, nombre, numPasaporte, paisNacimiento);
		this.acudiente = acudiente;
	}

	/**
	 * Método que calcula el valor de la visa para Niño0_2. Se hace un descuento del 90%
	 * @return valor a pagar con descuentos aplicados
	 */
	public double calcularValorVisa()
	{
		double valorBase = this.solicitud.calcularValorVisa();
		return valorBase*0.1;
	}

	/**
	 * Método que retorna el acudiente del niño
	 * @return objeto de tipo String con el acudiente del niño
	 */
	public String getAcudiente()
	{
		return acudiente;
	}
}
