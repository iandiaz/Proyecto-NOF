
import java.io.IOException;
import java.io.PrintWriter;

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
import VO.PedidoDetalleVO;
import VO.PedidoVO;
import VO.ProductoVO;
import VO.UbicacionVO;

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
		
		
		String idtick=request.getParameter("idtick");
		String id_cliente=request.getParameter("id_cliente");
		String id_prov=request.getParameter("id_prov");
		
		
		if(request.getParameter("grabar") != null){
			
			HttpSession session =request.getSession(true);
			PedidoVO pedido = (PedidoVO)session.getAttribute("pedido");
			
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
				pd.setId_ticket(idtick);
				
				pedido.getDetallePedido().add(pd);
			}
			
			
			
			//integramos los detalles al pedido 
			String id_pedido = PedidosDAO.insertPedido(pedido,Controlador.getUsuIDSession(request),Constantes.PINGRESAR);
			
			
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
			PedidoVO pedido = (PedidoVO)session.getAttribute("pedido");
			
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
			
			session.setAttribute("pedido", pedido);
				
		}
		
		
		if(request.getParameter("grabarOtroTicket") != null){
			
			String ubi_id=request.getParameter("ubi_id_linea");
			String direcciones_id=request.getParameter("direcciones_id");
			String prod_id=request.getParameter("prod_id");
			String cantidad=request.getParameter("cantidad");
			String obs=request.getParameter("pedido_obs");
			
			if(prod_id!=null && !prod_id.equals("")){

				HttpSession session =request.getSession(true);
				PedidoVO pedido = (PedidoVO)session.getAttribute("pedido");
				
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
					
				session.setAttribute("pedido", pedido);
					
			}
			
			response.sendRedirect("/070/I01");
			return;
				
		}
		
		HttpSession session =request.getSession(true);
		PedidoVO pedido = (PedidoVO)session.getAttribute("pedido");
		
		EmpresaVO cliente = new EmpresaVO();
		cliente.setId(id_cliente);
		cliente=EmpresasDAO.getEmpresa(cliente.getId());
		
		pedido.setCliente(cliente);
		
		EmpresaVO proveedor = new EmpresaVO();
		proveedor.setId(id_prov);
		proveedor=EmpresasDAO.getEmpresa(proveedor.getId());
		
		pedido.setProveedor(proveedor);
		
		
		pedido.setId_ticket(idtick);
		
		session.setAttribute("pedido", pedido);
		
		
		request.setAttribute("idtick", idtick);
		request.setAttribute("id_cliente", id_cliente);
		request.setAttribute("pedido", pedido);
		
		request.setAttribute("direcciones_id", pedido.getId_direccion());
		RequestDispatcher rd = request.getRequestDispatcher("I02.jsp");
        rd.forward(request, response);
		   
	}
}
