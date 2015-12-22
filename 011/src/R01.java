

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
import Constantes.Controlador;
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
		
		 //guardams el usuario qeu esta haciendo el reporte
       String usuario="";
       Cookie [] cookies=request.getCookies();
		
       if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if(cookie.getName().equals("Usuarios_nombre")) usuario=cookie.getValue();
		    }
		}
       
       /////////////////////GENERAR POR WEB ////////////////////////
       
       if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
           if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
           if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
          
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_011_1_pdf.php?inf=010-1&usu="+usuario+filtros+"';</script>");
		}
       if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("2")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
           if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
           if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
         
           out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_011_2_pdf.php?inf=010-2&usu="+usuario+filtros+"';</script>");
		}
       if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("3")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
           if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
           if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
         
           out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_011_3_pdf.php?inf=010-3&usu="+usuario+filtros+"';</script>");
		}
       if(request.getParameter("informe").equals("4")){
			
           	RequestDispatcher a = request.getRequestDispatcher("R_GEN4");
      		a.forward(request, response);
      		return;
          
		}
      
       
       
 /////////////////////GENERAR POR EXCEL ////////////////////////
       
       if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
           if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
           if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
          
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_011_1_xls.php?inf=010-1&usu="+usuario+filtros+"';</script>");
		}
       if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("2")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
           if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
           if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
           
           out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_011_2_xls.php?inf=010-2&usu="+usuario+filtros+"';</script>");
		}
       if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("3")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
           if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
           if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
           
           out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_011_3_xls.php?inf=010-3&usu="+usuario+filtros+"';</script>");
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
	
	   	//VERIFICAMOS PERMISOS DE REPORTES 
	   	
	   	
	   	String p117=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "117");
		String p118=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "118");
		String p119=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "119");
		String p252=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "252");
		request.setAttribute("p117", p117);
		request.setAttribute("p118", p118);
		request.setAttribute("p119", p119);
		request.setAttribute("p252", p252);
		
		RequestDispatcher a = request.getRequestDispatcher("R01.jsp");
    	a.forward(request, response);
		
		
	}

}
