package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;

/**
 * <p>
 * Clase Requisito es la clase que guarda los requsitos de los tipos de visa que tramita la embajada. 
 * Es parte del modelo y contiene los requsitos de los tipos de visa que tramita la embajada.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */

public class Requisito implements Serializable, Comparable<Requisito> 
{

	/**
	 * Número de la versión serial de la implementación Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo de la clase Requisito. Modela la información de cada requisito de visa.
	 */
	private String descripcion;
	
	/**
	 * Constructor de la clase Requisito. Los atributos se cargan con la información dada por argumento.
	 * @param requisitoActual con la descripcion de cada requisito como String.
	 */
	public Requisito(String requisitoActual)
	{
		descripcion = requisitoActual;
	}

	/**
	 * Método encargado de dar la descripcion de cada uno de los requisitos.
	 * @return un String con la descripcion de cada requisito.
	 */
	public String getDescripcion()
	{
		return descripcion;
	}
	
	/**
	 * Método comparador para organizar alfabéticamente los requisitos
	 * @param otro con el requsito a comparar.
	 */
	public int compareTo(Requisito otro) 
    {
    	String descripcion1 = this.getDescripcion();
    	String descripcion2 = otro.getDescripcion();
        return descripcion1.compareTo(descripcion2);
    } 
}
