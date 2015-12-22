

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
 * Servlet implementation class mpro
 */
@WebServlet("/mpro")
public class mpro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mpro() {
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
		
		//grabar

		if(request.getParameter("grabar") != null){
			Statement state = null;	
			ResultSet RS_CLIENTE = null;
			try {
				String clpr_id=request.getParameter("clpr_id");
				String f1="",f2="";
				if(!request.getParameter("f1").isEmpty() && !request.getParameter("f1").isEmpty()){
					f1=request.getParameter("f1");
					String d=f1.substring(0, 2);
					String m=f1.substring(3, 5);
					String y=f1.substring(6, 10);
					f1=y+"-"+m+"-"+d+" 00:00:00";
					f2=request.getParameter("f2");
					String dia=f2.substring(0, 2);
					String m1=f2.substring(3, 5);
					String y1=f2.substring(6, 10);
					f2=y1+"-"+m1+"-"+dia+" 23:59:59";
				}
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		int dato=0;
	    		String SQL_Cliente = "SELECT * FROM periodos_tc WHERE peri_tc_id_emp='"+clpr_id+"' ORDER BY peri_tc_correlativo DESC"; 
	    		System.out.println(SQL_Cliente);
	 		    RS_CLIENTE =  state.executeQuery(SQL_Cliente);
	 		    if(RS_CLIENTE.next()){;
	 		    	dato=RS_CLIENTE.getInt("peri_tc_correlativo")+1;
	 		    }else{
	 		    	dato=1;
	 		    }
	 		    
			    //INSERTAMOS TODOS LOS DATOS EN NOF EN EL DETALLE DE LA FACTURA
				String SQL_PROG = "INSERT INTO periodos_tc (peri_tc_id_emp, peri_tc_fdesde, peri_tc_fhasta, peri_tc_correlativo,peri_tc_feccreacion,peri_tc_creador) "
				    			+ "VALUES ("+clpr_id+",'"+f1+"','"+f2+"',"+dato+",NOW(),"+Usuarios_ID+")";
				//System.out.println("SQL Instert: "+SQL_FACDET);
				state.executeUpdate(SQL_PROG);
	        	
	        	RequestDispatcher rd_up = request.getRequestDispatcher("menupro?Exito=OK");
	        	rd_up.forward(request, response);
	        	//return;
	        	
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("ERROR: "+ex);
			}
	  
			
		}else{
		
			try {
				Statement state = null;
				Statement statecli = null;
				ResultSet GUIAS_RS = null;
				ResultSet RS_CLIENTE = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		statecli = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String clpr_id = request.getParameter("clpr_id");
	            
	    		String SQL_Cliente = "SELECT *, DATE_FORMAT(now(), '%d-%m-%Y') as hoy FROM `empresas` "
	    				+ "	WHERE"
	    				+ "		`empresas`.`empresas_escliente` = 1"
	    				+ "	AND `empresas`.`estados_vig_novig_id` = 1"
	    				+ "	AND `empresas`.`empresas_id` = '"+clpr_id+"'"; 
	    		System.out.println(SQL_Cliente);
	 		    RS_CLIENTE =  statecli.executeQuery(SQL_Cliente);
	 		    RS_CLIENTE.next();
	 		    request.setAttribute("clpr_razon_social", RS_CLIENTE.getString("empresas_razonsocial"));
	 		    request.setAttribute("clpr_id", RS_CLIENTE.getString("empresas_id"));
	 		    request.setAttribute("hoy", RS_CLIENTE.getString("hoy"));
	 		    
	    		String SQL_Guias = "SELECT peri_tc_correlativo, "
	    				+ " DATE_FORMAT(peri_tc_fdesde, '%d-%m-%Y') as peri_tc_fdesde,"
	    				+ " DATE_FORMAT(peri_tc_fhasta, '%d-%m-%Y') as peri_tc_fhasta"
	    				+ " FROM periodos_tc WHERE peri_tc_id_emp='"+clpr_id+"' and estados_vig_novig_id=1 ORDER BY peri_tc_correlativo DESC"; 
	    		//System.out.println(SQL_Guias);
	 		    GUIAS_RS =  state.executeQuery(SQL_Guias);
	 		    ArrayList<String> prod_res = new ArrayList<String>();
	 		    int count=0;
			    while(GUIAS_RS.next()){ 
			    	
			    	if(count==0){
			    		 request.setAttribute("fec_limite", GUIAS_RS.getString("peri_tc_fhasta"));
			    	}
			    	
			    	prod_res.add(GUIAS_RS.getString("peri_tc_correlativo")+"/"+GUIAS_RS.getString("peri_tc_fdesde")+"/"+GUIAS_RS.getString("peri_tc_fhasta"));
			    count++;}
	 		    GUIAS_RS.close();
	 		    state.close();
	 		    
	 		    String[] ar_productos = new String[prod_res.size()];
	 		    for(int x=0; x < prod_res.size(); x++){
	 		    	ar_productos[x]=prod_res.get(x);
	 		    }
	 		    request.setAttribute("ar_productos", ar_productos);
	           
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("ipro.jsp");
        rd.forward(request, response);
		
		}
		
	}

}
