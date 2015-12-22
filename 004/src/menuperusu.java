import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;

/**
 * Servlet implementation class menuperusu
 */
@WebServlet("/menuperusu")
public class menuperusu extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public menuperusu() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		boolean sesion_valida=validateSession(request,response);
		
		if(!sesion_valida) response.sendRedirect("/001/");
		else {
			 mt(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		
		
			///////////////////////////////////////////
			//////DEFINE DESLOGEO//////////////////////
			
			Cookie [] cookies=request.getCookies();
			if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			for (Cookie cookie : cookies) {
			//System.out.println("cookie logout: "+cookie.getName());
			if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			}
			}
			response.sendRedirect("/001/");
			return;
			
			}
			
			
			//////////////////////////////////////////////////
			////////DEFINE PARAMETROS DE USUARIO//////////////
			
			String Usuarios_nombre="";
			String Perfil_id="";
			
			
			Calendar ahoraCal = Calendar.getInstance();
			String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
			String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
					if(cookie.getName().equals("perfilusu_id")) Perfil_id=cookie.getValue();
				}
			}
			request.setAttribute("usuname", Usuarios_nombre);
			
			//////////////////////////////////////////////////
		
		
		
		String perb_ingresar="0";
		String perb_modificar="0";
		String perb_eliminar="0";
		String perb_consultar="0";
		String perb_reportes="0";
		
		Statement state = null;
		ResultSet PERFILES_RS = null;
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_PERFILES = "SELECT "
		    		+ " 	`permisos`.`tipo_permiso_id`,"
		    		+ " 	`modulos`.`Modulos_codigo`,"
		    		+ " 	`modulos`.`estados_vig_novig_id`,"
		    		+ " 	`permisos`.`permisos_nombre`," 
		    		+ " 	`modulos`.`modulos_manualnombre` "
		    		+ " FROM"
		    		+ " 	`modulos`"
		    		+ " INNER JOIN `permisos` ON `permisos`.`Modulos_id` = `modulos`.`Modulos_id`"
		    		+ " INNER JOIN ("
		    		+ " 	SELECT"
		    		+ " 		`perfilusu_has_permisos`.`permisos_id`"
		    		+ " 	FROM"
		    		+ " 		`perfilusu_has_permisos`"
		    		+ " 	INNER JOIN `perfilusu` ON `perfilusu`.`perfilusu_id` = "+Perfil_id
		    		+ " 	WHERE"
		    		+ " 		`perfilusu`.`estados_vig_novig_id` = 1"
		    		+ " 	AND `perfilusu_has_permisos`.`estados_vig_novig_id` = 1"
		    		+ " 	AND `perfilusu_has_permisos`.`perfilusu_id` = "+Perfil_id
		    		+ " ) php ON php.permisos_id = `permisos`.`permisos_id`"
		    		+ " WHERE"
		    		+ " 	`modulos`.`estados_vig_novig_id` = 1"
		    		+ " 	AND `permisos`.`Modulos_id` = 4"
		    		+ " ORDER BY"
		    		+ " 	`modulos`.`Menus_id`,"
		    		+ " 	`modulos`.`Modulos_codigo`";
		   
		    
		    System.out.println(SQL_PERFILES);
		    PERFILES_RS =  state.executeQuery(SQL_PERFILES);
		    
		   
		    //recorremos los resultados de la consulta y validamos los botones que tendr� disponibles
		    	String manual="";
		    while(PERFILES_RS.next()){
		    	if(PERFILES_RS.getString("tipo_permiso_id")!= null && PERFILES_RS.getString("tipo_permiso_id").equals("1")) perb_ingresar="1";
        		if(PERFILES_RS.getString("tipo_permiso_id")!= null && PERFILES_RS.getString("tipo_permiso_id").equals("2"))perb_eliminar="1";
        		if(PERFILES_RS.getString("tipo_permiso_id")!= null && PERFILES_RS.getString("tipo_permiso_id").equals("3"))perb_modificar="1";
        		if(PERFILES_RS.getString("tipo_permiso_id")!= null && PERFILES_RS.getString("tipo_permiso_id").equals("4"))perb_consultar="1";
        		if(PERFILES_RS.getString("tipo_permiso_id")!= null && PERFILES_RS.getString("tipo_permiso_id").equals("5"))perb_reportes="1";
        		if(PERFILES_RS.getString("modulos_manualnombre")!= null ) manual=Constantes.URL_MANUALES+""+PERFILES_RS.getString("modulos_manualnombre");
        		        	   
        		
    	    }
		    
		    PERFILES_RS.close();
		 	state.close();
	       	conexion.close();
	      
	    request.setAttribute("manual", manual);
       	
        request.setAttribute("perb_ingresar", perb_ingresar);
        request.setAttribute("perb_eliminar", perb_eliminar);
        request.setAttribute("perb_modificar", perb_modificar);
        request.setAttribute("perb_consultar", perb_consultar);
        request.setAttribute("perb_reportes", perb_reportes);
        
        
        RequestDispatcher a = request.getRequestDispatcher("menuperusu.jsp");
    	a.forward(request, response);
    	
		}catch(Exception ex){
		    out.println("Error: "+ex);
		}
		
		
		
		
	}
	

}
