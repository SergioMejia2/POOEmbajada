package co.edu.javeriana.citasembajada.vista;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Color;

/**
 * <p>
 * Clase PanelMenuServicios que contiene el panel de la interfaz grafica que muestra todos
 * los servicios que ofrece el sistema de citas de la embajada.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */

public class PanelMenuServicios extends JPanel implements ActionListener
{

	/**
	 * Atributos estáticos que se le asignan a cada botón para ser usados en el actionPerformed
	 */
	private static final long serialVersionUID = 1L;
	public static final String ASOCIAR_PAIS = "Asociar Pais";
	public static final String CALCULAR_VALOR = "Calcular Valor";
	public static final String INGRESAR_SOLICITANTES = "Ingresar Solicitantes";
	public static final String LISTAR_BENEFICIARIOS = "Listar Beneficiarios";
	public static final String REPORTE_CITAS = "Reporte Citas";
	public static final String SOLICITAR_VISA_TRABAJO = "Solicitar Visa Trabajo";
	public static final String SOLICITAR_VISA_TURISMO = "Solicitar Visa Turismo";
	public static final String SOLICITAR_VISA_ESTUDIANTE = "Solicitar Visa Estudiante";
	public static final String GUARDAR_SISTEMA = "Guardar Sistema";
	public static final String CARGAR_SISTEMA = "Cargar Sistema";
	public static final String INFO_EMBAJADA = "Info Embajada";
	
	/**
	 * Relación con la ventana de la interfaz grafica.
	 */
	private InterfazGrafica ventana;
	
	
	
	/**
	 * Atributos del panel para poder prestar todos los servicios que el usuario pueda 
	 * necesitar, incluyendo los atributos que necesitara la ventana para ejecutar ciertos servicios
	 * como el de los archivos.
	 */
	private JButton btnAsociarPaisEmbajada;
	private JButton btnCalcularValorVisa;
	private JButton btnIngresarSolicitantes;
	private JButton btnListaBeneficiarios;
	private JButton btnReporteCitas;
	private JButton btnSolicitarVisaTrabajo;
	private JButton btnSolicitarVisaTurismo;
	private JButton btnSolicitarVisaEstudiante;
	private JLabel titulo;
	private JButton btnGuardarSistema;
	private JButton btnCargarSistema;
	private JButton btnInfoEmbajada;
	
	private JFileChooser seleccionArchivo;
	private File archivo;
	private String ruta;
	
	/**
	 * Contructor del panel, aqui se crea el layout donde se ubicaran los botones,
	 * se crean los botonoes, los label y todo lo necesario para el funcionamiento de la ventana
	 * tambien se define la ubicacion de cada uno, su color si lo posee, y un ActionComand que
	 * nos ayudara a ejecutar otra accion cuando sea oprimido algun boton.
	 * @param pVentana con la interfaz grafica principal.
	 */
	public PanelMenuServicios(InterfazGrafica pVentana)
	{
		setBackground(new Color(255, 255, 255));
		ventana = pVentana;
		
		btnAsociarPaisEmbajada = new JButton("Asociar País a Embajada");
		btnAsociarPaisEmbajada.setBounds(85, 11, 192, 47);
		btnAsociarPaisEmbajada.addActionListener(this);
		btnAsociarPaisEmbajada.setActionCommand(ASOCIAR_PAIS);
		
		btnCalcularValorVisa = new JButton("Calcular Valor de Visa");
		btnCalcularValorVisa.setBounds(583, 174, 192, 47);
		btnCalcularValorVisa.addActionListener(this);
		btnCalcularValorVisa.setActionCommand(CALCULAR_VALOR);
		
		btnIngresarSolicitantes = new JButton("Ingresar solicitantes");
		btnIngresarSolicitantes.setBounds(583, 11, 192, 47);
		btnIngresarSolicitantes.addActionListener(this);
		btnIngresarSolicitantes.setActionCommand(INGRESAR_SOLICITANTES);
		
		btnListaBeneficiarios = new JButton("Mostrar Reporte de Beneficiarios");
		btnListaBeneficiarios.setBounds(583, 116, 192, 47);
		btnListaBeneficiarios.addActionListener(this);
		btnListaBeneficiarios.setActionCommand(LISTAR_BENEFICIARIOS);
		
		btnReporteCitas = new JButton("Mostrar reporte de citas");
		btnReporteCitas.setBounds(583, 64, 192, 47);
		btnReporteCitas.addActionListener(this);
		btnReporteCitas.setActionCommand(REPORTE_CITAS);
		
		btnSolicitarVisaTrabajo = new JButton("Solicitar Visa Trabajo");
		btnSolicitarVisaTrabajo.setBounds(85, 116, 192, 47);
		btnSolicitarVisaTrabajo.addActionListener(this);
		btnSolicitarVisaTrabajo.setActionCommand(SOLICITAR_VISA_TRABAJO);
		
		btnSolicitarVisaTurismo = new JButton("Solicitar Visa Turismo");
		btnSolicitarVisaTurismo.setBounds(85, 64, 192, 47);
		btnSolicitarVisaTurismo.addActionListener(this);
		btnSolicitarVisaTurismo.setActionCommand(SOLICITAR_VISA_TURISMO);
		
		btnSolicitarVisaEstudiante = new JButton("Solicitar Visa Estudiante");
		btnSolicitarVisaEstudiante.setBounds(85, 174, 192, 47);
		btnSolicitarVisaTurismo.addActionListener(this);
		btnSolicitarVisaTurismo.setActionCommand(SOLICITAR_VISA_ESTUDIANTE);
		
		titulo = new JLabel("Servicios del sistema");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		setLayout(new BorderLayout(0, 0));
		
		add(titulo,BorderLayout.NORTH);
		
		JPanel auxiliarCentro = new JPanel();
		auxiliarCentro.setBackground(new Color(255, 255, 255));
		add(auxiliarCentro, BorderLayout.CENTER);
		auxiliarCentro.setLayout(null);
		auxiliarCentro.add(btnAsociarPaisEmbajada);
		auxiliarCentro.add(btnCalcularValorVisa);
		auxiliarCentro.add(btnIngresarSolicitantes);
		auxiliarCentro.add(btnReporteCitas);
		auxiliarCentro.add(btnSolicitarVisaTurismo);
		auxiliarCentro.add(btnListaBeneficiarios);
		auxiliarCentro.add(btnSolicitarVisaTrabajo);
		auxiliarCentro.add(btnSolicitarVisaEstudiante);
		
		btnGuardarSistema = new JButton("Salvar los datos del sistema");
		btnGuardarSistema.setBounds(270, 254, 316, 23);
		btnGuardarSistema.addActionListener(this);
		btnGuardarSistema.setActionCommand(GUARDAR_SISTEMA);
		
		auxiliarCentro.add(btnGuardarSistema);
		btnCargarSistema = new JButton("Cargar los datos del sistema");
		btnCargarSistema.setBounds(270, 232, 316, 23);
		btnCargarSistema.addActionListener(this);
		btnCargarSistema.setActionCommand(CARGAR_SISTEMA);
		
		seleccionArchivo = new JFileChooser();
		seleccionArchivo.setCurrentDirectory(new java.io.File("."));
		
		auxiliarCentro.add(btnCargarSistema);
		
		btnInfoEmbajada = new JButton("Informacion de la Embajada");
		btnInfoEmbajada.setBounds(329, 92, 196, 47);
		btnInfoEmbajada.addActionListener(this);
		btnInfoEmbajada.setActionCommand(INFO_EMBAJADA);
		auxiliarCentro.add(btnInfoEmbajada);
	}
	
	/**
	 * Método encargado de ejecutar uan acción o proceso especifico luego de oprimir algun boton.
	 * Se tiene en cuenta el atributo estatico asignado a cada boton.
	 * @param accion con la  acción que se acaba de realizar
	 */
	public void actionPerformed(ActionEvent accion)
	{
		String comando = accion.getActionCommand();
		
		if(comando.equals(CARGAR_SISTEMA))
		{
			int returnVal = seleccionArchivo.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				archivo = seleccionArchivo.getSelectedFile();
				ventana.cargarSerializacion();
			}
		}
		else if(comando.equals(GUARDAR_SISTEMA))
		{
			int returnVal = seleccionArchivo.showSaveDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				ruta = seleccionArchivo.getSelectedFile().getAbsolutePath();
				ventana.serializarSistema();
			}
		}
		else if(comando.equals(INFO_EMBAJADA))
		{
			ventana.mostrarInfoEmbajada();
		}
		else
		{
			ventana.cambiarPanel(comando);
		}
	}
	
	/**
	 * Método encargado de dar la dirección del  archivo que se cargara cuando
	 * se seleccione leer sistema.
	 * @return String con la direccion completa del archivo a cargar
	 */
	public String getArchivoACargar()
	{
			return archivo.getAbsolutePath();	
	}
	
	/**
	 * Método encargado de dar la dirección donde se guardara el archivo 
	 * en el que se serializara el sistema.
	 * @return String con la dirección donde se guardara el archivo.
	 */
	public String getDireccionAGuardar()
	{
		return ruta;
	}
}
