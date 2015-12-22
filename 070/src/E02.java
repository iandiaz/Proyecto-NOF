

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
import DAO.PedidosDAO;
import DAO.PedidosDetallesDAO;
import VO.EstadosVigNoVigVO;
import VO.PedidoDetalleVO;
import VO.PedidoVO;

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
		
		//==========PEDIDO=========///
		//buscamos pedido 
		
		PedidoVO pedido = PedidosDAO.getPedido(id_pedido);
		
		PedidoDetalleVO detallefilter= new PedidoDetalleVO();
		detallefilter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		detallefilter.setId_pedido(id_pedido);
		
		ArrayList<PedidoDetalleVO> detalle_pedido = PedidosDetallesDAO.getDetallePedidos(detallefilter);
		pedido.setDetallePedido(detalle_pedido);
		
		request.setAttribute("pedido", pedido);
	    
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("E02.jsp");
        rd.forward(request, response);
		
        
        
	}

}
