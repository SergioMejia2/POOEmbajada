package co.edu.javeriana.citasembajada.vista;

import java.util.List;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

/**
 * <p>
 * Clase PanelAsociarPaisEmbajada que contiene el panel de la interfaz grafica que muestra la sección
 * para asociar algun pais a la embajada.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */

public class PanelAsociarPaisEmbajada extends JPanel implements ActionListener
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
	 * como el de los archivos.
	 */
	private JLabel titulo;
	private JLabel lblPais;
	private JComboBox<String> opcionesPais;
	private JButton btnBuscarArchivo;
	private JFileChooser seleccionArchivo;
	private File archivo;
	
	/**
	 * Contructor del panel, aqui se crea el layout donde se ubicaran los botones,
	 * se crean los botonoes, los label y todo lo necesario para el funcionamiento de la ventana
	 * tambien se define la ubicacion de cada uno, su color si lo posee, y un ActionComand que
	 * nos ayudara a ejecutar otra accion cuando sea oprimido algun boton.
	 * @param pVentana con la interfaz grafica principal.
	 */
	public PanelAsociarPaisEmbajada(List<String> paises, InterfazGrafica pVentana)
	{
		setBackground(new Color(255, 255, 255));
		ventana = pVentana;
		setLayout(new BorderLayout(0, 0));
		
		titulo = new JLabel("Asociar País a Embajada");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titulo.setBackground(new Color(255, 255, 255));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(titulo, BorderLayout.NORTH);
		
		JPanel auxiliarCentro = new JPanel();
		auxiliarCentro.setBackground(new Color(255, 255, 255));
		add(auxiliarCentro, BorderLayout.CENTER);
		auxiliarCentro.setLayout(null);
		
		lblPais = new JLabel("País");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setBounds(298, 113, 47, 36);
		auxiliarCentro.add(lblPais);
		
		opcionesPais = new JComboBox<String>();
		opcionesPais.setBounds(349, 118, 225, 26);
		for (String paisActual : paises)
		{
			opcionesPais.addItem(paisActual);
		}
		auxiliarCentro.add(opcionesPais);
		
		btnBuscarArchivo = new JButton("Buscar archivos de embajada");
		btnBuscarArchivo.setForeground(new Color(255, 255, 255));
		btnBuscarArchivo.setBackground(new Color(128, 0, 0));
		btnBuscarArchivo.setBounds(290, 155, 328, 41);
		auxiliarCentro.add(btnBuscarArchivo);
		btnBuscarArchivo.addActionListener(this);
		
		seleccionArchivo = new JFileChooser();
		seleccionArchivo.setCurrentDirectory(new java.io.File("."));
	}
	
	/**
	 * Método encargado de dar el pais que se selecciono en el
	 * JComboBox.
	 * @return String con el nombre del pais seleccionado.
	 */
	public String getPaisSeleccionado()
	{
		return opcionesPais.getSelectedItem().toString();
	}
	
	/**
	 * Método encargado de ejecutar uan acción o proceso especifico luego de oprimir algun boton
	 * @param accion con la  acción que se acaba de realizar
	 */
	public void actionPerformed(ActionEvent e)
	{
		int returnVal = seleccionArchivo.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			archivo = seleccionArchivo.getSelectedFile();
			ventana.asociarPaisEmbajada();
		}
	}
	
	/**
	 * Método encargado de dar la dirección del archivo donde se 
	 * encuentran la información de todos los paises que se podrian asociar a la embajada.
	 * @return
	 */
	public String getArchivo()
	{
		return archivo.getAbsolutePath();
	}

}
