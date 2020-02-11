package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Solicitud es una clase del modelo del programa. Consiste en la clase que guarda la información
 * de una solicitud de visa. Contiene relaciones bidireccionales con la clase Visa y Usuario.
 * Se puede serializar el objeto al tener implementado el Serializable.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julian David Parada Galvis
 *
 */
public class Solicitud implements Serializable
{
	/**
	 * Número de la versión serial de la implementación Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	//ATRIBUTOS
	/**
	 * Acorde al diagrama de clases, la clase Solicitud posee atributos para la identificación
	 * de esta dentro de una lista. Posee un código único, un estado que por defecto se encontrará
	 * en pendiente y una fecha y hora relacionadas a la hora de la cita. Es la clase que posee
	 * el atributo estático del Consecutivo por el cual se asignarán los códigos de las futuras
	 * solicitudes.
	 */
	private int codigo;
	public static int CONSECUTIVO=1000;
	private String estado;
	private LocalDateTime fecha;

	/**
	 * Se encuentran las relaciones mostradas en el diagrama de clases. Estas en específico
	 * hacen referencia a las relaciones bidireccionales con las clases Visa y Usuario. Cada
	 * Solicitud puede tener varios usuarios solicitantes y solo un tipo de visa asignada a la
	 * solicitud.
	 */
	private Visa visaSolicitud;
	private List<Usuario> solicitantes;

	// ----------------------------
	//  Constructor
	// ----------------------------
	/**
	 *  Constructor de la clase. Inicia las listas vacías y sus atributos con los parámetros
	 *  y el valor CONSECUTIVO.
	 *  
	 *  @param pVisa Visa asignada a la solicitud
	 */
	public Solicitud(Visa pVisa)
	{
		solicitantes = new ArrayList<Usuario>();
		visaSolicitud = pVisa;
		codigo = CONSECUTIVO;
		estado = "Pendiente";
	}

	// ----------------------------
	//  Métodos
	// ----------------------------
	/**
	 *  Método para agregar un Usuario Solicitante a la lista de Usuarios del objeto
	 *  
	 *  @param usuario Usuario a agregar a la lista
	 *  @return usuario Usuario agregado para validar si se pudo agregar correctamente
	 */
	public Usuario agregarSolicitante(Usuario usuario)
	{
		solicitantes.add(usuario);
		return usuario;
	}

	/**
	 * Método que retorna el id único de la solicitud
	 * @return int con el id único de la solicitud
	 */
	public int getCodigo()
	{
		return codigo;
	}

	/**
	 * Método que retorna el estado de la solicitud
	 * @return String con el estado de la solicitud
	 */
	public String getEstado()
	{
		return estado;
	}

	/**
	 * Método que retorna la fecha y hora de la solicitud
	 * @return LocalDateTime con la fecha y hora de la solicitud
	 */
	public LocalDateTime getFecha()
	{
		return fecha;
	}

	/**
	 * Método que asigna la fecha y hora de la solicitud
	 * @param fecha LocalDateTime con la fecha y hora de la solicitud
	 */
	public void setFecha(LocalDateTime fecha)
	{
		this.fecha = fecha;
	}

	/**
	 * Método que retorna la Visa asignada de la solicitud
	 * @return Visa con la Visa asignada de la solicitud
	 */
	public Visa getVisaSolicitud()
	{
		return visaSolicitud;
	}

	/**
	 * Método que retorna la Lista de usuarios asignados a la solicitud
	 * @return Lista de tipo Usuario con la Lista de usuarios asignados a la solicitud
	 */
	public List<Usuario> getSolicitantes()
	{
		return solicitantes;
	}

	/**
	 * Método que retorna el valor base del tipo de visa asociado a la Solicitud
	 * @return Double con el valor base del tipo de visa asociado a la Solicitud
	 */
	public double calcularValorVisa()
	{
		return visaSolicitud.getValor();
	}
}
