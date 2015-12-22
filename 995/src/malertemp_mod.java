

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

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
 * Servlet implementation class malertemp_mod
 */
@WebServlet("/malertemp_mod")
public class malertemp_mod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public malertemp_mod() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
                System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
		    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
		    }
		}
		
		
		if(user_in_session && username_in_session) user_in_session=true;
		else user_in_session=false;
		//refrescamos la session
		
		if (user_in_session && cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
                //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	if(cookie.getName().equals("Usuarios_nombre")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	if(cookie.getName().equals("Usuarios_login")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	if(cookie.getName().equals("Usuarios_email")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	if(cookie.getName().equals("tipo_usu_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	if(cookie.getName().equals("perfilusu_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	
		    	
		    }
		}
		
		
		return user_in_session;
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(validateSession(request, response)){
			
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
		
		String id_iusuario="";
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    }
		}
		
		//grabar
		
		boolean existeAlerta = false;
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet rs_valgrupos= null;
			Statement stateval_al = null;
			ResultSet rs_valalertas= null;
			try {
		    	String alertas_temp_id=request.getParameter("id_a");
		    	request.setAttribute("alertas_temp_id", alertas_temp_id);
				String alertas_temp_nombre = request.getParameter("alertas_temp_nombre");
				String alertas_temp_minutos = request.getParameter("alertas_temp_minutos");
				
				String alertemp_minutos_revisar = request.getParameter("alertemp_minutos_revisar");
				
				String ticket_estados_id = request.getParameter("ticket_estados_id");
				String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
                String alertas_temp_descripcion=request.getParameter("alertemp_desc");
                String tipo_ticket_id=request.getParameter("tipo_ticket");
                String alertas_temp_notiftype=request.getParameter("notif_tipe");
                
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		 stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();

		    		stateval_al = (Statement) ((java.sql.Connection) conexion).createStatement();
		    		String sql_valalerta="SELECT * FROM alertas_temp WHERE alertas_temp_nombre='"+alertas_temp_nombre.toUpperCase()+"' AND alertas_temp_id<>"+request.getParameter("id_a");
			    	rs_valalertas=stateval_al.executeQuery(sql_valalerta);
			    	System.out.println("BUSCA ID: "+sql_valalerta); 
			    	
			    	
			    	if(rs_valalertas.next()){
			    		stateval_al.close();
		    			rs_valgrupos.close();conexion.close();
		    			existeAlerta=true;
		    			System.out.println("if(rs_valgrupos.next()){"+existeAlerta);
		    		}else{
				    String SQL_UPDATE = ""
				    		+ "UPDATE alertas_temp "
				    		+ " SET alertas_temp_nombre='"+alertas_temp_nombre.toUpperCase()+"', estados_vig_novig_id="+estados_vig_novig_id+" "
				    		+ " 	,alertas_temp_minutos= "+alertas_temp_minutos
				    		+ " 	,alertas_temp_minutos_revisar= "+alertemp_minutos_revisar
				    		+ " 	,ticket_estados_id= "+ticket_estados_id
				    		+ " 	,alertas_temp_fecmod=now() "
				    		+ " 	,alertas_temp_modificador= "+id_iusuario
                            + "     ,alertas_temp_descripcion='"+alertas_temp_descripcion+"'"
                            + "     ,tipo_ticket_id="+tipo_ticket_id
                            + "     ,alertas_temp_notiftype="+alertas_temp_notiftype
                            + "     ,alertas_temp_accion_alertada=0, alertas_temp_ult_idper_exec=42 "
				    		+ " WHERE alertas_temp_id="+alertas_temp_id;
				    System.out.println(SQL_UPDATE);
				    stategrabar.executeUpdate(SQL_UPDATE);
				    stategrabar.close();
				    stateval_al.close();
		    		
	    		 
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		String sql_valgrupo="SELECT * FROM gruposusu_alertas_temp WHERE alertas_temp_id = "+alertas_temp_id;
	    		System.out.println("GRUPOS ALERTA: "+sql_valgrupo);
		    	rs_valgrupos=stateval.executeQuery(sql_valgrupo);

					stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
					String up_delete=""
	    					+ " UPDATE gruposusu_alertas_temp SET "
	    					+ " estados_vig_novig_id=2 "
	    					+ " WHERE alertas_temp_id="+alertas_temp_id;
    				stategrabar.executeUpdate(up_delete);
    				stategrabar.close(); 
    				stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
					 String[] seleccionado = request.getParameterValues("permisos[]");
			    		for (String id_per: seleccionado) {
			    			if(id_per!=null && !id_per.equals("-1")){
			    				String insertusuemp=""
				    					+ " INSERT INTO gruposusu_alertas_temp ("
				    					+ " 	gruposusu_id, alertas_temp_id "
				    					+ " ) "
				    					+ " VALUES"
				    					+ " 	('"+id_per+"', '"+alertas_temp_id+"')";
			    				stategrabar.executeUpdate(insertusuemp);
			    			}	    			
			    		}
				    
		    		stategrabar.close(); 
			        conexion.close();
			        
			    RequestDispatcher rd_up = request.getRequestDispatcher("malertemp_search?mExito=OK");
		        rd_up.forward(request, response);
		    	}
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			}
			if(!existeAlerta)return;
		}
		
		//fin grabar
		
		String Usuarios_nombre="";
		
		//fecha 
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		Statement state = null;
		Statement state2 = null;
		Statement state_est = null;
		Statement state_est_ticket = null;
		ResultSet Grupo_RS = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet PERUSUEMP_RS = null;
		ResultSet ESTADOS_RS = null;
		ResultSet ESTADOS_TICKET_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state_est = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state_est_ticket = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		//:::::::::::::::::::::::::: SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
   		    String SQL_PERUSUEMP = "SELECT * FROM gruposusu_alertas_temp WHERE estados_vig_novig_id=1 AND alertas_temp_id="+Integer.parseInt(request.getParameter("id_a"));
   		    PERUSUEMP_RS =  state.executeQuery(SQL_PERUSUEMP);
   		    System.out.println("SQL GRUPO ALERTA: "+SQL_PERUSUEMP);
   		    
   		    //definimos un arreglo para los resultados
   		    ArrayList<String> gruposusualertas = new ArrayList<String>();
   		    //recorremos los resultados de la consulta
   		    while(PERUSUEMP_RS.next()){        	   
           	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
   		    	gruposusualertas.add(PERUSUEMP_RS.getString("gruposusu_id"));
       	    }
   		 
   		  PERUSUEMP_RS.close();
   		  state.close();
   		  String[] ar_gruposusualertas = new String[gruposusualertas.size()];
             for(int x=0; x < gruposusualertas.size(); x++){
            	 ar_gruposusualertas[x]=gruposusualertas.get(x);
             }
                   
             request.setAttribute("ar_gruposusualertas", ar_gruposusualertas);
             //::::::::::::::::::::::::::SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
     		 
     		//:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
             state = (Statement) ((java.sql.Connection) conexion).createStatement();
  		    String SQL_EMPRESAS = "SELECT * FROM gruposusu WHERE estados_vig_novig_id=1 ORDER BY gruposusu_nombre";
  		    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
  		    //definimos un arreglo para los resultados
  		    ArrayList<String> gruposusu = new ArrayList<String>();
  		    //recorremos los resultados de la consulta
  		    while(EMPRESAS_RS.next()){        	   
          	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  		    	gruposusu.add(EMPRESAS_RS.getString("gruposusu_id")+"/"+EMPRESAS_RS.getString("gruposusu_nombre").replace("/", " "));    
      	    }
  		  
  		  EMPRESAS_RS.close();
  		  
  		  String[] ar_gruposusu = new String[gruposusu.size()];
            for(int x=0; x < gruposusu.size(); x++){
            	ar_gruposusu[x]=gruposusu.get(x);
            }
                  
            request.setAttribute("ar_gruposusu", ar_gruposusu);
            
            //:::::::::::::::::::::::::: sql trae tipos de ticket para select option:::::::::::::::::::::::::::::::::::::
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    String SQL_TIPOTCK = "SELECT "
            + "             `tipo_ticket`.`tipo_ticket_id`, "
            + "             `tipo_ticket`.`tipo_ticket_nombre` "
            + "             FROM "
            + "             `tipo_ticket` ORDER BY tipo_ticket_nombre ";
            System.out.println(SQL_TIPOTCK);
		    ResultSet RS_TIPOTCK =  state.executeQuery(SQL_TIPOTCK);
		    //definimos un arreglo para los resultados
		    ArrayList<String> tipotck = new ArrayList<String>();
		    //recorremos los resultados de la consulta
		    while(RS_TIPOTCK.next()){
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	tipotck.add(RS_TIPOTCK.getString("tipo_ticket_id")+"/"+RS_TIPOTCK.getString("tipo_ticket_nombre"));
    	    }
		    RS_TIPOTCK.close();
            
            String[] ar_tipotck = new String[tipotck.size()];
            for(int x=0; x < tipotck.size(); x++){
                ar_tipotck[x]=tipotck.get(x);
            }
            
            request.setAttribute("ar_tipotck", ar_tipotck);

  		//::::::::::::::::::::::::::fin sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::

		    String SQL_GRUPO = ""
		    		+ "SELECT a.alertas_temp_notiftype,a.tipo_ticket_id,a.alertas_temp_descripcion,a.alertas_temp_id, a.alertas_temp_nombre, a.alertas_temp_minutos,a.alertas_temp_minutos_revisar, a.ticket_estados_id, a.estados_vig_novig_id,"
		    		+ "		CONCAT( "
		    		+ "			`usuarios`.`Usuarios_nombre`, "
		    		+ "			' ',"
		    		+ "			`usuarios`.`Usuarios_ape_p` "
		    		+ "		) AS alertas_temp_creador, "
		    		+ "		DATE_FORMAT(a.alertas_temp_feccreacion,'%d-%m-%Y %H:%i:%s') AS alertas_temp_feccreacion, "
		    		+ "		IF ( "
		    		+ "			a.alertas_temp_fecmod IS NULL,"
		    		+ "			' ',"
		    		+ "			DATE_FORMAT(a.alertas_temp_fecmod, '%d-%m-%Y %H:%i:%s')"
		    		+ "		) AS alertas_temp_fecmod,"
		    		+ "		IF ("
		    		+ "			a.alertas_temp_modificador IS NULL,"
		    		+ "			' ',"
		    		+ "			CONCAT( "
		    		+ "			`u2`.`Usuarios_nombre`,"
		    		+ "			' ',"
		    		+ "			`u2`.`Usuarios_ape_p`"
		    		+ "			)"
		    		+ "		) AS alertas_temp_modificador "
		    		+ " FROM alertas_temp a "
		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=a.estados_vig_novig_id"
		    		+ " 	INNER JOIN `usuarios` ON `a`.`alertas_temp_creador` = `usuarios`.`Usuarios_id` "
		    		+ " 	LEFT JOIN `usuarios` u2 ON `a`.`alertas_temp_modificador` = `u2`.`Usuarios_id` "
		    		+ " WHERE alertas_temp_id="+Integer.parseInt(request.getParameter("id_a"));
		    System.out.println("TRAE GRUPO: "+SQL_GRUPO);
		    Grupo_RS =  state2.executeQuery(SQL_GRUPO);
		    if(Grupo_RS.next()){
		    	request.setAttribute("alertas_temp_id",Grupo_RS.getString("alertas_temp_id"));
		    	request.setAttribute("alertas_temp_nombre",Grupo_RS.getString("alertas_temp_nombre"));
		    	request.setAttribute("alertas_temp_minutos",Grupo_RS.getString("alertas_temp_minutos"));
                request.setAttribute("alertas_temp_descripcion",Grupo_RS.getString("alertas_temp_descripcion"));
		    	request.setAttribute("tipo_ticket_id",Grupo_RS.getString("tipo_ticket_id"));
		    	request.setAttribute("alertas_temp_notiftype",Grupo_RS.getString("alertas_temp_notiftype"));
		    	request.setAttribute("alertas_temp_minutos_revisar",Grupo_RS.getString("alertas_temp_minutos_revisar"));
		    	
		    	request.setAttribute("ticket_estados_id",Grupo_RS.getString("ticket_estados_id"));
		    	request.setAttribute("estados_vig_novig_id",Grupo_RS.getString("estados_vig_novig_id"));
		    	
		    	String user_crea=Grupo_RS.getString("alertas_temp_creador");
		    	String fec_crea=Grupo_RS.getString("alertas_temp_feccreacion");
		    	String fec_mod=Grupo_RS.getString("alertas_temp_fecmod");
		    	String user_mod=Grupo_RS.getString("alertas_temp_modificador") ;
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
		    	
    	    }
		    Grupo_RS.close();
		    state2.close();	    
		    
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state_est.executeQuery(SQL_ESTADOS);
		    ArrayList<String> estados = new ArrayList<String>();
			
		    System.out.println("PROBANDO A");
		    //recorremos los resultados de la consulta
		    while(ESTADOS_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
        	    
    	    }
		  System.out.println("SIZE Estados: "+estados.size());
		    	
		  ESTADOS_RS.close();
		  state_est.close();
                
          String[] ar_estados = new String[estados.size()];
          for(int x=0; x < estados.size(); x++){
           	ar_estados[x]=estados.get(x);
          }
                
          request.setAttribute("ar_estados", ar_estados);
          
          
		    String SQL_ESTADOS_TICKET = "SELECT * FROM ticket_estados ORDER BY ticket_estados_nombre";
		    ESTADOS_TICKET_RS =  state_est_ticket.executeQuery(SQL_ESTADOS_TICKET);
		    ArrayList<String> estados_ticket = new ArrayList<String>();
			
		    System.out.println("PROBANDO A");
		    //recorremos los resultados de la consulta
		    while(ESTADOS_TICKET_RS.next()){        	   
      	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estados_ticket.add(ESTADOS_TICKET_RS.getString("ticket_estados_id")+"/"+ESTADOS_TICKET_RS.getString("ticket_estados_nombre"));
      	    
  	    }
		  System.out.println("SIZE Estados: "+estados_ticket.size());
		    	
		  ESTADOS_TICKET_RS.close();
		  state_est_ticket.close();
            state.close();
		  conexion.close();
              
        String[] ar_estados_ticket = new String[estados_ticket.size()];
        for(int x=0; x < estados_ticket.size(); x++){
        	ar_estados_ticket[x]=estados_ticket.get(x);
        }
              
        request.setAttribute("ar_estados_ticket", ar_estados_ticket);
          
          

		}catch(Exception ex){
		    out.println("ERROR: "+ex);
            ex.printStackTrace();
		}
		System.out.println("bool: "+existeAlerta);
		String msg="";
		if(existeAlerta){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String alertas_temp_nombre = request.getParameter("alertas_temp_nombre");
			String alertas_temp_minutos = request.getParameter("alertas_temp_minutos");
			String ticket_estados_id = request.getParameter("ticket_estados_id");
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			request.setAttribute("alertas_temp_nombre",alertas_temp_nombre);
			request.setAttribute("alertas_temp_minutos",alertas_temp_minutos);
			request.setAttribute("ticket_estados_id",ticket_estados_id);
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		System.out.println("msg: "+msg);
		RequestDispatcher rd = request.getRequestDispatcher("malertemp_mod.jsp");
        rd.forward(request, response);
		
	}

}
