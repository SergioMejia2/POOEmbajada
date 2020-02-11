package co.edu.javeriana.citasembajada.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JDialog;

import co.edu.javeriana.citasembajada.controlador.Controlador;
import co.edu.javeriana.citasembajada.modelo.Usuario;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

public class InterfazGrafica extends JDialog implements ChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Relación con el controlador
	 */
	private Controlador controlador;
	/**
	 * Relaciones con los paneles
	 */
	private JTabbedPane tabbedPane;
	
	private PanelAsociarPaisEmbajada asociarPaisEmbajada;
	private PanelCalcularValorVisa calcularValorVisa;
	private PanelIngresarSolicitantes ingresarSolicitantes;
	private PanelListaBeneficiarios listaBeneficiarios;
	private PanelMenuServicios menuServicios;
	private PanelReporteCitas reporteCitas;
	private PanelSolicitarVisaTrabajo solicitarVisaTrabajo;
	private PanelSolicitarVisaTurismo solicitarVisaTurismo;
	private PanelSolicitarVisaEstudiante solicitarVisaEstudiante;

	
	/**
	 * Create the dialog.
	 */
	public InterfazGrafica(List<String> paises, Controlador pControlador) 
	{
		getContentPane().setBackground(new Color(255, 255, 255));
		controlador = pControlador;
		asociarPaisEmbajada = new PanelAsociarPaisEmbajada(paises,this);
		calcularValorVisa = new PanelCalcularValorVisa(this);
		ingresarSolicitantes = new PanelIngresarSolicitantes(this);
		listaBeneficiarios = new PanelListaBeneficiarios();
		menuServicios = new PanelMenuServicios(this);
		reporteCitas = new PanelReporteCitas(this);
		solicitarVisaTrabajo = new PanelSolicitarVisaTrabajo(this);
		solicitarVisaTurismo = new PanelSolicitarVisaTurismo(this);
		solicitarVisaEstudiante = new PanelSolicitarVisaEstudiante(this);
		setBounds(100, 100, 900, 400);
		getContentPane().setLayout(new BorderLayout());
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addChangeListener(this);
		
		tabbedPane.add("Menú de servicios",menuServicios);
		tabbedPane.add("Asociar país a la Embajada", asociarPaisEmbajada);
		tabbedPane.add("Ingresar Solicitantes",ingresarSolicitantes);
		tabbedPane.add("Solicitar Visa Turismo", solicitarVisaTurismo);
		tabbedPane.add("Solicitar Visa Trabajo", solicitarVisaTrabajo);
		tabbedPane.add("Solicitar Visa Estudiante", solicitarVisaEstudiante);
		tabbedPane.add("Reporte de citas", reporteCitas);
		tabbedPane.add("Calcular Valor Visa", calcularValorVisa);
		tabbedPane.add("Lista de beneficiarios", listaBeneficiarios);
		
		
		setTitle("Sistema de Embajada");
		
	}

	public String getPaisSeleccionado()
	{
		String paisSeleccionado = asociarPaisEmbajada.getPaisSeleccionado();
		return paisSeleccionado;
	}

	public void asociarPaisEmbajada()
	{
		controlador.asociarPaisEmbajada();
		
	}

	public String getArchivoPais()
	{
		String archivoSeleccionado = asociarPaisEmbajada.getArchivo();
		return archivoSeleccionado;
	}

	public String getArchivoUsuarios()
	{
		String archivoUsuarios = ingresarSolicitantes.getArchivo();
		return archivoUsuarios;
	}

	public void ingresarSolicitantes()
	{
		controlador.insertarSolicitantes();
		
	}

	public void actualizarIngresarSolicitantes(List<Usuario> usuarios)
	{
		ingresarSolicitantes.actualizar(usuarios);
	}

	public void realizarSolicitudTurismo()
	{
		controlador.realizarSolicitudTurismo();
		
	}



	public int getDiasEstadia()
	{
		String diasEstadia = solicitarVisaTurismo.getDiasEstadia();
		int dias = Integer.parseInt(diasEstadia);
		return dias;
	}

	public String getArchivoTarifas()
	{
		String archivoTarifa = null;
		if(tabbedPane.getSelectedComponent().equals(solicitarVisaTurismo))
		{
			archivoTarifa = solicitarVisaTurismo.getArchivo();
		
		}
		else if(tabbedPane.getSelectedComponent().equals(solicitarVisaTrabajo))
		{
			archivoTarifa = solicitarVisaTrabajo.getArchivo();
		}
		else if(tabbedPane.getSelectedComponent().equals(solicitarVisaEstudiante))
		{
			archivoTarifa = solicitarVisaEstudiante.getArchivo();
		}
		return archivoTarifa;
	}

	public boolean agregarUsuario()
	{
		return controlador.agregarUsuario();	
	}


	public String getPasaporteSeleccionado()
	{
		String pasaporte = null;
		if(tabbedPane.getSelectedComponent().equals(solicitarVisaTurismo))
		{
			pasaporte = solicitarVisaTurismo.getPasaporteSeleccionado();
		
		}
		else if(tabbedPane.getSelectedComponent().equals(solicitarVisaTrabajo))
		{
			pasaporte = solicitarVisaTrabajo.getPasaporteSeleccionado();
		}
		else if(tabbedPane.getSelectedComponent().equals(solicitarVisaEstudiante))
		{
			pasaporte = solicitarVisaEstudiante.getPasaporteSeleccionado();
		}
		return pasaporte;
	}

	public int getNumSolicitud()
	{
		int noSolicitud = -1;
		if(tabbedPane.getSelectedComponent().equals(solicitarVisaTurismo))
		{
			noSolicitud = solicitarVisaTurismo.getNumSolicitud();
		
		}
		else if(tabbedPane.getSelectedComponent().equals(solicitarVisaTrabajo))
		{
			noSolicitud = solicitarVisaTrabajo.getNumSolicitud();
		}
		else if(tabbedPane.getSelectedComponent().equals(solicitarVisaEstudiante))
		{
			noSolicitud = solicitarVisaEstudiante.getNumSolicitud();
		}
		return noSolicitud;
	}

	public void actualizarSolicitud(int codigo, List<Usuario> usuariosParam)
	{
		if(tabbedPane.getSelectedComponent().equals(solicitarVisaTurismo))
		{
			solicitarVisaTurismo.actualizar(codigo, usuariosParam);	
		}
		else if(tabbedPane.getSelectedComponent().equals(solicitarVisaTrabajo))
		{
			solicitarVisaTrabajo.actualizar(codigo, usuariosParam);	
		}
		else if(tabbedPane.getSelectedComponent().equals(solicitarVisaEstudiante))
		{
			solicitarVisaEstudiante.actualizar(codigo, usuariosParam);	
		}
	}

	public void cambiarPanel(String comando)
	{
		if(comando.equals(PanelMenuServicios.ASOCIAR_PAIS))
			tabbedPane.setSelectedIndex(1);
		else if(comando.equals(PanelMenuServicios.INGRESAR_SOLICITANTES))
			tabbedPane.setSelectedIndex(2);
		else if(comando.equals(PanelMenuServicios.SOLICITAR_VISA_TURISMO))
			tabbedPane.setSelectedIndex(3);
		else if(comando.equals(PanelMenuServicios.SOLICITAR_VISA_TRABAJO))
			tabbedPane.setSelectedIndex(4);
		else if(comando.equals(PanelMenuServicios.SOLICITAR_VISA_ESTUDIANTE))
			tabbedPane.setSelectedIndex(5);
		else if(comando.equals(PanelMenuServicios.REPORTE_CITAS))
			tabbedPane.setSelectedIndex(6);
		else if(comando.equals(PanelMenuServicios.CALCULAR_VALOR))
			tabbedPane.setSelectedIndex(7);
		else if(comando.equals(PanelMenuServicios.LISTAR_BENEFICIARIOS))
			tabbedPane.setSelectedIndex(8);
	}
	
	public void cargarSistema()
	{
		controlador.leerDatosSistema();
		
	}

	public void serializarSistema()
	{
		controlador.serializarSistema();
		
	}

	public void realizarSolicitudTrabajo()
	{
		controlador.realizarSolicitudTrabajo();
		
	}

	public String getEmpresaSeleccionada()
	{
		String empresa = solicitarVisaTrabajo.getEmpresa();
		return empresa;
	}

	public String getCargoSeleccionado()
	{
		String cargo = solicitarVisaTrabajo.getCargo();
		return cargo;
	}

	public void calcularValorVisa(String code)
	{
		controlador.calcularValorVisa(code);
		
	}

	public int getCalcularValor()
	{
		return calcularValorVisa.getNumero();
	}

	public void actualizarCalcularValor(List<Usuario> usuarios, List<Float> listValorVisa, List<Float> listImpuesto,
			List<Float> listValorTotal, float pagoMonedaPesos, String simbolo, double cambioOficial,
			float totalSolicitud)
	{
		calcularValorVisa.actualizar(usuarios,listValorVisa,listImpuesto,listValorTotal,pagoMonedaPesos,simbolo,cambioOficial,totalSolicitud);
	}

	public void actualizarPais(String paisEmbajada)
	{
		setTitle("Sistema de Embajada de "+paisEmbajada);
		reporteCitas.setNombrePais(paisEmbajada);	
	}

	public void generarReporteCitas()
	{
		controlador.generarReporteCitas();	
	}

	public String getFechaReporte()
	{
		String fecha = reporteCitas.getFechaReporte();
		return fecha;
	}

	public void actualizarReporteCitas(List<Usuario> usuariosCitas)
	{
		reporteCitas.actualizar(usuariosCitas);	
	}


	public void mostrarUsuariosBeneficiarios(String valorPerdidoValor, List<String> lineasArchivo)
	{
		listaBeneficiarios.mostrarUsuarios(valorPerdidoValor, lineasArchivo);
		
	}

	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		if(tabbedPane.getSelectedComponent().equals(listaBeneficiarios))
		{
			controlador.generarReporteBeneficiados();
		}
		
	}

	public void cargarSerializacion()
	{
		controlador.leerDatosSistema();
	}

	public String getArchivoACargar()
	{
		String archivo = menuServicios.getArchivoACargar();
		return archivo;
	}
	public String getDireccionAGuardar()
	{
		String direccion = menuServicios.getDireccionAGuardar();
		return direccion;
	}

	public void realizarSolicitudEstudiante()
	{
		controlador.realizarSolicitudEstudiante();
		
	}

	public String getInstitucion()
	{
		String institucion = solicitarVisaEstudiante.getInstitucion();
		return institucion;
	}

	public String getEscolaridad()
	{
		String escolaridad = solicitarVisaEstudiante.getEscolaridad();
		return escolaridad;
	}

	public void mostrarInfoEmbajada()
	{
		controlador.mostrarInfoEmbajada();
	}

	
}
