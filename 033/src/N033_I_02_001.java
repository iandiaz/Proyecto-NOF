

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
import DAO.RecepcionesCompraDAO;
import VO.RecepcionCompraVO;

/**
 * Servlet implementation class N030_I_01_001
 */
@WebServlet("/N033_I_02_001")
public class N033_I_02_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public N033_I_02_001() {
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
		String id_proveedor=request.getParameter("id_proveedor");
		String id_comprador=request.getParameter("id_comprador");
		
		HttpSession session =request.getSession(true);
		
		RecepcionCompraVO rc=null; 
		if(request.getParameter("n") !=null && request.getParameter("n").equals("1")){
			rc = new RecepcionCompraVO();
			rc.setProveedor(EmpresasDAO.getEmpresa(id_proveedor));
			rc.setComprador(EmpresasDAO.getEmpresa(id_comprador));
			rc.setId(RecepcionesCompraDAO.getNewId()+"");
			
		}
		else{
			
			rc = (RecepcionCompraVO)session.getAttribute("rc");
			
			if(rc==null){
				rc = new RecepcionCompraVO();
				rc.setId(RecepcionesCompraDAO.getNewId()+"");
			}
		}
		
		session.setAttribute("rc", rc);
		request.setAttribute("rc", rc);
		
		RequestDispatcher rd = request.getRequestDispatcher("N"+Constantes.MODULO+"_I_02_001.jsp");
        rd.forward(request, response);
		
	}
	
	
	 
	
	

}
