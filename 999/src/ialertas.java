

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
 * Servlet implementation class ialertas
 */
@WebServlet("/ialertas")
public class ialertas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ialertas() {
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
				String alertas_nombre = request.getParameter("alertas_nombre");
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		state_lastid = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stategrabar2 = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String SQL_ADDDATO="SELECT * FROM alertas WHERE alertas_nombre='"+alertas_nombre.toUpperCase()+"'";
	    		RS_ADDDATO=stateval.executeQuery(SQL_ADDDATO);
	    		//stateval.close();
	    		if(RS_ADDDATO.next()){
	    			stateval.close();
	    			RS_ADDDATO.close();conexion.close();
	    			alertaexiste=true;
	    			//response.sendRedirect("Igrupo?Exito=NOK");
	    		}else{
			    String SQL_INSERT = "INSERT INTO alertas (alertas_nombre,alertas_accion_alertada,alertas_ult_idper_exec,estados_vig_novig_id, alertas_feccreacion, alertas_creador) VALUES ('"+alertas_nombre.toUpperCase()+"',0,28,"+estados_vig_novig_id+",now(),"+id_iusuario+")";
			    System.out.println("SQL Instert: "+SQL_INSERT); 
			    stategrabar.executeUpdate(SQL_INSERT);
			    stateval.close();
			    RS_ADDDATO.close();
			    stategrabar.close();
		          
			    String last_id_sql="SELECT MAX(alertas_id) as ultimo_id FROM alertas "
			    		+ "WHERE estados_vig_novig_id = 1 order by ultimo_id desc limit 1";
			    System.out.println("ULTIMO REGISTRO: "+last_id_sql);
			    LASTID_RS =  state_lastid.executeQuery(last_id_sql);
		        int max_last_id=0;
				if(LASTID_RS.next()) max_last_id=LASTID_RS.getInt("ultimo_id");

			    
				 String[] seleccionado = request.getParameterValues("permisos[]");
		    		for (String id_per: seleccionado) {
		    			if(id_per!=null && !id_per.equals("-1")){
		    				String insertusuemp=""
			    					+ " INSERT INTO gruposusu_alertas ("
			    					+ " 	gruposusu_id, alertas_id "
			    					+ " ) "
			    					+ " VALUES"
			    					+ " 	('"+id_per+"', '"+max_last_id+"')";
		    				System.out.println("INSERTA REGISTRO: "+insertusuemp);
		    				stategrabar2.executeUpdate(insertusuemp);
		    			}	    			
		    		}
			    
		    	stategrabar2.close();
                    
                
                    ////insertamos los eventos configurados para esa alerta
                    
                   Statement stategrabar3 = (Statement) ((java.sql.Connection) conexion).createStatement();
                    
                    String[] seleccionado2 = request.getParameterValues("eventos[]");
		    		for (String id_ev: seleccionado2) {
		    			if(id_ev!=null && !id_ev.equals("-1")){
		    				String insertalertaevento=""
                            + " INSERT INTO alertas_has_evento ("
                            + " `alertas_has_evento`.`alertas_has_evento_id_permiso`, `alertas_has_evento`.`alertas_has_evento_id_alertas`  "
                            + " ) "
                            + " VALUES"
                            + " 	('"+id_ev+"', '"+max_last_id+"')";
		    				System.out.println("INSERTA REGISTRO: "+insertalertaevento);
		    				stategrabar3.executeUpdate(insertalertaevento);
		    			}	    			
		    		}

                    
                    stategrabar3.close();
                    
                    
                    
                    
		        conexion.close();
	            
	            
	            RequestDispatcher rd_up = request.getRequestDispatcher("menualertas?Exito=OK");
	            rd_up.forward(request, response);
	    		}
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("ERROR "+ex);
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
          String last_id_grupos_sql="SELECT alertas_id FROM alertas ORDER BY alertas_id DESC LIMIT 1";
          System.out.println("Correlativo: "+last_id_grupos_sql);
          CORRELATIVO_RS =  statecor.executeQuery(last_id_grupos_sql);
         
          int correlativo=0;
		  if(CORRELATIVO_RS.next()) correlativo=CORRELATIVO_RS.getInt("alertas_id")+1;
          request.setAttribute("correlativo", correlativo+"");

          
  		ResultSet RS_GRUPOSUSU = null;
   		//:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
           state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    String SQL_GRUPOSUSU = "SELECT * FROM gruposusu WHERE estados_vig_novig_id=1 ORDER BY gruposusu_nombre";
		    RS_GRUPOSUSU =  state.executeQuery(SQL_GRUPOSUSU);
		    //definimos un arreglo para los resultados
		    ArrayList<String> gruposusu = new ArrayList<String>();
		    //recorremos los resultados de la consulta
		    while(RS_GRUPOSUSU.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	gruposusu.add(RS_GRUPOSUSU.getString("gruposusu_id")+"/"+RS_GRUPOSUSU.getString("gruposusu_nombre").replace("/", " "));    
    	    }
		    RS_GRUPOSUSU.close();
		  state.close();
		  String[] ar_gruposusu = new String[gruposusu.size()];
          for(int x=0; x < gruposusu.size(); x++){
        	  ar_gruposusu[x]=gruposusu.get(x);
          }
                
          request.setAttribute("ar_gruposusu", ar_gruposusu);
		//::::::::::::::::::::::::::fin sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
          
            //:::::::::::::::::::::::::: sql trae eventos para select option:::::::::::::::::::::::::::::::::::::
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    String SQL_EVENTOS = "SELECT "
            + "            `permisos`.`permisos_id`, "
            + "            `permisos`.`permisos_nombre`, "
            + "            `modulos`.`Modulos_codigo` "
            + "            FROM "
            + "            `permisos` "
            + "            INNER JOIN `modulos` ON `modulos`.`Modulos_id` = `permisos`.`Modulos_id` "
            + "            WHERE "
            + "            `permisos`.`tipo_permiso_id` IN (1, 2, 3) ORDER BY `permisos`.`permisos_nombre` ";
		    ResultSet RS_EVENTOS =  state.executeQuery(SQL_EVENTOS);
		    //definimos un arreglo para los resultados
		    ArrayList<String> list_eventos = new ArrayList<String>();
		    //recorremos los resultados de la consulta
		    while(RS_EVENTOS.next()){
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	list_eventos.add(RS_EVENTOS.getString("permisos_id")+"/"+RS_EVENTOS.getString("permisos_nombre")+"/"+RS_EVENTOS.getString("Modulos_codigo"));
    	    }
		    RS_EVENTOS.close();
            state.close();
            String[] ar_eventos = new String[list_eventos.size()];
            for(int x=0; x < list_eventos.size(); x++){
                ar_eventos[x]=list_eventos.get(x);
            }
            
            request.setAttribute("ar_eventos", ar_eventos);

          
          
          
          statecor.close();
          conexion.close();
          
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("ERROR "+ex);
		}
		
		String msg="";
		if(alertaexiste){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String alertas_nombre = request.getParameter("alertas_nombre");
			request.setAttribute("alertas_nombre",alertas_nombre);
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("ialertas.jsp"+msg);
        rd.forward(request, response);
		
	}

}
