

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
 * Servlet implementation class mgruper_mod
 */
@WebServlet("/mgruper_mod")
public class mgruper_mod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mgruper_mod() {
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
		boolean existeGrupo = false;
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet rs_valgrupos= null;
			try {
				String gruposusu_nombre = request.getParameter("gruposusu_nombre");
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		 stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		 
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		String sql_valgrupo="SELECT * FROM gruposusu WHERE gruposusu_nombre='"+gruposusu_nombre.toUpperCase()+"' AND gruposusu_id<>"+request.getParameter("id_g");
		    	rs_valgrupos=stateval.executeQuery(sql_valgrupo);
		    	System.out.println("BUSCA ID: "+sql_valgrupo); 
		    	
		    	
		    	if(rs_valgrupos.next()){
		    		stateval.close();
	    			rs_valgrupos.close();conexion.close();
	    			existeGrupo=true;
	    			System.out.println("if(rs_valgrupos.next()){"+existeGrupo);
	    		}else{
			    String SQL_UPDATE = ""
			    		+ "UPDATE gruposusu "
			    		+ " SET gruposusu_nombre='"+gruposusu_nombre.toUpperCase()+"', estados_vig_novig_id="+estados_vig_novig_id+" "
			    		+ " 	,gruposusu_fecmod=now() "
			    		+ " 	,gruposusu_modificador= "+id_iusuario
			    		+ " WHERE gruposusu_id="+request.getParameter("id_g");
			    System.out.println(SQL_UPDATE);
			    stategrabar.executeUpdate(SQL_UPDATE);
			
                    
                    //primero borramos todo lo que tenia anteriormente para volver a insertarlo
                    String update_grup="update `gruposusu_has_perfilusu` set `gruposusu_has_perfilusu`.`estados_vig_novig_id`=2 where `gruposusu_has_perfilusu`.`gruposusu_id`= "+request.getParameter("id_g");
                    System.out.println(update_grup);
                    stategrabar.executeUpdate(update_grup);
                    
                    
                    //insertamos todo de nuevo
                    
                    
                    String[] seleccionado = request.getParameterValues("perfiles_sel[]");
		    		if(seleccionado!=null) for (String id_per: seleccionado) {
		    			if(id_per!=null && !id_per.equals("-1")){
		    				String insertperper=""
                            + " INSERT INTO `gruposusu_has_perfilusu` ("
                            + " 	`gruposusu_has_perfilusu`.`gruposusu_id`, "
                            + " 	`gruposusu_has_perfilusu`.`perfilusu_id`  "
                            + " ) "
                            + " VALUES"
                            + " 	('"+request.getParameter("id_g")+"', '"+id_per+"')";
			    			
		    				stategrabar.executeUpdate(insertperper);
			    			System.out.println("Perfilusu insertado: "+id_per+" perfdeperfiles: "+request.getParameter("id_g"));
		    			}
		    		}
                    
                    
    
                    stategrabar.close();
                    stateval.close();
                    rs_valgrupos.close();
                    
	            conexion.close();
	            existeGrupo=false;
	            System.out.println("UP: "+SQL_UPDATE);  
	            RequestDispatcher rd_up = request.getRequestDispatcher("mgruper?mExito=OK");
	            rd_up.forward(request, response);
	    		}
			}catch(Exception ex){
			    out.println("Unable to connect to database "+ex);
			}
			if(!existeGrupo)return;
		}
		
		//fin grabar
		
		System.out.println("existeGrupo"+existeGrupo);
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
		ResultSet ESTADOS_RS = null;
		ResultSet Grupo_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    
		    String SQL_GRUPO = ""
		    		+ "SELECT *,"
		    		+ "		CONCAT( "
		    		+ "			`usuarios`.`Usuarios_nombre`, "
		    		+ "			' ',"
		    		+ "			`usuarios`.`Usuarios_ape_p` "
		    		+ "		) AS perfilusu_creador, "
		    		+ "		DATE_FORMAT(g.gruposusu_feccreacion, '%d-%m-%Y %H:%i:%s') AS gruposusu_feccreacion_ok, "
		    		+ "		IF ( "
		    		+ "			g.gruposusu_fecmod IS NULL,"
		    		+ "			' ',"
		    		+ "			DATE_FORMAT(g.gruposusu_fecmod, '%d-%m-%Y %H:%i:%s') "
		    		+ "		) AS perfilusu_fecmod,"
		    		+ "		IF ("
		    		+ "			g.gruposusu_modificador IS NULL,"
		    		+ "			' ',"
		    		+ "			CONCAT( "
		    		+ "			`u2`.`Usuarios_nombre`,"
		    		+ "			' ',"
		    		+ "			`u2`.`Usuarios_ape_p`"
		    		+ "			)"
		    		+ "		) AS perfilusu_modificador "
		    		+ " FROM gruposusu g "
		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=g.estados_vig_novig_id"
		    		+ " 	INNER JOIN `usuarios` ON `g`.`gruposusu_creador` = `usuarios`.`Usuarios_id` "
		    		+ " 	LEFT JOIN `usuarios` u2 ON `g`.`gruposusu_modificador` = `u2`.`Usuarios_id` "
		    		+ " WHERE gruposusu_id="+Integer.parseInt(request.getParameter("id_g"));
		    Grupo_RS =  state2.executeQuery(SQL_GRUPO);
		    if(Grupo_RS.next()){
		    	request.setAttribute("gruposusu_id",Grupo_RS.getString("gruposusu_id"));
		    	request.setAttribute("gruposusu_nombre",Grupo_RS.getString("gruposusu_nombre"));
		    	request.setAttribute("estados_vig_novig_id",Grupo_RS.getString("estados_vig_novig_id"));
		    	
		    	String fec_crea=Grupo_RS.getString("gruposusu_feccreacion_ok");
		    	String user_crea=Grupo_RS.getString("perfilusu_creador");
		    	String fec_mod=Grupo_RS.getString("perfilusu_fecmod");
		    	String user_mod=Grupo_RS.getString("perfilusu_modificador") ;
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
    	    }
		    Grupo_RS.close();
		    state2.close();	    
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> estados = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(ESTADOS_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
        	    
    	    }
		  //System.out.println("SIZE LIST: "+estados.size());
		    	
		  ESTADOS_RS.close();
		  state.close();
          
                
          String[] ar_estados = new String[estados.size()];
          for(int x=0; x < estados.size(); x++){
           	ar_estados[x]=estados.get(x);
          }
                
          request.setAttribute("ar_estados", ar_estados);
            
            //:::::::::::::::::::::::::: sql trae perfiles usuario:::::::::::::::::::::::::::::::::::::
            
            Statement state_perf1 = (Statement) ((java.sql.Connection) conexion).createStatement();
            
            String SQL_PERFILES = " "
            + "             SELECT "
            + "             `perfilusu`.`perfilusu_id`, "
            + "             `perfilusu`.`perfilusu_nombre` "
            + "             FROM "
            + "                 `perfilusu` "
            + "             INNER JOIN `gruposusu_has_perfilusu` ON `perfilusu`.`perfilusu_id` = `gruposusu_has_perfilusu`.`perfilusu_id` "
            + "             WHERE "
            + "                 `perfilusu`.`estados_vig_novig_id` = 1  "
            + "                 AND `gruposusu_has_perfilusu`.`gruposusu_id` = "+request.getParameter("id_g")
            + "                 AND `gruposusu_has_perfilusu`.`estados_vig_novig_id` = 1 " ;
            
            System.out.println(SQL_PERFILES);
            
            ResultSet PERFILES_RS1 =  state_perf1.executeQuery(SQL_PERFILES);
            
            String perf_seleccionados=",";
            while(PERFILES_RS1.next()){
                perf_seleccionados+=""+PERFILES_RS1.getString("perfilusu_id")+",";
                
            }
            PERFILES_RS1.close();
            state_perf1.close();
            
            
            
            //:::::::::::::::::::::::::: sql trae perfiles usuario:::::::::::::::::::::::::::::::::::::
            
            Statement state_perf = (Statement) ((java.sql.Connection) conexion).createStatement();
            
             SQL_PERFILES = " "
            + "             SELECT "
            + "             `perfilusu`.`perfilusu_id`, "
            + "             `perfilusu`.`perfilusu_nombre` "
            + "             FROM "
            + "                 `perfilusu` "
            + "             WHERE "
            + "             `perfilusu`.`estados_vig_novig_id` = 1 ORDER BY  `perfilusu`.`perfilusu_nombre` ";
            
            System.out.println(SQL_PERFILES);
            
            ResultSet PERFILES_RS =  state_perf.executeQuery(SQL_PERFILES);
            
            ArrayList<String> perfiles  = new ArrayList<String>();
            
            int numrows=0;
            while(PERFILES_RS.next()){
                
                
                
                
                String perfiles_enbd=PERFILES_RS.getString("perfilusu_id")+"/"+PERFILES_RS.getString("perfilusu_nombre")+"/";
                
                if(perf_seleccionados.indexOf(","+PERFILES_RS.getString("perfilusu_id")+",")!=-1){
                    
                    perfiles_enbd+="sel";
                    System.out.println(perfiles_enbd);
                }
                
                perfiles.add(perfiles_enbd);
                
                numrows++;
            }
            PERFILES_RS.close();
            state_perf.close();
            
            String[] ar_perfiles = new String[perfiles.size()];
            for(int x=0; x < perfiles.size(); x++){
                ar_perfiles[x]=perfiles.get(x);
            }
            
            request.setAttribute("ar_perfiles", ar_perfiles);
            

            
            
            
            
            conexion.close();
            
            
            
            
		}catch(Exception ex){
		    out.println("Unable to connect to database "+ex);
		}
		System.out.println("bool: "+existeGrupo);
		String msg="";
		if(existeGrupo){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String gruposusu_nombre = request.getParameter("gruposusu_nombre");
			request.setAttribute("gruposusu_nombre",gruposusu_nombre);
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		System.out.println("msg: "+msg);
		RequestDispatcher rd = request.getRequestDispatcher("mgruper_mod.jsp"+msg);
        rd.forward(request, response);
		
	}

}
