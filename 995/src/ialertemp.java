

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
 * Servlet implementation class ialertemp
 */
@WebServlet("/ialertemp")
public class ialertemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ialertemp() {
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
		boolean alertaexiste=false;
		if(request.getParameter("grabar") != null){
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet RS_ADDDATO= null;
			Statement state_lastid = null;	
			Statement stategrabar2 = null;	
			ResultSet LASTID_RS = null;
			try {
				String alertemp_nombre = request.getParameter("alertemp_nombre");
		    	String alertemp_minutos = request.getParameter("alertemp_minutos");
		    	String alertemp_minutos_revisar = request.getParameter("alertemp_minutos_revisar");
		    	
		    	
		    	
		    	
		    	String ticket_estados_id = request.getParameter("ticket_estados_id");
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
                String alertas_temp_descripcion=request.getParameter("alertemp_desc");
                String tipo_ticket_id=request.getParameter("tipo_ticket");
                String alertas_temp_notiftype=request.getParameter("notif_tipe");
                
                
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		state_lastid = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stategrabar2 = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String SQL_ADDDATO="SELECT * FROM alertas_temp WHERE alertas_temp_nombre='"+alertemp_nombre.toUpperCase()+"'";
	    		RS_ADDDATO=stateval.executeQuery(SQL_ADDDATO);
	    		//stateval.close();
	    		if(RS_ADDDATO.next()){
	    			stateval.close();
	    			RS_ADDDATO.close();conexion.close();
	    			alertaexiste=true;
	    			//response.sendRedirect("Igrupo?Exito=NOK");
	    		}else{
			    String SQL_INSERT = "INSERT INTO alertas_temp (alertas_temp_nombre, alertas_temp_minutos,alertas_temp_minutos_revisar, ticket_estados_id, alertas_temp_descripcion, tipo_ticket_id,alertas_temp_notiftype ,alertas_temp_accion_alertada,alertas_temp_ult_idper_exec,estados_vig_novig_id, alertas_temp_feccreacion, alertas_temp_creador) "
			    		+ "VALUES ('"+alertemp_nombre.toUpperCase()+"',"+alertemp_minutos+","+alertemp_minutos_revisar+","+ticket_estados_id+",'"+alertas_temp_descripcion+"',"+tipo_ticket_id+","+alertas_temp_notiftype+",0,43,"+estados_vig_novig_id+",now(),"+id_iusuario+")";
			    System.out.println("SQL Instert: "+SQL_INSERT);
			    stategrabar.executeUpdate(SQL_INSERT);
			    stateval.close();
			    RS_ADDDATO.close();
			    stategrabar.close();
		          
			    String last_id_sql="SELECT MAX(alertas_temp_id) as ultimo_id FROM alertas_temp "
			    		+ "WHERE estados_vig_novig_id = 1 order by ultimo_id desc limit 1";
			    System.out.println("ULTIMO REGISTRO: "+last_id_sql);
			    LASTID_RS =  state_lastid.executeQuery(last_id_sql);
		        int max_last_id=0;
				if(LASTID_RS.next()) max_last_id=LASTID_RS.getInt("ultimo_id");

			    
				 String[] seleccionado = request.getParameterValues("permisos[]");
		    		if(seleccionado!=null && seleccionado.length >0 )for (String id_per: seleccionado) {
		    			if(id_per!=null && !id_per.equals("-1")){
		    				String insertusuemp=""
			    					+ " INSERT INTO gruposusu_alertas_temp ("
			    					+ " 	gruposusu_id, alertas_temp_id "
			    					+ " ) "
			    					+ " VALUES"
			    					+ " 	('"+id_per+"', '"+max_last_id+"')";
		    				System.out.println("INSERTA REGISTRO: "+insertusuemp);
		    				stategrabar2.executeUpdate(insertusuemp);
		    			}	    			
		    		}
			    
		    	stategrabar2.close(); 
		        conexion.close();
	            
	            
	            RequestDispatcher rd_up = request.getRequestDispatcher("menualertemp?Exito=OK");
	            rd_up.forward(request, response);
	    		}
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
                ex.printStackTrace();
			}
			if(!alertaexiste){return;}
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
		Statement state_estado_ticket = null;
		ResultSet ESTADOS_RS = null;
		ResultSet CORRELATIVO_RS = null;
		ResultSet ESTADOS_TICKET_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
            System.out.println(SQL_ESTADOS);
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
                
          String[] ar_estados = new String[estados.size()];
          for(int x=0; x < estados.size(); x++){
           	ar_estados[x]=estados.get(x);
          }
          request.setAttribute("ar_estados", ar_estados);
          
    		state_estado_ticket = (Statement) ((java.sql.Connection) conexion).createStatement();
     		
 		    String SQL_ESTADOS_TICKET = "SELECT * FROM ticket_estados ORDER BY ticket_estados_nombre";
            System.out.println(SQL_ESTADOS_TICKET);
 		    ESTADOS_TICKET_RS =  state_estado_ticket.executeQuery(SQL_ESTADOS_TICKET);
 		    
 		    //definimos un arreglo para los resultados
 		    
 		    ArrayList<String> estados_ticket = new ArrayList<String>();
 		   
 		    //recorremos los resultados de la consulta
 		    while(ESTADOS_TICKET_RS.next()){        	   
         	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	estados_ticket.add(ESTADOS_TICKET_RS.getString("ticket_estados_id")+"/"+ESTADOS_TICKET_RS.getString("ticket_estados_nombre"));
         	    
     	    }
 		  System.out.println("SIZE LIST: "+estados_ticket.size());
 		    	
 		 ESTADOS_TICKET_RS.close();
 		state_estado_ticket.close();
           
                 
           String[] ar_estados_ticket = new String[estados_ticket.size()];
           for(int x=0; x < estados_ticket.size(); x++){
        	   ar_estados_ticket[x]=estados_ticket.get(x);
           }
           request.setAttribute("ar_estados_ticket", ar_estados_ticket);
          
          
          
          
          
          //id correlativo
          String last_id_grupos_sql="SELECT alertas_temp_id FROM alertas_temp ORDER BY alertas_temp_id DESC LIMIT 1";
          System.out.println("Correlativo: "+last_id_grupos_sql);
          CORRELATIVO_RS =  statecor.executeQuery(last_id_grupos_sql);
         
          int correlativo=0;
		  if(CORRELATIVO_RS.next()) correlativo=CORRELATIVO_RS.getInt("alertas_temp_id")+1;
          request.setAttribute("correlativo", correlativo+"");

          
  		ResultSet RS_GRUPOSUSU = null;
   		//:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
           state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    String SQL_GRUPOSUSU = "SELECT * FROM gruposusu WHERE estados_vig_novig_id=1 ORDER BY gruposusu_nombre";
            System.out.println(SQL_GRUPOSUSU);
		    RS_GRUPOSUSU =  state.executeQuery(SQL_GRUPOSUSU);
		    //definimos un arreglo para los resultados
		    ArrayList<String> gruposusu = new ArrayList<String>();
		    //recorremos los resultados de la consulta
		    while(RS_GRUPOSUSU.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	gruposusu.add(RS_GRUPOSUSU.getString("gruposusu_id")+"/"+RS_GRUPOSUSU.getString("gruposusu_nombre").replace("/", " "));    
    	    }
		    RS_GRUPOSUSU.close();
		 
		  String[] ar_gruposusu = new String[gruposusu.size()];
          for(int x=0; x < gruposusu.size(); x++){
        	  ar_gruposusu[x]=gruposusu.get(x);
          }
                
          request.setAttribute("ar_gruposusu", ar_gruposusu);
            
            
		    //:::::::::::::::::::::::::: sql trae tipos de ticket para select option:::::::::::::::::::::::::::::::::::::
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    String SQL_TIPOTCK = "SELECT "
            + "             `tipo_ticket`.`tipo_ticket_id`, "
            + "             `tipo_ticket`.`tipo_ticket_nombre` "
            + "             FROM "
            + "             `tipo_ticket` ORDER BY tipo_ticket_nombre";
            System.out.println(SQL_TIPOTCK);
		    ResultSet RS_TIPOTCK =  state.executeQuery(SQL_TIPOTCK);
		    //definimos un arreglo para los resultados
		    ArrayList<String> tipotck = new ArrayList<String>();
		    //recorremos los resultados de la consulta
		    while(RS_TIPOTCK.next()){
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	tipotck.add(RS_TIPOTCK.getString("tipo_ticket_id")+"/"+RS_TIPOTCK.getString("tipo_ticket_nombre"));
    	    }
		    RS_TIPOTCK.close();
           
            String[] ar_tipotck = new String[tipotck.size()];
            for(int x=0; x < tipotck.size(); x++){
                ar_tipotck[x]=tipotck.get(x);
            }
            
            request.setAttribute("ar_tipotck", ar_tipotck);

          //fin problamiento campos
          
           state.close();
          statecor.close();
          conexion.close();
          
		}catch(Exception ex){
		    out.println("Unable to connect to database "+ex);
            ex.printStackTrace();
		}
		
		String msg="";
		if(alertaexiste){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String alertas_temp_nombre = request.getParameter("alertas_temp_nombre");
			request.setAttribute("alertas_temp_nombre",alertas_temp_nombre);
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("ialertemp.jsp"+msg);
        rd.forward(request, response);
		
	}

}
