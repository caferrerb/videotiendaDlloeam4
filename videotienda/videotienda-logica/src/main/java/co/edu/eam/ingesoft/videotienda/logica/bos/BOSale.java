package co.edu.eam.ingesoft.videotienda.logica.bos;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Sale;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;


@Component
public class BOSale extends BOGenerico<Sale>{
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void crearSa(Sale venta){	
		super.crear(venta);
	}

}
