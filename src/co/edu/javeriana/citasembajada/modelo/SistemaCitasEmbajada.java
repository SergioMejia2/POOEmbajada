package co.edu.javeriana.citasembajada.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.javeriana.citasembajada.persistencia.Persistencia;
import co.edu.javeriana.citasembajada.vista.Utils;

/**
 * <p>
 * SistemaCitasEmbajada es la clase principal del modelo del programa. En ella se encuentra
 * la información de los objetos principales del programa. Contiene el map de Usuarios, la
 * lista de Visas y Solicitudes,y un map adicional de los tipos de visa con sus requisitos, 
 * ademas es la encargada de comunicarse con el controlador para realizar funciones principales del programa. 
 * Se puede serializar el objeto al tener implementado el Serializable. 
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julian David Parada Galvis
 *
 */
public class SistemaCitasEmbajada implements ISistemaCitaEmbajada, Serializable
{
	// ----------------------------
	//  Constantes
	// ----------------------------

	/**
	 * Número de la versión serial de la implementación Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;

	// ----------------------------
	//  Atributos
	// ----------------------------

	/**
	 * Acorde al diagrama de clases, se tienen los datos principales del sistema
	 * que corresponden a la información general de la embajada. Se encuentran el nombre del
	 * país asignado, el nombre de la moneda, la tasa de cambio oficial a peso colombiano,
	 * el porcentaje de impuesto del valor de la visa, el id del país que existe en el archivo
	 * de los países y el símbolo de la moneda (USD, EUR, etc.)
	 */
	private PaisEmbajada paisEmbajada;
	private String moneda;
	private double cambioOficial;
	private float impuesto;
	private int id;
	private String simbolo;

	// ----------------------------
	//  Relaciones
	// ----------------------------
	/**
	 * Se encuentran las relaciones mostradas en el diagrama de clases. Al poseer
	 * estas dos listas y el mapa, el sistema se convierte en el poseedor de toda la información
	 * del programa.
	 */
	private List<Visa> visas;
	private List<Solicitud> solicitudes;
	private Map<String,Usuario> usuarios;
	private Map<String,List<Requisito> > visasRequisitos;

	// ----------------------------
	//  Constructor
	// ----------------------------
	/**
	 *  Constructor de la clase. Inicia las listas y los maps vacíos y su atributos vacíos
	 */
	public SistemaCitasEmbajada()
	{
		visas = new ArrayList<Visa>();
		solicitudes = new ArrayList<Solicitud>();
		usuarios = new HashMap<String,Usuario>();
		visasRequisitos = new HashMap<String, List<Requisito> >();
		paisEmbajada = null;
		moneda = "";
		cambioOficial = 0;
		impuesto = 0;
		id = 0;
	}
	
	// ----------------------------
	//  Métodos
	// ----------------------------
	
	/**
	 * Método que agrega un solicitante dada una lista de datos
	 * @param datos con la información del usuario. Los datos deben contener la siguiente información en orden: 
	 * Pasaporte, Nombre, Pais de Origen, Ciudad de Nacimiento, fecha de Nacimiento, email e Información Adicional
	 * @return objeto de tipo Usuario con los datos agregados
	 */
	public Usuario agregarSolicitante(String[] datos)
	{
		Usuario usuarioNuevo = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate DateNacimiento = LocalDate.parse(datos[4].trim(), formatter);
		int edad = Utils.edadEnAnnos(DateNacimiento, LocalDate.now());
		String numPasaporte = datos[0].trim();
		String nombre = datos[1].trim();
		String paisNacimiento = datos[2].trim();
		String ciudadNacimiento = datos[3].trim();
		String email = datos[5].trim();
		if(usuarios.containsKey(numPasaporte))
		{
			return usuarioNuevo;
		}
		if(edad>=0 && edad<=2)
		{
			String acudiente = datos[6].trim();
			usuarioNuevo = new Niño0_2(ciudadNacimiento, email, DateNacimiento, nombre, numPasaporte, paisNacimiento, acudiente);
		}
		else if(edad > 2 && edad<=12)
		{
			String escolaridad = datos[6].trim();
			usuarioNuevo = new Niño2_12(ciudadNacimiento, email, DateNacimiento, nombre, numPasaporte, paisNacimiento, escolaridad);
		
		}
		else if(edad > 12 && edad <=65)
		{
			usuarioNuevo = new Adulto(ciudadNacimiento, email, DateNacimiento, nombre, numPasaporte, paisNacimiento);
		}
		else if(edad>65)
		{
			usuarioNuevo = new AdultoMayor(ciudadNacimiento, email, DateNacimiento, nombre, numPasaporte, paisNacimiento);
		}
		usuarios.put(numPasaporte, usuarioNuevo);
		return usuarioNuevo;
	}
	
	/**
	 * Método que agrega los requisitos de una visa dado su nombre.
	 * @param tipoVisa con el nombre del tipo de Visa
	 * @param pRequisitos con una lista de String de los requisitos
	 * @return boolean en true si se pudo agregar y false si no.
	 */
	public boolean agregarTipoVisa(String tipoVisa,  List<String> pRequisitos)
	{
		List <Requisito>requisitos = new ArrayList<Requisito>();
		boolean pudoAgregar = false;
		
		if(tipoVisa.equalsIgnoreCase("Turismo"))
		{
			for (String requisitoActual : pRequisitos)
			{
				Requisito newRequisito = new Requisito(requisitoActual);
				requisitos.add(newRequisito);
			}
			visasRequisitos.put("Turismo", requisitos);
			pudoAgregar = true;
		}
		else if(tipoVisa.equalsIgnoreCase("Trabajo"))
		{
			for (String requisitoActual : pRequisitos)
			{
				Requisito newRequisito = new Requisito(requisitoActual);
				requisitos.add(newRequisito);
			}
			visasRequisitos.put("Trabajo", requisitos);
			pudoAgregar = true;
		}
		else if(tipoVisa.equalsIgnoreCase("Estudiante"))
		{
			for (String requisitoActual : pRequisitos)
			{
				Requisito newRequisito = new Requisito(requisitoActual);
				requisitos.add(newRequisito);
			}
			visasRequisitos.put("Estudiante", requisitos);
			pudoAgregar = true;
		}
		return pudoAgregar;
	}
	
	/**
	 * Método que busca un objeto de tipo Visa basado en el nombre del tipo
	 * @param string con el tipo de visa a buscar
	 * @return objeto de tipo Visa que corresponde al nombre buscado. Retorna null si no se encuentra
	 */
	public List<Requisito> buscarVisa(String string)
	{
		List<Requisito> visaEncontrada = null;
		if(visasRequisitos.containsKey(string))
		{
			visaEncontrada = visasRequisitos.get(string);
		}
		return visaEncontrada;
	}
	
	/**
	 * Método que busca un Usuario basado en un número de pasaporte
	 * @param string con el número de pasaporte a buscar
	 * @return objeto de tipo Usuario encontrado, retorna null si no se encontró
	 */
	public Usuario buscarSolicitante(String string)
	{
		Usuario usuarioEncontrado = null;
		if(usuarios.containsKey(string))
		{
			usuarioEncontrado = usuarios.get(string);
		}
		return usuarioEncontrado;
	}
	
	/**
	 * Método que realiza una solicitud de tipo Turismo, este crea la solicitud con la lista de usuarios vacia o en null.
	 * @param archivoTarifas con la dirección que contiene las tarifas de cada tipo de visa.
	 * @param diasEstadia con el número de días de estadia en el país
	 * @return objeto de tipo Solicitud con la solicitud creada
	 * @throws Exception si sucede algún problema durante la creación del objeto
	 */
	public Solicitud realizarSolicitud(String archivoTarifas, int diasEstadia) throws Exception
	{
		Visa nuevaVisa = null;
		LocalDateTime ultimaFecha;
		Solicitud nuevaSolicitud = null;
		
		String linea = Persistencia.leerTarifa("turismo", archivoTarifas);
		String datos[] = linea.split("\\*");
		int id = Integer.parseInt(datos[0].trim());
		double valor = Double.parseDouble(datos[2].trim());
		nuevaVisa = new Turismo(valor, id, diasEstadia);

		nuevaSolicitud = new Solicitud(nuevaVisa); 
		nuevaVisa.agregarSolicitud(nuevaSolicitud);
		int numSolicitudes = solicitudes.size();
		if(numSolicitudes!=0)
		{
			Solicitud ultimaSolicitud = solicitudes.get(numSolicitudes-1);
			ultimaFecha = ultimaSolicitud.getFecha();
			ultimaFecha = Utils.actualizarFecha(ultimaFecha);
			if(ultimaFecha.isBefore(LocalDateTime.now()))
			{
				ultimaFecha = Utils.compararFechaHoraOficina(LocalDateTime.now());
			}
		}
		else
		{
			ultimaFecha = Utils.compararFechaHoraOficina(LocalDateTime.now());
		}
		nuevaSolicitud.setFecha(ultimaFecha);
		solicitudes.add(nuevaSolicitud);
		Solicitud.CONSECUTIVO++;

		return nuevaSolicitud;
	}
	
	/**
	 * Método encargado de agregar un usuario a una solicitud previamente creada usando el 
	 * pasaporte del usuario a agregar.
	 * @param pasaporte con el numero de pasaporte del usuario a agregar.
	 * @param numSolicitud con el numero de la solicitud a la que se va a agregar el usuario.
	 */
	public void agregarUsuarioSolicitud(String pasaporte, int numSolicitud) throws Exception
	{
		Usuario usuario;
		if(usuarios.containsKey(pasaporte))
		{
			usuario = usuarios.get(pasaporte);
			Solicitud solicitud = buscarSolicitud(numSolicitud);
			if(usuario.getSolicitud() != null)
			{
				throw new Exception("El usuario ya tiene una solicitud de visa pendiente");
			}
			Visa visa = solicitud.getVisaSolicitud();
			if (visa instanceof Estudiante)
			{
				if(!(usuario instanceof Niño2_12) || !(((Estudiante) visa).getEscolaridad().equals(((Niño2_12) usuario).getEscolaridad())))
					throw new Exception("El usuario no posee escolaridad o la escolaridad del usuario no coincide con la de la solicitud");
			}
			usuario.setSolicitud(solicitud);
			solicitud.agregarSolicitante(usuario);
			usuarios.put(pasaporte, usuario); //Lo reescribe
		}
		else
		{
			throw new Exception("El usuario no existe");
		}
	}
	
	/**
	 * Método que realiza una solicitud de tipo trabajo, este crea la solicitud con la lista de usuarios vacia o en null.
	 * @param archivoTarifas con la dirección que contiene las tarifas de cada tipo de visa.
	 * @param diasEstadia con el número de días de estadia en el país
	 * @param dato1 con la empresa a la que pertenece el solicitante
	 * @param dato2 con el cargo que posee el solicitante en la empresa
	 * @return objeto de tipo Solicitud con la solicitud creada
	 * @throws Exception si sucede algún problema durante la creación del objeto
	 */
	public Solicitud realizarSolicitudTrabajo(String dato1, String dato2, String archivoTarifas) throws Exception
	{
		Solicitud nuevaSolicitud = null;
		Visa nuevaVisa = null;
		LocalDateTime ultimaFecha;

		String linea = Persistencia.leerTarifa("trabajo", archivoTarifas);
		String datos[] = linea.split("\\*");
		int id = Integer.parseInt(datos[0].trim());
		double valor = Double.parseDouble(datos[2].trim());
		nuevaVisa = new Trabajo(valor, id, dato1, dato2); 
		
		nuevaSolicitud = new Solicitud(nuevaVisa);
		int numSolicitudes = solicitudes.size();
		if(numSolicitudes!=0)
		{
			Solicitud ultimaSolicitud = solicitudes.get(numSolicitudes-1);
			ultimaFecha = ultimaSolicitud.getFecha();
			ultimaFecha = Utils.actualizarFecha(ultimaFecha);
			if(ultimaFecha.isBefore(LocalDateTime.now()))
			{
				ultimaFecha = Utils.compararFechaHoraOficina(LocalDateTime.now());
			}
		}
		else
		{
			ultimaFecha = Utils.compararFechaHoraOficina(LocalDateTime.now());
		}
		nuevaSolicitud.setFecha(ultimaFecha);
		solicitudes.add(nuevaSolicitud);
		Solicitud.CONSECUTIVO++;
		return nuevaSolicitud;
	}

	/**
	 * Método que realiza una solicitud de tipo estudiante, este crea la solicitud con la lista de usuarios vacia o en null.
	 * @param archivoTarifas con la dirección que contiene las tarifas de cada tipo de visa.
	 * @param diasEstadia con el número de días de estadia en el país
	 * @param dato1 con la esolaridad que posee el solicitante
	 * @param dato2 con la institución a la que pertenece el solicitante
	 * @return objeto de tipo Solicitud con la solicitud creada
	 * @throws Exception si sucede algún problema durante la creación del objeto
	 */
	public Solicitud realizarSolicitudEstudiante(String dato1, String dato2, String archivoTarifas) throws Exception
	{
		Solicitud nuevaSolicitud = null;
		Visa nuevaVisa = null;
		LocalDateTime ultimaFecha;

		String linea = Persistencia.leerTarifa("Estudiante", archivoTarifas);
		String datos[] = linea.split("\\*");
		int id = Integer.parseInt(datos[0].trim());
		double valor = Double.parseDouble(datos[2].trim());
		nuevaVisa = new Estudiante(valor, id, dato1, dato2); //···//

		nuevaSolicitud = new Solicitud(nuevaVisa);
		int numSolicitudes = solicitudes.size();
		if(numSolicitudes!=0)
		{
			Solicitud ultimaSolicitud = solicitudes.get(numSolicitudes-1);
			ultimaFecha = ultimaSolicitud.getFecha();
			ultimaFecha = Utils.actualizarFecha(ultimaFecha);
			if(ultimaFecha.isBefore(LocalDateTime.now()))
			{
				ultimaFecha = Utils.compararFechaHoraOficina(LocalDateTime.now());
			}
		}
		else
		{
			ultimaFecha = Utils.compararFechaHoraOficina(LocalDateTime.now());
		}
		nuevaSolicitud.setFecha(ultimaFecha);
		solicitudes.add(nuevaSolicitud);
		Solicitud.CONSECUTIVO++;
		return nuevaSolicitud;
	}
	
	/**
	 * Método que busca una solicitud basado en el id dado 
	 * @param codigo con el id que se va a buscar
	 * @return objeto de tipo Solicitud encontrado, retorna null si no se encontró
	 */
	public Solicitud buscarSolicitud(int codigo)
	{
		Solicitud solicitudEncontrada = null;
		
		for (Solicitud solicitudActual : solicitudes)
		{
			if(solicitudActual.getCodigo()==codigo)
			{
				solicitudEncontrada = solicitudActual;
				break;
			}
		}
		return solicitudEncontrada;
	}
	
	/**
	 * Método encargado de dar los paises que puede manejar el sistema de solicitud de citas embajada
	 * que se encuentran en el Enumerado PaisEmbajada.
	 * @return List con la lista de los paises que estan en el Enumerado.
	 */
	public List<String> darPaisesEmbajada()
	{
		List<String> ListPaises = new ArrayList<String>();
		PaisEmbajada paises[] = PaisEmbajada.values();
		
		for (PaisEmbajada paisEmbajadaActual : paises)
		{
			ListPaises.add(paisEmbajadaActual.toString());
		}
		return ListPaises;
	}
	
	/**
	 * Método que genera una lista con los usuarios que obtuvieron descuento y la retorna
	 * @return	Lista de tipo Usuario con los usuarios que obtuvieron descuento
	 */
	public List<Usuario> darUsuariosDescuento()
	{
		List <Usuario> usuariosDescuento = new ArrayList <Usuario>();
		List<String> llaves = new ArrayList<String>(usuarios.keySet());
		Collections.sort(llaves);
		for (String llaveActual : llaves)
		{
			Usuario usuarioActual = usuarios.get(llaveActual);
			if(usuarioActual.getSolicitud()!=null)
			{
				int edad = Utils.edadEnAnnos(usuarioActual.getFechaNacimiento(), LocalDate.now());
				if(edad<=12 || edad > 65)
				{
					usuariosDescuento.add(usuarioActual);
				}
			}
		}
		Collections.sort(usuariosDescuento);
		return usuariosDescuento;
	}
	
	/**
	 * Método que devuelve el valor del nombre del país de la embajada
	 * @return Nombre de la embajada
	 */
	public String getPaisEmbajada()
	{
		if(paisEmbajada != null)
		{
			return paisEmbajada.toString();
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Método que asigna el valor del nombre del país de la embajada
	 * @param paisEmbajada con el nombre del país de la embajada
	 */
	public void setPaisEmbajada(String paisEmbajada)
	{
		this.paisEmbajada = PaisEmbajada.valueOf(paisEmbajada.toUpperCase());
	}

	/**
	 * Método que devuelve el valor del nombre de la moneda de la embajada
	 * @return Nombre de la moneda
	 */
	public String getMoneda()
	{
		return moneda;
	}

	/**
	 * Método que asigna el valor del nombre de la del país de la embajada
	 * @param moneda con el nombre de la moneda del país de la embajada
	 */
	public void setMoneda(String moneda)
	{
		this.moneda = moneda;
	}

	/**
	 * Método que devuelve el valor del cambio oficial de pesos colombianos a la moneda de la embajada
	 * @return cambioOficial con el valor de cambio X COP =  1 Moneda Embajada
	 */
	public double getCambioOficial()
	{
		return cambioOficial;
	}

	/**
	 * Método que asigna el valor del cambio oficial de pesos colombianos a la moneda de la embajada
	 * @param cambioOficial con el valor de cambio X COP = 1 Moneda Embajada
	 */
	public void setCambioOficial(double cambioOficial)
	{
		this.cambioOficial = cambioOficial;
	}

	/**
	 * Método que devuelve el valor de impuesto de una solicitud de visa, si es 35% se retorna 35
	 * @return impuesto con el valor en porcentaje del impuesto
	 */
	public float getImpuesto()
	{
		return impuesto;
	}

	/**
	 * Método que asigna el valor de impuesto de una solicitud de visa, si es 35% se retorna 35
	 * @param impuesto con el valor en porcentaje del impuesto
	 */
	public void setImpuesto(float impuesto)
	{
		this.impuesto = impuesto;
	}

	/**
	 * Método que devuelve el valor del id del país de la embajada
	 * @return id con el valor del id del país de la embajada
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Método que asigna el valor del id del país de la embajada
	 * @param id con el valor del id del país de la embajada
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Método que devuelve el valor del símbolo de la moneda del país de la embajada
	 * @return simbolo con el valor de símbolo de la moneda del país de la embajada
	 */
	public String getSimbolo()
	{
		return simbolo;
	}

	/**
	 * Método que asigna el valor del símbolo de la moneda del país de la embajada
	 * @param simbolo con el valor de símbolo de la moneda del país de la embajada
	 */
	public void setSimbolo(String simbolo)
	{
		this.simbolo = simbolo;
	}

	/**
	 * Método que devuelve la lista de Visas concretas para cada solicitud
	 * @return Lista de Visas
	 */
	public List<Visa> getVisas()
	{
		return visas;
	}

	/**
	 * Método que devuelve la lista de Solicitudes de la embajada
	 * @return Lista de Solicitudes
	 */
	public List<Solicitud> getSolicitudes()
	{
		return solicitudes;
	}

	/**
	 * Método que devuelve el Map de Usuarios de la embajada
	 * @return Map de tipo String, Usuario
	 */
	public Map<String, Usuario> getUsuarios()
	{
		return usuarios;
	}

	/**
	 * Método que devuelve el Map con las visas que contienen los requisitos
	 * @return visasRequisitos con el Map de las visas que contienen los requisitos
	 */
	public Map<String, List<Requisito> 	> getVisasRequisitos()
	{
		return visasRequisitos;
	}
}
