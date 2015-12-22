

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
 * Servlet implementation class egrupo_del
 */
@WebServlet("/ealertas_del")
public class ealertas_del extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ealertas_del() {
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
		ResultSet EMPRESAS_RS = null;
		ResultSet PERUSUEMP_RS = null;
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 if(request.getParameter("delete_id") !=  null){
 			 	state3 = (Statement) ((java.sql.Connection) conexion).createStatement();
 				System.out.println("delete_id: "+request.getParameter("delete_id"));
 				String UPDATE = "UPDATE alertas SET estados_vig_novig_id=2, alertas_accion_alertada=0, alertas_ult_idper_exec=29 WHERE alertas_id="+request.getParameter("delete_id");
 				System.out.println("delete : "+UPDATE);
 				state3.executeUpdate(UPDATE);
 				state3.close();
 				RequestDispatcher redir = request.getRequestDispatcher("ealertas?Exito=OK");
 				redir.forward(request, response);
 		        return;
 			}
    		 

     		//:::::::::::::::::::::::::: SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
    		    String SQL_PERUSUEMP = "SELECT * FROM gruposusu_alertas WHERE estados_vig_novig_id=1 AND alertas_id="+Integer.parseInt(request.getParameter("id_a"));
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
   		    String SQL_EMPRESAS = "SELECT * FROM gruposusu WHERE estados_vig_novig_id=1 ORDER BY gruposusu_nombre ";
   		    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
   		    //definimos un arreglo para los resultados
   		    ArrayList<String> gruposusu = new ArrayList<String>();
   		    //recorremos los resultados de la consulta
   		    while(EMPRESAS_RS.next()){        	   
           	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
   		    	gruposusu.add(EMPRESAS_RS.getString("gruposusu_id")+"/"+EMPRESAS_RS.getString("gruposusu_nombre"));    
       	    }
   		  
   		  EMPRESAS_RS.close();
   		  state.close();
   		  String[] ar_gruposusu = new String[gruposusu.size()];
             for(int x=0; x < gruposusu.size(); x++){
             	ar_gruposusu[x]=gruposusu.get(x);
             }
                   
             request.setAttribute("ar_gruposusu", ar_gruposusu);
            
            //:::::::::::::::::::::::::: sql trae eventos para select option:::::::::::::::::::::::::::::::::::::
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    String SQL_EVENTOS = "SELECT "
            + "            `permisos`.`permisos_id`, "
            + "            `permisos`.`permisos_nombre`, "
            + "            `modulos`.`Modulos_codigo` "
            + "            FROM "
            + "            `permisos` "
            + "             INNER JOIN `modulos` ON `modulos`.`Modulos_id` = `permisos`.`Modulos_id` "
            + "             INNER JOIN `alertas_has_evento` ON (`alertas_has_evento`.`alertas_has_evento_id_permiso` = permisos.permisos_id AND `alertas_has_evento`.`alertas_has_evento_id_alertas` = "+Integer.parseInt(request.getParameter("id_a"))+")"
            + "            WHERE "
            + "            `permisos`.`tipo_permiso_id` IN (1, 2, 3) ORDER BY `permisos`.`permisos_nombre` ";
            
            System.out.println("select: "+SQL_EVENTOS);
		    ResultSet RS_EVENTOS =  state.executeQuery(SQL_EVENTOS);
		    //definimos un arreglo para los resultados
		    ArrayList<String> list_eventos = new ArrayList<String>();
		    //recorremos los resultados de la consulta
		    while(RS_EVENTOS.next()){
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	list_eventos.add(RS_EVENTOS.getString("permisos_id")+"/"+RS_EVENTOS.getString("permisos_nombre").replace("/", " ")+"/"+RS_EVENTOS.getString("Modulos_codigo"));
    	    }
		    RS_EVENTOS.close();
            state.close();
            String[] ar_eventos = new String[list_eventos.size()];
            for(int x=0; x < list_eventos.size(); x++){
                ar_eventos[x]=list_eventos.get(x);
            }
            
            request.setAttribute("ar_eventos", ar_eventos);
            
            ///////////////////////////////////
            
    		
		    String SQL_GRUPO = ""
		    		+ "SELECT a.alertas_id, a.alertas_nombre, e.estados_vig_novig_nombre, "
		    		+ "		CONCAT( "
		    		+ "			`usuarios`.`Usuarios_nombre`, "
		    		+ "			' ',"
		    		+ "			`usuarios`.`Usuarios_ape_p` "
		    		+ "		) AS alertas_creador, "
		    		+ "		DATE_FORMAT(a.alertas_feccreacion,'%d-%m-%Y %H:%i:%s') AS alertas_feccreacion, "
		    		+ "		IF ( "
		    		+ "			a.alertas_fecmod IS NULL,"
		    		+ "			' ',"
		    		+ "			DATE_FORMAT(a.alertas_fecmod, '%d-%m-%Y %H:%i:%s')"
		    		+ "		) AS alertas_fecmod,"
		    		+ "		IF ("
		    		+ "			a.alertas_modificador IS NULL,"
		    		+ "			' ',"
		    		+ "			CONCAT( "
		    		+ "			`u2`.`Usuarios_nombre`,"
		    		+ "			' ',"
		    		+ "			`u2`.`Usuarios_ape_p`"
		    		+ "			)"
		    		+ "		) AS alertas_modificador "
		    		+ " FROM alertas a "
		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=a.estados_vig_novig_id"
		    		+ " 	INNER JOIN `usuarios` ON `a`.`alertas_creador` = `usuarios`.`Usuarios_id` "
		    		+ " 	LEFT JOIN `usuarios` u2 ON `a`.`alertas_modificador` = `u2`.`Usuarios_id` "
		    		+ " WHERE alertas_id="+Integer.parseInt(request.getParameter("id_a"));
		    System.out.println("select: "+SQL_GRUPO);
		    Grupo_RS =  state2.executeQuery(SQL_GRUPO);
		    if(Grupo_RS.next()){ 
		    	
		    	request.setAttribute("alertas_id",Grupo_RS.getString("alertas_id"));
		    	request.setAttribute("alertas_nombre",Grupo_RS.getString("alertas_nombre"));
		    	request.setAttribute("estados_vig_novig_nombre",Grupo_RS.getString("estados_vig_novig_nombre"));
		    	
		    	String user_crea=Grupo_RS.getString("alertas_creador");
		    	String fec_crea=Grupo_RS.getString("alertas_feccreacion");
		    	String fec_mod=Grupo_RS.getString("alertas_fecmod");
		    	String user_mod=Grupo_RS.getString("alertas_modificador") ;
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
    	    }
		  Grupo_RS.close();
		  state2.close();	    
          conexion.close();
		}catch(Exception ex){
			
		    out.println("ERROR "+ex);
            ex.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("ealertas_del.jsp");
        rd.forward(request, response);
		
	}

}
