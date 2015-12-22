

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
 * Servlet implementation class ctc1
 */
@WebServlet("/ctc1")
public class ctc1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ctc1() {
        super();
        // TODO Auto-generated constructor stub
    }

    public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	//System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
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
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		 String SQL_Alertas = "SELECT * FROM empresas WHERE `empresas`.`estados_vig_novig_id`=1 AND `empresas`.`empresas_escliente`=1  ORDER BY empresas_nombrenof";
			    
			    if(request.getAttribute("filtro")!=null){
			    	request.setAttribute("filtro",request.getAttribute("filtro"));
			    }
			    /*System.out.println("Consulta: "+SQL_Alertas);*/
			    Alertas_RS =  state.executeQuery(SQL_Alertas);
			    //definimos un arreglo para los resultados
			    
			    ArrayList<String> alertas = new ArrayList<String>();
			   
			    //recorremos los resultados de la consulta
			    while(Alertas_RS.next()){        	   
			    	alertas.add(Alertas_RS.getString("empresas_id")+"/"+Alertas_RS.getString("empresas_nombrenof"));
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
				out.println("ERROR: "+ex);
			   ex.printStackTrace();
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("ctc1.jsp");
	        rd.forward(request, response);
	}

}
