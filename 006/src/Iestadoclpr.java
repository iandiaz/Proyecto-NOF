

import java.io.FileOutputStream;
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
 * Servlet implementation class Iestadoclpr
 */
@WebServlet("/Iestadoclpr")
public class Iestadoclpr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Iestadoclpr() {
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
		System.out.println("ingresando a funcion mt");
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
		
		//calendario
		
		String Usuarios_nombre="";
		
		//fecha 
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String Datenow=ahoraCal.get(Calendar.YEAR)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.DATE)+" "+ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String Usuarios_ID="";
		//System.out.print(Datenow);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) { Usuarios_ID=cookie.getValue();}
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		//System.out.print("Usuarios_ID: "+Usuarios_ID);
		// end calendario
		
		
		
		
		//grabar
		boolean eclpr=false;
		if(request.getParameter("grabar") !=  null){
			System.out.println("ingresando al grabar");
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet rs_valEstclpr= null;
			try {
				String Estado_Clpr_nombre = request.getParameter("Estado_Clpr_nombre");
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
		    	
		    	
		    	if(Constantes.SYNCDB==1){
		    		//insertamos en birt
	    			 ConBirt birtBD= new ConBirt();
	    			 
	    			 String estado_v_nv="VIGENTE";
	    			 if(estados_vig_novig_id.equals("2")) estado_v_nv="NO VIGENTE";
	    			 
	    			 String insert_birt="INSERT INTO [ESTADO_CLPR] ("
	    			 		+ "		[ESTADO_CLPR].[ESTCLPR_DESCRIPCION],"
	    			 		+ "		[ESTADO_CLPR].[ESTCLPR_ESTADO],"
	    			 		+ "		[ESTADO_CLPR].[USU_ID_UM],"
	    			 		+ "		[ESTADO_CLPR].[ESTCLPR_FECHA_UM]"
	    			 		+ "	)"
	    			 		+ "	VALUES"
	    			 		+ "		('"+Estado_Clpr_nombre.toUpperCase()+"', '"+estado_v_nv+"', "+Usuarios_ID+", getdate())";
	    			 System.out.println("BIRT: "+insert_birt);
	    		      birtBD.ConsultaINUP(insert_birt);
	    		     
	    		      
	    		        birtBD.Desconectar();
	    			}
	    			 
		    	
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		
	    		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String sql_valEstclpr="SELECT * FROM estado_clpr WHERE Estado_Clpr_nombre='"+Estado_Clpr_nombre.toUpperCase()+"'";
	    		System.out.println("sql_valEstclpr "+sql_valEstclpr);
	    		rs_valEstclpr=stateval.executeQuery(sql_valEstclpr);
	    		
	    		if(rs_valEstclpr.next()){
	    			stateval.close();
	    			rs_valEstclpr.close();conexion.close();
	    			eclpr=true;
	    			
	    		}else{
			    String SQL_INSERT = "INSERT INTO estado_clpr (Estado_Clpr_nombre, Estado_Clpr_feccreacion, Estado_Clpr_creador,Estado_Clpr_accion_alertada,Estado_Clpr_ult_idper_exec,estados_vig_novig_id) VALUES('"+Estado_Clpr_nombre.toUpperCase()+"','"+Datenow+"','"+Usuarios_ID+"',0,12,"+estados_vig_novig_id+")";
			    System.out.print("SQL_INSERT estadoclpr : "+SQL_INSERT+"\n");
			    stategrabar.executeUpdate(SQL_INSERT);
			    stategrabar.close();
			    conexion.close();
			    
			   
			    
	            RequestDispatcher rd_up = request.getRequestDispatcher("Menuestadoclpr?Exito=OK");
	            rd_up.forward(request, response);
			}
			}catch(Exception ex){
			    out.println("Unable to connect to database "+ex);
			}
			if(!eclpr)return;
		}
		
		//fin grabar
		
		
		
		Statement state = null;
		ResultSet ESTADOS_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> estados = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(ESTADOS_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
        	    
    	    }
		  System.out.println("SIZE LIST: "+estados.size());
		    	
		  ESTADOS_RS.close();
		  state.close();
		  
		//id cor
  		Statement statecor = null;
  		ResultSet CORRELATIVO_RS = null;
  		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
  		 String last_id_grupos_sql="SELECT"
  		    		+ " 	`estado_clpr`.`Estado_Clpr_id`"
  		    		+ " FROM"
  		    		+ " 	`estado_clpr`"
  		    		+ " ORDER BY"
  		    		+ " 	`estado_clpr`.`Estado_Clpr_id` DESC"
  		    		+ " LIMIT 1";
         System.out.println("correlativo : no pasa na"+last_id_grupos_sql);
         CORRELATIVO_RS =  statecor.executeQuery(last_id_grupos_sql);
        
         int correlativo=0;
  		  if(CORRELATIVO_RS.next()) correlativo=CORRELATIVO_RS.getInt("Estado_Clpr_id")+1;
  		  System.out.println("correlativo : no pasa na2 "+correlativo);
         request.setAttribute("correlativo", correlativo+"");
         
         statecor.close();		  
          conexion.close();
                
          String[] ar_estados = new String[estados.size()];
          for(int x=0; x < estados.size(); x++){
           	ar_estados[x]=estados.get(x);
          }
                
          request.setAttribute("ar_estados", ar_estados);
		}catch(Exception ex){
		    out.println("Unable to connect to database "+ex);
		}
		String msg="";
		if(eclpr){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String Estado_Clpr_nombre = request.getParameter("Estado_Clpr_nombre");
			request.setAttribute("Estado_Clpr_nombre",Estado_Clpr_nombre);
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			System.out.println("Estado_Clpr_nombre "+Estado_Clpr_nombre);
		}
		RequestDispatcher rd = request.getRequestDispatcher("iestadoclpr.jsp"+msg);
        rd.forward(request, response);
		
	}

}
