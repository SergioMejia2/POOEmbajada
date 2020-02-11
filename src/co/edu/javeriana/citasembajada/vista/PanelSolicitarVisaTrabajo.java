package co.edu.javeriana.citasembajada.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import co.edu.javeriana.citasembajada.modelo.Ni�o0_2;
import co.edu.javeriana.citasembajada.modelo.Ni�o2_12;
import co.edu.javeriana.citasembajada.modelo.Usuario;
import java.awt.Font;
import java.awt.Color;

/**
 * <p>
 * Clase PanelSolicitarVisaTrabajo que contiene el panel de la interfaz grafica que muestra la 
 * secci�n para pedir una solicitud de Visa de Trabajo
 * 
 * @author Sergio Andr�s Mej�a Tovar
 * @author Juli�n David Parada Galvis
 */
public class PanelSolicitarVisaTrabajo extends JPanel implements ActionListener
{

	/**
	 * N�mero de la versi�n serial de la implementaci�n Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos est�ticos que se le asignan a cada bot�n para ser usados en el actionPerformaed
	 */
	private static final String BUSCAR_ARCHIVO = "Buscar Archivo";
	private static final String SELECCIONAR = "Seleccionar Usuario";
	
	/**
	 * Relaci�n con la ventana de la interfaz grafica.
	 */
	private InterfazGrafica ventana;
	
	/**
	 * Atributos del panel para poder prestar todos los servicios que el usuario pueda 
	 * necesitar, incluyendo la tabla en la que se mostrar�n los usuarios para que el usuario
	 * seleccione el que va a estar asociado a la solicitud
	 */
	private JTextField txtEmpresa;
	private JTextField txtCargo;
	private JButton btnAsociarUsuario;
	private JLabel lblSolicitudDeVisa;
	private JLabel lblEmpresa;
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
	 * Constructor del panel. Aqu� se crea el layout donde se crear�n y ubicar�n los botones,
	 * los label y todo lo necesario para el funcionamiento de la ventana.
	 * Tambi�n se define la ubicaci�n de cada uno, su color si lo posee. Adem�s, se inicializan 
	 * los valores de las columnas de la tabla que se usar�n para mostrar los usuarios existentes
	 * en la embajada.
	 */
	public PanelSolicitarVisaTrabajo(InterfazGrafica pVentana)
	{
		setBackground(new Color(255, 255, 255));
		ventana = pVentana;
		setLayout(new BorderLayout(0, 0));

		btnAsociarUsuario = new JButton("Asociar Usuario");
		btnAsociarUsuario.setForeground(new Color(255, 255, 255));
		btnAsociarUsuario.setBackground(new Color(128, 0, 0));
		btnAsociarUsuario.addActionListener(this);
		btnAsociarUsuario.setActionCommand(SELECCIONAR);
		add(btnAsociarUsuario, BorderLayout.SOUTH);
		
		JPanel auxiliarNorte = new JPanel();
		auxiliarNorte.setBackground(new Color(255, 255, 255));
		add(auxiliarNorte, BorderLayout.NORTH);
		auxiliarNorte.setLayout(new GridLayout(4, 1, 0, 0));
		
		lblSolicitudDeVisa = new JLabel("Hacer Solicitud de Visa de Trabajo");
		lblSolicitudDeVisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSolicitudDeVisa.setHorizontalAlignment(SwingConstants.CENTER);
		auxiliarNorte.add(lblSolicitudDeVisa);
		
		JPanel auxiliarDatos = new JPanel();
		auxiliarDatos.setBackground(new Color(255, 255, 255));
		auxiliarNorte.add(auxiliarDatos);
		auxiliarDatos.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		auxiliarDatos.add(lblEmpresa);
		
		txtEmpresa = new JTextField();
		auxiliarDatos.add(txtEmpresa);
		txtEmpresa.setColumns(10);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setHorizontalAlignment(SwingConstants.RIGHT);
		auxiliarDatos.add(lblCargo);
		
		txtCargo = new JTextField();
		auxiliarDatos.add(txtCargo);
		txtCargo.setColumns(10);
		
		btnBuscarArchivo = new JButton("Cargar Archivo de Tarifas");
		btnBuscarArchivo.setBackground(new Color(128, 0, 0));
		btnBuscarArchivo.setForeground(new Color(255, 255, 255));
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
	 * M�todo encargado de ejecutar uan acci�n o proceso especifico luego de oprimir alg�n bot�n.
	 * @param accion con la  acci�n que se acaba de realizar
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
				ventana.realizarSolicitudTrabajo();
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
	 * M�todo encargado de dar la direcci�n del archivo donde se encuentran las tarifas de
	 * las visas que maneja el sistema
	 * @return String con la direcci�n del archivo.
	 */
	public String getArchivo()
	{
		return archivo.getAbsolutePath();	
	}

	/**
	 * M�todo encargado de retornar el n�mero de pasaporte del usuario seleccionado en la tabla.
	 * @return String con el pasaporte seleccionado
	 */
	public String getPasaporteSeleccionado()
	{
		int row = tableUsuarios.getSelectedRow();
		String pasaporte = ((Vector)rowData.elementAt(row)).elementAt(0).toString();
		return pasaporte;
	}

	/**
	 * M�todo que actualiza la tabla de usuarios con la lista de usuarios del modelo. Tambi�n
	 * actualiza el n�mero de la solicitud.
	 * @param codigo int con el n�mero de la solicitud
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
			if(usuarioActual instanceof Ni�o0_2)
			{
				auxVector.add(((Ni�o0_2) usuarioActual).getAcudiente());
			}
			else if(usuarioActual instanceof Ni�o2_12)
			{
				auxVector.add(((Ni�o2_12) usuarioActual).getEscolaridad());
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
	 * M�todo que retorna el n�mero de solicitud asignado
	 * @return int con el n�mero de solicitud
	 */
	public int getNumSolicitud()
	{
		int noSolicitud = Integer.parseInt(lblSolicitud.getText());
		return noSolicitud;
	}

	/**
	 * M�todo que retorna el nombre de la empresa digitado por el usuario
	 * @return String con el nombre de la empresa
	 */
	public String getEmpresa()
	{
		return txtEmpresa.getText();
		
	}
	
	/**
	 * M�todo que retorna el cargo digitado por el usuario
	 * @return String con el cargo
	 */
	public String getCargo()
	{
		return txtCargo.getText();
		
	}

}
