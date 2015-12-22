

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
 * Servlet implementation class cpro_ver
 */
@WebServlet("/cpro_ver")
public class cpro_ver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cpro_ver() {
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
			    	//System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
		}
		//fin logout

		String Usuarios_nombre="";
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
		
		request.setAttribute("usuname", Usuarios_nombre);
		Statement state2 = null;		
		ResultSet Grupo_RS = null;
		ResultSet Existe_RS = null;
		Statement state3 = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_GRUPO = ""
		    		+ " SELECT ep.estado_periodo_nombre,p.peri_tc_id, m.empresas_id, m.empresas_razonsocial, m.empresas_nombrenof, e.estados_vig_novig_nombre,"
		    		+ " DATE_FORMAT(p.peri_ffac,'%d-%m-%Y') as peri_ffac,"
		    		+ " DATE_FORMAT(p.peri_tc_fdesde,'%d-%m-%Y') as peri_tc_fdesde,"
		    		+ " DATE_FORMAT(p.peri_tc_fhasta,'%d-%m-%Y') as peri_tc_fhasta,"
		    		+ "	CONCAT(u.Usuarios_nombre, ' ', u.Usuarios_ape_p) AS creador, "
		    		+ "	DATE_FORMAT(p.peri_tc_feccreacion,'%d-%m-%Y %H:%i:%s') AS feccreacion, "
		    		+ "	IF (p.peri_tc_fecmod IS NULL,' ',DATE_FORMAT(p.peri_tc_fecmod, '%d-%m-%Y %H:%i:%s')) AS fecmod,"
		    		+ "	IF (p.peri_tc_modificador IS NULL,' ',CONCAT(u2.Usuarios_nombre,' ',u2.Usuarios_ape_p)) AS modificador "
		    		+ " FROM periodos_tc p "
		    		+ " 	INNER JOIN empresas m ON p.peri_tc_id_emp=m.empresas_id"
		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=p.estados_vig_novig_id"
		    		+ " 	INNER JOIN `usuarios` u ON `p`.`peri_tc_creador` = u.`Usuarios_id` "
		    		+ " 	LEFT JOIN `usuarios` u2 ON `p`.`peri_tc_modificador` = `u2`.`Usuarios_id` "
		    		+ " 	LEFT JOIN estado_periodo ep ON ep.estado_periodo_id=p.peri_tc_id_estperiodo "
		    		+ " WHERE peri_tc_id="+Integer.parseInt(request.getParameter("id_p"));
		    //System.out.println(SQL_GRUPO);
		    Grupo_RS =  state2.executeQuery(SQL_GRUPO);
		    if(Grupo_RS.next()){ 
		    	
		    	request.setAttribute("peri_tc_id",Grupo_RS.getString("peri_tc_id"));
		    	request.setAttribute("empresas_id",Grupo_RS.getString("empresas_id"));
		    	request.setAttribute("empresas_razonsocial",Grupo_RS.getString("empresas_razonsocial"));
		    	request.setAttribute("peri_tc_fdesde",Grupo_RS.getString("peri_tc_fdesde"));
		    	request.setAttribute("peri_tc_fhasta",Grupo_RS.getString("peri_tc_fhasta"));
		    	request.setAttribute("peri_ffac",Grupo_RS.getString("peri_ffac"));
		    	request.setAttribute("empresas_nombrenof",Grupo_RS.getString("empresas_nombrenof"));
		    	request.setAttribute("estados_vig_novig_nombre",Grupo_RS.getString("estados_vig_novig_nombre"));
		    	
		    	request.setAttribute("user_crea",Grupo_RS.getString("creador"));
		    	request.setAttribute("fec_crea",Grupo_RS.getString("feccreacion"));
		    	request.setAttribute("user_mod",Grupo_RS.getString("modificador"));
		    	request.setAttribute("fec_mod",Grupo_RS.getString("fecmod"));
		    	request.setAttribute("estado_periodo_nombre",Grupo_RS.getString("estado_periodo_nombre"));
		    	
    	    }
		  Grupo_RS.close();
		  state2.close();	    
          conexion.close();
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Unable to connect to database "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("cpro_ver.jsp");
        rd.forward(request, response);
		
	}

}
