package co.edu.eam.ingesoft.videotienda.vista.util;

import org.controlsfx.control.Notifications;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Clase de la que deben heredar todos los controladores.
 * 
 * @author Camilo Andres Ferrer Bustos<br/>
 *         email: caferrerb@gmail.com <br/>
 *         Fecha: 19/10/2015<br/>
 */
public class BaseController {

	/**
	 * Controlador principal.
	 */
	protected MainController mainController;

	/**
	 * Metodo para setear el controlador principal al controlador.
	 * 
	 * @author Camilo Andres Ferrer Bustos<br/>
	 *         email: caferrerb@gmail.com <br/>
	 *         Fecha: 19/10/2015<br/>
	 * @param ctller
	 */
	public void init(MainController ctller) {
		mainController = ctller;

	}

	/**
	 * Metodo para mostrar un mensaje al usuario.
	 * 
	 * @author Camilo Andres Ferrer Bustos<br/>
	 *         email: caferrerb@gmail.com <br/>
	 *         Fecha: 25/10/2015<br/>
	 * @param tipo,
	 *            tipo del mensaje
	 * @param header,
	 *            header del mensaje
	 * @param titulo,
	 *            titlo del mensaje
	 * @param msj,
	 *            mensaje.
	 */
	public void mostrarMensaje(AlertType tipo, String header, String titulo, String msj) {
		Alert alert = new Alert(tipo);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(msj);
		alert.showAndWait();

	}

	/**
	 * Metodo para mostrar un mensaje al usuario.
	 * 
	 * @author Camilo Andres Ferrer Bustos<br/>
	 *         email: caferrerb@gmail.com <br/>
	 *         Fecha: 25/10/2015<br/>
	 * @param tipo,
	 *            tipo del mensaje
	 * @param header,
	 *            header del mensaje
	 * @param titulo,
	 *            titlo del mensaje
	 * @param msj,
	 *            mensaje.
	 */
	public void notificar(String titulo, String msj, TipoNotificacion tipo) {
		String toastMsg = msj;
		int toastMsgTime = 3500; // 3.5 seconds
		int fadeInTime = 500; // 0.5 seconds
		int fadeOutTime = 500; // 0.5 seconds
		Notifications not = Notifications.create().position(Pos.TOP_RIGHT).text(msj).title(titulo);
		switch (tipo) {
		case INFO:
			not.showInformation();
			break;
		case ERROR:
			not.showError();
			break;

		default:
			break;
		}
	}

	/**
	 * Evento de cierre de la ventana.
	 * 
	 * @author Camilo Andres Ferrer Bustos<br/>
	 *         email: caferrerb@gmail.com <br/>
	 *         Fecha: 20/10/2015<br/>
	 */
	@FXML
	public void cerrar() {
	}
}
