package co.edu.eam.ingesoft.videotienda.vista.util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import javafx.embed.swing.SwingNode;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 * Clase utilitaria para generar el reporte.
 *
 * @author Camilo Andres Ferrer Bustos<br/>
 *         email: caferrerb@gmail.com <br/>
 *         Fecha: 17/11/2015<br/>
 */
public class GeneradorReporte {

	/**
	 * Conexion a la BD.
	 */
	private Connection conexion;

	/**
	 * Constructor
	 * 
	 * @param conexion
	 */
	public GeneradorReporte(Connection conexion) {
		super();
		this.conexion = conexion;
	}

	/**
	 * Metodo para generar el reporte.
	 *
	 * @author Camilo Andres Ferrer Bustos<br/>
	 *         email: caferrerb@gmail.com <br/>
	 *         Fecha: 17/11/2015<br/>
	 * @param parametros,
	 *            mapa con parametros.
	 * @param rutaJrxml,
	 *            ruta del archivo.
	 * @param nombreReporte,
	 *            nombre del reporte.
	 */
	public void generarReporte(Map<String, Object> parametros, String rutaJrxml, String nombreReporte) throws Exception {

		try (InputStream input = this.getClass().getResourceAsStream(rutaJrxml);) {
			// carga la plantilla para ser compilada.
			JasperDesign platilla = JRXmlLoader.load(input);

			// compila el reporte..
			JasperReport report = JasperCompileManager.compileReport(platilla);
			// impresora del reporte.
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, conexion);
			// visor del reporte.
			JRViewer viewer = new JRViewer(jasperPrint);

			// dialogo para mostrar el reporte.
			Alert dialog = new Alert(AlertType.INFORMATION);
			dialog.setTitle("Reporte");
			dialog.setHeaderText(nombreReporte);
			SwingNode node = new SwingNode();
			node.setContent(viewer);
			dialog.getDialogPane().setExpandableContent(node);
			dialog.getDialogPane().setExpanded(true);
			dialog.showAndWait();

		}finally {
			conexion.close();
		}

	}
}
