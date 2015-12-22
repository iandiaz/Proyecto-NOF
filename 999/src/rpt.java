

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
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_999_1_xls.php?"+filtros+"';</script>");
		}
        
		if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("2")){
			PrintWriter out = response.getWriter();
			String filtros="a=1";
			if(request.getParameter("select_grupoperfiles") !=  null && !request.getParameter("select_grupoperfiles").equals("") ){
				filtros+="&id_gp="+request.getParameter("select_grupoperfiles")+"";
				
			}
			
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_999_2_xls.php?"+filtros+"';</script>");
		}
			
		/////////////////////////////PDF 
		
		if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupoperfiles") !=  null && !request.getParameter("select_grupoperfiles").equals("") ){
				filtros+="&id_gp="+request.getParameter("select_grupoperfiles")+"";
				
			}
			out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=999-1&usu="+usuario+filtros+"';</script>");
		}
		
		if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("2")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupoperfiles") !=  null && !request.getParameter("select_grupoperfiles").equals("") ){
				filtros+="&id_gp="+request.getParameter("select_grupoperfiles")+"";
				
			}
			out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=999-2&usu="+usuario+filtros+"';</script>");
		}
			
		
   /////////////////////GENERAR POR WEB ////////////////////////
        
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("1")){
        	PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupoperfiles") !=  null && !request.getParameter("select_grupoperfiles").equals("") ){
				filtros+="&id_gp="+request.getParameter("select_grupoperfiles")+"";
				
			}
			
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_999_1_pdf.php?inf=999-1&usu="+usuario+filtros+"';</script>");
		}
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("2")){
        	PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupoperfiles") !=  null && !request.getParameter("select_grupoperfiles").equals("") ){
				filtros+="&id_gp="+request.getParameter("select_grupoperfiles")+"";
				
			}
			
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_999_2_pdf.php?inf=999-2&usu="+usuario+filtros+"';</script>");
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
   //:::::::::::::::::::::::::: sql trae periodos emisoras para select option:::::::::::::::::::::::::::::::::::::
   state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    String SQL_PERIODOS = "SELECT "
	    		+ "		`periodos_tc`.`peri_tc_id`,"
	    		+ "		`periodos_tc`.`peri_tc_correlativo`,"
	    		+ "		`periodos_tc`.`peri_tc_fdesde`,"
	    		+ "		`periodos_tc`.`peri_tc_fhasta`, "
	    		+ "		`periodos_tc`.`peri_tc_id_emp`, "
	    		+ "		CONCAT_WS("
	    		+ "			' ',"
	    		+ "			`periodos_tc`.`peri_tc_correlativo`,"
	    		+ "			DATE_FORMAT(`periodos_tc`.`peri_tc_fdesde`,'%d-%m-%Y'),"
	    		+ "			DATE_FORMAT(`periodos_tc`.`peri_tc_fhasta`,'%d-%m-%Y') "
	    		+ "		) AS per_name"
	    		+ "	FROM"
	    		+ "		`periodos_tc`"
	    		+ "	WHERE"
	    		+ "		`periodos_tc`.`estados_vig_novig_id` = 1";
	    System.out.println(SQL_PERIODOS);
	    
	    ResultSet PERIODOS_RS = state.executeQuery(SQL_PERIODOS);
	    //definimos un arreglo para los resultados
	    ArrayList<String> periodos = new ArrayList<String>();
	    //recorremos los resultados de la consulta
	    while(PERIODOS_RS.next()){        	   
	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
	    	periodos.add(PERIODOS_RS.getString("peri_tc_id")+"/"+PERIODOS_RS.getString("per_name")+"/"+PERIODOS_RS.getString("peri_tc_id_emp"));    
	    }
	    PERIODOS_RS.close();
		  state.close();
		  String[] ar_periodos = new String[periodos.size()];
		for(int x=0; x < periodos.size(); x++){
			ar_periodos[x]=periodos.get(x);
		}
      
		request.setAttribute("ar_select_periodo", ar_periodos);
      
      //::::::::::::::::::::::::::::::::::::::::::sql usuarios internos para responsable select option::::::::::::::::::::::::::::::::::::::
		//:::::::::::::::::::::::::: sql trae estados periodos para select option:::::::::::::::::::::::::::::::::::::
		   state = (Statement) ((java.sql.Connection) conexion).createStatement();
			    String SQL_ESTPERIODOS = ""
			    		+ "	SELECT"
			    		+ "		`estado_periodo`.`estado_periodo_id`,"
			    		+ "		`estado_periodo`.`estado_periodo_nombre`"
			    		+ "	FROM"
			    		+ "		`estado_periodo`"
			    		+ "	ORDER BY"
			    		+ "		`estado_periodo`.`estado_periodo_orden`";
			    System.out.println(SQL_ESTPERIODOS);
			    ResultSet ESTPERIODOS_RS = state.executeQuery(SQL_ESTPERIODOS);
			    //definimos un arreglo para los resultados
			    ArrayList<String> estperiodos = new ArrayList<String>();
			    //recorremos los resultados de la consulta
			    while(ESTPERIODOS_RS.next()){        	   
			    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
			    	estperiodos.add(ESTPERIODOS_RS.getString("estado_periodo_id")+"/"+ESTPERIODOS_RS.getString("estado_periodo_nombre"));    
			    }
			    ESTPERIODOS_RS.close();
				  
				  String[] ar_estperiodos = new String[estperiodos.size()];
				for(int x=0; x < estperiodos.size(); x++){
					ar_estperiodos[x]=estperiodos.get(x);
				}
		      
				request.setAttribute("ar_estperiodos", ar_estperiodos);
				
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
