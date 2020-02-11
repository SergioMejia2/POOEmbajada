package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;

/**
 * <p>
 * Clase hijo de la clase abstracta Visa. Contiene la informacion del tipo de visa Turismo.
 * 
 * @author Sergio Andr�s Mej�a Tovar
 * @author Juli�n David Parada Galvis
 */
public class Turismo extends Visa implements Serializable
{

	/**
	 * N�mero de la versi�n serial de la implementaci�n Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos �nicos de la clase Turismo. 
	 */
	private int diasEstadia;
	
	/**
	 * Constructor de la clase Turismo. Los atributos se cargan con la informaci�n dada por argumento
	 * @param valor con el valor de la visa de tipo Turismo
	 * @param id con el id unico del tipo de visa Turismo
	 * @param diasEstadia con el atributo propio de la visa de tipo Turismo, corresponde 
	 * a los dias de estadia durante el viaje del solicitante
	 */
	public Turismo (double valor, int id, int diasEstadia)
	{
		super(valor,id);
		this.diasEstadia = diasEstadia;
	}
	
	/**
	 * M�todo constructor sobrecargado para instanciar la clase Turismo con sus datos vac�os.
	 */
	public Turismo()
	{
		super(0,0);
		this.diasEstadia = 0;
	}

	/**
	 * M�todo encargado de agregar una solcitud al tipo de visa Turismo.
	 */
	public void agregarSolicitud(Solicitud solicitud)
	{
		solicitudes.add(solicitud);
	}
	
	/**
	 * M�todo encargado de dar los dias de estadia durante el viaje del solicitante.
	 * @return Int con los dias de estadia durante el viaje del solicitante.
	 */
	public int getDiasEstadia()
	{
		return diasEstadia;
	}
}
