

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
 * Servlet implementation class esubu2
 */
@WebServlet("/esubu2")
public class esubu2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public esubu2() {
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
		    	//System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
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
			    	//System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
		}
		//fin logout
			
		String Usuarios_nombre="";
		String id_iusuario="";
		//fecha 
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		Statement state2 = null;		
		ResultSet Grupo_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
		    
	 if(request.getParameter("delete_id") !=  null){
    			 
    			 /*
    			 //borramos en birt 
    			 if(Constantes.SYNCDB==1){
 	    			//actualizamos grupo en birt
 	    			
 	    			ConBirt birtBD= new ConBirt();
 	       			String estado_v_nv="NO VIGENTE";
 	       			 
 	       			 String insert_birt=" UPDATE [TIPO_CONTACTO] "
 	       			 		+ "	SET  "
 	       			 		+ "	 [TIPO_CONTACTO].[TICON_ESTADO] = 'NO VIGENTE',"
 	       			 		+ "	 [TIPO_CONTACTO].[TICON_FECHA_UM] = GETDATE(),"
 	       			 		+ "	 [TIPO_CONTACTO].[USU_ID_UM] = '"+id_iusuario+"'"
 	       			 		+ "	WHERE"
 	       			 		+ "		[TIPO_CONTACTO].[TICON_ID] = "+request.getParameter("delete_id");
 	       		 System.out.println("BIRT: "+insert_birt);
 	       		      birtBD.ConsultaINUP(insert_birt);
 	       		     
 	       		     
 	       		        birtBD.Desconectar();
 	       			}
    			 */
    			 
    			 
 			 	Statement state3 = (Statement) ((java.sql.Connection) conexion).createStatement();
 				String UPDATE = "UPDATE sububicacion "
 						+ "	SET estados_vig_novig_id=2,"
 						+ "		subi_fecmod= now(), "
 						+ "		subi_modificador= "+id_iusuario+" "
 						+ " WHERE SUBI_ID="+request.getParameter("delete_id");
 				System.out.println("delete : "+UPDATE);
 				state3.executeUpdate(UPDATE);
 				state3.close();
 				out.print("<script>location='/012/?Exito=OK'</script>");
	    		
	    		 return;
 			}
    		
    		String SQL_GRUPO = ""
		    		+ "SELECT *,"
		    		+ "		CONCAT( "
		    		+ "			`usuarios`.`Usuarios_nombre`, "
		    		+ "			' ',"
		    		+ "			`usuarios`.`Usuarios_ape_p` "
		    		+ "		) AS perfilusu_creador, "
		    		+ "		DATE_FORMAT(g.subi_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion, "
		    		+ "		IF ( "
		    		+ "			g.subi_fecmod IS NULL,"
		    		+ "			' ',"
		    		+ "			DATE_FORMAT(g.subi_fecmod,'%d-%m-%Y %H:%i:%s')"
		    		+ "		) AS perfilusu_fecmod,"
		    		+ "		IF ("
		    		+ "			g.subi_modificador IS NULL,"
		    		+ "			' ',"
		    		+ "			CONCAT( "
		    		+ "			`u2`.`Usuarios_nombre`,"
		    		+ "			' ',"
		    		+ "			`u2`.`Usuarios_ape_p`"
		    		+ "			)"
		    		+ "		) AS perfilusu_modificador, "
		    		+ "	`direccion`.`DIRE_DIRECCION`,`empresas`.`empresas_nombrenof`   "
		    		
		    		+ " FROM sububicacion g "
		    		+ " 	INNER JOIN ubicacion u ON u.ubi_id=g.ubi_id"
		    		+ "		INNER JOIN `direccion` ON `direccion`.`DIRE_ID` = u.`DIRE_ID` "
		    		+ "		INNER JOIN `empresas` ON `empresas`.`empresas_id` = `direccion`.`CLPR_ID` "
		    	
		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=g.estados_vig_novig_id"
		    		+ " 	INNER JOIN `usuarios` ON `g`.`subi_creador` = `usuarios`.`Usuarios_id` "
		    		+ " 	LEFT JOIN `usuarios` u2 ON `g`.`subi_modificador` = `u2`.`Usuarios_id` "
		    		+ " WHERE SUBI_ID="+Integer.parseInt(request.getParameter("id_g"));
		    System.out.println("SQL: "+SQL_GRUPO);
		    Grupo_RS =  state2.executeQuery(SQL_GRUPO);
		    if(Grupo_RS.next()){ 
		    	request.setAttribute("grupos_id",Grupo_RS.getString("SUBI_ID"));
		    	request.setAttribute("grupos_nombre",Grupo_RS.getString("SUBI_NOMBRE"));
		    	request.setAttribute("ubi_fisica",Grupo_RS.getString("ubi_fisica"));
		    	request.setAttribute("estados_vig_novig_nombre",Grupo_RS.getString("estados_vig_novig_nombre"));
		    	
		    	request.setAttribute("DIRE_DIRECCION",Grupo_RS.getString("DIRE_DIRECCION"));
		    	request.setAttribute("empresas_nombrenof",Grupo_RS.getString("empresas_nombrenof"));
		    	
		    	String fec_crea=Grupo_RS.getString("perfilusu_feccreacion");
		    	String user_crea=Grupo_RS.getString("perfilusu_creador");
		    	String fec_mod=Grupo_RS.getString("perfilusu_fecmod");
		    	String user_mod=Grupo_RS.getString("perfilusu_modificador") ;
		    	
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
		}
		RequestDispatcher rd = request.getRequestDispatcher("esubu2.jsp");
        rd.forward(request, response);
		
	}

}
