

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
import DAO.EmpresasDAO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;

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
		
		
		EmpresaVO emp = new EmpresaVO();
		emp.setEmpresas_escliente("1");
		
		EstadosVigNoVigVO estado = new EstadosVigNoVigVO();
		estado.setId("1");
		
		emp.setEstadoVigNoVig(estado);
		
		request.setAttribute("empresas", EmpresasDAO.getEmpresas(emp));
		
		RequestDispatcher rd = request.getRequestDispatcher("I01.jsp");
        rd.forward(request, response);
		
        
        
	}

}
