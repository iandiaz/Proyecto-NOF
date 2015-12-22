

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
import DAO.OCDetallesBirtDAO;
import DAO.OCDetallesDAO;
import DAO.OCsBirtDAO;
import DAO.OCsDAO;
import DAO.PedidosDAO;
import DAO.PedidosDetallesDAO;
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
import VO.PedidoDetalleVO;
import VO.PedidoVO;
import VO.ProductoVO;
import VO.SpecialBidVO;
import VO.TipoMonedaVO;

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
		//String id_pedido=request.getParameter("id_pedido");
		
		
		if(request.getParameter("grabar") != null){
			//insertamos oc  
			String[] id_detalles = request.getParameterValues("id_det_pedidos[]");
			
			String oc_nro=request.getParameter("oc_nro");
			String tcambio_ref=request.getParameter("tcambio_ref");
			String tipo_moneda=request.getParameter("tipo_moneda");
			String id_cliente=request.getParameter("id_cliente");
			String id_proveedor=request.getParameter("id_proveedor");
			String id_direccion=request.getParameter("id_direccion");
			String correo_aviso=request.getParameter("correo_aviso");
			String id_contacto=request.getParameter("id_contacto");
			String oc_obs=request.getParameter("oc_obs");
			if(oc_obs==null)oc_obs="";
			else oc_obs=oc_obs.toUpperCase();
			
			
			OcVO orden_compra= new OcVO();
			
			orden_compra.setTcambio_ref(tcambio_ref);
			orden_compra.setTipomoneda(new TipoMonedaVO(tipo_moneda));
			orden_compra.setCliente(new EmpresaVO(id_cliente));
			orden_compra.setProveedor(new EmpresaVO(id_proveedor));
			
			orden_compra.setDireccion(new DireccionVO(id_direccion));
			orden_compra.setCorreoa(correo_aviso);
			orden_compra.setNro(oc_nro);
			
			orden_compra.setObs(oc_obs);
			
			if(id_contacto!=null)orden_compra.setContacto(new ContactoVO(id_contacto));
			
			/**
			 * CALCULAMOS EL NETO IVA TOTAL PARA EL ENCABEZADO
			 */
			
			int neto=0;
			for(String id_detalleP:id_detalles){
				String preciocl=request.getParameter("preciocl_"+id_detalleP);
				neto+=Integer.parseInt(preciocl);
				
			}
			orden_compra.setSubtotal(neto+"");
			orden_compra.setNeto(neto+"");
			orden_compra.setIva((neto*0.19)+"");
			orden_compra.setTotal((neto*1.19)+"");
			
			String id_oc=OCsDAO.insertOc(orden_compra, Controlador.getUsuIDSession(request), Constantes.PINGRESAR);
			
			String id_oc_birt = null;
			if(Constantes.SYNCBIRT==1) id_oc_birt=OCsBirtDAO.insertOc(orden_compra, Controlador.getUsuIDSession(request));
			
			orden_compra.setId(id_oc);
			
			ArrayList<PedidoVO> pedidos_update = new ArrayList<PedidoVO>();
			
			for(String id_detalleP:id_detalles){
				String precio=request.getParameter("precio_"+id_detalleP);
				String preciocl=request.getParameter("preciocl_"+id_detalleP);
				String sb_id=request.getParameter("special_bid_"+id_detalleP);
				String sb_nombre=request.getParameter("special_bid_name_"+id_detalleP);
				
				String id_producto=request.getParameter("id_prod_"+id_detalleP);
				String id_pedido=request.getParameter("id_pedido_"+id_detalleP);
				
				String cantidad=request.getParameter("cantidad_"+id_detalleP);
				
				if(sb_id==null || sb_id.equals("")) sb_id="0";
				
				OcDetalleVO oc_detalle = new OcDetalleVO();
				
				oc_detalle.setOc(new OcVO(id_oc));
				oc_detalle.setId_detalle_pedido(id_detalleP);
				oc_detalle.setCantidad(cantidad);
				oc_detalle.setProducto(new ProductoVO(id_producto));
				oc_detalle.setSb_id(sb_id);
				oc_detalle.setSb_nombre(sb_nombre);
				oc_detalle.setPrecio(precio);
				oc_detalle.setPrecio_pesos(preciocl);
				
				orden_compra.getDetalle_oc().add(oc_detalle);
				
				OCDetallesDAO.insertOcDetalle(oc_detalle);
				
				if(Constantes.SYNCBIRT==1) OCDetallesBirtDAO.insertOcDetalle(id_oc_birt,oc_detalle);
				
				PedidoVO pedidoUp= new PedidoVO();
				pedidoUp.setId(id_pedido);
				pedidoUp.setId_estado_p("2");
				
				if(!pedidos_update.contains(pedidoUp))pedidos_update.add(pedidoUp);
				
				PedidosDAO.updatePedido(pedidoUp, Controlador.getUsuIDSession(request), "245");
				
			}
			
			
			
			//enviamos correo  
			
			//vemos las obs que tienen los pedidos 
			
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
			
					//correo_aviso="david.alexis.sb@gmail.com";
			correo.setBody(body);
			correo.setSubject("NUEVA OC DESDE PEDIDO A MAYORISTAS");
			correo.setTo(correo_aviso);
			correo.setModulos_id(Constantes.MODULOID);
			
			CorreosDAO.insertCorreo(correo);
			
			out.println(body);
			response.sendRedirect("/071?Exito=OK");
			return;
			
			
		}
		
		//==========PEDIDO=========///
		PedidoDetalleVO pedidoDetalleFilter= new PedidoDetalleVO();
		pedidoDetalleFilter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		
		String[] id_pedidos=request.getParameterValues("selected_pedidos");
		
		pedidoDetalleFilter.setIds_pedidos_filter(id_pedidos);
		
		ArrayList<PedidoDetalleVO> detalle_pedido = PedidosDetallesDAO.getDetallePedidos(pedidoDetalleFilter);
		
		request.setAttribute("nrooc", OCsDAO.getNewId()+"");
		request.setAttribute("pedido", PedidosDAO.getPedido(id_pedidos[0]));
		request.setAttribute("detalle_pedido", detalle_pedido);
		request.setAttribute("tipocambios", TipoCambioDAO.getTipoCambios());
		request.setAttribute("lastcorreo", CorreosDAO.getLastCorreo(Constantes.MODULOID));
		
		RequestDispatcher rd = request.getRequestDispatcher("I02.jsp");
        rd.forward(request, response);
	}
}
