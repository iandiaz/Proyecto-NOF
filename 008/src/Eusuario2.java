

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
 * Servlet implementation class Eusuario2
 */
@WebServlet("/Eusuario2")
public class Eusuario2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eusuario2() {
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
		
		String Usuarios_ID="";
		
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
		
				if(request.getParameter("grabar") !=  null){
					Statement stategrabar = null;
					try {	
						
						if(Constantes.SYNCDB==1){
			       			 ConBirt birtBD= new ConBirt();
			       			 
			       			 String estado_v_nv="NO VIGENTE";
			       			 
			       			 String insert_birt="UPDATE [USUARIO] "
			       			 		+ "	SET "
			       			 		+ "	 [USUARIO].[USU_ESTADO] = '"+estado_v_nv+"',"
			       			 		+ "	 [USUARIO].[USU_ID_UM] = "+Usuarios_ID+","
			       			 		+ "	 [USUARIO].[USU_FECHA_UM] = GETDATE()"
			       			 		+ "	WHERE"
			       			 		+ "		[USUARIO].[USU_ID] = "+request.getParameter("Usuarios_id");
			       			 
			       			 System.out.println("BIRT: "+insert_birt);
			       		      birtBD.ConsultaINUP(insert_birt);
			       		     
			       		      
			       		        birtBD.Desconectar();
			       			}
						
						//import java.io.IOException;
						Class.forName("com.mysql.jdbc.Driver");
			    		Connection conexion=(Connection) DriverManager.getConnection
			    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			    		 stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
			    		
					    String SQL_UPDATE = ""
					    		+ " UPDATE Usuarios "
					    		+ " SET estados_vig_novig_id=2,Usuarios_accion_alertada=0,Usuarios_ult_idper_exec=21,Usuarios_fecmod=now(),Usuarios_modificador="+Usuarios_ID
					    		+ " WHERE Usuarios_id="+request.getParameter("Usuarios_id");
					    System.out.println("UP: "+SQL_UPDATE); 
					    stategrabar.executeUpdate(SQL_UPDATE);
					    stategrabar.close();
			            conexion.close();
			            
			            RequestDispatcher rd_up = request.getRequestDispatcher("Eusuario?mExito=OK");
			            rd_up.forward(request, response);
					    
					}catch(Exception ex){
					    out.println("Unable to connect to database "+ex);
					}
					return;
				}
				
				//fin grabar
		
		Statement state2 = null;
		Statement state = null;
		ResultSet USUARIOS_RS = null;
		ResultSet PERUSUEMP_RS = null;
		ResultSet EMPRESAS_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
		   
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
     		//:::::::::::::::::::::::::: SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
    		    String SQL_PERUSUEMP = "SELECT * FROM usuarios_has_empresas WHERE estados_vig_novig_id=1 AND Usuarios_id="+Integer.parseInt(request.getParameter("Usuarios_id"));
    		    System.out.println("SQL_PERUSUEMP: "+SQL_PERUSUEMP);
    		    PERUSUEMP_RS =  state.executeQuery(SQL_PERUSUEMP);
    		    //definimos un arreglo para los resultados
    		    ArrayList<String> usuariosempresas = new ArrayList<String>();
    		    //recorremos los resultados de la consulta
    		    while(PERUSUEMP_RS.next()){        	   
            	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
    		    	usuariosempresas.add(PERUSUEMP_RS.getString("empresas_id"));
        	    }
    		  System.out.println("SIZE LIST: "+usuariosempresas.size());	
    		  PERUSUEMP_RS.close();
    		  state.close();
    		  String[] ar_usuariosempresas = new String[usuariosempresas.size()];
              for(int x=0; x < usuariosempresas.size(); x++){
              	ar_usuariosempresas[x]=usuariosempresas.get(x);
              }
                    
              request.setAttribute("ar_usuariosempresas", ar_usuariosempresas);
    		//::::::::::::::::::::::::::SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
     		 
            //:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
              state = (Statement) ((java.sql.Connection) conexion).createStatement();
   		    String SQL_EMPRESAS = "SELECT * FROM empresas WHERE estados_vig_novig_id=1";
   		    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
   		    //definimos un arreglo para los resultados
   		    ArrayList<String> empresas = new ArrayList<String>();
   		    //recorremos los resultados de la consulta
   		    while(EMPRESAS_RS.next()){        	   
           	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
   		    	empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombre"));    
       	    }
   		  System.out.println("SIZE LIST: "+empresas.size());	
   		  EMPRESAS_RS.close();
   		  state.close();
   		  String[] ar_empresas = new String[empresas.size()];
             for(int x=0; x < empresas.size(); x++){
           	 ar_empresas[x]=empresas.get(x);
             }
                   
             request.setAttribute("ar_empresas", ar_empresas);
   		//::::::::::::::::::::::::::fin sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
             String SQL_Usuarios = "SELECT u.*,`es`.`estados_vig_novig_nombre`,`p`.`perfilusu_nombre`,tu.tipo_usu_nombre,e.empresas_nombre, "
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
 		    		+ " INNER JOIN empresas e ON e.empresas_id=u.empresas_id"
 		    		+ " WHERE u.Usuarios_id="+Integer.parseInt(request.getParameter("Usuarios_id"));
 		    System.out.println("SQL_Usuarios: "+SQL_Usuarios);
 		    USUARIOS_RS =  state2.executeQuery(SQL_Usuarios);
 		    if(USUARIOS_RS.next()){   	
 		    	request.setAttribute("Usuarios_id",USUARIOS_RS.getString("Usuarios_id")+"");
 		    	request.setAttribute("Usuarios_nombre",USUARIOS_RS.getString("Usuarios_nombre"));
 		    	request.setAttribute("estados_vig_novig_nombre",USUARIOS_RS.getString("estados_vig_novig_nombre"));
 		    	request.setAttribute("Usuarios_ape_p",USUARIOS_RS.getString("Usuarios_ape_p"));
 		    	request.setAttribute("Usuarios_ape_m",USUARIOS_RS.getString("Usuarios_ape_m"));
 		    	request.setAttribute("Usuarios_pass",USUARIOS_RS.getString("Usuarios_pass"));
 		    	request.setAttribute("Usuarios_login",USUARIOS_RS.getString("Usuarios_login"));
 		    	request.setAttribute("Usuarios_telefono",USUARIOS_RS.getString("Usuarios_telefono"));
 		    	request.setAttribute("Usuarios_email",USUARIOS_RS.getString("Usuarios_email"));
 		    	request.setAttribute("tipo_usu_nombre",USUARIOS_RS.getString("tipo_usu_nombre"));
 		    	request.setAttribute("perfilusu_nombre",USUARIOS_RS.getString("perfilusu_nombre"));
 		    	request.setAttribute("empresas_nombre",USUARIOS_RS.getString("empresas_nombre"));
 		    	request.setAttribute("Usuarios_inicial",USUARIOS_RS.getString("Usuarios_inicial"));
 		    	request.setAttribute("Usuarios_all_emp",USUARIOS_RS.getString("Usuarios_all_emp"));
 		    	request.setAttribute("Usuarios_imei",USUARIOS_RS.getString("Usuarios_imei"));
 		    	
 		    	
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
		    out.println("Unable to connect to database "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("eusuario2.jsp");
        rd.forward(request, response);
		
	}
}
