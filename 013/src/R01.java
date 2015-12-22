

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
import DAO.GruposDAO;
import VO.EmpresaVO;
import VO.GrupoVO;

/**
 * Servlet implementation class R01
 */
@WebServlet("/R01")
public class R01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R01() {
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
		  //guardams el usuario qeu esta haciendo el reporte
        String usuario=Controlador.getUsunameSession(request);
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("1")){
            PrintWriter out = response.getWriter();
			String filtros="";
            
            if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            
            out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_013_1_pdf.php?inf=013-1&usu="+usuario+filtros+"';</script>");
        }
	}
	
	private void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = response.getWriter();		
		
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
		
		//////////////////////////////////////////////////
		////////VERIFICAMOS PERMISO ASOCIADO/////////////

		String p240=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "240");
		
		if(p240.equals("0")){
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
		
		
		
		request.setAttribute("empresas", EmpresasDAO.getEmpresas(new EmpresaVO()));
		
		request.setAttribute("grupos", GruposDAO.getGrupos(new GrupoVO()));
		
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("R01.jsp");
        rd.forward(request, response);
	}


}
