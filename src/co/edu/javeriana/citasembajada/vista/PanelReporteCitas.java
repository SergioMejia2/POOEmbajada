package co.edu.javeriana.citasembajada.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTextField;

import co.edu.javeriana.citasembajada.modelo.Solicitud;
import co.edu.javeriana.citasembajada.modelo.Usuario;
import co.edu.javeriana.citasembajada.modelo.Visa;

import javax.swing.JButton;
import java.awt.Color;

/**
 * <p>
 * Clase PanelReporteCitas que contiene el panel de la interfaz grafica que muestra la sección
 * para mostrar el reporte de citas que ha asignado la embajada a una fecha determinada.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */

public class PanelReporteCitas extends JPanel implements ActionListener
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
	 * necesitar, incluyendo la tabla en la que se mostrarán los usuarios que tienen una
	 * cita asignada a esa fecha.
	 */
	private JTextField txtFechaReporte;
	private JTable tableUsuarios;
	private JScrollPane jScrollPane;
	private Vector <String> dataColumnas;
	@SuppressWarnings("rawtypes")
	private Vector rowData;
	private JButton btnGenerarReporte;
	private JLabel lblReporteDeCitas;
	
	/**
	 * Constructor del panel. Aquí se crea el layout donde se crearán y ubicarán los botones,
	 * los label y todo lo necesario para el funcionamiento de la ventana.
	 * También se define la ubicación de cada uno, su color si lo posee. Además, se inicializan 
	 * los valores de las columnas de la tabla que se usarán para mostrar los usuarios que se 
	 * tienen una cita asiganada a la fecha ingresada.
	 */
	@SuppressWarnings("rawtypes")
	public PanelReporteCitas(InterfazGrafica pVentana)
	{
		ventana = pVentana;
		setLayout(new BorderLayout(0, 0));
		
		JPanel auxiliarNorte = new JPanel();
		auxiliarNorte.setBackground(new Color(255, 255, 255));
		add(auxiliarNorte, BorderLayout.NORTH);
		auxiliarNorte.setLayout(new GridLayout(2, 0, 0, 0));
		
		lblReporteDeCitas = new JLabel("Reporte de Solicitudes Embajada de ");
		lblReporteDeCitas.setBackground(new Color(255, 255, 255));
		auxiliarNorte.add(lblReporteDeCitas);
		
		JPanel auxiliarDatos = new JPanel();
		auxiliarDatos.setBackground(new Color(255, 255, 255));
		auxiliarNorte.add(auxiliarDatos);
		auxiliarDatos.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblFecha = new JLabel("Fecha: (AAAA-MM-DD)");
		lblFecha.setBackground(new Color(255, 255, 255));
		auxiliarDatos.add(lblFecha);
		
		txtFechaReporte = new JTextField();
		auxiliarDatos.add(txtFechaReporte);
		txtFechaReporte.setColumns(10);
		
		btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.setForeground(new Color(255, 255, 255));
		btnGenerarReporte.setBackground(new Color(128, 0, 0));
		btnGenerarReporte.addActionListener(this);
		auxiliarDatos.add(btnGenerarReporte);
		
		dataColumnas = new Vector<String>();
		dataColumnas.add("num Pass");
		dataColumnas.add("Nombre");
		dataColumnas.add("Tipo Visa");
		dataColumnas.add("Num Solicitud");;
		rowData = new Vector();
		
		tableUsuarios = new JTable(rowData, dataColumnas);
		jScrollPane = new JScrollPane(tableUsuarios);
		jScrollPane.setViewportView(tableUsuarios);
		tableUsuarios.setColumnSelectionAllowed(false);
	    tableUsuarios.setRowSelectionAllowed(true);
		add(jScrollPane,BorderLayout.CENTER);
		

	}
	
	/**
	 * Método encargado de ejecutar uan acción o proceso especifico luego de oprimir algun boton.
	 * @param accion con la  acción que se acaba de realizar
	 */
	public void actionPerformed(ActionEvent e)
	{
		ventana.generarReporteCitas();
		
	}
	
	/**
	 * Método encargado de asignar un valor al atributo del nombre del pais de la embajada cuando se genera el reporte
	 * @param paisEmbajada con el nombre del pais de la embajada
	 */
	public void setNombrePais(String paisEmbajada)
	{
		lblReporteDeCitas.setText("Reporte de Solicitudes Embajada de "+paisEmbajada);
		
	}
	
	/**
	 * Métodoe encargado de dar la fecha que se ingreso para generar el reporte
	 * @return String con la fecha que se ingreso para generar el reporte
	 */
	public String getFechaReporte()
	{
		return txtFechaReporte.getText();
	}
	
	/**
	 * Método encargado de actualizar la tabla para mostrar el reporte de los usuarios
	 * que tienen una cita asignada para la fecha ingresada.
	 * @param usuariosCitas con los usuarios que tienen la cita asignada a esa fecha.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void actualizar(List<Usuario> usuariosCitas)
	{
		rowData = new Vector();
		for (Usuario usuarioActual : usuariosCitas)
		{
			Vector<String> auxVector = new Vector<String>();
			auxVector.add(usuarioActual.getNumPasaporte());
			auxVector.add(usuarioActual.getNombre());
			Solicitud solicitudActual = usuarioActual.getSolicitud();
			Visa visaActual = solicitudActual.getVisaSolicitud();
			String tipo = visaActual.getClass().getSimpleName();
			auxVector.add(tipo);
			auxVector.add(""+solicitudActual.getCodigo());
			rowData.add(auxVector);
		}
		tableUsuarios = new JTable(rowData, dataColumnas);
		jScrollPane.setViewportView(tableUsuarios);
	}
}
