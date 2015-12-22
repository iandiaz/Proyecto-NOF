

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
import DAO.DireccionesDAO;
import DAO.EmpresasDAO;
import DAO.FacturacionDAO;
import DAO.PeriodosTcDAO;
import DAO.TipoCambioDAO;
import DAO.TomaContadorDAO;
import VO.AnexoContratoVO;
import VO.DireccionVO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;
import VO.FacturaDetalleVO;
import VO.FacturaVO;
import VO.PeriodoTcVO;
import VO.TipoCambioVO;

/**
 * Servlet implementation class I02
 */
@WebServlet("/I02")
public class I02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public I02() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Controlador.validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Controlador.validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}
	
	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();		
		
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
		
		//////////////////////////////////////////////////
		////////VERIFICAMOS PERMISO ASOCIADO/////////////

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PINGRESAR);
		
		if(p.equals("0")){
			response.sendRedirect("/001/");	
			return;
		}
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		
		request.setAttribute("modname", Controlador.getNameModulo());
		
		String Perfil_id=Controlador.getPerfilIDSession(request);
		
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////
		
		String empresas_id=request.getParameter("empresas_id");
		String id_per =request.getParameter("id_per");
		String tipo_cambio_id=request.getParameter("tipo_cambio_id");
		String tcambio=request.getParameter("tcambio");
		String[] ubicaciones=(String[])request.getParameterValues("ubicaciones[]");
			
		String condiciones=request.getParameter("condiciones");
		String obs=request.getParameter("obs");
		
		request.setAttribute("condiciones", condiciones);
		request.setAttribute("obs", obs);
		
		request.setAttribute("ubicaciones", ubicaciones);
		request.setAttribute("empresas_id", empresas_id);
		request.setAttribute("id_per", id_per);
		
		request.setAttribute("tipo_cambio_id", tipo_cambio_id);
		request.setAttribute("tcambio", tcambio);
		
		
		EstadosVigNoVigVO estado = new EstadosVigNoVigVO();
		estado.setId("1");
		
		EmpresaVO empSeleccionada = new EmpresaVO();
		empSeleccionada.setEmpresas_id(empresas_id);
		
		empSeleccionada=EmpresasDAO.getEmpresa(empresas_id);
		
		request.setAttribute("empSeleccionada", empSeleccionada);
		
		
		
		if(request.getParameter("grabar") != null){
			
			String fecha=request.getParameter("fecha");
			
			String id_emisor=request.getParameter("id_emisor");
			String rz_cliente=request.getParameter("rz_cliente");
			String dire_id=request.getParameter("dire_id");
			String dir_direccion=request.getParameter("dir_direccion");
			
			String regi=request.getParameter("regi");
			String ciudad=request.getParameter("ciudad");
			String comuna=request.getParameter("comuna");
			String subtotal=request.getParameter("subtotal");
			String desc=request.getParameter("desc");
			String neto=request.getParameter("neto");
			String iva=request.getParameter("iva");
			
			String total=request.getParameter("total");
			
			String glosa_desc=request.getParameter("glosa_desc");
			String n_impresiones=request.getParameter("n_impresiones");
			
			String empresas_rut=request.getParameter("empresas_rut");
			
			//creamos objeto factura 
			
			FacturaVO factura = new FacturaVO();
			factura.setId_cliente(empresas_id);
			
			String fecha_ar[]=fecha.split("-");
			
			factura.setFecha(fecha_ar[2]+"-"+fecha_ar[1]+"-"+fecha_ar[0]);
			factura.setCondpago(condiciones.toUpperCase());
			factura.setId_emisor(id_emisor);
			factura.setCliente_rz(rz_cliente.toUpperCase());
			factura.setDire_id(dire_id);
			factura.setDir_direccion(dir_direccion);
			factura.setDir_region(regi);
			factura.setDir_ciudad(ciudad);
			factura.setDir_comuna(comuna);
			factura.setPeri_tc_id(id_per);
			factura.setSubtotal(subtotal);
			factura.setDesc(desc);
			factura.setNeto(neto);
			factura.setIva(iva);
			factura.setTotal(total);
			factura.setGlosa_desc(glosa_desc);
			factura.setN_imp(n_impresiones);
			factura.setTcambio(tcambio);
			factura.setCliente_rut(empresas_rut);
			factura.setObservaciones(obs.toUpperCase());
			factura.setTipo_cambio(new TipoCambioVO(tipo_cambio_id));
			
			String id_factura= FacturacionDAO.insertFactura(factura);
			
			String[] activosdetail = request.getParameterValues("activosdetail[]");
			String[] anexosdetail = request.getParameterValues("anexosdetail[]");
			String[] detallePreciosAnexoCF = request.getParameterValues("detallePreciosAnexoCF[]");
			
			
			
			String[] detallePreciosCF = request.getParameterValues("detallePreciosCF[]");
			String[] detallePreciosCV = request.getParameterValues("detallePreciosCV[]");
			String[] detalleImps = request.getParameterValues("detalleImps[]");
			String[] ubisdetail = request.getParameterValues("ubisdetail[]");
			String[] estrucdetail = request.getParameterValues("estrucdetail[]");
			String[] anexo_detail = request.getParameterValues("anexo_detail[]");
			
			
			if(activosdetail!=null)for(int i =0; i<activosdetail.length;i++){
				FacturaDetalleVO facturaDetalle = new FacturaDetalleVO();
				facturaDetalle.setId_fact(id_factura);
				facturaDetalle.setId_activo(activosdetail[i]);
				facturaDetalle.setPrecioCF(detallePreciosCF[i]);
				facturaDetalle.setPrecioCV(detallePreciosCV[i]);
				facturaDetalle.setNimp(detalleImps[i]);
				facturaDetalle.setUbi_id(ubisdetail[i]);
				facturaDetalle.setEstrcobro_id(estrucdetail[i]);
				facturaDetalle.setAnc_id(anexo_detail[i]);
				
				facturaDetalle.setEs_activo("1");
				facturaDetalle.setEs_anexo("0");
				facturaDetalle.setEs_estructuracobro("0");
				
				//verificamos si la ubicacion esta dentro de las seleccionadas 
				
				for(String ubic:ubicaciones){
					if(ubic.equals(ubisdetail[i])){
						FacturacionDAO.insertDetalleFactura(facturaDetalle);
						break;
					}
				}
				
				
			}
			
			String[] anexos_obs_detail = request.getParameterValues("anexos_obs_detail[]");
			
			//insert de anexos en detalle
			if(anexosdetail!=null)for(int i =0; i<anexosdetail.length;i++){
				
				FacturaDetalleVO facturaDetalle = new FacturaDetalleVO();
				facturaDetalle.setId_fact(id_factura);
				facturaDetalle.setPrecioCF(detallePreciosAnexoCF[i]);
				facturaDetalle.setEstrcobro_id(estrucdetail[i]);
				facturaDetalle.setAnc_id(anexosdetail[i]);
				facturaDetalle.setEs_activo("0");
				facturaDetalle.setEs_anexo("1");
				facturaDetalle.setEs_estructuracobro("0");
				facturaDetalle.setDescripcion(anexos_obs_detail[i]);
				FacturacionDAO.insertDetalleFactura(facturaDetalle);
				
			}
			
			//insert de estructuras en detalle
			
			String[] estrdetail = request.getParameterValues("estrdetail[]");
			String[] detallePreciosEstrCF = request.getParameterValues("detallePreciosEstrCF[]");
			String[] detallePreciosEstrCV = request.getParameterValues("detallePreciosEstrCV[]");
			
			String[] anexos_estrdetail = request.getParameterValues("anexos_estrdetail[]");
			String[] estr_obs_detail = request.getParameterValues("estr_obs_detail[]");
		
			
			if(estrdetail!=null)for(int i =0; i<estrdetail.length;i++){
				
				FacturaDetalleVO facturaDetalle = new FacturaDetalleVO();
				facturaDetalle.setId_fact(id_factura);
				facturaDetalle.setPrecioCF(detallePreciosEstrCF[i]);
				facturaDetalle.setPrecioCV(detallePreciosEstrCV[i]);
					
				facturaDetalle.setEstrcobro_id(estrdetail[i]);
				facturaDetalle.setAnc_id(anexos_estrdetail[i]);
				facturaDetalle.setEs_activo("0");
				facturaDetalle.setEs_anexo("0");
				facturaDetalle.setEs_estructuracobro("1");
				facturaDetalle.setDescripcion(estr_obs_detail[i]);
				
				FacturacionDAO.insertDetalleFactura(facturaDetalle);
				
			}
			
			
			
			
			
			response.sendRedirect("menu?Exito=OK");
			return; 
        	
		}
		
		
		//==========SELECT DE EMISORES=========///
		
		EmpresaVO emp = new EmpresaVO();
		emp.setEmpresas_escliente("1");
		
		emp.setEstadoVigNoVig(estado);
		emp.setEmpresas_relacionada("1");
		
		request.setAttribute("emisores", EmpresasDAO.getEmpresas(emp));
		
		//==========SELECT DE DIRECCIONES=========///
		
		DireccionVO dire = new DireccionVO();
		
		
		dire.setEmpresa(empSeleccionada);
		
		dire.setEstadoVigNoVig(estado);
		
		request.setAttribute("direcciones", DireccionesDAO.getDirecciones(dire));
		
		//==========SELECT DE PERIODOS=========///
		
		PeriodoTcVO periodo = new PeriodoTcVO();
		periodo.setEmpresa(empSeleccionada);
		periodo.setEstadoVigNoVig(estado);
		periodo.setEstado_periodo_id("4");
		
		request.setAttribute("periodostc", PeriodosTcDAO.getPeriodosTc(periodo));
		
		//==========FECHA ACTUAL=========///
		
		request.setAttribute("fecha", Controlador.getNowFormated());
		
		//==========DETALLE FACTURA=========///
		
		if(request.getParameter("calcTotal") != null){
			
			
				//calculamos los valores de las estructuras en el periodo seleccionado
			
				ArrayList<AnexoContratoVO> anexosContratos = FacturacionDAO.getAnexosFactura(id_per, empresas_id,tipo_cambio_id);
				FacturacionDAO.setPreciosTomaC(anexosContratos, id_per);
				request.setAttribute("anexosContratos", anexosContratos);
				
			
				//EXTRAEMOS FOTO DE ACTIVOS PARA EL PERIODO SELECCIONADO
				     
			   ArrayList<String> activos = PeriodosTcDAO.getActivosPeriodoHis(id_per, empresas_id,tipo_cambio_id);
			    
			    String[] ar_activos = new String[activos.size()];
			    for(int x=0; x < activos.size(); x++){
			    	ar_activos[x]=activos.get(x);
			    }
		             
			    request.setAttribute("ar_activos", ar_activos);
			    
			  //si no tiene ubicaciones seleccionadas entonces le seleccionamos todas automaticamente
				
				if(ubicaciones==null || ubicaciones.length==0){
					ubicaciones= new String[ar_activos.length];
					for(int x =0; x<ar_activos.length;x++){
						String ar_activo[]=ar_activos[x].split(";;");
						ubicaciones[x]=ar_activo[7];
					}
					
					request.setAttribute("ubicaciones", ubicaciones);
				}
				
			    
			    ///////////////////////////////////////////////////
			    //POBLAMOS DATOS DEL PERIODO SELECCIONADO
	 					
	 			ArrayList<String> cont6_activo = new ArrayList<String>();
	 	 		ArrayList<String> cont6_values = new ArrayList<String>();
	 	 		ArrayList<String> cont6_difs = new ArrayList<String>();
	 	 		ArrayList<String> cont6_precios = new ArrayList<String>();
	 	 		
	 	 				
	 			TomaContadorDAO.setContadorValues(id_per, cont6_activo, cont6_values,cont6_difs);
	 					
	 			request.setAttribute("cont6_activo", cont6_activo);
	 	 		request.setAttribute("cont6_values", cont6_values);
	 	 		request.setAttribute("cont6_difs", cont6_difs);
	 	 		request.setAttribute("cont6_precios", cont6_precios);
	 	 		
	 	 		
	 	 		//calculamos los precios
	 	 		
	 	 		//FacturacionDAO.getPrecioContador(cont6_activo,cont6_difs,cont6_precios);
	 				
	 	 		
	 				
	 				ArrayList<PeriodoTcVO> periodosAnt= PeriodosTcDAO.getPeriodosTcAnteriores(id_per,empresas_id);
	 				
	 				String periodos_para_tc[] = new String[7];
	 				String periodosfechas_para_tc[] = new String[7];
                
	 				ArrayList<String> cont5_activo = new ArrayList<String>();
	 				ArrayList<String> cont5_values = new ArrayList<String>();
	 				ArrayList<String> cont5_difs = new ArrayList<String>();
	 				
	 				ArrayList<String> cont4_activo = new ArrayList<String>();
	 				ArrayList<String> cont4_values = new ArrayList<String>();
	 				ArrayList<String> cont4_difs = new ArrayList<String>();
	 				
	 				ArrayList<String> cont3_activo = new ArrayList<String>();
	 				ArrayList<String> cont3_values = new ArrayList<String>();
	 				ArrayList<String> cont3_difs = new ArrayList<String>();
	 				
	 				ArrayList<String> cont2_activo = new ArrayList<String>();
	 				ArrayList<String> cont2_values = new ArrayList<String>();
	 				ArrayList<String> cont2_difs = new ArrayList<String>();
	 				
	 				ArrayList<String> cont1_activo = new ArrayList<String>();
	 				ArrayList<String> cont1_values = new ArrayList<String>();
	 				ArrayList<String> cont1_difs = new ArrayList<String>();
	 				
	 				ArrayList<String> cont0_activo = new ArrayList<String>();
	 				ArrayList<String> cont0_values = new ArrayList<String>();
	 				ArrayList<String> cont0_difs = new ArrayList<String>();
	 				
	 				int cont=5;
	 				for(PeriodoTcVO periodo_:periodosAnt){
	 					periodos_para_tc[cont]=periodo_.getCorrelativo();
	 					periodosfechas_para_tc[cont]=periodo_.getFdesde()+" "+periodo_.getFhasta();

	 					
	 					if(cont==5){
	 						TomaContadorDAO.setContadorValues(periodo_.getId(), cont5_activo, cont5_values,cont5_difs);
 						}
 						if(cont==4){
 							TomaContadorDAO.setContadorValues(periodo_.getId(), cont4_activo, cont4_values,cont4_difs);
 						}
 						if(cont==3){
 							TomaContadorDAO.setContadorValues(periodo_.getId(), cont3_activo, cont3_values,cont3_difs);
 						}
 						if(cont==2){
 							TomaContadorDAO.setContadorValues(periodo_.getId(), cont2_activo, cont2_values,cont2_difs);
 						}
 						if(cont==1){
 							TomaContadorDAO.setContadorValues(periodo_.getId(), cont1_activo, cont1_values,cont1_difs);
 						}
 						if(cont==0){
 							TomaContadorDAO.setContadorValues(periodo_.getId(), cont0_activo, cont0_values,cont0_difs);
 						}
 						
	 					//sacamos para este periodo la toma de contadores 
	 					
	 					cont--;
	 				}
	 				
	 				request.setAttribute("cont5_activo", cont5_activo);
	 				request.setAttribute("cont5_values", cont5_values);
	 				request.setAttribute("cont5_difs", cont5_difs);
	 				
	 				request.setAttribute("cont4_activo", cont4_activo);
	 				request.setAttribute("cont4_values", cont4_values);
	 				request.setAttribute("cont4_difs", cont4_difs);
	 				
	 				request.setAttribute("cont3_activo", cont3_activo);
	 				request.setAttribute("cont3_values", cont3_values);
	 				request.setAttribute("cont3_difs", cont3_difs);
	 				
	 				request.setAttribute("cont2_activo", cont2_activo);
	 				request.setAttribute("cont2_values", cont2_values);
	 				request.setAttribute("cont2_difs", cont2_difs);
	 				
	 				request.setAttribute("cont1_activo", cont1_activo);
	 				request.setAttribute("cont1_values", cont1_values);
	 				request.setAttribute("cont1_difs", cont1_difs);
	 				
	 				request.setAttribute("cont0_activo", cont0_activo);
	 				request.setAttribute("cont0_values", cont0_values);
	 				request.setAttribute("cont0_difs", cont0_difs);
	 				
	 				
	 				request.setAttribute("periodos_para_tc", periodos_para_tc);
	 				request.setAttribute("periodosfechas_para_tc", periodosfechas_para_tc);
	 				
	 				request.setAttribute("id_per", request.getParameter("id_per"));
	 				
	 				
	 				
	 				
			}//fin seleccion periodo
			
		request.setAttribute("tipocambios", TipoCambioDAO.getTipoCambios());
		
		
		RequestDispatcher rd = request.getRequestDispatcher("I02.jsp");
        rd.forward(request, response);
		
        
        
	}
}
