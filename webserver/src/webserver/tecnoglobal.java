package webserver;


import javax.jws.WebMethod;

import DAO.CorreosDAO;
import VO.CorreoVO;
public class tecnoglobal {
	 
	public void tecnoglobal() {}
	 
	    @WebMethod
	    public String inputFactOC(String fechadoc,String rut,int sucursal,int tipodoc,String estdoc,int numdoc,String linea
	    		,String codigoarticulo,String partnumber,String serie,String descripcionarticulo,float cantidadlinea,float preciounitario
	    		,String ordencompra,int notaventa,String observacion,int codigomoneda,float valormoneda,String glosamoneda) {     
	    		/*
	    	
	    			fechadoc[string(26)]
	    			rut[string(12)]
	    			sucursal[int(1)]
	    			tipodoc[int(33)]
	    			estdoc[string(5)]
	    			numdoc[int(347849)]
	    			linea[string(20)]
	    			codigoarticulo[string(20)]
	    			partnumber[string(20)]
	    			serie[string(25)]
	    			descripcionarticulo[string(50)]
	    			cantidadlinea[float(3)]
	    			preciounitario[float(23928.48)]
	    			ordencompra[string(30)]
	    			notaventa[int(1113748)]
	    			observacion[string(80)]
	    			-codigomoneda[int]: Da el código de la moneda
					-valormoneda[int]: Da el valor de la moneda
					-glosamoneda[string(30): Indica el nombre de la moneda.
	    					
	    		*/
	    	
	    	CorreoVO correo= new CorreoVO();
	    	correo.setTo("david.alexis.sb@gmail.com;gerente@newoffice.cl");
	    	correo.setModulos_id("0");
	    	correo.setSubject("WEBSERVICE TECNOGLOBAL");
	    	
	    	String body ="<table border='1'>"
	    			+ "			<tr> "
	    			+ "				<td>fechadoc</td>"
	    			+ "				<td>rut</td>"
	    			+ "				<td>sucursal</td>"
	    			+ "				<td>tipodoc</td>"
	    			+ "				<td>estdoc</td>"
	    			+ "				<td>numdoc</td>"
					+ "				<td>linea</td>"
					+ "				<td>codigoarticulo</td>"
					+ "				<td>partnumber</td>"
					
					+ "				<td>serie</td>"
					+ "				<td>descripcionarticulo</td>"
					+ "				<td>cantidadlinea</td>"
					+ "				<td>preciounitario</td>"
					+ "				<td>ordencompra</td>"
					
					+ "				<td>notaventa</td>"
					+ "				<td>observacion</td>"
					+ "				<td>codigomoneda</td>"
					
					+ "				<td>valormoneda</td>"
					+ "				<td>glosamoneda</td>"

	    			+ "			</tr>"
	    			+ "			<tr> "
	    			+ "				<td>"+fechadoc+"</td>"
	    			+ "				<td>"+rut+"</td>"
	    			+ "				<td>"+sucursal+"</td>"
	    			+ "				<td>"+tipodoc+"</td>"
	    			+ "				<td>"+estdoc+"</td>"
	    			
					+ "				<td>"+numdoc+"</td>"
					+ "				<td>"+linea+"</td>"
					+ "				<td>"+codigoarticulo+"</td>"
					+ "				<td>"+partnumber+"</td>"
					
					+ "				<td>"+serie+"</td>"
					+ "				<td>"+descripcionarticulo+"</td>"
					+ "				<td>"+cantidadlinea+"</td>"
					+ "				<td>"+preciounitario+"</td>"
					+ "				<td>"+ordencompra+"</td>"
					
					+ "				<td>"+notaventa+"</td>"
					+ "				<td>"+observacion+"</td>"
					+ "				<td>"+codigomoneda+"</td>"
					
					+ "				<td>"+valormoneda+"</td>"
					+ "				<td>"+glosamoneda+"</td>"
	    			+ "			</tr>"
    			+ "		</table>";
	    	
	    	correo.setBody(body);
	    	CorreosDAO.insertCorreo(correo);
	    	
	    	
	    	return "OK";
	    	
	    }
}
