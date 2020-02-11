package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;

/**
 * <p>
 * Clase hijo de la clase abstracta Visa. Contiene la informacion del tipo de visa Trabajo.
 * 
 * @author Sergio Andr�s Mej�a Tovar
 * @author Juli�n David Parada Galvis
 */
public class Trabajo extends Visa implements Serializable
{

	/**
	 * N�mero de la versi�n serial de la implementaci�n Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos �nicos de la clase Trabajo. 
	 */
	private String empresa;
	private String cargo;
	
	/**
	 * Constructor de la clase Trabajo. Los atributos se cargan con la informaci�n dada por argumento
	 * @param valor con el valor de la visa de tipo Trabajo
	 * @param id con el id unico del tipo de visa Trabajo
	 * @param empresa con el atributo propio de la visa de tipo Trabajo, corresponde 
	 * a la empresa del solicitante
	 * @param cargo con el atributo propio de la visa de tipo Trabajo, corresponde 
	 * al cargo del solicitante
	 */
	public Trabajo (double valor, int id, String empresa, String cargo)
	{
		super(valor, id);
		this.empresa = empresa;
		this.cargo = cargo;
	}
	
	/**
	 * M�todo constructor sobrecargado para instanciar la clase Trabajo con sus datos vac�os.
	 */
	public Trabajo()
	{
		super(0,0);
		this.empresa = null;
		this.cargo = null;
	}

	/**
	 * M�todo encargado de agregar una solcitud al tipo de visa Trabajo.
	 */
	public void agregarSolicitud(Solicitud solicitud)
	{
		solicitudes.add(solicitud);
	}

	/**
	 * M�todo encargado de dar la empresa a la cual pertenece el solicitante de este tipo de visa.
	 * @return String con la empresa a la cual pertenece el solicitante de este tipo de visa.
	 */
	public String getEmpresa()
	{
		return empresa;
	}
	
	/**
	 * M�todo encargado de dar el cargo el cual posee el solicitante de este tipo de visa.
	 * @return String con el cargo el cual posee el solicitante de este tipo de visa.
	 */
	public String getCargo()
	{
		return cargo;
	}
}
