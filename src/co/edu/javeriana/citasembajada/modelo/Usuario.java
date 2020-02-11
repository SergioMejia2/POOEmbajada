package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * Clase Usuario es la clase que abstrae a los clientes que utilizan la embajada. Es parte
 * del modelo y contiene los atributos de los usuarios de la embajada.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */

public abstract class Usuario implements Serializable, Comparable<Usuario>
{

	/**
	 * Número de la versión serial de la implementación Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos de la clase Usuario. Modelan la información personal de usuario
	 */
	protected String ciudadNacimiento;
	protected String email;
	protected LocalDate fechaNacimiento;
	protected String nombre;
	protected String numPasaporte;
	protected String paisNacimiento;
	
	/**
	 * Relaciones de clase Usuario con los otros objetos del modelo. Se encuentra la solicitud
	 * que puede tener el usuario
	 */
	protected Solicitud solicitud;
	
	/**
	 * Constructor de la clase Usuario. Los atributos se cargan con la información dada por argumento
	 * @param ciudadNacimiento con la ciudad de Nacimiento del usuario
	 * @param email con el email del usuario
	 * @param fechaNacimiento con la fecha del usuario
	 * @param nombre con el nombre del usuario
	 * @param numPasaporte con el número de pasaporte
	 * @param paisNacimiento con el país de nacimiento
	 */
	public Usuario(String ciudadNacimiento, String email, LocalDate fechaNacimiento, String nombre, 
			String numPasaporte, String paisNacimiento)
	{
		this.ciudadNacimiento = ciudadNacimiento;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
		this.numPasaporte = numPasaporte;
		this.paisNacimiento = paisNacimiento;
	}
	
	/**
	 * Método abstracto que calcula el valor de la visa para un usuario. Se implementa 
	 * en las clases hijo
	 * @return valor a pagar con descuentos aplicados
	 */
	public abstract double calcularValorVisa();
	
	/**
	 * Método que retorna la solicitud del usuario
	 * @return objeto de tipo Solicitud con la solicitud del usuario
	 */
	public Solicitud getSolicitud()
	{
		return solicitud;
	}

	/**
	 * Método que asigna la solicitud del usuario
	 * @param solicitud con la solicitud del usuario
	 */
	public void setSolicitud(Solicitud solicitud)
	{
		this.solicitud = solicitud;
	}

	/**
	 * Método que retorna la ciudad de nacimiento del usuario
	 * @return objeto de tipo String con la ciudad de nacimiento del usuario
	 */
	public String getCiudadNacimiento()
	{
		return ciudadNacimiento;
	}

	/**
	 * Método que retorna el email del usuario
	 * @return objeto de tipo String con el email del usuario
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Método que retorna la fecha de nacimiento del usuario
	 * @return objeto de tipo LocalDate con la fecha de nacimiento del usuario
	 */
	public LocalDate getFechaNacimiento()
	{
		return fechaNacimiento;
	}

	/**
	 * Método que retorna el nombre del usuario
	 * @return objeto de tipo String con el nombre del usuario
	 */
	public String getNombre()
	{
		return nombre;
	}
	
	/** 
	 * Método que retorna el número de pasaporte del usuario
	 * @return objeto de tipo String con el número de pasaporte del usuario
	 */
	public String getNumPasaporte()
	{
		return numPasaporte;
	}
	
	/** 
	 * Método que retorna el país de nacimiento del usuario
	 * @return objeto de tipo String con el país de nacimiento del usuario
	 */
	public String getPaisNacimiento()
	{
		return paisNacimiento;
	}

	/**
	 * Método que sobrecarga la comparación de este objeto. Usado en ordenamiento
	 */
	public int compareTo(Usuario otro) 
	{
		String nombre1 = this.getNombre();
		String nombre2 = otro.getNombre();
	    return nombre1.compareTo(nombre2);
	}	
}
