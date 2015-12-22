

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
import Constantes.Controlador;
import DAO.CorreosDAO;
import DAO.DireccionesDAO;
import DAO.EmpresasDAO;
import DAO.EstadosVigNoVigDAO;
import DAO.OCDetallesDAO;
import DAO.OCsDAO;
import DAO.PedidosDAO;
import DAO.ProductosDAO;
import DAO.SpecialBidDAO;
import DAO.TipoCambioDAO;
import VO.ContactoVO;
import VO.CorreoVO;
import VO.DireccionVO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;
import VO.OcDetalleVO;
import VO.OcVO;
import VO.PedidoVO;
import VO.ProductoVO;
import VO.SpecialBidVO;
import VO.TipoMonedaVO;

/**
 * Servlet implementation class M02
 */
@WebServlet("/M02")
public class M02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public M02() {
        super();
        // TODO Auto-generated constructor stub
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

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PCONSULTAR);
		
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
		
		String id_oc=request.getParameter("id");
		
		if(request.getParameter("grabar") != null){
			//insertamos oc  
			String[] id_detalles = request.getParameterValues("id_det_pedidos[]");
			
			String tcambio_ref=request.getParameter("tcambio_ref");
			String tipo_moneda=request.getParameter("tipo_moneda");
			String correo_aviso=request.getParameter("correo_aviso");
			String id_cliente=request.getParameter("id_cliente");
			String id_direccion=request.getParameter("id_direccion");
			String id_contacto=request.getParameter("id_contacto");
			String estados_vig_novig_id=request.getParameter("estados_vig_novig_id");
			String oc_obs=request.getParameter("oc_obs");
			if(oc_obs==null)oc_obs="";
			else oc_obs=oc_obs.toUpperCase();
			
			OcVO orden_compra= new OcVO();
			
			orden_compra.setTcambio_ref(tcambio_ref);
			orden_compra.setTipomoneda(new TipoMonedaVO(tipo_moneda));
			orden_compra.setCorreoa(correo_aviso);
			orden_compra.setId(id_oc);
			orden_compra.setEstadoVigNoVig(new EstadosVigNoVigVO(estados_vig_novig_id));
			
			orden_compra.setObs(oc_obs);
			
			if(id_contacto!=null)orden_compra.setContacto(new ContactoVO(id_contacto));
			
			
			int neto=0;
			for(String id_detalleP:id_detalles){
				String preciocl=request.getParameter("preciocl_"+id_detalleP);
				neto+=Integer.parseInt(preciocl);
				
			}
			
			orden_compra.setSubtotal(neto+"");
			orden_compra.setNeto(neto+"");
			orden_compra.setIva((neto*0.19)+"");
			orden_compra.setTotal((neto*1.19)+"");
		
			OCsDAO.updateOc(orden_compra, Controlador.getUsuIDSession(request), Constantes.PINGRESAR);
			
			orden_compra.setId(id_oc);
			ArrayList<PedidoVO> pedidos_update = new ArrayList<PedidoVO>();
			for(String id_detalleP:id_detalles){
				String precio=request.getParameter("precio_"+id_detalleP);
				String preciocl=request.getParameter("preciocl_"+id_detalleP);
				String sb_id=request.getParameter("special_bid_"+id_detalleP);
				String sb_nombre=request.getParameter("special_bid_name_"+id_detalleP);
				
				String id_producto=request.getParameter("id_prod_"+id_detalleP);
				
				String cantidad=request.getParameter("cantidad_"+id_detalleP);
				
				if(sb_id==null || sb_id.equals("")) sb_id="0";
				
				OcDetalleVO oc_detalle = new OcDetalleVO();
				
				oc_detalle.setOc(new OcVO(id_oc));
				oc_detalle.setId(id_detalleP);
				oc_detalle.setSb_id(sb_id);
				oc_detalle.setSb_nombre(sb_nombre);
				oc_detalle.setPrecio(precio);
				oc_detalle.setPrecio_pesos(preciocl);
				oc_detalle.setCantidad(cantidad);
				oc_detalle.setProducto(new ProductoVO(id_producto));
				
				
				orden_compra.getDetalle_oc().add(oc_detalle);
				
				OCDetallesDAO.updateOcDetalle(oc_detalle);
				
				
				
			}
			
		
			//enviamos correo  
			String obsPedidos ="";
			//pedidos_update
			for(PedidoVO pedido : pedidos_update){
				pedido=PedidosDAO.getPedido(pedido.getId());
				if(pedido.getObs()!=null)obsPedidos+=pedido.getObs()+"<br>";
			}
			CorreoVO correo = new CorreoVO();
			
			DireccionVO direccionenvio= DireccionesDAO.getDireccion(id_direccion);
			EmpresaVO clientenvio= EmpresasDAO.getEmpresa(id_cliente);
			
			String body="<p>NUEVA OC N:"+orden_compra.getNro()+"</p>"
					+ "<p>CLIENTE: "+clientenvio.getNombre_nof()+", "+clientenvio.getRut()+"</p>"
					+ "<p>DIRECCION: "+direccionenvio.getDireccion()+", "+(direccionenvio.getComu_nombre()==null?"":direccionenvio.getComu_nombre())+", "+direccionenvio.getRegi_nombre()+"</p>"
					+ "<p>T/C: "+orden_compra.getTcambio_ref()+"</p>"
					+ "<p>OBSERVACION OC: "+orden_compra.getObs()+"</p>"
					+ "<p>OBSERVACION ADICIONAL: "+obsPedidos+"</p>"
					+ "<table border=\\'1\\' >"; 
			
			body+= "		<tr> "
					+ "			<td>ID </td>"
					+ "			<td>PN/TLI </td>"
					+ "			<td>PRODUCTO</td>"
					+ "			<td>CANTIDAD</td>"
					+ "			<td>PRECIO T/C</td>"
					+ "			<td>$CL</td>"
					+ "			<td>SPECIAL BID</td>"
					+ "		</tr>";
					
			for(OcDetalleVO detalle_oc : orden_compra.getDetalle_oc()){
				detalle_oc.setProducto(ProductosDAO.getProducto(detalle_oc.getProducto().getId()));
				//BUSCAMOS SB 
				String sb_s="";
				if(detalle_oc.getSb_id()!=null && !detalle_oc.getSb_id().equals("") && !detalle_oc.getSb_id().equals("0")){

					SpecialBidVO sb = new SpecialBidVO();
					sb.setId(detalle_oc.getSb_id());
					sb.setId_prod(detalle_oc.getProducto().getId());
					SpecialBidDAO cnx = new SpecialBidDAO();
					
					sb=cnx.getSpecialBid(sb);
					
					sb_s=" "+sb.getGRUPO_NOM()+" "+sb.getSB_NRO();
				}
				
				body+= "		<tr> "
						+ "			<td>"+detalle_oc.getProducto().getId()+"</td>"
						+ "			<td>"+detalle_oc.getProducto().getPn()+"</td>"
						+ "			<td>"+detalle_oc.getProducto().getDesc_corto()+"</td>"
						+ "			<td>"+detalle_oc.getCantidad()+"</td>"
						+ "			<td>"+detalle_oc.getPrecio()+"</td>"
						+ "			<td>"+detalle_oc.getPrecio_pesos()+"</td>"
						+ "			<td>"+sb_s+"</td>"
						+ "		</tr>";
			}
					
					body+= "</table>";
			
			correo.setBody(body);
			correo.setSubject("NUEVA OC DESDE PEDIDO A MAYORISTAS");
			correo.setTo(correo_aviso);
			correo.setModulos_id(Constantes.MODULOID);
			
			CorreosDAO.insertCorreo(correo);
			
			out.println(body);
			response.sendRedirect("/071?Exito=OK");
			return;
			
			
		}
		
		//==========OC=========///
		
		OcVO OC = OCsDAO.getOC(id_oc);
		
		OcDetalleVO detalle_oc_filter = new OcDetalleVO();
		detalle_oc_filter.setOc(new OcVO(OC.getId()));
		detalle_oc_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		
		OC.setDetalle_oc(OCDetallesDAO.getOCDetalles(detalle_oc_filter));
		
		request.setAttribute("OC", OC);
		
		request.setAttribute("tipocambios", TipoCambioDAO.getTipoCambios());
		request.setAttribute("estadosvignovig", EstadosVigNoVigDAO.getEstadoVigNoVig());
		
		
		RequestDispatcher rd = request.getRequestDispatcher("M02.jsp");
        rd.forward(request, response);
	}


}
