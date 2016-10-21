package co.edu.eam.ingesoft.videotienda.vista.util;

import java.nio.Buffer;
import java.util.ResourceBundle;


/**
 * Clase para manejar los mensajes de internacionalizacion de la logica.
 * @author Camilo Andres Ferrer Bustos<br/>
 *         email: caferrerb@gmail.com <br/>
 *         Fecha: 18/10/2015<br/>
 */
public class MensajesGui {

	/**
	 * Isntancia singleton.
	 */
	private static MensajesGui INSTANCIA=new MensajesGui();
	
	/**
	 * Mensajes de internacionalizacion
	 */
	private ResourceBundle bundle;
	/**
	 * COnstrictirp
	 */
	private MensajesGui() {
		bundle=ResourceBundle.getBundle("i18n.mensajes");
	}
	
	/**
	 * Metodo para obtener un mensaje.
	 * @author Camilo Andres Ferrer Bustos<br/>
	 *         email: caferrerb@gmail.com <br/>
	 *         Fecha: 18/10/2015<br/>
	 * @param clave, llave dentro del properties.
	 */
	public static String getMensaje(String clave){
		return INSTANCIA.bundle.getString(clave);
	}

}
