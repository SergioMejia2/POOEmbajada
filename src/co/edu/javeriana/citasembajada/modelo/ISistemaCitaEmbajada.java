package co.edu.javeriana.citasembajada.modelo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Interfaz del modelo SistemaCitasEmbajada que contiene los m�todos abstractos 
 * de la clase SistemaCitasmebajada.
 * 
 * @author Sergio Andr�s Mej�a Tovar
 * @author Julian David Parada Galvis
 *
 */
public interface ISistemaCitaEmbajada 
{
	/**
	 * M�todo que devuelve el valor del nombre del pa�s de la embajada
	 * @return Nombre de la embajada
	 */
	public String getPaisEmbajada();

	/**
	 * M�todo que asigna el valor del nombre del pa�s de la embajada
	 * @param paisEmbajada con el nombre del pa�s de la embajada
	 */
	public void setPaisEmbajada(String paisEmbajada);

	/**
	 * M�todo que devuelve el valor del nombre de la moneda de la embajada
	 * @return Nombre de la moneda
	 */
	public String getMoneda();

	/**
	 * M�todo que asigna el valor del nombre de la del pa�s de la embajada
	 * @param moneda con el nombre de la moneda del pa�s de la embajada
	 */
	public void setMoneda(String moneda);

	/**
	 * M�todo que devuelve el valor del cambio oficial de pesos colombianos a la moneda de la embajada
	 * @return cambioOficial con el valor de cambio X COP =  1 Moneda Embajada
	 */
	public double getCambioOficial();

	/**
	 * M�todo que asigna el valor del cambio oficial de pesos colombianos a la moneda de la embajada
	 * @param cambioOficial con el valor de cambio X COP = 1 Moneda Embajada
	 */
	public void setCambioOficial(double cambioOficial);

	/**
	 * M�todo que devuelve el valor de impuesto de una solicitud de visa, si es 35% se retorna 35
	 * @return impuesto con el valor en porcentaje del impuesto
	 */
	public float getImpuesto();

	/**
	 * M�todo que asigna el valor de impuesto de una solicitud de visa, si es 35% se retorna 35
	 * @param impuesto con el valor en porcentaje del impuesto
	 */
	public void setImpuesto(float impuesto);

	/**
	 * M�todo que devuelve el valor del id del pa�s de la embajada
	 * @return id con el valor del id del pa�s de la embajada
	 */
	public int getId();

	/**
	 * M�todo que asigna el valor del id del pa�s de la embajada
	 * @param id con el valor del id del pa�s de la embajada
	 */
	public void setId(int id);

	/**
	 * M�todo que devuelve el valor del s�mbolo de la moneda del pa�s de la embajada
	 * @return simbolo con el valor de s�mbolo de la moneda del pa�s de la embajada
	 */
	public String getSimbolo();

	/**
	 * M�todo que asigna el valor del s�mbolo de la moneda del pa�s de la embajada
	 * @param simbolo con el valor de s�mbolo de la moneda del pa�s de la embajada
	 */
	public void setSimbolo(String simbolo);

	
	/**
	 * M�todo que devuelve el Map con las visas que contienen los requisitos
	 * @return visasRequisitos con el Map de las visas que contienen los requisitos
	 */
	public Map<String, List<Requisito>> getVisasRequisitos();

	/**
	 * M�todo que devuelve la lista de Visas concretas para cada solicitud
	 * @return Lista de Visas
	 */
	public List<Visa> getVisas();

	/**
	 * M�todo que devuelve la lista de Solicitudes de la embajada
	 * @return Lista de Solicitudes
	 */
	public List<Solicitud> getSolicitudes();

	/**
	 * M�todo que devuelve el Map de Usuarios de la embajada
	 * @return Map de tipo String, Usuario
	 */
	public Map<String, Usuario> getUsuarios();

	/**
	 * M�todo que agrega un solicitante dada una lista de datos
	 * @param datos con la informaci�n del usuario. Los datos deben contener la siguiente informaci�n en orden: 
	 * Pasaporte, Nombre, Pais de Origen, Ciudad de Nacimiento, fecha de Nacimiento, email e Informaci�n Adicional
	 * @return objeto de tipo Usuario con los datos agregados
	 */
	public Usuario agregarSolicitante(String[] datos);

	/**
	 * M�todo que agrega los requisitos de una visa dado su nombre.
	 * @param tipoVisa con el nombre del tipo de Visa
	 * @param pRequisitos con una lista de String de los requisitos
	 * @return boolean en true si se pudo agregar y false si no.
	 */
	public boolean agregarTipoVisa(String tipoVisa, List<String> requisitos);
	
	/**
	 * M�todo que busca un objeto de tipo Visa basado en el nombre del tipo
	 * @param string con el tipo de visa a buscar
	 * @return objeto de tipo Visa que corresponde al nombre buscado. Retorna null si no se encuentra
	 */
	public List<Requisito> buscarVisa(String string);

	/**
	 * M�todo que busca un Usuario basado en un n�mero de pasaporte
	 * @param string con el n�mero de pasaporte a buscar
	 * @return objeto de tipo Usuario encontrado, retorna null si no se encontr�
	 */
	public Usuario buscarSolicitante(String num);

	/**
	 * M�todo que realiza una solicitud de tipo Turismo, este crea la solicitud con la lista de usuarios vacia o en null.
	 * @param archivoTarifas con la direcci�n que contiene las tarifas de cada tipo de visa.
	 * @param diasEstadia con el n�mero de d�as de estadia en el pa�s
	 * @return objeto de tipo Solicitud con la solicitud creada
	 * @throws Exception si sucede alg�n problema durante la creaci�n del objeto
	 */
	public Solicitud realizarSolicitud(String archivoTarifas, int diasEstadia) throws Exception;

	/**
	 * M�todo que realiza una solicitud de tipo trabajo, este crea la solicitud con la lista de usuarios vacia o en null.
	 * @param archivoTarifas con la direcci�n que contiene las tarifas de cada tipo de visa.
	 * @param diasEstadia con el n�mero de d�as de estadia en el pa�s
	 * @param dato1 con la empresa a la que pertenece el solicitante
	 * @param dato2 con el cargo que posee el solicitante en la empresa
	 * @return objeto de tipo Solicitud con la solicitud creada
	 * @throws Exception si sucede alg�n problema durante la creaci�n del objeto
	 */
	public Solicitud realizarSolicitudTrabajo(String dato1, String dato2, String archivoTarifas) throws Exception;
	
	/**
	 * M�todo que realiza una solicitud de tipo estudiante, este crea la solicitud con la lista de usuarios vacia o en null.
	 * @param archivoTarifas con la direcci�n que contiene las tarifas de cada tipo de visa.
	 * @param diasEstadia con el n�mero de d�as de estadia en el pa�s
	 * @param dato1 con la esolaridad que posee el solicitante
	 * @param dato2 con la instituci�n a la que pertenece el solicitante
	 * @return objeto de tipo Solicitud con la solicitud creada
	 * @throws Exception si sucede alg�n problema durante la creaci�n del objeto
	 */
	public Solicitud realizarSolicitudEstudiante(String dato1, String dato2, String archivoTarifas) throws Exception;
	
	/**
	 * M�todo que busca una solicitud basado en el id dado 
	 * @param codigo con el id que se va a buscar
	 * @return objeto de tipo Solicitud encontrado, retorna null si no se encontr�
	 */
	public Solicitud buscarSolicitud(int codigo);

	/**
	 * M�todo que genera una lista con los usuarios que obtuvieron descuento y la retorna
	 * @return	Lista de tipo Usuario con los usuarios que obtuvieron descuento
	 */
	public List<Usuario> darUsuariosDescuento();
	
	/**
	 * M�todo encargado de dar los paises que puede manejar el sistema de solicitud de citas embajada
	 * que se encuentran en el Enumerado PaisEmbajada.
	 * @return List con la lista de los paises que estan en el Enumerado.
	 */
	public List<String> darPaisesEmbajada();

	/**
	 * M�todo encargado de agregar un usuario a una solicitud previamente creada usando el 
	 * pasaporte del usuario a agregar.
	 * @param pasaporte con el numero de pasaporte del usuario a agregar.
	 * @param numSolicitud con el numero de la solicitud a la que se va a agregar el usuario.
	 */
	public void agregarUsuarioSolicitud(String pasaporte, int numSolicitud) throws Exception;

}
