package co.edu.javeriana.citasembajada.controlador;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import co.edu.javeriana.citasembajada.modelo.ISistemaCitaEmbajada;
import co.edu.javeriana.citasembajada.modelo.SistemaCitasEmbajada;
import co.edu.javeriana.citasembajada.modelo.Solicitud;
import co.edu.javeriana.citasembajada.modelo.Usuario;
import co.edu.javeriana.citasembajada.modelo.Visa;
import co.edu.javeriana.citasembajada.persistencia.Persistencia;
import co.edu.javeriana.citasembajada.vista.InterfazGrafica;

/**
 * <p>
 * Clase que controla toda el programa. Recibe la información dada por el usuario desde
 * la vista para el desarrollo lógico del programa y donde se llaman a cada uno de los métodos
 * del modelo que se encargan de realizar toda la lógica del programa.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julian David Parada Galvis
 *
 */

public class Controlador 
{

	// ----------------------------
	//  Relaciones
	// ----------------------------
	
	/**
	 * Se encuentran las relaciones mostradas en el diagrama de clases, una con el sistema
	 * que posee toda la información del programa y otra que permite la interacción con 
	 * el usuario llamada Vista
	 */
	private ISistemaCitaEmbajada modelo;
	private InterfazGrafica vista;

	// ----------------------------
	//  Main
	// ----------------------------

	/**
	 * Método main donde se crea el controlador
	 * @param args
	 */
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		Controlador c = new Controlador();
	}

	// ----------------------------
	//  Constructores
	// ----------------------------
	
	/**
	 * Constructor de la clase donde se crean las relaciones con un nombre específico 
	 * y se crea y llama a mostrar la interfaz. También se encarag de darle los paises
	 * del enumerado a la interfaz para que esta los muestre al momento de escoger el 
	 * pais que funcionara en la embajada.
	 */
	public Controlador()
	{
		modelo = new SistemaCitasEmbajada();
		ingresarTiposVisa();
		List<String> paises = modelo.darPaisesEmbajada();
		vista = new InterfazGrafica(paises,this);
		vista.setVisible(true);
	}

	// ----------------------------
	//  Métodos
	// ----------------------------

	/**
	 * Método encargado de asociar un país al sistema de citas de embajada.
	 * Este metodo es llamado desde la vista cuando se selecciona la opccion
	 * de asociar pais a la embajada, luego este metodo vuelve a llamar a la 
	 * vista para que este le de el pais que se desea asociar y el documento 
	 * donde esta la información de los paises, seguidamente llamando al método de 
	 * la persistencia, que lee el país y se agrega toda la información al modelo.
	 * Muestra un mensaje de error en caso de que el país no exista o en caso de 
	 * que el documento de texto no exista o el pais no sea encontrado en el documento,
	 * de lo contrario se notificará que se asignó correctamente el país.
	 */
	public void asociarPaisEmbajada() 
	{		
		String paisSeleccionado = vista.getPaisSeleccionado();
		String archivoSeleccionado = vista.getArchivoPais();
		try
		{
			Persistencia.leerPais(paisSeleccionado, archivoSeleccionado, modelo);
			vista.actualizarPais(modelo.getPaisEmbajada());
			mostrarInfoEmbajadaAgg();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Método encargado de leer el nombre del archivo donde se encuentran todos los solicitantes
	 * que se asociarán a la embajada.Este metodo es llamado desde la vista cuando se selecciona 
	 * la opccion de agregar usuarios, luego este metodo vuelve a llamar a la 
	 * vista para que este le el documento donde esta la información de los usuarios,
	 * seguidamente llamando al método de la persistencia, que lee el archivo de los usuarios 
	 * y agrega toda la información al modelo.
	 * Muestra un mensaje de error en caso de que el archivo no exista, de lo contrario se 
	 * muestra un mensaje donde los usuarios agregdos correctamente y los que no se agregaron correctamente.
	 */
	public void insertarSolicitantes()
	{
		String archivoSeleccionado = vista.getArchivoUsuarios();
		try
		{
			List <String> usuariosAgregados = new ArrayList<String>();
			List <String> usuariosNoAgregados = new ArrayList<String>();
			Persistencia.LeerSolicitantes(archivoSeleccionado,modelo,usuariosAgregados, usuariosNoAgregados);
			String mensaje = "";
			if(usuariosNoAgregados.isEmpty())
			{
				mensaje = "Todos los usuarios se agregaron correctamente";
			}
			else
			{
				mensaje = "USUARIOS NO AGREGADOS:\n";
				for (String string : usuariosNoAgregados)
				{
					mensaje = mensaje.concat(string+"\n");
				}
			}
			Map<String,Usuario> usuarios = modelo.getUsuarios();
			List<String> llaves = new ArrayList<String>(usuarios.keySet());
			Collections.sort(llaves);
			List <Usuario> usuariosParam = new ArrayList<Usuario>();
			for (String llaveActual : llaves)
			{
				usuariosParam.add(usuarios.get(llaveActual));
			}
			vista.actualizarIngresarSolicitantes(usuariosParam);
			JOptionPane.showMessageDialog(null, mensaje);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método que se encarga de leer los tipos de visa y sus requisitos
	 * que manejará la embajada en sus solicitudes y llama a un método en la persistencia que
	 * lee dicho archivo.
	 * Este metodo se llama en el constructor del controlador y se "quema" este llamado pues en 
	 * la vista no hay una opccion o panel que lo llame.
	 * Muestra un mensaje de error en caso de que el archivo con el nombre especificado no exista.
	 */
	public void ingresarTiposVisa()
	{
		try
		{
			Persistencia.LeerTiposVisa("tiposVisa", modelo);
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Método encargado de realizar la solicitud de visa exclusivamente para la solicitud de tipo turismo,
	 * se crea la solicitud de visa con la lista de solicitantes en null para luego agregarlos.
	 * Este metodo es llamado desde la vista y luego este mismo le solicita los dias de estadia
	 * y tambien el archivo de tarifas para leer la tarifa de la visa.
	 * Mostrara un mensaje de error enn caso de que la solicitud no pueda ser creada.
	 */
	public void realizarSolicitudTurismo()
	{
			try
			{
				int diasEstadia = vista.getDiasEstadia();
				String archivoTarifas = vista.getArchivoTarifas();
				Solicitud solicitudCreada = modelo.realizarSolicitud(archivoTarifas,diasEstadia);
				JOptionPane.showMessageDialog(null,"Solicitud de cita para visa creada exitosamente");
				
				Map<String,Usuario> usuarios = modelo.getUsuarios();
				List<String> llaves = new ArrayList<String>(usuarios.keySet());
				Collections.sort(llaves);
				List <Usuario> usuariosParam = new ArrayList<Usuario>();
				for (String llaveActual : llaves)
				{
					usuariosParam.add(usuarios.get(llaveActual));
				}
				vista.actualizarSolicitud(solicitudCreada.getCodigo(), usuariosParam);

			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
	}

	/**
	 * Método encargado de agregar un usuario a la solicitud previamente creada.
	 * Este metodo es llamado desde la vista y luego este le solicita el numero de pasaporte del usuario a agregar
	 * y el numero de la solicitud al que sera agregado.
	 * @return boolean con true si se pudo agregar y false si no se pudo agregar a la solicitud.
	 */
	public boolean agregarUsuario()
	{
		boolean pudoAgregarse = false;
		try
		{
			String pasaporte = vista.getPasaporteSeleccionado();
			int numSolicitud = vista.getNumSolicitud();
			modelo.agregarUsuarioSolicitud(pasaporte,numSolicitud);
			JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");
			pudoAgregarse = true;
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		return pudoAgregarse;
	}		
	
	/**
	 * Método encargado de realizar la solicitud de visa exclusivamente para la solicitud de tipo trabajo,
	 * se crea la solicitud de visa con la lista de solicitantes en null para luego agregarlos.
	 * Este metodo es llamado desde la vista y luego este mismo le solicita la empresa y el cargo 
	 * del solicitante, tambien el archivo de tarifas para leer la tarifa de la visa.
	 * Mostrara un mensaje de error enn caso de que la solicitud no pueda ser creada.
	 */
	public void realizarSolicitudTrabajo()
	{
		
		try
		{
			String empresa = vista.getEmpresaSeleccionada();
			String cargo = vista.getCargoSeleccionado();
			String archivoTarifas = vista.getArchivoTarifas();
			Solicitud newSolicitud;
			newSolicitud = modelo.realizarSolicitudTrabajo(empresa, cargo, archivoTarifas);
			JOptionPane.showMessageDialog(null, "Solicitud de cita para visa creada exitosamente");
			
			Map<String,Usuario> usuarios = modelo.getUsuarios();
			List<String> llaves = new ArrayList<String>(usuarios.keySet());
			Collections.sort(llaves);
			List <Usuario> usuariosParam = new ArrayList<Usuario>();
			for (String llaveActual : llaves)
			{
				usuariosParam.add(usuarios.get(llaveActual));
			}
			vista.actualizarSolicitud(newSolicitud.getCodigo(), usuariosParam);
			
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Método encargado de realizar la solicitud de visa exclusivamente para la solicitud de tipo estudiante,
	 * se crea la solicitud de visa con la lista de solicitantes en null para luego agregarlos.
	 * Este metodo es llamado desde la vista y luego este mismo le solicita la escolaridad y la institucion 
	 * del solicitante, tambien el archivo de tarifas para leer la tarifa de la visa.
	 * Mostrara un mensaje de error enn caso de que la solicitud no pueda ser creada.
	 */
	public void realizarSolicitudEstudiante()
	{
		
		try
		{
			String institucion = vista.getInstitucion();
			String escolaridad = vista.getEscolaridad();
			String archivoTarifas = vista.getArchivoTarifas();
			Solicitud newSolicitud;
			newSolicitud = modelo.realizarSolicitudEstudiante(escolaridad, institucion, archivoTarifas);
			JOptionPane.showMessageDialog(null, "Solicitud de cita para visa creada exitosamente");
			
			Map<String,Usuario> usuarios = modelo.getUsuarios();
			List<String> llaves = new ArrayList<String>(usuarios.keySet());
			Collections.sort(llaves);
			List <Usuario> usuariosParam = new ArrayList<Usuario>();
			for (String llaveActual : llaves)
			{
				usuariosParam.add(usuarios.get(llaveActual));
			}
			vista.actualizarSolicitud(newSolicitud.getCodigo(), usuariosParam);
			
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	/**
	 * Método que calcula el valor de la visa para los datos en el Panel. Calcula el valor en el modelo y envía
	 * los datos finales a la Interfaz para actualizar el panel
	 * @param code con el tipo de búsqueda (NP para Número de Pasaporte y CP para Código de Solicitud)
	 */
	public void calcularValorVisa(String code)
	{
		float totalSolicitud = 0;
		
		List<Float> listValorVisa = new ArrayList<Float>();
		List<Float> listImpuesto = new ArrayList<Float>();
		List<Float> listValorTotal = new ArrayList<Float>();
		
		if(code.equals("CP"))
		{
			int codigo = vista.getCalcularValor();
			Solicitud solicitudEncontrada = modelo.buscarSolicitud(codigo);
			if(solicitudEncontrada!=null)
			{
				List <Usuario> usuarios = solicitudEncontrada.getSolicitantes();
				for (Usuario usuarioActual : usuarios)
				{
					float impuesto;
					float valorTotal;
					float valorVisa = (float) usuarioActual.calcularValorVisa();
					impuesto = valorVisa*(modelo.getImpuesto()/100);
					
					valorTotal = impuesto+valorVisa;
					totalSolicitud+=valorTotal;
					valorVisa = (float) (valorVisa*modelo.getCambioOficial());
					listValorVisa.add(valorVisa);
					impuesto = (float) (impuesto*modelo.getCambioOficial());
					listImpuesto.add(impuesto);
					valorTotal = (float) (valorTotal*modelo.getCambioOficial());
					listValorTotal.add(valorTotal);
				}
				float pagoMonedaPesos;
				pagoMonedaPesos = (float) (totalSolicitud*modelo.getCambioOficial());
				vista.actualizarCalcularValor(usuarios,listValorVisa,listImpuesto,listValorTotal,pagoMonedaPesos,modelo.getSimbolo(),modelo.getCambioOficial(),totalSolicitud);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"** Código de solicitud inválido", "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(code.equals("NP"))
		{
			int codigo = vista.getCalcularValor();
			String num = String.valueOf(codigo);
			Usuario usuarioEcontrado = modelo.buscarSolicitante(num); 
			if(usuarioEcontrado!=null&&usuarioEcontrado.getSolicitud()!=null)
			{
				Solicitud solicitudEncontrada = usuarioEcontrado.getSolicitud();
				List <Usuario> usuarios = solicitudEncontrada.getSolicitantes();

				for (Usuario usuarioActual : usuarios)
				{
					float impuesto;
					float valorTotal;
					float valorVisa = (float) usuarioActual.calcularValorVisa();
					impuesto = valorVisa*(modelo.getImpuesto()/100);
					
					valorTotal = impuesto+valorVisa;
					totalSolicitud+=valorTotal;
					valorVisa = (float) (valorVisa*modelo.getCambioOficial());
					listValorVisa.add(valorVisa);
					impuesto = (float) (impuesto*modelo.getCambioOficial());
					listImpuesto.add(impuesto);
					valorTotal = (float) (valorTotal*modelo.getCambioOficial());
					listValorTotal.add(valorTotal);
				}
				float pagoMonedaPesos;
				pagoMonedaPesos = (float) (totalSolicitud*modelo.getCambioOficial());
				vista.actualizarCalcularValor(usuarios,listValorVisa,listImpuesto,listValorTotal,pagoMonedaPesos,modelo.getSimbolo(),modelo.getCambioOficial(),totalSolicitud);
			
			}
			else
			{
				JOptionPane.showMessageDialog(null,"**Número de pasaporte inválido", "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Valor ingresado incorrecto", "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Método encargado de generar un reporte de la citas asignadas a una fecha 
	 * ingresada por el usuario, este método llama a persistencia para que ella escriba el 
	 * archivo de texto que contendrá toda la informacion del informe y le asignará 
	 * el nombre de la fecha a dicho archivo.
	 * Este método es llamado desde la vista y luego este mismo le solicita e la vista 
	 * la fecha sobre la cual se realizara el reporte.
	 * Muestra un mensaje de error en caso de que el reporte no se pudo generar, de lo
	 * contrario este muestra un mensaje de confirmación.
	 */
	public void generarReporteCitas()
	{
		String fecha = vista.getFechaReporte();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaReporte = LocalDate.parse(fecha, formatter);
		try
		{
			List <Usuario> usuariosCitas = Persistencia.generarReporte(modelo, fechaReporte);
			if(!usuariosCitas.isEmpty())
			{
				
				JOptionPane.showMessageDialog(null,"El reporte se generó correctamente");
				vista.actualizarReporteCitas(usuariosCitas);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Para la fecha seleccionada, no existen citas agendadas");
			}
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Método encargado de llamar al método de la persistencia que lee 
	 * un archivo serializado y carga los datos en el sistema.
	 * Este método es llamado desde la vista y luego este mismo le pide a la 
	 * vista el archivo previamente serializado que se va a cargar.
	 */
	public void leerDatosSistema()
	{
		try
		{
			String archivoCargar = vista.getArchivoACargar();
			modelo = Persistencia.leerSistema(archivoCargar);
			JOptionPane.showMessageDialog(null, "Sistema cargado exitosamente");
			Solicitud.CONSECUTIVO+=modelo.getSolicitudes().size();
			vista.actualizarPais(modelo.getPaisEmbajada());
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}		
	}

	/**
	 * Método encargado de serializar la información del sistema
	 * para poder ser usada en otro sistema.
	 * Este método es llamado desde la vista y luego este mismo le pide a la 
	 * vista la dirección donde se guardara el archivo serializado.
	 */
	public void serializarSistema()
	{
		try
		{
			String direccionGuardar = vista.getDireccionAGuardar();
			Persistencia.serializarSistema(modelo, direccionGuardar);
			JOptionPane.showMessageDialog(null, "Sistema guardado exitosamente");
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Método encargado de generar el reporte de usuarios beneficiados por algun tipo de descuento
	 * al momento de calcular el valor de su solicitud de visa, y además muestra el dinero que dejó 
	 * de recaudar la embajada con estos descuentos.
	 * El método recalcula el valor de la visa de cada usuario sin descuentos y lo compara con el valor
	 * que este pagó.
	 * Cada linea que será escrita en el archivo será agregada a una lista de String y tabien se esribe
	 * de una forma similar a otra lista de String para ser mostrada en una tabla como informe en la vista.
	 * Al final la primera lista esenviada a la persistencia para generar el archivo del reporte y la segunda
	 * a la vista para mostrarla en una tabla.
	 * Se muestra un mensaje de error en caso de que ocurra algo generando el reporte, de lo contrario se da
	 * una confirmación de que el reporte de genero correctamente.
	 */
	public void generarReporteBeneficiados()
	{
		List <Usuario> usuariosDescuento = modelo.darUsuariosDescuento();
		List <String> lineasArchivo = new ArrayList <String>();
		List <String> lineasInterfaz = new ArrayList <String>();
		double valorPerdido = 0;
		String valorPerdidoValor;
		
		lineasArchivo.add("--Lista de beneficiarios");
		lineasArchivo.add("#numPass—--nombre----- valorTotal (pesos)");
		
		for (Usuario usuarioActual : usuariosDescuento)
		{
			Solicitud solicitudUsuario = usuarioActual.getSolicitud();
			Visa visaSolicitud = solicitudUsuario.getVisaSolicitud();
			double valorBase = visaSolicitud.getValor();
			double valorPagado = usuarioActual.calcularValorVisa();
			valorPerdido += (valorBase - valorPagado);
			valorBase = valorBase*(1+(modelo.getImpuesto()/100));
			valorBase = valorBase*modelo.getCambioOficial();
			String impresion = String.format("%1$-13s %2$-16s $%3$-11s", usuarioActual.getNumPasaporte(),usuarioActual.getNombre(),(float)valorBase);
			String row = usuarioActual.getNumPasaporte() + "*" + usuarioActual.getNombre()+"*" + "$"+(float)valorBase;
			lineasArchivo.add(impresion);
			lineasInterfaz.add(row);
		}
		
		lineasArchivo.add("\n");
		valorPerdidoValor= "$" + (valorPerdido*modelo.getCambioOficial());
		
		valorPerdido = valorPerdido*modelo.getCambioOficial();
		lineasArchivo.add("El valor total en pesos que se dejó de recaudar por visas de beneficiados es: $"+(float)valorPerdido);
		try
		{
			Persistencia.generarReporteBeneficiados(lineasArchivo);
			vista.mostrarUsuariosBeneficiarios(valorPerdidoValor, lineasInterfaz);
			JOptionPane.showMessageDialog(null,"El reporte se generó correctamente");
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	/**
	 * Método encargado de mostrar la información del país que se encuentra asociado
	 * a la embajada por medio de una JOptionPane.
	 */
	public void mostrarInfoEmbajada()
	{
		String idNombre = "["+modelo.getId()+"] "+"Embajada de "+modelo.getPaisEmbajada();
		String moneda = "La moneda es: "+modelo.getMoneda();
		String impuesto = "Impuesto es: "+modelo.getImpuesto();
		String cambioOficial = "El cambio oficial es: $"+modelo.getCambioOficial();
		
		if(modelo.getPaisEmbajada() == null)
		{
			JOptionPane.showMessageDialog(null, "No hay un pais asociado a la embajada", "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, idNombre +"\n" +moneda+"\n"+ impuesto +"%"+"\n"+cambioOficial +"\n");
	
		}
	}
	
	/**
	 * Método encargado de mostrar la informació de la embajada una vez sea agregada por medio
	 * de una JOptionPane.
	 */
	public void mostrarInfoEmbajadaAgg()
	{
		JOptionPane.showMessageDialog(null, "Se ha asignado correctamente el país al sistema.");
		mostrarInfoEmbajada();
	}
}
