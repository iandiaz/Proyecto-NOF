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
 * Servlet implementation class iperusu
 */
@WebServlet("/iperusu")
public class iperusu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iperusu() {
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
			
			 mt(request,response);
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
			insertPerfil(request,response);
			
			
			//response.sendRedirect("/004/");
			// mt(request,response);
		}
		
		
		
		
	}
	protected void insertPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		Statement state = null;
		
		try {
			
			//extraemos id de usuario logeado
			
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
    		
    		 String SQL_BUSCA=""
    		 		+ " SELECT"
    		 		+ "		count(*) AS n "
    		 		+ "	FROM "
    		 		+ "		`perfilusu`"
    		 		+ "	WHERE"
    		 		+ " 	`perfilusu`.`perfilusu_nombre` = '"+request.getParameter("perfilusu_nombre").toUpperCase()+"'";
    		 
    		 ResultSet SQL_BUSCA_RS = state.executeQuery(SQL_BUSCA);
    		 
    		 boolean ya_existe = false;
    		 
    		 while(SQL_BUSCA_RS.next()){
          	   
         	    if(SQL_BUSCA_RS.getInt("n")>0)ya_existe=true;
     	    }
    		 state.close();
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 if(!ya_existe){
    			if(Constantes.SYNCDB==1){
    			 ConBirt birtBD= new ConBirt();
    			 
    			 String estado_v_nv="VIGENTE";
    			 if(request.getParameter("select_estado").toString().equals("2")) estado_v_nv="NO VIGENTE";
    			 
    			 String pusu_nom=request.getParameter("perfilusu_nombre").toUpperCase();
    			 if(pusu_nom.length()>20) pusu_nom= pusu_nom.substring(0, 19);
    			 
    			 String insert_birt="INSERT INTO [PERFIL] "
     		      		+ "	(	[PERFIL].[PERF_ESTADO],"
     		      		+ "		[PERFIL].[PERF_FECHA_UM],"
     		      		+ "		[PERFIL].[PERF_NOMBRE],"
     		      		+ "		[PERFIL].[USU_ID_UM]"
     		      		+ "	) "
     		      		+ "	VALUES"
     		      		+ "		('"+estado_v_nv+"', getdate(), '"+pusu_nom+"', "+id_iusuario+")";
    			 System.out.println("BIRT: "+insert_birt);
    		      birtBD.ConsultaINUP(insert_birt);
    		     
    		      
    		        birtBD.Desconectar();
    			}
    			 
    			 String SQL_IN = " "
    			    		+ " INSERT INTO `perfilusu` (`perfilusu`.`perfilusu_nombre`, perfilusu_accion_alertada, perfilusu_ult_idper_exec, `perfilusu`.`estados_vig_novig_id`,`perfilusu`.`perfilusu_feccreacion`,`perfilusu`.`perfilusu_creador`) "
    			    		+ " VALUES "
    			    		+ " 	('"+request.getParameter("perfilusu_nombre").toUpperCase()+"',0,1, "+request.getParameter("select_estado")+",now(),"+id_iusuario+") ";
    			    
    			    System.out.println(SQL_IN);
    			    		state.executeUpdate(SQL_IN,Statement.RETURN_GENERATED_KEYS);
    			  
    			    		
    			    		ResultSet generatedKeys = null;
    			    		  generatedKeys = state.getGeneratedKeys();
    			    		  String id_perfil="";
    			    		  if (generatedKeys.next()) {
    			    			  id_perfil=generatedKeys.getString(1);
    			    		  }
    			    		  
    			    		  System.out.println("Nuevo id perfil: "+id_perfil);
    			    		  
    			    		  
    			    		  
    			    		  
    			    		  try{
    			    		String[] seleccionado = request.getParameterValues("permisos[]");
    			    		if(seleccionado!=null && seleccionado.length>0)
    			    		for (String id_per: seleccionado) {
    			    			if(id_per!=null && !id_per.equals("-1")){
    			    				String insertPermisoPerfil=""
    				    					+ " INSERT INTO `perfilusu_has_permisos` ("
    				    					+ " 	`perfilusu_has_permisos`.`perfilusu_id`,"
    				    					+ " 	`perfilusu_has_permisos`.`permisos_id` "
    				    					+ " ) "
    				    					+ " VALUES"
    				    					+ " 	('"+id_perfil+"', '"+id_per+"')";
    				    			
    				    			state.executeUpdate(insertPermisoPerfil);
    				    			System.out.println("Permiso insertado: "+id_per+" perfiln: "+id_perfil);
    			    			}
    			    			
    			    			
    			    		}
    			    		
    			    		  }catch(Exception e){
    			    			  e.printStackTrace();
    			    		  }
    			    	
    			    		  
    			    		  out.print("<script>alert('OPERACION REALIZADA CON EXITO'); location=\"/004/\"</script>");
    		 }else{
    			 out.print("<script>alert('PERFIL USUARIO YA REGISTRADO EN SISTEMA'); location=\"/004/\"</script>");
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
		    		+ " `permisos`.`permisos_id`"
		    		+ " FROM"
		    		+ " 	`menus`"
		    		+ " INNER JOIN `modulos` ON `modulos`.`Menus_id` = `menus`.`Menus_id`"
		    		+ " LEFT JOIN `permisos` ON `permisos`.`Modulos_id` = `modulos`.`Modulos_id`"
		    		+ " WHERE"
		    		+ " 	`modulos`.`estados_vig_novig_id` = 1"
		    		+ " ORDER BY"
		    		+ " 	`modulos`.`Menus_id`,"
		    		+ " 	`modulos`.`Modulos_codigo`,"
		    		+ "		`permisos`.`permisos_nombre` ";
		    
		    System.out.println(SQL_MENUS);
		    MENUS_RS =  state.executeQuery(SQL_MENUS);
		    
		    String last_id_perfil_sql="SELECT"
		    		+ " 	`perfilusu`.`perfilusu_id`"
		    		+ " FROM"
		    		+ " 	`perfilusu`"
		    		+ " ORDER BY"
		    		+ " 	`perfilusu`.`perfilusu_id` DESC"
		    		+ " LIMIT 1";
		    
		   
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> menus = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    	
		    while(MENUS_RS.next()){
        	   
        	    //SI HAY MODULOS PARA EL MENU, ENTONCES MOSTRAMOS EL MENU
		    	menus.add(MENUS_RS.getString("Modulos_id")+"/"+MENUS_RS.getString("Modulos_nombre")+"/"+MENUS_RS.getString("Modulos_codigo")+"/"+MENUS_RS.getString("tipo_permiso_id")+"/"+MENUS_RS.getString("permisos_id")+"/"+MENUS_RS.getString("permisos_nombre"));
        	    
    	    }
		  //System.out.println("SIZE LIST: "+menus.size());
		    	
		    	MENUS_RS.close();
		    	
		    	
		    	
		    	 ResultSet last_id_perfil_rs = state.executeQuery(last_id_perfil_sql);
				    System.out.println(last_id_perfil_sql);
				    int last_id_perfil=0;
				    if(last_id_perfil_rs.next()) last_id_perfil=last_id_perfil_rs.getInt("perfilusu_id");
		    	
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
                
                int arPosition=0;
                
                for(int x=0; x < menus.size(); x++){
                	
                	String[] parse_linea=menus.get(x).split("/");
                	//System.out.println(menus.get(x));
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
							per_reportes+=parse_linea[4]+"__"+parse_linea[5]+"y";
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
							per_reportes+=parse_linea[4]+"__"+parse_linea[5]+"y";
							break;
						
						default:
							break;
						}
        		    
                		mod_linea=id_modulo+"/"+modulo_codigo+"/"+modulo_nombre+"/"+per_ingresar+"/"+per_eliminar+"/"+per_modificar+"/"+per_consultar+"/"+per_reportes;
                		arMenus[arPosition]=mod_linea;
                		//System.out.println(mod_linea);
                		
                		
                	}
                	
                	
                }
                
                request.setAttribute("armenus", arMenus);
                request.setAttribute("correlativo", (last_id_perfil+1)+"");
                RequestDispatcher a = request.getRequestDispatcher("iperusu.jsp");
            	a.forward(request, response);
              
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error: "+ex);
		}
		
		
		
		
		
		
		
	}

}
