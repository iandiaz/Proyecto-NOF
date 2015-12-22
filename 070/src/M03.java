

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
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;
import VO.PedidoVO;

/**
 * Servlet implementation class M03
 */
@WebServlet("/M03")
public class M03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public M03() {
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
		
		
		//==========PEDIDO=========///
		
		HttpSession session =request.getSession(true);
		
		PedidoVO pedido=null; 
		
			pedido = (PedidoVO)session.getAttribute("pedido_");
			
			if(pedido==null){
				pedido = new PedidoVO();
				pedido.setId(PedidosDAO.getNewId()+"");
			}
		
		
        session.setAttribute("pedido", pedido);
		
		//==========SELECT DE CLIENTES=========///
		
		EmpresaVO empresaVO = new EmpresaVO();
		empresaVO.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		empresaVO.setEscliente("1");
		
		if(pedido.getDireccion()!=null)empresaVO.setId_dire(pedido.getDireccion().getId());
		request.setAttribute("clientes", EmpresasDAO.getEmpresas(empresaVO));
		
		//==========SELECT DE PROVEEDORES=========///
		
		EmpresaVO empresaprovVO = new EmpresaVO();
		empresaprovVO.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		empresaprovVO.setEsproveedor("1");
				
		request.setAttribute("proveedores", EmpresasDAO.getEmpresas(empresaprovVO));
		
		request.setAttribute("pedido", pedido);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("M03.jsp");
        rd.forward(request, response);
		
        
        
	}

}
