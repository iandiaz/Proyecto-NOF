

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
 * Servlet implementation class R01
 */
@WebServlet("/R01")
public class N025_R_01_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public N025_R_01_001() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean sesion_valida=Controlador.validateSession(request,response);
		
		if(!sesion_valida) response.sendRedirect("/001/");
		else {
			 mt(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/////////////////////GENERAR ///////////////////////
		if(request.getParameter("informe").equals("1")){
			
           	RequestDispatcher a = request.getRequestDispatcher("R_GEN1");
      		a.forward(request, response);
      		return;
          
		}
		if(request.getParameter("informe").equals("2")){
			
           	RequestDispatcher a = request.getRequestDispatcher("R_GEN2");
      		a.forward(request, response);
      		return;
          
		}
		if(request.getParameter("informe").equals("3")){
			
           	RequestDispatcher a = request.getRequestDispatcher("R_GEN3");
      		a.forward(request, response);
      		return;
          
		}
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
	   	////////DEFINE PARAMETROS DE USUARIO//////////////
	   	
	   	request.setAttribute("modname", Controlador.getNameModulo());
	   	
	   	String Perfil_id=Controlador.getPerfilIDSession(request);
	   	
	   	request.setAttribute("usuname", Controlador.getUsunameSession(request));
	   	////////////////////////////////////////////////////////////
	   	//////////////////////////////////////////////////////////
	
	   	//VERIFICAMOS PERMISOS DE REPORTES 
	   	
	   	
	   	String p1=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "262");
	   	String p2=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "263");
	   	String p3=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "264");
	   	
	   	
		request.setAttribute("p1", p1);
		request.setAttribute("p2", p2);
		request.setAttribute("p3", p3);
		
		RequestDispatcher a = request.getRequestDispatcher("N025_R_01_001.jsp");
    	a.forward(request, response);
		
		
	}

}
