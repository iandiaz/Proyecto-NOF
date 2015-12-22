import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
 * Servlet implementation class Cclpr2
 */
@WebServlet("/Cclpr2")
public class Cclpr2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cclpr2() {
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
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		
		Statement state2 = null;
		ResultSet CLPR_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
		   
		    
    		 String SQL_CLPR = "SELECT * ,CONCAT_WS(' ',u3.`Usuarios_nombre`,u3.`Usuarios_ape_p`,u3.`Usuarios_ape_m`) AS responsable_nombre, "
    				+ " IF(em.empresas_escliente='1','SI','NO') AS empresas_escl,"
		    		+ " IF(em.empresas_esproveedor='1','SI','NO') AS empresas_espr, "
		    		+ " IF(em.empresas_esprospeccion='1','SI','NO') AS empresas_espro, "
		    		+ " IF(em.empresas_relacionada='1','SI','NO') AS empresas_rel, "
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
 		    		+ "		) AS perfilusu_modificador,em.empresas_email "
		    		+ " FROM empresas em"
		    		+ " INNER JOIN grupos g ON g.grupos_id=em.grupos_id"
		    		+ " INNER JOIN estado_clpr e ON e.Estado_Clpr_id=em.Estado_Clpr_id"
		    		+ " INNER JOIN estados_vig_novig es ON es.estados_vig_novig_id=em.estados_vig_novig_id"
		    		+ " INNER JOIN `usuarios` ON `em`.`empresas_creador` = `usuarios`.`Usuarios_id` "
 		    		+ " LEFT JOIN `usuarios` u2 ON `em`.`empresas_modificador` = `u2`.`Usuarios_id` "
 		    		+ " LEFT JOIN `usuarios` u3 ON `em`.`responsable_id` = `u3`.`Usuarios_id` "
		    		+ " WHERE em.empresas_id="+Integer.parseInt(request.getParameter("empresas_id"));
		    System.out.println("SQL_CLPR: "+SQL_CLPR);
		    CLPR_RS =  state2.executeQuery(SQL_CLPR);
		    if(CLPR_RS.next()){   	
		    	request.setAttribute("empresas_id",CLPR_RS.getString("empresas_id")+"");
		    	request.setAttribute("empresas_nombre",CLPR_RS.getString("empresas_nombre"));
		    	request.setAttribute("estados_vig_novig_nombre",CLPR_RS.getString("estados_vig_novig_nombre"));
		    	request.setAttribute("grupos_nombre",CLPR_RS.getString("grupos_nombre"));
		    	request.setAttribute("Estado_Clpr_nombre",CLPR_RS.getString("Estado_Clpr_nombre"));
		    	request.setAttribute("empresas_rut",CLPR_RS.getString("empresas_rut"));
		    	request.setAttribute("empresas_escliente",CLPR_RS.getString("empresas_escl"));
		    	request.setAttribute("empresas_esproveedor",CLPR_RS.getString("empresas_espr"));
		    	request.setAttribute("empresas_relacionada",CLPR_RS.getString("empresas_rel"));
		    	
		    	request.setAttribute("empresas_razonsocial",CLPR_RS.getString("empresas_razonsocial"));
		    	request.setAttribute("empresas_nombrenof",CLPR_RS.getString("empresas_nombrenof"));
		    	request.setAttribute("empresas_giro",CLPR_RS.getString("empresas_giro"));
		    	request.setAttribute("empresas_web",CLPR_RS.getString("empresas_web"));
		    	request.setAttribute("empresas_espro",CLPR_RS.getString("empresas_espro"));
		    	request.setAttribute("empresas_email",CLPR_RS.getString("empresas_email"));
		    	request.setAttribute("responsable_nombre",CLPR_RS.getString("responsable_nombre"));
		    	
		    	String fec_crea=CLPR_RS.getString("perfilusu_feccreacion");
		    	String user_crea=CLPR_RS.getString("perfilusu_creador");
		    	String fec_mod=CLPR_RS.getString("perfilusu_fecmod");
		    	String user_mod=CLPR_RS.getString("perfilusu_modificador") ;
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
		    	System.out.println(CLPR_RS.getString("estados_vig_novig_nombre")+" "+CLPR_RS.getString("grupos_nombre"));
    	    }
		    CLPR_RS.close();
		    state2.close();	    
		    
		   
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("ERROR "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("Cclpr2.jsp");
        rd.forward(request, response);
		
	}

}
