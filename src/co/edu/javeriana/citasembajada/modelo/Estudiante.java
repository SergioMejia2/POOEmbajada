package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;

/**
 * <p>
 * Clase hijo de la clase abstracta Visa. Contiene la informacion del tipo de visa Estudiante.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */
public class Estudiante extends Visa  implements Serializable
{

	/**
	 * Número de la versión serial de la implementación Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos únicos de la clase Estudiante. 
	 */
	private Escolaridad escolaridad;
	private String institucion;
	
	/**
	 * Constructor de la clase Estudiante. Los atributos se cargan con la información dada por argumento
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
	 * Método constructor sobrecargado para instanciar la clase Estudiante con sus datos vacíos.
	 */
	public Estudiante()
	{
		super(0,0);
		this.escolaridad = null;
		this.institucion = null;
	}

	/**
	 * Método encargado de agregar una solcitud al tipo de visa Estudiante.
	 */
	public void agregarSolicitud(Solicitud solicitud)
	{
		solicitudes.add(solicitud);
	}
	
	/**
	 * Método encargado de dar la escolaridad del usuario que solicita este tipo de visa.
	 * @return String con la escolaridad del usuario que solicita este tipo de visa.
	 */
	public String getEscolaridad()
	{
		return escolaridad.toString();
	}
	
	/**
	 * Método encargado de dar la Institución del usuario que solicita este tipo de visa.
	 * @return String con la Institución del usuario que solicita este tipo de visa.
	 */
	public String getInstitucion()
	{
		return institucion;
	}
}
