package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOCustomer;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOSale;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Sale;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@Controller
public class ControladorvenderPelicula extends BaseController implements Initializable {

	@Autowired
	private BOCustomer boCus;
	
	@Autowired
	private BOSale boSale;
	
	@Autowired
	private BOEmpleado boEm;
	
	@Autowired
	private BOFilm boPel;

	@FXML
	private TextField jtfIdCliente;

	@FXML
	private TextField jtfNombreCliente;

	@FXML
	private TextField jtfIdPelicula;
	
	@FXML
	private TextField jtfIdEmpleado;
	
	@FXML
	private TextField jtfIdVenta;
	
	@FXML
	private TextField jtfFecha;
	
	@FXML
	private ImageView jcampoFoto;	
	
	private Staff empleado;

	private Film film;
	
	
	@FXML
	public void buscarCliente() {
		try {
			int idCliente = Integer.parseInt(jtfIdCliente.getText());
			List<Customer> cus = boCus.listaBucarCliente(idCliente);
			Customer regiCus = boCus.buscar(idCliente);
			if (regiCus != null) {
				
			for (int i = 0; i < cus.size(); i++) {
				jtfNombreCliente.setText(cus.get(i).getFirstName());
				}
			
			}else{
				notificar("¡ERROR!", "El cliente no se encuentra registrado",  TipoNotificacion.ERROR);
				abrirVentana("/fxml/VentanaGestionarClientes.fxml", ControladorGestionarClientes.class);
			}
			
			if (regiCus.getPicture() != null) {
				Image im = new Image(new ByteArrayInputStream(regiCus.getPicture()));
				jcampoFoto.setImage(im);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void venderPelicula(){
		try {
			Sale vender = new Sale();
			Customer cus = boCus.buscar(Integer.parseInt(jtfIdCliente.getText()));		
			vender.setCustomer(cus);
			Staff idEmpleado = boEm.buscar(Byte.parseByte(jtfIdEmpleado.getText()));
			vender.setStaff(idEmpleado);
			Film idFilm = boPel.buscar(Integer.parseInt(jtfIdPelicula.getText()));
			vender.setFilm(idFilm);			
			vender.setSaleDate(new Timestamp(new Date().getTime()));
			
			boSale.crearSa(vender);
			
			notificar("¡NOTIFICACION!", "La venta se realizo exitosamente",  TipoNotificacion.INFO);
			limpiarcampos();
			abrirVentana("/fxml/GestionarVentaPeliculas.fxml", ControladorGestionarVenta.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		film = (Film) obtenerValor("peliculavender");
		empleado = (Staff) obtenerValor("empleadologin"); 
		int id = film.getFilmId();
		int idem = empleado.getStaffId();
		jtfIdPelicula.setText(String.valueOf(id));
		jtfIdEmpleado.setText(String.valueOf(idem));
		
	}
	
	public void limpiarcampos(){
		jtfIdCliente.setText(null);
		jtfIdEmpleado.setText(null);
		jtfIdPelicula.setText(null);
		jtfNombreCliente.setText(null);
	}
	

}
