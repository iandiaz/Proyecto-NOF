

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
import DAO.SpecialBidsDAO;
import VO.SpecialBidVO;

/**
 * Servlet implementation class N030_I_01_001
 */
@WebServlet("/N030_I_01_001")
public class N030_I_01_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public N030_I_01_001() {
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
		
		HttpSession session =request.getSession(true);
		
		SpecialBidVO specialbid=null; 
		if(request.getParameter("n") !=null && request.getParameter("n").equals("1")){
			specialbid = new SpecialBidVO();
			specialbid.setId(SpecialBidsDAO.getNewId());
			
		}
		else{
			
			specialbid = (SpecialBidVO)session.getAttribute("specialbid");
			
			if(specialbid==null){
				specialbid = new SpecialBidVO();
				specialbid.setId(SpecialBidsDAO.getNewId());
			}
		}
		
		session.setAttribute("specialbid", specialbid);
		request.setAttribute("specialbid", specialbid);
		
		RequestDispatcher rd = request.getRequestDispatcher("N030_I_01_001.jsp");
        rd.forward(request, response);
		
	}
	
	
	 
	
	

}