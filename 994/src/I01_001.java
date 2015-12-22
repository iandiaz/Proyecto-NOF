

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Controlador;

/**
 * Servlet implementation class I01_001
 */
@WebServlet("/I01_001")
public class I01_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public I01_001() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			 mt(request,response);
		
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

		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE MODULO//////////////
		
		request.setAttribute("modname", Controlador.getNameModulo());
		
		////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////
		
		
		request.setAttribute("date", Controlador.getNowFormated());
		request.setAttribute("time", Controlador.getNowTimeFormated());
		
		RequestDispatcher rd = request.getRequestDispatcher("I01_001.jsp");
        rd.forward(request, response);
		
        
        
	}

}
