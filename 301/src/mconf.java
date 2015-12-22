

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
 * Servlet implementation class mconf
 */
@WebServlet("/mconf")
public class mconf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mconf() {
        super();
        // TODO Auto-generated constructor stub
    }

public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
  		boolean user_in_session=false;
  		Cookie [] cookies=request.getCookies();
  		
  		if (cookies != null) {
  		    for (Cookie cookie : cookies) {
  		        //work with cookies
  		    	 //System.out.println("cookie: "+cookie.getName());
  		    	if(cookie.getName().equals("Usuarios_id")) user_in_session=true;
  		    }
  		}
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
		
		String Usuarios_nombre="";
		
		//fecha 
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String Datenow=ahoraCal.get(Calendar.YEAR)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.DATE)+" "+ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String Usuarios_ID="";
		//System.out.print(Datenow);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) { Usuarios_ID=cookie.getValue();}
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		
		//grabar
		boolean existeclpr = false;
		boolean existeclpr2 = false;
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet rs_valclpr= null;
			Statement stateval2 = null;
			ResultSet rs_valclpr2= null;
			try {
				String UID=request.getParameter("Usuarios_id");
				String Usuarios_name = request.getParameter("Usuarios_nombre");
				String Usuarios_ape_p = request.getParameter("Usuarios_ape_p");
			   	String Usuarios_ape_m = request.getParameter("Usuarios_ape_m");
			   	String Usuarios_pass = request.getParameter("Usuarios_pass");
			   	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			   	String Usuarios_login = request.getParameter("Usuarios_login");
			   	String Usuarios_telefono = request.getParameter("Usuarios_telefono");
			   	String Usuarios_email = request.getParameter("Usuarios_email");
		    	String tipo_usu_id = request.getParameter("tipo_usu_id");
		    	String perfilusu_id = request.getParameter("perfilusu_id");
		    	String empresas_id = request.getParameter("empresas_id");
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
		   		Connection conexion=(Connection) DriverManager.getConnection
		 	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		  		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
		  		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
		  		stateval2 = (Statement) ((java.sql.Connection) conexion).createStatement();  
		  		
		  		String DEL_USUTIC = "DELETE FROM usu_tipoticket WHERE usuarios_id = "+Integer.parseInt(request.getParameter("Usuarios_id"));
		  		stategrabar.executeUpdate(DEL_USUTIC);
	   		    
		  		String DEL_USUGPS = "DELETE FROM usu_gps WHERE usuarios_id = "+Integer.parseInt(request.getParameter("Usuarios_id"));
		  		stategrabar.executeUpdate(DEL_USUGPS);

		  		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
		  		String[] sel1 = request.getParameterValues("usutti[]");
		  		if(sel1!=null){
		  		for (String id_tic: sel1) {
		  			if(id_tic!=null && !id_tic.equals("-1")){
		  				String insertusuemp="INSERT INTO usu_tipoticket (usuarios_id, tipo_ticket_id) VALUES ('"+UID+"', '"+id_tic+"')";
		  				stategrabar.executeUpdate(insertusuemp);
		  			}	    			
		  		}
		  		}
		  		
		  		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
		  		String[] sel2 = request.getParameterValues("usugps[]");
		  		if(sel2!=null){
		  		for (String id_gps: sel2) {
		  			if(id_gps!=null && !id_gps.equals("-1")){
		  				String insertusuemp="INSERT INTO usu_gps (usuarios_id, usuarios_id_gps) VALUES ('"+UID+"', '"+id_gps+"')";
		  				stategrabar.executeUpdate(insertusuemp);
		  			}	    			
		  		}
		  		}
				    
		  		stategrabar.close(); 
		  		conexion.close();
			           
		  		RequestDispatcher rd_up = request.getRequestDispatcher("mconf_search?mExito=OK");
		  		rd_up.forward(request, response);
			}catch(Exception ex){
			    out.println("Unable to connect to database "+ex);
			    ex.printStackTrace();
			}
		}else{
				
		//fin grabar
		
		Statement state2 = null;
		ResultSet USUARIOS_RS = null;
		Statement state = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet TICKET_RS = null;
		ResultSet TICUSU_RS = null;
		ResultSet USUGPS_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
     		//:::::::::::::::::::::::::: SQL TRAE TIPO DE TICKET  ::::::::::::::::::::::::::::::::::::://
   		    String SQL_TICKET = "SELECT * FROM tipo_ticket";
   		    TICKET_RS =  state.executeQuery(SQL_TICKET);
   		    ArrayList<String> tticket = new ArrayList<String>();
   		    while(TICKET_RS.next()){ tticket.add(TICKET_RS.getString("tipo_ticket_id")+"/"+TICKET_RS.getString("tipo_ticket_nombre"));}
   		    TICKET_RS.close();state.close();
   		    String[] ar_tticket = new String[tticket.size()];
            for(int x=0; x < tticket.size(); x++){ ar_tticket[x]=tticket.get(x);}
            request.setAttribute("ar_tticket", ar_tticket);
            //:::::::::::::::::::::::::: SQL TRAE TIPO DE TICKET ::::::::::::::::::::::::::::::::::::://
    		 
     		//:::::::::::::::::::::::::: SQL TRAE USUARIOS CON TIPO DE TICKET :::::::::::::::::::::::::::::::::::::://
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
            String SQL_TICUSU = "SELECT * FROM usu_tipoticket WHERE usuarios_id = "+Integer.parseInt(request.getParameter("Usuarios_id"));
   		    TICUSU_RS =  state.executeQuery(SQL_TICUSU);
   		    ArrayList<String> tusu = new ArrayList<String>();
   		    while(TICUSU_RS.next()){ tusu.add(TICUSU_RS.getString("tipo_ticket_id"));}
   		    TICUSU_RS.close();state.close();
   		    String[] ar_tusu = new String[tusu.size()];
            for(int x=0; x < tusu.size(); x++){ ar_tusu[x]=tusu.get(x);}
            request.setAttribute("ar_tusu", ar_tusu);
            //:::::::::::::::::::::::::: SQL TRAE USUARIOS CON TIPO DE TICKET ::::::::::::::::::::::::::::::::::::://

     		//:::::::::::::::::::::::::: SQL TRAE USUARIOS PARA MOSTRAR EN GPS :::::::::::::::::::::::::::::::::::::://
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
   		    String SQL_USUGPS = "SELECT * FROM usu_gps WHERE usuarios_id = "+Integer.parseInt(request.getParameter("Usuarios_id"));
   		    System.out.println(SQL_USUGPS);
   		    USUGPS_RS =  state.executeQuery(SQL_USUGPS);
   		    ArrayList<String> usugps = new ArrayList<String>();
   		    while(USUGPS_RS.next()){ usugps.add(USUGPS_RS.getString("usuarios_id_gps"));}
   		    USUGPS_RS.close();state.close();
   		    String[] ar_usugps = new String[usugps.size()];
            for(int x=0; x < usugps.size(); x++){ ar_usugps[x]=usugps.get(x);}
            request.setAttribute("ar_usugps", ar_usugps);
            //:::::::::::::::::::::::::: SQL TRAE USUARIOS PARA MOSTRAR EN GPS ::::::::::::::::::::::::::::::::::::://
            
    		//:::::::::::::::::::::::::: SQL USUARIOS PARA GPS ::::::::::::::::::::::::::::::::::::::://
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
 		    String SQL_USUARIOS = "SELECT * FROM usuarios WHERE estados_vig_novig_id=1";
 		    USUARIOS_RS =  state.executeQuery(SQL_USUARIOS);
 		    ArrayList<String> usuarios = new ArrayList<String>();
 		    while(USUARIOS_RS.next()){        	   
 		    	usuarios.add(USUARIOS_RS.getString("Usuarios_id")+"/"+USUARIOS_RS.getString("Usuarios_nombre")+"/"+USUARIOS_RS.getString("Usuarios_ape_p")+"/"+USUARIOS_RS.getString("Usuarios_ape_m"));    
     	    }	
 		    USUARIOS_RS.close();
 		    state.close();
 		    String[] ar_usuarios = new String[usuarios.size()];
 		    for(int x=0; x < usuarios.size(); x++){
 		    	ar_usuarios[x]=usuarios.get(x);
 		    }
 		    request.setAttribute("ar_usuarios", ar_usuarios);
 		    //:::::::::::::::::::::::::: SQL USUARIOS PARA GPS ::::::::::::::::::::::::::::::::::::::://

		    
		    String SQL_Usuarios = "SELECT u.*, "
		    		+ "		CONCAT_WS(' ' , "
 		    		+ "			`u1`.`Usuarios_nombre`, "
 		    		+ "			`u1`.`Usuarios_ape_p` "
 		    		+ "		) AS perfilusu_creador, "
 		    		+ "		DATE_FORMAT(u.usuarios_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion, "
 		    		+ "		IF ( "
 		    		+ "			u.usuarios_fecmod IS NULL,"
 		    		+ "			' ',"
 		    		+ "			DATE_FORMAT(u.usuarios_fecmod,'%d-%m-%Y %H:%i:%s')"
 		    		+ "		) AS perfilusu_fecmod,"
 		    		+ "		IF ("
 		    		+ "			u.usuarios_modificador IS NULL,"
 		    		+ "			' ',"
 		    		+ "			CONCAT_WS(' ', "
 		    		+ "			`u2`.`Usuarios_nombre`,"
 		    		+ "			`u2`.`Usuarios_ape_p`"
 		    		+ "			)"
 		    		+ "		) AS perfilusu_modificador "
		    		+ " FROM usuarios u"
		    		+ " INNER JOIN perfilusu p ON p.perfilusu_id=u.perfilusu_id"
		    		+ " INNER JOIN tipo_usu tu ON tu.tipo_usu_id=u.tipo_usu_id"
		    		+ " INNER JOIN estados_vig_novig es ON es.estados_vig_novig_id=u.estados_vig_novig_id"
		    		+ " INNER JOIN `usuarios` u1 ON `u`.`usuarios_creador` = `u1`.`Usuarios_id` "
 		    		+ " LEFT JOIN `usuarios` u2 ON `u`.`usuarios_modificador` = `u2`.`Usuarios_id` "
		    		//+ " INNER JOIN empresas e ON e.empresas_id=u.empresas_id"
		    		+ " WHERE u.Usuarios_id="+Integer.parseInt(request.getParameter("Usuarios_id"));
		    USUARIOS_RS =  state2.executeQuery(SQL_Usuarios);
		    if(USUARIOS_RS.next()){   	
		    	request.setAttribute("correlativo",USUARIOS_RS.getString("Usuarios_id")+"");
		    	request.setAttribute("Usuarios_nombre",USUARIOS_RS.getString("Usuarios_nombre"));
		    	request.setAttribute("estados_vig_novig_id1",USUARIOS_RS.getString("estados_vig_novig_id"));
		    	request.setAttribute("Usuarios_ape_p",USUARIOS_RS.getString("Usuarios_ape_p"));
		    	request.setAttribute("Usuarios_ape_m",USUARIOS_RS.getString("Usuarios_ape_m"));
		    	request.setAttribute("Usuarios_pass",USUARIOS_RS.getString("Usuarios_pass"));
		    	request.setAttribute("Usuarios_login",USUARIOS_RS.getString("Usuarios_login"));
		    	request.setAttribute("Usuarios_telefono",USUARIOS_RS.getString("Usuarios_telefono"));
		    	request.setAttribute("Usuarios_email",USUARIOS_RS.getString("Usuarios_email"));
		    	request.setAttribute("tipo_usu_id1",USUARIOS_RS.getString("tipo_usu_id"));
		    	request.setAttribute("perfilusu_id1",USUARIOS_RS.getString("perfilusu_id"));
		    	//request.setAttribute("empresas_id1",USUARIOS_RS.getString("empresas_id"));		
		    	request.setAttribute("Usuarios_id",USUARIOS_RS.getString("Usuarios_id"));
		    	
		    	
		    	
		    	String fec_crea=USUARIOS_RS.getString("perfilusu_feccreacion");
		    	String user_crea=USUARIOS_RS.getString("perfilusu_creador");
		    	String fec_mod=USUARIOS_RS.getString("perfilusu_fecmod");
		    	String user_mod=USUARIOS_RS.getString("perfilusu_modificador") ;
		    	
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
		    	//System.out.println(CLPR_RS.getString("estados_vig_novig_nombre")+" "+CLPR_RS.getString("grupos_nombre"));
    	    }
		    USUARIOS_RS.close();
		    state2.close();	    
		    
		   
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Unable to connect to database "+ex);
		}
		
		String msg="";
		if(existeclpr){
			msg="?Exito=NOK1";
		}if(existeclpr2){
			msg="?Exito=NOK2";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("mconf.jsp"+msg);
        rd.forward(request, response);
		}
		
	}

}
