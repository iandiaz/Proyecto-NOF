

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
 * Servlet implementation class Cmanual
 */
@WebServlet("/Cmanual")
public class Cmanual extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cmanual() {
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
		ResultSet modulos_RS = null;
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 String SQL_modulos = "SELECT modulos.Modulos_id, modulos.Modulos_nombre, modulos.Modulos_codigo, modulos.estados_vig_novig_id, "
    		 					+ "modulos.Menus_id, modulos.modulos_manualnombre, e.estados_vig_novig_nombre FROM modulos INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=modulos.estados_vig_novig_id";
  		    System.out.println("SQL_modulos : "+SQL_modulos);
  		    modulos_RS =  state.executeQuery(SQL_modulos);
 		    
 		    //definimos un arreglo para los resultados
 		    
 		    ArrayList<String> modulos = new ArrayList<String>();
 		   
 		    //recorremos los resultados de la consulta
 		    while(modulos_RS.next()){        	   
         	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	modulos.add(modulos_RS.getString("Modulos_id")+"/"+modulos_RS.getString("Modulos_nombre")+"/"+modulos_RS.getString("estados_vig_novig_id")+"/"+modulos_RS.getString("estados_vig_novig_nombre"));
         	    
     	    }
 		  System.out.println("SIZE LIST: "+modulos.size());
 		    	
 		 modulos_RS.close();
 		  state.close();
 	          conexion.close();
                 
           String[] ar_modulos = new String[modulos.size()];
           for(int x=0; x < modulos.size(); x++){
        	   ar_modulos[x]=modulos.get(x);
           }
                 
           request.setAttribute("ar_modulos", ar_modulos);
          
		}catch(Exception ex){
		    out.println("Unable to connect to database "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("cmanual.jsp");
        rd.forward(request, response);
		
	}

}
