

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
import DAO.AnexoContratoDAO;
import DAO.UbicacionesDAO;
import VO.AnexoContratoVO;

/**
 * Servlet implementation class C02
 */
@WebServlet("/C02")
public class C02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C02() {
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

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PCONSULTAR);
		
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

		
		try {
			
			
			
    		String id_anexo=request.getParameter("anexo");
    		
    		//------------------------- ANEXO CONTRATO -------------------------//
    	
    		AnexoContratoVO anexoContrato = new AnexoContratoVO();
    		
    		anexoContrato.setAnc_id(id_anexo);
    		
    		AnexoContratoDAO.getAnexoContrato(anexoContrato);
    		
    		anexoContrato.setMaquinasContadores(UbicacionesDAO.getUbicacionesConAnexo(id_anexo,null));
	    	
    		request.setAttribute("anexoContrato", anexoContrato);
    	    //-------------------------NOW-------------------------//
            
            
            //----------------------- FIN ------------------------//
           
		}catch(Exception ex){
		    out.println("Error "+ex);
		    ex.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("C02.jsp");
        rd.forward(request, response);
		
	}

}
