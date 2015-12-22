

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
 * Servlet implementation class mres_search
 */
@WebServlet("/mres_search")
public class mres_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mres_search() {
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
			    	//System.out.println("cookie logout: "+cookie.getName());
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
		Statement statedet = null;
		ResultSet clpr_rs = null;
		ResultSet RS_DET = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statedet = (Statement) ((java.sql.Connection) conexion).createStatement();
    		

     		 String id_e = request.getParameter("id_e");
     		 String id_em="";
     		 if(id_e!=null){
     			 id_em=id_e;
     			 id_e="AND `g`.`802_empresa_emisora`='"+id_e+"'"; 
     		 }
     		 else{
     			 id_em="123";
     			 id_e="AND `g`.`802_empresa_emisora`='123'"; 
     		 }
   		
    		
		    String SQL_clpr = "SELECT g.802_id, e.empresas_nombrenof, g.estados_vig_novig_id, s.estados_vig_novig_nombre, g.802_total "
		    		+ " FROM `802` g"
		    		+ " INNER JOIN empresas e ON e.empresas_id=g.cliente_id"
		    		+ " INNER JOIN estados_vig_novig s ON s.estados_vig_novig_id=g.estados_vig_novig_id"
		    		+ "	WHERE g.id_dte IS NULL "+id_e
		    		+ " ORDER BY g.802_id";
		    System.out.println(SQL_clpr);
		    clpr_rs =  state.executeQuery(SQL_clpr);
		    ArrayList<String> estado_clpral = new ArrayList<String>();
		    while(clpr_rs.next()){
		    	
			    	estado_clpral.add(clpr_rs.getString("802_id")+"/"+clpr_rs.getString("empresas_nombrenof")+"/"+clpr_rs.getString("estados_vig_novig_id")
		    			+"/"+clpr_rs.getString("estados_vig_novig_nombre")+"/"+clpr_rs.getString("802_total"));
			    
    	    }	
		 
          String[] ar_clpr = new String[estado_clpral.size()];
          for(int x=0; x < estado_clpral.size(); x++){
        	  //System.out.println(estado_clpral.get(x));
        	  ar_clpr[x]=estado_clpral.get(x);
          }
                
          request.setAttribute("ar_clpr", ar_clpr);
          
          
          //--------------------- EMISOR ----------------------//
  		    String SQL_EMPRESA = "SELECT * FROM empresas WHERE estados_vig_novig_id = 1 AND empresas_relacionada = 1 ORDER BY empresas_nombre";
  		    ResultSet EMPRESAS_RS = state.executeQuery(SQL_EMPRESA);
  		    ArrayList<String> empresas = new ArrayList<String>();
  		    while(EMPRESAS_RS.next()){ empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombre")); }
  		    EMPRESAS_RS.close();
  		    String[] ar_empresas = new String[empresas.size()];
  		    for(int x=0; x < empresas.size(); x++){ ar_empresas[x]=empresas.get(x);}
  		    request.setAttribute("ar_empresas", ar_empresas);
  		    //----------------------- FIN ------------------------//
  		    request.setAttribute("id_e", id_em);
  		    
  		  clpr_rs.close();
		  state.close();
          conexion.close();
                
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("mres_search.jsp");
        rd.forward(request, response);
		
	}

}
