

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
 * Servlet implementation class Musuario2
 */
@WebServlet("/Musuario2")
public class Musuario2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Musuario2() {
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
		
		//grabar
		boolean existeclpr = false;
		boolean existeclpr2 = false;
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet rs_valclpr= null;
			Statement stateval2 = null;
			ResultSet rs_valclpr2= null;
			try {
				String UID=request.getParameter("Usuarios_id");
				String Usuarios_name = request.getParameter("Usuarios_nombre");
				String Usuarios_ape_p = request.getParameter("Usuarios_ape_p");
			   	String Usuarios_ape_m = request.getParameter("Usuarios_ape_m");
			   	String Usuarios_pass = request.getParameter("Usuarios_pass");
			   	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			   	String Usuarios_login = request.getParameter("Usuarios_login");
			   	String Usuarios_telefono = request.getParameter("Usuarios_telefono");
			   	String Usuarios_email = request.getParameter("Usuarios_email");
		    	String tipo_usu_id = request.getParameter("tipo_usu_id");
		    	String perfilusu_id = request.getParameter("perfilusu_id");
		    	String empresas_id = request.getParameter("empresas_id");
		    	
		    	String Usuarios_inicial= request.getParameter("Usuarios_inicial").toUpperCase();
		    	
		    	String Usuarios_all_emp=request.getParameter("todos");	
		    	String Usuarios_imei=request.getParameter("Usuarios_imei");	
		    	
		    	
		    	if(Usuarios_all_emp==null) Usuarios_all_emp="0";
		    	
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
		   		Connection conexion=(Connection) DriverManager.getConnection
		 	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		  		 stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
		  		 stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
		  		 stateval2 = (Statement) ((java.sql.Connection) conexion).createStatement();  
		  		 
			    String sql_valclpr="SELECT * FROM usuarios WHERE Usuarios_login='"+Usuarios_login.toUpperCase()+"' AND Usuarios_id<>"+UID;
			    rs_valclpr=stateval.executeQuery(sql_valclpr);
			    
			    String sql_valgrupo2="SELECT * FROM usuarios WHERE Usuarios_nombre='"+Usuarios_name.toUpperCase()+"' AND Usuarios_ape_p='"+Usuarios_ape_p.toUpperCase()+"' AND Usuarios_ape_m='"+Usuarios_ape_m.toUpperCase()+"'AND Usuarios_id<>"+UID;
	    		System.out.println("sql_valgrupo2: "+sql_valgrupo2);
	    		rs_valclpr2=stateval2.executeQuery(sql_valgrupo2);
			    
			    if(rs_valclpr.next()){
			    	stateval.close();
			    	rs_valclpr.close();
			    	existeclpr=true;
			    	//System.out.println("if(rs_valgrupos.next()){"+existeclpr);
			    }
			    if(rs_valclpr2.next()){
    			stateval.close();
    			rs_valclpr.close();
    			stateval2.close();
    			rs_valclpr2.close();
    			existeclpr2=true;   		
			    }
				if(existeclpr  || existeclpr2){conexion.close();}
	    		if(!existeclpr  && !existeclpr2){
	    			
	    			
	    			//insertamos en birt 
	    			
	    			if(Constantes.SYNCDB==1){
	       			 ConBirt birtBD= new ConBirt();
	       			 
	       			 String estado_v_nv="VIGENTE";
	       			 if(estados_vig_novig_id.equals("2")) estado_v_nv="NO VIGENTE";
	       			 
	       			 String nombre_enbirt=Usuarios_name.toUpperCase()+" "+Usuarios_ape_p.toUpperCase();
	       			 if(nombre_enbirt.length()>30)nombre_enbirt=nombre_enbirt.substring(0, 29);
	       			 
	       			 String insert_birt="UPDATE [USUARIO] "
	       			 		+ "	SET "
	       			 		+ "		[USUARIO].[USU_EMAIL] = '"+Usuarios_email.toUpperCase()+"',"
	       			 		+ "		[USUARIO].[USU_ESTADO] = '"+estado_v_nv+"',"
	       			 		+ "		[USUARIO].[USU_NOMBRE] = '"+nombre_enbirt+ "',"
	       			 		+ "	 	[USUARIO].[USU_PASSWORD] = '"+Usuarios_pass+"',"
	       			 		+ "		[USUARIO].[USU_TELEFONO] = '"+Usuarios_telefono+"',"
	       			 		+ "		[USUARIO].[USU_USERNAME] = '"+Usuarios_login.toUpperCase()+"',"
	       			 		+ "	 	[USUARIO].[USU_ID_UM] = "+Usuarios_ID+","
	       			 		+ "	 	[USUARIO].[USU_FECHA_UM] = GETDATE(), "
	       			 		+ "		[USUARIO].[USU_INICIAL] = '"+Usuarios_inicial+"'"
	       			 		+ "	WHERE"
	       			 		+ "		[USUARIO].[USU_ID] = "+UID;
	       			 
	       			 System.out.println("BIRT: "+insert_birt);
	       		      birtBD.ConsultaINUP(insert_birt);
	       		     
	       		      
	       		        birtBD.Desconectar();
	       			}
	    			
	    			
				    String SQL_UPDATE = ""
			   		+ "UPDATE usuarios "
			   		+ "SET Usuarios_nombre='"+Usuarios_name.toUpperCase()+"', Usuarios_accion_alertada=0,Usuarios_ult_idper_exec=22,estados_vig_novig_id="+estados_vig_novig_id+", "
			   		+ " tipo_usu_id="+tipo_usu_id+", empresas_id="+empresas_id+",perfilusu_id="+perfilusu_id+","
			   		+ " Usuarios_telefono='"+Usuarios_telefono+"',Usuarios_ape_p='"+Usuarios_ape_p.toUpperCase()+"',"
			   		+ "Usuarios_ape_m='"+Usuarios_ape_m.toUpperCase()+"',"
			   		+ " Usuarios_pass='"+Usuarios_pass+"',Usuarios_login='"+Usuarios_login.toUpperCase()+"',"
			   		+ "Usuarios_email='"+Usuarios_email.toUpperCase()+"',Usuarios_fecmod='"+Datenow+"', Usuarios_modificador="+Usuarios_ID+", Usuarios_inicial='"+Usuarios_inicial+"', "
			   		+ " Usuarios_all_emp='"+Usuarios_all_emp+"' , Usuarios_imei='"+Usuarios_imei+"'"
			   		+ " WHERE Usuarios_id="+UID;
				    System.out.println("UP: "+SQL_UPDATE); 
					stategrabar.executeUpdate(SQL_UPDATE);
					stategrabar.close();
					
					stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
					String up_delete=""
	    					+ " UPDATE usuarios_has_empresas SET "
	    					+ " estados_vig_novig_id=2 "
	    					+ " WHERE Usuarios_id="+UID;
					System.out.println("up_delete: "+up_delete);
    				stategrabar.executeUpdate(up_delete);
    				stategrabar.close(); 
    				stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
					 String[] seleccionado = request.getParameterValues("permisos[]");
			    		if(seleccionado!=null)for (String id_per: seleccionado) {
			    			if(id_per!=null && !id_per.equals("-1")){
			    				String insertusuemp=""
				    					+ " INSERT INTO `usuarios_has_empresas` ("
				    					+ " 	`usuarios_has_empresas`.`Usuarios_id`,"
				    					+ " 	`usuarios_has_empresas`.`empresas_id` "
				    					+ " ) "
				    					+ " VALUES"
				    					+ " 	('"+UID+"', '"+id_per+"')";
			    				System.out.println(""+insertusuemp);
			    				stategrabar.executeUpdate(insertusuemp);
			    			}	    			
			    		}
				    
		    		stategrabar.close(); 
			        conexion.close();
			           
			        RequestDispatcher rd_up = request.getRequestDispatcher("Musuario?mExito=OK");
			        rd_up.forward(request, response);
				}
			}catch(Exception ex){
			    ex.printStackTrace();
			}
			if(!existeclpr && !existeclpr2)return;
		}
				
		//fin grabar
		
		Statement state2 = null;
		ResultSet USUARIOS_RS = null;
		Statement state = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet TIPO_USU_RS = null;
		ResultSet PERFILUSU_RS = null;
		ResultSet PERUSUEMP_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		//:::::::::::::::::::::::::: SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
  		    String SQL_PERUSUEMP = "SELECT * FROM usuarios_has_empresas WHERE estados_vig_novig_id=1 AND Usuarios_id="+Integer.parseInt(request.getParameter("Usuarios_id"));
  		    System.out.println("SQL_PERUSUEMP: "+SQL_PERUSUEMP);
  		    PERUSUEMP_RS =  state.executeQuery(SQL_PERUSUEMP);
  		    //definimos un arreglo para los resultados
  		    ArrayList<String> usuariosempresas = new ArrayList<String>();
  		    //recorremos los resultados de la consulta
  		    while(PERUSUEMP_RS.next()){        	   
          	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  		    	usuariosempresas.add(PERUSUEMP_RS.getString("empresas_id"));
      	    }
  		  //System.out.println("SIZE LIST: "+usuariosempresas.size());	
  		  PERUSUEMP_RS.close();
  		  state.close();
  		  String[] ar_usuariosempresas = new String[usuariosempresas.size()];
            for(int x=0; x < usuariosempresas.size(); x++){
            	ar_usuariosempresas[x]=usuariosempresas.get(x);
            }
                  
            request.setAttribute("ar_usuariosempresas", ar_usuariosempresas);
  		//::::::::::::::::::::::::::SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
    		 
    		//:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
 		    String SQL_EMPRESAS = "SELECT * FROM empresas ORDER BY empresas_nombrenof";
 		    System.out.println(SQL_EMPRESAS);
 		    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
 		    //definimos un arreglo para los resultados
 		    ArrayList<String> empresas = new ArrayList<String>();
 		    //recorremos los resultados de la consulta
 		    while(EMPRESAS_RS.next()){        	   
         	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombrenof"));    
     	    }
 		  //System.out.println("SIZE LIST: "+empresas.size());	
 		  EMPRESAS_RS.close();
 		  state.close();
 		  String[] ar_empresas = new String[empresas.size()];
           for(int x=0; x < empresas.size(); x++){
         	 ar_empresas[x]=empresas.get(x);
           }
                 
           request.setAttribute("ar_empresas", ar_empresas);
 		//::::::::::::::::::::::::::fin sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
 		  
 		  //::::::::::::::::::::::::::::::::::::::::::sql tipo_usu para select option::::::::::::::::::::::::::::::::::::::
 		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
 		  String SQL_tipo_usu = "SELECT tipo_usu_id,tipo_usu_nombre FROM tipo_usu";
 		 System.out.println(SQL_tipo_usu);
 		    TIPO_USU_RS =  state.executeQuery(SQL_tipo_usu);	    
 		    //definimos un arreglo para los resultados		    
 		    ArrayList<String> tipo_usu = new ArrayList<String>();		   
 		    //recorremos los resultados de la consulta
 		    while(TIPO_USU_RS.next()){        	   
       	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	tipo_usu.add(TIPO_USU_RS.getString("tipo_usu_id")+"/"+TIPO_USU_RS.getString("tipo_usu_nombre"));
       	    }
 		 // System.out.println("SIZE LIST: "+tipo_usu.size());  	
 		  TIPO_USU_RS.close();	
 		  state.close();
 		  String[] ar_tipo_usu = new String[tipo_usu.size()];
           for(int x=0; x < tipo_usu.size(); x++){
         	  ar_tipo_usu[x]=tipo_usu.get(x);
           }
                 
           request.setAttribute("ar_tipo_usu", ar_tipo_usu);
 		//::::::::::::::::::::::::::::::::::::::::fin sql tipo_usu para select option::::::::::::::::::::::::::::::::::::::
 		  
 		//::::::::::::::::::::::::::::::::::::::::::sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
 		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
 		  String SQL_perfilusu = "SELECT perfilusu_id,perfilusu_nombre FROM perfilusu WHERE estados_vig_novig_id=1";
 		  System.out.println(SQL_perfilusu);
 		    PERFILUSU_RS =  state.executeQuery(SQL_perfilusu);		    
 		    //definimos un arreglo para los resultados		    
 		    ArrayList<String> perfilusu = new ArrayList<String>();		   
 		    //recorremos los resultados de la consulta
 		    while(PERFILUSU_RS.next()){        	   
     	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	perfilusu.add(PERFILUSU_RS.getString("perfilusu_id")+"/"+PERFILUSU_RS.getString("perfilusu_nombre").replace("/", " "));
     	    }
 		  //System.out.println("SIZE LIST: "+perfilusu.size());  	
 		  PERFILUSU_RS.close();
 		  String[] ar_perfilusu = new String[perfilusu.size()];
           for(int x=0; x < perfilusu.size(); x++){
         	  ar_perfilusu[x]=perfilusu.get(x);
           }
                 
           request.setAttribute("ar_perfilusu", ar_perfilusu);
 		//::::::::::::::::::::::::::::::::::::::::::fin sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
 		  state.close();
		    
		    String SQL_Usuarios = "SELECT u.*, "
		    		+ "		CONCAT_WS(' ' , "
 		    		+ "			`u1`.`Usuarios_nombre`, "
 		    		+ "			`u1`.`Usuarios_ape_p` "
 		    		+ "		) AS perfilusu_creador, "
 		    		+ "		DATE_FORMAT(u.usuarios_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion, "
 		    		+ "		IF ( "
 		    		+ "			u.usuarios_fecmod IS NULL,"
 		    		+ "			' ',"
 		    		+ "			DATE_FORMAT(u.usuarios_fecmod,'%d-%m-%Y %H:%i:%s')"
 		    		+ "		) AS perfilusu_fecmod,"
 		    		+ "		IF ("
 		    		+ "			u.usuarios_modificador IS NULL,"
 		    		+ "			' ',"
 		    		+ "			CONCAT_WS(' ', "
 		    		+ "			`u2`.`Usuarios_nombre`,"
 		    		+ "			`u2`.`Usuarios_ape_p`"
 		    		+ "			)"
 		    		+ "		) AS perfilusu_modificador "
		    		+ " FROM usuarios u"
		    		+ " INNER JOIN perfilusu p ON p.perfilusu_id=u.perfilusu_id"
		    		+ " INNER JOIN tipo_usu tu ON tu.tipo_usu_id=u.tipo_usu_id"
		    		+ " INNER JOIN estados_vig_novig es ON es.estados_vig_novig_id=u.estados_vig_novig_id"
		    		+ " INNER JOIN `usuarios` u1 ON `u`.`usuarios_creador` = `u1`.`Usuarios_id` "
 		    		+ " LEFT JOIN `usuarios` u2 ON `u`.`usuarios_modificador` = `u2`.`Usuarios_id` "
		    		+ " INNER JOIN empresas e ON e.empresas_id=u.empresas_id"
		    		+ " WHERE u.Usuarios_id="+Integer.parseInt(request.getParameter("Usuarios_id"));
		    System.out.println("SQL_Usuarios: "+SQL_Usuarios);
		    USUARIOS_RS =  state2.executeQuery(SQL_Usuarios);
		    if(USUARIOS_RS.next()){   	
		    	request.setAttribute("correlativo",USUARIOS_RS.getString("Usuarios_id")+"");
		    	request.setAttribute("Usuarios_nombre",USUARIOS_RS.getString("Usuarios_nombre"));
		    	request.setAttribute("estados_vig_novig_id1",USUARIOS_RS.getString("estados_vig_novig_id"));
		    	request.setAttribute("Usuarios_ape_p",USUARIOS_RS.getString("Usuarios_ape_p"));
		    	request.setAttribute("Usuarios_ape_m",USUARIOS_RS.getString("Usuarios_ape_m"));
		    	request.setAttribute("Usuarios_pass",USUARIOS_RS.getString("Usuarios_pass"));
		    	request.setAttribute("Usuarios_login",USUARIOS_RS.getString("Usuarios_login"));
		    	request.setAttribute("Usuarios_telefono",USUARIOS_RS.getString("Usuarios_telefono"));
		    	request.setAttribute("Usuarios_email",USUARIOS_RS.getString("Usuarios_email"));
		    	request.setAttribute("tipo_usu_id1",USUARIOS_RS.getString("tipo_usu_id"));
		    	request.setAttribute("perfilusu_id1",USUARIOS_RS.getString("perfilusu_id"));
		    	request.setAttribute("empresas_id1",USUARIOS_RS.getString("empresas_id"));		
		    	request.setAttribute("Usuarios_inicial",USUARIOS_RS.getString("Usuarios_inicial"));
		    	request.setAttribute("Usuarios_all_emp",USUARIOS_RS.getString("Usuarios_all_emp"));
		    	request.setAttribute("Usuarios_imei",USUARIOS_RS.getString("Usuarios_imei"));
 		    	
		    	
		    	String fec_crea=USUARIOS_RS.getString("perfilusu_feccreacion");
		    	String user_crea=USUARIOS_RS.getString("perfilusu_creador");
		    	String fec_mod=USUARIOS_RS.getString("perfilusu_fecmod");
		    	String user_mod=USUARIOS_RS.getString("perfilusu_modificador") ;
		    	
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
		    	//System.out.println(CLPR_RS.getString("estados_vig_novig_nombre")+" "+CLPR_RS.getString("grupos_nombre"));
    	    }
		    USUARIOS_RS.close();
		    state2.close();	    
		    
		   
		}catch(Exception ex){
            ex.printStackTrace();
        }
		
		System.out.println("bool: "+existeclpr);
		String msg="";
		if(existeclpr){
			msg="?Exito=NOK1";
			System.out.println(msg);
		}if(existeclpr2){
			msg="?Exito=NOK2";
			System.out.println(msg);
		}
		
		if(existeclpr || existeclpr2){
			String Usuarios_name = request.getParameter("Usuarios_nombre");
			String Usuarios_ape_p = request.getParameter("Usuarios_ape_p");
			String Usuarios_ape_m = request.getParameter("Usuarios_ape_m");
			String Usuarios_login = request.getParameter("Usuarios_login");
			String Usuarios_pass = request.getParameter("Usuarios_pass");
			String Usuarios_email = request.getParameter("Usuarios_email");
			String Usuarios_telefono = request.getParameter("Usuarios_telefono");
			String tipo_usu_id = request.getParameter("tipo_usu_id");
			String perfilusu_id = request.getParameter("perfilusu_id");				
	    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
	    	String empresas_id = request.getParameter("empresas_id");
	    	ArrayList<String> miusuemp = new ArrayList<String>();
	    	 String[] seleccionado = request.getParameterValues("permisos[]");
	    		if(seleccionado!=null)for (String id_per: seleccionado) {
	    			if(id_per!=null && !id_per.equals("-1")){
	    				miusuemp.add(id_per);
	    			}
	    		}
	    	//System.out.println("SIZE LIST: "+miusuemp.size());  	
	   		
	   		String[] ar_miusuemp = new String[miusuemp.size()];
	        for(int x=0; x < miusuemp.size(); x++){
	        	ar_miusuemp[x]=miusuemp.get(x);
	        }
	                   
	        request.setAttribute("ar_usuariosempresas", ar_miusuemp);
			request.setAttribute("Usuarios_nombre",Usuarios_name);
	    	request.setAttribute("Usuarios_ape_p",Usuarios_ape_p);
	    	request.setAttribute("Usuarios_ape_m",Usuarios_ape_m);
	    	request.setAttribute("Usuarios_login",Usuarios_login);
	    	request.setAttribute("Usuarios_pass",Usuarios_pass);
	    	request.setAttribute("Usuarios_email",Usuarios_email);
	    	request.setAttribute("Usuarios_telefono",Usuarios_telefono);
	    	request.setAttribute("tipo_usu_id",tipo_usu_id);
	    	request.setAttribute("perfilusu_id",perfilusu_id);
	    	request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
	    	request.setAttribute("empresas_id",empresas_id);
		}
		
		System.out.println("msg: "+msg);
		RequestDispatcher rd = request.getRequestDispatcher("musuario2.jsp"+msg);
        rd.forward(request, response);
		
	}

}
