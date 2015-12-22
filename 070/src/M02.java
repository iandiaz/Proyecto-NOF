

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Constantes.Constantes;
import Constantes.Controlador;
import DAO.EmpresasDAO;
import DAO.PedidosDAO;
import DAO.PedidosDetallesDAO;
import DAO.ProductosDAO;
import DAO.UbicacionesDAO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;
import VO.PedidoDetalleVO;
import VO.PedidoVO;
import VO.ProductoVO;
import VO.UbicacionVO;

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
		
		String id_pedido= request.getParameter("id_pedido");
		String idtick=request.getParameter("idtick");
		String id_prov=request.getParameter("id_prov");
		
		
		if(request.getParameter("deteleProducto") != null){
			PedidosDetallesDAO.deletePedidoDetalle(request.getParameter("deteleProducto"), Controlador.getUsuIDSession(request));
			
			//preguntamos cuandos detalles quedan, en caso que no quede ninguno , dejamos pedido como no vigente 
			PedidoDetalleVO detallefilter= new PedidoDetalleVO();
			detallefilter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
			detallefilter.setId_pedido(id_pedido);
			
			ArrayList<PedidoDetalleVO> detallesVigente= PedidosDetallesDAO.getDetallePedidos(detallefilter);
			
			PedidoVO pedidoUpdate = new PedidoVO();
			pedidoUpdate.setId(id_pedido);
			
			if(detallesVigente.size()==0){
				//dejamos pedido como no vigente
				pedidoUpdate.setEstadoVigNoVig(new EstadosVigNoVigVO("2"));
				PedidosDAO.updatePedido(pedidoUpdate, Controlador.getUsuIDSession(request), Constantes.PELIMINAR);
			}
			else{
				PedidosDAO.updatePedido(pedidoUpdate, Controlador.getUsuIDSession(request), Constantes.PMODIFICAR);
			}
			
			
		}
		
		if(request.getParameter("grabar") != null){
			
			HttpSession session =request.getSession(true);
			PedidoVO pedido = (PedidoVO)session.getAttribute("pedido_");
			
			String ubi_id=request.getParameter("ubi_id_linea");
			String direcciones_id=request.getParameter("direcciones_id");
			String prod_id=request.getParameter("prod_id");
			String cantidad=request.getParameter("cantidad");
			String obs=request.getParameter("pedido_obs");
			
			pedido.setId_direccion(direcciones_id);
			pedido.setObs(obs);
			
			if(prod_id!=null && !prod_id.equals("")){
				PedidoDetalleVO pd= new PedidoDetalleVO();
				ProductoVO producto = new ProductoVO();
				producto.setId(prod_id);
				
				producto=ProductosDAO.getProducto(producto.getId());
				
				pd.setProducto(producto);
				pd.setId_pedido(pedido.getId());
				pd.setCantidad(cantidad);
				pd.setId_ubi(ubi_id);
				
				pedido.getDetallePedido().add(pd);
			}
			
			pedido.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
			
			//update del pedido 
			PedidosDAO.updatePedido(pedido, Controlador.getUsuIDSession(request), Constantes.PMODIFICAR);;
			
			//eliminamos detalles anteriores 
			PedidosDetallesDAO.deletePedidoDetalleAll(pedido.getId(), null, Controlador.getUsuIDSession(request));
			//traemos los id producto que no hay q eliminar 
			
			PedidosDetallesDAO.insertPedidoDetalle(pedido,id_pedido,Controlador.getUsuIDSession(request));
			
			session.invalidate();
			response.sendRedirect("/070?Exito=OK");;
			return; 
		}
		if(request.getParameter("grabarMismoTicket") != null){
			String ubi_id=request.getParameter("ubi_id_linea");
			String direcciones_id=request.getParameter("direcciones_id");
			String prod_id=request.getParameter("prod_id");
			String cantidad=request.getParameter("cantidad");
			String obs=request.getParameter("pedido_obs");
			
			HttpSession session =request.getSession(true);
			PedidoVO pedido = (PedidoVO)session.getAttribute("pedido_");
			
			pedido.setId_direccion(direcciones_id);
			pedido.setObs(obs);
			
			PedidoDetalleVO pd= new PedidoDetalleVO();
			
			ProductoVO producto = new ProductoVO();
			producto.setId(prod_id);
			
			producto=ProductosDAO.getProducto(producto.getId());
			
			pd.setProducto(producto);
			pd.setId_pedido(pedido.getId());
			pd.setCantidad(cantidad);
			pd.setId_ubi(ubi_id);
			pd.setId_ticket(idtick);
			
			UbicacionVO ubilinea= UbicacionesDAO.getUbicacion(ubi_id);
			pd.setUbicacion(ubilinea);
			
			pedido.getDetallePedido().add(pd);
			
			session.setAttribute("pedido_", pedido);
				
		}
		if(request.getParameter("grabarOtroTicket") != null){
			
			String ubi_id=request.getParameter("ubi_id_linea");
			String direcciones_id=request.getParameter("direcciones_id");
			String prod_id=request.getParameter("prod_id");
			String cantidad=request.getParameter("cantidad");
			String obs=request.getParameter("pedido_obs");
			
			if(prod_id!=null && !prod_id.equals("")){

				HttpSession session =request.getSession(true);
				PedidoVO pedido = (PedidoVO)session.getAttribute("pedido_");
				
				pedido.setId_direccion(direcciones_id);
				pedido.setObs(obs);
				
				PedidoDetalleVO pd= new PedidoDetalleVO();
				
				ProductoVO producto = new ProductoVO();
				producto.setId(prod_id);
				
				producto=ProductosDAO.getProducto(producto.getId());
				
				pd.setProducto(producto);
				pd.setId_pedido(pedido.getId());
				pd.setCantidad(cantidad);
				pd.setId_ubi(ubi_id);
				pd.setId_ticket(idtick);
				
				UbicacionVO ubilinea= UbicacionesDAO.getUbicacion(ubi_id);
				pd.setUbicacion(ubilinea);
				
				pedido.getDetallePedido().add(pd);
					
				session.setAttribute("pedido_", pedido);
					
			}
			
			response.sendRedirect("/070/M03");
			return;
				
		}
		//==========PEDIDO=========///
		//buscamos pedido 
		
		PedidoVO pedido;
		
		HttpSession session =request.getSession(true);
		
		if(session.getAttribute("pedido_")==null || request.getParameter("n")!=null){
			pedido = PedidosDAO.getPedido(id_pedido);
			
			PedidoDetalleVO detallefilter= new PedidoDetalleVO();
			detallefilter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
			detallefilter.setId_pedido(id_pedido);
			
			ArrayList<PedidoDetalleVO> detalle_pedido = PedidosDetallesDAO.getDetallePedidos(detallefilter);
			pedido.setDetallePedido(detalle_pedido);
		
			if(detalle_pedido.size()>0) pedido.setId_ticket(detalle_pedido.get(0).getId_ticket());
			
			session.setAttribute("pedido_", pedido);
			
			
		}
		else{
			pedido=(PedidoVO) session.getAttribute("pedido_");
				
		}
		
		if(request.getParameter("idtick")!=null) pedido.setId_ticket(request.getParameter("idtick"));
		
		if(request.getParameter("idtick")!=null){
			
			EmpresaVO proveedor = new EmpresaVO();
			proveedor.setId(id_prov);
			proveedor=EmpresasDAO.getEmpresa(proveedor.getId());
			
			pedido.setProveedor(proveedor);
		}
		
		
		
		request.setAttribute("pedido", pedido);
	    
		RequestDispatcher rd = request.getRequestDispatcher("M02.jsp");
        rd.forward(request, response);
		
        
        
	}

}
