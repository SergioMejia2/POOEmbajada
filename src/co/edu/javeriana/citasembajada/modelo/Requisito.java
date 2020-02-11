package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;

/**
 * <p>
 * Clase Requisito es la clase que guarda los requsitos de los tipos de visa que tramita la embajada. 
 * Es parte del modelo y contiene los requsitos de los tipos de visa que tramita la embajada.
 * 
 * @author Sergio Andr�s Mej�a Tovar
 * @author Juli�n David Parada Galvis
 */

public class Requisito implements Serializable, Comparable<Requisito> 
{

	/**
	 * N�mero de la versi�n serial de la implementaci�n Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo de la clase Requisito. Modela la informaci�n de cada requisito de visa.
	 */
	private String descripcion;
	
	/**
	 * Constructor de la clase Requisito. Los atributos se cargan con la informaci�n dada por argumento.
	 * @param requisitoActual con la descripcion de cada requisito como String.
	 */
	public Requisito(String requisitoActual)
	{
		descripcion = requisitoActual;
	}

	/**
	 * M�todo encargado de dar la descripcion de cada uno de los requisitos.
	 * @return un String con la descripcion de cada requisito.
	 */
	public String getDescripcion()
	{
		return descripcion;
	}
	
	/**
	 * M�todo comparador para organizar alfab�ticamente los requisitos
	 * @param otro con el requsito a comparar.
	 */
	public int compareTo(Requisito otro) 
    {
    	String descripcion1 = this.getDescripcion();
    	String descripcion2 = otro.getDescripcion();
        return descripcion1.compareTo(descripcion2);
    } 
}
