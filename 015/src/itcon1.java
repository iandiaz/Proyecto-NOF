

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
 * Servlet implementation class itcon1
 */
@WebServlet("/itcon1")
public class itcon1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public itcon1() {
        // TODO Auto-generated constructor stub
    }

public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
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
		String id_iusuario="";
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    }
		}
		//grabar
		boolean grupoexiste=false;
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet rs_valgrupos= null;
			try {
				String grupos_nombre = request.getParameter("tcon_nombre");
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String sql_valgrupo="SELECT * FROM tipo_contacto WHERE TICON_DESCRIPCION='"+grupos_nombre.toUpperCase()+"'";
	    		rs_valgrupos=stateval.executeQuery(sql_valgrupo);
	    		//stateval.close();
	    		if(rs_valgrupos.next()){
	    			stateval.close();
	    			rs_valgrupos.close();conexion.close();
	    			grupoexiste=true;
	    			//response.sendRedirect("Igrupo?Exito=NOK");
	    		}else{
	    			
	    			if(Constantes.SYNCDB==1){
	       			 ConBirt birtBD= new ConBirt();
	       			 
	       			 String estado_v_nv="VIGENTE";
	       			 if(estados_vig_novig_id.equals("2")) estado_v_nv="NO VIGENTE";
	       			 
	       			 String insert_birt=" INSERT INTO [TIPO_CONTACTO] ("
	       			 		+ "		[TIPO_CONTACTO].[TICON_DESCRIPCION],"
	       			 		+ "		[TIPO_CONTACTO].[TICON_ESTADO],"
	       			 		+ "		[TIPO_CONTACTO].[TICON_FECHA_UM],"
	       			 		+ "		[TIPO_CONTACTO].[USU_ID_UM]"
	       			 		+ "	)"
	       			 		+ "	VALUES"
	       			 		+ "		('"+grupos_nombre.toUpperCase()+"','"+estado_v_nv+"',GETDATE(),'"+id_iusuario+"')";
	       			System.out.println("BIRT: "+insert_birt);
	       		      birtBD.ConsultaINUP(insert_birt);
	       		      
	       		        birtBD.Desconectar();
	       			}
	    			
	    			
			    String SQL_INSERT = "INSERT INTO tipo_contacto (ticon_ult_idper_exec,TICON_DESCRIPCION, estados_vig_novig_id,ticon_feccreacion,ticon_creador) VALUES (128,'"+grupos_nombre.toUpperCase()+"',"+estados_vig_novig_id+",now(),"+id_iusuario+")";
			    System.out.println(SQL_INSERT);
			    stategrabar.executeUpdate(SQL_INSERT);
			    stateval.close();
    			rs_valgrupos.close();
			    stategrabar.close();
	            conexion.close(); 
	            
	            
	            
	            out.print("<script>location='/015/?Exito=OK'</script>");
	    		 return;
	    		}
			}catch(Exception ex){
			    out.println("ERROR "+ex);
			}
			if(!grupoexiste){return;}
		}
		
		//fin grabar
		
		
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
		Statement statecor = null;		
		ResultSet ESTADOS_RS = null;
		ResultSet CORRELATIVO_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
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
          
          String[] ar_estados = new String[estados.size()];
          for(int x=0; x < estados.size(); x++){
           	ar_estados[x]=estados.get(x);
          }
          request.setAttribute("ar_estados", ar_estados);
          //id correlativo
          String last_id_grupos_sql="SELECT"
		    		+ " 	`tipo_contacto`.`TICON_ID`"
		    		+ " FROM"
		    		+ " 	`tipo_contacto`"
		    		+ " ORDER BY"
		    		+ " 	`tipo_contacto`.`TICON_ID` DESC"
		    		+ " LIMIT 1";
          System.out.println("SQL: "+last_id_grupos_sql);
          CORRELATIVO_RS =  statecor.executeQuery(last_id_grupos_sql);
         
          int correlativo=1;
		  if(CORRELATIVO_RS.next()) correlativo=CORRELATIVO_RS.getInt("TICON_ID")+1;
		  System.out.println("correlativo :  "+correlativo);
          request.setAttribute("correlativo", correlativo+"");
          
          statecor.close();
          conexion.close();
          
		}catch(Exception ex){
		    out.println("ERROR: "+ex);
		}
		
		String msg="";
		if(grupoexiste){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String grupos_nombre = request.getParameter("tcon_nombre");
			request.setAttribute("grupos_nombre",grupos_nombre);
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("itcon1.jsp"+msg);
        rd.forward(request, response);
		
	}

}