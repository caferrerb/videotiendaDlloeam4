package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOAccesoRol;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuario;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuarioRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * COntrolador de la ventana de login.
 *
 * @author Camilo Andres Ferrer Bustos<br/>
 *         email: caferrerb@gmail.com <br/>
 *         Fecha: 18/10/2015<br/>
 */
@Controller
public class LoginUsuarioController extends BaseController implements Initializable {

	/**
	 * Componente del user name.
	 */
	@FXML
	private TextField tfUser;

	/**
	 * Componente del password name.
	 */
	@FXML
	private PasswordField tfPass;

	/**
	 * BO con los métodos de la lógica de Usuario
	 */
	@Autowired
	private BOUsuario boUsuario;

	/**
	 * BO con los métodos de la lógica de AccesoRol
	 */
	@Autowired
	private BOAccesoRol boAccesoRol;

	/**
	 * BO con los métodos de la lógica de UsuarioRol
	 */
	@Autowired
	private BOUsuarioRol boUsuarioRol;

	/**
	 * BO con los métodos de la lógica de Empleado
	 */
	@Autowired
	private BOEmpleado boEmpleado;

	private Usuario usu;

	private Staff emp;

	/**
	 * Constructor
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		usu = new Usuario();
		emp = new Staff();
	}

	/**
	 * Evento de login.
	 *
	 * @author Richard Vanegas<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 2/11/2016<br/>
	 */
	@FXML
	public void login() {

		try {

			Usuario usu = new Usuario();
			usu.setUsuario(tfUser.getText());
			usu.setPass(tfPass.getText());
			List<Usuario> lista = boUsuario.buscarEntidad(usu);
			int list = lista.size();
			if (list == 0 || !(lista.get(0).getPass().equals(tfPass.getText()))) {
				notificar("LogIn", "El usuario o el password que ha ingresado no son correctos",
						TipoNotificacion.ERROR);
			} else {
				List<Rol> roles = boUsuarioRol.listarRolesPorUsuario(usu.getUsuario());
				List<Menu> menus = new ArrayList<>();
				menus.add(mainController.mnuInicio);
				menus.add(mainController.mnuPeliculas);
				menus.add(mainController.menuCliente);
				menus.add(mainController.mnuActores);
				menus.add(mainController.mnuGeneros);
				menus.add(mainController.mnuEmpleados);
				menus.add(mainController.mnuPrestamos);
				menus.add(mainController.mnuParametrizacion);
				menus.add(mainController.mnuTiendas);
				menus.add(mainController.mnuAutorizacion);
				menus.add(mainController.menuReportes);

				for (Rol rol : roles) {
					List<AccesoRol> accesosRol = boAccesoRol.listarPorRol(rol);
					for (AccesoRol accesoRol : accesosRol) {

						for (Menu men : menus) {
							if (accesoRol.getAcceso().getNombre().equals(men.getText())) {
								men.setVisible(true);
							}
						}
					}
				}
				emp = boEmpleado.buscarEmpleadoPorUsuario(usu).get(0);
				notificar("LogIn","El empleado " + emp.getFirstName() + " " + emp.getLastName() + " ha iniciado sesion",
						TipoNotificacion.INFO);
				guardarEnSesion("empleadologin", emp);
				mainController.abrirInicio();
				mainController.btnCerrarSesion.setVisible(true);

				// obtenerValor("empleadologin");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
