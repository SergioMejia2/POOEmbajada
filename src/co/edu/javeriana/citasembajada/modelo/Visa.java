package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Clase Visa es la clase que abstrae a los tipos de visa que tramita la embajada. Es parte
 * del modelo y contiene los atributos de los tipos de visa que tramita la embajada.
 * 
 * @author Sergio Andr�s Mej�a Tovar
 * @author Juli�n David Parada Galvis
 */

public abstract class Visa implements Serializable
{
	/**
	 * N�mero de la versi�n serial de la implementaci�n Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos de la clase Visa. Modelan la informaci�n de cada tipo de visa.
	 */
	protected double valor;
	protected int id;
	
	/**
	 * Relaciones de clase Visa con los otros objetos del modelo. Se encuentran las lista de 
	 * solicitudes.
	 */
	public List <Solicitud> solicitudes;
	
	/**
	 * Constructor de la clase Visa. Los atributos se cargan con la informaci�n dada por argumento
	 * @param valor con el valor base que se lee de persistencia de cada tipo de visa.
	 * @param id con el id unico que identifica cada tipo de visa.
	 */
	public Visa(double valor, int id)
	{
		solicitudes = new ArrayList<Solicitud>();
		this.valor = valor;
		this.id = id;
	}
	
	/**
	 * M�todo abstracto que agrega una solicitud a la lista de solicitudes de cada tipo de visa.
	 * @param solicitud con la solicitud que sera agregada.
	 */
	public abstract void agregarSolicitud(Solicitud solicitud);

	/**
	 * M�todo encargado de retornar el valor de la visa.
	 * @return el valor de la visa.
	 */
	public double getValor()
	{
		return this.valor;
	}

	/**
	 * Metodo encargado de retornar el id unico de la visa.
	 * @return id unico de la visa.
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * M�todo encargado de dar la lista de solicitudes sociadas a un tipo visa.
	 * @return la lista de solicitudes sociadas a un tipo visa.
	 */
	public List<Solicitud> getSolicitudes()
	{
		return this.solicitudes;
	}

	
}
