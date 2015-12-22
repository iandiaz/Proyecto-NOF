

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
 * Servlet implementation class Cgrupo
 */
@WebServlet("/Cgrupo")
public class Cgrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cgrupo() {
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
		ResultSet Grupos_RS = null;
		ResultSet grupos_empresa_rs = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    //consultamos por los grupos
    		 String SQL_Grupos = ""
		    		+ "SELECT *"
		    		+ " FROM grupos g "
		    		+ "INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=g.estados_vig_novig_id "
		    		+ " ORDER BY g.grupos_nombre";
		    System.out.println("SQL_Grupos : "+SQL_Grupos);
		    Grupos_RS =  state.executeQuery(SQL_Grupos);
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> grupos = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(Grupos_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	grupos.add(Grupos_RS.getString("grupos_id")+"/"+Grupos_RS.getString("grupos_nombre")+"/"+Grupos_RS.getString("estados_vig_novig_id")+"/"+Grupos_RS.getString("estados_vig_novig_nombre"));
        	    
    	    }
		  System.out.println("SIZE LIST: "+grupos.size());
		    	
		  Grupos_RS.close();
		  state.close();
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		//traemos las empresas con su id_grupo (asociacion) y nombre de empresa
		  String SQL_grupo_empresa = ""
		    		+ "SELECT *"
		    		+ " FROM empresas "
		    		+ " ORDER BY empresas_nombre";
		   System.out.println("SQL_grupo_empresa : "+SQL_grupo_empresa);
		   grupos_empresa_rs =  state.executeQuery(SQL_grupo_empresa);
		   ArrayList<String> grupo_emp = new ArrayList<String>();
		   //recorremos los resultados de la consulta
		    while(grupos_empresa_rs.next()){       	   
       	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	grupo_emp.add(grupos_empresa_rs.getString("grupos_id")+"/"+grupos_empresa_rs.getString("empresas_nombre"));       	    
		    }
		  
		    grupos_empresa_rs.close();
		    state.close();
          conexion.close();
                
          String[] ar_grupos = new String[grupos.size()];
          for(int x=0; x < grupos.size(); x++){
        	  ar_grupos[x]=grupos.get(x);
          }
          
          String[] ar_grupos_emp = new String[grupo_emp.size()];
          for(int x=0; x < grupo_emp.size(); x++){
        	  ar_grupos_emp[x]=grupo_emp.get(x);
          }
                
          request.setAttribute("arGrupos", ar_grupos);
          request.setAttribute("ar_grupos_emp", ar_grupos_emp);
		}catch(Exception ex){
		    out.println("Unable to connect to database "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("cgrupo.jsp");
        rd.forward(request, response);
		
	}

}
