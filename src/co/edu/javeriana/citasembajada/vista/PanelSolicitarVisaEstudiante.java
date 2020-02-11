package co.edu.javeriana.citasembajada.vista;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import co.edu.javeriana.citasembajada.modelo.Niño0_2;
import co.edu.javeriana.citasembajada.modelo.Niño2_12;
import co.edu.javeriana.citasembajada.modelo.Usuario;
import java.awt.Font;
import java.awt.Color;

/**
 * <p>
 * Clase PanelSolicitarVisaEstudiante que contiene el panel de la interfaz grafica que muestra la sección
 * para pedir una solicitud de Visa de Estudiante
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */
public class PanelSolicitarVisaEstudiante extends JPanel implements ActionListener
{

	/**
	 * Número de la versión serial de la implementación Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos estáticos que se le asignan a cada botón para ser usados en el actionPerformaed
	 */
	private static final String BUSCAR_ARCHIVO = "Buscar Archivo";
	private static final String SELECCIONAR = "Seleccionar Usuario";
	
	/**
	 * Relación con la ventana de la interfaz grafica.
	 */
	private InterfazGrafica ventana;
	
	/**
	 * Atributos del panel para poder prestar todos los servicios que el usuario pueda 
	 * necesitar, incluyendo la tabla en la que se mostrarán los usuarios para que el usuario
	 * seleccione el que va a estar asociado a la solicitud
	 */
	private JTextField txtInstitucion;
	private JTextField txtEscolaridad;
	private JButton btnAsociarUsuario;
	private JLabel lblSolicitudDeVisa;
	private JLabel lblInstitucion;
	private JButton btnBuscarArchivo;
	private JLabel lblNoSolicitud;
	private JLabel lblSolicitud;
	
	private JFileChooser seleccionArchivo;
	private File archivo;
	private JTable tableUsuarios;
	
	private JScrollPane jScrollPane;
	
	private Vector <String> dataColumnas;
	private Vector rowData;
	
	private boolean usuarioYaSeleccionado;
	
	/**
	 * Constructor del panel. Aquí se crea el layout donde se crearán y ubicarán los botones,
	 * los label y todo lo necesario para el funcionamiento de la ventana.
	 * También se define la ubicación de cada uno, su color si lo posee. Además, se inicializan 
	 * los valores de las columnas de la tabla que se usarán para mostrar los usuarios existentes
	 * en la embajada.
	 */
	public PanelSolicitarVisaEstudiante(InterfazGrafica pVentana)
	{
		setBackground(new Color(255, 255, 255));
		ventana = pVentana;
		setLayout(new BorderLayout(0, 0));

		btnAsociarUsuario = new JButton("Asociar Usuario");
		btnAsociarUsuario.setBackground(new Color(128, 0, 0));
		btnAsociarUsuario.setForeground(new Color(255, 255, 255));
		btnAsociarUsuario.addActionListener(this);
		btnAsociarUsuario.setActionCommand(SELECCIONAR);
		add(btnAsociarUsuario, BorderLayout.SOUTH);
		
		JPanel auxiliarNorte = new JPanel();
		auxiliarNorte.setBackground(new Color(255, 255, 255));
		add(auxiliarNorte, BorderLayout.NORTH);
		auxiliarNorte.setLayout(new GridLayout(4, 1, 0, 0));
		
		lblSolicitudDeVisa = new JLabel("Hacer Solicitud de Visa de Estudiante");
		lblSolicitudDeVisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSolicitudDeVisa.setHorizontalAlignment(SwingConstants.CENTER);
		auxiliarNorte.add(lblSolicitudDeVisa);
		
		JPanel auxiliarDatos = new JPanel();
		auxiliarDatos.setBackground(new Color(255, 255, 255));
		auxiliarNorte.add(auxiliarDatos);
		auxiliarDatos.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblInstitucion = new JLabel("Instituci\u00F3n");
		lblInstitucion.setHorizontalAlignment(SwingConstants.RIGHT);
		auxiliarDatos.add(lblInstitucion);
		
		txtInstitucion = new JTextField();
		auxiliarDatos.add(txtInstitucion);
		txtInstitucion.setColumns(10);
		
		JLabel lblEscolaridad = new JLabel("Escolaridad");
		lblEscolaridad.setHorizontalAlignment(SwingConstants.RIGHT);
		auxiliarDatos.add(lblEscolaridad);
		
		txtEscolaridad = new JTextField();
		auxiliarDatos.add(txtEscolaridad);
		txtEscolaridad.setColumns(10);
		
		btnBuscarArchivo = new JButton("Cargar Archivo de Tarifas");
		btnBuscarArchivo.setForeground(new Color(255, 255, 255));
		btnBuscarArchivo.setBackground(new Color(128, 0, 0));
		btnBuscarArchivo.addActionListener(this);
		btnBuscarArchivo.setActionCommand(BUSCAR_ARCHIVO);
		auxiliarNorte.add(btnBuscarArchivo);
		
		JPanel auxiliarSolicitud = new JPanel();
		auxiliarSolicitud.setBackground(new Color(255, 255, 255));
		auxiliarNorte.add(auxiliarSolicitud);
		auxiliarSolicitud.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblNoSolicitud = new JLabel("No Solicitud: ");
		lblNoSolicitud.setHorizontalAlignment(SwingConstants.RIGHT);
		auxiliarSolicitud.add(lblNoSolicitud);
		
		lblSolicitud = new JLabel("");
		auxiliarSolicitud.add(lblSolicitud);
	
		dataColumnas = new Vector<String>();
		dataColumnas.add("num Pass");
		dataColumnas.add("Nombre");
		dataColumnas.add("Pais Origen");
		dataColumnas.add("Ciudad Nacimiento");
		dataColumnas.add("Fecha Nacimiento");
		dataColumnas.add("email");
		dataColumnas.add("Info Ad.");
		rowData = new Vector();
		
		seleccionArchivo = new JFileChooser();
		seleccionArchivo.setCurrentDirectory(new java.io.File("."));
		
		tableUsuarios = new JTable(rowData, dataColumnas);
		jScrollPane = new JScrollPane(tableUsuarios);
		jScrollPane.setViewportView(tableUsuarios);
		tableUsuarios.setColumnSelectionAllowed(false);
	    tableUsuarios.setRowSelectionAllowed(true);
		add(jScrollPane,BorderLayout.CENTER);
		
		
	}
	
	/**
	 * Método encargado de ejecutar uan acción o proceso especifico luego de oprimir algún botón.
	 * @param accion con la  acción que se acaba de realizar
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String accion = e.getActionCommand();
		if(accion.equals(BUSCAR_ARCHIVO))
		{
			int returnVal = seleccionArchivo.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				usuarioYaSeleccionado = false;
				archivo = seleccionArchivo.getSelectedFile();
				ventana.realizarSolicitudEstudiante();
			}
		}
		else if(accion.equals(SELECCIONAR))
		{
			if(!usuarioYaSeleccionado)
			{
				usuarioYaSeleccionado = ventana.agregarUsuario();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Solo puede ingresar un usuario");
			}
		}
		
	}
	
	/**
	 * Método encargado de dar la dirección del archivo donde se encuentran las tarifas de
	 * las visas que maneja el sistema
	 * @return String con la dirección del archivo.
	 */
	public String getArchivo()
	{
		return archivo.getAbsolutePath();	
	}
	
	/**
	 * Método encargado de retornar el número de pasaporte del usuario seleccionado en la tabla.
	 * @return String con el pasaporte seleccionado
	 */
	public String getPasaporteSeleccionado()
	{
		int row = tableUsuarios.getSelectedRow();
		String pasaporte = ((Vector)rowData.elementAt(row)).elementAt(0).toString();
		System.out.println(pasaporte);
		return pasaporte;
	}
	
	/**
	 * Método que actualiza la tabla de usuarios con la lista de usuarios del modelo. También
	 * actualiza el número de la solicitud.
	 * @param codigo int con el número de la solicitud
	 * @param usuarios Lista de Usuario con los usuarios a mostrar en la tabla
	 */
	public void actualizar(int codigo, List<Usuario> usuarios)
	{
		lblSolicitud.setText(""+codigo);
		
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

		//tableUsuarios.setVisible(true);
		//add(tableUsuarios, BorderLayout.CENTER);
		
	}

	/**
	 * Método que retorna el número de solicitud asignado
	 * @return int con el número de solicitud
	 */
	public int getNumSolicitud()
	{
		int noSolicitud = Integer.parseInt(lblSolicitud.getText());
		return noSolicitud;
	}

	/**
	 * Método que retorna el nombre de la institución digitado por el usuario
	 * @return String con el nombre de la institución
	 */
	public String getInstitucion()
	{
		return txtInstitucion.getText();
		
	}

	/**
	 * Método que retorna la escolaridad digitada por el usuario
	 * @return String con la escolaridad
	 */
	public String getEscolaridad()
	{
		return txtEscolaridad.getText();
		
	}
}