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
 * Servlet implementation class Iclpr
 */
@WebServlet("/Iclpr")
public class Iclpr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Iclpr() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Controlador.validateSession(request,response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(Controlador.validateSession(request,response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}
	
public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();		
		
		// logout
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	 System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
		}
		//fin logout
		
		//calendario
		
		String Usuarios_nombre="";
		
		//fecha 
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String Datenow=ahoraCal.get(Calendar.YEAR)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.DATE)+" "+ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String U_ID="";
		//System.out.print(Datenow);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) { U_ID=cookie.getValue();}
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		//System.out.print("Usuarios_ID: "+Usuarios_ID);
		// end calendario
		
		
		
		
		//grabar
		boolean existeclpr = false;
		boolean existeclpr2 = false;
		boolean existeclpr3 = false;
		boolean existeclpr4 = false;
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			Statement stateval2 = null;
			Statement stateval3= null;
			Statement stateval4= null;
			ResultSet rs_valclpr= null;
			ResultSet rs_valclpr2= null;
			ResultSet rs_valclpr3= null;
			ResultSet rs_valclpr4= null;
		
			try {
				String empresas_esproveedor="0";
				String empresas_escliente = "0";
				String empresas_esprospeccion="0";
				String empresas_relacionada="0";
				
				if(request.getParameter("empresas_escliente")!=null && request.getParameter("empresas_escliente").equals("1")) empresas_escliente="1";
		    	if(request.getParameter("empresas_esproveedor")!=null && request.getParameter("empresas_esproveedor").equals("1")) empresas_esproveedor="1";
		    	if(request.getParameter("empresas_esprospeccion")!=null && request.getParameter("empresas_esprospeccion").equals("1")) empresas_esprospeccion="1";
		    	if(request.getParameter("empresas_relacionada")!=null && request.getParameter("empresas_relacionada").equals("1")) empresas_relacionada="1";
		    	
		    	
				String empresas_nombre = request.getParameter("empresas_nombre");
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
		    	String grupos_id = request.getParameter("grupos_id");
		    	String Estado_Clpr_id = request.getParameter("Estado_Clpr_id");
		    	String empresas_rut = request.getParameter("empresas_rut");
		    	String empresas_razonsocial = request.getParameter("empresas_razonsocial");
		    	String empresas_nombrenof = request.getParameter("empresas_nombrenof");
		    	String empresas_giro = request.getParameter("empresas_giro");
		    	String empresas_web = request.getParameter("empresas_web");
		    	String empresas_email = request.getParameter("empresas_email");
		    	String responsable_id = request.getParameter("responsable_id");
		    	
		    	
		    	
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		
	    		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval2 = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval3 = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval4 = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String sql_valgrupo="SELECT * FROM empresas WHERE empresas_nombrenof='"+empresas_nombrenof.toUpperCase()+"'";	    		
	    		rs_valclpr=stateval.executeQuery(sql_valgrupo);
	    		
	    		String sql_valgrupo2="SELECT * FROM empresas WHERE empresas_razonsocial='"+empresas_razonsocial.toUpperCase()+"'";
	    		rs_valclpr2=stateval2.executeQuery(sql_valgrupo2);
	    		
	    		String sql_valgrupo3="SELECT * FROM empresas WHERE empresas_nombre='"+empresas_nombre.toUpperCase()+"'";
	    		rs_valclpr3=stateval3.executeQuery(sql_valgrupo3);
	    		
	    		String sql_valgrupo4="SELECT * FROM empresas WHERE empresas_rut='"+empresas_rut.replace(".", "") +"'";
	    		rs_valclpr4=stateval4.executeQuery(sql_valgrupo4);
	    		if(rs_valclpr.next()){	    			
	    			//devuelveVariables(request,response,empresas_esproveedor,empresas_escliente,empresas_esprospeccion,empresas_nombre,estados_vig_novig_id,grupos_id,Estado_Clpr_id,empresas_rut,empresas_razonsocial,empresas_nombrenof,empresas_giro,empresas_web); // este metodo hace los setAttribute
	    			existeclpr = true;
	    			//response.sendRedirect("Iclpr?Exito=NOK1");
	    			//return;
	    		}if(rs_valclpr2.next()){	    			
	    			rs_valclpr.close();rs_valclpr2.close(); stateval.close(); stateval2.close(); conexion.close();	    			
	    			//devuelveVariables(request,response,empresas_esproveedor,empresas_escliente,empresas_esprospeccion,empresas_nombre,estados_vig_novig_id,grupos_id,Estado_Clpr_id,empresas_rut,empresas_razonsocial,empresas_nombrenof,empresas_giro,empresas_web); // este metodo hace los setAttribute
	    			existeclpr2 = true;
	    			//response.sendRedirect("Iclpr?Exito=NOK2");
	    			//return;
	    		}if(rs_valclpr3.next()){	    			
	    			//devuelveVariables(request,response,empresas_esproveedor,empresas_escliente,empresas_esprospeccion,empresas_nombre,estados_vig_novig_id,grupos_id,Estado_Clpr_id,empresas_rut,empresas_razonsocial,empresas_nombrenof,empresas_giro,empresas_web); // este metodo hace los setAttribute
	    			existeclpr3 = true;
	    			//response.sendRedirect("Iclpr?Exito=NOK3");
	    			//return;
	    		}if(rs_valclpr4.next()){	    			
	    			//devuelveVariables(request,response,empresas_esproveedor,empresas_escliente,empresas_esprospeccion,empresas_nombre,estados_vig_novig_id,grupos_id,Estado_Clpr_id,empresas_rut,empresas_razonsocial,empresas_nombrenof,empresas_giro,empresas_web); // este metodo hace los setAttribute
	    			existeclpr4 = true;
	    			//response.sendRedirect("Iclpr?Exito=NOK4");
	    			//return;
	    		 }if(!existeclpr && !existeclpr2 && !existeclpr3 && !existeclpr4){
	    			 
	    			 //insertamos en birt 
	    			 
	    			 if(Constantes.SYNCDB==1){
	        			 ConBirt birtBD= new ConBirt();
	        			 
	        			 
	        			 String relacion="";
	        			 if(empresas_escliente.equals("1")) relacion="CLIENTE";
	        			 if(empresas_esproveedor.equals("1")) relacion="PROVEEDOR";
	        			 if(empresas_esproveedor.equals("1") && empresas_escliente.equals("1")) relacion="CLIENTE/PROVEEDOR";
	        			 
	        			 String[] rut_birt_ar=empresas_rut.replace(".", "").split("-");
	        			 
	        			 String empresas_email_birt=empresas_email;
	        			 
	        			 if(empresas_email_birt.length()>40){
	        				 empresas_email_birt=empresas_email_birt.substring(0,39);
	        			 }
	        			 
	        			 	String Estado_Clpr_id_birt=Estado_Clpr_id;
	        			 
	        			 if(Estado_Clpr_id.equals("7")){Estado_Clpr_id_birt="1";}
	        			 String insert_birt="INSERT INTO [CLIENTEPROVEEDOR] ("
	        			 		+ "		[CLIENTEPROVEEDOR].[CLPR_NOMBRE_FANTASIA], "
	        			 		+ "		[CLIENTEPROVEEDOR].[CLPR_RAZON_SOCIAL], "
	        			 		+ "		[CLIENTEPROVEEDOR].[CLPR_RUT],"
	        			 		+ "		[CLIENTEPROVEEDOR].[CLPR_DV],"
	        			 		+ "		[CLIENTEPROVEEDOR].[CLPR_WEB], "
	        			 		+ "		[CLIENTEPROVEEDOR].[GRUP_ID],"
	        			 		+ "		[CLIENTEPROVEEDOR].[ESTCLPR_ID],"
	        			 		+ "		[CLIENTEPROVEEDOR].[CLPR_GIRO],"
	        			 		+ "		[CLIENTEPROVEEDOR].[CLPR_EMAIL],"
	        			 		+ "		[CLIENTEPROVEEDOR].[USU_ID_UM],"
	        			 		+ "		[CLIENTEPROVEEDOR].[CLPR_FECHA_UM],"
	        			 		+ "		[CLIENTEPROVEEDOR].[CLPR_RELACION] "
	        			 		+ "	)"
	        			 		+ "	VALUES"
	        			 		+ "	( '"+empresas_nombrenof.toUpperCase()+"','"+empresas_razonsocial.toUpperCase()+"', '"+rut_birt_ar[0]+"','"+rut_birt_ar[1]+"','"+empresas_web.toUpperCase()+"',"+grupos_id+", "+Estado_Clpr_id_birt+", '"+empresas_giro.toUpperCase()+"', '"+empresas_email_birt+"', "+U_ID+", getdate(),'"+relacion+"');";
	        			 System.out.println("BIRT: "+insert_birt);
	        		      birtBD.ConsultaINUP(insert_birt);
	        		     
	        		      
	        		        birtBD.Desconectar();
	        			}
	    			 
	    			 //insertamos en nof
			    String SQL_INSERT = "INSERT INTO empresas "
			    		+ "(empresas_nombre, grupos_id, Estado_Clpr_id,empresas_accion_alertada,empresas_ult_idper_exec,estados_vig_novig_id,empresas_rut,"
			    		+ "empresas_escliente,empresas_esproveedor,empresas_razonsocial,empresas_nombrenof,"
			    		+ "empresas_giro,empresas_web, empresas_feccreacion, empresas_creador,empresas_esprospeccion,empresas_email,empresas_relacionada,responsable_id) "
			    		+ "VALUES('"+empresas_nombre.toUpperCase()+"',"+grupos_id+","+Estado_Clpr_id+""
			    		+ ",0,16,"+estados_vig_novig_id+","
			    		+ "'"+empresas_rut.replace(".", "")+"',"+empresas_escliente+","+empresas_esproveedor+""
			    		+ ",'"+empresas_razonsocial.toUpperCase()+"','"+empresas_nombrenof.toUpperCase()+"'"
			    		+ ", '"+empresas_giro.toUpperCase()+"','"+empresas_web.toUpperCase()+"','"+Datenow+"',"+U_ID+","+empresas_esprospeccion+",'"+empresas_email+"','"+empresas_relacionada+"','"+responsable_id+"')";
			    System.out.print("SQL_INSERT empresas : "+SQL_INSERT+"\n");
			    stategrabar.executeUpdate(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
			    ResultSet generatedKeys = null;
	    		  generatedKeys = stategrabar.getGeneratedKeys();
	    		  String id_emp_last="";
	    		  if (generatedKeys.next()) {
	    			  id_emp_last=generatedKeys.getString(1);
	    		  }
	    		  
	    		//asociamos la empresa a todos los usuarios que tengan asignadas todas las empresas
	    		  
	    		  	//buscamos usuarios que tengan asociadas todas las empresas
	    		  
	    		  
	    		  Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
	  		    String SQL_USERS_ALL_E = "SELECT `usuarios`.`Usuarios_id` FROM 	`usuarios` WHERE	`usuarios`.`Usuarios_all_emp` = 1";
	  		    System.out.println(SQL_USERS_ALL_E);
	  		    ResultSet USERS_ALL_E_RS = state.executeQuery(SQL_USERS_ALL_E);
	  		    //recorremos los resultados de la consulta
	  		    while(USERS_ALL_E_RS.next()){        	   
	          	    String insert ="INSERT INTO `usuarios_has_empresas` (`usuarios_has_empresas`.`empresas_id`,`usuarios_has_empresas`.`estados_vig_novig_id`,`usuarios_has_empresas`.`Usuarios_id`)"
	          	    		+ "	VALUES ('"+id_emp_last+"',1,'"+USERS_ALL_E_RS.getString("Usuarios_id")+"')";
	          	    System.out.println(insert);
	          	  stategrabar.executeUpdate(insert);
	      	    }
	    		  
			    stategrabar.close();
			    rs_valclpr.close();rs_valclpr2.close(); rs_valclpr3.close();
			    conexion.close();
	            RequestDispatcher rd_up = request.getRequestDispatcher("menuclpr?Exito=OK");
	            rd_up.forward(request, response);
	    		}
			}catch(Exception ex){
			    out.println("ERROR "+ex);
			    ex.printStackTrace();
			}
			if(!existeclpr && !existeclpr2 && !existeclpr3 && !existeclpr4)return;
		}
		
		//fin grabar
		
		
		
		Statement state = null;
		ResultSet ESTADOS_RS = null;
		ResultSet GRUPOS_RS = null;
		ResultSet ESTCLPR_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    	//:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    //definimos un arreglo para los resultados
		    ArrayList<String> estados = new ArrayList<String>();
		    //recorremos los resultados de la consulta
		    while(ESTADOS_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));    
    	    }
		  ESTADOS_RS.close();
		  state.close();
		  String[] ar_estados = new String[estados.size()];
          for(int x=0; x < estados.size(); x++){
           	ar_estados[x]=estados.get(x);
          }
                
          request.setAttribute("ar_estados", ar_estados);
		//::::::::::::::::::::::::::fin sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
		  
		  //::::::::::::::::::::::::::::::::::::::::::sql grupos para select option::::::::::::::::::::::::::::::::::::::
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_GRUPOS = "SELECT * FROM grupos WHERE estados_vig_novig_id=1";
		    GRUPOS_RS =  state.executeQuery(SQL_GRUPOS);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> grupos = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(GRUPOS_RS.next()){        	   
      	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	grupos.add(GRUPOS_RS.getString("grupos_id")+"/"+GRUPOS_RS.getString("grupos_nombre"));
      	    }
		  GRUPOS_RS.close();	
		  state.close();
		  String[] ar_grupos = new String[grupos.size()];
          for(int x=0; x < grupos.size(); x++){
        	  ar_grupos[x]=grupos.get(x);
          }
                
          request.setAttribute("ar_grupos", ar_grupos);
		//::::::::::::::::::::::::::::::::::::::::fin sql grupos para select option::::::::::::::::::::::::::::::::::::::
        //::::::::::::::::::::::::::::::::::::::::::sql usuarios internos para responsable select option::::::::::::::::::::::::::::::::::::::  
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_resp = "SELECT "
		  		+ "		`usuarios`.`Usuarios_id`,"
		  		+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre "
		  		+ "	FROM"
		  		+ "		`usuarios`"
		  		+ "	WHERE "
		  		+ "		`usuarios`.`tipo_usu_id` = 1 AND `usuarios`.`estados_vig_novig_id`=1 "
		  		+ "	ORDER BY `usuarios`.`Usuarios_nombre` ";
		  	System.out.println(SQL_resp);
		    ResultSet resp_RS = state.executeQuery(SQL_resp);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> responsables = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(resp_RS.next()){        	   
    	    	responsables.add(resp_RS.getString("Usuarios_id")+"/"+resp_RS.getString("Usuarios_nombre"));
    	    }
		  String[] ar_responsables = new String[responsables.size()];
          for(int x=0; x < responsables.size(); x++){
        	  ar_responsables[x]=responsables.get(x);
          }
                
          request.setAttribute("ar_responsables", ar_responsables);
		//::::::::::::::::::::::::::::::::::::::::::sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_estclpr = "SELECT * FROM estado_clpr WHERE estados_vig_novig_id=1";
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
		//::::::::::::::::::::::::::::::::::::::::::fin sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
		  state.close();
		  
		//id cor
  		Statement statecor = null;
  		ResultSet CORRELATIVO_RS = null;
  		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
  		 String last_id_grupos_sql="SELECT"
  		    		+ " 	`empresas`.`empresas_id`"
  		    		+ " FROM"
  		    		+ " 	`empresas`"
  		    		+ " ORDER BY"
  		    		+ " 	`empresas`.`empresas_id` DESC"
  		    		+ " LIMIT 1";
         System.out.println(""+last_id_grupos_sql);
         CORRELATIVO_RS =  statecor.executeQuery(last_id_grupos_sql);
        
         int correlativo=0;
  		  if(CORRELATIVO_RS.next()) correlativo=CORRELATIVO_RS.getInt("empresas_id")+1;
  		  //System.out.println("correlativo : no pasa na2 "+correlativo);
         request.setAttribute("correlativo", correlativo+"");
         
         statecor.close();		  
          conexion.close();
                
          
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("ERROR: "+ex);
		}
		
		String msg="";
		if(existeclpr){
			msg="?Exito=NOK1";
		}
		if(existeclpr2){
			msg="?Exito=NOK2";
		}
		if(existeclpr3){
			msg="?Exito=NOK3";
		}
		if(existeclpr4){
			msg="?Exito=NOK4";
		}
		if(existeclpr || existeclpr2 || existeclpr3 || existeclpr4){
			
			String empresas_esproveedor="0";
			String empresas_escliente = "0";
			String empresas_esprospeccion = "0";
			if(request.getParameter("empresas_escliente")!=null && request.getParameter("empresas_escliente").equals("1")) empresas_escliente="1";
	    	if(request.getParameter("empresas_esproveedor")!=null && request.getParameter("empresas_esproveedor").equals("1")) empresas_esproveedor="1";
	    	if(request.getParameter("empresas_esprospeccion")!=null && request.getParameter("empresas_esprospeccion").equals("1")) empresas_esprospeccion="1";
	    	String empresas_nombre = request.getParameter("empresas_nombre");
	    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
	    	String grupos_id = request.getParameter("grupos_id");
	    	String Estado_Clpr_id = request.getParameter("Estado_Clpr_id");
	    	String empresas_rut = request.getParameter("empresas_rut");
	    	String empresas_razonsocial = request.getParameter("empresas_razonsocial");
	    	String empresas_nombrenof = request.getParameter("empresas_nombrenof");
	    	String empresas_giro = request.getParameter("empresas_giro");
	    	String empresas_web = request.getParameter("empresas_web");
			
			request.setAttribute("empresas_nombre",empresas_nombre);
	    	request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
	    	request.setAttribute("grupos_id",grupos_id);
	    	request.setAttribute("Estado_Clpr_id",Estado_Clpr_id);
	    	request.setAttribute("empresas_rut",empresas_rut);
	    	request.setAttribute("empresas_escliente",empresas_escliente);
	    	request.setAttribute("empresas_esproveedor",empresas_esproveedor);
	    	request.setAttribute("empresas_razonsocial",empresas_razonsocial);
	    	request.setAttribute("empresas_nombrenof",empresas_nombrenof);
	    	request.setAttribute("empresas_giro",empresas_giro);
	    	request.setAttribute("empresas_web",empresas_web);
	    	request.setAttribute("empresas_esprospeccion",empresas_esprospeccion);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Iclpr.jsp"+msg);
        rd.forward(request, response);
		
	}

//HttpServletRequest request, HttpServletResponse response
public void devuelveVariables(HttpServletRequest request, HttpServletResponse response,String empresas_esproveedor,String empresas_escliente,String empresas_esprospeccion,String empresas_nombre,String estados_vig_novig_id,String grupos_id,String Estado_Clpr_id,String empresas_rut,String empresas_razonsocial,String empresas_nombrenof,String empresas_giro,String empresas_web){
	System.out.println("devuelveVariables: creando setAtribute");
	
	System.out.println(empresas_nombre+" "+estados_vig_novig_id+" "+empresas_escliente);
	request.setAttribute("empresas_nombre",empresas_nombre);
	request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
	request.setAttribute("grupos_id",grupos_id);
	request.setAttribute("Estado_Clpr_id",Estado_Clpr_id);
	request.setAttribute("empresas_rut",empresas_rut);
	request.setAttribute("empresas_escliente",empresas_escliente);
	request.setAttribute("empresas_esproveedor",empresas_esproveedor);
	request.setAttribute("empresas_razonsocial",empresas_razonsocial);
	request.setAttribute("empresas_nombrenof",empresas_nombrenof);
	request.setAttribute("empresas_giro",empresas_giro);
	request.setAttribute("empresas_web",empresas_web);
	request.setAttribute("empresas_esprospeccion",empresas_esprospeccion);
}


}
