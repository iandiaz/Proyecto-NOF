import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;

/**
 * Servlet implementation class mperusu
 */
@WebServlet("/mperusu")
public class mperusu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mperusu() {
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
		boolean sesion_valida=validateSession(request,response);
		
		if(!sesion_valida) response.sendRedirect("/001/");
		else {
			if(request.getParameter("id_p")!=null) mt_detail(request,response);
			else mt(request,response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		boolean sesion_valida=validateSession(request,response);
		
		if(!sesion_valida) response.sendRedirect("/001/");
		else {
			//insertamos el nuevo perfil
			modificarPerfil(request,response);
			 mt(request,response);
		}
	}
	protected void modificarPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		Statement state = null;
		
		try {
			
			String id_iusuario="";
			
			Cookie [] cookies=request.getCookies();
			
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
			    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
			    }
			}
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    
		    		  String id_perfil=request.getParameter("id_p");
		    		  
		    		  String nombre_perfil=request.getParameter("perfilusu_nombre");
		    		  String estado_perfil=request.getParameter("select_estado");
		    		  //PREGUNTAMOS SI YA EXISTE NOMBRE DE PERFIL
		    		  boolean existePerfil = false;
		    		  
		    		  String sql_valEstclpr="SELECT perfilusu_nombre FROM perfilusu WHERE `perfilusu`.`perfilusu_nombre`='"+nombre_perfil.toUpperCase()+"' AND `perfilusu`.`perfilusu_id` <> '"+id_perfil+"'";
		    		  ResultSet rs_nusuarios = state.executeQuery(sql_valEstclpr);
		    		  if(rs_nusuarios.next())existePerfil=true;
		    		  state.close();
		    		  
		    		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    		  //actualizamos nombre y estado
		    		  if(!existePerfil){
		    			  
		    			  //hacemos la modificacion en birt
		    			  
		    			  if(Constantes.SYNCDB==1){
		    			  
		    			  String estado_v_nv="VIGENTE";
		     			 if(estado_perfil.equals("2")) estado_v_nv="NO VIGENTE";
		     			 
		     			 ConBirt birtBD= new ConBirt();
		     			String update_perfilBIRT_SQL=""
		     					+ "	UPDATE [PERFIL] "
		     					+ "	SET [PERFIL].[PERF_ESTADO] = '"+estado_v_nv+"', "
		     					+ "	 [PERFIL].[PERF_FECHA_UM] = getdate(), "
		     					+ "	 [PERFIL].[PERF_NOMBRE] = '"+nombre_perfil.toUpperCase()+"', "
		     					+ "	 [PERFIL].[USU_ID_UM] = "+id_iusuario+" "
		     					+ "	WHERE"
		     					+ "		[PERFIL].[PERF_ID] = "+id_perfil;
		     			System.out.println("BIRT: "+update_perfilBIRT_SQL);
		     			 birtBD.ConsultaINUP(update_perfilBIRT_SQL);
		     		        
		     		       // QueryBirt.next();
		     		       // System.out.println("RESULTADO BIRT: "+QueryBirt.getString("REGI_NOMBRE"));
		     		       // QueryBirt.close();
		     		        birtBD.Desconectar();
		    		  }
		    			  
		    			  
		    		  String update_perfil_sql="UPDATE `perfilusu` "
		    		  		+ " SET `perfilusu`.`perfilusu_nombre` = '"+nombre_perfil.toUpperCase()+"', "
		    		  		+ "  `perfilusu`.`estados_vig_novig_id` = "+estado_perfil +", "
		    		  		+ "	 `perfilusu`.`perfilusu_modificador`= "+id_iusuario+", "
		    		  		+ "	 `perfilusu`.`perfilusu_fecmod`=now(), "
		    		  		+ "	  perfilusu_accion_alertada=0, perfilusu_ult_idper_exec=3  "
		    		  		+ " WHERE"
		    		  		+ " 	`perfilusu`.`perfilusu_id`="+id_perfil;
		    		  System.out.println("SQL update: "+update_perfil_sql);
		    		  state.addBatch(update_perfil_sql);
		    		  
		    		  //borramos todos los permisos anteriores
		    		  
		    		  String update_per_sql="UPDATE `perfilusu_has_permisos` "
		    		  		+ " SET `perfilusu_has_permisos`.`estados_vig_novig_id` = 2"
		    		  		+ " WHERE"
		    		  		+ " 	`perfilusu_has_permisos`.`perfilusu_id` = "+id_perfil;
		    		  
		    		  System.out.println("SQL update: "+update_per_sql);
		    		  state.addBatch(update_per_sql);
		    		  
		    		  String[] seleccionado = request.getParameterValues("permisos[]");
		    		if(seleccionado!=null)for (String id_per: seleccionado) {
		    			if(id_per!=null && !id_per.equals("-1")){
		    				String insertPermisoPerfil=""
			    					+ " INSERT INTO `perfilusu_has_permisos` ("
			    					+ " 	`perfilusu_has_permisos`.`perfilusu_id`,"
			    					+ " 	`perfilusu_has_permisos`.`permisos_id` "
			    					+ " ) "
			    					+ " VALUES"
			    					+ " 	('"+id_perfil+"', '"+id_per+"')";
			    			
		    				System.out.println("SQL in: "+insertPermisoPerfil);
			    			state.addBatch(insertPermisoPerfil);
			    			
		    			}
		    			
		    			
		    		}
		    		
		    		state.executeBatch();
		    		
            	
                
            	request.setAttribute("MSG", "OPERACION REALIZADA CON EXITO");
		    		  }
		    		  else{
		    			  request.setAttribute("MSG", "NOMBRE DE PERFIL YA EXISTE");
		    		  }
		    	state.close();
		           conexion.close();
               // request.setAttribute("armenus", arMenus);
                //RequestDispatcher a = request.getRequestDispatcher("iperusu.jsp");
            	//a.forward(request, response);
              
		}catch(Exception ex){
		    ex.printStackTrace();
		}
		
	}
	private void mt_detail(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
PrintWriter out = response.getWriter();
		
		///////////////////////////////////////////
		//////DEFINE DESLOGEO//////////////////////
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			    	//System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
			
		}
		
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		
		String Usuarios_nombre="";
		
		
		
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		
		//////////////////////////////////////////////////
		Statement state = null;
		ResultSet MENUS_RS = null;
		
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		
    		
    		
    		
    		
    		///CONTAMOS LA CANTIDAD DE MODULOS EN SISTEMA 
    		
   		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_cant = "select count(*) as n from `modulos` ";
		    
		    System.out.println(SQL_cant);
		    ResultSet modulos_cant_rs = state.executeQuery(SQL_cant);
   		
   		int modulos =0;
   		
   		while(modulos_cant_rs.next()){
        	   
       	    //SI HAY MODULOS PARA EL MENU, ENTONCES MOSTRAMOS EL MENU
   			modulos=modulos_cant_rs.getInt("n");
       	    
   	    }
   		
   		
   		
    		
    		
    		
    		
    		
    		
    		
    		
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_MENUS = "SELECT "
		    		+ " `modulos`.`Modulos_id`, "
		    		+ "	`modulos`.`Modulos_nombre`, "
		    		+ "	`modulos`.`Modulos_codigo`, "
		    		+ "	`modulos`.`estados_vig_novig_id`,"
		    		+ "	`permisos`.`permisos_nombre`,"
		    		+ "	`permisos`.`tipo_permiso_id`,"
		    		+ " `permisos`.`permisos_id`,"
		    		+ " php.estados_vig_novig_id AS per_disponible "
		    		+ " FROM"
		    		+ " 	`menus`"
		    		+ " INNER JOIN `modulos` ON `modulos`.`Menus_id` = `menus`.`Menus_id`"
		    		+ " LEFT JOIN `permisos` ON `permisos`.`Modulos_id` = `modulos`.`Modulos_id`"
		    		+ " LEFT JOIN ( "
		    		+ " 	SELECT"
		    		+ " 		`perfilusu_has_permisos`.`estados_vig_novig_id`,"
		    		+ " 		`perfilusu_has_permisos`.`permisos_id`"
		    		+ " 	FROM"
		    		+ " 		`perfilusu_has_permisos`"
		    		+ " 	WHERE"
		    		+ " 		`perfilusu_has_permisos`.`estados_vig_novig_id` = 1"
		    		+ " 	AND `perfilusu_has_permisos`.`perfilusu_id` ="+request.getParameter("id_p")
		    		+ " ) php ON `php`.`permisos_id` = `permisos`.`permisos_id`"
		    		+ " WHERE"
		    		+ " 	`modulos`.`estados_vig_novig_id` = 1"
		    		+ " ORDER BY"
		    		+ " 	`modulos`.`Menus_id`,"
		    		+ " 	`modulos`.`Modulos_codigo`," 
		    		+ "	`permisos`.`permisos_nombre` ";
		    
		    System.out.println(SQL_MENUS);
		    MENUS_RS =  state.executeQuery(SQL_MENUS);
		    
		    String perfil_sql="SELECT "
		    		+ " 	`perfilusu`.`perfilusu_nombre`, "
		    		+ "		`perfilusu`.`perfilusu_id`, "
		    		+ " 	`estados_vig_novig`.`estados_vig_novig_nombre`, "
		    		+ " 	`estados_vig_novig`.`estados_vig_novig_id`, "
		    		+ "		CONCAT( "
		    		+ "			`usuarios`.`Usuarios_nombre`, "
		    		+ "			' ',"
		    		+ "			`usuarios`.`Usuarios_ape_p` "
		    		+ "		) AS perfilusu_creador, "
		    		+ "		DATE_FORMAT(perfilusu.perfilusu_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion, "
		    		+ "		IF ( "
		    		+ "			perfilusu.perfilusu_fecmod IS NULL,"
		    		+ "			' ',"
		    		+ "			DATE_FORMAT(perfilusu.perfilusu_fecmod,'%d-%m-%Y %H:%i:%s')"
		    		+ "		) AS perfilusu_fecmod,"
		    		+ "		IF ("
		    		+ "			perfilusu.perfilusu_modificador IS NULL,"
		    		+ "			' ',"
		    		+ "			CONCAT( "
		    		+ "			`u2`.`Usuarios_nombre`,"
		    		+ "			' ',"
		    		+ "			`u2`.`Usuarios_ape_p`"
		    		+ "			)"
		    		+ "		) AS perfilusu_modificador "
		    		+ " FROM"
		    		+ " 	`perfilusu`"
		    		+ " INNER JOIN `estados_vig_novig` ON `perfilusu`.`estados_vig_novig_id` = `estados_vig_novig`.`estados_vig_novig_id`"
		    		+ " INNER JOIN `usuarios` ON `perfilusu`.`perfilusu_creador` = `usuarios`.`Usuarios_id` "
		    		+ " LEFT JOIN `usuarios` u2 ON `perfilusu`.`perfilusu_modificador` = `u2`.`Usuarios_id` "
		    		+ " WHERE"
		    		+ " 	`perfilusu`.`perfilusu_id` = "+request.getParameter("id_p");
		    
		   
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> menus = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    	
		    while(MENUS_RS.next()){
        	   
        	    //SI HAY MODULOS PARA EL MENU, ENTONCES MOSTRAMOS EL MENU
		    	menus.add(MENUS_RS.getString("Modulos_id")+"/"+MENUS_RS.getString("Modulos_nombre")+"/"+MENUS_RS.getString("Modulos_codigo")+"/"+MENUS_RS.getString("tipo_permiso_id")+"/"+MENUS_RS.getString("permisos_id")+"/"+MENUS_RS.getString("per_disponible")+"/"+MENUS_RS.getString("permisos_nombre"));
        	    
    	    }
		  //System.out.println("SIZE LIST: "+menus.size());
		    	
		    	MENUS_RS.close();
		    	
		    	
		    	
		    	 ResultSet perfil_rs = state.executeQuery(perfil_sql);
				    System.out.println(perfil_sql);
				    String perfil_nombre="";
				    String perfil_id="";
				    String estado_vignovig="";
				    String estado_vignovig_id="";
				    
				    String fec_crea="";
				    String user_crea="";
				    String fec_mod="";
				    String user_mod="";
				    
				    if(perfil_rs.next()){ 
				    	perfil_nombre=perfil_rs.getString("perfilusu_nombre");
				    	perfil_id=perfil_rs.getString("perfilusu_id");
				    	estado_vignovig=perfil_rs.getString("estados_vig_novig_nombre");
				    	estado_vignovig_id=perfil_rs.getString("estados_vig_novig_id");
				    	
				    	fec_crea=perfil_rs.getString("perfilusu_feccreacion");
						user_crea=perfil_rs.getString("perfilusu_creador");
						fec_mod=perfil_rs.getString("perfilusu_fecmod");
						user_mod=perfil_rs.getString("perfilusu_modificador");
				    }
		    	
            	state.close();
            	conexion.close();
                
            	
            	//definimos variables para las acciones o funciones que puede realizar por modulo
            	String id_modulo="";
            	String modulo_codigo="";
            	String modulo_nombre="";
		    	
            	String per_ingresar="-1";
		    	String per_eliminar="-1";
		    	String per_modificar="-1";
		    	String per_consultar="-1";
		    	String per_reportes="-1";
		    
		    	String mod_linea="";
		    
		    	String mod_actual="";
            	
                Object[] arMenus = new Object[modulos];
                ArrayList<String> permisos_otorgados = new ArrayList<String>();
                
                int arPosition=0;
                
                for(int x=0; x < menus.size(); x++){
                	
                	String[] parse_linea=menus.get(x).split("/");
                	if(parse_linea[5]!=null && !parse_linea[5].equals("null"))permisos_otorgados.add(parse_linea[4]);
                	if(mod_actual.equals("") || parse_linea[2].equals(mod_actual)){
                		mod_actual=parse_linea[2];
                		
                		id_modulo=parse_linea[0]; 
                		modulo_nombre=parse_linea[1];
                		modulo_codigo=parse_linea[2];
                		switch (parse_linea[3]) {
						case "1":
							per_ingresar=parse_linea[4];
							break;
							
						case "2":
							per_eliminar=parse_linea[4];
							break;
						case "3":
							per_modificar=parse_linea[4];
							break;
						case "4":
							per_consultar=parse_linea[4];
							break;
						case "5":
							if(per_reportes.equals("-1")) per_reportes="";
							per_reportes+=parse_linea[4]+"__"+parse_linea[6]+"y";
							break;
						
						default:
							break;
						}
                		
                		mod_linea=id_modulo+"/"+modulo_codigo+"/"+modulo_nombre+"/"+per_ingresar+"/"+per_eliminar+"/"+per_modificar+"/"+per_consultar+"/"+per_reportes;
                		arMenus[arPosition]=mod_linea;
                		//System.out.println(mod_linea);
                		
                		
                	}
                	else{
                		mod_actual=parse_linea[2];
                		
                		arPosition++;
                		
                		id_modulo="";
                    	modulo_codigo="";
                    	modulo_nombre="";
        		    	per_ingresar="-1";
        		    	per_eliminar="-1";
        		    	per_modificar="-1";
        		    	per_consultar="-1";
        		    	per_reportes="-1";
        		    	mod_linea="";
        		    	
        		    	id_modulo=parse_linea[0]; 
                		modulo_nombre=parse_linea[1];
                		modulo_codigo=parse_linea[2];
                		
                		switch (parse_linea[3]) {
						case "1":
							per_ingresar=parse_linea[4];
							break;
							
						case "2":
							per_eliminar=parse_linea[4];
							break;
						case "3":
							per_modificar=parse_linea[4];
							break;
						case "4":
							per_consultar=parse_linea[4];
							break;
						case "5":
							if(per_reportes.equals("-1")) per_reportes="";
							per_reportes+=parse_linea[4]+"__"+parse_linea[6]+"y";
							break;
						
						default:
							break;
						}
        		    
                		mod_linea=id_modulo+"/"+modulo_codigo+"/"+modulo_nombre+"/"+per_ingresar+"/"+per_eliminar+"/"+per_modificar+"/"+per_consultar+"/"+per_reportes;
                		arMenus[arPosition]=mod_linea;
                		//System.out.println(mod_linea);
                		
                		
                	}
                	
                	
                }
                
                
                
                //los permisos otorgados los pasamos a un arreglo 
                
                String[] permisos_o = new String[permisos_otorgados.size()];
                
                for(int x=0; x < permisos_otorgados.size(); x++){
                	permisos_o[x]= permisos_otorgados.get(x);
                	
                }
                
                request.setAttribute("per_otor", permisos_o);
                request.setAttribute("armenus", arMenus);
                request.setAttribute("perfil_name", (perfil_nombre)+"");
                request.setAttribute("perfil_id", (perfil_id)+"");
                request.setAttribute("estado_vignovig", (estado_vignovig)+"");
                request.setAttribute("estado_vignovig_id", (estado_vignovig_id)+"");
                
                request.setAttribute("fec_crea", (fec_crea)+"");
                request.setAttribute("fec_mod", (fec_mod)+"");
                request.setAttribute("user_mod", (user_mod)+"");
                request.setAttribute("user_crea", (user_crea)+"");
                
                RequestDispatcher a = request.getRequestDispatcher("mperusu_det.jsp");
            	a.forward(request, response);
              
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error: "+ex);
		}
		
		
	}
	
public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		
		///////////////////////////////////////////
		//////DEFINE DESLOGEO//////////////////////
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			    	//System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
			
		}
		
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		
		String Usuarios_nombre="";
		
		
		
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		
		//////////////////////////////////////////////////
		
		
		Statement state = null;
		ResultSet PERFILES_RS = null;
		ResultSet USUARIOS_RS = null;
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_PERFILES = ""
		    		+ " SELECT"
		    		+ " 	`perfilusu`.`perfilusu_id`,"
		    		+ " 	`perfilusu`.`perfilusu_nombre`,"
		    		+ " 	`perfilusu`.`estados_vig_novig_id`,"
		    		+"		`estados_vig_novig`.`estados_vig_novig_nombre`"
		    		+ " FROM"
		    		+ " 	`perfilusu`" 
		    		+ " INNER JOIN `estados_vig_novig` ON `estados_vig_novig`.`estados_vig_novig_id` = `perfilusu`.`estados_vig_novig_id` "
		    		+ " ORDER BY `perfilusu`.`perfilusu_nombre` ";
		    
		    System.out.println(SQL_PERFILES);
		    
		    
		    PERFILES_RS =  state.executeQuery(SQL_PERFILES);
		    
		    String SQL_USUARIOS = ""
		    		+ " SELECT"
		    		+ "  	`usuarios`.`perfilusu_id`,"
		    		+ " 	CONCAT_WS( 	' ', `usuarios`.`Usuarios_nombre`, `usuarios`.`Usuarios_ape_p`,	`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre  "
		    		+ " FROM"
		    		+ " 	`usuarios`"
		    		+ " WHERE"
		    		+ " 	`usuarios`.`estados_vig_novig_id` = 1";
		    
		    System.out.println(SQL_USUARIOS);
		   
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> perf = new ArrayList<String>();
		    ArrayList<String> usuarios = new ArrayList<String>();
		    
		   
		   
		    //recorremos los resultados de la consulta
		    	
		    while(PERFILES_RS.next()){
        	   
        	    //SI HAY MODULOS PARA EL MENU, ENTONCES MOSTRAMOS EL MENU
		    	perf.add(PERFILES_RS.getString("perfilusu_id")+"/"+PERFILES_RS.getString("perfilusu_nombre").replace("/", " ")+"/"+PERFILES_RS.getString("estados_vig_novig_id")+"/"+PERFILES_RS.getString("estados_vig_novig_nombre"));
        	    
    	    }
		    
 state.close();
		    
		    state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    USUARIOS_RS =  state.executeQuery(SQL_USUARIOS);
		    
		    while(USUARIOS_RS.next()){
        	   
        	    //SI HAY MODULOS PARA EL MENU, ENTONCES MOSTRAMOS EL MENU
		    	usuarios.add(USUARIOS_RS.getString("perfilusu_id")+"/"+USUARIOS_RS.getString("Usuarios_nombre"));
        	    
    	    }
		    
		    USUARIOS_RS.close();
		    PERFILES_RS.close();
		    state.close();
	       	conexion.close();
		   
       	
       	String[] arPerfiles = new String[perf.size()];
        for(int x=0; x < perf.size(); x++){
        	arPerfiles[x]=perf.get(x);
        }
        String[] arUsuarios = new String[usuarios.size()];
        for(int x=0; x < usuarios.size(); x++){
        	arUsuarios[x]=usuarios.get(x);
        }
        System.out.println("Usuarios encontrados: "+ arUsuarios.length);
        request.setAttribute("perfiles", arPerfiles);
        request.setAttribute("usuarios", arUsuarios);
        
        
        RequestDispatcher a = request.getRequestDispatcher("mperusu.jsp");
    	a.forward(request, response);
    	
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error: "+ex);
		}
		
	}


}
