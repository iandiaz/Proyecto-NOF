

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
 * Servlet implementation class inc1
 */
@WebServlet("/inc1")
public class inc1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inc1() {
        super();
        // TODO Auto-generated constructor stub
    }
    
public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		boolean perf_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
		    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
		    	if(cookie.getName().equals("perfilusu_id") && cookie.getValue()!=null) perf_in_session=true;
		    	
		    }
		}
		
		
		if(user_in_session && username_in_session && perf_in_session) user_in_session=true;
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
	ResultSet clpr_rs = null;
	try {
		//import java.io.IOException;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion=(Connection) DriverManager.getConnection
	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
		
	    String SQL_clpr = "SELECT "
	    		+ "		`empresas`.`empresas_id`,"
	    		+ "		`empresas`.`empresas_nombrenof`,"
	    		+ "		`estados_vig_novig`.`estados_vig_novig_nombre`, "
	    		+ "		`estados_vig_novig`.`estados_vig_novig_id` "
	    		+ "	FROM"
	    		+ "		`empresas`"
	    		+ "	INNER JOIN `estados_vig_novig` ON `estados_vig_novig`.`estados_vig_novig_id` = `empresas`.`estados_vig_novig_id` "
	    		+ " ORDER BY `empresas`.`empresas_nombrenof` ";
	    
	    System.out.println(SQL_clpr);
	    clpr_rs =  state.executeQuery(SQL_clpr);
	    ArrayList<String> estado_clpral = new ArrayList<String>();
	    String cuenta="0";
	    while(clpr_rs.next()){
	    	cuenta="1";
	    	//System.out.println(SQL_clpr);
	    	estado_clpral.add(clpr_rs.getString("empresas_id")+"/"+clpr_rs.getString("empresas_nombrenof")+"/"+clpr_rs.getString("estados_vig_novig_id")+"/"+clpr_rs.getString("estados_vig_novig_nombre"));
	    }	
	  clpr_rs.close();
	  state.close();
      conexion.close();
            
      String[] ar_clpr = new String[estado_clpral.size()];
      for(int x=0; x < estado_clpral.size(); x++){
    	  ar_clpr[x]=estado_clpral.get(x);
      }
            
      request.setAttribute("ar_clpr", ar_clpr);
      request.setAttribute("cuenta", cuenta);
	}catch(Exception ex){
		ex.printStackTrace();
	    out.println("Error: "+ex);
	}
	RequestDispatcher rd = request.getRequestDispatcher("inc1.jsp");
    rd.forward(request, response);
	
}
}
