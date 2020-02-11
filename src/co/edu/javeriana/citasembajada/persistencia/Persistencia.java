package co.edu.javeriana.citasembajada.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.edu.javeriana.citasembajada.modelo.ISistemaCitaEmbajada;
import co.edu.javeriana.citasembajada.modelo.Solicitud;
import co.edu.javeriana.citasembajada.modelo.Usuario;
import co.edu.javeriana.citasembajada.modelo.Visa;

/**
 * <p>
 * Persistencia es una clase que contiene métodos estáticos necesarios para el manejo de archivos
 * del programa.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julian David Parada Galvis
 *
 */

public class Persistencia
{
	// ----------------------------
	//  Métodos
	// ----------------------------

	/**
	 * Método encargado de leer un país que está en un archivo de texto. Encontrar y extraer
	 * la información del país que se le manda por árgumento y agregar dicha información al modelo.
	 *
	 * @param namePais con el nombre del país a buscar
	 * @param nameArchivo con la dirección del archivo donde está la información de los países
	 * @param modelo con la interfaz que conecta con la clase SistemaCitasEmbaja donde se guardara la información del país
	 * @throws Exception Lanza una excepción en caso de que el país no exista
	 */
	public static void leerPais(String namePais, String nameArchivo, ISistemaCitaEmbajada modelo) throws Exception
	{
		BufferedReader br = new BufferedReader (new FileReader (nameArchivo));
		String linea = br.readLine();
		boolean encontro = false;
		while(linea != null) {
			if(!linea.startsWith("#")) 
			{
				String datos[] = linea.split("\\*");
				String nombrePais = datos[1].trim();
				if(nombrePais.equalsIgnoreCase(namePais)) 
				{
					int id = Integer.parseInt(datos[0].trim());
					encontro = true;
					modelo.setId(id);
					modelo.setPaisEmbajada(nombrePais);
					String moneda = datos[2].trim();
					modelo.setMoneda(moneda);
					String impuesto;
					impuesto = datos[3];
					impuesto = impuesto.replace("%", "");
					modelo.setImpuesto(Float.parseFloat(impuesto));
					double cambioOficial = Double.parseDouble(datos[4].trim());
					modelo.setCambioOficial(cambioOficial);
					String simbolo = datos[5].trim();
					modelo.setSimbolo(simbolo);
				}
			}
			linea = br.readLine();
		}
		br.close();
		if(!encontro)
		{
			throw new Exception("El país no existe");
		}
	}
	
	/**
	 * Método encargado de leer los solicitantes que están en un archivo de texto, agrega dicha información al modelo.
	 * 
	 * @param nameArchivo con la dirección del archivo que contiene los solicitantes
	 * @param modelo con la interfaz que conecta con la clase SistemaCitasEmbaja donde se guardará la información
	 * @param usuariosAgregados esta será la lista donde se guardarán los usuarios que se pudieron agregar correctamente al sistema
	 * @param usuariosNoAgregados esta será la lista donde se guardarán los usuarios que no se pudieron agregar correctamente al sistema
	 * @throws Exception Lanza una Excepción dado el caso de que algun solicitantes ya exista 
	 * o que el archivo este vacío.
	 */
	public static void LeerSolicitantes(String nameArchivo, ISistemaCitaEmbajada modelo,List<String> usuariosAgregados, List<String> usuariosNoAgregados) throws Exception
	{
		BufferedReader br = new BufferedReader (new FileReader (nameArchivo));	
		String linea = br.readLine();
		boolean encontro = false;
		Usuario solicitanteBuscado = null;
		while(linea != null) 
		{
			if(!linea.startsWith("#")) 
			{
				String datos[]=linea.split("\\*");
				solicitanteBuscado = modelo.agregarSolicitante(datos);
				if(solicitanteBuscado==null)
				{
					usuariosNoAgregados.add(datos[0]);
				}
				else
				{
					usuariosAgregados.add(datos[0]);
				}
				encontro = true;
			}
			linea = br.readLine();
		}
		if(!encontro)
		{
			br.close();
			throw new Exception("No existen usuarios");
		}
		br.close();
	}
	
	/**
	 * Método encargado de leer los tipos de visa y sus requisitos de un archivo de texto
	 * 
	 * @param nameArchivo con el nombre del archivo donde se encuentran los tipos de visa
	 * @param modelo con la interfaz que conecta con la clase SistemaCitasEmbaja donde se guardará la información
	 * @throws Exception Lanzara una excepción en caso de que el tipo de visa ya exista 
	 * o en caso de que el archivo este vacio.
	 */
	public static void LeerTiposVisa(String nameArchivo, ISistemaCitaEmbajada modelo) throws Exception
	{
		List <String> requisitos;
		if(nameArchivo.endsWith(".txt"))
		{
			nameArchivo = nameArchivo.replace(".txt", "");
		}
		BufferedReader br = new BufferedReader(new FileReader("./data/"+nameArchivo+".txt"));	
		String linea = br.readLine();
		boolean encontro = false; 
		boolean visaBuscada;
		while (linea!=null && !linea.equals("#FIN"))
		{
			requisitos = new ArrayList<String>();
			if(linea.equals("#VISA"))
			{
				linea = br.readLine();
				linea = br.readLine();
				String tipoVisa = linea.trim();
				linea = br.readLine();
				linea = br.readLine();
				while(!linea.equals("#VISA")&&!linea.equals("#FIN"))
				{
					requisitos.add(linea.trim());
					linea = br.readLine();
				}
				encontro = true;
				visaBuscada = modelo.agregarTipoVisa(tipoVisa,requisitos);
				if(!visaBuscada)
				{
					br.close();
					throw new Exception("El tipo de visa ya existe o está mal ingresado");
				}
			}
		}
		if(!encontro)
		{
			br.close();
			throw new Exception("No existen visas");
		}
		br.close();
	}

	/**
	 * Método encargado de leer la información de una visa, como su id y su tarifa base o valor.
	 * 
	 * @param pTipo con el tipo de visa del que se va a leer la información.
	 * @param archivoTarifas  con la dirección del archivo donde estan las tarifas de cada tipo de visa
	 * @return String con la linea de texto que contiene toda la información de un unico tipo de visa.
	 * @throws Exception Lanzara una excepción en caso de que el tipo de visa no se encuentre en el archivo.
	 */
	public static String leerTarifa(String pTipo, String archivoTarifas) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(archivoTarifas));	
		String linea = br.readLine();
		linea = br.readLine();
		linea = br.readLine();
		boolean encontro = false;
		while(linea!=null && !linea.equals("#FIN"))
		{
			String datos[] = linea.split("\\*");
			if(datos[1].trim().equalsIgnoreCase(pTipo))
			{
				br.close();
				encontro = true;
				return linea;
			}
			linea = br.readLine();
		}
		br.close();
		if(!encontro)
		{
			throw new Exception("La visa no se encuentra en el archivo");
		}
		return null;
	}

	/**
	 * Método encargado de generar el reporte de las citas asignadas por la embajada a la 
	 * fecha recibida por argumento y colocando de nombre del archivo de texto la fecha 
	 * a la cual se hizo el informe.
	 * 
	 * @param modelo con la interfaz que conecta con toda la información del sistema 
	 * @param fechaReporte la fecha a la cual se hace el reporte
	 * @return List de Usuarios con la lista de usuarios que se agregaron al informe.
	 * @throws Exception Lanza una excepción si existe problemas con el archivo
	 */
	public static List<Usuario> generarReporte(ISistemaCitaEmbajada modelo, LocalDate fechaReporte) throws Exception
	{
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		List <Usuario> usuariosReporte = new ArrayList <Usuario>();
		File archivo = new File("./data/Citas-"+fechaReporte.format(formato)+".txt");
		PrintWriter pw = new PrintWriter(archivo);
		pw.println("--REPORTE DE SOLICITUDES EMBAJADA DE "+modelo.getPaisEmbajada().toUpperCase());
		pw.println("Fecha: "+fechaReporte);
		pw.println();
		List <Solicitud> solicitudes = modelo.getSolicitudes();
		boolean existenUsuarios = false;
		for(Solicitud solicitudActual: solicitudes)
		{
			LocalDateTime fecha = solicitudActual.getFecha();
			LocalDate fechaSolicitud = fecha.toLocalDate();
			if(fechaSolicitud.isEqual(fechaReporte))
			{
				existenUsuarios = true;
			}
		}
		if(existenUsuarios)
		{
			pw.println("#numPass—-----nombre-----------tipoVisa----numSolicitud");

			for (Solicitud solicitudActual : solicitudes)
			{
				LocalDateTime fecha = solicitudActual.getFecha();
				LocalDate fechaSolicitud = fecha.toLocalDate();
				if(fechaSolicitud.isEqual(fechaReporte))
				{
					List<Usuario> usuariosSolicitud = solicitudActual.getSolicitantes();
					for (Usuario usuarioActual : usuariosSolicitud)
					{
						usuariosReporte.add(usuarioActual);
					}
				}
			}
			Collections.sort(usuariosReporte);
			for (Usuario usuarioActual : usuariosReporte)
			{
				Solicitud solicitudActual = usuarioActual.getSolicitud();
				Visa visaActual = solicitudActual.getVisaSolicitud();
				String tipo = visaActual.getClass().getSimpleName();
				String impresion = String.format("%1$-13s %2$-16s %3$-11s %4$-11s", usuarioActual.getNumPasaporte(),usuarioActual.getNombre(),tipo,solicitudActual.getCodigo());
				pw.println(impresion);
			}
		}
		else
		{
			pw.println("Para la fecha seleccionada, no existen citas agendadas");
		}
		pw.close();
		return usuariosReporte;
	}

	/**
	 * Método encargado de generar un reporte de los usuarios que son beneficiados con descuentos
	 * al momento de pagar su solicitud de visa, este método solo genera y escribe el archivo que 
	 * contiene esta información.
	 * 
	 * @param lineasArchivo Es un String que contiene la linea de texto con toda la información 
	 * de cada usuario ya lista para ser escrita en el archivo.
	 * @throws Exception Lanza una excepción si existe problemas con el archivo.
	 */
	public static void generarReporteBeneficiados(List<String> lineasArchivo) throws Exception
	{
		File archivo = new File("./data/beneficiarios.txt");
		PrintWriter pw = new PrintWriter(archivo);
		for (String string : lineasArchivo)
		{
			pw.println(string);
		}
		pw.close();
	}
	
	/**
	 * Método encargado de guardar y serializar la información del sistema en un archivo binario
	 * @param objeto con la información del sistema
	 * @param direccion con la direccion donde se guardara el archivo serializado.
	 * @throws Exception Lanza una excepción si el archivo no existe o no se pudo escribir
	 */
	public static void serializarSistema(ISistemaCitaEmbajada objeto, String direccion) throws Exception
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(direccion));
		oos.writeObject(objeto);
		oos.close();
	}
	
	/**
	 * Método encargado de leer un archivo binario con la información de otro posible sistema
	 * para ser trabajada en este.
	 * @param archivo con la dirección del archivo previamente serializado a cargar al sistema.
	 * @throws Exception Lanza una excepción si el archivo no existe o no se pudo leer
	 * @return Returna el SistemaCitasEmbajada con todos los datos cargados
	 */
	public static ISistemaCitaEmbajada leerSistema(String archivo) throws Exception
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
		ISistemaCitaEmbajada sistema = (ISistemaCitaEmbajada) ois.readObject();
		ois.close();
		return sistema;
	} 
}
