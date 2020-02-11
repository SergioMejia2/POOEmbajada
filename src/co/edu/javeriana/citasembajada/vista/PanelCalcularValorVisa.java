package co.edu.javeriana.citasembajada.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingConstants;

import co.edu.javeriana.citasembajada.modelo.Usuario;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;

/**
 * <p>
 * Clase PanelCalcularValorVisa que contiene el panel de la interfaz grafica que muestra la sección
 * para calcular el valor de la solicitud.
 * 
 * @author Sergio Andrés Mejía Tovar
 * @author Julián David Parada Galvis
 */

public class PanelCalcularValorVisa extends JPanel implements ActionListener
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
	 * como el de los archivos y las tablas que se usan para mostrar el resultado del calculo de la solicitud
	 * al usuario.
	 */
	private JTextField valorBusqueda;
	private JTable tableUsuarios;
	private JTable tableUsuariosValores;
	private JScrollPane jScrollPane;
	private JScrollPane jScrollPane2;
	
	private Vector <String> dataColumnas1;
	private Vector <String> dataColumnas2;
	
	@SuppressWarnings("rawtypes")
	private Vector rowData1;
	@SuppressWarnings("rawtypes")
	private Vector rowData2;
	
	private JLabel lblCalcularValor;
	private JLabel lblTipoDeBusqueda;
	private JComboBox<String> comboBox;
	private JLabel lblNumero;
	private JButton btnCalcularValorVisa;
	private JLabel lblValorCop;
	private JLabel lblMonedaLocal;
	private JLabel lblTasaCambio;
	private JLabel lblValorMonedaLocal;
	
	/**
	 * Contructor del panel, aqui se crea el layout donde se ubicaran los botones,
	 * se crean los botonoes, los label y todo lo necesario para el funcionamiento de la ventana
	 * tambien se define la ubicacion de cada uno, su color si lo posee, y un ActionComand que
	 * nos ayudara a ejecutar otra accion cuando sea oprimido algun boton. Ademas, se inicializan 
	 * los valores de las columnas de las tablas que se usaran para mostrar el resultado del calculo.
	 * @param pVentana con la interfaz grafica principal.
	 */
	@SuppressWarnings("rawtypes")
	public PanelCalcularValorVisa(InterfazGrafica pVentana)
	{
		ventana = pVentana;
		setLayout(new BorderLayout(0, 0));
		
		JPanel auxiliarNorte = new JPanel();
		auxiliarNorte.setBackground(new Color(255, 255, 255));
		add(auxiliarNorte, BorderLayout.NORTH);
		auxiliarNorte.setLayout(new GridLayout(3,1));
		
		lblCalcularValor = new JLabel("Calcular valor de Visa");
		lblCalcularValor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCalcularValor.setHorizontalAlignment(SwingConstants.CENTER);
		auxiliarNorte.add(lblCalcularValor);
		
		JPanel auxiliarBusqueda = new JPanel();
		auxiliarBusqueda.setBackground(new Color(255, 255, 255));
		auxiliarNorte.add(auxiliarBusqueda);
		auxiliarBusqueda.setLayout(new GridLayout(1,2));
		
		lblTipoDeBusqueda = new JLabel("Búsqueda por:");
		lblTipoDeBusqueda.setBackground(new Color(255, 255, 255));
		lblTipoDeBusqueda.setHorizontalAlignment(SwingConstants.RIGHT);
		auxiliarBusqueda.add(lblTipoDeBusqueda);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Número de Pasaporte");
		comboBox.addItem("Número de Solicitud");
		auxiliarBusqueda.add(comboBox);
		
		JPanel auxiliarCalculo = new JPanel();
		auxiliarCalculo.setBackground(new Color(255, 255, 255));
		auxiliarNorte.add(auxiliarCalculo);
		auxiliarCalculo.setLayout(new GridLayout(1,3));
		
		lblNumero = new JLabel("Número:");
		lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		auxiliarCalculo.add(lblNumero);
		
		valorBusqueda = new JTextField();
		auxiliarCalculo.add(valorBusqueda);
		valorBusqueda.setColumns(10);
		
		btnCalcularValorVisa = new JButton("Calcular Valor Visa");
		btnCalcularValorVisa.setForeground(new Color(255, 255, 255));
		btnCalcularValorVisa.setBackground(new Color(128, 0, 0));
		btnCalcularValorVisa.addActionListener(this);
		auxiliarCalculo.add(btnCalcularValorVisa);
		
		JPanel auxiliarCentro = new JPanel();
		add(auxiliarCentro, BorderLayout.CENTER);
		auxiliarCentro.setLayout(new GridLayout(2, 1));
		
		dataColumnas1 = new Vector<String>();
		dataColumnas1.add("num Pass");
		dataColumnas1.add("Nombre");
		dataColumnas1.add("Pais Origen");
		dataColumnas1.add("Ciudad Nacimiento");
		dataColumnas1.add("Fecha Nacimiento");
		dataColumnas1.add("email");
		rowData1 = new Vector();
		
		tableUsuarios = new JTable(rowData1, dataColumnas1);
		jScrollPane = new JScrollPane(tableUsuarios);
		jScrollPane.setViewportView(tableUsuarios);
		auxiliarCentro.add(jScrollPane);

		
		dataColumnas2 = new Vector<String>();
		dataColumnas2.add("num Pass");
		dataColumnas2.add("Nombre");
		dataColumnas2.add("Fecha Nacimiento");
		dataColumnas2.add("Valor Visa");
		dataColumnas2.add("Impuesto");
		dataColumnas2.add("Valor Total");
		
		rowData2 = new Vector();
		tableUsuariosValores = new JTable(rowData2, dataColumnas2);
		jScrollPane2 = new JScrollPane(tableUsuariosValores);
		jScrollPane2.setViewportView(tableUsuariosValores);
		auxiliarCentro.add(jScrollPane2);

		
		JPanel auxiliarSur = new JPanel();
		auxiliarSur.setBackground(new Color(255, 255, 255));
		add(auxiliarSur, BorderLayout.SOUTH);
		auxiliarSur.setLayout(new GridLayout(2, 2));

		lblValorCop = new JLabel("Valor COP:");
		auxiliarSur.add(lblValorCop);
		
		lblMonedaLocal = new JLabel("Moneda Local:");
		auxiliarSur.add(lblMonedaLocal);
		
		lblTasaCambio = new JLabel("Tasa de Cambio:");
		auxiliarSur.add(lblTasaCambio);
		
		lblValorMonedaLocal = new JLabel("Valor Moneda Local:");
		auxiliarSur.add(lblValorMonedaLocal);

	}
	
	/**
	 * Método encargado de ejecutar uan acción o proceso especifico luego de oprimir algun boton.
	 * @param accion con la  acción que se acaba de realizar
	 */
	public void actionPerformed(ActionEvent arg0)
	{
		if(comboBox.getSelectedIndex() == 0)
		{
			ventana.calcularValorVisa("NP");
		}
		else
			ventana.calcularValorVisa("CP");
	}
	
	/**
	 * Método encargado de dar el atibuto que posee el número de pasaporte o
	 * el número de la solicitud por la que se realizara el calculo del  valor de la visa.
	 * @return Int con el numero
	 */
	public int getNumero()
	{
		String seleccion = valorBusqueda.getText();
		int numero = Integer.parseInt(seleccion);
		return numero;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	/**
	 * Método encargado de actualizar las tablas donde se mostrara los resultados del calculo de 
	 * la visa y los valores a pagar.
	 * @param usuarios Con los usuarios que se encuentren asociados en la soliciud.
	 * @param listValorVisa con el valor de los valores de la visa de cada usuario 
	 * ordenado paralelamente con la lista de usuarios.
	 * @param listImpuesto con el valor de los valores de impuesto la visa de cada usuario 
	 * ordenado paralelamente con la lista de usuarios.
	 * @param listValorTotal con el valor total a pagar de la visa de cada usuario 
	 * ordenado paralelamente con la lista de usuarios.
	 * @param pagoMonedaPesos  valor que se debe pagar por la solicitud en pesos colombianos.
	 * @param simbolo con el simbolo de la moneda del pais que se este trabajando en la embajada.
	 * @param cambioOficial el valor del cambio oficial que posee la embajada.
	 * @param totalSolicitud con el valor a pagar en moneda local por la visa.
	 */
	public void actualizar(List<Usuario> usuarios, List<Float> listValorVisa, List<Float> listImpuesto,
			List<Float> listValorTotal, float pagoMonedaPesos, String simbolo, double cambioOficial,
			float totalSolicitud)
	{
		lblValorCop.setText("Valor COP: "+pagoMonedaPesos);
		lblMonedaLocal.setText("Moneda Local: "+simbolo);
		lblTasaCambio.setText("Tasa de Cambio: "+cambioOficial);	
		lblValorMonedaLocal.setText("Valor Moneda Local: "+totalSolicitud);
		
		rowData1 = new Vector();
		for (int i = 0; i < usuarios.size(); i++)
		{
			Usuario usuarioActual = usuarios.get(i);
			Vector<String> auxVector = new Vector<String>();
			auxVector.add(usuarioActual.getNumPasaporte());
			auxVector.add(usuarioActual.getNombre());
			auxVector.add(usuarioActual.getPaisNacimiento());
			auxVector.add(usuarioActual.getCiudadNacimiento());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String fechaNacimiento = usuarioActual.getFechaNacimiento().format(formatter);
			auxVector.add(fechaNacimiento);
			auxVector.add(usuarioActual.getEmail());
			
			
			rowData1.add(auxVector);
		}
		tableUsuarios = new JTable(rowData1, dataColumnas1);
		jScrollPane.setViewportView(tableUsuarios);
		
		rowData2 = new Vector();
		for (int i = 0; i < usuarios.size(); i++)
		{
			Usuario usuarioActual = usuarios.get(i);
			
			Vector<String> auxVector = new Vector<String>();
			auxVector.add(usuarioActual.getNumPasaporte());
			auxVector.add(usuarioActual.getNombre());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String fechaNacimiento = usuarioActual.getFechaNacimiento().format(formatter);
			auxVector.add(fechaNacimiento);

			float valorVisa = listValorVisa.get(i);
			auxVector.add(""+valorVisa);
			float impuesto = listImpuesto.get(i);
			auxVector.add(""+impuesto);
			float valorTotal = listValorTotal.get(i);
			auxVector.add(""+valorTotal);
			rowData2.add(auxVector);
		}
		tableUsuariosValores = new JTable(rowData2, dataColumnas2);
		jScrollPane2.setViewportView(tableUsuariosValores);
	}
}
