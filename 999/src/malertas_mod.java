

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
 * Servlet implementation class malertas_mod
 */
@WebServlet("/malertas_mod")
public class malertas_mod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public malertas_mod() {
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
		
		boolean existeAlerta = false;
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet rs_valgrupos= null;
			Statement stateval_al = null;
			ResultSet rs_valalertas= null;
			try {
		    	String alertas_id=request.getParameter("id_a");
		    	request.setAttribute("alertas_id", alertas_id);
				String alertas_nombre = request.getParameter("alertas_nombre");
				String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
		    	
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		 stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();

		    		stateval_al = (Statement) ((java.sql.Connection) conexion).createStatement();
		    		String sql_valalerta="SELECT * FROM alertas WHERE alertas_nombre='"+alertas_nombre.toUpperCase()+"' AND alertas_id<>"+request.getParameter("id_a");
			    	rs_valalertas=stateval_al.executeQuery(sql_valalerta);
			    	System.out.println("BUSCA ID: "+sql_valalerta); 
			    	
			    	
			    	if(rs_valalertas.next()){
			    		stateval_al.close();
		    			rs_valgrupos.close();conexion.close();
		    			existeAlerta=true;
		    			System.out.println("if(rs_valgrupos.next()){"+existeAlerta);
		    		}else{
				    String SQL_UPDATE = ""
				    		+ "UPDATE alertas "
				    		+ " SET alertas_nombre='"+alertas_nombre.toUpperCase()+"', estados_vig_novig_id="+estados_vig_novig_id+" "
				    		+ " 	,alertas_fecmod=now(), alertas_accion_alertada=0, alertas_ult_idper_exec=30  "
				    		+ " 	,alertas_modificador= "+id_iusuario
				    		+ " WHERE alertas_id="+alertas_id;
				    System.out.println(SQL_UPDATE);
				    stategrabar.executeUpdate(SQL_UPDATE);
				    stategrabar.close();
				    stateval_al.close();
		    		
	    		 
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		String sql_valgrupo="SELECT * FROM gruposusu_alertas WHERE alertas_id = "+alertas_id;
	    		System.out.println("GRUPOS ALERTA: "+sql_valgrupo);
		    	rs_valgrupos=stateval.executeQuery(sql_valgrupo);

					stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
					String up_delete=""
	    					+ " UPDATE gruposusu_alertas SET "
	    					+ " estados_vig_novig_id=2  "
	    					+ " WHERE alertas_id="+alertas_id;
    				stategrabar.executeUpdate(up_delete);
    				stategrabar.close(); 
    				stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
					 String[] seleccionado = request.getParameterValues("permisos[]");
					 if(seleccionado!=null && seleccionado.length>0)
			    		for (String id_per: seleccionado) {
			    			if(id_per!=null && !id_per.equals("-1")){
			    				String insertusuemp=""
				    					+ " INSERT INTO gruposusu_alertas ("
				    					+ " 	gruposusu_id, alertas_id "
				    					+ " ) "
				    					+ " VALUES"
				    					+ " 	('"+id_per+"', '"+alertas_id+"')";
			    				stategrabar.executeUpdate(insertusuemp);
			    			}	    			
			    		}
				    
		    		
                        
                        //borramos registros anteriores de esta alerta
                        stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
                        String up_delete2="delete from `alertas_has_evento` where `alertas_has_evento`.`alertas_has_evento_id_alertas`="+alertas_id;
                        stategrabar.executeUpdate(up_delete2);
                        stategrabar.close();
                        
                        
                        stategrabar.close();
                        
                        
                        ////insertamos los eventos configurados para esa alerta
                        
                        Statement stategrabar3 = (Statement) ((java.sql.Connection) conexion).createStatement();
                        
                        String[] seleccionado2 = request.getParameterValues("eventos[]");
                        if(seleccionado2!= null && seleccionado2.length>0)
                        for (String id_ev: seleccionado2) {
                            if(id_ev!=null && !id_ev.equals("-1")){
                                String insertalertaevento=""
                                + " INSERT INTO alertas_has_evento ("
                                + " `alertas_has_evento`.`alertas_has_evento_id_permiso`, `alertas_has_evento`.`alertas_has_evento_id_alertas`  "
                                + " ) "
                                + " VALUES"
                                + " 	('"+id_ev+"', '"+alertas_id+"')";
                                System.out.println("INSERTA REGISTRO: "+insertalertaevento);
                                stategrabar3.executeUpdate(insertalertaevento);
                            }	    			
                        }
                        
                        
                        stategrabar3.close();
                        
                        
                        
                        
                        
                        
                        
			        conexion.close();
			        
			    RequestDispatcher rd_up = request.getRequestDispatcher("malertas_search?mExito=OK");
		        rd_up.forward(request, response);
		    	}
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("ERROR "+ex);
			}
			if(!existeAlerta)return;
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
		Statement state2 = null;
		Statement state_est = null;
		ResultSet Grupo_RS = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet PERUSUEMP_RS = null;
		ResultSet ESTADOS_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state_est = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		//:::::::::::::::::::::::::: SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
   		    String SQL_PERUSUEMP = "SELECT * FROM gruposusu_alertas WHERE estados_vig_novig_id=1 AND alertas_id="+Integer.parseInt(request.getParameter("id_a"));
   		    PERUSUEMP_RS =  state.executeQuery(SQL_PERUSUEMP);
   		    System.out.println("SQL GRUPO ALERTA: "+SQL_PERUSUEMP);
   		    
   		    //definimos un arreglo para los resultados
   		    ArrayList<String> gruposusualertas = new ArrayList<String>();
   		    //recorremos los resultados de la consulta
   		    while(PERUSUEMP_RS.next()){        	   
           	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
   		    	gruposusualertas.add(PERUSUEMP_RS.getString("gruposusu_id"));
       	    }
   		 
   		  PERUSUEMP_RS.close();
   		  state.close();
   		  String[] ar_gruposusualertas = new String[gruposusualertas.size()];
             for(int x=0; x < gruposusualertas.size(); x++){
            	 ar_gruposusualertas[x]=gruposusualertas.get(x);
             }
                   
             request.setAttribute("ar_gruposusualertas", ar_gruposusualertas);
             //::::::::::::::::::::::::::SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
     		 
     		//:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
             state = (Statement) ((java.sql.Connection) conexion).createStatement();
  		    String SQL_EMPRESAS = "SELECT * FROM gruposusu WHERE estados_vig_novig_id=1 ORDER BY gruposusu_nombre ";
  		    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
  		    //definimos un arreglo para los resultados
  		    ArrayList<String> gruposusu = new ArrayList<String>();
  		    //recorremos los resultados de la consulta
  		    while(EMPRESAS_RS.next()){        	   
          	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  		    	gruposusu.add(EMPRESAS_RS.getString("gruposusu_id")+"/"+EMPRESAS_RS.getString("gruposusu_nombre").replace("/", " "));    
      	    }
  		  
  		  EMPRESAS_RS.close();
  		  state.close();
  		  String[] ar_gruposusu = new String[gruposusu.size()];
            for(int x=0; x < gruposusu.size(); x++){
            	ar_gruposusu[x]=gruposusu.get(x);
            }
                  
            request.setAttribute("ar_gruposusu", ar_gruposusu);
            
            
            //:::::::::::::::::::::::::: sql trae eventos para select option:::::::::::::::::::::::::::::::::::::
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    String SQL_EVENTOS = "SELECT "
            + "            `permisos`.`permisos_id`, "
            + "            `permisos`.`permisos_nombre`, "
            + "            `modulos`.`Modulos_codigo`, "
            + "             `alertas_has_evento`.`alertas_has_evento_id`   "
            + "            FROM "
            + "            `permisos` "
            + "            INNER JOIN `modulos` ON `modulos`.`Modulos_id` = `permisos`.`Modulos_id` "
            + "             LEFT JOIN `alertas_has_evento` ON (`alertas_has_evento`.`alertas_has_evento_id_permiso` = permisos.permisos_id AND `alertas_has_evento`.`alertas_has_evento_id_alertas` = "+Integer.parseInt(request.getParameter("id_a"))+") "
            + "            WHERE "
            + "            `permisos`.`tipo_permiso_id` IN (1, 2, 3) ORDER BY `permisos`.`permisos_nombre` ";
		    ResultSet RS_EVENTOS =  state.executeQuery(SQL_EVENTOS);
		    //definimos un arreglo para los resultados
		    ArrayList<String> list_eventos = new ArrayList<String>();
		    //recorremos los resultados de la consulta
		    while(RS_EVENTOS.next()){
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
                String sel  ="0";
                
                if(RS_EVENTOS.getString("alertas_has_evento_id")!=null && !RS_EVENTOS.getString("alertas_has_evento_id").equals("")) sel="1";
                
		    	list_eventos.add(RS_EVENTOS.getString("permisos_id")+"/"+RS_EVENTOS.getString("permisos_nombre")+"/"+RS_EVENTOS.getString("Modulos_codigo")+"/"+sel);
    	    }
		    RS_EVENTOS.close();
            state.close();
            String[] ar_eventos = new String[list_eventos.size()];
            for(int x=0; x < list_eventos.size(); x++){
                ar_eventos[x]=list_eventos.get(x);
            }
            
            request.setAttribute("ar_eventos", ar_eventos);
            

            
            
  		//::::::::::::::::::::::::::fin sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
            
		    String SQL_GRUPO = ""
		    		+ "SELECT a.alertas_id, a.alertas_nombre, a.estados_vig_novig_id, "
		    		+ "		CONCAT( "
		    		+ "			`usuarios`.`Usuarios_nombre`, "
		    		+ "			' ',"
		    		+ "			`usuarios`.`Usuarios_ape_p` "
		    		+ "		) AS alertas_creador, "
		    		+ "		DATE_FORMAT(a.alertas_feccreacion,'%d-%m-%Y %H:%i:%s') AS alertas_feccreacion, "
		    		+ "		IF ( "
		    		+ "			a.alertas_fecmod IS NULL,"
		    		+ "			' ',"
		    		+ "			DATE_FORMAT(a.alertas_fecmod, '%d-%m-%Y %H:%i:%s')"
		    		+ "		) AS alertas_fecmod,"
		    		+ "		IF ("
		    		+ "			a.alertas_modificador IS NULL,"
		    		+ "			' ',"
		    		+ "			CONCAT( "
		    		+ "			`u2`.`Usuarios_nombre`,"
		    		+ "			' ',"
		    		+ "			`u2`.`Usuarios_ape_p`"
		    		+ "			)"
		    		+ "		) AS alertas_modificador "
		    		+ " FROM alertas a "
		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=a.estados_vig_novig_id"
		    		+ " 	INNER JOIN `usuarios` ON `a`.`alertas_creador` = `usuarios`.`Usuarios_id` "
		    		+ " 	LEFT JOIN `usuarios` u2 ON `a`.`alertas_modificador` = `u2`.`Usuarios_id` "
		    		+ " WHERE alertas_id="+Integer.parseInt(request.getParameter("id_a"));
		    System.out.println("TRAE GRUPO: "+SQL_GRUPO);
		    Grupo_RS =  state2.executeQuery(SQL_GRUPO);
		    if(Grupo_RS.next()){
		    	request.setAttribute("alertas_id",Grupo_RS.getString("alertas_id"));
		    	request.setAttribute("alertas_nombre",Grupo_RS.getString("alertas_nombre"));
		    	request.setAttribute("estados_vig_novig_id",Grupo_RS.getString("estados_vig_novig_id"));
		    	
		    	String user_crea=Grupo_RS.getString("alertas_creador");
		    	String fec_crea=Grupo_RS.getString("alertas_feccreacion");
		    	String fec_mod=Grupo_RS.getString("alertas_fecmod");
		    	String user_mod=Grupo_RS.getString("alertas_modificador") ;
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
    	    }
		    Grupo_RS.close();
		    state2.close();	    
		    
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state_est.executeQuery(SQL_ESTADOS);
		    ArrayList<String> estados = new ArrayList<String>();
			
		    System.out.println("PROBANDO A");
		    //recorremos los resultados de la consulta
		    while(ESTADOS_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
        	    
    	    }
		  System.out.println("SIZE Estados: "+estados.size());
		    	
		  ESTADOS_RS.close();
		  state_est.close();
          conexion.close();
                
          String[] ar_estados = new String[estados.size()];
          for(int x=0; x < estados.size(); x++){
           	ar_estados[x]=estados.get(x);
          }
                
          request.setAttribute("ar_estados", ar_estados);

		}catch(Exception ex){
		    out.println("ERROR "+ex);
            ex.printStackTrace();
		}
		System.out.println("bool: "+existeAlerta);
		String msg="";
		if(existeAlerta){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String alertas_nombre = request.getParameter("alertas_nombre");
			request.setAttribute("alertas_nombre",alertas_nombre);
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		System.out.println("msg: "+msg);
		RequestDispatcher rd = request.getRequestDispatcher("malertas_mod.jsp");
        rd.forward(request, response);
		
	}

}
