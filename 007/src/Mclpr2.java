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
 * Servlet implementation class Mclpr2
 */
@WebServlet("/Mclpr2")
public class Mclpr2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mclpr2() {
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
		
		String Usuarios_nombre="";
		
		//fecha 
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String Datenow=ahoraCal.get(Calendar.YEAR)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.DATE)+" "+ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String U_ID="";
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) { U_ID=cookie.getValue();}
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		
		//grabar
		boolean existeclpr = false;
		boolean existeclpr2 = false;
		boolean existeclpr3 = false;
		boolean existeclpr4 = false;
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet rs_valclpr= null;
			Statement stateval2 = null;
			ResultSet rs_valclpr2= null;
			Statement stateval3 = null;
			ResultSet rs_valclpr3= null;
			Statement stateval4 = null;
			ResultSet rs_valclpr4= null;
			try {
				String empresas_esproveedor="0";
				String empresas_escliente = "0";
				String empresas_esprospeccion = "0";
				String empresas_relacionada = "0";
				
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
	    		 stateval4= (Statement) ((java.sql.Connection) conexion).createStatement();
		    		
			    String sql_valclpr="SELECT * FROM empresas WHERE empresas_nombrenof='"+empresas_nombrenof.toUpperCase()+"' AND empresas_id<>"+request.getParameter("empresas_id");
			    System.out.println("sql_valclpr: "+sql_valclpr);
			    rs_valclpr=stateval.executeQuery(sql_valclpr);
			    
			    String sql_valclpr2="SELECT * FROM empresas WHERE empresas_razonsocial='"+empresas_razonsocial.toUpperCase()+"' AND empresas_id<>"+request.getParameter("empresas_id");
			    System.out.println("sql_valclpr2: "+sql_valclpr2);
			    rs_valclpr2=stateval2.executeQuery(sql_valclpr2);
			    
			    String sql_valclpr3="SELECT * FROM empresas WHERE empresas_nombre='"+empresas_nombre.toUpperCase()+"' AND empresas_id<>"+request.getParameter("empresas_id");
			    System.out.println("sql_valclpr3: "+sql_valclpr3);
			    rs_valclpr3=stateval3.executeQuery(sql_valclpr3);
			    
			    String sql_valclpr4="SELECT * FROM empresas WHERE empresas_rut='"+empresas_rut.replace(".", "")+"' AND empresas_id<>"+request.getParameter("empresas_id");
			    System.out.println("sql_valclpr4: "+sql_valclpr4);
			    rs_valclpr4=stateval4.executeQuery(sql_valclpr4);
			    
			    if(rs_valclpr.next()){
			    	stateval.close();
			    	rs_valclpr.close();conexion.close();
			    	existeclpr=true;
		    		
			    }if(rs_valclpr2.next()){
			    	stateval.close(); stateval2.close();
			    	rs_valclpr.close(); rs_valclpr2.close();conexion.close();
			    	existeclpr2=true;
		    		
			    }if(rs_valclpr3.next()){
			    	stateval.close(); stateval2.close(); stateval3.close();
			    	rs_valclpr.close(); rs_valclpr2.close();rs_valclpr3.close();conexion.close();
			    	existeclpr3=true;		    		
			    }if(rs_valclpr4.next()){
			    	stateval.close(); stateval2.close(); stateval4.close();
			    	rs_valclpr.close(); rs_valclpr2.close(); rs_valclpr4.close();conexion.close();
			    	existeclpr4=true;
		    		
			    }if(!existeclpr && !existeclpr2 && !existeclpr3 && !existeclpr4){
			    	
			    	
			    	if(Constantes.SYNCDB==1){
			    		//actualizamos en birt
	        			 ConBirt birtBD= new ConBirt();
	        			 
	        			 
	        			 String relacion="";
	        			 if(empresas_escliente.equals("1")) relacion="CLIENTE";
	        			 if(empresas_esproveedor.equals("1")) relacion="PROVEEDOR";
	        			 if(empresas_esproveedor.equals("1") && empresas_escliente.equals("1")) relacion="CLIENTE/PROVEEDOR";
	        			 
	        			 String[] rut_birt_ar=empresas_rut.replace(".", "").split("-");
	        			 
	        			 
	        			 String Estado_Clpr_id_birt=Estado_Clpr_id;
	        			 
	        			 if(Estado_Clpr_id.equals("7")){Estado_Clpr_id_birt="1";}
	        			 String insert_birt="UPDATE [CLIENTEPROVEEDOR]"
	        			 		+ "	SET [CLIENTEPROVEEDOR].[CLPR_NOMBRE_FANTASIA] = '"+empresas_nombrenof.toUpperCase()+"',"
	        			 		+ "	 [CLIENTEPROVEEDOR].[CLPR_RAZON_SOCIAL] = '"+empresas_razonsocial.toUpperCase()+"',"
	        			 		+ "	 [CLIENTEPROVEEDOR].[CLPR_RUT] = '"+rut_birt_ar[0]+"',"
	        			 		+ "  [CLIENTEPROVEEDOR].[CLPR_DV]='"+rut_birt_ar[1]+"',"
	        			 		+ "	 [CLIENTEPROVEEDOR].[CLPR_WEB] = '"+empresas_web.toUpperCase()+"',"
	        			 		+ "	 [CLIENTEPROVEEDOR].[GRUP_ID] = "+grupos_id+","
	        			 		+ "	 [CLIENTEPROVEEDOR].[ESTCLPR_ID] = "+Estado_Clpr_id_birt+","
	        			 		+ "	 [CLIENTEPROVEEDOR].[CLPR_GIRO] = '"+empresas_giro.toUpperCase()+"',"
	        			 		+ "	 [CLIENTEPROVEEDOR].[CLPR_EMAIL] = '"+empresas_email+"',"
	        			 		+ "	 [CLIENTEPROVEEDOR].[USU_ID_UM]="+U_ID+","
	        			 		+ "	 [CLIENTEPROVEEDOR].[CLPR_FECHA_UM]=getdate(),"
	        			 		+ "	 [CLIENTEPROVEEDOR].[CLPR_RELACION] = '"+relacion+"'"
	        			 		+ "	WHERE"
	        			 		+ "		[CLIENTEPROVEEDOR].[CLPR_ID] = "+request.getParameter("empresas_id");
	        			 System.out.println("BIRT: "+insert_birt);
	        		      birtBD.ConsultaINUP(insert_birt);
	        		     
	        		      
	        		        birtBD.Desconectar();
	        			}
			    	
			    String SQL_UPDATE = ""
			    		+ "UPDATE empresas "
			    		+ "SET empresas_nombre='"+empresas_nombre.toUpperCase()+"', empresas_accion_alertada=0,empresas_ult_idper_exec=18 ,estados_vig_novig_id="+estados_vig_novig_id+", "
			    		+ " grupos_id="+grupos_id+", Estado_Clpr_id="+Estado_Clpr_id+",empresas_escliente="+empresas_escliente+","
			    		+ " empresas_esproveedor="+empresas_esproveedor+",empresas_rut='"+empresas_rut.replace(".", "")+"',empresas_razonsocial='"+empresas_razonsocial.toUpperCase()+"',"
			    		+ " empresas_nombrenof='"+empresas_nombrenof.toUpperCase()+"',empresas_giro='"+empresas_giro.toUpperCase()+"',empresas_web='"+empresas_web.toUpperCase()+"', "
			    		+ " empresas_fecmod='"+Datenow+"',  empresas_modificador="+U_ID+",empresas_esprospeccion="+empresas_esprospeccion+",empresas_email = '"+empresas_email +"', empresas_relacionada='"+empresas_relacionada+"',responsable_id='"+responsable_id+"' " 
			    		+ " WHERE empresas_id="+request.getParameter("empresas_id");
			    System.out.println("UP: "+SQL_UPDATE); 
			    stategrabar.executeUpdate(SQL_UPDATE);
			    stategrabar.close();
	            conexion.close();
	            
	            RequestDispatcher rd_up = request.getRequestDispatcher("Mclpr?mExito=OK");
	            rd_up.forward(request, response);
			    }
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("Error "+ex);
			}
			if(!existeclpr && !existeclpr2 && !existeclpr3 && !existeclpr4)return;
		}
		
		//fin grabar
		
		
		
		Statement state = null;
		Statement state2 = null;
		ResultSet ESTADOS_RS = null;
		ResultSet GRUPOS_RS = null;
		ResultSet ESTCLPR_RS = null;
		ResultSet CLPR_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
		   
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
 		  System.out.println("SIZE LIST: "+estados.size());	
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
 		  String id_rep="";
		    
		    String SQL_CLPR = "SELECT *,"
		    		+ "		CONCAT_WS(' ' , "
 		    		+ "			`usuarios`.`Usuarios_nombre`, "
 		    		+ "			`usuarios`.`Usuarios_ape_p` "
 		    		+ "		) AS perfilusu_creador, "
 		    		+ "		DATE_FORMAT(em.empresas_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion, "
 		    		+ "		IF ( "
 		    		+ "			em.empresas_fecmod IS NULL,"
 		    		+ "			' ',"
 		    		+ "			DATE_FORMAT(em.empresas_fecmod,'%d-%m-%Y %H:%i:%s')"
 		    		+ "		) AS perfilusu_fecmod,"
 		    		+ "		IF ("
 		    		+ "			em.empresas_modificador IS NULL,"
 		    		+ "			' ',"
 		    		+ "			CONCAT_WS(' ', "
 		    		+ "			`u2`.`Usuarios_nombre`,"
 		    		+ "			`u2`.`Usuarios_ape_p`"
 		    		+ "			)"
 		    		+ "		) AS perfilusu_modificador, em.empresas_email "
		    		+ " FROM empresas em"
		    		+ " INNER JOIN grupos g ON g.grupos_id=em.grupos_id"
		    		+ " INNER JOIN estado_clpr e ON e.Estado_Clpr_id=em.Estado_Clpr_id"
		    		+ " INNER JOIN `usuarios` ON `em`.`empresas_creador` = `usuarios`.`Usuarios_id` "
 		    		+ " LEFT JOIN `usuarios` u2 ON `em`.`empresas_modificador` = `u2`.`Usuarios_id` "
 		    		+ " WHERE em.empresas_id="+Integer.parseInt(request.getParameter("empresas_id"));
		    CLPR_RS =  state2.executeQuery(SQL_CLPR);
		    if(CLPR_RS.next()){   	
		    	request.setAttribute("empresas_id",CLPR_RS.getString("empresas_id")+"");
		    	request.setAttribute("empresas_nombre",CLPR_RS.getString("empresas_nombre"));
		    	request.setAttribute("estados_vig_novig_id",CLPR_RS.getString("estados_vig_novig_id"));
		    	request.setAttribute("grupos_id",CLPR_RS.getString("grupos_id"));
		    	request.setAttribute("Estado_Clpr_id",CLPR_RS.getString("Estado_Clpr_id"));
		    	request.setAttribute("empresas_rut",CLPR_RS.getString("empresas_rut"));
		    	request.setAttribute("empresas_escliente",CLPR_RS.getString("empresas_escliente"));
		    	request.setAttribute("empresas_esproveedor",CLPR_RS.getString("empresas_esproveedor"));
		    	request.setAttribute("empresas_razonsocial",CLPR_RS.getString("empresas_razonsocial"));
		    	request.setAttribute("empresas_nombrenof",CLPR_RS.getString("empresas_nombrenof"));
		    	request.setAttribute("empresas_giro",CLPR_RS.getString("empresas_giro"));
		    	request.setAttribute("empresas_web",CLPR_RS.getString("empresas_web"));
		    	request.setAttribute("empresas_esprospeccion",CLPR_RS.getString("empresas_esprospeccion"));
		    	request.setAttribute("empresas_relacionada",CLPR_RS.getString("empresas_relacionada"));
		    	request.setAttribute("responsable_id",CLPR_RS.getString("responsable_id"));
		    	id_rep=CLPR_RS.getString("responsable_id");
		    	request.setAttribute("empresas_email",CLPR_RS.getString("empresas_email"));
		    	
		    	
		    	String fec_crea=CLPR_RS.getString("perfilusu_feccreacion");
		    	String user_crea=CLPR_RS.getString("perfilusu_creador");
		    	String fec_mod=CLPR_RS.getString("perfilusu_fecmod");
		    	String user_mod=CLPR_RS.getString("perfilusu_modificador");
		    	
		    	
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
    	    }
		    CLPR_RS.close();
		    state2.close();	    
		
		    
		    
		    //::::::::::::::::::::::::::::::::::::::::::sql usuarios internos para responsable select option::::::::::::::::::::::::::::::::::::::  
	 		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
	 		  String SQL_resp = "SELECT "
	 		  		+ "		`usuarios`.`Usuarios_id`,"
	 		  		+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre "
			  		+ "	FROM"
	 		  		+ "		`usuarios`"
	 		  		+ "	WHERE "
	 		  		+ "		`usuarios`.`tipo_usu_id` = 1 AND (`usuarios`.`estados_vig_novig_id`=1 OR `usuarios`.`Usuarios_id`="+id_rep+") "
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
		   
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		System.out.println("bool: "+existeclpr);
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
		System.out.println("msg: "+msg);
		RequestDispatcher rd = request.getRequestDispatcher("Mclpr2.jsp"+msg);
        rd.forward(request, response);
		
	}

}
