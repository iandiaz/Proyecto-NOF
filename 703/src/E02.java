

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
import DAO.FacturacionDAO;
import DAO.PeriodosTcDAO;
import DAO.TomaContadorDAO;
import VO.FacturaDetalleVO;
import VO.FacturaVO;
import VO.PeriodoTcVO;

/**
 * Servlet implementation class E02
 */
@WebServlet("/E02")
public class E02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public E02() {
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

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PELIMINAR);
		
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
		
		String factura_id=request.getParameter("factura_id");
		
		FacturaVO factura = FacturacionDAO.getFactura(factura_id);
		ArrayList<FacturaDetalleVO> detalles_factura = FacturacionDAO.getFacturaDetalle(factura_id);
		
		request.setAttribute("factura", factura);
		request.setAttribute("detalles_factura", detalles_factura);
		
		
		String tcambio=request.getParameter("tcambio");
		
		
		if(request.getParameter("grabar") != null){
			//desactivamos factura 
			
			FacturacionDAO.toggleFactura(factura_id, "2");
			response.sendRedirect("menu?Exito=OK");
			return;
		}
		
		
		//==========DETALLE FACTURA=========///
		
				//EXTRAEMOS FOTO DE ACTIVOS PARA EL PERIODO SELECCIONADO
				//seteamos los activos que se cobraron en ese momento 
				
				String id_activos ="'0'";
				
				for(FacturaDetalleVO detalle : detalles_factura){
					id_activos+=",'"+detalle.getId_activo()+"'";
				}
				     
			   ArrayList<String> activos = PeriodosTcDAO.getActivosPeriodoHis(factura.getPeri_tc_id(), factura.getId_cliente(),factura.getTipo_cambio().getId(),id_activos);
			    
			    String[] ar_activos = new String[activos.size()];
			    for(int x=0; x < activos.size(); x++){
			    	ar_activos[x]=activos.get(x);
			    }
		             
			    request.setAttribute("ar_activos", ar_activos);
			    
			    ///////////////////////////////////////////////////
			    //POBLAMOS DATOS DEL PERIODO SELECCIONADO
	 					
	 			ArrayList<String> cont6_activo = new ArrayList<String>();
	 	 		ArrayList<String> cont6_values = new ArrayList<String>();
	 	 		ArrayList<String> cont6_difs = new ArrayList<String>();
	 	 		ArrayList<String> cont6_precios = new ArrayList<String>();
	 	 		
	 	 				
	 			TomaContadorDAO.setContadorValues(factura.getPeri_tc_id(), cont6_activo, cont6_values,cont6_difs);
	 					
	 			request.setAttribute("cont6_activo", cont6_activo);
	 	 		request.setAttribute("cont6_values", cont6_values);
	 	 		request.setAttribute("cont6_difs", cont6_difs);
	 	 		request.setAttribute("cont6_precios", cont6_precios);
	 	 		
	 	 		
	 	 		//calculamos los precios
	 	 		
	 	 		//FacturacionDAO.getPrecioContador(cont6_activo,cont6_difs,cont6_precios);
	 				
	 	 		
	 				
	 				ArrayList<PeriodoTcVO> periodosAnt= PeriodosTcDAO.getPeriodosTcAnteriores(factura.getPeri_tc_id(),factura.getId_cliente());
	 				
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
	 				
	 				
	 				
		RequestDispatcher rd = request.getRequestDispatcher("E02.jsp");
        rd.forward(request, response);
		
        
        
	}

}
