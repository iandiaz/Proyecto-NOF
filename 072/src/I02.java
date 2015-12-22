

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
import DAO.PedidosDAO;
import DAO.PedidosSeriesDAO;
import VO.PedidoSerieVO;
import VO.PedidoVO;

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
		
		String id_pedido=request.getParameter("id_pedido");
		
		//==========PEDIDO=========///
		PedidoVO pedido = PedidosDAO.getPedido(id_pedido);
		request.setAttribute("pedido", pedido);
		
		ArrayList<PedidoSerieVO> detalle_pedido = PedidosSeriesDAO.getPedidosSeries(id_pedido);
		
		if(request.getParameter("grabar") != null){
			for(PedidoSerieVO detalle:detalle_pedido){
				
				String serie=request.getParameter("serie_"+detalle.getId());
				if(serie!=null && !serie.equals(""))PedidosSeriesDAO.setSerie(detalle.getId(), serie);
			}
			
			response.sendRedirect("/072?Exito=OK");;
			return;
		}
		
		detalle_pedido = PedidosSeriesDAO.getPedidosSeries(id_pedido);
		request.setAttribute("detalle_pedido", detalle_pedido);
		
		RequestDispatcher rd = request.getRequestDispatcher("I02.jsp");
        rd.forward(request, response);
		
        
        
	}

}
