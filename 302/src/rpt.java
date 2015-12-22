

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class rpt
 */
@WebServlet("/rpt")
public class rpt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rpt() {
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
        String usuario=Controlador.getUsunameSession(request);
        
		if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("1") ){
			PrintWriter out = response.getWriter();
			String filtros="a=1";
			
			//out.write("<script>window.open(\"www.google.com\");</script>");
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_302_1_xls.php?"+filtros+"';</script>");
		}
		 
        /////////////////////GENERAR POR PDF ////////////////////////
        
		if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="";
			
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/generaPDF.php?inf=302-1&usu="+usuario+filtros+"';</script>");
		}
        
        /////////////////////GENERAR POR WEB ////////////////////////
        
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="";
			
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_302_1_pdf.php?inf=302-1&usu="+usuario+filtros+"';</script>");
		}
        
       
        
        
		
	}
public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		
		///////////////////////////////////////////
		//////DEFINE DESLOGEO//////////////////////
		
		if(request.getParameter("logout") !=  null){
			Controlador.eraseCookie(request, response);
			response.sendRedirect("/001/");	
			return;
		}
		
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		
		
		request.setAttribute("modname", Controlador.getNameModulo(Constantes.MODULO));
		
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		
		//////////////////////////////////////////////////
		
		
		
		try{
	
		
		
		
		 
     
     
     
    
            
     
     
		
        RequestDispatcher a = request.getRequestDispatcher("rpt.jsp");
    	a.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	

}
