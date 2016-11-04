package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOAccesoRol;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuario;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuarioRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.MensajesGui;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
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
	 * Componente del user name.
	 */
	@FXML
	private TextField tfUser;

	/**
	 * Componente del password name.
	 */
	@FXML
	private TextField tfPass;

	@Autowired
	private BOUsuario boUsuario;
	
	@Autowired
	private BOAccesoRol boAccesoRol;
	
	@Autowired
	private BOUsuarioRol boUsuarioRol;

	/**
	 * Evento de login.
	 *
	 * @author Richard Vanegas<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 2/11/2016<br/>
	 */
//	@FXML
//	public void login() {
//
//		try {
//
//			Usuario usu = new Usuario();
//			usu.setUsuario(tfUser.getText());
//			usu.setPass(tfPass.getText());
//			if (boUsuario.buscarEntidad(usu).size() == 0) {
//				notificar("LogIn", "El usuario o el password que ha ingresado no son correctos",
//						TipoNotificacion.ERROR);
//			} else {
//
//				List<Rol> roles = boUsuarioRol.listarRolesPorUsuario(usu.getUsuario());
//				
//				for (Rol rol : roles) {
//					List<AccesoRol> accesosRol = boAccesoRol.listarPorRol(rol);
//					
//				}
//				
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//	}

}
