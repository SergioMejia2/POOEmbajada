package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;
import java.time.LocalDate;

import co.edu.javeriana.citasembajada.vista.Utils;

/**
 * <p>
 * Clase hijo de la clase abstracta Usuario. Contiene los usuarios de las edades entre
 * 2 y 12 años.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */
public class Niño2_12 extends Usuario  implements Serializable
{

	/**
	 * Valor constante para la correcta serialización de los datos
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos únicos de la clase Niño2_12
	 */
	private Escolaridad escolaridad;
	
	/**
	 * Constructor de la clase Niño2_12. Los atributos se cargan con la información dada por argumento
	 * @param ciudadNacimiento con la ciudad de Nacimiento del usuario
	 * @param email con el email del usuario
	 * @param fechaNacimiento con la fecha del usuario
	 * @param nombre con el nombre del usuario
	 * @param numPasaporte con el número de pasaporte
	 * @param paisNacimiento con el país de nacimiento
	 * @param escolaridad con el nivel de educación del niño
	 */
	public Niño2_12(String ciudadNacimiento, String email, LocalDate fechaNacimiento, String nombre,
			String numPasaporte, String paisNacimiento, String escolaridad)
	{
		super(ciudadNacimiento, email, fechaNacimiento, nombre, numPasaporte, paisNacimiento);
		this.escolaridad = Escolaridad.valueOf(escolaridad.toUpperCase());
	}

	/**
	 * Método que calcula el valor de la visa para Niño2_12. Se hace un descuento 
	 * basado en la edad y el tipo de la visa
	 * @return valor a pagar con descuentos aplicados
	 */
	public double calcularValorVisa()
	{
		double valorBase = this.solicitud.calcularValorVisa();
		int edad = Utils.edadEnAnnos(fechaNacimiento, LocalDate.now());
		int difEdad = 18 - edad;
		double valorVisa = valorBase * (1-(0.05*difEdad));
		if(this.solicitud.getVisaSolicitud() instanceof Turismo)
		{
			valorVisa = valorVisa * 1.2;
		}
		else if(this.solicitud.getVisaSolicitud() instanceof Estudiante)
		{
			valorVisa = valorVisa * 0.7;
		}
		return valorVisa;
	}
	
	/**
	 * Método que retorna la escolaridad del niño
	 * @return objeto de tipo String con la escolaridad del niño
	 */
	public String getEscolaridad()
	{
		return escolaridad.name(); 
	}

}
