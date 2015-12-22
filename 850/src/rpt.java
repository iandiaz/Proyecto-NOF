

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

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
         String usuario="";
         Cookie [] cookies=request.getCookies();
 		
         if (cookies != null) {
 		    for (Cookie cookie : cookies) {
 		        if(cookie.getName().equals("Usuarios_nombre")) usuario=cookie.getValue();
 		    }
 		}
         
         System.out.println("inf select:"+request.getParameter("informe"));
         
 		
         //////////////////////////EXCEL 
         if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("1")){
 			PrintWriter out = response.getWriter();
 			String filtros="a=1";
 			if(request.getParameter("select_grupoperfiles") !=  null && !request.getParameter("select_grupoperfiles").equals("") ){
 				filtros+="&id_gp="+request.getParameter("select_grupoperfiles")+"";
 				
 			}
 			if(request.getParameter("select_tdoc") !=  null && !request.getParameter("select_tdoc").equals("") ){
				filtros+="&tdoc="+request.getParameter("select_tdoc")+"";
				
			}
			
 			if(request.getParameter("select_empresa_e") !=  null && !request.getParameter("select_empresa_e").equals("") ){
				filtros+="&id_eme="+request.getParameter("select_empresa_e")+"";
				
			}
 			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_850_1_xls.php?"+filtros+"';</script>");
 		}
         
 			
 		/////////////////////////////PDF 
 		
 		if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("1")){
 			PrintWriter out = response.getWriter();
 			String filtros="";
 			if(request.getParameter("select_grupoperfiles") !=  null && !request.getParameter("select_grupoperfiles").equals("") ){
 				filtros+="&id_gp="+request.getParameter("select_grupoperfiles")+"";
 				
 			}
 			if(request.getParameter("select_tdoc") !=  null && !request.getParameter("select_tdoc").equals("") ){
				filtros+="&tdoc="+request.getParameter("select_tdoc")+"";
				
			}
 			if(request.getParameter("select_empresa_e") !=  null && !request.getParameter("select_empresa_e").equals("") ){
				filtros+="&id_eme="+request.getParameter("select_empresa_e")+"";
				
			}
 			out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=850-1&usu="+usuario+filtros+"';</script>");
 		}
 		
 			
 		
    /////////////////////GENERAR POR WEB ////////////////////////
         
         if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("1")){
         	PrintWriter out = response.getWriter();
 			String filtros="";
 			if(request.getParameter("select_grupoperfiles") !=  null && !request.getParameter("select_grupoperfiles").equals("") ){
 				filtros+="&id_gp="+request.getParameter("select_grupoperfiles")+"";
 				
 			}
 			
 			if(request.getParameter("select_tdoc") !=  null && !request.getParameter("select_tdoc").equals("") ){
				filtros+="&tdoc="+request.getParameter("select_tdoc")+"";
				
			}
 			if(request.getParameter("select_empresa_e") !=  null && !request.getParameter("select_empresa_e").equals("") ){
				filtros+="&id_eme="+request.getParameter("select_empresa_e")+"";
				
			}
 			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_850_1_pdf.php?inf=850-1&usu="+usuario+filtros+"';</script>");
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
 		
 		
 		request.setAttribute("usuname", Controlador.getUsunameSession(request));
 		
 		//////////////////////////////////////////////////
 		
 		Statement state = null;
 		
 		ResultSet GRUPOS_RS=null;
 		ResultSet EMPRESAS_RS=null;
 		ResultSet ESTCLPR_RS=null;
 		try{
 		Class.forName("com.mysql.jdbc.Driver");
 		Connection conexion=(Connection) DriverManager.getConnection
 	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
 		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
 		
 		//::::::::::::::::::::::::::::::::::::::::::sql grupo perfiles::::::::::::::::::::::::::::::::::::::  
 		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
 		  String SQL_grupoperfiles = "SELECT * FROM gruposusu ORDER BY gruposusu_nombre";
 		  ResultSet GRUPOPERFILES_RS = state.executeQuery(SQL_grupoperfiles);		    
 		    //definimos un arreglo para los resultados		    
 		    ArrayList<String> grupoperfiles = new ArrayList<String>();		   
 		    //recorremos los resultados de la consulta
 		    while(GRUPOPERFILES_RS.next()){        	   
   	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	grupoperfiles.add(GRUPOPERFILES_RS.getString("gruposusu_id")+"/"+GRUPOPERFILES_RS.getString("gruposusu_nombre"));
   	    }
 		 
 		  String[] ar_grupoperfiles = new String[grupoperfiles.size()];
         for(int x=0; x < grupoperfiles.size(); x++){
         	ar_grupoperfiles[x]=grupoperfiles.get(x);
         }
               
         request.setAttribute("ar_grupoperfiles", ar_grupoperfiles);
 		
 		
 		
 		  //::::::::::::::::::::::::::::::::::::::::::sql grupos para select option::::::::::::::::::::::::::::::::::::::
 		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
 		  String SQL_GRUPOS = "SELECT * FROM grupos WHERE estados_vig_novig_id=1 ORDER BY grupos_nombre";
 		  System.out.println(SQL_GRUPOS);  	
 		    GRUPOS_RS =  state.executeQuery(SQL_GRUPOS);		    
 		    //definimos un arreglo para los resultados		    
 		    ArrayList<String> grupos = new ArrayList<String>();		   
 		    //recorremos los resultados de la consulta
 		    while(GRUPOS_RS.next()){        	   
      	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	grupos.add(GRUPOS_RS.getString("grupos_id")+"/"+GRUPOS_RS.getString("grupos_nombre").replace(" ", "&nbsp;"));
      	    }
 		  GRUPOS_RS.close();	
 		  state.close();
 		  String[] ar_grupos = new String[grupos.size()];
          for(int x=0; x < grupos.size(); x++){
        	  ar_grupos[x]=grupos.get(x);
          }
                
          request.setAttribute("ar_grupos", ar_grupos);
       //:::::::::::::::::::::::::: sql trae empresas para select option:::::::::::::::::::::::::::::::::::::
          state = (Statement) ((java.sql.Connection) conexion).createStatement();
 	    String SQL_EMPRESAS = "SELECT * FROM empresas WHERE estados_vig_novig_id=1 ORDER BY empresas_nombrenof";
 	    System.out.println(SQL_EMPRESAS);
 	    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
 	    //definimos un arreglo para los resultados
 	    ArrayList<String> empresas = new ArrayList<String>();
 	    //recorremos los resultados de la consulta
 	    while(EMPRESAS_RS.next()){        	   
     	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 	    	empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombrenof")+"/"+EMPRESAS_RS.getString("grupos_id"));    
 	    }
 	  EMPRESAS_RS.close();
 	  state.close();
 	  String[] ar_empresas = new String[empresas.size()];
       for(int x=0; x < empresas.size(); x++){
     	 ar_empresas[x]=empresas.get(x);
       }
             
       request.setAttribute("ar_empresas", ar_empresas);
   
 				 //:::::::::::::::::::::::::: sql trae empresas emisoras para select option:::::::::::::::::::::::::::::::::::::
 			      state = (Statement) ((java.sql.Connection) conexion).createStatement();
 				    String SQL_EMPRESAS_e = "SELECT * FROM empresas WHERE estados_vig_novig_id=1 AND empresas_relacionada=1 ORDER BY empresas_nombrenof";
 				    ResultSet EMPRESAS_E_RS = state.executeQuery(SQL_EMPRESAS_e);
 				    //definimos un arreglo para los resultados
 				    ArrayList<String> empresas_e = new ArrayList<String>();
 				    //recorremos los resultados de la consulta
 				    while(EMPRESAS_E_RS.next()){        	   
 			 	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 				    	empresas_e.add(EMPRESAS_E_RS.getString("empresas_id")+"/"+EMPRESAS_E_RS.getString("empresas_nombrenof"));    
 				    }
 				    EMPRESAS_E_RS.close();
 				  state.close();
 				  String[] ar_empresas_e = new String[empresas_e.size()];
 			   for(int x=0; x < empresas_e.size(); x++){
 				   ar_empresas_e[x]=empresas_e.get(x);
 			   }
 			         
 			   request.setAttribute("ar_empresas_emisor", ar_empresas_e);
 				
 				/////////////////////FIN///////////////////////////////
 				state.close();
 				conexion.close();
 			
 		        RequestDispatcher a = request.getRequestDispatcher("rpt.jsp");
 		    	a.forward(request, response);
 		}
 		catch(Exception e){
 			e.printStackTrace();
 		}
 		
 		
 	}

}
