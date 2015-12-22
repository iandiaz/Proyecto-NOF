

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

/**
 * Servlet implementation class usullave
 */
@WebServlet("/usullave")
public class usullave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public usullave() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		mt(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		mt(request,response);
	}
	

public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		//System.out.println("1");
		
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
		ResultSet usuarios_rs = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_usuarios = "SELECT "
		    		+ "	u.Usuarios_id,"
		    		+ "	u.Usuarios_login,"
		    		+ "	e.estados_vig_novig_id ,"
		    		+ "	e.estados_vig_novig_nombre , "
		    		
					+ "		CONCAT_WS(' ' , "
					+ "			`u`.`Usuarios_nombre`, "
					+ "			`u`.`Usuarios_ape_p` "
					+ "		) AS usu_name  "
		
		    		+ "	FROM usuarios u INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=u.estados_vig_novig_id "
		    		+ " WHERE e.estados_vig_novig_id=1 "
		    		+ "	ORDER BY u.Usuarios_login";
		    
		    usuarios_rs =  state.executeQuery(SQL_usuarios);
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> usuariosal = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(usuarios_rs.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	usuariosal.add(usuarios_rs.getString("Usuarios_id")+"/"+usuarios_rs.getString("Usuarios_login")+"/"+usuarios_rs.getString("estados_vig_novig_id")+"/"+usuarios_rs.getString("estados_vig_novig_nombre")+"/"+usuarios_rs.getString("usu_name"));
        	    
    	    }
		  //System.out.println("SIZE LIST: "+grupos.size());
		    	
		    usuarios_rs.close();
		  state.close();
         
                
          String[] arusuarios = new String[usuariosal.size()];
          for(int x=0; x < usuariosal.size(); x++){
        	  arusuarios[x]=usuariosal.get(x);
          }
        
          state.close();
          
          conexion.close();
          request.setAttribute("arusuarios", arusuarios);
          request.setAttribute("block", request.getParameter("block"));
          
       
		}catch(Exception ex){
		    out.println("Error "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("usullaveon.jsp");
        rd.forward(request, response);
		
	}

}
