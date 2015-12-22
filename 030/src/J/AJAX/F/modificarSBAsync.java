package J.AJAX.F;




import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Constantes.Constantes;
import Constantes.Controlador;
import DAO.AlertEventTriggeredDAO;
import DAO.SpecialBidsDAO;
import VO.EstadosVigNoVigVO;
import VO.ProductoVO;
import VO.SpecialBidVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/modificarSBAsync")
public class modificarSBAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarSBAsync() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 mt(request,response);
	}

	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		
		
		System.out.println("MODIFICANDO ASYNC");
		
		//==========TRAEMOS SB =========///
		
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session =request.getSession(true);
		SpecialBidVO specialbid=null;
		
		specialbid = (SpecialBidVO)session.getAttribute("specialbid_");
		
		PrintWriter out = response.getWriter();
		
		if(specialbid==null){
			out.println("ERROR");
		}
		else{
			saveSBStatus(specialbid,request);
			
			//damos vuelta las fechas 
			specialbid.setFecAprobacion(inverseFec(specialbid.getFecAprobacion()));
			specialbid.setFecSolicitud(inverseFec(specialbid.getFecSolicitud()));
			specialbid.setFecInicio(inverseFec(specialbid.getFecInicio()));
			specialbid.setFecTermino(inverseFec(specialbid.getFecTermino()));
			
			//traemos detalles originales 
			ArrayList<ProductoVO> specialbiddetoriginal=SpecialBidsDAO.getSpecialBidsDetalles(specialbid);
			//insertamos sb
			
			String id=SpecialBidsDAO.updateSpecialBid(specialbid, Controlador.getUsuIDSession(request));
			//ahora insertamos los detalles
			if(id!=null){
				specialbid.setId(Integer.parseInt(id));
				for(ProductoVO producto:specialbid.getProductos()){
					
					//verificamos si debemos insertar o updatear 
					//revisamos si el producto existia originalmente 
					
					boolean Prodexiste = false; 
					
					for(ProductoVO prodOri:specialbiddetoriginal){
						if(prodOri.equals(producto)){
							Prodexiste=true; 
							break;
						}
					}
					if(Prodexiste){
						SpecialBidsDAO.updateSpecialBidDetalle(specialbid, producto, Controlador.getUsuIDSession(request));
					}else{
						SpecialBidsDAO.insertSpecialBidDetalle(specialbid, producto, Controlador.getUsuIDSession(request));	
					}
					
					
				}
				
				AlertEventTriggeredDAO.insert(Constantes.MODULOID, Constantes.PMODIFICAR, Controlador.getUsuIDSession(request), specialbid.getEmpresa(), specialbid.getId()+"");
				session.invalidate();
				out.println("OK");
			}
			else{
				out.println("ERROR");
			}
			
			
		}
		
	}
	public String inverseFec(String fechaIn){
		//ENTRA EN FORMATO DD-MM-YYYY
		String fec_inverted="";
		String ar_fec[]=fechaIn.split("-");
		fec_inverted=ar_fec[2]+"-"+ar_fec[1]+"-"+ar_fec[0];
		return fec_inverted;
	}
	public void saveSBStatus(SpecialBidVO sb_session, HttpServletRequest request){
		//save encabezado 
		
		String sb_valor_tc= request.getParameter("sb_valor_tc").trim();
		String cod= request.getParameter("cod").trim();
		String tipo_cambio_id=request.getParameter("tipo_cambio_id").trim();
		String sb_empresa=request.getParameter("sb_empresa").trim();
		String sb_fecsolicitud=request.getParameter("sb_fecsolicitud").trim();
		String sb_fecaprobacion=request.getParameter("sb_fecaprobacion").trim();
		
		String sb_fecinicio=request.getParameter("sb_fecinicio").trim();
		String sb_fectermino=request.getParameter("sb_fectermino").trim();
		
		String estados_vig_novig_id=request.getParameter("estados_vig_novig_id").trim();
		
		sb_session.setValor_tc(Double.parseDouble(sb_valor_tc.trim()));
		sb_session.setCod(cod);
		if(tipo_cambio_id!=null)sb_session.setTipo_moneda_id(Integer.parseInt(tipo_cambio_id));
		sb_session.setEmpresa(sb_empresa);
		sb_session.setFecSolicitud(sb_fecsolicitud);
		sb_session.setFecAprobacion(sb_fecaprobacion);
		sb_session.setFecInicio(sb_fecinicio);
		sb_session.setFecTermino(sb_fectermino);
		sb_session.setEstados_vig_novig(new EstadosVigNoVigVO(estados_vig_novig_id));
		
		//save detalle 
		for(ProductoVO prod :sb_session.getProductos()){
			String total_us= request.getParameter("total_us_"+prod.getId());
			String cantidad= request.getParameter("cant_"+prod.getId());
			String total_cl= request.getParameter("total_cl_"+prod.getId());
			
			if(total_us!=null)prod.setPrecio_us(Double.parseDouble(total_us));
			if(cantidad!=null)prod.setCantidad(Integer.parseInt(cantidad));
			if(total_cl!=null)prod.setPrecio_cl(Integer.parseInt(total_cl));
			
		}
		
	}
}
