package co.edu.eam.ingesoft.videotienda.vista.controladores;



import java.awt.TextField;

import javax.swing.text.TableView;

import org.hibernate.hql.internal.ast.tree.InitializeableNode;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;

@Controller
public class ControladorGestionarVenta extends BaseController implements InitializeableNode {

	@FXML
	private TextField jtfTitulo;
	
	@FXML
	private TableView jttablacontenidoPelicula;
	
	
	@Override
	public void initialize(Object arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void buscar(){
		System.out.println("buscando...");
	}

}
