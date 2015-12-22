

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
 * Servlet implementation class mpro_tomas
 */
@WebServlet("/mpro_tomas")
public class mpro_tomas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mpro_tomas() {
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
		ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		try {
			String empresas_id = request.getParameter("empresas_id");
			
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		
    		
    		
    		//traemos primero los datos de la empresa sleccionada
    		
    		
   		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
   		 String SQL_emps = "select `empresas`.`empresas_nombrenof`,`empresas`.`empresas_razonsocial` from `empresas` where `empresas`.`empresas_id`="+empresas_id+"";
   	
   		 System.out.println(SQL_emps);
   		 ResultSet emps_RS = state.executeQuery(SQL_emps);	
   		
   		 emps_RS.next();
   		
   			request.setAttribute("empresas_razonsocial", emps_RS.getString("empresas_razonsocial"));
 	 		  request.setAttribute("empresas_nombrenof", emps_RS.getString("empresas_nombrenof"));
   	
    		
    		
    		
    		
    		
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 String SQL_Alertas = "SELECT ep.estado_periodo_id,ep.estado_periodo_nombre,p.peri_tc_correlativo,p.peri_tc_id, m.empresas_razonsocial,m.empresas_nombrenof, e.estados_vig_novig_id, e.estados_vig_novig_nombre," 		+ " DATE_FORMAT(p.peri_tc_fdesde, '%d-%m-%Y') as peri_tc_fdesde,"
    		 		+ " DATE_FORMAT(p.peri_tc_fhasta, '%d-%m-%Y') as peri_tc_fhasta,"
    		 		+ " DATE_FORMAT(p.peri_ffac, '%d-%m-%Y') as peri_ffac"
    		 		+ " FROM periodos_tc p"
    				+ " INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=p.estados_vig_novig_id "
    				+ " INNER JOIN empresas m ON p.peri_tc_id_emp=m.empresas_id "
    				+ " INNER JOIN estado_periodo ep ON ep.estado_periodo_id=p.peri_tc_id_estperiodo "
    				+ " WHERE p.peri_tc_id_emp = "+empresas_id+""
    		 		+ " ORDER BY peri_tc_correlativo DESC";
    	
		    if(request.getAttribute("filtro")!=null){
		    	request.setAttribute("filtro",request.getAttribute("filtro"));
		    }
		    /*System.out.println("Consulta: "+SQL_Alertas);*/
		    Alertas_RS =  state.executeQuery(SQL_Alertas);
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> alertas = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(Alertas_RS.next()){        	   
		    	alertas.add(Alertas_RS.getString("peri_tc_id")+"/"+
		    				Alertas_RS.getString("empresas_razonsocial")+"/"+
		    				Alertas_RS.getString("peri_tc_fdesde")+"/"+Alertas_RS.getString("peri_tc_fhasta")+"/"+
		    				Alertas_RS.getString("estados_vig_novig_id")+"/"+
		    				Alertas_RS.getString("estados_vig_novig_nombre")+"/"+
		    				Alertas_RS.getString("peri_ffac")+"/"+
		    				Alertas_RS.getString("peri_tc_correlativo")+"/"+
		    				Alertas_RS.getString("estado_periodo_nombre")+"/"+Alertas_RS.getString("estado_periodo_id"));
		    	
    	    }
		    Alertas_RS.close();
		  state.close();
          conexion.close();
                
          String[] ar_alertas = new String[alertas.size()];
          for(int x=0; x < alertas.size(); x++){ 
        	  ar_alertas[x]=alertas.get(x);
          }
             
          request.setAttribute("ar_alertas", ar_alertas);
		}catch(Exception ex){
			ex.printStackTrace();
			out.println("ERROR: "+ex);
		
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("mpro_tomas.jsp");
        rd.forward(request, response);
		
	}

}
