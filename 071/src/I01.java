

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
import VO.EstadosVigNoVigVO;
import VO.PedidoVO;

/**
 * Servlet implementation class I01
 */
@WebServlet("/I01")
public class I01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public I01() {
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
		
		
		if(request.getParameter("siguiente")!=null){
			
			//validamos que los check seleccionados sean de la misma direccion 
			
			boolean validate = true; 
			
			String[] id_pedidos=request.getParameterValues("selected_pedidos");
			
			String dir=null;
			for(String id_pedido: id_pedidos){
				
				PedidoVO pedido= PedidosDAO.getPedido(id_pedido);
				
				if(dir==null){
					//asignamos
					dir= pedido.getDireccion().getId();
				}else{
					//comparamos
					
					if(!dir.equals(pedido.getDireccion().getId())){
						validate=false; 
						break;
					}
				}
				
			}
			
			if(validate){
				//enviamos 
				
				RequestDispatcher a = request.getRequestDispatcher("I02");
	      		a.forward(request, response);
	      		
				
				return; 
			}
			else{
				response.sendRedirect("/071?NOOK=2");
				return;
			}
			
			
		}
		
		
		//==========PEDIDOS=========///
		//buscamos pedidos no nviados 
		PedidoVO pedi_filter = new PedidoVO();
		pedi_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		pedi_filter.setId_estado_p("1");
		ArrayList<PedidoVO> pedidos = PedidosDAO.getPedidos(pedi_filter);
		request.setAttribute("pedidos", pedidos);
	    
		RequestDispatcher rd = request.getRequestDispatcher("I01.jsp");
        rd.forward(request, response);
		
        
        
	}

}
