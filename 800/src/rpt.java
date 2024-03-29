import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

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
        
		
        //////////////////////////EXCEL 
        
        
		if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="a=1";
			if(request.getParameter("f1") !=  null && !request.getParameter("f1").equals("") ){
				filtros+="&id_f1="+request.getParameter("f1")+"";
				
			}
            
            if(request.getParameter("f2") !=  null && !request.getParameter("f2").equals("") ){
				filtros+="&id_f2="+request.getParameter("f2")+"";
				
			}
            if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
		
			if(request.getParameter("select_empresa_e") !=  null && !request.getParameter("select_empresa_e").equals("") ){
				filtros+="&id_eme="+request.getParameter("select_empresa_e")+"";
				
			}
			if(request.getParameter("select_periodo") !=  null && !request.getParameter("select_periodo").equals("") ){
				filtros+="&id_per="+request.getParameter("select_periodo")+"";
				
			}
			if(request.getParameter("select_tdoc") !=  null && !request.getParameter("select_tdoc").equals("") ){
				filtros+="&tdoc="+request.getParameter("select_tdoc")+"";
				
			}
			
			
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_800_1_xls.php?"+filtros+"';</script>");
		}
		
		/////////////////////////////PDF 
		
		
		
		if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("f1") !=  null && !request.getParameter("f1").equals("") ){
				filtros+="&id_f1="+request.getParameter("f1")+"";
				
			}
            
            if(request.getParameter("f2") !=  null && !request.getParameter("f2").equals("") ){
				filtros+="&id_f2="+request.getParameter("f2")+"";
				
			}
            if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_empresa_e") !=  null && !request.getParameter("select_empresa_e").equals("") ){
				filtros+="&id_eme="+request.getParameter("select_empresa_e")+"";
				
			}
			if(request.getParameter("select_periodo") !=  null && !request.getParameter("select_periodo").equals("") ){
				filtros+="&id_per="+request.getParameter("select_periodo")+"";
				
			}
			if(request.getParameter("select_tdoc") !=  null && !request.getParameter("select_tdoc").equals("") ){
				filtros+="&tdoc="+request.getParameter("select_tdoc")+"";
				
			}
			
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=800-1&usu="+usuario+filtros+"';</script>");
		}
		
		
		
		
   /////////////////////GENERAR POR WEB ////////////////////////
        
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("1")){
        	PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("f1") !=  null && !request.getParameter("f1").equals("") ){
				filtros+="&id_f1="+request.getParameter("f1")+"";
				
			}
            
            if(request.getParameter("f2") !=  null && !request.getParameter("f2").equals("") ){
				filtros+="&id_f2="+request.getParameter("f2")+"";
				
			}
            if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_empresa_e") !=  null && !request.getParameter("select_empresa_e").equals("") ){
				filtros+="&id_eme="+request.getParameter("select_empresa_e")+"";
				
			}
			if(request.getParameter("select_periodo") !=  null && !request.getParameter("select_periodo").equals("") ){
				filtros+="&id_per="+request.getParameter("select_periodo")+"";
				
			}
			if(request.getParameter("select_tdoc") !=  null && !request.getParameter("select_tdoc").equals("") ){
				filtros+="&tdoc="+request.getParameter("select_tdoc")+"";
				
			}
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_800_1_pdf.php?inf=800-1&usu="+usuario+filtros+"';</script>");
		}
	}
	
public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		
		///////////////////////////////////////////
		//////DEFINE DESLOGEO//////////////////////
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			    	//System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
			
		}
		
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		
		String Usuarios_nombre="";
		
		
		
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		
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
		
		//::::::::::::::::::::::::::::::::::::::::::sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_estclpr = "SELECT * FROM estado_clpr WHERE estados_vig_novig_id=1 ORDER BY Estado_Clpr_nombre";
		    ESTCLPR_RS =  state.executeQuery(SQL_estclpr);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> estclpr = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(ESTCLPR_RS.next()){        	   
  	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estclpr.add(ESTCLPR_RS.getString("Estado_Clpr_id")+"/"+ESTCLPR_RS.getString("Estado_Clpr_nombre"));
  	    }
		  ESTCLPR_RS.close();
		  String[] ar_estclpr = new String[estclpr.size()];
        for(int x=0; x < estclpr.size(); x++){
      	  ar_estclpr[x]=estclpr.get(x);
        }
              
        request.setAttribute("ar_estclpr", ar_estclpr);
		
		
		
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
	    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
	    //definimos un arreglo para los resultados
	    ArrayList<String> empresas = new ArrayList<String>();
	    //recorremos los resultados de la consulta
	    while(EMPRESAS_RS.next()){        	   
    	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
	    	empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombrenof"));    
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
   //:::::::::::::::::::::::::: sql trae periodos emisoras para select option:::::::::::::::::::::::::::::::::::::
   state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    String SQL_PERIODOS = "SELECT "
	    		+ "		`periodos_tc`.`peri_tc_id`,"
	    		+ "		`periodos_tc`.`peri_tc_correlativo`,"
	    		+ "		`periodos_tc`.`peri_tc_fdesde`,"
	    		+ "		`periodos_tc`.`peri_tc_fhasta`, "
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
	    ResultSet PERIODOS_RS = state.executeQuery(SQL_PERIODOS);
	    //definimos un arreglo para los resultados
	    ArrayList<String> periodos = new ArrayList<String>();
	    //recorremos los resultados de la consulta
	    while(PERIODOS_RS.next()){        	   
	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
	    	periodos.add(PERIODOS_RS.getString("peri_tc_id")+"/"+PERIODOS_RS.getString("per_name"));    
	    }
	    PERIODOS_RS.close();
		  state.close();
		  String[] ar_periodos = new String[periodos.size()];
		for(int x=0; x < periodos.size(); x++){
			ar_periodos[x]=periodos.get(x);
		}
      
request.setAttribute("ar_select_periodo", ar_periodos);
      
      //::::::::::::::::::::::::::::::::::::::::::sql usuarios internos para responsable select option::::::::::::::::::::::::::::::::::::::  
	  state = (Statement) ((java.sql.Connection) conexion).createStatement();
	  String SQL_resp = "SELECT `contacto`.`CONT_ID`, CONCAT_WS(' ',`contacto`.`CONT_NOMBRE`,`contacto`.`CONT_APEP`,`contacto`.`CONT_APEM`) as Usuarios_nombre "
	  		+ "	FROM"
	  		+ "		`contacto`"
	  		+ "	ORDER BY `contacto`.`CONT_NOMBRE` ";
	  
	    ResultSet resp_RS = state.executeQuery(SQL_resp);		    
	    //definimos un arreglo para los resultados		    
	    ArrayList<String> responsables = new ArrayList<String>();		   
	    //recorremos los resultados de la consulta
	    while(resp_RS.next()){        	   
	    	responsables.add(resp_RS.getString("CONT_ID")+"/"+resp_RS.getString("Usuarios_nombre"));
	    }
	  String[] ar_responsables = new String[responsables.size()];
      for(int x=0; x < responsables.size(); x++){
    	  ar_responsables[x]=responsables.get(x);
      }
            
      request.setAttribute("ar_responsables", ar_responsables);
	        
		conexion.close();
        RequestDispatcher a = request.getRequestDispatcher("rpt.jsp");
    	a.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
