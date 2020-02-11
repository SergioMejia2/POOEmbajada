package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;

/**
 * <p>
 * Clase hijo de la clase abstracta Visa. Contiene la informacion del tipo de visa Estudiante.
 * 
 * @author Sergio Andr�s Mej�a Tovar
 * @author Juli�n David Parada Galvis
 */
public class Estudiante extends Visa  implements Serializable
{

	/**
	 * N�mero de la versi�n serial de la implementaci�n Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos �nicos de la clase Estudiante. 
	 */
	private Escolaridad escolaridad;
	private String institucion;
	
	/**
	 * Constructor de la clase Estudiante. Los atributos se cargan con la informaci�n dada por argumento
	 * @param valor con el valor de la visa de tipo estudiante
	 * @param id con el id unico del tipo de visa estudiante
	 * @param escolaridad con el atributo propio de la visa de tipo estudiante, corresponde 
	 * a la escolaridad del solicitante
	 * @param institucion con el atributo propio de la visa de tipo estudiante, corresponde 
	 * a la institucion a la cual pertenece el solicitante
	 */
	public Estudiante (double valor, int id, String escolaridad, String institucion)
	{
		super(valor, id);
		this.escolaridad = Escolaridad.valueOf(escolaridad.toUpperCase());
		this.institucion = institucion;
	}
	
	/**
	 * M�todo constructor sobrecargado para instanciar la clase Estudiante con sus datos vac�os.
	 */
	public Estudiante()
	{
		super(0,0);
		this.escolaridad = null;
		this.institucion = null;
	}

	/**
	 * M�todo encargado de agregar una solcitud al tipo de visa Estudiante.
	 */
	public void agregarSolicitud(Solicitud solicitud)
	{
		solicitudes.add(solicitud);
	}
	
	/**
	 * M�todo encargado de dar la escolaridad del usuario que solicita este tipo de visa.
	 * @return String con la escolaridad del usuario que solicita este tipo de visa.
	 */
	public String getEscolaridad()
	{
		return escolaridad.toString();
	}
	
	/**
	 * M�todo encargado de dar la Instituci�n del usuario que solicita este tipo de visa.
	 * @return String con la Instituci�n del usuario que solicita este tipo de visa.
	 */
	public String getInstitucion()
	{
		return institucion;
	}
}
