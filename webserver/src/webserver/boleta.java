package webserver;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.jws.WebMethod;

import Constantes.Constantes;
import DAO.BoletasDAO;
import DAO.CorreosDAO;
import DAO.DteDetallesDAO;
import DAO.DteTotalesDAO;
import DAO.DtesDAO;
import DAO.EmpresasDAO;
import DAO.ProductosDAO;
import VO.BoletaDetalleVO;
import VO.BoletaVO;
import VO.ContactoVO;
import VO.CorreoVO;
import VO.DireccionVO;
import VO.DteDetalleVO;
import VO.DteTotalVO;
import VO.DteVO;
import VO.ProductoVO;
public class boleta {
	 
	public void boleta() {}
	 
	    @WebMethod
public String inputEncUAI(String RUTCliente,String RSCliente,String GiroCliente,String DirCliente,String ComCliente,String CiuCliente,String Email,int n_proceso, int Monto_precio,Calendar Fecha,int id_producto) {     
	    	
	    	
	    	BoletaVO boleta = new BoletaVO();
	    	boleta.setRUTCliente(RUTCliente);
	    	boleta.setRSCliente(RSCliente.toUpperCase());
	    	boleta.setGiroCliente(GiroCliente.toUpperCase());
	    	boleta.setDirCliente(DirCliente.toUpperCase());
	    	boleta.setComCliente(ComCliente.toUpperCase());
	    	boleta.setCiuCliente(CiuCliente.toUpperCase());
	    	boleta.setEmail(Email.toUpperCase());
	    	boleta.setN_proceso(n_proceso);
	    	/**
	    	 * EL VALOR LLEGA CON IVA INCLUIDO
	    	 */
	    	boleta.setMonto_precio((int)(Monto_precio/1.19));
	    	boleta.setMonto_iva((int) (Monto_precio-boleta.getMonto_precio()));
	    	boleta.setMonto_total((int) (Monto_precio));
	    	
	    	boleta.setFecha(Fecha);
	    	boleta.setId_producto(id_producto);
	    	boleta.setId_clpr(114);
	    	boleta.setTipoDTE("39");
	    	boleta.setEmpresa_emisora("123");
	    	/**
	    	 * INSERTAMOS ENCABEZADO 
	    	 */
	    	String id_boleta= BoletasDAO.insertBoleta(boleta);
	    	
	    	/**
	    	 * INSERTAMOS DETALLE BOLETA 
	    	 */
	    	
	    	if(id_boleta==null) return "ERROR";
	    	
	    	boleta.setId(Integer.parseInt(id_boleta));
	    	
	    	BoletaDetalleVO boletaDetalle= new BoletaDetalleVO();
	    	boletaDetalle.setId_boleta(id_boleta);
	    	boletaDetalle.setPrecio(boleta.getMonto_precio());
	    	boletaDetalle.setId_producto(id_producto);
	    	boletaDetalle.setCantidad(1);
	    	
	    	//buscamos el producto para traer la descripcion 
	    	
	    	ProductoVO producto = ProductosDAO.getProducto(id_producto+"");
	    	boletaDetalle.setDescripcion(producto.getDesc_corto());
	    	
	    	boleta.setN_impresiones(producto.getVida_util_imp());
	    	
	    	boolean checkInsert=BoletasDAO.insertBoletaDetalle(boletaDetalle);
	    	
	    	ArrayList<BoletaDetalleVO> detalles_boleta= new ArrayList<BoletaDetalleVO>();
	    	detalles_boleta.add(boletaDetalle);
	    	
	    	Calendar ahora = Calendar.getInstance();
	    	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			
	    	String FechaEmision=formatDate.format(ahora.getTime());
	    	String IndServicio="3";
	    	String IndMntNeto="2"; 
	    	String PeriodoDesde="";
	    	String PeriodoHasta="";
	    	String FechaVenc="";
	    	String CodInterno=boleta.getId_clpr()+"";
	    	
	    	//seteamos en un principio q no puede enviar a sii 
	    	
	    	boolean puedeenviar=false;
 		    
	    	//buscamos folio que le corresponde a la boleta 
	    	
	    	String folio=BoletasDAO.findNextFolio(boleta);
	    	
	    	if(folio!=null){
	    		if(folio.equals("0")){
	    			//si no existen folios anteriores, tomamos el primer folio que corresponda al ingreso en 850
	    			folio=BoletasDAO.getFirstFolio(boleta);
	    			if(folio!=null){
	    				puedeenviar=true; 
	    			} 
	    		}
	    		else{
	    			//comprobamos que el folio tomado se encuentre disponible en 850
	    			
	    			puedeenviar=DtesDAO.isFolioAvailable(boleta.getEmpresa_emisora(),boleta.getTipoDTE(), folio);
	    			
	    			
	    		}
	    	}
	    	if(puedeenviar){
	    		boleta.setFolio(Integer.parseInt(folio));
	    		/**
	    		 * ARMAMOS CABECERA DEL ARCHIVO
	    		 */
	    		String encabezado=ArchivoBoleta.getEncabezado(boleta.getTipoDTE(), folio, FechaEmision, IndServicio, IndMntNeto, 
		    						PeriodoDesde, PeriodoHasta, FechaVenc, RUTCliente, CodInterno, RSCliente, GiroCliente, DirCliente, ComCliente, CiuCliente, Email);
		    	DteVO dteEncabezado = new DteVO();
		    	   
		    	dteEncabezado.setTipoDTE(boleta.getTipoDTE());
		    	dteEncabezado.setFolio(folio);
		    	dteEncabezado.setFechaEmision(FechaEmision);
		    	dteEncabezado.setCliente(EmpresasDAO.getEmpresa(boleta.getId_clpr()+""));
		    	
		    	dteEncabezado.getCliente().setRut(boleta.getRUTCliente());
		    	dteEncabezado.getCliente().setRazonsocial(boleta.getRSCliente());
		    	dteEncabezado.getCliente().setGiro(boleta.getGiroCliente());
		    	
		    	
		    	DireccionVO direccionCliente = new DireccionVO();
		    	direccionCliente.setDireccion(boleta.getDirCliente());
		    	direccionCliente.setComu_nombre(boleta.getComCliente());
		    	direccionCliente.setCiudad(boleta.getCiuCliente());
		    	   
		    	dteEncabezado.setDireccionCliente(direccionCliente);
		    	   
		    	dteEncabezado.setTotal(boleta.getMonto_total()+"");
		    	dteEncabezado.setNeto(boleta.getMonto_precio()+"");
		    	dteEncabezado.setIva(boleta.getMonto_iva()+"");
		    	dteEncabezado.setCondiciones("PAGO INMEDIATO");
		    	dteEncabezado.setTipo_cambio("1");
		    	dteEncabezado.setEmisor(EmpresasDAO.getEmpresa(boleta.getEmpresa_emisora()));
		    	dteEncabezado.setModulo("831");
		    	dteEncabezado.setCodigo_relacionado(boleta.getId()+"");
		    	dteEncabezado.setN_impresiones(boleta.getN_impresiones());
		    	dteEncabezado.setTotal(boleta.getMonto_total()+"");
		    	
		    	ContactoVO contactoCliente = new ContactoVO();
		    	contactoCliente.setEmail(boleta.getEmail());
		    	contactoCliente.setNombre(boleta.getRSCliente());
		    	dteEncabezado.setContactoCliente(contactoCliente);
		    	//INSERTAMOS CABECERA DEL ARCHIVO EN LA BD 
		    	System.out.println("insertamos en dte");
		    	dteEncabezado.setId(DtesDAO.insertDte(dteEncabezado, "91", "0")); 
		    	   
		    	boleta.setId_dte(dteEncabezado.getId());
		    	   
		    	BoletasDAO.updateBoleta(boleta,"91");
				  
					
	    		/**
	    		 * ARMAMOS TOTALES DEL ARCHIVO 
	    		 */
	    		
	    		String totales = ArchivoBoleta.getTotales(boleta.getMonto_precio()+"", "0", boleta.getMonto_iva()+"", boleta.getMonto_total()+"", "0", boleta.getMonto_total()+"", "0", boleta.getMonto_total()+"");
	    		
	    		DteTotalVO dte_total= new DteTotalVO();
	    		dte_total.setId_enc(dteEncabezado.getId());
	    		dte_total.setNeto(boleta.getMonto_precio()+"");
	    		dte_total.setSubtotal(boleta.getMonto_precio()+"");
	    		dte_total.setTasaIVA("19");
	    		dte_total.setExento("0");
	    		dte_total.setDesctoGral("0");
	    		
	    		dte_total.setIVA(boleta.getMonto_iva()+"");
	    		dte_total.setTotal(boleta.getMonto_total()+"");
	    		
	    		DteTotalesDAO.insertDteTotales(dte_total, "91", "");
	    		
	    		/**
	    		 * ARMAMOS DETALLE DEL ARCHIVO 
	    		 */
	    		
	    		String detalle = ArchivoBoleta.getDetalle(detalles_boleta);
	    		
	    		DteDetalleVO dte_detalle= new DteDetalleVO();
	    		dte_detalle.setId_enc(dteEncabezado.getId());
	    		dte_detalle.setNroLinea("1");
	    		dte_detalle.setCodigo("SERV");
	    		dte_detalle.setDescripcion(boletaDetalle.getDescripcion());
	    		dte_detalle.setCantidad(boletaDetalle.getCantidad()+"");
	    		dte_detalle.setPrecio(boletaDetalle.getPrecio()+"");
	    		dte_detalle.setValorExento("0");
	    		dte_detalle.setValor(boletaDetalle.getPrecio()+"");
	    		dte_detalle.setDescLarga("");
	    		
	    		DteDetallesDAO.insertDteDetalle(dte_detalle, "91", "");
	    		
	    		/**
	    		 * ARMAMOS ARCHIVO COMPLETO 
	    		 */
	    		
	    		String data = encabezado+""+totales+""+detalle+""+"\r\n";
	    		String directorio=Constantes.DIR_FILES_DEV;
	    		
	    		try{
	    			int foliosRestantes=DtesDAO.getFoliosRestantes(dteEncabezado.getEmisor().getId(), dteEncabezado.getTipoDTE());
					System.out.println("FOLIOS RESTANTES: "+foliosRestantes);
	    			Writer outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(""+directorio+(boleta.getTipoDTE())+"_"+dteEncabezado.getModulo()+"_"+(boleta.getId())+"_"+boleta.getFolio()+"_"+boleta.getEmpresa_emisora()+".txt"),"UTF-8"));
					outFile.write(data);
					outFile.close();
					
					// EN CASO QUE QUEDEN MENOS DE 25 FOLIOS MANDAMOS CORREO DE AVISO 
					if(foliosRestantes<=25){
	 		    		//enviamos email 
						CorreoVO correo = new CorreoVO();
						correo.setBody("ATENCION: QUEDAN "+foliosRestantes+" FOLIOS RESTANTES PARA EL TIPO DE DOCUMENTO "+dteEncabezado.getTipoDTE()+"");
						correo.setSubject("AVISO FOLIOS");
						correo.setModulos_id(Constantes.MODULOID);
						correo.setTo(DtesDAO.getCorreosAviso(dteEncabezado.getEmisor().getId(), dteEncabezado.getTipoDTE()));
						
						CorreosDAO.insertCorreo(correo);

	 		    	}

					
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    			return "ERROR GEN FILE";
	    		}
				
	    		
	    	
			}
			else{
				
				CorreoVO correo = new CorreoVO();
				correo.setBody("ATENCION: QUEDAN 0 FOLIOS RESTANTES PARA EL TIPO DE DOCUMENTO "+boleta.getTipoDTE()+"");
				correo.setSubject("AVISO FOLIOS");
				correo.setModulos_id(Constantes.MODULOID);
				correo.setTo(DtesDAO.getCorreosAviso(boleta.getEmpresa_emisora(), boleta.getTipoDTE()));
				
				CorreosDAO.insertCorreo(correo);
				
				return "ERROR: NO EXISTEN FOLIOS DISPONIBLES";
				
			}
			
	    	
	    	
	    	if(checkInsert)return "OK";
	    	else return "ERROR2";
	    }
}
