package co.edu.eam.ingesoft.videotienda.vista.controladores;

import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.MensajesGui;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * COntrolador de la ventana de login.
 *
 * @author Camilo Andres Ferrer Bustos<br/>
 *         email: caferrerb@gmail.com <br/>
 *         Fecha: 18/10/2015<br/>
 */
public class LoginUsuarioController extends BaseController {

	/**
	 * compoente del user name.
	 */
	@FXML
	private TextField tfUser;

	/**
	 * compoente del password name.
	 */
	@FXML
	private TextField tfPass;

	

	/**
	 * COnstructor.
	 */
	public LoginUsuarioController() {
	}

	/**
	 * Evento de login.
	 *
	 * @author Camilo Andres Ferrer Bustos<br/>
	 *         email: caferrerb@gmail.com <br/>
	 *         Fecha: 18/10/2015<br/>
	 */
	@FXML
	public void login() {

		try {

		} catch (Exception e) {

			mostrarMensaje(AlertType.ERROR, MensajesGui.getMensaje("loginusuario.erroriniciosesion"),
					MensajesGui.getMensaje("general.error"), e.getMessage());

			e.printStackTrace();
		}

	}

}
