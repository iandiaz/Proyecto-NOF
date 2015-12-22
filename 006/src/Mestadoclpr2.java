

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
 * Servlet implementation class Mestadoclpr2
 */
@WebServlet("/Mestadoclpr2")
public class Mestadoclpr2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mestadoclpr2() {
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
		boolean existeEstclpr = false;
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet rs_valEstclpr= null;
			try {
				String Estado_Clpr_nombre = request.getParameter("Estado_Clpr_nombre");
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		 stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		 stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
		    		
		    	String sql_valEstclpr="SELECT * FROM estado_clpr WHERE Estado_Clpr_nombre='"+Estado_Clpr_nombre.toUpperCase()+"' AND Estado_Clpr_id<>"+request.getParameter("id_clpr");
		    	rs_valEstclpr=stateval.executeQuery(sql_valEstclpr);
		    	if(rs_valEstclpr.next()){
		    		stateval.close();
		    		rs_valEstclpr.close();conexion.close();
		    		existeEstclpr=true;
	    			System.out.println("if(rs_valgrupos.next()){"+existeEstclpr);
		    	}else{
		    		
		    		if(Constantes.SYNCDB==1){
			    		//updateamos en birt
		    			 ConBirt birtBD= new ConBirt();
		    			 
		    			 String estado_v_nv="VIGENTE";
		    			 if(estados_vig_novig_id.equals("2")) estado_v_nv="NO VIGENTE";
		    			 
		    			 String insert_birt="UPDATE [ESTADO_CLPR] "
		    			 		+ "	SET [ESTADO_CLPR].[ESTCLPR_DESCRIPCION] = '"+Estado_Clpr_nombre.toUpperCase()+"',"
		    			 		+ "	 [ESTADO_CLPR].[ESTCLPR_ESTADO] = '"+estado_v_nv+"',"
		    			 		+ "	 [ESTADO_CLPR].[USU_ID_UM] = "+Usuarios_ID+" ,"
		    			 		+ "	 [ESTADO_CLPR].[ESTCLPR_FECHA_UM] = GETDATE()"
		    			 		+ "	WHERE"
		    			 		+ "		[ESTADO_CLPR].[ESTCLPR_ID] = "+request.getParameter("id_clpr");
		    			 System.out.println("BIRT: "+insert_birt);
		    		      birtBD.ConsultaINUP(insert_birt);
		    		     
		    		      
		    		        birtBD.Desconectar();
		    			}
		    			 
			    	
		    		
			    String SQL_UPDATE = ""
			    		+ "UPDATE estado_clpr "
			    		+ "SET Estado_Clpr_nombre='"+Estado_Clpr_nombre.toUpperCase()+"', Estado_Clpr_accion_alertada=0,Estado_Clpr_ult_idper_exec=14, estados_vig_novig_id="+estados_vig_novig_id+", "
			    		+ " Estado_Clpr_fecmod='"+Datenow+"', Estado_Clpr_modificador="+Usuarios_ID
			    		+ " WHERE Estado_Clpr_id="+request.getParameter("id_clpr");
			    System.out.println("UP: "+SQL_UPDATE); 
			    stategrabar.executeUpdate(SQL_UPDATE);
			    stategrabar.close();
	            conexion.close();
	            
	            RequestDispatcher rd_up = request.getRequestDispatcher("Mestadoclpr?mExito=OK");
	            rd_up.forward(request, response);
		    	}
			}catch(Exception ex){
			    out.println("Unable to connect to database "+ex);
			}
			if(!existeEstclpr)return;
		}
		
		//fin grabar
		System.out.println("Estado bandera: "+existeEstclpr);
		
		
		Statement state = null;
		Statement state2 = null;
		ResultSet ESTADOS_RS = null;
		ResultSet ESTADOCLPR_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    
		    String SQL_ESTADOCLPR = " SELECT *, "
 		    		+ "		CONCAT_WS(' ' , "
 		    		+ "			`usuarios`.`Usuarios_nombre`, "
 		    		+ "			`usuarios`.`Usuarios_ape_p` "
 		    		+ "		) AS perfilusu_creador, "
 		    		+ "		DATE_FORMAT(clpr.estado_clpr_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion, "
 		    		+ "		IF ( "
 		    		+ "			clpr.estado_clpr_fecmod IS NULL,"
 		    		+ "			' ',"
 		    		+ "			DATE_FORMAT(clpr.estado_clpr_fecmod,'%d-%m-%Y %H:%i:%s')"
 		    		+ "		) AS perfilusu_fecmod,"
 		    		+ "		IF ("
 		    		+ "			clpr.estado_clpr_modificador IS NULL,"
 		    		+ "			' ',"
 		    		+ "			CONCAT_WS(' ', "
 		    		+ "			`u2`.`Usuarios_nombre`,"
 		    		+ "			`u2`.`Usuarios_ape_p`"
 		    		+ "			)"
 		    		+ "		) AS perfilusu_modificador "
 		    		+ " FROM estado_clpr clpr "
 		    		+ " INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=clpr.estados_vig_novig_id "
 		    		+ " INNER JOIN `usuarios` ON `clpr`.`estado_clpr_creador` = `usuarios`.`Usuarios_id` "
 		    		+ " LEFT JOIN `usuarios` u2 ON `clpr`.`estado_clpr_modificador` = `u2`.`Usuarios_id` "
		    		+ "WHERE Estado_Clpr_id="+Integer.parseInt(request.getParameter("id_clpr"));
		    ESTADOCLPR_RS =  state2.executeQuery(SQL_ESTADOCLPR);
		    if(ESTADOCLPR_RS.next()){   	
		    	request.setAttribute("id_clpr",ESTADOCLPR_RS.getString("Estado_Clpr_id")+"");
		    	request.setAttribute("grupos_nombre",ESTADOCLPR_RS.getString("Estado_Clpr_nombre"));
		    	request.setAttribute("estados_vig_novig_id",ESTADOCLPR_RS.getString("estados_vig_novig_id"));
		    	
		    	String fec_crea=ESTADOCLPR_RS.getString("perfilusu_feccreacion");
		    	String user_crea=ESTADOCLPR_RS.getString("perfilusu_creador");
		    	String fec_mod=ESTADOCLPR_RS.getString("perfilusu_fecmod");
		    	String user_mod=ESTADOCLPR_RS.getString("perfilusu_modificador") ;
		    	
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
		    	
    	    }
		    ESTADOCLPR_RS.close();
		    state2.close();	    
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> estados = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(ESTADOS_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
        	    
    	    }
		  //System.out.println("SIZE LIST: "+estados.size());
		    	
		  ESTADOS_RS.close();
		  state.close();
          conexion.close();
                
          String[] ar_estados = new String[estados.size()];
          for(int x=0; x < estados.size(); x++){
           	ar_estados[x]=estados.get(x);
          }
                
          request.setAttribute("ar_estados", ar_estados);
		}catch(Exception ex){
		    out.println("Unable to connect to database "+ex);
		}
		
		System.out.println("bool: "+existeEstclpr);
		String msg="";
		if(existeEstclpr){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String Estado_Clpr_nombre = request.getParameter("Estado_Clpr_nombre");
			
			request.setAttribute("grupos_nombre",Estado_Clpr_nombre);
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			System.out.println(msg);
		}
		System.out.println("msg: "+msg);
		RequestDispatcher rd = request.getRequestDispatcher("Mestadoclpr2.jsp"+msg);
        rd.forward(request, response);
		
	}

}
