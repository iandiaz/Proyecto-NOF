

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
 * Servlet implementation class ealertemp_del
 */
@WebServlet("/ealertemp_del")
public class ealertemp_del extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ealertemp_del() {
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
		Statement state2 = null;		
		ResultSet Grupo_RS = null;
		Statement state3 = null;
		Statement state = null;
		Statement state_est = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet PERUSUEMP_RS = null;
		ResultSet ESTADOS_RS = null;		
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
   		 	state_est = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
		    
    		 if(request.getParameter("delete_id") !=  null){
 			 	state3 = (Statement) ((java.sql.Connection) conexion).createStatement();
 				System.out.println("delete_id: "+request.getParameter("delete_id"));
 				String UPDATE = "UPDATE alertas_temp SET estados_vig_novig_id=2,alertas_temp_accion_alertada=0, alertas_temp_ult_idper_exec=44 WHERE alertas_temp_id="+request.getParameter("delete_id");
 				System.out.println("delete : "+UPDATE);
 				state3.executeUpdate(UPDATE);
 				state3.close();
 				RequestDispatcher redir = request.getRequestDispatcher("ealertemp?Exito=OK");
 				redir.forward(request, response);
 		        return;
 			}
    		 
    		//:::::::::::::::::::::::::: SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
   		    String SQL_PERUSUEMP = "SELECT * FROM gruposusu_alertas_temp "
   		    		+ "WHERE estados_vig_novig_id=1 AND alertas_temp_id="+Integer.parseInt(request.getParameter("id_a"));
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
  		  System.out.println("SQL EMPRESA: "+SQL_EMPRESAS);
  		    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
  		    //definimos un arreglo para los resultados
  		    ArrayList<String> gruposusu = new ArrayList<String>();
  		    //recorremos los resultados de la consulta
  		    while(EMPRESAS_RS.next()){        	   
          	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  		    	gruposusu.add(EMPRESAS_RS.getString("gruposusu_id")+"/"+EMPRESAS_RS.getString("gruposusu_nombre").replace("/", " "));    
      	    }
  		  
  		  EMPRESAS_RS.close();
  		  state.close();
  		  String[] ar_gruposusu = new String[gruposusu.size()];
            for(int x=0; x < gruposusu.size(); x++){
            	ar_gruposusu[x]=gruposusu.get(x);
            }
                  
            request.setAttribute("ar_gruposusu", ar_gruposusu);    		
    		
		    String SQL_GRUPO = ""
		    		+ "SELECT *,"
		    		+ "		CONCAT( "
		    		+ "			`usuarios`.`Usuarios_nombre`, "
		    		+ "			' ',"
		    		+ "			`usuarios`.`Usuarios_ape_p` "
		    		+ "		) AS alertas_temp_creador2, "
		    		+ "		DATE_FORMAT(a.alertas_temp_feccreacion,'%d-%m-%Y %H:%i:%s') AS alertas_temp_feccreacion4, "
		    		+ "		IF ( "
		    		+ "			a.alertas_temp_fecmod IS NULL,"
		    		+ "			' ',"
		    		+ "			DATE_FORMAT(a.alertas_temp_fecmod, '%d-%m-%Y %H:%i:%s')"
		    		+ "		) AS alertas_temp_fecmod2,"
		    		+ "		IF ("
		    		+ "			a.alertas_temp_modificador IS NULL,"
		    		+ "			' ',"
		    		+ "			CONCAT( "
		    		+ "			`u2`.`Usuarios_nombre`,"
		    		+ "			' ',"
		    		+ "			`u2`.`Usuarios_ape_p`"
		    		+ "			)"
		    		+ "		) AS alertas_temp_modificador2, "
		    		+ "		t.ticket_estados_nombre, "
                    + "     `tipo_ticket`.`tipo_ticket_nombre` "
		    		+ " FROM alertas_temp a "
		    		+ " 	LEFT JOIN ticket_estados t ON t.ticket_estados_id=a.ticket_estados_id"
                    + "     LEFT JOIN `tipo_ticket` ON `tipo_ticket`.`tipo_ticket_id` = `a`.`tipo_ticket_id` "
		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=a.estados_vig_novig_id"
		    		+ " 	INNER JOIN `usuarios` ON `a`.`alertas_temp_creador` = `usuarios`.`Usuarios_id` "
		    		+ " 	LEFT JOIN `usuarios` u2 ON `a`.`alertas_temp_modificador` = `u2`.`Usuarios_id` "
		    		+ " WHERE alertas_temp_id="+Integer.parseInt(request.getParameter("id_a"));
		    System.out.println("select: "+SQL_GRUPO);
		    Grupo_RS =  state2.executeQuery(SQL_GRUPO);
		    if(Grupo_RS.next()){ 
		    	
		    	request.setAttribute("alertas_temp_id",Grupo_RS.getString("alertas_temp_id"));
		    	request.setAttribute("alertas_temp_nombre",Grupo_RS.getString("alertas_temp_nombre"));
		    	request.setAttribute("alertas_temp_minutos",Grupo_RS.getString("alertas_temp_minutos"));
		    	
		    	request.setAttribute("alertas_temp_minutos_revisar",Grupo_RS.getString("alertas_temp_minutos_revisar"));
		    	
                request.setAttribute("alertas_temp_descripcion",Grupo_RS.getString("alertas_temp_descripcion"));
		    	request.setAttribute("ticket_estados_nombre",Grupo_RS.getString("ticket_estados_nombre"));
		    	request.setAttribute("estados_vig_novig_nombre",Grupo_RS.getString("estados_vig_novig_nombre"));
		    	request.setAttribute("tipo_ticket_nombre",Grupo_RS.getString("tipo_ticket_nombre"));
		    	request.setAttribute("alertas_temp_notiftype",Grupo_RS.getString("alertas_temp_notiftype"));
		    	
                
		    	String fec_crea=Grupo_RS.getString("alertas_temp_feccreacion4");
		    	String user_crea=Grupo_RS.getString("alertas_temp_creador2");
		    	String fec_mod=Grupo_RS.getString("alertas_temp_fecmod2");
		    	String user_mod=Grupo_RS.getString("alertas_temp_modificador2") ;
		    	
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
    	    }
		  Grupo_RS.close();
		  state2.close();	    
          conexion.close();
		}catch(Exception ex){
			
		    out.println("Unable to connect to database "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("ealertemp_del.jsp");
        rd.forward(request, response);
		
	}

}
