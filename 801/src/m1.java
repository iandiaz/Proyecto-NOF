	

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
 * Servlet implementation class m1
 */
@WebServlet("/m1")
public class m1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m1() {
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
		    	if(cookie.getName().equals("perfilusu_id") && cookie.getValue()!=null && !cookie.getValue().equals("")) perf_in_session=true;
		    	
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
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Statement state_fac = null;
		ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		try {
			String TDTE=request.getParameter("tipo_dte");
			//System.out.println(TDTE);
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_fac = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		
    		 String id_e = request.getParameter("id_e");
    		 String id_em="";
    		 if(id_e!=null){
    			 id_em=id_e;
    			 id_e="AND f.801_empresa_emisora='"+id_e+"'"; 
    		 }
    		 else{
    			 id_em="123";
    			 id_e="AND f.801_empresa_emisora='123'"; 
    		 }
    		
    		
    		String SQL_DATOS = "SELECT f.801_id as id, c.empresas_razonsocial as clpr_razon_social, f.801_ESTADO as est, f.801_TOTAL as tot,"
    		 			+ " DATE_FORMAT(f.801_FECHA, '%d-%m-%Y') AS fecha,f.estados_vig_novig_id"
    		 			+ " FROM `801` f INNER JOIN empresas c ON f.CLPR_ID = c.empresas_id "
    		 			+ "	WHERE f.id_dte IS NULL "+id_e
    		 			+ "	ORDER BY f.801_FECHA DESC";
    		System.out.println(SQL_DATOS);
 		   
    			
    		Alertas_RS =  state.executeQuery(SQL_DATOS);
 		    ArrayList<String> alertas = new ArrayList<String>();
 		   while(Alertas_RS.next()){
 			  String d1 = Alertas_RS.getString("id"); String d2 = Alertas_RS.getString("clpr_razon_social"); String d3 = Alertas_RS.getString("tot");
 			  String d4 = Alertas_RS.getString("fecha"); 
 			 String d5=Alertas_RS.getString("estados_vig_novig_id");
 			 alertas.add(d1+"/"+d2+"/"+d3+"/"+d4+"/"+d5);
 			
 		   }
 		   
 		   
                
		    String[] ar_alertas = new String[alertas.size()];
		    for(int x=0; x < alertas.size(); x++){
		    	//System.out.println(alertas.get(x));
		    	ar_alertas[x]=alertas.get(x);
		    }
            request.setAttribute("ar_alertas", ar_alertas);
            request.setAttribute("tdte", TDTE);
            
            
            
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
    		    
    		    
    		    Alertas_RS.close();
    		    state.close();
    		    conexion.close();
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("m1.jsp");
        rd.forward(request, response);
		
		}

}
