package co.edu.javeriana.citasembajada.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

/**
 * <p>
 * Clase PanelListarBeneficiarios que contiene el panel de la interfaz grafica que muestra la sección
 * para listar los que se benefician al momento de pagar una visa.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */

public class PanelListaBeneficiarios extends JPanel
{

	/**
	 * Número de la versión serial de la implementación Serializable para guardar este objeto.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Atributos del panel para poder prestar todos los servicios que el usuario pueda 
	 * necesitar, incluyendo la tablas que se usara para mostrar los beneficiaros del pago de la visa.
	 */
	private JLabel lblReporteBeneficiarios;
	private JLabel lblValorMensaje;
	private JLabel lblValor;
	private JTable tableUsuarios;
	private JScrollPane jScrollPane;
	private Vector <String> dataColumnas;
	@SuppressWarnings("rawtypes")
	private Vector rowData;
	
	/**
	 * Contructor del panel, aqui se crea el layout donde se ubicaran los botones,
	 * se crean los botonoes, los label y todo lo necesario para el funcionamiento de la ventana
	 * tambien se define la ubicacion de cada uno, su color si lo posee. Ademas, se inicializan 
	 * los valores de las columnas de la tabla que se usaran para mostrar el reporte de beneficiarios.
	 */
	@SuppressWarnings("rawtypes")
	public PanelListaBeneficiarios()
	{
		setBackground(new Color(255, 255, 255));
		
		
		setLayout(new BorderLayout(0, 0));
		
		lblReporteBeneficiarios = new JLabel("REPORTE DE BENEFICIARIOS");
		lblReporteBeneficiarios.setBackground(new Color(255, 255, 255));
		lblReporteBeneficiarios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReporteBeneficiarios.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblReporteBeneficiarios, BorderLayout.NORTH);
		
		JPanel auxiliarSur = new JPanel();
		auxiliarSur.setBackground(new Color(255, 255, 255));
		add(auxiliarSur, BorderLayout.SOUTH);
		auxiliarSur.setLayout(new GridLayout(1, 2, 0, 0));
		
		lblValorMensaje = new JLabel("Valor total que se dejo de recaudar: ");
		lblValorMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		auxiliarSur.add(lblValorMensaje);
		
		lblValor = new JLabel();
		auxiliarSur.add(lblValor);

		dataColumnas = new Vector<String>();
		dataColumnas.add("num Pass");
		dataColumnas.add("Nombre");
		dataColumnas.add("Valor Total");
		rowData = new Vector();
		
		tableUsuarios = new JTable(rowData, dataColumnas);
		jScrollPane = new JScrollPane(tableUsuarios);
		jScrollPane.setViewportView(tableUsuarios);
		tableUsuarios.setColumnSelectionAllowed(false);
	    tableUsuarios.setRowSelectionAllowed(true);
		add(jScrollPane,BorderLayout.CENTER);
		
	}
	
	/**
	 * Método encargado de mostrar los usuarios beneficiarios en la tabla del panel.
	 * @param valorSimbolo con el valor que dejo de ganar la embajada y el simbolo de la moneda.
	 * @param usuarios con la lista de usuarios beneficiados
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void mostrarUsuarios(String valorSimbolo, List<String> usuarios)
	{
		lblValor.setText(valorSimbolo);
		
		dataColumnas = new Vector<String>();
		dataColumnas.add("num Pass");
		dataColumnas.add("Nombre");
		dataColumnas.add("Valor Total");
		rowData = new Vector();
		for (String usuarioActual : usuarios)
		{

			Vector<String> auxVector = new Vector<String>();
			//usuarioActual.trim();
			String dato[] = usuarioActual.split("\\*");
			auxVector.add(dato[0].trim());
			auxVector.add(dato[1].trim());
			auxVector.add(dato[2].trim());
			rowData.add(auxVector);
		}
		tableUsuarios = new JTable(rowData, dataColumnas);
		jScrollPane.setViewportView(tableUsuarios);
		//tableUsuarios.setVisible(true);
		//add(tableUsuarios, BorderLayout.CENTER);
	}
}
