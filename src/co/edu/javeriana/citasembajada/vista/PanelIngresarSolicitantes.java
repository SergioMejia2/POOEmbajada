package co.edu.javeriana.citasembajada.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;

import co.edu.javeriana.citasembajada.modelo.Niño0_2;
import co.edu.javeriana.citasembajada.modelo.Niño2_12;
import co.edu.javeriana.citasembajada.modelo.Usuario;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

/**
 * <p>
 * Clase PanelIngresarSolicitantes que contiene el panel de la interfaz grafica que muestra la sección
 * para cargar los usuarios que podran realizar solicitudes de visa en el sistema.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */

public class PanelIngresarSolicitantes extends JPanel implements ActionListener
{
	
	/**
	 * Número de la versión serial de la implementación Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Relación con la ventana de la interfaz grafica.
	 */
	private InterfazGrafica ventana;
	
	/**
	 * Atributos del panel para poder prestar todos los servicios que el usuario pueda 
	 * necesitar, incluyendo los atributos que necesitara la ventana para ejecutar ciertos servicios
	 * como el de los archivos y la tabla en la que se mostraran los usuarios agregados.
	 */
	private JLabel lblTitulo;
	private JButton btnBuscarArchivo;
	private JFileChooser seleccionArchivo;
	private File archivo;
	private JTable tableUsuarios;
	private JScrollPane jScrollPane;
	private Vector <String> dataColumnas;
	@SuppressWarnings("rawtypes")
	private Vector rowData;
	
	
	/**
	 * Contructor del panel, aqui se crea el layout donde se ubicaran los botones,
	 * se crean los botonoes, los label y todo lo necesario para el funcionamiento de la ventana
	 * tambien se define la ubicacion de cada uno, su color si lo posee. Ademas, se inicializan 
	 * los valores de las columnas de la tabla que se usaran para mostrar los usuarios que se agregaron al sistema.
	 */
	@SuppressWarnings("rawtypes")
	public PanelIngresarSolicitantes(InterfazGrafica pVentana)
	{
		setBackground(new Color(255, 255, 255));
		ventana = pVentana;
		setLayout(new BorderLayout(0, 0));
		JPanel auxiliarNorte = new JPanel();
		auxiliarNorte.setBackground(new Color(255, 255, 255));
		add(auxiliarNorte, BorderLayout.NORTH);
		auxiliarNorte.setLayout(new GridLayout(2,1));
		
		lblTitulo = new JLabel("Ingresar Solicitantes");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		auxiliarNorte.add(lblTitulo);
		
		btnBuscarArchivo = new JButton("Seleccionar archivo de usuarios");
		btnBuscarArchivo.setForeground(new Color(255, 255, 255));
		btnBuscarArchivo.setBackground(new Color(128, 0, 0));
		btnBuscarArchivo.addActionListener(this);
		auxiliarNorte.add(btnBuscarArchivo);
		
		seleccionArchivo = new JFileChooser();
		seleccionArchivo.setCurrentDirectory(new java.io.File("."));
		
		dataColumnas = new Vector<String>();
		dataColumnas.add("num Pass");
		dataColumnas.add("Nombre");
		dataColumnas.add("Pais Origen");
		dataColumnas.add("Ciudad Nacimiento");
		dataColumnas.add("Fecha Nacimiento");
		dataColumnas.add("email");
		dataColumnas.add("Info Ad.");
		rowData = new Vector();
		
		
		tableUsuarios = new JTable(rowData, dataColumnas);
		jScrollPane = new JScrollPane(tableUsuarios);
		jScrollPane.setViewportView(tableUsuarios);
		add(jScrollPane,BorderLayout.CENTER);
	}

	/**
	 * Método encargado de ejecutar uan acción o proceso especifico luego de oprimir algun boton.
	 * @param accion con la  acción que se acaba de realizar
	 */
	public void actionPerformed(ActionEvent arg0)
	{
		int returnVal = seleccionArchivo.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			archivo = seleccionArchivo.getSelectedFile();
			ventana.ingresarSolicitantes();
		}
		
	}
	
	/**
	 * Método encargado de dar la dirección del archivo donde se encuentran los usuarios a
	 * agregar al sistema
	 * @return String con la dirección del archivo.
	 */
	public String getArchivo()
	{
		return archivo.getAbsolutePath();
	}

	/**
	 * Método encargado de actualizar la tabla y mostrar los usuarios que se agregaron satisfactoriamente al sistema.
	 * @param usuarios con los usuarios que se agregaron correctamente en el sistema.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void actualizar(List<Usuario> usuarios)
	{
		Vector <String> dataColumnas = new Vector<String>();
		dataColumnas.add("num Pass");
		dataColumnas.add("Nombre");
		dataColumnas.add("Pais Origen");
		dataColumnas.add("Ciudad Nacimiento");
		dataColumnas.add("Fecha Nacimiento");
		dataColumnas.add("email");
		dataColumnas.add("Info Ad.");
		rowData = new Vector();
		for (Usuario usuarioActual : usuarios)
		{
			Vector<String> auxVector = new Vector<String>();
			auxVector.add(usuarioActual.getNumPasaporte());
			auxVector.add(usuarioActual.getNombre());
			auxVector.add(usuarioActual.getPaisNacimiento());
			auxVector.add(usuarioActual.getCiudadNacimiento());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String fechaNacimiento = usuarioActual.getFechaNacimiento().format(formatter);
			auxVector.add(fechaNacimiento);
			auxVector.add(usuarioActual.getEmail());
			if(usuarioActual instanceof Niño0_2)
			{
				auxVector.add(((Niño0_2) usuarioActual).getAcudiente());
			}
			else if(usuarioActual instanceof Niño2_12)
			{
				auxVector.add(((Niño2_12) usuarioActual).getEscolaridad());
			}
			else
			{
				auxVector.add("");
			}
			rowData.add(auxVector);
		}
		tableUsuarios = new JTable(rowData, dataColumnas);
		jScrollPane.setViewportView(tableUsuarios);
	}
}
